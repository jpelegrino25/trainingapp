import React from 'react'
import UserService from '../../api/services/UserService.js';
import AuthenticationService from '../../api/services/AuthenticationService.js';
import SessionService from '../../api/services/SessionService.js';

class Trainer extends React.Component {

    constructor(props) {
        super(props)
        this.state={
            sessionList:[],
            session:{
                id:-1                
            },
            user:null
        }
    }

    componentDidMount() {
        AuthenticationService.handleInterceptor();
        this.getSessions();
    }

    getSessions=()=> {
        let userLogin=JSON.parse(AuthenticationService.getLoginUser());
        SessionService.findSessionsByInstructor(userLogin.id)
        .then(response=>{
            this.setState({sessionList:response.data,user:userLogin})
        })
    }

    changeField=(e)=>{
        const {name,value,type}=e.target;
        let sessionUpdate= {...this.state.session};
        
        sessionUpdate.id=value;
       
        this.setState({session:sessionUpdate})
    }

    showList=(e,user,session)=> {
        this.props.history.push(`/attendance/${user.id}/${session.id}`);
    }


    render() {
        const {sessionList,session,user}=this.state;

        let trainerSessionRender=
        (sessionList) &&      
        
        <div className="form-group">
        <label>Session</label>
        <select                         
        name="session"
        data-type='user'
        value={session.id}
        onChange={this.changeField}
        className="form-control">
            {sessionList.map((session,index)=> {
                return <option key={session.id} value={session.id}>{session.sessionName}</option>
            })}                            

        </select>
        <button className="btn btn-primary my-2" onClick={(e)=>this.showList(e,user,session)}>Show List</button>
    </div>

        return <div className="container">
            <h1 className="text-center">Pick a Session</h1>
            <div className="maintananceLayout">
                {trainerSessionRender}
            </div>
        </div>
    }
}

export default Trainer;