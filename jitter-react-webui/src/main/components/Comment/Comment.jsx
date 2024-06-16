import './comment.scss';

export function Comment({commentInfo}){
    const date = new Date(commentInfo.createdAt);

    return (
        <div className={"comment"}>
            <div className={"user-img-container"}>
                <img src={commentInfo.owner.profileImgUrl}/>
            </div>
            <div className={"comment-content"}>
                <div className={"comment-content-first-section"}>
                    {<a className={"highlight-text-color "} href={`/user/${commentInfo.owner.id}`}>@{commentInfo.owner.username}</a>}
                    <span className={"comment-content-timestamp"}>{date.toLocaleString('es-ES', {
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric',
                        hour: '2-digit',
                        minute: '2-digit',
                        hour12: false,
                    })}</span>
                </div>
                <p className={"comment-content-body"}>{commentInfo.body}</p>
            </div>
        </div>
    );
}