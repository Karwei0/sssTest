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
            <form action="/user_register" method="post">
                <div class="register-top-grid">
                    <h3>注册新用户</h3>
                    <div class="input">
                        <span>用户名 <label style="color: red">*</label></span>
                        <input type="text" name="username" placeholder="请输入用户名" required="required" value="${u.username}">
                        <c:if test="${!empty failUserNameMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failUserNameMsg }</div>
                        </c:if>
                    </div>
                    <div class="input">
                        <span>邮箱 <label style="color: red">*</label></span>
                        <input type="text" name="email" placeholder="请输入邮箱" required="required" value="${u.email}">
                        <c:if test="${!empty failEmailMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failEmailMsg }</div>
                        </c:if>
                    </div>
                    <div class="input">
                        <span>密码 <label style="color: red">*</label></span>
                        <input type="password" name="password" placeholder="请输入6-15位密码（包括大小写字母的组合）" required="required" value="${u.password}">
                        <c:if test="${!empty failPwdMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failPwdMsg }</div>
                        </c:if>
                    </div>
                    <div class="input">
                        <span>收货人 </span>
                        <input type="text" name="name" placeholder="请输入收货人" value="${u.name}">
                        <c:if test="${!empty failNameMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failNameMsg }</div>
                        </c:if>
                    </div>
                    <div class="input">
                        <span>收货电话 </span>
                        <input type="text" name="phone" placeholder="请输入收货电话" value="${u.phone}">
                        <c:if test="${!empty failPhoneMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failPhoneMsg }</div>
                        </c:if>
                    </div>
                    <div class="input">
                        <span>收货地址 </span>
                        <input type="text" name="address" placeholder="请输入收货地址" value="${u.address}">
                        <c:if test="${!empty failAddressMsg }">
                            <div class="alert-danger" style="width: 96%; padding-left: 0.5em">${failAddressMsg }</div>
                        </c:if>
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
