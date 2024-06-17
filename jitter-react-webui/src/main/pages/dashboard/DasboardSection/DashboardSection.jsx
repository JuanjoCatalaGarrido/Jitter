import './dashboardSection.scss'
import {useEffect, useState} from "react";
import {PostsInfiniteScroll} from "./PostsInfiniteScroll/PostsInfiniteScroll";
import {Icon} from "@iconify/react";
import axios from "axios";
import {resolveAIEndpoint, resolveEndpoint} from "../../../utils/endpoints";

export function DashboardSection({userInfo}) {
    const [postText, setPostText] = useState("");

    function handlePostFieldChange(e){
        e.preventDefault();
        setPostText(e.target.value);
    }

    function handleAIButtonClick(e){
        e.preventDefault();
        if(postText === ""){
            return;
        }

        console.log(resolveAIEndpoint(`/api/ai?prompt=${postText}`.replaceAll(" ", "%20")));
        setPostText("Generando respuesta...");
        axios.get(resolveAIEndpoint(`/api/ai?prompt=${postText}`).replaceAll(" ", "%20"))
            .then((response) => {
                let choices = response.data.choices;
                setPostText(choices[0].text);

            }).catch((reason) => {
                setPostText("Error de conexión con la API de AI")
        });

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
                    <Icon icon="material-symbols:robot-2-outline" width={"2rem"} onClick={handleAIButtonClick}/>
                </div>
            </div>
          </div>
      </section>
  );
}