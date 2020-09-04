import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function statusReducers(state = initialState.statuses, action) {
  switch (action.type) {
    case types.LOAD_STATUSES:
      return action.statuses;
    default:
      return state;
  }
}
