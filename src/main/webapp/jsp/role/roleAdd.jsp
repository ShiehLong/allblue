<%--
  Created by IntelliJ IDEA.
  User: Xone
  Date: 2018/9/1
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增角色</title>
    <%@ include file="../common/head.jsp" %>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8"/>
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="box box-primary">
        <div class="register-logo">
            <h3 class="box-title">新&nbsp;增&nbsp;角&nbsp;色</h3>
        </div>
        <div class="box-body">
            <form action="/role/add" method="post" enctype="multipart/form-data" role="form">
                <div class="form-group" style="text-align: center;">
                    <label>
                        <img id="image" src="/img/user1-128x128.jpg" class="img-circle"
                             style="width: 128px;height: 128px;">
                        <input type="file" name="pic" id="pic" style="width: 128px;display: none">
                    </label>
                </div>
                <div class="form-group">
                    <label for="name">名称</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请填写名称">
                </div>
                <div class="form-group">
                    <label for="sex">性别</label>
                    <input type="text" class="form-control" name="sex" id="sex" placeholder="请填写性别">
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="text" class="form-control" name="age" id="age" placeholder="请填写年龄">
                </div>
                <div class="form-group">
                    <label for="description">描述</label>
                    <input type="text" class="form-control" name="description" id="description" placeholder="请填写描述">
                </div>
                <div class="form-group">
                    <label for="video">视频</label>
                    <input type="text" class="form-control" name="video" id="video" placeholder="请填写视频">
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
<script>
    $(document).ready(function () {

        //实现预览功能
        $("#pic").change(function preview() {
            //获取文件框的第一个文件,因为文件有可能上传多个文件,咱这里是一个文件
            var file = document.getElementById("pic").files[0];
            //可以进行一下文件类型的判断
            var fileType = file.type.split("/")[0];
            if (fileType != "image") {
                alert("请上传图片")
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
                img.src = url;
            };
        });

        $('button').click(function () {
            var pic = document.getElementById("pic").files[0];
            var name = $("#name").val();
            var sex = $("#sex").val();
            var age = $("#age").val();
            var description = $("#description").val();
            var video = $("#video").val();

            if(pic === ""){
                alert("图片不能为空！");
                return false;
            }
            if(name === ""){
                alert("名称不能为空！");
                return false;
            }
            if(sex === ""){
                alert("性别不能为空！");
                return false;
            }
            if(age === ""){
                alert("年龄不能为空！");
                return false;
            }
            if(description === ""){
                alert("描述不能为空！");
                return false;
            }
            if(video === ""){
                alert("视频不能为空！");
                return false;
            }
        });
    });
</script>
</body>
</html>
