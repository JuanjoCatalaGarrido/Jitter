import {Navbar} from "../../components/Navbar/Navbar";
import {DashboardSection} from "./DasboardSection/DashboardSection";
import './dashboardPage.scss'

export function DashboardPage(){
    return (
        <div className={"dashboard"}>
            <Navbar/>
            <DashboardSection/>
        </div>
    );
}