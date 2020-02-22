import React,{Component} from 'react'
import './dashboard.css'
import UserService from '../../api/services/UserService.js'

class Dashboard extends Component {


    constructor(props) {
        super(props)
    }

    componentDidMount() {
        UserService.findAll()
        .then(data=>{
            console.log(data);
        }).catch(err=>{
            console.log(err)
        })
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
                            <button className="btn btn-lg button-card" ><i className="fas fa-users"></i>USERS</button>
                        </div>
                    </div>       
                </div>


                <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" >
                        <i className="fas fa-chalkboard-teacher"></i>
                            <span className="mx">INSTRUCTORS</span>
                        </button>
                        </div>
                    </div>       
                </div>               

                
            </div>
                


          
            
        </div>
    }
}

export default Dashboard;