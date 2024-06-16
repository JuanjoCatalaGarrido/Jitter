import './userPreview.scss';
import {Button} from "react-bootstrap";
import {useLoggedUserInfo} from "../../hooks/users";
import {useEffect, useState} from "react";


export function UserPreview({userData}){
    const [loggedInUserInfo, setLoggedInUserInfo] =  useLoggedUserInfo();
    const [userIsAlreadyFollowed, setUserIsAlreadyFollowed] = useState(false);

    useEffect(() => {
        if(!loggedInUserInfo){
            return;
        }
        loggedInUserInfo.follows.forEach((follow) => {
            if(follow.id.followerId === userData.id){
                setUserIsAlreadyFollowed(true);
            }
        });

    }, [loggedInUserInfo]);

    return (
        <div className={"user-preview"}>
            <div className={"user-preview-first-section"}>
                <div className={"user-image-container"}>
                    <img src={userData.profileImgUrl}/>
                </div>
                <div className={"user-information"}>
                    {<a className={"highlight-text-color "} href={`/user/${userData.id}`}>@{userData.username}</a>}
                    <p className={"follows"}>Seguidos: <span className={"highlight-text-color"}>{userData.followsCount}</span></p>
                    <p className={"followers"}>Seguidores: <span className={"highlight-text-color"}>{userData.followersCount}</span></p>
                </div>
            </div>
            <div className={"user-preview-follow-button-container"}>
                {
                    userIsAlreadyFollowed ? <Button href={""}>Seguido</Button> :
                        <Button href={""}>Seguir</Button>
                }
            </div>
        </div>
    );
}