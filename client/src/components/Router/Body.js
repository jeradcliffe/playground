import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Home from '../Home/Home';
import './Body.css';

const Body = () => {
  return(
    <div className="body">
    <Switch>
      <Route exact path="/" component={Home} />
    </Switch>
    </div>
  );
};

export default Body;
