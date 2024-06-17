import {Navbar} from "../../components/Navbar/Navbar";
import {RegisterSection} from "./RegisterSection/RegisterSection";


export function RegisterPage(){
    return (
            <div className={"register"}>
                <Navbar/>
                <RegisterSection/>
            </div>
    );
}