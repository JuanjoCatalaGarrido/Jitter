
import {API_HOSTNAME, API_PORT} from '../config/globalVars'

export function resolveEndpoint(serverPath){
    let socket = "http://" + API_HOSTNAME + ":" + API_PORT;
    if(!serverPath.startsWith("/")){
        return socket + "/" + serverPath;
    }
    return socket + serverPath;
}