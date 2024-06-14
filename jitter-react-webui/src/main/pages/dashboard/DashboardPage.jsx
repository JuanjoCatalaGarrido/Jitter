import {Navbar} from "../../components/Navbar/Navbar";
import {DashboardSection} from "./DasboardSection/DashboardSection";
import './dashboardPage.scss'
import {UserInfoPanel} from "../../components/UserInfoPanel/UserInfoPanel";
import {useLoggedUserInfo} from "../../hooks/users";
import {useEffect, useState} from "react";

export function DashboardPage(){
    const [userInfo, setUserInfo] =  useLoggedUserInfo();
    const [error, setError] = useState(null);

    useEffect(() => {
        if(!userInfo){
            setError("No se pudo obtener la información del usuario");
        } else {
            setError(null);
        }
    }, [userInfo]);

    return (
        <div className={"dashboard"}>
            <Navbar/>
            {error && (
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            )}
            {
                userInfo != null && (
                    <DashboardSection userInfo={userInfo}/>
                )
            }

            <aside className={"right-aside"}>
                <h3>Etiquetas con más posts</h3>
                <div className={"tags-container"}>

                </div>
            </aside>
            <aside className={"left-aside"}>
                {
                    userInfo != null && (
                        <UserInfoPanel userInfo={userInfo}/>
                    )
                }
            </aside>
        </div>
    );
}