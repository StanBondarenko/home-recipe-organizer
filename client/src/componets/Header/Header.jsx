import styles from "./Header.module.css";
import React, { useContext, useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import UserService from "../../services/UserService";

export default function Header() {
  const { isAuth, token, logout } = useContext(AuthContext); 
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  const linkClass = ({ isActive }) =>
    `${styles.navLink} ${isActive ? styles.active : ""}`;

  const handleLogout = () => {
    logout();
    setUser(null);         
    navigate("/login");
  };

  useEffect(() => {
    if (!isAuth || !token) {
      setUser(null);
      return;
    }

    UserService.getCurrentUser(token)
      .then((res) => setUser(res.data))
      .catch(() => setUser(null));
  }, [isAuth, token]);

 return (
  <header className={styles.header}>
    <div className={styles.topFade} />

    <div className={styles.container}>
      <nav className={styles.nav}>
        <div className={styles.brand}>
          <img className={styles.logo} alt="logo" src="../../res/logo.png"/>
        </div>
        <div className={styles.navLeft}>
          <NavLink className={linkClass} to="/about">
            ABOUT US
          </NavLink>

          <NavLink className={linkClass} to="/recipe">
            ALL RECIPES
          </NavLink>

          {isAuth && user?.login && (
            <NavLink className={linkClass} to="/room">
              {`${user.login}'S ROOM`}
            </NavLink>
          )}
        </div>

        <div className={styles.navRight}>
          {isAuth ? (
            <button
              type="button"
              className={`${styles.navLink} ${styles.login}`}
              onClick={handleLogout}
            >
              LOGOUT
            </button>
          ) : (
            <NavLink className={`${styles.navLink} ${styles.login}`} to="/login">
              LOGIN
            </NavLink>
          )}
        </div>
      </nav>
    </div>
  </header>
);
}
