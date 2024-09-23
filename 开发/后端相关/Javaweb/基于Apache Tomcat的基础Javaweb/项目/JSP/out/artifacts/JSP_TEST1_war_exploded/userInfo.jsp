<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    } else {
        String username = (String) session.getAttribute("username");
%>
<h1>欢迎, <%= username %>!</h1>
<a href="LogoutServlet">退出登录</a>
<%
    }
%>
</body>
</html>
