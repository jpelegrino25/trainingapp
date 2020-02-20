import React,{Component} from 'react'
import './dashboard.css'

class Dashboard extends Component {


    constructor(props) {
        super(props)
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
                            <button className="btn btn-lg button-card" ><i class="fas fa-users"></i>USERS</button>
                        </div>
                    </div>       
                </div>


                <div className="col-lg-3 bg-success rounded">
                    <div className="card">
                        <div className="card-body">
                        <button className="btn btn-lg button-card" >
                        <i class="fas fa-chalkboard-teacher"></i>
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