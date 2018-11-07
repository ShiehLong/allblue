<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户[${userInfo.name}]详细信息</title>
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
<%@ include file="/jsp/common/header.jsp" %>
<div class="table-responsive" style="margin: 60px auto;width: 40%;">
    <table class="table table-striped">
        <tr>
            <td>头像:</td>
            <td><img id="image" src="${userInfo.photo}" class="img-circle" alt="photo"></td>

        </tr>
        <tr>
            <td>用户标识:</td>
            <td>${userInfo.id }</td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td>${userInfo.name }</td>
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
<script>
    $(function () {
        var photo = "${userInfo.photo}";
        if (photo === null || photo === "") {
            document.getElementById('image').src = "/img/default.jpg";
        }
    });

</script>
</body>
</html>