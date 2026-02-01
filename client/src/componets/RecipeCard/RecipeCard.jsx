import styles from "./RecipeCard.module.css";
import { AuthContext } from "../context/AuthContext";
import React, { useContext, useEffect, useState } from "react";
import UserFavoriteService from "../../services/UserFavoriteService";
import RecipeService from "../../services/RecipeService";


export default function RecipeCard({ id }) {
  const { isAuth, token } = useContext(AuthContext); 
  const[ favorite, setFavorite] = useState([]);
  const [isInFavorite, setIsInFavorite] = useState(false);
  const [recipe, setRecipe] = useState([]);
    useEffect(()=>{
        RecipeService.find({ id })
        .then((res) => setRecipe(res.data[0]))
        .catch((err) => console.log(err));
      },[id])

    useEffect(() => {
        if (!isAuth || !id) return;
        UserFavoriteService.getAll(token)
          .then((res) => {
            setFavorite(res.data);
            const exists = res.data.some(f => f.recId === id);
            setIsInFavorite(exists);
          });
    }, [isAuth, token, id ]);
    
 return (
  <article className={styles.card}>
    <div className={styles.imageWrapper}>
      <img
        className={styles.image}
        src={"http://localhost:8080/api" + recipe.picURL}
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
