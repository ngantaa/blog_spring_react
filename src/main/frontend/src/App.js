import React, { useState } from 'react';
import Webpages from './webpages';
import { AuthContext } from './AuthContext';
import axios from 'axios';

function App() {
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
          localStorage.setItem("userId", JSON.stringify(data.userId))
      },
      (error) => {
          console.log(error)
      }
  )

const [loggedIn, setLoggedIn] = useState(false)

    async function getLoginStatus () {
      await axios.get("http://localhost:8080/api/users",
      {headers: {"Authorization": 'Bearer ' + localStorage.getItem("token")}}
      ).then(response => setLoggedIn(true)).catch(error => setLoggedIn(false));
    }
    
    getLoginStatus();

  return (
    <AuthContext.Provider value={{loggedIn}}>
      <div className="d-flex flex-column align-items-center justify-content-center">
        <Webpages/>
      </div>
    </AuthContext.Provider>
  );
}
export default App;