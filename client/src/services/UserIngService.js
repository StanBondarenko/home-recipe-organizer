import axios from "axios";

export default{
    getAll(token){
        return axios.get("/api/users/me/ingredients",{
            headers:{
                Authorization: `Baarer ${token}`
            }
        });
    },
    getAllRead(token){
        return axios.get("/api/users/me/ingredients/read",{
            headers:{
                Authorization: `Bearer ${token}`
            }
        });
    }

}