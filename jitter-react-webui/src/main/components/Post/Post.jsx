import './post.scss'
import {useState} from "react";
import { Icon } from '@iconify/react';
import {Comment} from "../Comment/Comment";

export function Post({postInfo}){

    const [shouldRenderComments, setShouldRenderComments] = useState(false);
    const [shouldRenderInteractionsPanel, setShouldRenderInteractionsPanel] = useState(false);

    const date = new Date(postInfo.createdAt);

    function handleCommentsButtonClick(e){
        e.preventDefault();
        setShouldRenderComments(!shouldRenderComments);
    }

    function handleInteractionsButtonClick(e){
        e.preventDefault();
        setShouldRenderInteractionsPanel(!shouldRenderInteractionsPanel);
    }

    return (
        <div className={"post"}>
            <div className={"post-main"}>
                <div className={"user-img-container"}>
                    {
                        postInfo.owner.profileImgUrl != null ?
                            <img src={postInfo.owner.profileImgUrl}/> :
                            <Icon icon="ph:user-bold" width={"2rem"}/>
                    }

                </div>
                <div className={"post-content"}>
                    <div className={"post-content-first-section"}>
                        <span className={"post-content-username"}>
                            {<a className={"highlight-text-color "} href={`/user/${postInfo.owner.id}`}>@{postInfo.owner.username}</a>}
                        </span>
                        <span className={"post-content-timestamp"}>{date.toLocaleString('es-ES', {
                            year: 'numeric',
                            month: 'long',
                            day: 'numeric',
                            hour: '2-digit',
                            minute: '2-digit',
                            hour12: false,
                        })}</span>
                    </div>
                    <p className={"post-content-body"}>{postInfo.body}</p>
                </div>
            </div>
            <div className={"post-options"}>
                <div className={"options-container"}>

                    {
                        shouldRenderInteractionsPanel &&
                        <div className={"interactions-container"}>
                            {
                                postInfo.interactions.map((item) => (
                                    <div>{item.interactionType}</div>
                                ))
                            }
                        </div>
                    }

                    <span>{postInfo.interactions.length}</span>
                    <Icon icon="ic:outline-add-reaction" height={"1.5rem"} className={"clickable-icon"}
                          onClick={handleInteractionsButtonClick}/>
                </div>
                <div className={"options-container"}>
                    <span className={"options-container"}>{postInfo.userComments.length}</span>
                    <Icon icon="material-symbols:comment-outline" height={"1.5rem"} className={"clickable-icon"}
                          onClick={handleCommentsButtonClick}/>
                </div>
                <div className={"options-container"}>
                    <span className={"options-container"}>{postInfo.reports.length}</span>
                    <Icon icon="material-symbols:report-outline" className={"clickable-icon"} height={"1.5rem"}/>
                </div>
            </div>
            {
                shouldRenderComments && (
                    <div className={"comments-container"}>
                        {
                            postInfo.userComments.map((item) => (
                                <Comment commentInfo={item}/>
                            ))
                        }

                    </div>
                )
            }
        </div>
    );

}