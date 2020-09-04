import * as types from "./actionTypes";
import Userservice from "../../api/UserService";

export function loadUsersSuccess(users) {
  return { type: types.LOAD_USERS_SUCCESS, users };
}

export function saveUserSuccess(user) {
  return { type: types.SAVE_USER_SUCCESS, user };
}

export function updateUserSuccess(user) {
  return { type: types.UPDATE_USER_SUCCESS, user };
}

export function deleteUserSuccess(user) {
  return { type: types.DELETE_USER_SUCCESS, user };
}

export function loadUsers() {
  return function (dispatch) {
    return Userservice.getUsers()
      .then((users) => {
        dispatch(loadUsersSuccess(users.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}

export function saveUser(user) {
  return function (dispatch) {
    return Userservice.saveUser(user)
      .then((userSaved) => {
        dispatch(saveUserSuccess(userSaved.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}

export function updateUser(user) {
  return function (dispatch) {
    return Userservice.updateUser(user)
      .then((userUpdated) => {
        dispatch(updateUserSuccess(userUpdated.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}

export function deleteUser(user) {
  return function (dispatch) {
    return Userservice.deleteUser(user)
      .then((userDeleted) => {
        dispatch(deleteUserSuccess(userDeleted.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}
