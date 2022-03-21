import React, { useState } from 'react';
import Axios from 'axios';

function RegistrationForm() {
    const url = "http://localhost:8080/api/signup";
    const [data, setData] = useState({
        userName: "",
        firstName: "",
        lastName: "",
        email: "",
        dateOfBirth: "",
        password: "",
    });
    function handleSubmission(event) {
        event.preventDefault();
        Axios.post(url, {
            userName: data.userName,
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            dateOfBirth: data.dateOfBirth,
            password: data.password
        })
        .then(res => {
            console.log(res.data);
            window.location.replace("http://localhost:3000/")
        }
        )
    }
    function handleChange(event) {
        const newData = {...data};
        newData[event.target.id] = event.target.value;
        setData(newData);
    }
    return (
        <div>
            <form onSubmit={(event) => handleSubmission(event)}>
                <div>
                    username : <input onChange={(event) => handleChange(event)} id="userName" value={data.userName} placeholder='username' type='text'></input>
                </div>
                <div>
                    first name : <input onChange={(event) => handleChange(event)} id="firstName" value={data.firstName} placeholder='first name' type='text'></input>
                </div>
                <div>
                    last name : <input onChange={(event) => handleChange(event)} id="lastName" value={data.lastName} placeholder='last name' type='text'></input>
                </div>
                <div>
                    email : <input onChange={(event) => handleChange(event)} id="email" value={data.email} placeholder='email' type='email'></input>
                </div>
                <div>
                    date of birth : <input onChange={(event) => handleChange(event)} id="dateOfBirth" value={data.dateOfBirth} placeholder='date of birth' type='date'></input>
                </div>
                <div>
                    password : <input onChange={(event) => handleChange(event)} id="password" value={data.password} placeholder='password' type='password'></input>
                </div>
                <button>Submit</button>
            </form>
        </div>
    )
}

export default RegistrationForm;