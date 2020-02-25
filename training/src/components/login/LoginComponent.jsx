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

    login=()=> {
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
            <div className="LoginComponent container">


                <div className="wrapper fadeInDown">
                    <div id="formContent">
                        
                        <h2 className="active"> Sign In </h2>
                        <h2 className="inactive underlineHover">Sign Up </h2>

                    
                        <div className="fadeIn first">
                        {/* <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" /> */}
                        </div>

                        
                        <div>
                            <input type="text" id="login" className="fadeIn second"                            
                            name="username"
                            placeholder="login" 
                            value={username}
                            onChange={this.handleChangeField}
                            
                            />

                            <input type="text" id="password" className="fadeIn third"
                            name="password" 
                            placeholder="password" 
                            value={password}
                            onChange={this.handleChangeField}
                            />
                            <input type="button" onClick={this.login} className="fadeIn fourth" value="Log In"/>
                        </div>

                        
                        <div id="formFooter">
                        {/* <a className="underlineHover" href="http://google.com" target="_blank">Forgot Password?</a> */}
                        </div>

                    </div>
                </div>

                
            </div>
        )

    }
    
}

export default LoginComponent;