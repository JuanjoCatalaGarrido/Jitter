import './comment.scss';

export function Comment({commentInfo}){

    return (
        <div className={"comment"}>
            <div className={"user-img-container"}>
                <img src={commentInfo.owner.profileImgUrl}/>
            </div>
            <div className={"comment-content"}>
                <div className={"comment-content-first-section"}>
                    <span className={"comment-content-username"}>@{commentInfo.owner.username}</span>
                    <span className={"comment-content-timestamp"}>{commentInfo.createdAt}</span>
                </div>
                <p className={"comment-content-body"}>{commentInfo.body}</p>
            </div>
        </div>
    );
}