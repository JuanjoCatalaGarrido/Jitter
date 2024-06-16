
import {API_HOSTNAME, API_PORT, AI_API_PORT} from '../config/globalVars'

export function resolveEndpoint(serverPath){
    let socket = "http://" + API_HOSTNAME + ":" + API_PORT;
    if(!serverPath.startsWith("/")){
        return socket + "/" + serverPath;
    }
    return socket + serverPath;
}


export function resolveAIEndpoint(serverPath){
    let socket = "http://" + API_HOSTNAME + ":" + AI_API_PORT;
    if(!serverPath.startsWith("/")){
        return socket + "/" + serverPath;
    }
    return socket + serverPath;
}