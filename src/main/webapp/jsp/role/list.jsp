<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>角色列表</title>
</head>
<body>
<%@ include file="/jsp/common/header.jsp" %>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="row" style="margin-bottom: 10px;">
                <div class="col-md-12">
                    <div class="collapse navbar-collapse form-inline">
                        <button type="button" class="btn btn-primary" data-target="#createRole" data-toggle="modal"
                                style="margin-right: 50px;">
                            新建
                        </button>
                        <div class="form-group">
                            <input id="search_context" type="text" name="searchContext" class="form-control"
                                   placeholder="请输入查询信息" value="${searchContext}">
                        </div>
                        <button type="submit" class="btn btn-primary" id="searchRole" onclick="doQuery();"
                                style="margin-left: 10px;">查询
                        </button>
                    </div>
                </div>
            </div>
            <table id="table"></table>
        </div>

        <%--新建model--%>
        <div id="createRole" class="modal fade" role="dialog" aria-labelledby="createModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 id="createModalLabel">
                            新建角色
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="create_role_form" class="form-horizontal">
                            <div class="form-group">
                                <label for="create_name" class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="create_name" placeholder="请输入角色名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="create_remark" class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                                    <textarea rows="3" cols="20" class="form-control" id="create_remark"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                        <button class="btn btn-primary" onclick="submitCreateForm()">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <%--修改model--%>
        <div id="editRole" class="modal fade" role="dialog" aria-labelledby="editModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 id="editModalLabel">
                            修改角色信息
                        </h4>
                        <input type="text" id="edit_id" value="" style="display: none">
                    </div>
                    <div class="modal-body">
                        <form id="edit_role_form" class="form-horizontal">
                            <div class="form-group">
                                <label for="edit_name" class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="edit_name" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-10">
                                    <input type="radio" name="status" value="1">有效
                                    <input type="radio" name="status" value="0">无效
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit_remark" class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                                    <textarea rows="3" cols="20" class="form-control" id="edit_remark"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                        <button class="btn btn-primary" onclick="submitEditForm()">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/role/list.js"></script>
</body>
</html>