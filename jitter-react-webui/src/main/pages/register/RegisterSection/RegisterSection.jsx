import './registerSection.scss'

export function RegisterSection(){

    return (
        <section className={"register-section"}>
            <form className={"register-form"}>
                <h2 className={"important-text "}>Registro</h2>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="email-input">Email</label>
                    <input type="email" id="email-input" className="form-control"/>
                </div>

                <div className="form-outline mb-4">
                    <label className="form-label" htmlFor="password-input">Contraseña</label>
                    <input type="password" id="password-input" className="form-control" minLength={10}/>
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