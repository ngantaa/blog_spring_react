<h1>Backend of a blog</h1>
Available at https://polar-bayou-46017.herokuapp.com/
and front-end application at https://cryptic-everglades-41257.herokuapp.com/
<h2>Rest api storing data in a postgreSQL database</h2>
Setup : 
<ul>
    <li>Language : Java 11</li>
    <li>Framework : Spring Boot</li>
    <li>Database Management System : PostgreSQL</li>
    <li>Build automation tool : Maven</li>
    <li>Dependancies : 
        <ul>
            <li>spring-boot-starter-data-jpa</li>
            <li>spring-boot-starter-security</li>
            <li>spring-boot-starter-web</li>
            <li>postgresql</li>
            <li>jjwt-jackson</li>
            <li>jjwt-impl</li>
        </ul>
    </li>
</ul>

The project implements security using a Bearer token stored in the local storage of the user's client.

The project provides the following endpoints
    /api/signup
        POST    create new user

    /api/user/{userId}
        DELETE  remove specified user

    /api/user/{userId}
        PATCH   update user informations

    /api/authenticated
        GET     get currently authenticated user

    /api/authenticate
        POST    login user

    /api/user/{userId}/picture
        POST    add user's profile picture

    /api/image/{userId}
        GET     get user's profile picture

    /api/thread/new
        POST    create new thread

    /api/threads
        GET     get all threads

    /api/thread/{threadId}/commentaries
        GET     get commentaries associated with specified thread

    /api/thread/{threadId}
        GET     get specified thread

    /api/thread/{threadId}
        DELETE  remove specified thread

    /api/commentary/{commentaryId}
        DELETE  remove specified commentary
