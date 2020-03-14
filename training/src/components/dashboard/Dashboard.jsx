import React,{Component} from 'react'
import './dashboard.css'
import AuthenticationService from '../../api/services/AuthenticationService.js'
import * as Constant from '../../Constant.js'
import {Link} from 'react-router-dom'
import userimg from '../../resources/user.png'
import trainingimg from '../../resources/training.png'
import sessionimg from '../../resources/session.png'
import availablesession from '../../resources/available.png'
import attendanceimg from '../../resources/attendance.png'
import registrationimg from '../../resources/registration.png'


class Dashboard extends Component {


    constructor(props) {
        super(props)
    }

    manageUser=()=> {
        this.props.history.push("/users");
    }

    render() {
        let authorities=JSON.parse(AuthenticationService.getLoginUser()).rol.authorities;
        
        return <div className="container Dashboard">
            <h1 className="display-3 text-center">Dashboard</h1>
            <div className="card-columns">
                
            {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_USER) && <div className="card">
                    <div className="card-header">
                        <h4>Users</h4>
                    </div>

                    <div className="card-body">
                        <Link to="/users"><img src={userimg}/></Link>
                    </div>
                </div> }


                {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_TRAINING) && 
                    <div className="card">
                        <div className="card-header">
                            <h4>Training</h4>
                        </div>

                        <div className="card-body">
                           <Link to="/trainings"><img src={trainingimg}/></Link> 
                        </div>
                    </div> 
                    }

                {AuthenticationService.
                hasAuthority(authorities,
                Constant.AUTH_SESSION) && 
                <div className="card">
                    <div className="card-header">
                        <h4>Session</h4>
                    </div>

                    <div className="card-body">
                      <Link to="/sessions"><img src={sessionimg}/></Link>  
                    </div>
                </div> 
                }

                {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_AVAILABLE_SESSION) && 
                <div className="card">
                    <div className="card-header">
                        <h4>Available Session</h4>
                    </div>

                    <div className="card-body">
                        <Link to="/availableSessions"><img src={availablesession}/></Link>
                    </div>
                </div> 
                }

                {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_TRAINER) && 
                <div className="card">
                    <div className="card-header">
                        <h4>Attendance</h4>
                    </div>

                    <div className="card-body">
                       <Link to="/trainer"><img src={attendanceimg}/></Link> 
                    </div>
                </div> 
                }

                {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_TRAINEES) && 
                <div className="card">
                    <div className="card-header">
                        <h4>Trainees Registration</h4>
                    </div>

                    <div className="card-body">
                        <Link to="/register"><img src={registrationimg}/></Link>
                    </div>
                </div> 
                }
                

            </div>
          
            
        </div>
    }
}

export default Dashboard;