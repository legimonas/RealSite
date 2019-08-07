<%@ page import="com.company.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19.07.2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<% User user = (User)request.getAttribute("User"); %>
<h1>Имя: <%=user.getName()%></h1>
<h1>mail: <%=user.getUserMail()%></h1>
<h1>День рождения: <%=user.getBirthdate()%></h1>

<a href="home" align="center">To Leave</a>
</body>
</html>
