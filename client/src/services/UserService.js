import axios from "axios";

export default{
    regist(user){
        return axios.post("/api/auth/signup",user);
    },

    login(login, pass){
       return axios.post("/api/auth/signin",{
        identifier: login,
         password: pass
  });
},

    getCurrentUser(token) {
  return axios.get("/api/secured", {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
}
   
} 