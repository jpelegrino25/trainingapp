import React from 'react'
import { Link } from 'react-router-dom'

class Navigation extends React.Component {

    render() {
        return <div>
           <nav className="navbar navbar-expand-lg navbar-light bg-dark">
            <Link className="navbar-brand text-white" to="/dashboard">TrainingApp</Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">  
            <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/todos">home</Link>
                    </li>
                   <li className="nav-item">
                        <Link className="nav-link text-white" to="/todos">todos</Link>
                    </li>
               </ul>              
               
               <ul className="navbar-nav justify-content-end navbar-collapse">
                   <li className="nav-item">
                        <Link className="nav-link text-white" to="/login">Login</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link text-white"  to="/logout">Logout</Link>
                    </li>
               </ul>
               
            </div>
        </nav>
           
        </div>
    }
}

export default Navigation;