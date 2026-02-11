import axios from "axios";

export default {
  getAll(token) {
    return axios.get("/api/users/me/ingredients", {
      headers: { Authorization: `Bearer ${token}` },
    });
  },

  getAllRead(token) {
    return axios.get("/api/users/me/ingredients/read", {
      headers: { Authorization: `Bearer ${token}` },
    });
  },

  create(payload, token) {
    return axios.post("/api/users/me/ingredients", payload, {
      headers: { Authorization: `Bearer ${token}` },
    });
  },

  update(ingId, payload, token) {
    return axios.patch(`/api/users/me/ingredients/${ingId}`, payload, {
      headers: { Authorization: `Bearer ${token}` },
    });
  },

  delete(ingId, token) {
    return axios.delete(`/api/users/me/ingredients/${ingId}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
  },
};