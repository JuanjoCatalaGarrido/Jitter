import './profileInfoSection.scss'
import {useLoggedUserInfo} from "../../../hooks/users";
import {useState} from "react";

export function ProfileInfoSection(){
    const [userInfo, setUserInfo] =  useLoggedUserInfo();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [error, setError] = useState(null);


    return (
        <section className={"profile-info-section"}>
            {
                userInfo != null &&
                <h3 className={"title"}>¡Hola, {userInfo.username}!</h3>
            }

            {error && (
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            )}

            {
                userInfo != null &&
                <main className={"profile-info-main"}>
                    <div className={"profile-img-container"}>
                        <img src={""}/>
                    </div>
                    <form className={"profile-form"}>
                        <div className={"form-field"}>
                            <label id={"username-label"} htmlFor={"username-input"}>Usuario</label>
                            <input type={"text"} id={"username-input"} value={userInfo.username}/>
                        </div>
                        <div className={"form-field"}>
                            <label id={"email-label"} htmlFor={"email-input"}>Email</label>
                            <input type={"email"} id={"email-input"} value={userInfo.email}/>
                        </div>
                        <div className={"form-field"}>
                            <label id={"password-label"} htmlFor={"password-input"}>Contraseña</label>
                            <input type={"password"} id={"password-input"}/>
                        </div>
                    </form>
                </main>
            }

                <button type="button " className="btn btn-primary secondary-bg-color btn-block">
                Aplicar Cambios
                </button>
        </section>
    );
}