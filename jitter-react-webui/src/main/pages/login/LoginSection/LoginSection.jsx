import './loginSection.scss'

export function LoginSection(){

    return (
        <section className={"login-section"}>
            <form className={"login-form"}>
                <h2 className={"important-text "}>Inicio de Sesión</h2>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="email-input">Email</label>
                    <input type="email" id="email-input" className="form-control"/>
                </div>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="password-input">Contraseña</label>
                    <input type="password" id="password-input" className="form-control"/>
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