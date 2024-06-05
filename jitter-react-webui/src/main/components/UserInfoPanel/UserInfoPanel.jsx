import defaultProfileImage from '../../assets/images/userProfile.svg'
import './userInfoPanel.scss';

export function UserInfoPanel({
  username,
  profileImgUrl,
  numFollowers,
  numFollowed
}) {
    defaultProfileImage
  return (
      <div className={"user-info-panel"}>
        <div className={"profile-info"}>
            <img src={!profileImgUrl ? profileImgUrl : defaultProfileImage}/>
            <span className={"highlight-text-color"}>@{username}</span>
        </div>
        <div className={"profile-statistics"}>
          <div className={"follows-container"}>
            <p>Seguidos: </p>
            <span className={"highlight-text-color"}>{numFollowed}</span>
          </div>
          <div className={"followers-container"}>
            <p>Seguidores: </p>
            <span className={"highlight-text-color"}>{numFollowers}</span>
          </div>
        </div>
      </div>
  );
}