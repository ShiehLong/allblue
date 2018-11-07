<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/css/allblue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
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
<div class="table-responsive" style="padding: 10px;">
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
<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
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