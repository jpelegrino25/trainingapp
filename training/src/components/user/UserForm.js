import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { loadRoles } from "../../redux/actions/rolesAction";
import { loadStatuses } from "../../redux/actions/statusActions";
import { connect } from "react-redux";
import InputField from "../common/InputField";
import SelectInput from "../common/SelectInput";

const UserForm = ({
  user,
  onChange,
  roles,
  loadRoles,
  statuses,
  loadStatuses,
  onSave,
}) => {
  useEffect(() => {
    if (roles.length === 0) loadRoles();

    if (statuses.length === 0) loadStatuses();
  }, [roles, statuses]);

  const title = user.id > 0 ? "Edit User" : "Create User";
  return (
    <>
      <div className="container">
        <div className="card shadow mb-4">
          <div className="card-header py-3">
            <h3>{title}</h3>
          </div>
          <div className="card-body">
            <form>
              <InputField
                label="FirstName"
                name="firstname"
                value={user.firstname || ""}
                onChange={onChange}
              />

              <InputField
                label="LastName"
                name="lastname"
                value={user.lastname || ""}
                onChange={onChange}
              />

              <InputField
                label="Email Address"
                name="emailAddress"
                value={user.emailAddress || ""}
                onChange={onChange}
              />

              {user.id < 0 && (
                <>
                  <InputField
                    label="UserName"
                    name="username"
                    value={user.username || ""}
                    onChange={onChange}
                  />

                  <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                      type="password"
                      className="form-control"
                      name="password"
                      value={user.password || ""}
                      onChange={onChange}
                    />
                  </div>
                </>
              )}

              <SelectInput
                name="rol"
                value={user.rol && user.rol.id}
                onChange={onChange}
                defaultOption="Select a Rol"
                label="User Rol"
                options={roles.map((rol) => ({
                  value: rol.id,
                  text: rol.description,
                }))}
              />

              {user.id > 0 && (
                <SelectInput
                  name="status"
                  value={user.status && user.status.id}
                  onChange={onChange}
                  defaultOption="Select a Status"
                  label="Status"
                  options={statuses.map((status) => ({
                    value: status.id,
                    text: status.description,
                  }))}
                />
              )}

              <div className="form-group">
                <button className="btn btn-primary btn-lg" onClick={onSave}>
                  Save
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

const mapStateToProps = (state) => {
  return {
    roles: state.roles,
    statuses: state.statuses,
  };
};

const mapDispatchToProps = {
  loadRoles,
  loadStatuses,
};

UserForm.propTypes = {
  onChange: PropTypes.func,
  user: PropTypes.object,
  roles: PropTypes.array,
  loadRoles: PropTypes.func,
  statuses: PropTypes.array,
  loadStatuses: PropTypes.func,
  onSave: PropTypes.func,
};

export default connect(mapStateToProps, mapDispatchToProps)(UserForm);
