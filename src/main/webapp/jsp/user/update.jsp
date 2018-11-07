<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改用户信息</title>
    <%@ include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8"/>
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="box box-primary">
        <div class="register-logo">
            <h3 class="box-title">修&nbsp;改&nbsp;${userInfo.name}&nbsp;信&nbsp;息</h3>
        </div>
        <div class="box-body">
            <form action="/user/${userInfo.id}/update" method="post" enctype="multipart/form-data" role="form">
                <div class="form-group" style="text-align: center;">
                    <label>
                        <img id="image" src="${userInfo.photo}" class="img-circle" style="width: 128px;height: 128px;">
                        <input type="file" name="photo" id="photo" style="width: 128px;display: none">
                    </label>
                </div>
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="email" class="form-control" name="email" id="email" value="${userInfo.email}">
                </div>
                <div class="form-group">
                    <label>状态&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="radio" name="status" value="1">有效
                    <input type="radio" name="status" value="0">无效
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="retryPassword">确认密码</label>
                    <input type="password" class="form-control" name="retryPassword" id="retryPassword"
                           placeholder="retryPassword">
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-block">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
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
        $("input[name=status][value=" + status + "]").attr("checked", true)

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

