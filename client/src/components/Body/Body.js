import React from 'react';
import {Route, Switch} from 'react-router-dom';
import './Body.css';
import FaceRecognition from "../FaceRecognition/FaceRecognition";

const Body = () => {
  return (
    <div className="body">
      <Switch>
        <Route exact path="/" component={FaceRecognition}/>
        <Route exact path="/FaceRecognition" component={FaceRecognition}/>
      </Switch>
    </div>
  );
};

export default Body;
