import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI='sessions/'

class SessionService {

    findAll=()=> {
        return axios.get(Constant.URL_BASE+URI);
    }

    findAllAvailables=(userId)=>{
        return axios.get(Constant.URL_BASE+URI+'availables/'+userId)
    }

    findById=(sessionId)=> {
        return axios.get(Constant.URL_BASE+URI+sessionId);
    }

    save=(session)=> {
        return axios.post(Constant.URL_BASE+URI,session);
    }

    update=(session)=> {
        return axios.put(Constant.URL_BASE+URI,session);
    }

    delete=(sessionId)=> {
        return axios.delete(Constant.URL_BASE+URI+sessionId);
    }

}

export default new SessionService();