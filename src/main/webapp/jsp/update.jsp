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
    <title>修改用户信息</title>
    <%@ include file="common/head.jsp" %>
</head>
<body class="hold-transition register-page">

<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">All Blue</h3>
    </div>
    <div class="box-body">
        <p class="login-box-msg">修&nbsp;改&nbsp;${userInfo.username}&nbsp;信&nbsp;息</p>

        <div class="form-group">
            <label for="email">邮箱</label>
            <input type="email" class="form-control" id="email" value="${userInfo.email}">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="retryPassword">确认密码</label>
            <input type="password" class="form-control" id="retryPassword" placeholder="retryPassword">
        </div>
        <div class="form-group">
            <label for="inputFile">头像</label>
            <input type="file" id="inputFile">
        </div>
    </div>

    <div class="box-footer">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</div>
<!-- jQuery 3 -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../js/bootstrap.min.js"></script>
<!--sha1加密-->
<script type="text/ecmascript" src="../js/sha1.js"></script>
<script>
    $(document).ready(function () {
        $('button').click(function () {
            var email = $("#email").val();
            var password = $("#password").val();
            var retryPassword = $("#retryPassword").val();

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
            var url = "/user/${userInfo.id}/update";
            $.post(url,
                {
                    email: email,
                    password: hex_sha1(password),
                },
                function (data) {
                    if (data["result"] === "success") {
                        alert(data["msg"]);
                        window.location.href = "/user/list";
                    } else if (data["result"] === "fail") {
                        alert(data["msg"]);
                    }
                });
        });
    });
</script>
</body>
</html>

