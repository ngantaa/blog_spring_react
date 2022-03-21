import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import UserList from './userList';
import RegistrationForm from './registrationForm';
import ThreadList from './threadList';
import LoginForm from './login';
import Thread from './thread'
import NavBar from '../components/Navbar';
import Profile from './profile';
import Ban from '../components/Ban';
import NewThreadForm from './NewThreadForm';
import PrivateRoute from '../components/PrivateRoute';
import ProfileSettingForm from './accountsettings';
function Webpages() {
        return(
            <Router>
                <NavBar />
                <Ban />
                <Routes>
                    <Route path="/" element = {<PrivateRoute />}>
                        <Route exact path="/" element = {<ThreadList />} />
                        <Route exact path="/users" element = {<UserList />} />
                        <Route path = "/thread/:id" element = {<Thread />} />
                        <Route path = "/profile" element = {<Profile />} />
                        <Route path = "/thread/new" element = {<NewThreadForm />} />
                        <Route path = "/account/settings" element = {<ProfileSettingForm />} />
                    </Route>
                    <Route exact path="/signup" element = {<RegistrationForm />} />
                    <Route exact path="/login" element = {<LoginForm />} />
                </Routes>
            </Router>
        );
};
export default Webpages;