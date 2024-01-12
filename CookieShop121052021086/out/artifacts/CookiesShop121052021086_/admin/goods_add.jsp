<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/5/23
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品添加</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>
    <br>
    <c:if test="${!empty msg }">
        <div class="alert alert-success">${msg }</div>
    </c:if>
    <c:if test="${!empty failMsg }">
        <div class="alert alert-danger">${failMsg }</div>
    </c:if>
    <br><br>
    <form class="form-horizontal" action="/admin/goods_add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">名称 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="name"  required="required" value="${goods.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">价格 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="price" required="required" value="${goods.price}">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">介绍</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="intro" required="required" value="${goods.intro}">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">库存 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="stock" required="required" value="${goods.stock}">
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">封面图片 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="file" name="cover"  id="input_file" required="required" value="goods.cover">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">详情图片1 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="file" name="image1"  id="input_file" required="required" value="${goods.image1}">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">详情图片2 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <input type="file" name="image2"  id="input_file" required="required" value="${goods.image2}">推荐尺寸: 500 * 500
            </div>
        </div>
        <div class="form-group">
            <label for="select_topic" class="col-sm-1 control-label">类目 <label style="color: red">*</label></label>
            <div class="col-sm-6">
                <select class="form-control" id="select_topic" name="typeid">
                    <c:forEach items="${typeList }" var="t">
                        <option value="${t.id }" <c:if test="${t.id == goods.type_id}">selected="selected"</c:if>>${t.name }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>