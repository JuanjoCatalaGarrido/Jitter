import './exploreAccountsSection.scss'
import {SearchBar} from "../../../components/SearchBar/SearchBar";
import {useEffect, useState} from "react";
import axios from "axios";
import {resolveEndpoint} from "../../../utils/endpoints";
import {UserPreview} from "../../../components/UserPreview/UserPreview";

export function ExploreAccountsSection(){

    const [search, setSearch] = useState("");
    const [usersData, setUsersData] = useState([]);
    const [error, setError] = useState("")

    function onNewSearch(newSearch){
        setSearch(newSearch);
    }

    useEffect(() => {
        if(search === ""){
            return;
        }
        axios.post(resolveEndpoint("/api/users/filter"), {username: search})
            .then((response) => {
                setUsersData(response.data);

            }).catch((reason) => {
            if(!reason.response){
                setError("No se pudo relizar la petición :(")
                return;
            }
            setError(reason.response.data.message);
        });
    }, [search]);

    return (
        <div className={"explore-accounts-section"}>
            <h3 className={"title"}>Descubre nuevos usuarios</h3>

            <SearchBar onDocumentUpdateCallback={onNewSearch} delay={200}/>

            <div className={"users-container"}>
                {
                    (usersData.length < 1) && <h4 className={"no-results"}>No hay resultados :(</h4>
                }
                {
                    usersData.map((item)=>(
                        <UserPreview userData={item}/>
                    ))
                }
            </div>
        </div>
    );
}