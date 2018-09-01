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
    <%@ include file="../common/head.jsp" %>
    <title>角色列表</title>
    <style>
        table td {
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <a href="/jsp/role/roleAdd.jsp" class="btn btn-primary" role="button">新增角色</a>
</div>
<div>
    <table width="80%" align="center" border="1">
        <tr>
            <td>角色ID</td>
            <td>名称</td>
            <td>性别</td>
            <td>年龄</td>
            <td>图片</td>
            <td>描述</td>
            <td>视频</td>
            <td>操作</td>
        </tr>
        <c:if test="${list.size() le 0 }">
            <tr>
                <td colspan="8">目前还没有角色数据</td>
            </tr>
        </c:if>
        <c:if test="${list.size() gt 0}">
            <c:forEach items="${list}" var="role">
                <tr>
                    <td>${role.id }</td>
                    <td>
                        <a href="/role/${role.id}/detail">${role.name }</a>
                    </td>
                    <td>${role.sex }</td>
                    <td>${role.age }</td>
                    <td><img id="image" src="${role.pic }" class="img-circle" style="width: 128px;height: 128px;"></td>
                    <td>${role.description }</td>
                    <td>${role.video }</td>
                    <td>
                        <a href="/role/${role.id }/update">更新</a>
                        <a href="/role/${role.id }/delete">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
