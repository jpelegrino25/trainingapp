import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getUserAuthenticated } from "../../redux/actions/authenticationActions";

const Header = ({ user, getUserAuthenticated }) => {
  useEffect(() => {
    if (!user) getUserAuthenticated();
  }, [user]);
  return (
    <>
      {user && (
        <ul className="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion">
          <li className="nav-item active">
            <NavLink className="nav-link" to="/home">
              <span>Dashboard</span>
            </NavLink>
          </li>
          <hr className="sidebar-divider" />
        </ul>
      )}
    </>
  );
};

const mapStateToProps = (state) => {
  return {
    user: state.authenticatedUser,
  };
};

const mapDispatchToProps = {
  getUserAuthenticated,
};

Header.propTypes = {
  user: PropTypes.object,
  getUserAuthenticated: PropTypes.func.isRequired,
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);
