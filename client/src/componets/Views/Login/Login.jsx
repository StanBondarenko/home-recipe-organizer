import React from "react";
import UserService from "../../../services/UserService";
import { useState, useContext} from "react";
import { useNavigate, Link } from "react-router-dom";
import styles from "./Login.module.css";
import { AuthContext } from "../../context/AuthContext"; 
import Registration from "../Registrate/Registraite";
export default function Login(){
   const [identifier, setIdentifier] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("")
    const navigate = useNavigate();
    const { login } = useContext(AuthContext);

   function handleSubmit(e) {
    e.preventDefault();
    setError("");
    UserService.login(identifier, password)
      .then((res) => {
        const token = res.data.token;
        login(token);      
        navigate("/recipe");
      })
      .catch((err) => {
        const msg =
          err.response?.data?.message ||
          err.response?.data ||
          "Invalid login or password";
        setError(String(msg));
      });
  }

 return (
   <form className={styles.loginForm} onSubmit={handleSubmit}>
  <label htmlFor="login" className={styles.label}>
    Email/Login:
  </label>
  <input
    id="login"
    type="text"
    className={styles.input}
    value={identifier}
    onChange={(e) => setIdentifier(e.target.value)}
    placeholder="Enter login or Email"
  />
  <label htmlFor="password" className={styles.label}>
    Password:
  </label>
  <input
    id="password"
    type="password"
    className={styles.input}
    value={password}
    onChange={(e) => setPassword(e.target.value)}
    placeholder="Password"
  />
  <p>Still don't have an account?<Link to="/reg">Sign up.</Link></p>
  {error && <p className={styles.error}>{error}</p>}
  <button type="submit" className={styles.button}>
    Enter
  </button>
</form>
  );
}