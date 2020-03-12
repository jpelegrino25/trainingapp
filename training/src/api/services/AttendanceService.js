import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI="attendances/";

class AttendanceService {

    saveAttendance=(attendance)=> {
        return axios.post(Constant.URL_BASE+URI,attendance);
    }

}

export default new AttendanceService();