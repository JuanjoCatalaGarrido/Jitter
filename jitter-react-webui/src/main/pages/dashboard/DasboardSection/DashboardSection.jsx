import './dashboardSection.scss'
import {UserInfoPanel} from "../../../components/UserInfoPanel/UserInfoPanel";
import {useEffect, useState} from "react";
import {useLoggedUserInfo, useUserInfo} from "../../../hooks/users";
import {AuthProvider, useAuth} from "../../../hooks/authentication";
import {PostsInfiniteScroll} from "./PostsInfiniteScroll/PostsInfiniteScroll";
import {Icon} from "@iconify/react";
import axios from "axios";
import {resolveEndpoint} from "../../../utils/endpoints";

export function DashboardSection({userInfo}) {
    const [postText, setPostText] = useState("");

    function handlePostFieldChange(e){
        setPostText(e.target.value);
    }

    function handlePostCreation(e){
        e.preventDefault();
        let body = {
            owner : userInfo,
            body : postText
        }
        axios.post(resolveEndpoint("/api/posts"), body)
            .then((response) => {
                alert("Post creado correctamente!");
            })
            .catch((reason) => {
            if(!reason.response){
                alert("No se pudo crear el post");
            }

        });
    }

  return (
      <section className={"dashboard-section"}>
              {
                  userInfo != null && (
                      <PostsInfiniteScroll userId={userInfo.id}/>
                  )
              }
          <div className={"newpost-form"}>
            <textarea value={postText} onChange={handlePostFieldChange}></textarea>
            <div className={"newpost-options"}>
                <div className={"send-icon-container secondary-bg-color"}>
                    <Icon icon="material-symbols:send" width={"2rem"} onClick={handlePostCreation}/>
                </div>
                <div className={"ai-icon-container secondary-bg-color"}>
                    <Icon icon="material-symbols:robot-2-outline" width={"2rem"}/>
                </div>
            </div>
          </div>
      </section>
  );
}