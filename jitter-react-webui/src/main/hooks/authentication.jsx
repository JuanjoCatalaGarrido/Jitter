import axios from "axios";
import { createContext, useContext, useEffect, useMemo, useState } from "react";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [token, setToken] = useState(localStorage.getItem("auth_token"));
    const [userId, setUserId] = useState(null);
    const [username, setUsername] = useState(null);

    function extractUsernameInfoFromToken(token) {
        try {
            const decodedToken = jwtDecode(token);
            return [decodedToken.sub, decodedToken.username];
        } catch (error) {
            console.error("Error decoding token:", error);
            return null;
        }
    }

    useEffect(() => {
        if (token) {
            axios.defaults.headers.common["Authorization"] = "Bearer " + token;
            localStorage.setItem('auth_token',token);

            let [userId, username] = extractUsernameInfoFromToken(token);
            setUserId(userId);
            setUsername(username);

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
            username
        }),
        [token, userId, username]
    );

    return (
        <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
