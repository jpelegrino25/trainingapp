const baseUrl = process.env.API_URL + "/authentications";
import axios from "axios";
import * as Constant from "../components/common/Constants";

class AuthenticationService {
  authenticate = (username, password) => {
    return axios.get(baseUrl, {
      headers: {
        authorization: this.basicHeaderAuth(username, password),
      },
    });
  };

  basicHeaderAuth = (username, password) => {
    let headerAuthorization = "Basic " + window.btoa(username + ":" + password);
    return headerAuthorization;
  };

  setUserSession = (userAuth, username, password) => {
    sessionStorage.setItem(
      Constant.USER_AUTH_SESSION,
      JSON.stringify(userAuth)
    );
    let token = this.basicHeaderAuth(username, password);
    sessionStorage.setItem(Constant.APP_REQUEST_HEADER_AUTH, token);
    this.interceptor();
  };

  getUserSession = () => {
    let userAuthenticated = sessionStorage.getItem(Constant.USER_AUTH_SESSION);
    if (userAuthenticated) return JSON.parse(userAuthenticated);
    return null;
  };

  removeUserSession = () => {
    sessionStorage.removeItem(Constant.USER_AUTH_SESSION);
  };

  getAuthorizationHeader = () => {
    let userAuthenticated = sessionStorage.getItem(Constant.USER_AUTH_SESSION);
    if (userAuthenticated)
      return sessionStorage.getItem(Constant.APP_REQUEST_HEADER_AUTH);

    return null;
  };

  interceptor = () => {
    let token = sessionStorage.getItem(Constant.APP_REQUEST_HEADER_AUTH);
    axios.interceptors.request.use(async (config) => {
      if (this.getUserSession()) {
        config.headers.authorization = token;
      }

      return config;
    });
  };
}

export default new AuthenticationService();
