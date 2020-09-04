import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function authencateReducers(
  state = initialState.authenticatedUser,
  action
) {
  switch (action.type) {
    case types.AUTHENTICATE_USER_SESSION:
      return action.user;
    default:
      return state;
  }
}
