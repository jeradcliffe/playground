import React from 'react';
import {Route, Switch} from 'react-router-dom';
import './Body.css';
import Home from "../Home/Home";

const Body = () => {
    return (
        <div className="body">
            <Switch>
                <Route exact path="/" component={Home}/>npm
            </Switch>
        </div>
    );
};

export default Body;
