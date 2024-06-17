import {Navbar} from "../../components/Navbar/Navbar";

export function AboutUsPage() {

  return (
      <div className={"aboutUs"}>
        <Navbar/>
        <section className={"about-us-section"}>
          <h2>Sobre Jitter</h2>
          <p>Jitter es un servicio de microblogueo diseñado para promover la
            democracia y la libertad de expresión. Nuestro objetivo es
            proporcionar una plataforma donde todos puedan compartir sus
            opiniones y participar activamente en discusiones
            significativas.</p>

          <h3>Nuestra Misión</h3>
          <p>En Jitter, creemos en la importancia de la diversidad de opiniones
            y en el derecho de cada individuo a ser escuchado. Buscamos fomentar
            un ambiente inclusivo donde las ideas puedan fluir libremente y
            donde la discusión constructiva sea valorada.</p>

          <h3>¿Por qué Jitter?</h3>
          <p>El nombre "Jitter" refleja la rapidez y la agilidad con la que
            nuestras ideas pueden propagarse y generar impacto en la sociedad.
            Queremos que cada "jitter" de pensamiento contribuya a un cambio
            positivo en el mundo.</p>

          <h3>Nuestros Valores</h3>
          <ul>
            <li>Democracia: Promovemos un espacio donde cada voz cuenta, sin
              importar el trasfondo o la afiliación política.
            </li>
            <li>Transparencia: Valoramos la honestidad y la apertura en todas
              nuestras interacciones.
            </li>
            <li>Respeto: Fomentamos un ambiente respetuoso donde las diferencias
              son celebradas y no discriminadas.
            </li>
            <li>Innovación: Buscamos constantemente nuevas formas de mejorar la
              experiencia de nuestros usuarios y de impactar positivamente en la
              sociedad.
            </li>
          </ul>

          <h3>Únete a Nosotros</h3>
          <p>¡Únete a Jitter y sé parte de una comunidad que impulsa el cambio
            mediante el poder de las palabras! Explora, comparte y participa en
            las conversaciones que importan.</p>
        </section>
      </div>
  );
}