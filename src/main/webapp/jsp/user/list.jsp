<%--
  Created by IntelliJ IDEA.
  User: Xone
  Date: 2018/8/6
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <style>
        table td {
            text-align: center;
        }
    </style>
</head>
<body>
<table width="80%" align="center" border="1">
    <tr>
        <td>用户ID</td>
        <td>用户名</td>
        <td>用户密码</td>
        <td>用户邮箱</td>
        <td>操作</td>
    </tr>
    <c:if test="${list.size() le 0 }">
        <tr>
            <td colspan="6">目前还没有用户数据</td>
        </tr>
    </c:if>
    <c:if test="${list.size() gt 0}">
        <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.id }</td>
                <td>
                    <a href="/user/${u.id}/detail">${u.username }</a>
                </td>
                <td>${u.password }</td>
                <td>${u.email }</td>
                <td>
                    <a href="/user/${u.id }/update">更新</a>
                    <a href="/user/${u.id }/delete">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
