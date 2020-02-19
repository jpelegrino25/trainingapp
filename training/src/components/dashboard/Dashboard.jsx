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
        return <div className="Dashboard">

            <h1 className="title">Dashboard</h1>

            <div className="layout">

                <div className="row">
                    <div className="col-lg-4 col-sm-12 col-md-4">
                        <h1 className="text-center">Manage Users</h1>
                        <div className="jumbotron rounded-circle">
                            <div className="m-0">

                                <button className="btn btn-success btn-center" onClick={this.manageUser}>
                                    <span><i class="fas fa-users"></i></span>
                                </button>

                            </div>
                            
                        </div>
                    </div>

                   
                </div>

                
            </div>

        </div>
    }
}

export default Dashboard;