import {Navbar} from "../../components/Navbar/Navbar";
import {LoginSection} from "./LoginSection/LoginSection";

export function LoginPage(){
    return (
        <div className={"register"}>
            <Navbar/>
            <LoginSection/>
        </div>
    );
}