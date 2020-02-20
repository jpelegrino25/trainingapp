import React,{Component} from 'react'
import {BrowserRouter as Router,Route, Switch} from 'react-router-dom'
import LoginComponent from './login/LoginComponent'
import Dashboard from './dashboard/Dashboard';
import UserComponent from './UserComponent';

class TrainingApp extends Component {

    render() {

        return(
            <Router>
                <>
                <Switch>
                    <Route path="/" exact component={Dashboard}/>
                    <Route path="/login"  component={LoginComponent}/>
                    <Route path="/dashboard" component={Dashboard}/>
                    <Route path="/users" component={UserComponent}/>

                </Switch>
                </>
            </Router>
        )

    }
}

export default TrainingApp;