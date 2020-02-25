import axios from 'axios'


const URI='users/';
class UserService {

    findAll=()=> {
        return axios.get('http://localhost:8086/training/'+URI);
    }

    findById=(userId)=> {
        return axios.get('http://localhost:8086/training/'+URI+userId);
    }

    save=(user)=> {
        return axios.post('http://localhost:8086/training/'+URI,user);
    }

    update=(user)=> {
        return axios.put('http://localhost:8086/training/'+URI,user);
    }

    delete=(userId)=> {
        return axios.delete('http://localhost:8086/training/'+URI+userId);
    }

    getRoles=()=> {
        return axios.get('http://localhost:8086/training/'+URI+'roles');
    }

}

export default new UserService();