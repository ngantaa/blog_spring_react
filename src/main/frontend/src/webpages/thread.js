import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import CommentaryForm from '../components/CommentaryForm';
import ThreadShow from '../components/ThreadShow'
const Thread = () => {
    const {id} = useParams();
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [thread, setThread] = useState({});
    const [commentaries, setCommentaries] = useState([]);

    const deleteCommentary = (id) => {
        axios.delete(("http://localhost:8080/api/commentary/" + id),
            {
                headers: {"Authorization": "Bearer " + localStorage.getItem("token")}
            }  
        )
        window.location.reload();
    }

    async function loadData() {

        await axios.get(
            ("http://localhost:8080/api/thread/" + id),
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
        )
        .then(response => {
                setThread(response.data);
            }
        )
        .catch(error => {
                setError(error);
            }
        );
        await axios.get(
            ("http://localhost:8080/api/thread/" + id + "/commentaries"),
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
        )
        .then( response => {
                setCommentaries(response.data);
            }
        )
        .catch(error => {
                setError(error);
            }
        );
        setIsLoaded(true);
    }

    useEffect(() => {
        loadData();
    }, [])

    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <>
                <ThreadShow thread={thread} />
    
                <ul>
    { commentaries.map(commentary => (
                    <li key={commentary.commentId}>
                            <div>
                                {commentary.commentaryContent}
                            </div>
                            <div>
                                {commentary.createdAt + " " + commentary.user.username}
                            </div>
        { commentary.user.userId == localStorage.getItem("userId") ?
                            <button onClick={() => deleteCommentary(commentary.commentId)}>delete</button>
        :
        null
        }
                    </li>
    ))}
                </ul>
                <CommentaryForm />
            </>
            );
    }
}
export default Thread;