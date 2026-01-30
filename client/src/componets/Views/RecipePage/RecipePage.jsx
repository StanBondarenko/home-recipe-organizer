import React from "react";
import StepService from "../../../services/StepService";
import RecipeService from "../../../services/RecipeService";
import DishIngService from "../../../services/DishIngService";
import UserFavoriteService from "../../../services/UserFavoriteService";
import { AuthContext } from "../../context/AuthContext";
import { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import styles from "./RecipePage.module.css";

export default function RecipePage(){
  const [steps, setSteps] = useState([]);
  const [recipe, setRecipe] = useState({});
  const[dishIng, setDishIng] = useState([]);
  const { id } = useParams(); 
  const { isAuth, token } = useContext(AuthContext); 
  const [isInFavorite, setIsInFavorite] = useState(false);
  
    useEffect(() => {
          if (!isAuth) return;
          UserFavoriteService.getByRecId(token, id)
            .then((res) => {
              console.log("getByRecId status:", res.status);
            setIsInFavorite(Array.isArray(res.data) && res.data.length > 0);
            })
            .catch(() => setIsInFavorite(false));
      }, [isAuth, token, id]);

    useEffect(()=>{
       StepService.getById(id).then((step)=> setSteps(step.data));
    },[id]);

    useEffect(()=>{
        RecipeService.find({id : id})
        .then((res)=> setRecipe(res.data[0]));
    },[id]);

    useEffect(()=>{
        DishIngService.getDishIng(id).then((ing)=> setDishIng(ing.data));
    },[id])

    function handleClic(){
      if(isInFavorite){ 
        UserFavoriteService
        .deleteFromFavorite(id, token).then(()=> setIsInFavorite(!isInFavorite))
        .catch((err) =>{})
      }else{
        UserFavoriteService.addToFavorite(id,token)
        .then(()=>{setIsInFavorite(!isInFavorite);})
        .catch((err) =>{})
      }
    }



   return (
  <div className={styles.recipe}>
    <div className={styles.title}>{recipe.recipeName}</div>

    <div className={styles.topRow}>
      <img
        className={styles.image}
        src={"http://localhost:8080/api" + recipe.picURL}
        alt="recipe Image"
      />
      <section className={styles.ingredients}>
        <div className={styles.ingredientsTitle}>Ingredients</div>
        <ul>
          {dishIng.length > 0 ? (
            dishIng.map((ing, index) => (
              <li key={index}>
                {ing.ingName} {ing.amount} {ing.code}
              </li>
            ))
          ) : (
            <p>Loading...</p>
          )}
        </ul>
      </section>
    </div>
    <section className={styles.steps}>
      <div className={styles.stepsTitle}>Steps</div>
      {steps.length > 0 ? (
        steps.map((step) => (
          <div key={step.stepNumber} className={styles.step}>
            {step.stepNumber}: {step.stepText}
          </div>
        ))
      ) : (
        <p>Loading...</p>
      )}
    </section>
    {isAuth ? (
    <img
      className={styles.favoriteIcon}
      src={ isInFavorite ? "/res/Like.png" : "/res/Unlike.png"}
      onClick={handleClic}/>) : (<></>) }
  </div>
);
}