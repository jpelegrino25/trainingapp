import React, { useEffect } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { loadUsers, deleteUser } from "../../redux/actions/userActions";
import UserList from "./UserList";
import { error } from "jquery";

const UserPage = ({ users, loadUsers, deleteUser, history }) => {
  useEffect(() => {
    if (users.length === 0) loadUsers();
  }, [users]);

  function onClickAdd() {
    history.push("/user/-1");
  }

  function onClickDelete(userId) {
    try {
      deleteUser(userId);
    } catch (err) {
      console.error(error);
    }
  }

  const usersUp = [...users].sort();
  return (
    <>
      <UserList
        users={usersUp}
        addUser={onClickAdd}
        deleteUser={onClickDelete}
      />
    </>
  );
};

const mapStateToProps = (state) => {
  return {
    users: state.users.map((user) => {
      return {
        ...user,
        statusName: user.status.description,
      };
    }),
    roles: state.roles,
  };
};

const mapDispatchToProps = {
  loadUsers,
  deleteUser,
};

UserPage.propTypes = {
  users: PropTypes.array.isRequired,
  loadUsers: PropTypes.func.isRequired,
  history: PropTypes.object,
  deleteUser: PropTypes.func,
};

export default connect(mapStateToProps, mapDispatchToProps)(UserPage);
