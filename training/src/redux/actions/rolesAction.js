import * as types from "./actionTypes";
import Userservice from "../../api/UserService";

export function loadRolesSuccess(roles) {
  return { type: types.LOAD_USER_ROLES, roles };
}

export function loadRoles() {
  return function (dispatch) {
    return Userservice.getRoles()
      .then((roles) => {
        dispatch(loadRolesSuccess(roles.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}
