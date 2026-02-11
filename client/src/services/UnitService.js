import axios from "axios";

export default{
    getUnits({code=""}={}){
        return axios.get("/api/unit",{
            params: {code}
        });
    },
    getToBaseById(id){
        return axios.get(`/api/unit/base/${id}`);
    }

    
}