
import React from 'react'
import { Link } from 'react-router-dom';

class RegisterConfirmation extends React.Component {

    constructor(props) {
        super(props)
    }

    componentDidMount() {
        let userId=this.props.match.params.userId;
        let sessionId=this.props.match.params.sessionId;

        console.log('UserId:: '+ userId+ ' sessionId:: '+ sessionId)
    }

    render() {
        return <div>
            <h1 className="display-5 text-center my-3">Thank you to Enroll in our Course</h1>
           <p className="text-center"><Link to="/dashboard" >Back to Dashboard</Link></p> 
        </div>
    }
}

export default RegisterConfirmation;