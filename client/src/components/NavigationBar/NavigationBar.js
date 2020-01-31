import React from 'react';
import {Link} from 'react-router-dom';
import {FaGithub, FaHome, FaLinkedin} from "react-icons/fa";

import "./NavigationBar.css";

const NavigationBar = () => {
  return (
    <nav className="navbar navbar navbar-expand-sm navbar-dark bg-dark">
      <Link to="/" className="nav-link">
        <FaHome className="navbar__link__icon"/>
      </Link>

      <button className="navbar-toggler" type="button" data-toggle="collapse"
              data-target="#navbarSupportedContent">
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to="/FaceRecognition" className="nav-link">Face Recognition</Link>
          </li>
        </ul>

        <div>
          <a href="https://github.com/jeradcliffe/" target="_blank" rel="noopener noreferrer"
             className="nav-item">
            <FaGithub className="navbar__link__icon"/>
          </a>
          <a href="https://www.linkedin.com/in/jacob-radcliffe-36a20579" rel="noopener noreferrer"
             target="_blank" className="nav-item">
            <FaLinkedin className="navbar__link__icon"/>
          </a>
        </div>
      </div>
    </nav>
  );
};

export default NavigationBar;
