import React from "react";
import Header from "../Header/Header.jsx";
import About from "../Views/About/About";
import Recipe from "../Views/Recipes/Recipes";
import RecipePage from "../Views/RecipePage/RecipePage.jsx";
import Login from "../Views/Login/Login.jsx";
import Registration from "../Views/Registrate/Registraite.jsx";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

export default function App() {
  return (
    <BrowserRouter>
      <Header />

      <main style={{ paddingTop: "240px" }}>
        <Routes>
          <Route path="/" element={<Navigate to="/about" replace />} />
          <Route path="/about" element={<About />} />
          <Route path="/recipe" element={<Recipe />} />
          <Route path="/recipe/:id" element={<RecipePage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/reg" element={<Registration/>} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}
