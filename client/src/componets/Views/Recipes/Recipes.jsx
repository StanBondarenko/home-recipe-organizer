import React from "react";
import TypeService from "../../../services/TypeService";
import RecipeService from "../../../services/RecipeService";
import RecipeCard from "../../RecipeCard/RecipeCard";
import styles from "./Recipes.module.css"
import { Link } from "react-router-dom";
import { useState, useEffect} from "react";
import RecipePage from "../RecipePage/RecipePage";
 
export default function Recipe() {
  const [types, setTypes] = useState([]);
  const [selectedType, setSelectedType] = useState("");
  const [name, setName] = useState("");
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    TypeService.getAll().then((res) => setTypes(res.data));
  }, []);

  function handelChange(e) {
    setSelectedType(e.target.value);
  }

  function handeleChangeName(e) {
    setName(e.target.value);
  }
    useEffect(()=>{
      RecipeService.find({ type: selectedType, name: name.trim() })
      .then((res) => setRecipes(res.data))
      .catch((err) => console.log(err));
    },[])

  function handleFindClick() {
    RecipeService.find({ type: selectedType, name: name.trim() })
      .then((res) => setRecipes(res.data))
      .catch((err) => console.log(err));
  }

  return (
    <div className={styles.page}>
      <div className={styles.filters}>
        <div className={styles.field}>
          <label className={styles.label} htmlFor="searchInput">
            Search by name
          </label>
          <input
            id="searchInput"
            className={styles.input}
            type="text"
            value={name}
            onChange={handeleChangeName}
            placeholder="Enter name"
          />
        </div>

        <div className={styles.field}>
          <label className={styles.label} htmlFor="typeSelect">
            Dish type
          </label>
          <select
            id="typeSelect"
            className={styles.select}
            value={selectedType}
            onChange={handelChange}
          >
            <option value="">All types</option>
            {types.map((t) => (
              <option key={t.typeId} value={t.typeName}>
                {t.typeName}
              </option>
            ))}
          </select>
        </div>

        <button className={styles.button} onClick={handleFindClick}>
          Find
        </button>
      </div>
      <div className={styles.recipes}>
        {recipes.length > 0 ? (
          recipes.map((rec) => (
            <Link key={rec.id} to={"/recipe/"+rec.id}>
            <div className={styles.cardWrap} key={rec.recId}>
              <RecipeCard recipe={rec} />
            </div>
            </Link>
          ))
        ) : (
          <p className={styles.empty}>No recipes found</p>
        )}
      </div>
    </div>
  );

}