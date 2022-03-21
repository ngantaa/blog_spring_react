import { useEffect, useState } from "react"
import axios from "axios"
import "../css/Profile.css"

const Profile = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [user, setUser] = useState([])

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
                setError(error);
            }
        )
    }, [])

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
    return(
        <>
        <div className="wrapper">
            <img id="profilePicture" src={"http://localhost:8080/api/image/" + localStorage.getItem("userId")} />
            <p className="text-align-right">
                email : <br />
                full name : <br />
                date of birth : 
            </p>
            <p>
                {user.email}<br />
                {user.firstName + " " + user.lastName}<br />
                {user.dateOfBirth}
            </p>
        </div>
        <h2>Welcome, {user.username}</h2>
        <a href="/thread/new">post a new thread</a>
        <a href="/account/settings">edit profile</a>
        </>
    );
    }
}

export default Profile;