import {useEffect, useState} from "react";
import useDebounce from "../../utils/useDebounce";
import {Icon} from "@iconify/react";
import './searchBar.scss'

export function SearchBar({onDocumentUpdateCallback, delay}){

    const [search, setSearch] = useState("");
    const debouncedSearch = useDebounce(search, delay);

    useEffect(() => {
        if (debouncedSearch) {
            onDocumentUpdateCallback(debouncedSearch);
        }
    }, [debouncedSearch, onDocumentUpdateCallback]);

    const handleInputChange = (event) => {
        const newValue = event.target.value;
        setSearch(newValue);
    };

    return (
        <div className={"search-bar"}>
            <input
                type="text"
                value={search}
                onChange={handleInputChange}
                placeholder="PacoFiestas72"
            />
            <div className={"search-icon-container secondary-bg-color "}>
                <Icon icon="material-symbols-light:search" width={"2rem"}/>
            </div>
        </div>
    );

}