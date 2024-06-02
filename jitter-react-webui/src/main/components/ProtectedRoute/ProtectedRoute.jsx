import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from "../../hooks/authentication";

export function ProtectedRoute({redirectOnFail, children }) {
    const { token } = useAuth();

    return (token == null ? <Navigate to={redirectOnFail} /> : children);
}

export default ProtectedRoute;
