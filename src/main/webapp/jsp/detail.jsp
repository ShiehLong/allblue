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
    <title>用户[${userInfo.username}]详细信息</title>
</head>

<body>
<table width="700" align="center" border="1">
    <tr>
        <th>头像:</th>
        <td style="text-align: center"><img id="image" src="${userInfo.photo}" style="width: 128px;height: 128px;"></td>

    </tr>
    <tr>
        <th>用户标识:</th>
        <td>${userInfo.id }</td>
    </tr>
    <tr>
        <th>用户名:</th>
        <td>${userInfo.username }</td>
    </tr>
    <tr>
        <th>用户密码:</th>
        <td>${userInfo.password }</td>
    </tr>
    <tr>
        <th>用户邮箱:</th>
        <td>${userInfo.email }</td>
    </tr>
</table>
<div style="text-align: center;">
    <a href="/user/home">首页</a>
    <a href="/user/${userInfo.id }/update">更新</a>
</div>
</body>
</html>