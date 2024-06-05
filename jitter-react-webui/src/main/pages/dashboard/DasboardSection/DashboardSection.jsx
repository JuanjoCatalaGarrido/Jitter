import './dashboardSection.scss'
import {UserInfoPanel} from "../../../components/UserInfoPanel/UserInfoPanel";
import {useEffect, useState} from "react";
import {useLoggedUserInfo, useUserInfo} from "../../../hooks/users";
import {AuthProvider, useAuth} from "../../../hooks/authentication";
import {PostsInfiniteScroll} from "./PostsInfiniteScroll/PostsInfiniteScroll";

export function DashboardSection() {

    const {token, userId} = useAuth();
    const [userInfo, setUserInfo] =  useLoggedUserInfo();
    const [error, setError] = useState(null);

    useEffect(() => {
        if(!userInfo){
            setError("No se pudo obtener la información del usuario");
        } else {
            setError(null);
        }
    }, [userInfo]);


  return (
      <section className={"dashboard-section"}>
          {error && (
              <div className="alert alert-danger" role="alert">
                  {error}
              </div>
          )}

          <aside className={"dashboard-left-aside"}>
              {
                  userInfo != null && (
                      <UserInfoPanel username={userInfo.username} profileImgUrl={userInfo.profileImgUrl}
                                     numFollowed={0}
                                     numFollowers={0}/>
                  )
              }
          </aside>

          <main className={"dashboard-main"}>
              {
                  userInfo != null && (
                      <PostsInfiniteScroll userId={userInfo.id}/>
                  )
              }
          </main>

          <aside className={"dashboard-right-aside"}>
            <h3>Etiquetas con más posts</h3>
              <div className={"tags-container"}>

              </div>
          </aside>

      </section>
  );
}