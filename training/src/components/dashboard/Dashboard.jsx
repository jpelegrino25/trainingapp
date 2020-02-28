import React,{Component} from 'react'
import './dashboard.css'
import UserService from '../../api/services/UserService.js'
import {Link} from 'react-router-dom'

class Dashboard extends Component {


    constructor(props) {
        super(props)
    }

    componentDidMount() {
        
    }

    manageUser=()=> {
        this.props.history.push("/users");
    }

    render() {
        return <div className="clearfix container Dashboard">
            <h1 className="display-3 text-center">Dashboard</h1>
            
            <div className="row clearfix">
               
                <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                            <button className="btn btn-lg button-card" ><Link to="/users">
                                <i className="fas fa-users"></i>USERS</Link></button>
                        </div>
                    </div>       
                </div>


                <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" ><Link to="/trainings">
                        <i className="fas fa-address-book"></i>Training</Link></button>
                        </div>
                    </div>       
                </div>   

                 <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" ><Link to="/sessions">
                        <i className="fas fa-address-book"></i>Session</Link></button>
                        </div>
                    </div>       
                </div>               

                
            </div>
                


          
            
        </div>
    }
}

export default Dashboard;