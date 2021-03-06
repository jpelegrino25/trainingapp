import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function rolesReducer(state = initialState.roles, action) {
  switch (action.type) {
    case types.LOAD_USER_ROLES:
      return action.roles;
    default:
      return state;
  }
}
