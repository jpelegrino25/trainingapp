import React from 'react'
import AuthenticationService from '../../api/services/AuthenticationService';

class Logout extends React.Component {

    constructor(props) {
        super(props)
    }

    componentDidMount() {
        AuthenticationService.unRegisterUser();
        
    }

    render() {
        return <div>
            <p className="text-center my-3">Thank you for using our application</p>
        </div>
    }
}

export default Logout;