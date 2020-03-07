import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI='registers'
class RegisterService {

    subscribeToSession=(registerSession)=> {
        return axios.post(Constant.URL_BASE+URI,registerSession);
    }

}
export default new RegisterService();