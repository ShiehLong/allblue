<%--
  Created by IntelliJ IDEA.
  User: Xone
  Date: 2018/7/17
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Blue | Registration Page</title>
    <%@ include file="../common/head.jsp" %>
</head>
<body class="register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="../../index.jsp"><b>All Blue</b></a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">注&nbsp;&nbsp;册&nbsp;&nbsp;会&nbsp;&nbsp;员</p>

        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="name" placeholder="用户名">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="email" class="form-control" id="email" placeholder="邮箱">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" class="form-control" id="retryPassword" placeholder="确认密码">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">&nbsp;同意 <a href="#">条款</a>
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button id="register" type="submit" class="btn btn-primary btn-block btn-flat">注&nbsp;&nbsp;册</button>
            </div>
            <!-- /.col -->
        </div>

        <a href="login.jsp" class="text-center">已有账户</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
<!--sha1加密-->
<script type="text/ecmascript" src="/js/sha1.js"></script>
<script>
    $(document).ready(function () {
        $('button').click(function () {
            var name = $("#name").val();
            var email = $("#email").val();
            var password = $("#password").val();
            var retryPassword = $("#retryPassword").val();

            if (name === "") {
                alert("用户名不能为空！");
                return false;
            }
            if (email === "") {
                alert("邮箱不能为空！");
                return false;
            }
            if (password === "") {
                alert("密码不能为空！");
                return false;
            }
            if (retryPassword === "") {
                alert("确认密码不能为空！");
                return false;
            }
            if (retryPassword !== password) {
                alert("两次密码不一致！");
                return false;
            }
            var url = "/user/register";
            $.post(url,
                {
                    name: name,
                    email: email,
                    password: hex_sha1(password),
                },
                function (data) {
                    if (data["result"] === "success") {
                        alert(data["msg"]);
                        window.location.href = "/jsp/user/login.jsp";
                    } else if (data["result"] === "fail") {
                        alert(data["msg"]);
                    }
                });
        });
    });
</script>
</body>
</html>

