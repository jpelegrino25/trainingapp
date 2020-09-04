import React from "react";
import { Link } from "react-router-dom";

const HomePage = () => (
  <div className="container-fluid" id="content-wrapper">
    <div id="content">
      <h1>Dashboard</h1>

      <div className="row">
        <div className="col-xl-3 col-md-6 mb-4">
          <Link to="/user">
            <div className="card border-left-primary shadow h-100 py-2">
              <div className="card-body">
                <div className="row no-gutters align-items-center">
                  <div className="col mr-2">
                    <div className="text-xs font-weight-bold text-primary text-uppercase mb-1">
                      User maintenance
                    </div>
                  </div>
                  <div className="col-auto">
                    <i className="fas fa-calendar fa-2x text-gray-300"></i>
                  </div>
                </div>
              </div>
            </div>
          </Link>
        </div>
      </div>
    </div>
  </div>
);

export default HomePage;
