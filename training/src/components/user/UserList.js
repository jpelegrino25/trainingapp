import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const UserList = ({ users, addUser, deleteUser }) => {
  return (
    <div className="table-responsive">
      <table
        className="table table-bordered"
        id="dataTable"
        width="100%;"
        cellSpacing="0"
      >
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Status</th>
            <th colSpan="2">Action</th>
          </tr>
        </thead>

        <tbody>
          {users.map((user) => {
            return (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.firstname}</td>
                <td>{user.lastname}</td>
                <td>{user.emailAddress}</td>
                <td>{user.statusName}</td>
                <td>
                  <Link
                    to={`/user/${user.id}`}
                    className="btn btn-primary border border-warning"
                  >
                    Update
                  </Link>
                </td>
                <td>
                  <Link
                    to={`/user`}
                    className="btn btn-danger border border-warning"
                    onClick={() => deleteUser(user.id)}
                  >
                    Delete
                  </Link>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
      <button className="btn btn-success m-3" onClick={addUser}>
        New User
      </button>
    </div>
  );
};

UserList.propTypes = {
  users: PropTypes.array.isRequired,
  addUser: PropTypes.func,
  deleteUser: PropTypes.func,
};

export default UserList;
