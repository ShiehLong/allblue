<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
<%@ include file="/jsp/common/header.jsp" %>
<div class="table-responsive" style="margin-top: 60px;">
    <table width="80%" align="center" border="1">
        <tr>
            <td>用户ID</td>
            <td>用户名</td>
            <td>用户邮箱</td>
            <td>状态</td>
            <td>修改人</td>
            <td>修改时间</td>
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
                        <a href="/user/${u.id}/detail">${u.name }</a>
                    </td>
                    <td>${u.email }</td>
                    <td>${u.status}</td>
                    <td>${u.modifier}</td>
                    <td><fmt:formatDate value="${u.modified_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>
                        <a href="/user/${u.id }/update">更新</a>
                        <a href="/user/${u.id }/delete">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>

</body>
