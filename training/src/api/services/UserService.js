import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI='users/';
class UserService {

    findAll=()=> {
        return axios.get(Constant.URL_BASE+URI);
    }

    findTrainers=()=> {
        return axios.get(Constant.URL_BASE+URI+'trainers');
    }


    findById=(userId)=> {
        return axios.get(Constant.URL_BASE+URI+userId);
    }

    save=(user)=> {
        return axios.post(Constant.URL_BASE+URI,user);
    }

    update=(user)=> {
        return axios.put(Constant.URL_BASE+URI,user);
    }

    delete=(userId)=> {
        return axios.delete(Constant.URL_BASE+URI+userId);
    }

    getRoles=()=> {
        return axios.get(Constant.URL_BASE+URI+'roles');
    }

}

export default new UserService();