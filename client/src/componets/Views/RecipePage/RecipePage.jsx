import React from "react";
import StepService from "../../../services/StepService";
import RecipeService from "../../../services/RecipeService";
import DishIngService from "../../../services/DishIngService";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import styles from "./RecipePage.module.css";

export default function RecipePage(){
   const [steps, setSteps] = useState([]);
   const [recipe, setRecipe] = useState({});
   const[dishIng, setDishIng] = useState([]);
    const { id } = useParams(); 
    let fullDataIng = [{}]; 
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
  </div>
);

    
}