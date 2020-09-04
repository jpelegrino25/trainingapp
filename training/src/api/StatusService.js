const baseUrl = process.env.API_URL + "/statuses";
import axios from "axios";
import AuthenticationService from "./AuthenticationService";

class StatusService {
  getStatuses = () => {
    AuthenticationService.interceptor();
    return axios.get(baseUrl);
  };
}

export default new StatusService();
