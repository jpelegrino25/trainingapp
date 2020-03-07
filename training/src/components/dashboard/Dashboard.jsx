import React,{Component} from 'react'
import './dashboard.css'
import AuthenticationService from '../../api/services/AuthenticationService.js'
import * as Constant from '../../Constant.js'
import {Link} from 'react-router-dom'

class Dashboard extends Component {


    constructor(props) {
        super(props)
    }

  

    manageUser=()=> {
        this.props.history.push("/users");
    }

    render() {
        let authorities=JSON.parse(AuthenticationService.getLoginUser()).rol.authorities;
        
        return <div className="clearfix container Dashboard">
            <h1 className="display-3 text-center">Dashboard</h1>
            
            <div className="row clearfix">
               
                {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_USER) && <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                            <button className="btn btn-lg button-card" ><Link to="/users">
                                <i className="fas fa-users"></i>USERS</Link></button>
                        </div>
                    </div>       
                </div>}


               {AuthenticationService.
               hasAuthority(authorities,
               Constant.AUTH_TRAINING) && <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" ><Link to="/trainings">
                        <i className="fas fa-address-book"></i>Training</Link></button>
                        </div>
                    </div>       
                </div>  } 

                 {AuthenticationService.
                    hasAuthority(authorities,
                    Constant.AUTH_SESSION) && <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" ><Link to="/sessions">
                        <i className="fas fa-address-book"></i>Session</Link></button>
                        </div>
                    </div>       
                </div> }              

                
            </div>
                


          
            
        </div>
    }
}

export default Dashboard;