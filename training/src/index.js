import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/index.css";
import "jquery/dist/jquery";
import "datatables.net/js/jquery.dataTables";
import "bootstrap/dist/js/bootstrap.bundle";

import { render } from "react-dom";
import App from "./App";
import { BrowserRouter as Router } from "react-router-dom";
import { Provider as ReduxProvider } from "react-redux";
import configureStore from "./redux/configureStore";

const store = configureStore();

render(
  <ReduxProvider store={store}>
    <Router>
      <App />
    </Router>
  </ReduxProvider>,
  document.getElementById("root")
);
