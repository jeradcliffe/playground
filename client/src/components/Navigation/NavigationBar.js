import React from 'react';
import { Link } from 'react-router-dom';

import { FaHome, FaGithub, FaLinkedin } from 'react-icons/lib/fa';

import "./NavigationBar.css";

const NavigationBar = () => {
    return (
        <nav className="navbar navbar navbar-expand-sm navbar-dark bg-dark">
            <Link to="/" className="nav-link">
                <FaHome className="navbar__link__icon" />
            </Link>

            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" data-toggle="dropdown" role="button">Dropdown</a>
                        <div className="dropdown-menu">
                            <Link to="/htmlRefVsReactRef" className="dropdown-item">Demo1</Link>
                            <Link to="/htmlRefVsReactRef" className="dropdown-item">Demo2</Link>
                            <Link to="/htmlRefVsReactRef" className="dropdown-item">Demo3</Link>
                        </div>
                    </li>

                    <li className="nav-item">
                        <Link to="/" className="nav-link">Nav Link</Link>
                    </li>
                </ul>

                <div>
                    <a href="https://github.com/jeradcliffe/" target="_blank" rel="noopener noreferrer" className="nav-item">
                        <FaGithub className="navbar__link__icon" />
                    </a>
                    <a href="https://www.linkedin.com/in/jacob-radcliffe-36a20579" rel="noopener noreferrer" target="_blank" className="nav-item">
                        <FaLinkedin className="navbar__link__icon" />
                    </a>
                </div>
            </div>
        </nav>
    );
};

export default NavigationBar;
