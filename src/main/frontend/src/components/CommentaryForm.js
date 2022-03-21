import React, { useState } from 'react';
import Axios from 'axios';
import { useParams } from 'react-router-dom';

const CommentaryForm = () => {
    let {id} = useParams();
    const [data, setData] = useState({
        commentaryContent: ""
    });

    function handleSubmission(event) {
        event.preventDefault();
        Axios.post(("http://localhost:8080/api/thread/" + id), {
            commentaryContent: data.commentaryContent
        },
        {headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }})
        .then(res => {
            console.log(res.data);
            window.location.reload();
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
                <input className="commentaryInput" onChange={(event) => handleChange(event)} id="commentaryContent" value={data.commentaryContent} placeholder='comment' type='text'></input>
                <button>Submit</button>
            </form>
        </div>
    )
}

export default CommentaryForm;