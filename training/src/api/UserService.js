const baseUrl = process.env.API_URL + "/users";
import axios from "axios";
import AuthenticationService from "./AuthenticationService";

class UserService {
  getUsers = () => {
    AuthenticationService.interceptor();
    return axios.get(baseUrl);
  };

  getRoles = () => {
    AuthenticationService.interceptor();
    return axios.get(baseUrl + "/roles");
  };

  saveUser = (user) => {
    AuthenticationService.interceptor();
    return axios.post(baseUrl, user);
  };

  updateUser = (user) => {
    AuthenticationService.interceptor();
    return axios.put(baseUrl, user);
  };

  deleteUser = (userId) => {
    AuthenticationService.interceptor();
    return axios.delete(baseUrl + `/${userId}`);
  };
}

export default new UserService();
