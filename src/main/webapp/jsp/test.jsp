<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 引入bootstrap样式 -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入bootstrap-table样式 -->
    <link href="/css/bootstrap-table.min.css" rel="stylesheet">

    <!-- jquery -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <!-- bootstrap-table.min.js -->
    <script src="/js/bootstrap-table.min.js"></script>
    <!-- 引入中文语言包 -->
    <script src="/js/bootstrap-table-zh-CN.min.js"></script>
    <!--sha1加密-->
    <script type="text/ecmascript" src="/js/sha1.js"></script>
    <script src="/js/test.js"></script>
</head>
<body>
<div class="container">
    <div class="row-fluid">
        <div class="span12">
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-12">
                    <div class="collapse navbar-collapse form-inline">
                        <button type="button" class="btn btn-primary" href="#createUser" data-toggle="modal"
                                style="margin-right: 50px;">
                            新建
                        </button>
                        <div class="form-group">
                            <input id="search_context" type="text" name="searchContext" class="form-control"
                                   placeholder="请输入查询信息" value="${searchContext}">
                        </div>
                        <button type="submit" class="btn btn-primary" id="searchUser" onclick="doQuery();"
                                style="margin-left: 10px;">查询
                        </button>
                    </div>
                </div>
            </div>
            <table id="table"></table>
        </div>
    </div>
</div>

<%--新建model--%>
<div id="createUser" class="modal fade" role="dialog" aria-labelledby="createModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="createModalLabel">
                    新建用户
                </h4>
            </div>
            <div class="modal-body">
                <form id="create_user_form" class="form-horizontal">
                    <div class="form-group">
                        <label for="create_name" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="create_name" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create_email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="create_email" placeholder="请输入邮箱">
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
<div id="editUser" class="modal fade" role="dialog" aria-labelledby="editModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="editModalLabel">
                    修改用户信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="edit_user_form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            <img id="image" src="/img/default.jpg" class="img-circle"
                                 style="width: 128px;height: 128px;" alt="photo">
                            <input type="file" id="photo" style="width: 128px;display: none">
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="edit_name" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_name" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="edit_email" value="">
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
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" placeholder="Password">
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="retryPassword" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="retryPassword" placeholder="retryPassword">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                <button type="submit" class="btn btn-primary" onclick="submitEditForm()">保存</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
