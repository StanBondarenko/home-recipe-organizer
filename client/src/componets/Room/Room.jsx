import React from "react";
import styles from "./Room.module.css"
import { useState, useContext, useEffect } from "react";
import { useNavigate, NavLink } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import UserFavoriteService from "../../services/UserFavoriteService";
import UserIngService from "../../services/UserIngService";
import IngredientService from "../../services/IngredientService";  
import UnitService from "../../services/UnitService";  
import RecipeCard from "../RecipeCard/RecipeCard";


export default function Room(){
    const {token} = useContext(AuthContext);
    const [favorite, setFavorite] = useState([]);
    const [errorMes, setErrorMes] = useState("");
    const [userIng, setUserIng] = useState([]);
    const [loading, setLoading] = useState(false);
    const [ingName, setIngName] = useState("");
    const navigate = useNavigate();


    useEffect(()=>{
        setLoading(false);
        UserFavoriteService.getAll(token)
        .then((res)=>{
            setFavorite(res.data)
            setLoading(true);
        })
        .catch((err)=>{
            const msg =
            err.response?.data?.message ||
            err.response?.data ||
            "We got problem";
            setErrorMes(String(msg));
        });
    },[token])

    useEffect(()=>{
        UserIngService.getAllRead(token)
        .then((res)=>{
            setUserIng(res.data);
        })
        .catch((err)=>{
            const msg =
            err.response?.data?.message ||
            err.response?.data ||
            "We got problem";
            setErrorMes(String(msg));
        })
    },[token])
    
    return(
        <>
        <section className={styles.favRecipe}>
            <h1>Favorite recipes:</h1>

            {favorite.length > 0 ? (
                <div className={styles.favWrap}>
                {favorite.map((userFav) => (
                    <div
                    key={userFav.recId}
                    className={styles.favItem}
                    onClick={() => navigate(`/recipe/${userFav.recId}`)}
                    >
                    <RecipeCard id={userFav.recId} />
                    </div>
                ))}
                </div>
            ) : (
                <p className={styles.empty}>You don't have any favorite dishes.</p>
            )}
        </section>

        <section className={styles.userIng}>
            <h1>My Ingredients:</h1>

            {userIng.length > 0 ? (
                <ul className={styles.ingList}>
                {userIng.map((ing, i) => (
                    <li key={i} className={styles.ingItem}>
                    {ing.ingName} {ing.amount} {ing.unitCode}
                    </li>
                ))}
                </ul>
            ) : (
                <p className={styles.empty}>You don't have the ingredients.</p>
            )}

            <NavLink className={styles.manageBtn} to="/user/manage">
                Manage
            </NavLink>
         </section>

        </>
    )
}