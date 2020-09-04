import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { loadRoles } from "../../redux/actions/rolesAction";
import { loadStatuses } from "../../redux/actions/statusActions";
import { connect } from "react-redux";

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
              <div className="form-group">
                <label htmlFor="firstname">FirstName</label>
                <input
                  type="text"
                  className="form-control"
                  name="firstname"
                  value={user.firstname || ""}
                  onChange={onChange}
                />
              </div>

              <div className="form-group">
                <label htmlFor="lastname">LastName</label>
                <input
                  type="text"
                  className="form-control"
                  name="lastname"
                  value={user.lastname || ""}
                  onChange={onChange}
                />
              </div>

              <div className="form-group">
                <label htmlFor="emailAddress">Email Address</label>
                <input
                  type="text"
                  className="form-control"
                  name="emailAddress"
                  value={user.emailAddress || ""}
                  onChange={onChange}
                />
              </div>

              {user.id < 0 && (
                <>
                  <div className="form-group">
                    <label htmlFor="username">UserName</label>
                    <input
                      type="text"
                      className="form-control"
                      name="username"
                      value={user.username || ""}
                      onChange={onChange}
                    />
                  </div>

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

              <div className="form-group">
                <label htmlFor="rol">User Rol</label>
                <select
                  className="form-control"
                  name="rol"
                  value={user.rol && user.rol.id}
                  onChange={onChange}
                >
                  <option>Select a Rol</option>
                  {roles.map((rol) => {
                    return (
                      <React.Fragment key={rol.id || 0}>
                        <option value={rol.id}>{rol.description}</option>
                      </React.Fragment>
                    );
                  })}
                </select>
              </div>

              {user.id > 0 && (
                <div className="form-group">
                  <label htmlFor="status">Status</label>
                  <select
                    className="form-control"
                    name="status"
                    value={user.status && user.status.id}
                    onChange={onChange}
                  >
                    <option>Select a Status</option>
                    {statuses.map((status) => {
                      return (
                        <React.Fragment key={status.id}>
                          <option value={status.id}>
                            {status.description}
                          </option>
                        </React.Fragment>
                      );
                    })}
                  </select>
                </div>
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
