<%--
  Created by IntelliJ IDEA.
  User: Xone
  Date: 2018/7/17
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Blue | Log in</title>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="../../index.jsp"><b>All Blue</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">扬&nbsp;&nbsp;帆&nbsp;&nbsp;起&nbsp;&nbsp;航</p>

        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="username" placeholder="用户名">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> &nbsp;记住用户名
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="submit" id="login" class="btn btn-primary btn-block btn-flat">登录</button>
            </div>
            <!-- /.col -->
        </div>

        <a href="#">忘记密码</a><br>
        <a href="/jsp/user/register.jsp" class="text-center">注册会员</a>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
<!--sha1加密-->
<script type="text/ecmascript" src="/js/sha1.js"></script>
<script>
    $(function () {
        $('button').click(function () {
            var username = $("#username").val();
            var password = $("#password").val();

            if (username === "") {
                alert("用户名不能为空！");
                return false;
            }
            if (password === "") {
                alert("密码不能为空！");
                return false;
            }
            var url = "/user/login";
            $.post(url,
                {
                    username: username,
                    password: hex_sha1(password)
                },
                function (data) {
                    if (data["result"] === "success") {
                        console.log(data["msg"]);
                        window.location.href = "/index.jsp";
                    } else {
                        alert(data["msg"]);
                    }
                });

        });
    });
</script>
</body>
</html>

