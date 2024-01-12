<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/5/20
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>重置密码</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>
    <br>
    <c:if test="${!empty failMsg }">
        <div class="alert alert-danger">${failMsg }</div>
    </c:if>
    <form class="form-horizontal" action="/admin/user_reset" method="post">
        <input type="hidden" name="id" value="${param.id }">
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">用户名</label>
            <div class="col-sm-5">${param.username }</div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">邮箱</label>
            <div class="col-sm-5">${param.email }</div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">6-15位新密码（包括大小写字母的组合）</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="password" value="" required="required">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交修改</button>
            </div>
        </div>
    </form>

    <span style="color:red;"></span>

</div>
</body>
</html>