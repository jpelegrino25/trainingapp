import axios from 'axios'
import Constant from  '../../Constant.js'
const USER_AUTHENTICATED='userAuthenticated'
const AUTHORIZATION_HEADER='authorizationHeader'
const AUTHORITIES='authorities'

class AuthenticationService {

    registerUser=(username,password)=>{      
        
        let authorizationHeader=this.basicHeaderAuth(username,password);
            sessionStorage.setItem(USER_AUTHENTICATED,username);  
            sessionStorage.setItem(AUTHORIZATION_HEADER,authorizationHeader)
            this.axiosInterceptor(authorizationHeader)          
      
    }

    axiosInterceptor=(authorizationHeader)=> {
       
        axios.interceptors.request
        .use((config)=>{
            if(this.IsUserLogin()) {
            config.headers.authorization=authorizationHeader            
         }

            return config;
        })

    }

    getInterceptor=()=> {
        let interceptors=sessionStorage.getItem(AUTHORIZATION_HEADER);
        return interceptors;
    }

    getAuthenticatedUser=()=> {
        let userAuthenticated=sessionStorage.getItem(USER_AUTHENTICATED)
        return userAuthenticated;
    }

    handleInterceptor=()=> {
        let userLogin=this.getAuthenticatedUser();
        if(userLogin) {
            let interceptor=this.getInterceptor();
            if(interceptor)
            this.axiosInterceptor(interceptor)
        }
    }
    

    authenticatedBasic=(username,password)=> {       
        return axios.get('http://localhost:8086/training/authentications',{
            headers:{
                authorization:this.basicHeaderAuth(username,password)
            }
        })
    }

    getLoginUser=()=> {
        let authorities=sessionStorage.getItem(AUTHORITIES);
        return authorities;
    }

    unRegisterUser=()=>{
        sessionStorage.removeItem(USER_AUTHENTICATED);
        sessionStorage.removeItem(AUTHORIZATION_HEADER)
        sessionStorage.removeItem(AUTHORITIES)
    }

    hasAuthority=(authorities,authorityName)=> {
        let authResult=authorities.findIndex(auth=> auth.description===authorityName);
        return authResult>=0?true:false;
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