import React from 'react';

import './App.css';
import Body from '../Body/Body';
import NavigationBar from '../NavigationBar/NavigationBar';

const App = () => (
  <div className="app">
    <NavigationBar/>
    <Body/>
  </div>
);

export default App;
