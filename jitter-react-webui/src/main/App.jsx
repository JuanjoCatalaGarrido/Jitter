'use strict'
import {StrictMode} from 'react';
import {HomePage} from './pages/home/HomePage';
import {
  BrowserRouter,
  Route,
  Routes,
} from "react-router-dom";
import {RegisterPage} from "./pages/register/RegisterPage";
import {LoginPage} from "./pages/login/LoginPage";

export function App() {
  return (
      <StrictMode>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage/> }/>
              <Route path="/register" element={<RegisterPage/> }/>
              <Route path="/login" element={<LoginPage/> }/>
          </Routes>
        </BrowserRouter>
      </StrictMode>
  );
}

export default App
