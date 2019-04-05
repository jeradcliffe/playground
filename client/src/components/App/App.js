import React from 'react';

import './App.css';
import Body from '../Router/Body';
import NavigationBar from '../Navigation/NavigationBar';

const App = () => (
    <div className="app">
        <NavigationBar/>
        <Body/>
    </div>
);

export default App;
