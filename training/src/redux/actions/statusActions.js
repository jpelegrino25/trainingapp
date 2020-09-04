import * as types from "./actionTypes";
import StatusService from "../../api/StatusService";

export function loadStatusesSuccess(statuses) {
  return { type: types.LOAD_STATUSES, statuses };
}

export function loadStatuses() {
  return function (dispatch) {
    return StatusService.getStatuses()
      .then((statuses) => {
        dispatch(loadStatusesSuccess(statuses.data));
      })
      .catch((err) => {
        throw err;
      });
  };
}
