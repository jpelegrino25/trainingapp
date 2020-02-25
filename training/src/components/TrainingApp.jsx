import React,{Component} from 'react'
import {BrowserRouter as Router,Route, Switch} from 'react-router-dom'
import LoginComponent from './login/LoginComponent'
import Dashboard from './dashboard/Dashboard';
import UserComponent from './users/UserComponent';
import AuthenticatedRoute from './AuthenticatedRoute';
import EditUserComponent from './users/EditUserComponent';

class TrainingApp extends Component {

    render() {

        return(
            <Router>
                <>
                <Switch>
                    <Route path="/" exact component={LoginComponent}/>
                    <Route path="/login"  component={LoginComponent}/>
                    <AuthenticatedRoute path="/dashboard" component={Dashboard}/>
                    <AuthenticatedRoute path="/users/:id" component={EditUserComponent}/>
                    <AuthenticatedRoute path="/users" component={UserComponent}/>
                    

                </Switch>
                </>
            </Router>
        )

    }
}

export default TrainingApp;