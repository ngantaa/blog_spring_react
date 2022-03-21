import React, { useState, useEffect } from 'react';
import axios from 'axios';
import PictureUploader from '../components/PictureUploader';
import "../css/form.css";

function ProfileSettingForm() {
    const [user, setUser] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    const deleteUser = () => {
        if (window.confirm("This action is irreversible")) {
            axios.delete(("http://localhost:8080/api/user/" + localStorage.getItem("userId")),
                {
                    headers: {"Authorization": "Bearer " + localStorage.getItem("token")}
                }  
            )
            localStorage.clear();
            window.location.replace("http://localhost:3000/signup");
        }
    }

    function handleSubmission(event) {
        event.preventDefault();
        axios.patch(("http://localhost:8080/api/user/" + localStorage.getItem("userId")), {
            userName: data.userName,
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            dateOfBirth: data.dateOfBirth,
            password: data.password,
        },
        {
            headers: {"Authorization": "Bearer " + localStorage.getItem("token")}
        })
        .then(res => {
            console.log(res.data);
            window.location.replace("http://localhost:3000/profile")
        }
        )
    }
    function handleChange(event) {
        const newData = {...data};
        newData[event.target.id] = event.target.value;
        setData(newData);
    }

    useEffect(() => {
        axios.get("http://localhost:8080/api/authenticated",
        {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
            )
        .then(response => response.data)
        .then(
            (data) => {
                setIsLoaded(true);
                setUser(data);
            },
            (error) => {
                setIsLoaded(true);
                console.log(error);
            }
        )
    }, [])
    const [data, setData] = useState({
        userName: "",
        firstName: "",
        lastName: "",
        email: "",
        dateOfBirth: "",
        password: ""
    });

    if (isLoaded == false) {
        return (<p>Loading ...</p>)
    }
    return (
        <div>
            <form onSubmit={(event) => handleSubmission(event)}>
                <h4>Edit profile details</h4>
                <div>
                    username : <input onChange={(event) => handleChange(event)} id="userName" value={data.userName} placeholder={user.username} type='text'></input>
                </div>
                <div>
                    first name : <input onChange={(event) => handleChange(event)} id="firstName" value={data.firstName} placeholder={user.firstName} type='text'></input>
                </div>
                <div>
                    last name : <input onChange={(event) => handleChange(event)} id="lastName" value={data.lastName} placeholder={user.lastName} type='text'></input>
                </div>
                <div>
                    email : <input onChange={(event) => handleChange(event)} id="email" value={data.email} placeholder={user.email} type='email'></input>
                </div>
                <div>
                    date of birth : <input onChange={(event) => handleChange(event)} id="dateOfBirth" value={data.dateOfBirth} placeholder='date of birth' type='date'></input>
                </div>
                <div>
                    password : <input onChange={(event) => handleChange(event)} id="password" value={data.password} placeholder='password' type='password'></input>
                </div>
                <button>Submit</button>
            </form>
            <PictureUploader />
                <button id="deleteAccount" onClick={() => deleteUser()}>delete account</button>
        </div>
    )
}

export default ProfileSettingForm;