import React from 'react'
import AuthenticationService from '../api/services/AuthenticationService'
import { Redirect,Route } from 'react-router-dom'

class AuthenticatedComponent extends React.Component {

    render() {
        if(AuthenticationService.IsUserLogin()) {
            return <Route {... props}/>
        }else {
            return <Redirect to='/login'/>
        }
    }
}

export default AuthenticatedComponent;