import './loginSection.scss'
import {useState} from "react";
import { useNavigate } from "react-router-dom";
import {useAuth} from "../../../hooks/authentication";
import {resolveEndpoint} from "../../../utils/endpoints";
import axios from "axios";

export function LoginSection(){
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [status, setStatus] = useState(undefined);
    const {token, setToken} = useAuth();

    function handleusernameChange (e){
        setUsername(e.target.value);
    }

    function handlePasswordChange(e){
        setPassword(e.target.value);
    }

    function handleFormSubmit(e){
        e.preventDefault();

        let body = {
            username : username,
            password : password
        }

        axios.post(resolveEndpoint("/api/auth/login"), body)
            .then((response) => {
                setToken(response.data.token);
                setStatus(undefined);

                navigate("/dashboard");
            }).catch((reason) => {
            if(!reason.response){
                setStatus("No se pudo relizar la petición :(")
                return;
            }
            setStatus(reason.response.data.message)
        });


    }

    return (
        <section className={"login-section"}>
            <form className={"login-form"} onSubmit={handleFormSubmit}>
                <h2 className={"important-text "}>Inicio de Sesión</h2>

                {status && (
                    <div className="alert alert-danger" role="alert">
                        {status}
                    </div>
                )}
                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="username-input">Usuario</label>
                    <input type="text" id="username-input" className="form-control" required={true} onChange={handleusernameChange}/>
                </div>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="password-input">Contraseña</label>
                    <input type="password" id="password-input" className="form-control" required={true} onChange={ handlePasswordChange}/>
                </div>

                <div className="row mb-4">
                    <div className="col d-flex justify-content-center">
                        {/* Checkbox */}
                        <div className="form-check">
                            <input className="form-check-input" type="checkbox" value="" id="form2Example31"
                                   defaultChecked/>
                            <label className="form-check-label" htmlFor="form2Example31"> Recuérdame </label>
                        </div>
                    </div>

                    <div className="col">
                        <a href="#!">¿Olvidaste la contraseña?</a>
                    </div>
                </div>

                <button type="button " className="btn btn-primary secondary-bg-color btn-block mb-4">
                    Inicia sesión
                </button>

                <div className="text-center">
                    <p>¿No eres miembro? <a href="/register">Regístrate</a></p>
                </div>
            </form>
        </section>
    );
}