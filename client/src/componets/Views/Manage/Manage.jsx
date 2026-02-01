import React from "react";
import UserIngService from "../../../services/UserIngService";
import UnitService from "../../../services/UnitService"
import IngredientService from "../../../services/IngredientService"
import { useState, useContext, useEffect } from "react";
import { AuthContext } from "../../context/AuthContext";

export default function Manage(){
    const {isAuth, token} = useContext(AuthContext);
    const [ing, setIng] = useState([]);
    const [unit, setUnit] = useState([]);
    const [userIng, setUserIng] = useState([]);
    const [userIngRead, setUserIngRead] = useState([]);


    return(
        <>

        </>
    )
}