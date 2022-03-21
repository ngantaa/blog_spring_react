import React, { useState } from 'react';
import Axios from 'axios';

function NewThreadForm() {
    const [data, setData] = useState({
        threadName: "",
        content: ""
    });
    function handleSubmission(event) {
        event.preventDefault();
        Axios.post("http://localhost:8080/api/thread/new", {
            threadName: data.threadName,
            content: data.content,
        },
        {headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }})
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
                <h4>Write your thread</h4>
                <input className="newThreadTitle" onChange={(event) => handleChange(event)} id="threadName" value={data.threadName} placeholder='title' type='text'></input>
                <textarea onChange={(event) => handleChange(event)} id="content" value={data.content} placeholder='write the content here ...' rows="5"></textarea>
                <button>Submit</button>
            </form>
        </div>
    )
}

export default NewThreadForm;