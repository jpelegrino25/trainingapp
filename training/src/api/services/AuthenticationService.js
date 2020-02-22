import axios from 'axios'

const USER_AUTHENTICATED='userAuthenticated'
class AuthenticationService {

    registerUser=(username,password)=>{      
        
        let authorizationHeader=this.basicHeaderAuth(username,password);
            sessionStorage.setItem(USER_AUTHENTICATED,username);  
            this.axiosInterceptor(authorizationHeader)          
      
    }

    axiosInterceptor=(authorizationHeader)=> {
       
        axios.interceptors.request
        .use((config)=>{
            if(this.IsUserLogin())
            config.headers.authorization=authorizationHeader

            return config;
        })

    }

    authenticatedBasic=(username,password)=> {       
        return axios.get('http://localhost:8086/training/authentications',{
            headers:{
                authorization:this.basicHeaderAuth(username,password)
            }
        })
    }

    unRegisterUser=()=>{
        sessionStorage.removeItem(USER_AUTHENTICATED);
    }

    IsUserLogin=()=> {
        let user=sessionStorage.getItem(USER_AUTHENTICATED);       
        if(user===null) return false;

        return true;
    }

    basicHeaderAuth=(username,password)=> {
        return 'Basic '+ window.btoa(username+":"+password);
    }

}

export default new AuthenticationService();