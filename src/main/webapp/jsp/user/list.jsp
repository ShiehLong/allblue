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
                    <a href="/index.jsp">首页</a> <span class="divider"></span>
                </li>
                <li class="active">
                    用户列表
                </li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="collapse navbar-collapse">
                        <form class="form-inline" action="/user/getUserListBySearch" method="post">
                        <span>
                            <button type="button" class="btn btn-primary" href="#createUser" data-toggle="modal"
                                    style="margin-right: 50px;">
                                新建
                            </button>
                        </span>
                            <span>
                            <div class="form-group">
                                <input id="search_context" type="text" name="searchContext" class="form-control"
                                       placeholder="请输入查询信息" value="${searchContext}">
                            </div>
                            <button type="submit" class="btn btn-primary" id="searchUser"
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
                                    <%--href="/user/${u.id}/detail"--%>
                                <a href="#detailUser" data-toggle="modal">${u.name }</a>
                            </td>
                            <td>${u.email }</td>
                            <td>${u.status}</td>
                            <td>${u.modifier}</td>
                            <td><fmt:formatDate value="${u.modified_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <button type="button" class="btn btn-info" href="#editUser" data-toggle="modal">
                                    修改
                                </button>
                                <button type="button" class="btn btn-danger" href="#deleteUser" data-toggle="modal">
                                    删除
                                </button>
                                    <%--<a href="/user/${u.id }/update">修改</a>--%>
                                    <%--<a href="/user/${u.id }/delete">删除</a>--%>
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
        <div id="createUser" class="modal fade" role="dialog" aria-labelledby="createModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="createModalLabel">
                            新建用户
                        </h3>
                    </div>
                    <div class="modal-body">
                        <p>
                            显示信息
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                        <button class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>
        <%--详情model--%>
        <div id="detailUser" class="modal fade" role="dialog" aria-labelledby="detailModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="detailModalLabel">
                            用户详情
                        </h3>
                    </div>
                    <div class="modal-body">
                        <table class="table table-striped">
                            <tr>
                                <td>头像:</td>
                                <td><img id="detailPhoto" src="${userInfo.photo}" class="img-circle" alt="photo"></td>

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
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                        <button class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <%--修改model--%>
        <div id="editUser" class="modal fade" role="dialog" aria-labelledby="editModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="/user/${userInfo.id}/update" method="post" enctype="multipart/form-data"
                          role="form">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3 id="editModalLabel">
                                修改用户信息
                            </h3>
                        </div>
                        <div class="modal-body">
                            <div class="form-group" style="text-align: center;">
                                <label>
                                    <img id="image" src="${userInfo.photo}" class="img-circle"
                                         style="width: 128px;height: 128px;" alt="photo">
                                    <input type="file" name="photo" id="photo" style="width: 128px;display: none">
                                </label>
                            </div>
                            <div class="form-group">
                                <label for="email">邮箱</label>
                                <input type="email" class="form-control" name="email" id="email"
                                       value="${userInfo.email}">
                            </div>
                            <div class="form-group">
                                <label>状态&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <input type="radio" name="status" value="1">有效
                                <input type="radio" name="status" value="0">无效
                            </div>
                            <div class="form-group">
                                <label for="password">密码</label>
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="retryPassword">确认密码</label>
                                <input type="password" class="form-control" name="retryPassword" id="retryPassword"
                                       placeholder="retryPassword">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                            <button type="submit" class="btn btn-primary">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
<!--sha1加密-->
<script type="text/ecmascript" src="/js/sha1.js"></script>
<script>
    $(document).ready(function () {
        // 初始化内容
        var url = "${userInfo.photo}";
        if (url === null || url === "") {
            var img = document.getElementById("image");
            img.src = "/img/default.jpg";
        }

        var status = "${userInfo.status}";
        $("input[name=status][value=" + status + "]").attr("checked", true);

        //实现预览功能
        $("#photo").change(function preview() {
            //获取文件框的第一个文件,因为文件有可能上传多个文件,这里是一个文件
            var file = document.getElementById("photo").files[0];
            //可以进行一下文件类型的判断
            var fileType = file.type.split("/")[0];
            if (fileType !== "image") {
                alert("请上传图片");
                return;
            }
            //图片大小的限制
            var fileSize = Math.round(file.size / 1024 / 1024);
            if (fileSize >= 3) {
                alert("请上传小于少于3M的图片");
                return;
            }
            //获取img对象
            var img = document.getElementById("image");
            //建一条文件流来读取图片
            var reader = new FileReader();
            //根据url将文件添加的流中
            reader.readAsDataURL(file);
            //实现onload接口
            reader.onload = function (e) {
                //获取文件在流中url
                url = reader.result;
                //将url赋值给img的src属性
                console.log(url);
                img.src = url;
            };
        });

        $('button').click(function () {
            var photo = $("#photo").val();
            var email = $("#email").val();
            var password = $("#password").val();
            var retryPassword = $("#retryPassword").val();

            if (photo === "" && email === "" && password === "" && retryPassword === "") {
                alert("请填写需要变更信息！");
                return false;
            }

            if (retryPassword !== password) {
                alert("两次密码不一致！");
                return false;
            }

            if (password !== "") {
                document.getElementById("password").value = hex_sha1(password);
            }
        });
    });
</script>
</body>
</html>