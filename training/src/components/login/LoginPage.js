import React, { useState } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { authenticateUser } from "../../redux/actions/authenticationActions";
import LoginForm from "./LoginForm";
const LoginPage = ({ authenticateUser, history }) => {
  const [user, setUser] = useState({ username: "", password: "" });

  function fieldHandle(e) {
    const { value, name } = e.target;
    setUser({ ...user, [name]: value });
  }

  function onSubmitClick(e) {
    e.preventDefault();

    authenticateUser(user.username, user.password)
      .then(() => {
        history.push("/home");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <>
      <LoginForm onChange={fieldHandle} onSubmit={onSubmitClick} user={user} />
    </>
  );
};

const mapPropToState = (state) => {
  return {
    userLogin: state.authenticatedUser,
  };
};

const mapDispatchToState = {
  authenticateUser,
};

LoginPage.propTypes = {
  authenticateUser: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
  authenticatedUser: PropTypes.object,
};

export default connect(mapPropToState, mapDispatchToState)(LoginPage);
