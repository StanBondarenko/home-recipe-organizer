import axios from "axios";

export default{
    getUnits(){
        return axios.get("/api/unit");
    }
}