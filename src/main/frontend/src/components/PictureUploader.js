import { useState } from "react";
import axios from "axios";

const PictureUploader = () => {
const [file, setFile] = useState(null);


function handleChange(event) {
    setFile(event.target.files[0])
  }

  function handleSubmit(event) {
    event.preventDefault()
    const url = 'http://localhost:8080/api/user/' + localStorage.getItem("userId") + '/picture';
    const formData = new FormData();
    formData.append('image', file);
    const config = {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("token"),
        'content-type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      window.location.replace("http://localhost:3000/profile");
    });
  }

  return (
    <div>
        <form onSubmit={handleSubmit}>
          <h4>Change your profile picture</h4>
          <label htmlFor="filePicker" className="custom-file-upload">
            Select a file
          </label>
          <input id="filePicker" type="file" onChange={handleChange}/>
          <button type="submit">Upload</button>
        </form>
    </div>
  );

}
export default PictureUploader;