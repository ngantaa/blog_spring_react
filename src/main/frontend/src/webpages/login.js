import React, { useContext, useEffect, useState } from 'react';
import axios from 'axios';


function LoginForm(props) {
    const url = "http://127.0.0.1:8080/api/authenticate";
    const [data, setData] = useState({
        username: "",
        password: "",
    });
    function login(event) {
        event.preventDefault();
        axios.post(url, {
            username: data.username,
            password: data.password
        }
           )
           .then(
               response => {
                   localStorage.setItem("token", response.data);
                   console.log(response.data);
                   window.location.replace("/");
                }
           )
           .catch(error =>{
               console.log(error.response);
   
           });
    }
    function handleChange(event) {
        const newData = {...data};
        newData[event.target.id] = event.target.value;
        setData(newData);
    }
    return (
        <div className="w-25">
            <form className="d-flex flex-column" onSubmit={(event) => login(event)}>
                <input className="m-1" id="username" onChange={(event) => handleChange(event)} value={data.username} placeholder='username' type='text'></input>
                <input className="m-1" id="password" onChange={(event) => handleChange(event)} value={data.password} placeholder='password' type='password'></input>
                <button className="btn btn-primary">Submit</button>
            </form>
        </div>
    )
}

export default LoginForm;