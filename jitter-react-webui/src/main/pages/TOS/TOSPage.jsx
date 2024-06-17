import {Navbar} from "../../components/Navbar/Navbar";

export function TOSPage() {
  return (
      <div className={"termsOfService"}>
        <Navbar/>
        <section className={"terms-of-service-section"}>
          <h2>Términos de Servicio</h2>
          <p>Bienvenido a Jitter. Al utilizar nuestro servicio, aceptas los
            siguientes términos y condiciones:</p>

          <h3>1. Aceptación de los Términos</h3>
          <p>Al acceder y utilizar Jitter, aceptas y acuerdas cumplir con estos
            términos de servicio y todas las leyes y regulaciones aplicables. Si
            no estás de acuerdo con alguno de estos términos, no deberías usar
            nuestro servicio.</p>

          <h3>2. Modificaciones de los Términos</h3>
          <p>Nos reservamos el derecho de modificar estos términos de servicio
            en cualquier momento. Te notificaremos sobre cualquier cambio
            importante y tu uso continuado de Jitter después de dichas
            modificaciones constituirá tu aceptación de los nuevos términos.</p>

          <h3>3. Uso del Servicio</h3>
          <p>Jitter es un servicio de microblogueo que permite a los usuarios
            compartir pensamientos, ideas y contenido. Al utilizar Jitter, te
            comprometes a no publicar contenido que sea ilegal, ofensivo,
            difamatorio o que infrinja los derechos de terceros.</p>

          <h3>4. Privacidad</h3>
          <p>Tu privacidad es importante para nosotros. Por favor, revisa
            nuestra Política de Privacidad para entender cómo recopilamos,
            usamos y protegemos tu información personal.</p>

          <h3>5. Propiedad Intelectual</h3>
          <p>Todos los contenidos y materiales disponibles en Jitter, incluyendo
            pero no limitado a texto, gráficos, logotipos y software, son
            propiedad de Jitter o de sus licenciantes y están protegidos por las
            leyes de propiedad intelectual.</p>

          <h3>6. Terminación</h3>
          <p>Nos reservamos el derecho de suspender o terminar tu acceso a
            Jitter en cualquier momento, sin previo aviso, si creemos que has
            violado estos términos de servicio o cualquier ley aplicable.</p>
        </section>
      </div>
  );
}
