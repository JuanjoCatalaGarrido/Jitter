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
import {DashboardPage} from "./pages/dashboard/DashboardPage";
import {ProtectedRoute} from "./components/ProtectedRoute/ProtectedRoute";
import {AuthProvider} from "./hooks/authentication";

export function App() {
  return (
      <StrictMode>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/dashboard" element={
                <ProtectedRoute redirectOnFail={"/login"}>
                    <DashboardPage/>
                </ProtectedRoute>
            }/>
          </Routes>
        </BrowserRouter>
      </StrictMode>
  );
}

export default App
