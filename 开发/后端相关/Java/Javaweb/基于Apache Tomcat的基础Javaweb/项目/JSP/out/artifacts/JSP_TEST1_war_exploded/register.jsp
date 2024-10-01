<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h1>注册新用户</h1>
<form action="RegisterServlet" method="post">
    <label for="username">用户名：</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">密码：</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
