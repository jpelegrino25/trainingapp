import React from 'react'
import UserService from '../../api/services/UserService';
import AuthenticationService from '../../api/services/AuthenticationService';
import './training.css'
import TrainingService from '../../api/services/TrainingService';

const TRAINING_DEFAULT={
    "id": null,
    "description": "",   
    "status":{id:1}
}

class TrainingMaintanance extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            training:null,            
            createRender:false,
            editRender:false

        }
    }

    componentDidMount() {
        
        let trainingId=this.props.match.params.id;
        AuthenticationService.handleInterceptor();
        if(trainingId==-1) {
            this.setState({training:TRAINING_DEFAULT,createRender:true,editRender:false})
        }else {           
            this.loadTraining(trainingId)            
        }
        
       
    }

    loadTraining=(trainingId)=> {
        TrainingService.findById(trainingId)
        .then(response=> {            
            this.setState({training:response.data,createRender:false,editRender:true})
        }).catch(err=>console.log(err))
    }

   

    changeField=(e)=>{
        const {name,value,type}=e.target;
        let trainingUdated={...this.state.training,[name]:value}
        
        
       
        this.setState({training:trainingUdated})
    }

    
    onClickEdit=(e)=> {
        e.preventDefault()
        const {training,editRender}=this.state;
       
        if(editRender) {
            this.updateUser(training);
        }else {           
            this.saveUser(training);
        }
       
    }

    updateUser=(training)=> {
        TrainingService.update(training)
        .then( ()=>{
            this.props.history.push('/trainings')
        }
        ).catch(err=>console.log(err))
    }

    saveUser=(training)=>{
        TrainingService.save(training)
        .then(()=> {
        this.props.history.push('/trainings')})
        .catch(err=>console.log(err))
    }

    render() {

        const {training,createRender,editRender}=this.state;

        let renderTraining= (training) &&      
        <form>
        <div className="form-group">
            <label>Training Name</label>
            <input type="text" 
            placeholder="Training Name"
            name="description"
            value={training && training.description || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>      
      

       {editRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Edit</button> }

        {createRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Create</button> }
    </form>
        
        return (
            <div className="container">
                {editRender && <h1 className="text-center">Edit Training</h1>}
                {createRender && <h1 className="text-center">Create Training</h1>}
                <div className="bg-success trainingEditLayout">
                    {renderTraining}
                    
                </div>
            </div>
        )

    }
}

export default TrainingMaintanance;