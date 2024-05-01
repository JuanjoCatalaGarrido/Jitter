'use strict'
import {Button} from "../Button/Button";
import {NavbarItem} from "./NavbarItem/NavbarItem";
import './navbar.scss';

export function Navbar(){
    return (
        <nav>
            <div className={"nav-img-container"}>
                <img src={""} alt={"Jitter Icon"}/>
            </div>
            <div className={"nav-menu-container"}>
                <NavbarItem className={"terms-of-services"}>Términos de servicio</NavbarItem>
                <NavbarItem className={"about-us"}>Sobre nosotros</NavbarItem>
                <span className={"menu-separator"}>|</span>
                <NavbarItem className={"login-Button"}>Inicio Sesión</NavbarItem>
                <Button href={""}>Regístrate</Button>
            </div>
        </nav>
    );
}