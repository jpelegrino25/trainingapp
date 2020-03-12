import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI='registers'
class RegisterService {

    subscribeToSession=(registerSession)=> {
        return axios.post(Constant.URL_BASE+URI,registerSession);
    }

    findAttendance=(professor,sessionId)=> {
        return axios.get(Constant.URL_BASE+URI+`/attendance/${professor}/${sessionId}`);
    }

}
export default new RegisterService();