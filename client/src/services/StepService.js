import axios from "axios";

export default{
    getById(id){
        return axios.get("/api/recipes/"+id+"/steps");
    }
}