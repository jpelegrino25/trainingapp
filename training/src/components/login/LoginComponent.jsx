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
        .then((response)=> {
            AuthenticationService.registerUser(username,password);
            AuthenticationService.handleInterceptor();
            sessionStorage.setItem('authorities',JSON.stringify(response.data));
            this.props.history.push("/dashboard");
            
        })
        .catch(err=>console.log(err));

    }


    render() {
        const {username,password}=this.state.user;
        return (
            <div className=" my-5">

                <div className="Login">
                <div className="main">   
                   
                        <header>
                            <h1>Account Login</h1>
                        </header>
                    <div className="content">
                        
                        <div className="form-group">
                            <input type="text" 
                            className="form-control"
                            placeholder="User Name"
                            value={username}
                            name="username"
                            onChange={this.handleChangeField}
                            />
                        </div>

                        <div className="form-group">
                            <input type="password" 
                            className="form-control"
                            placeholder="Password"
                            value={password}
                            name="password"                                
                            onChange={this.handleChangeField} 
                            />
                        </div>
                        <div className="form-group">
                            <button 
                            className="btn btn-primary btn-block"
                            onClick={this.login}
                            >Sign In</button>
                        </div>
                        
                    </div>


                </div>    

                </div>            
                
            </div>
        )

    }
    
}

export default LoginComponent;