import './registerSection.scss'
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {useAuth} from "../../../hooks/authentication";
import axios from "axios";
import {resolveEndpoint} from "../../../utils/endpoints";

export function RegisterSection(){
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const {token, setToken} = useAuth();

    function handleUsernameChange (e){
        setUsername(e.target.value);
    }

    function handlePasswordChange(e){
        setPassword(e.target.value);
    }

    function handleEmailChange(e){
        setEmail(e.target.value);
    }

    function handleFormSubmit(e){
        e.preventDefault();

        let body = {
            username : username,
            email : email,
            password : password
        }

        axios.post(resolveEndpoint("/api/auth/register"), body)
            .then((response) => {
                setError(null);
                console.log("redirecting...")
                navigate("/login");
            }).catch((reason) => {
            if(!reason.response){
                setError("No se pudo relizar la petición :(")
                return;
            }
            setError(reason.response.data.message)
        });
    }


    return (
        <section className={"register-section"} onSubmit={handleFormSubmit}>
            <form className={"register-form"}>
                <h2 className={"important-text "}>Registro</h2>

                {error && (
                    <div className="alert alert-danger" role="alert">
                        {error}
                    </div>
                )}

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="username-input">Usuario</label>
                    <input type="text" id="username-input" className="form-control" onChange={handleUsernameChange}/>
                </div>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="email-input">Email</label>
                    <input type="email" id="email-input" className="form-control"  onChange={handleEmailChange}/>
                </div>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="password-input">Contraseña</label>
                    <input type="password" id="password-input" className="form-control" minLength={10} onChange={handlePasswordChange}/>
                </div>

                <button type="button " className="btn btn-primary secondary-bg-color btn-block mb-4">
                    Registrarse
                </button>

                <div className="text-center">
                    <p>¿Ya eres miembro? <a href="/login">Inicia Sesión</a></p>
                </div>
            </form>
        </section>
    );
}