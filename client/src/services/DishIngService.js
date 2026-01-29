import axios from "axios";

export default{
    getDishIng(recId){
        return axios.get('/api/'+recId+'/ingredients');
    }
}