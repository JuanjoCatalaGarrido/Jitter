import axios from "axios";
import { createContext, useContext, useEffect, useMemo, useState } from "react";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [token, setToken] = useState(localStorage.getItem("auth_token"));
    const [userId, setUserId] = useState(null);

    function extractUserIdFromToken(token) {
        try {
            const decodedToken = jwtDecode(token);
            return decodedToken.sub;
        } catch (error) {
            console.error("Error decoding token:", error);
            return null;
        }
    }

    useEffect(() => {
        if (token) {
            axios.defaults.headers.common["Authorization"] = "Bearer " + token;
            localStorage.setItem('auth_token',token);
            setUserId(extractUserIdFromToken(token))
        } else {
            delete axios.defaults.headers.common["Authorization"];
            localStorage.removeItem('auth_token')
        }
    }, [token]);

    const contextValue = useMemo(
        () => ({
            token,
            setToken,
            userId,
        }),
        [token, userId]
    );

    return (
        <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
