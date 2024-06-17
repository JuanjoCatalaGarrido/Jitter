import {Navbar} from "../../components/Navbar/Navbar";
import {ExploreAccountsSection} from "./ExploreAccountsSection/ExploreAccountsSection";
import './exploreAccounts.scss';

export function ExploreAccountsPage(){

    return (
        <div className={"explore-accounts"}>
            <Navbar/>
            <ExploreAccountsSection/>
        </div>
    );
}