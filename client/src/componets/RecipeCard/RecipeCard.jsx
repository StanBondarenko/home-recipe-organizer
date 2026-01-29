import styles from "./RecipeCard.module.css";

export default function RecipeCard({ recipe }) {
  const imgSrc = "http://localhost:8080/api" + recipe.picURL;

  return (
    <article className={styles.card}>
      <img
        className={styles.image}
        src={imgSrc}
        alt={recipe.recipeName}
      />

      <section className={styles.content}>
        <h2 className={styles.title}>{recipe.recipeName}</h2>
      </section>
    </article>
  );
}
