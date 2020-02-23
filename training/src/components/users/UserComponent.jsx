import React from 'react'
import UserService from '../../api/services/UserService.js'
import AuthenticationService from '../../api/services/AuthenticationService.js';
import { Link } from 'react-router-dom';

class UserComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            user : {
                userList:[]
            }
        }
    }

    componentDidMount() {

        let userLogin=AuthenticationService.getAuthenticatedUser();
        if(userLogin) {
            let interceptor=AuthenticationService.getInterceptor();
            if(interceptor)
            AuthenticationService.axiosInterceptor(interceptor)
        }
        this.userRefresh();

       
    }

    loadUsers=()=> {
        return UserService.findAll();
    }

    userRefresh=()=> {
        this.loadUsers()
        .then(response=>{            
            let userUpdate={...this.state.user};
            userUpdate.userList=response.data;
            this.setState({user:userUpdate})
        })
    }

    editUser=(userId,e)=> {
        console.log(`Edit User ${userId}`)
    }

    deleteUser=(userId,e)=> {
        console.log(`Delete User ${userId}`) 
    }

    addNewUser=()=> {
        console.log('Add new User')
    }

    render() {
        const {user}=this.state;
        
        let userRender=
        user.userList.map(user=> {
            return  <tr key={user.id}>
                        <td>{user.firstname}</td>
                        <td>{user.lastname}</td>
                        <td>{user.emailAddress}</td>
                        <td>{user.username}</td>
                        <td><button 
                            onClick={(e)=>this.editUser(user.id,e)}
                            className="btn btn-primary">
                            Edit</button></td>

                            <td><button 
                            onClick={(e)=>this.deleteUser(user.id,e)}
                            className="btn btn-danger">
                            Delete</button></td>
                    </tr>
                })
        

        return (
            <div className="container">
                <h1 className="text-center">User Maintanace</h1>
                <div>
                    <table className="table my-3">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email Address</th>
                                <th>Username</th>
                                <th colSpan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {userRender}                       
                        </tbody>

                    </table>
                </div>
                <button 
                className="btn btn-success"
                onClick={this.addNewUser}
                >New User</button>
            </div>
        )
    }

}

export default UserComponent;