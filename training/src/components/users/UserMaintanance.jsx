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
    "rol": {id:2},
    "status":{id:1}
}

class UserMaintanance extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            user:null,
            roles:[],
            createRender:false,
            editRender:false

        }
    }

    componentDidMount() {
        
        let userId=this.props.match.params.id;
        AuthenticationService.handleInterceptor();
        if(userId==-1) {
            this.setState({user:USER_DEFAULT,createRender:true,editRender:false})
        }else {           
            this.loadUser(userId)            
        }
        this.loadRoles()
       
    }

    loadUser=(userId)=> {
        UserService.findById(userId)
        .then(response=> {            
            this.setState({user:response.data,createRender:false,editRender:true})
        }).catch(err=>console.log(err))
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

    onClickEdit=(e)=> {
        e.preventDefault()
        const {user,editRender}=this.state;
       
        if(editRender) {
            this.updateUser(user);
        }else {
            // AuthenticationService.handleInterceptor();
            this.saveUser(user);
        }
       
    }

    updateUser=(user)=> {
        UserService.update(user)
        .then( ()=>{
            this.props.history.push('/users')
        }
        ).catch(err=>console.log(err))
    }

    saveUser=(user)=>{
        UserService.save(user)
        .then(()=> {
        this.props.history.push('/users')})
        .catch(err=>console.log(err))
    }

    render() {

        const {user,roles,createRender,editRender}=this.state;

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

       {createRender && <div className="form-group">
            <label>Password</label>
            <input type="password" 
            placeholder="Password"
            name="password"
            value={user && user.password || ''}
            onChange={this.changeField}
            className="form-control"/>
        </div>}

      <div className="form-group">
            <label>Rol</label>
            <select                         
            name="roles"
            value={user.rol.id}
            onChange={this.changeField}
            className="form-control">
                {roles.map((rol,index)=> {
                    return <option key={rol.id} value={rol.id}>{rol.description}</option>
                })}                            

            </select>
        </div>
      

       {editRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Edit</button> }

        {createRender && <button className="btn btn-lg btn-success"
         onClick={this.onClickEdit}>Create</button> }
    </form>
        
        return (
            <div className="container">
                {editRender && <h1 className="text-center">Edit User</h1>}
                {createRender && <h1 className="text-center">Create User</h1>}
                <div className="bg-success maintananceLayout">
                    {renderUser}
                    
                </div>
            </div>
        )

    }
}

export default UserMaintanance;