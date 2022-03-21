import axios from 'axios';
import React, { useState, useEffect }  from 'react';

const UserList = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/users/",
            {
                headers: {
                    "Authorization": 'Bearer ' + localStorage.getItem("token")
                }
            }
            )
            .then(response => response.data)
            .then(
                (data) => {
                    setIsLoaded(true);
                    setUsers(data);
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
        return (
            <ul className="list-unstyled">
                {users.map(user => (
                <li className="my-2" key={user.userId}>
                    <img className="m-2" width="45" src={"http://localhost:8080/api/image/" + user.userId} />
                    {user.username}
                </li>
                ))}
            </ul>
        );
    }
}
export default UserList;