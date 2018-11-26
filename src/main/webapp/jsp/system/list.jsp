<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>菜单树</title>
    <link rel="stylesheet" type="text/css" href="/css/metroStyle/metroStyle.css">
</head>
<body>
<%@ include file="/jsp/common/header.jsp" %>
<div class="container-fluid">
    <div class="row menu-row">
        <div class="col-md-12" style="text-align: left;">
                <span class="auth-menu">
                    <button type="button" class="btn btn-default" onclick="openCreateModel()">
                        新建页面元素
                    </button>
                </span>
        </div>
    </div>
    <hr style="margin:0;background-color:#2092d6;height:2px;">

    <div id="tree-container" class="row context-row" style="margin: 15px 0px; overflow-y:scroll">
        <div class=" col-md-12">
            <ul id="regionZTree" class="ztree"></ul>
        </div>
    </div>

    <hr style="margin:0;background-color:#2092d6;height:2px;">
</div>

<%--新建model--%>
<div id="createSystem" class="modal fade" role="dialog" aria-labelledby="createModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新建系统</h4>
            </div>

            <!-- 表单体 -->

            <div class="modal-body col-md-12">
                <form id="create-auth-form" class="form-horizontal">

                    <div class="form-group">
                        <label for="create_name" class="col-sm-2 control-label">菜单名称</label>
                        <div class="col-sm-10">
                            <input id="create_name" type="text" name="name" class="form-control" aria-describedby="basic-addon1">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create_code" class="col-sm-2 control-label">系统编码</label>
                        <div class="col-sm-10">
                            <input id="create_code" type="text" name="code" class="form-control" aria-describedby="basic-addon1">
                            <small>建议：编码可以为任意字符串，但建议根据业务含义填写，如 system.create</small>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="create_pcode" class="col-sm-2 control-label">所属系统</label>
                        <div class="col-sm-10">
                            <select id="create_pcode" name="systemId" class="form-control select2" >

                            </select>

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create_url" class="col-sm-2 control-label">菜单链接</label>
                        <div class="col-sm-10">
                            <textarea id="create_url" class="form-control" rows="3" name="url"></textarea>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="create_remark" class="col-sm-2 control-label">菜单描述</label>
                        <div class="col-sm-10">
                            <textarea id="create_remark" class="form-control" rows="3" name="remark"></textarea>
                        </div>
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitCreateForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery.ztree.all.min.js"></script>
<script src="/js/system/list.js"></script>
</body>
</html>