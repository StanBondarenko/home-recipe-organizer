import axios from "axios";

export default{
    getPecIngById(recId){
       return axios.get("/api/recipes/"+recId+"/ingredients");
    }
}