import React from "react";
import { useState, useEffect } from "react";
import UserService from "../../../services/UserService";
import styles from './Registrate.module.css';
export default function Registration(){
    const [log, setLog] = useState("");
    const [fName, setFName] = useState("");
    const [lName, setLname] = useState("");
    const [email, setEmail] = useState("");
    const [passCheck1, setPassCheck1] = useState("");
    const [passCheck2, setPassCheck2] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [errorMess, setErrorMess] = useState("");

    function createUserData(){
        if(passCheck1 !== passCheck2){
            setErrorMess("The passwords don't match");
            setPassCheck1("");
            setPassCheck2("");
            return;
        }
        if(!email.includes('@')){
            setErrorMess("Invalid email");
            setEmail("")
            return;
        }

        return {
                login: log,
                firstName: fName,
                lastName: lName,
                email: email,
                password: passCheck1,
                birth: birthDate
                }
    }

    function handelCklickCreate(){
        if(log.trim().length === 0 
        || fName.trim().length === 0
        || lName.trim().length === 0 
        || email.trim().length === 0 
        || passCheck1.trim().length === 0 
        || passCheck2.trim().length === 0){
            setErrorMess("Fill in the required fields");
            return;
        }else{
            let user = createUserData();
            if (user){
                UserService.regist(user)
                .then((res)=>{
                    setErrorMess(res.data);
                })
                .catch((err)=>{
                    setErrorMess("")
                     const msg =
                        err.response?.data?.message ||
                        err.response?.data ||
                        "We got problem";
                        setErrorMess(String(msg));
                })
            }
        }
    }
    return(
       <form className={styles.form}>
    <label htmlFor="login" className={styles.label}>
      Login:
    </label>
    <input
      id="login"
      type="text"
      value={log}
      placeholder="Enter your login"
      onChange={(e) => setLog(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="fName" className={styles.label}>
      First name:
    </label>
    <input
      id="fName"
      type="text"
      value={fName}
      placeholder="Enter your first name"
      onChange={(e) => setFName(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="lName" className={styles.label}>
      Last name:
    </label>
    <input
      id="lName"
      type="text"
      value={lName}
      placeholder="Enter your last name"
      onChange={(e) => setLname(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="eMail" className={styles.label}>
      Email:
    </label>
    <input
      id="eMail"
      type="text"
      value={email}
      placeholder="Ente your email"
      onChange={(e) => setEmail(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="pass1" className={styles.label}>
      Password:
    </label>
    <input
      id="pass1"
      type="password"
      value={passCheck1}
      placeholder="Enter password"
      onChange={(e) => setPassCheck1(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="pass2" className={styles.label}>
      Repeat password:
    </label>
    <input
      id="pass2"
      type="password"
      value={passCheck2}
      placeholder="Repeat password"
      onChange={(e) => setPassCheck2(e.target.value)}
      className={styles.input}
    />

    <label htmlFor="dob" className={styles.label}>
      Birthday:
    </label>
    <input
      id="dob"
      type="date"
      value={birthDate}
      onChange={(e) => setBirthDate(e.target.value)}
      className={styles.input}
    />

    <button
      type="button"
      onClick={handelCklickCreate}
      className={styles.button}
    >
      Create
    </button>

    {errorMess && <p className={styles.error}>{errorMess}</p>}
  </form>
    )
}