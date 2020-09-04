import React from "react";
import { Route, Switch } from "react-router-dom";
import HomePage from "./components/home/HomePage";
import UserPage from "./components/user/UserPage";
import Header from "./components/common/Header";
import LoginPage from "./components/login/LoginPage";
import ErrorPage from "./components/common/ErrorPage";
import LoginAuth from "./components/common/LoginAuth";
import LogoutAuth from "./components/common/LogoutAuth";
import ManageUserPage from "./components/user/ManageUserPage";

const App = () => (
  <div id="wrapper">
    <Header />
    <Switch>
      <LoginAuth exact path="/" component={LoginPage} />
      <LogoutAuth path="/home" component={HomePage} />
      <Route exact path="/user/:id" component={ManageUserPage} />
      <Route exact path="/user" component={UserPage} />
      <Route component={ErrorPage} />
    </Switch>
  </div>
);

export default App;
