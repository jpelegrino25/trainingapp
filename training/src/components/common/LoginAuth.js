import React from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Route, Redirect } from "react-router-dom";

const LoginAuth = ({ userAuth, ...props }) => {
  let routeDiv;
  if (userAuth) {
    routeDiv = <Redirect to="/error" />;
  } else {
    routeDiv = <Route {...props} />;
  }
  return <>{routeDiv}</>;
};

const mapStateToProps = (state) => {
  return {
    userAuth: state.authenticatedUser,
  };
};

LoginAuth.propTypes = {
  userAuth: PropTypes.object,
};

export default connect(mapStateToProps)(LoginAuth);
