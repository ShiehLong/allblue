<%--
  Created by IntelliJ IDEA.
  User: Xone
  Date: 2018/8/6
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../common/head.jsp" %>
    <title>用户[${userInfo.username}]详细信息</title>
    <style type="text/css">
        td {
            text-align: center;
        }

        img {
            width: 128px;
            height: 128px;
        }
    </style>
</head>

<body>
<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <td>头像:</td>
            <td><img id="image" src="${userInfo.photo}" class="img-circle"></td>

        </tr>
        <tr>
            <td>用户标识:</td>
            <td>${userInfo.id }</td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td>${userInfo.username }</td>
        </tr>
        <tr>
            <td>用户密码:</td>
            <td>${userInfo.password }</td>
        </tr>
        <tr>
            <td>用户邮箱:</td>
            <td>${userInfo.email }</td>
        </tr>
    </table>
    <div style="text-align: center;">
        <a href="/index.jsp" class="btn btn-primary" role="button">首页</a>
        <a href="/user/${userInfo.id }/update" class="btn btn-primary" role="button">更新</a>
    </div>
</div>
<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>