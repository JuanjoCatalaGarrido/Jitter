import './dashboardSection.scss'
import {UserInfoPanel} from "../../../components/UserInfoPanel/UserInfoPanel";
import {useEffect, useState} from "react";
import {useLoggedUserInfo, useUserInfo} from "../../../hooks/users";
import {AuthProvider, useAuth} from "../../../hooks/authentication";
import {PostsInfiniteScroll} from "./PostsInfiniteScroll/PostsInfiniteScroll";
import {Icon} from "@iconify/react";

export function DashboardSection({userInfo}) {
    const {token, userId} = useAuth();

  return (
      <section className={"dashboard-section"}>
              {
                  userInfo != null && (
                      <PostsInfiniteScroll userId={userInfo.id}/>
                  )
              }
          <div className={"newpost-form"}>
            <textarea></textarea>
            <div className={"newpost-options"}>
                <div className={"send-icon-container secondary-bg-color"}>
                    <Icon icon="material-symbols:send" width={"2rem"}/>
                </div>
                <div className={"ai-icon-container secondary-bg-color"}>
                    <Icon icon="material-symbols:robot-2-outline" width={"2rem"}/>
                </div>
            </div>
          </div>
      </section>
  );
}