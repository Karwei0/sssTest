<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/5/10
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/simpleCart.min.js"></script>
</head>
<body>
<!--header-->
<jsp:include page="/header.jsp">
    <jsp:param name="flag" value="1"></jsp:param>
</jsp:include>

<div class="account">
    <div class="container">
        <div class="register">
            <c:if test="${!empty msg}">
                <div class="alter alter-danger"></div>
            </c:if>
            <form action="/user_register" method="post">
                <div class="register-top-grid">
                    <h3>注册新用户</h3>
                    <div class="input">
                        <span>用户名 <label style="color: red">*</label></span>
                        <input type="text" name="username" placeholder="请输入用户名" required="required">
                    </div>
                    <div class="input">
                        <span>邮箱 <label style="color: red">*</label></span>
                        <input type="email" name="email" placeholder="请输入邮箱" required="required">
                    </div>
                    <div class="input">
                        <span>密码 <label style="color: red">*</label></span>
                        <input type="password" name="email" placeholder="请输入密码" required="required">
                    </div>
                    <div class="input">
                        <span>收货人 </span>
                        <input type="text" name="name" placeholder="请输入收货人">
                    </div>
                    <div class="input">
                        <span>收货电话 </span>
                        <input type="text" name="phone" placeholder="请输入收货电话">
                    </div>
                    <div class="input">
                        <span>收货地址 </span>
                        <input type="text" name="address" placeholder="请输入收货地址">
                    </div>
                    <div class="clearfix"> </div>
                    <div class="register-but text-center">
                        <input type="submit" value="提交">
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
