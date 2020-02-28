import React from 'react'
import UserService from '../../api/services/UserService.js'
import AuthenticationService from '../../api/services/AuthenticationService.js';
import { Link } from 'react-router-dom';
import SessionService from '../../api/services/SessionService.js';
import moment from 'moment'

class Session extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            session : {
                sessionList:[]
            }
        }
    }

    componentDidMount() {
        AuthenticationService.handleInterceptor();
        this.sessionRefresh();

       
    }

    loadSessions=()=> {
        return SessionService.findAll();
    }

    sessionRefresh=()=> {
        this.loadSessions()
        .then(response=>{            
            let sessionUpdate={...this.state.session};            
            sessionUpdate.sessionList=response.data;            
            this.setState({session:sessionUpdate})
        })
    }

    editSession=(sessionId,e)=> {        
        this.props.history.push(`/sessions/${sessionId}`);
    }

    deleteSession=(sessionId,e)=> {       
        SessionService.delete(sessionId)
        .then(()=>{
            this.sessionRefresh()
        })
        
    }

    addNewSession=()=> {
        const sessionId=-1;
        this.props.history.push(`/sessions/${sessionId}`);
    }

   

    render() {
        const {session}=this.state;
        
        let sessionRender=
        session.sessionList.map(session=> {
            return  <tr key={session.id}>
                        <td>{session.sessionName}</td>
                        <td>{moment(session.startDate).format('YYYY-MM-DD')}</td>
                        <td>{session.location}</td>
                        <td>{session.training.description}</td>                        
                        <td>{session.user.firstname+' '+ session.user.lastname}</td>
                        <td><button 
                            onClick={(e)=>this.editSession(session.id,e)}
                            className="btn btn-primary">
                            Edit</button></td>

                            <td><button 
                            onClick={(e)=>this.deleteSession(session.id,e)}
                            className="btn btn-danger">
                            Delete</button></td>
                    </tr>
                })
        

        return (
            <div className="container">
                <h1 className="text-center">Session Maintanace</h1>
                <div>
                    <table className="table my-3">
                        <thead>
                            <tr>
                                <th>Session Name</th>
                                <th>Start Date</th>
                                <th>Location</th>
                                <th>Training</th>
                                <th>Instructor</th>
                                <th colSpan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {sessionRender}                       
                        </tbody>

                    </table>
                </div>
                <button 
                className="btn btn-success"
                onClick={this.addNewSession}
                >New Session</button>
            </div>
        )
    }

}

export default Session;