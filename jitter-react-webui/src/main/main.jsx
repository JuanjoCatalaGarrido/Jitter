'use strict'
require('file-loader?name=[name].[ext]!./index.html')
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';

window.React = React

ReactDOM.createRoot(document.getElementById('react')).render(
    <App/>
)
