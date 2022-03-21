import React, {useContext} from "react";
import { useLocation } from "react-router-dom";
import { AuthContext } from "../AuthContext";
import "../css/Navbar.css"

function NavBar(props) {
    const {loggedIn} = useContext(AuthContext)
    useLocation();
    function logout() {
        localStorage.clear();
        window.location.replace("/login");
    }          
        { if(loggedIn) {
            return(
                <nav>
                <ul>
                    <li className="nav-item">
                        <a href="/">home</a>
                    </li>
                    <li className="nav-item">
                        <a href="/profile">profile</a>
                    </li>
                    <li className="nav-item">
                        <a href="/users">users</a>
                    </li>
                    <li className="nav-item">
                        <a onClick={() => logout()} href="#">logout</a>
                    </li>
                    </ul>
                    </nav>
            )
        } else if(!loggedIn && window.location.href == "http://localhost:3000/login") {
            return(
                <nav>
                    <ul>
                        <li>
                            <a href="/signup">signup</a>
                        </li>
                    </ul>
                </nav>
            )
        } else if(!loggedIn && window.location.href == "http://localhost:3000/signup") {
            return(
                <nav>
                    <ul>
                        <li>
                            <a href="/login">login</a>
                        </li>
                    </ul>
                </nav>
                    
            )
        } else {
            return(
                <nav>
                    <ul>
                        <li>
                            <a href="/login">login</a>
                        </li>
                        <li>
                            <a href="/signup">signup</a>
                        </li>
                    </ul>
                </nav>
            )
        }
}
}
export default NavBar;

