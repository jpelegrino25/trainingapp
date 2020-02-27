import axios from 'axios'
import * as Constant from '../../Constant.js'

const URI='trainings/'

class TrainingService {

    fingAll=()=>{
        return axios.get(Constant.URL_BASE+URI);
    }

    findById=(trainingId)=> {
        return axios.get(Constant.URL_BASE+URI+trainingId);
    }

    save=(training)=> {
        return axios.post(Constant.URL_BASE+URI,training);
    }

    update=(training)=> {
        return axios.put(Constant.URL_BASE+URI,training);
    }

    delete=(trainingId)=> {
        return axios.delete(Constant.URL_BASE+URI+trainingId);
    }




}

export default new TrainingService();