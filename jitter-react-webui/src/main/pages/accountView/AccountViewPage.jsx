import {useParams} from "react-router-dom";
import "./accountViewPage.scss";
import {useEffect, useState} from "react";
import {useUserInfo} from "../../hooks/users";
import {UserPreview} from "../../components/UserPreview/UserPreview";
import {Post} from "../../components/Post/Post";
import {Navbar} from "../../components/Navbar/Navbar";

export function AccountViewPage(){
    const { id } = useParams();
    const [userInfo, setUserInfo] = useUserInfo(id);


    return (
        <div className={"account-view"}>
            <Navbar/>
            {
                userInfo != null && <UserPreview userData={userInfo}/>
            }

            {
                userInfo != null &&
                <h5 className={"account-view-subtitle"}>Los posts de @{userInfo.username}</h5>
            }
            <div className={"posts-wrapper"}>
                {
                    userInfo != null && userInfo.userPosts.map((item) => (
                        <Post postInfo={item}></Post>
                    ))
                }
            </div>
        </div>
    );
}