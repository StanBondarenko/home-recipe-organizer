import axios from "axios";

export default{
    getAll(token){
        return axios.get("/api/my/favorites",{
            headers:{
                Authorization: `Bearer ${token}`
            }
        });
    },
    getByRecId(token, recId){
        return axios.get(`/api/my/favorites/${recId}`,{
            headers:{
                Authorization: `Bearer ${token}`
            }
        });
    },
    addToFavorite(recId, token){
        return axios.post("/api/my/favorites/"+recId, null, {
            headers:{
                Authorization: `Bearer ${token}`
            }
        });
    },
    deleteFromFavorite(recId, token){
       return axios.delete("/api/my/favorites/"+recId,{
        headers:{
                Authorization: `Bearer ${token}`
            }
       });
    }
}