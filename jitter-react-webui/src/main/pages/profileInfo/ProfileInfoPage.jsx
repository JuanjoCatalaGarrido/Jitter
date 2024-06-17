import {Navbar} from "../../components/Navbar/Navbar";
import {ProfileInfoSection} from "./ProfileInfoSection/ProfileInfoSection";


export function ProfileInfoPage(){

    return (
        <section className={"profile-info"}>
            <Navbar/>
            <ProfileInfoSection/>
        </section>
    );
}