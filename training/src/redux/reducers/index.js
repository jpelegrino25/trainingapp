import { combineReducers } from "redux";
import users from "./userReducers";
import authenticatedUser from "./authenticateReducers";
import roles from "./rolesReducer";
import statuses from "./statusReducers";

const rootReducer = combineReducers({
  users,
  authenticatedUser,
  roles,
  statuses,
});

export default rootReducer;
