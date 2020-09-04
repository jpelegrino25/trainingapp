import * as types from "./actionTypes";
import AuthenticationService from "../../api/AuthenticationService";

export function authenticateSuccess(user) {
  return { type: types.AUTHENTICATE_USER_SESSION, user };
}

export function authenticateUser(username, password) {
  return function (dispatch) {
    return AuthenticationService.authenticate(username, password)
      .then((user) => {
        AuthenticationService.setUserSession(user, username, password);
        dispatch(authenticateSuccess(user.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}

export function getUserAuthenticated() {
  return function (dispatch) {
    try {
      let userAuth = AuthenticationService.getUserSession();
      dispatch(authenticateSuccess(userAuth));
    } catch (err) {
      throw err;
    }
  };
}
