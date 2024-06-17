'use strict'
import './homepageSection.scss';
import {Button} from "../../../components/Button/Button";
import stockImage from "../../../assets/images/home-stock.jpg";

export function HomepageSection() {
  return (
      <section className={"homepage-section"}>
        <main>
          <div className={"section-call-to-action-container"}>
            <h1 className={"title"}>Expresarse es condición del ser humano</h1>
            <h6 className={"subtitle"}>Exprésate, sé tu mismo y defiende tu
              libertad de expresión</h6>
            <Button href={"/register"}>Comienza a hablar</Button>
          </div>
          <div className={"section-image-container"}>
            <img src={stockImage} alt={"stock image"}/>
          </div>
        </main>
      </section>
  );
}