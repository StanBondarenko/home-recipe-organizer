import styles from "./RecipeCard.module.css";
import { AuthContext } from "../context/AuthContext";
import React, { useContext, useEffect, useState } from "react";
import UserFavoriteService from "../../services/UserFavoriteService";
export default function RecipeCard({ recipe }) {
  const imgSrc = "http://localhost:8080/api" + recipe.picURL;
  const { isAuth, token } = useContext(AuthContext); 
  const[ favorite, setFavorite] = useState([]);
  const [isInFavorite, setIsInFavorite] = useState(false);
  const recId = recipe?.id;

    useEffect(() => {
        if (!isAuth || !recId) return;
        UserFavoriteService.getAll(token)
          .then((res) => {
            setFavorite(res.data);
            const exists = res.data.some(f => f.recId === recId);
            setIsInFavorite(exists);
          });
    }, [isAuth, token, recId ]);
    
 return (
  <article className={styles.card}>
    <div className={styles.imageWrapper}>
      <img
        className={styles.image}
        src={imgSrc}
        alt={recipe.recipeName}
      />
        {isAuth && isInFavorite ? (
      <img
        className={styles.favoriteIcon}
        src="/res/Like.png"
        alt="favorite"
      />) : (<></>)}
    </div>

    <section className={styles.content}>
      <h2 className={styles.title}>{recipe.recipeName}</h2>
    </section>
  </article>
);
}
