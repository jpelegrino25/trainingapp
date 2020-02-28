import React from 'react'
import UserService from '../../api/services/UserService';
import AuthenticationService from '../../api/services/AuthenticationService';
import './session.css'
import SessionService from '../../api/services/SessionService';
import TrainingService from '../../api/services/TrainingService';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

import ReactDatePicker from 'react-date-picker-cs';
import moment from 'moment';

const SESSION_DEFAULT={
    "id": null,
    "startDate": new Date(),
    "location": "",
    "capacity": 0,
    "user":{"id":-1},
    "training": {"id": -1},
    "status":{"id":1}
}

const useStyles = makeStyles(theme => ({
    container: {
      display: 'flex',
      flexWrap: 'wrap',
    },
    textField: {
      marginLeft: theme.spacing(1),
      marginRight: theme.spacing(1),
      width: 200,
    },
  }));

class SessionMaintanance extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            session:null,           
            createRender:false,
            editRender:false,
            userList:[],
            trainingList:[],
            classes:null

        }
    }

    componentDidMount() {
        
        let sessionId=this.props.match.params.id;
        AuthenticationService.handleInterceptor();
        if(sessionId==-1) {
            this.setState({session:SESSION_DEFAULT,createRender:true,editRender:false,classes:useStyles})
        }else {           
            this.loadSession(sessionId)            
        }

        this.loadUsers();
        this.loadTrainings();
        
        
       
    }

    loadSession=(sessionId)=> {
        SessionService.findById(sessionId)
        .then(response=> {            
            this.setState({session:response.data,createRender:false,editRender:true,classes:useStyles})
        }).catch(err=>console.log(err))
    }

   
    changeField=(e)=>{
        const {name,value,type}=e.target;
        const {session}=this.state;
        let dataType = e.currentTarget.getAttribute('data-type');
        let sessionUpdate;

        
        if(type==='select-one')  { 
           sessionUpdate=this.selectFieldSelection(dataType,session,value);
        }  else {
            sessionUpdate={...session,[name]:value}
        }   
        
       
        this.setState({session:sessionUpdate})
    }

   
    selectFieldSelection=(dataType,session,value)=> {
        let sessionUpdate;
        switch(dataType) {
            case 'user':
                sessionUpdate={...session};
                sessionUpdate.user.id=value;
                break;
            case 'training':
                sessionUpdate={...session};
                sessionUpdate.training.id=value;
                break;
        }

        return sessionUpdate;
    }

    onClickEdit=(e)=> {
        e.preventDefault()
        const {session,editRender}=this.state;
       
        if(editRender) {
            this.updateSession(session);
        }else {            
            this.saveSession(session);
        }
       
    }

    updateSession=(session)=> {
        SessionService.update(session)
        .then( ()=>{
            this.props.history.push('/sessions')
        }
        ).catch(err=>console.log(err))
    }

    saveSession=(session)=>{
        SessionService.save(session)
        .then(()=> {
        this.props.history.push('/sessions')})
        .catch(err=>console.log(err))
    }

    loadUsers=()=>{
        UserService.findAll()
        .then(response=>{
            this.setState({userList:response.data})
        }).catch(err=>console.log(err))
    }

    loadTrainings=()=> {
        TrainingService.fingAll()
        .then(response=>{
            this.setState({trainingList:response.data})
        }).catch(err=>console.log(err))
    }

    render() {

        const {session,createRender,editRender,userList,trainingList,classes}=this.state;

        let renderUser= (session) &&      
        <form className={classes.container} noValidate>
        <div className="form-group">
            <label>Location</label>
            <input type="text" 
            placeholder="Location"
            name="location"
            value={session && session.location || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

        <div className="form-group">
            <label>Capacity</label>
            <input type="number" 
            placeholder="Capacity"
            name="capacity"
            value={session && session.capacity || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

        <div className="form-group">
            <label>Start Date</label>
            <TextField
                id="date"                
                name="startDate"
                type="date"                
                value={session && session.startDate}
                onChange={this.changeField}
                className={'form-control '+classes.textField}
                InputLabelProps={{
                shrink: true,
                }}
        />
        </div>

        
        <div className="form-group">
            <label>Instructor</label>
            <select                         
            name="user"
            data-type='user'
            value={session.user.id}
            onChange={this.changeField}
            className="form-control">
                {userList.map((user,index)=> {
                    return <option key={user.id} value={user.id}>{user.firstname+' '+user.lastname}</option>
                })}                            

            </select>
        </div>

        <div className="form-group">
            <label>Training</label>
            <select                         
            name="training"
            data-type='training'
            value={session.training.id}
            onChange={this.changeField}
            className="form-control">
                {trainingList.map((training,index)=> {
                    return <option key={training.id} value={training.id}>{training.description}</option>
                })}                            

            </select>
        </div>

       {editRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Edit</button> }

        {createRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Create</button> }
    </form>
        
        return (
            <div className="container">
                {editRender && <h1 className="text-center">Edit User</h1>}
                {createRender && <h1 className="text-center">Create User</h1>}
                <div className="bg-success maintananceLayout">
                    {renderUser}
                    
                </div>
            </div>
        )

    }
}

export default SessionMaintanance;