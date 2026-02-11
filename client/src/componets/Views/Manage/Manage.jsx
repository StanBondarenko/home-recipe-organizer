import React, { useState, useContext, useEffect } from "react";
import UserIngService from "../../../services/UserIngService";
import UnitService from "../../../services/UnitService";
import IngredientService from "../../../services/IngredientService";
import { AuthContext } from "../../context/AuthContext";
import styles from "./Manage.module.css";

export default function Manage() {
  const { token } = useContext(AuthContext);
  const [ing, setIng] = useState([]);   
  const [unit, setUnit] = useState([]);   
  const [userIng, setUserIng] = useState([]);        
  const [userIngRead, setUserIngRead] = useState([]); 
  const [selectedIng, setSelectedIng] = useState("");
  const [selectedUnit, setSelectedUnit] = useState("");
  const [amount, setAmount] = useState("");
  const [updIngId, setUpdIngId] = useState("");
  const [updAmount, setUpdAmount] = useState("");
  const [updUnitId, setUpdUnitId] = useState("");
  const [delIngId, setDelIngId] = useState("");
  const [errMas, setErrMas] = useState("");

  const getToBaseByUnitId = (unitId) => {
    const found = unit.find((u) => String(u.unitId) === String(unitId));
    return found ? Number(found.toBase) : 0;
  };

  const refreshLists = () => {
    setErrMas("");

    const p1 = UserIngService.getAll(token)
      .then((ui) => setUserIng(ui.data))
      .catch(() => setErrMas("Problem with User ingredients"));

    const p2 = UserIngService.getAllRead(token)
      .then((ui) => setUserIngRead(ui.data))
      .catch(() => setErrMas("Problem with User ingredients or you don't have any."));

    return Promise.all([p1, p2]);
  };

  useEffect(() => {
    setErrMas("");
    UnitService.getUnits()
      .then((u) => setUnit(u.data))
      .catch(() => setErrMas("Problem with units"));
  }, []);

  useEffect(() => {
    if (!token) return;

    setErrMas("");
    IngredientService.getAll(token)
      .then((i) => setIng(i.data))
      .catch(() => setErrMas("Problem with ingredients"));

    refreshLists();
  }, [token]);

  function handelChangeIng(e) {
    setSelectedIng(e.target.value);
  }
  function handelChangeAmount(e) {
    setAmount(e.target.value);
  }
  function handelChangeUnit(e) {
    setSelectedUnit(e.target.value);
  }

  function handelAddClick() {
    setErrMas("");

    if (!selectedIng || !selectedUnit || !amount) {
      setErrMas("Fill out the form");
      return;
    }

    const toBase = getToBaseByUnitId(selectedUnit);
    if (!toBase) {
      setErrMas("Problem: unit toBase not found");
      return;
    }

    const payload = {
      ingId: Number(selectedIng),
      amount: Number(amount),
      unitId: Number(selectedUnit),
      amountBase: Number(amount) * Number(toBase),
    };

    UserIngService.create(payload, token)
      .then(() => {
        setErrMas("Ingredient was added.");
        setSelectedIng("");
        setSelectedUnit("");
        setAmount("");
        return refreshLists();
      })
      .catch(() => setErrMas("Problem with create ingredient"));
  }

  function handleUpdateClick() {
    setErrMas("");

    if (!updIngId || !updUnitId || !updAmount) {
      setErrMas("Fill out update form");
      return;
    }

    const toBase = getToBaseByUnitId(updUnitId);
    if (!toBase) {
      setErrMas("Problem: unit toBase not found (update)");
      return;
    }

    const dto = {
      amount: Number(updAmount),
      unitId: Number(updUnitId),
      amountBase: Number(updAmount) * Number(toBase),
    };

    UserIngService.update(Number(updIngId), dto, token)
      .then(() => {
        setErrMas("Ingredient was updated.");
        setUpdIngId("");
        setUpdAmount("");
        setUpdUnitId("");
        return refreshLists();
      })
      .catch(() => setErrMas("Problem with update"));
  }

function handleDeleteClick() {
  setErrMas("");

  if (!delIngId || isNaN(Number(delIngId))) {
    setErrMas("Select ingredient to delete");
    return;
  }

  UserIngService.delete(delIngId, token)
    .then(() => {
      setErrMas("Ingredient was deleted.");
      setDelIngId("");
      return refreshLists();
    })
    .catch(() => setErrMas("Problem with delete"));
}

return (
  <div className={`${styles.page} container`}>
    <section className={styles.userIng}>
      <h1>All your ingredients</h1>

      {userIngRead.length > 0 ? (
        <ul>
          {userIngRead.map((x) => (
            <li key={x.ingId}>
              {x.ingName} {x.amount} {x.unitCode}
            </li>
          ))}
        </ul>
      ) : (
        <p>{errMas || "You don't have any ingredients yet."}</p>
      )}
    </section>
    <div className={styles.actionsRow}>
      <section className={styles.add}>
        <h1>Add ingredient</h1>

        <select
          className={styles.selIng}
          value={selectedIng}
          onChange={handelChangeIng}
        >
          <option value="">Select ingredient</option>
          {ing.map((i) => (
            <option key={i.id} value={i.id}>
              {i.ingName}
            </option>
          ))}
        </select>

        <input
          type="number"
          value={amount}
          onChange={handelChangeAmount}
          placeholder="Enter amount"
        />

        <select
          className={styles.selectUnit}
          value={selectedUnit}
          onChange={handelChangeUnit}
        >
          <option value="">Select unit</option>
          {unit.map((u) => (
            <option key={u.unitId} value={u.unitId}>
              {u.code}
            </option>
          ))}
        </select>

        <button onClick={handelAddClick}>Add</button>
        {errMas && <p>{errMas}</p>}
      </section>

      <section className={styles.update}>
        <h1>Update ingredient</h1>

        <select value={updIngId} onChange={(e) => setUpdIngId(e.target.value)}>
          <option value="">Select ingredient</option>
          {userIngRead.map((x) => (
            <option key={x.ingId} value={x.ingId}>
              {x.ingName} ({x.amount} {x.unitCode})
            </option>
          ))}
        </select>

        <input
          type="number"
          value={updAmount}
          onChange={(e) => setUpdAmount(e.target.value)}
          placeholder="New amount"
        />

        <select
          value={updUnitId}
          onChange={(e) => setUpdUnitId(e.target.value)}
        >
          <option value="">Select unit</option>
          {unit.map((u) => (
            <option key={u.unitId} value={u.unitId}>
              {u.code}
            </option>
          ))}
        </select>

        <button onClick={handleUpdateClick}>Update</button>
      </section>


      <section className={styles.delete}>
        <h1>Delete ingredient</h1>

        <select value={delIngId} onChange={(e) => setDelIngId(e.target.value)}>
          <option value="">Select ingredient</option>
          {userIngRead.map((x) => (
            <option key={x.ingId} value={x.ingId}>
              {x.ingName}
            </option>
          ))}
        </select>

        <button onClick={handleDeleteClick}>Delete</button>
      </section>
    </div>
  </div>
);
}
