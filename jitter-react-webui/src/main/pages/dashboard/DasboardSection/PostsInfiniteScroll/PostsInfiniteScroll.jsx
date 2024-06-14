import React, { useState, useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import axios from "axios";
import {resolveEndpoint} from "../../../../utils/endpoints";
import {Post} from "../../../../components/Post/Post";
import './postsInfiniteScroll.scss'

export function PostsInfiniteScroll({userId}){
    const [apiResponse, setApiResponse] = useState({});
    const [posts, setPosts] = useState([]);
    const [hasMore, setHasMore] = useState(true);
    const [pageIndex, setPageIndex] = useState(0);

    useEffect(() => {
        console.log("GET : " + resolveEndpoint(`/api/feed/posts/forUser/${userId}/paginated/${pageIndex}?numElements=3`))
        axios.get(resolveEndpoint(`/api/feed/posts/forUser/${userId}/paginated/${pageIndex}?numElements=3`))
            .then((res) => {
                setApiResponse(res.data);
                setPosts(res.data.content);
                setHasMore(!res.data.last);
            })
            .catch((err) => console.log(err));
        }, []);

    function fetchMoreData() {
        console.log("FETCHING MORE DATA");
        axios.get(resolveEndpoint(`/api/feed/posts/forUser/${userId}/paginated/${pageIndex}?numElements=8`))
            .then((res) => {
                console.log("FETCHED MORE DATA: " + res.data);
                setApiResponse(res.data);
                setPosts([...posts, ...res.data.content]);
                res.data.numberOfElements > 0 ? setHasMore(true) : setHasMore(false);

            })
            .catch((err) => console.log("ERROR fetching more data: " + err));

        setPageIndex(pageIndex + 1);
    }

    return (
        <div id={"scrollableContainer"}>
            <InfiniteScroll
                dataLength={posts.length}
                next={fetchMoreData}
                hasMore={hasMore}
                loader={<h3 className={"loading-message"}>Cargando...</h3>}
                endMessage={<h3 className={"no-more-feed-message"}>¡Ya lo has visto todo!</h3>}
                scrollableTarget={"scrollableContainer"}>
                <div className='posts-container'>
                    {apiResponse &&
                        posts.map((item) => <Post key={item.id} postInfo={item}/>)}
                </div>
            </InfiniteScroll>
        </div>

    );
}