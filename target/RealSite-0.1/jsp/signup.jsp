<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16.07.2019
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
    <%@ include file="/css/style.css" %>
</style>
<html>
<link>
<title>Title</title>
<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="form-style-6">
    <form action="signup" method="post">
        <label for="name">User name
            <input type="text" id="name" name="name"><br>
        </label>
        <label for="usermail">Mail
            <input type="text" id="usermail" name="usermail"><br>
        </label>
        <label for="birthdate">BirthDate
            <input type="date" id="birthdate" name="birthdate">
        </label>
        <br>
        <label for="password">Password<br>
            <input type="password" id="password" name="password"><br><br>
        </label>
        <input type="submit" value="Sign Up">
        <a href="home" align="center">To Home</a>
    </form>
</div>


</body>
</html>


