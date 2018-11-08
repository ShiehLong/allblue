<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<%@ include file="/jsp/common/header.jsp" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <ul class="breadcrumb">
                <li>
                    <a href="#">数据中心</a> <span class="divider"></span>
                </li>
                <li class="active">
                    用户列表
                </li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="collapse navbar-collapse">
                        <form class="form-inline">
                        <span>
                            <button type="button" class="btn btn-primary" href="#modal-create" data-toggle="modal"
                                    style="margin-right: 50px;">
                                新建
                            </button>
                        </span>
                            <span>
                            <div class="form-group">
                                <input id="search-context" type="text" name="searchContext" class="form-control"
                                       placeholder="请输入查询信息">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="searchRole()"
                                    style="margin-left: 10px;">查询</button>
                        </span>
                        </form>
                    </div>
                </div>
            </div>
            <table class="table table-bordered table-striped" style="margin-top: 20px;">
                <thead>
                <tr>
                    <td>用户ID</td>
                    <td>用户名</td>
                    <td>用户邮箱</td>
                    <td>状态</td>
                    <td>修改人</td>
                    <td>修改时间</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
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
                            <td><fmt:formatDate value="${u.modified_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <a href="/user/${u.id }/update">更新</a>
                                <a href="/user/${u.id }/delete">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <div style="text-align: center;">
            <ul class="pagination pagination-lg">
                <li>
                    <a href="#">上一页</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">下一页</a>
                </li>
            </ul>
        </div>
        <%--新建model--%>
        <div id="modal-create" class="modal fade" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">
                            标题栏
                        </h3>
                    </div>
                    <div class="modal-body">
                        <p>
                            显示信息
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                        <button class="btn btn-primary">保存设置</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>