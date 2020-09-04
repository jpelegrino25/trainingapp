import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import {
  loadUsers,
  saveUser,
  updateUser,
} from "../../redux/actions/userActions";
import UserForm from "./UserForm";

const newUser = {
  id: -1,
  firstname: "",
  lastname: "",
  emailAddress: "",
  status: {
    id: -1,
  },
  rol: {
    id: -1,
  },
};

const ManageUserPage = ({
  users,
  loadUsers,
  saveUser,
  updateUser,
  history,
  ...props
}) => {
  const [user, setUser] = useState({ ...props.user });
  useEffect(() => {
    if (users.length === 0) {
      loadUsers();
    } else {
      setUser(props.user);
    }
  }, [props.user]);

  function handleField(e) {
    const { name, value } = e.target;

    setUser((prevUser) => ({
      ...prevUser,
      [name]:
        name === "rol"
          ? { ...prevUser.rol, id: value }
          : name === "status"
          ? { ...prevUser.status, id: value }
          : value,
    }));
  }

  function onSaveClick(e) {
    e.preventDefault();

    try {
      if (user.id > 0) {
        updateUser(user);
      } else {
        saveUser(user);
      }

      history.push("/user");
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <>
      <UserForm user={user} onChange={handleField} onSave={onSaveClick} />
    </>
  );
};

function getUserSelected(id, users) {
  let user = users.find((user) => user.id == id);
  return user;
}

const mapStateToProps = (state, ownProps) => {
  let paramId = ownProps.match.params.id;
  let user =
    paramId && parseInt(paramId) > 0
      ? getUserSelected(ownProps.match.params.id, state.users)
      : newUser;

  return {
    user,
    users: state.users,
    roles: state.roles,
  };
};

const mapDispatchToProps = {
  loadUsers,
  saveUser,
  updateUser,
};

ManageUserPage.propTypes = {
  user: PropTypes.object,
  loadUsers: PropTypes.func,
  users: PropTypes.array,
  saveUser: PropTypes.func,
  history: PropTypes.object,
  updateUser: PropTypes.func,
};

export default connect(mapStateToProps, mapDispatchToProps)(ManageUserPage);
