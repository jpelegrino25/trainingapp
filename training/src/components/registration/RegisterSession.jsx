import React from 'react'
import moment from 'moment'
import SessionService from '../../api/services/SessionService.js'
import AuthenticationService from '../../api/services/AuthenticationService.js'
import RegisterService from '../../api/services/RegisterService.js'

const REGISTER_SESSION=
    {
        "registerSessionId": {
            "session":{
                "id":null
            },
            "user":{
                "id":null
            }
        }
    }

class RegisterSession extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            sessions:[],
            user:null,
            registerSession:null
        }
    }

    componentDidMount() {

        AuthenticationService.handleInterceptor();
        this.openSessions();

    }

    openSessions=()=> {
        let loginUser=JSON.parse(AuthenticationService.getLoginUser());
        SessionService.findAllAvailables(loginUser.id)
        .then(response=>{
            this.setState({sessions:response.data,user:loginUser,registerSession:REGISTER_SESSION});
        }).catch(err=>console.log(err))

    }

    enroll=(userId,sessionId)=> {
        
        let registerSession={...this.state.registerSession}
        registerSession.registerSessionId.session.id=sessionId;
        registerSession.registerSessionId.user.id=userId;

        RegisterService.subscribeToSession(registerSession)
        .then(()=>{
            console.log('Successful Save');
            this.props.history.push(`/confirmationreg/${userId}/${sessionId}`)
        }).catch(err=>console.log(err))
    }

    render() {

        const {sessions,user}=this.state;

        let sessionRender=
        sessions.map(session=> {
            return  <tr key={session.id}>
                        <td>{session.sessionName}</td>
                        <td>{session.training.description}</td> 
                        <td>{session.user.firstname+' '+ session.user.lastname}</td>
                        <td>{session.location}</td>
                        <td>{moment(session.startDate).format('YYYY-MM-DD')}</td>
                        <td><button 
                        className="btn btn-primary"
                        onClick={()=>this.enroll(user.id,session.id)}
                        >Enroll</button></td>
                    </tr>
                })

        return <div>

            <table className="table">
                <thead>
                    <tr>
                        <th>Session Name</th>
                        <th>Training</th>
                        <th>Instructor</th>
                        <th>Location</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    {sessionRender}
                </tbody>
            </table>


        </div>
    }
}

export default RegisterSession;