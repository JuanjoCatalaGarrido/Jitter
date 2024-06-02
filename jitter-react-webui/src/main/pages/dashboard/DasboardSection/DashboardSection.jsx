import './dashboardSection.scss'
import {UserInfoPanel} from "../../../components/UserInfoPanel/UserInfoPanel";
import {useEffect, useState} from "react";
import {useUserInfo} from "../../../hooks/users";

export function DashboardSection() {

  const [userInfo, setUserInfo] = useUserInfo("");

  const [profileImgUrl, setProfileImgUrl] = useState(userInfo.profileImgUrl);
  const [username, setUsername] = useState(userInfo.username);
  const [followedCount, setFollowedCount] = useState(0);
  const [followersCount, setFollowersCount] = useState(0);

  return (
      <section>
        <UserInfoPanel username={username} profileImgUrl={profileImgUrl}
                       numFollowed={followedCount}
                       numFollowers={followersCount}/>
      </section>
  );
}