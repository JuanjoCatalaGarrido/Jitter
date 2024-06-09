'use strict'
import {Button} from "../Button/Button";
import {NavbarItem} from "./NavbarItem/NavbarItem";
import './navbar.scss';
import {useAuth} from "../../hooks/authentication";
import {useEffect} from "react";
import {Icon} from "@iconify/react";

export function Navbar(){
    const {token, setToken, username} = useAuth();

    useEffect(() => {
        console.log("TOKEN: " + token)
    }, []);

    function handleLogoutClick(e){
        e.preventDefault();
        setToken(null);
    }

    return (
        <nav>
            <div className={"nav-img-container"}>
                <img src={""} alt={"Jitter Icon"}/>
            </div>
            <div className={"nav-menu-container"}>
                <NavbarItem className={"terms-of-services"} href={"/tos"}>Términos de servicio</NavbarItem>
                <NavbarItem className={"about-us"} href={"/about-us"}>Sobre nosotros</NavbarItem>

                {
                    token != null && <NavbarItem className={"dashboard-item highlight-text-color"} href={"/dashboard"}>Dashboard</NavbarItem>

                }


                {
                    token != null && <NavbarItem className={"explore-accounts-item"} href={"/explore-accounts"}>Explorar cuentas</NavbarItem>

                }

                <span className={"menu-separator"}>|</span>
                {
                    token == null ? <NavbarItem className={"login-button"} href={"/login"}>Inicio Sesión</NavbarItem> :
                        <NavbarItem
                            className={"logout-button highlight-text-color"}
                            onClickHandle={handleLogoutClick}>Cerrar Sesión</NavbarItem>
                }


                {
                    token == null ? <Button href={"/register"}>Regístrate</Button> :
                        <div className={"profile"}>
                            <div className={"profile-pic-container secondary-bg-color"}>
                                <Icon icon="ph:user-bold" width={"2rem"}/>
                            </div>
                            <span>{username}</span>
                        </div>
                }

            </div>
        </nav>
    );
}