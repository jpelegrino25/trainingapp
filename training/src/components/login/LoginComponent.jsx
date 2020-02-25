import React,{Component} from 'react'
import AuthenticationService from '../../api/services/AuthenticationService.js'
import './login.css';


class LoginComponent extends Component {

    constructor(props) {
        super(props)
        this.state={
            user : {
                username:'',
                password:''
            }
           
        }
    }

    handleChangeField=(e)=> {
        let {name,value}=e.target;
        const usernameUpdate={...this.state.user,
        [name]:value}

        this.setState({
            user:usernameUpdate
        })
    }

    login=(e)=> {
        e.preventDefault();
        const {username,password}=this.state.user;
        
        AuthenticationService.authenticatedBasic(username,password)
        .then(()=> {
            AuthenticationService.registerUser(username,password)
            this.props.history.push("/dashboard");
        })
        .catch(err=>console.log(err));

    }

    render() {
        const {username,password}=this.state.user;
        return (
            <div className="container">

                <h1 className="text-center"></h1>

                <div className="Login">
                    <form>
                        <div className="form-row mb-3">
                            <div className="col">
                                <label>User Name:</label>
                            </div>
                            <div className="col">
                                <input type="text" 
                                value={username}
                                name="username"
                                onChange={this.handleChangeField}
                                placeholder="User Name"
                                className="form-control"/>
                            </div>
                        </div>

                        <div className="form-row mb-3">
                            <div className="col">
                                <label>Password:</label>
                            </div>
                            <div className="col">
                                <input type="password"
                                value={password}
                                name="password"
                                placeholder="Password"
                                onChange={this.handleChangeField} 
                                className="form-control"/>
                            </div>
                        </div>

                        <div className="form-row">
                            <div className="col">
                            <button className="btn btn-primary" 
                            onClick={this.login}>Login</button>
                            </div>                           
                        </div>

                       


                    </form>
                </div>

               
                
            </div>
        )

    }
    
}

export default LoginComponent;