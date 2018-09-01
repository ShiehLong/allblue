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
    <title>角色[${roleInfo.name}]详细信息</title>
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
            <td>图片:</td>
            <td><img id="image" src="${roleInfo.pic}" class="img-circle" style="width: 128px;height: 128px;"></td>

        </tr>
        <tr>
            <td>角色标识:</td>
            <td>${roleInfo.id }</td>
        </tr>
        <tr>
            <td>角色名:</td>
            <td>${roleInfo.name }</td>
        </tr>
        <tr>
            <td>角色性别:</td>
            <td>${roleInfo.sex }</td>
        </tr>
        <tr>
            <td>角色年龄:</td>
            <td>${roleInfo.age }</td>
        </tr>
        <tr>
            <td>角色描述:</td>
            <td>${roleInfo.description }</td>
        </tr>
        <tr>
            <td>角色视频:</td>
            <td>${roleInfo.video }</td>
        </tr>
    </table>
    <div style="text-align: center;">
        <a href="/role/list" class="btn btn-primary" role="button">返回列表</a>
        <a href="/role/${roleInfo.id }/update" class="btn btn-primary" role="button">更新</a>
    </div>
</div>
<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>