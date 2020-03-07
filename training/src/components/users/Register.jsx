import React from 'react'
import UserService from '../../api/services/UserService';
import AuthenticationService from '../../api/services/AuthenticationService';
import './user.css'

const USER_DEFAULT={
    "id": null,
    "firstname": "",
    "lastname": "",
    "emailAddress": "",
    "username": "",
    "rol": {id:3},
    "status":{id:1}
}

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            user:null,
            roles:[]

        }
    }

    componentDidMount() {       
        
        AuthenticationService.handleInterceptor();       
        this.setState({user:USER_DEFAULT})        
        this.loadRoles()
       
    }

   

    loadRoles=()=> {
        UserService.getRoles()
        .then(response=>{
            this.setState({roles:response.data})
        })
    }

    changeField=(e)=>{
        const {name,value,type}=e.target;
        let userUdated;
        
        if(type==='select-one')  {
            console.log(value)
            userUdated={...this.state.user};
            userUdated.rol.id=value

        }  else {
            userUdated={...this.state.user,[name]:value}
        }   
        
       
        this.setState({user:userUdated})
    }

    changeSelectField=(e)=> {
        const {name,value}=e.target;
        let {roles}=this.state;

        let userUpdate={...this.state.user}

        userUpdate.rol=roles[value];
        console.log(userUpdate)

        this.setState({user:userUpdate})
       
    }

    onRegister=(e)=> {
        e.preventDefault()
        const {user}=this.state;
        this.saveUser(user);
    }

    saveUser=(user)=>{
        UserService.save(user)
        .then(()=> {
            AuthenticationService.unRegisterUser()
        this.props.history.push('/login')})
        .catch(err=>console.log(err))
    }

    render() {

        const {user}=this.state;

        let renderUser= (user) &&      
        <form>
        <div className="form-group">
            <label>First Name</label>
            <input type="text" 
            placeholder="First Name"
            name="firstname"
            value={user && user.firstname || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

        <div className="form-group">
            <label htmlFor="lastname">Last Name</label>
            <input type="text" 
            placeholder="Last Name"
            name="lastname"
            value={user && user.lastname || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

        <div className="form-group">
            <label>Email Address</label>
            <input type="text" 
            placeholder="Email Address"
            name="emailAddress"
            value={user && user.emailAddress || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

        <div className="form-group">
            <label>User Name</label>
            <input type="text" 
            placeholder="User name"
            name="username"
            value={user && user.username || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

       <div className="form-group">
            <label>Password</label>
            <input type="password" 
            placeholder="Password"
            name="password"
            value={user && user.password || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>

      <div className="form-group">
            <label>Rol</label>
            <select                         
            name="roles"
            value={user.rol.id}
            onChange={this.changeField}
            disabled={true}
            className="form-control">
                <option value={user.rol.id}>Trainees</option>                      

            </select>
        </div>
      

     
        <button className="btn btn-lg btn-success"
         onClick={this.onRegister}>Create</button> 
    </form>
        
        return (
            <div className="container">
                
                <h1 className="text-center">Create User</h1>
                <div className="maintananceLayout">
                    {renderUser}
                    
                </div>
            </div>
        )

    }
}

export default Register;