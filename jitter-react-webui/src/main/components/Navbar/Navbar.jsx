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
                <NavbarItem className={"terms-of-services"} href={"/tos"}>Términos de servicio</NavbarItem>
                <NavbarItem className={"about-us"} href={"/about-us"}>Sobre nosotros</NavbarItem>
                <span className={"menu-separator"}>|</span>
                <NavbarItem className={"login-button"} href={"/login"}>Inicio Sesión</NavbarItem>
                <Button href={"/register"}>Regístrate</Button>
            </div>
        </nav>
    );
}