import axios, { Axios } from 'axios';
import React, { useState, useEffect }  from 'react';
import ThreadCard from '../components/ThreadCard';
const ThreadList = () => {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [threads, setThreads] = useState([]);
    let url = "http://localhost:8080/api/threads";
    useEffect(() => {
        axios.get(url,
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
                    setThreads(data);

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
            <ul>
                {threads.map(thread => (
                <ThreadCard thread={thread} key={thread.id} />
                ))}
            </ul>
        );
    }
}
export default ThreadList;