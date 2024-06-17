'use strict'
import {useEffect, useMemo, useState} from "react";
import {resolveEndpoint} from "../utils/endpoints";
import {useAuth} from "./authentication";
import axios from "axios";

export function useGeUserIdFromUsername(username){

}


export function useUserInfo(id) {
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    axios.get(resolveEndpoint(`/api/users/${id}`))
        .then((response) => {
          setUserInfo(response.data);
        }).catch(error => setUserInfo(null));

  }, [id]);

  return [userInfo, setUserInfo];
}


export function useLoggedUserInfo() {
  const {userId} = useAuth();
  const [userInfo, setUserInfo] = useState(null);
  const [cache, setCache] = useState({});

  useEffect(() => {
    if(!userId){
      setUserInfo(null);
      return;
    }
    if(cache[userId]){
      setUserInfo(cache[userId]);
      return;
    }

    axios.get(resolveEndpoint(`/api/users/${userId}`))
        .then((response) => {
          const userData = {
            id: response.data.id,
            username: response.data.username,
            email : response.data.email,
            profileImgUrl: response.data.profileImgUrl,
            followersCount: response.data.followersCount,
            followsCount: response.data.followsCount,
            verifiedAt: response.data.verifiedAt,
            createdAt: response.data.createdAt,
            updatedAt: response.data.updatedAt,
              roles: response.data.roles,
              userPreferences: response.data.userPreferences,
              followers: response.data.followers,
              follows: response.data.follows
          };

          cache[userId] = userData;
          setUserInfo(userData);
        }).catch(error => setUserInfo(null));

  }, [userId]);

  return [userInfo, setUserInfo];
}