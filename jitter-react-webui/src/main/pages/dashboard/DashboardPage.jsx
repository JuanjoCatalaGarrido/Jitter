import {Navbar} from "../../components/Navbar/Navbar";
import {DashboardSection} from "./DasboardSection/DashboardSection";
import './dashboardPage.scss'
import {UserInfoPanel} from "../../components/UserInfoPanel/UserInfoPanel";
import {useLoggedUserInfo} from "../../hooks/users";
import {useEffect, useState} from "react";
import {resolveEndpoint} from "../../utils/endpoints";
import axios from "axios";

export function DashboardPage(){
    const [userInfo, setUserInfo] =  useLoggedUserInfo();
    const [error, setError] = useState(null);
    const [trendingTags,  setTrendingTags] = useState([]);

    useEffect(() => {
        if(!userInfo){
            setError("No se pudo obtener la información del usuario");
        } else {
            setError(null);
        }
    }, [userInfo]);

    useEffect(() => {
        axios.get(resolveEndpoint(`/api/tags/trending`))
            .then((res) => {
                console.log(JSON.stringify(res.data.content))
                setTrendingTags(res.data);
            })
            .catch((err) => console.log(err));
    }, []);

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
                    {
                        trendingTags != null && trendingTags.slice(0, 5).map((item, index) => (
                            <span key={index} className={"highlight2-text-color"}>#{item.name}</span>
                        ))
                    }
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