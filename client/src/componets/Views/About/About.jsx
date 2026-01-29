import React from 'react';
import styles from './About.module.css'
export default function About(){
    return(
         <div className={styles.about}>
            <h1>About Us</h1>

            <section className={styles.content}>
            <p>I developed this project as an educational and practical activity.</p>
            <p>At the moment the site is built on the principle of MVP.</p>
            <p>
                Users can register on the website and then log in with their username and password.
                They can also add ingredients they have at home and then search for dishes.
            </p>
            </section>
  </div>
    );
}