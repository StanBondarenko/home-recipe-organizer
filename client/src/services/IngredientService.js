import axios from "axios";

export default {
    getById(id){
        return axios.get("/api/ing"+id);
    },
    getAll(){
        return axios.get("/api/ing");
    }
}