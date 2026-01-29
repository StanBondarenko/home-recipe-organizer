import axios from "axios";

export default {
  find({ type = "", id = 0, name = "" } = {}) {
    return axios.get("/api/rec", {
      params: { type, id, name }
    });
  }
};