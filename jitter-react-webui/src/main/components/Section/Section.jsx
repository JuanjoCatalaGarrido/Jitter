'use strict'
import './section.scss';
import {Button} from "../Button/Button";

export function Section(){
    return (
        <section>
            <main>
                <div className={"section-call-to-action-container"}>
                    <h1 className={"title"}>Expresarse es condición necesaria del ser humano</h1>
                    <h6 className={"subtitle"}>Exprésate, sé tu mismo y defiende tu libertad de expresión</h6>
                    <Button>Comienza a hablar</Button>
                </div>
                <div className={"section-image-container"}>
                    <img src={""} alt={"image1"}/>
                </div>
            </main>
            <div className={"section-cards-container"}>
                <div className={"section-card"}>
                    <img src={""} alt={"card1"}/>
                </div>
                <div className={"section-card"}>
                    <img src={""} alt={"card2"}/>
                </div>
                <div className={"section-card"}>
                    <img src={""} alt={"card3"}/>
                </div>
            </div>
        </section>
    );
}