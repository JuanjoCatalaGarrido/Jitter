import defaultProfileImage from '../../assets/images/userProfile.svg'
import './userInfoPanel.scss';

export function UserInfoPanel({userInfo}) {
    defaultProfileImage
  return (
      <div className={"user-info-panel"}>
        <div className={"profile-info"}>
            <img src={!userInfo.profileImgUrl ? userInfo.profileImgUrl : defaultProfileImage}/>
            <span className={"highlight-text-color"}>@{userInfo.username}</span>
        </div>
        <div className={"profile-statistics"}>
          <div className={"follows-container"}>
            <p>Seguidos: </p>
            <span className={"highlight-text-color"}>{userInfo.followsCount}</span>
          </div>
          <div className={"followers-container"}>
            <p>Seguidores: </p>
            <span className={"highlight-text-color"}>{userInfo.followersCount}</span>
          </div>
        </div>
      </div>
  );
}