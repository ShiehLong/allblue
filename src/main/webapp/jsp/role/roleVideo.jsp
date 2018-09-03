<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/3
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>视频</title> 
</head>
<body>


<div style="text-align:center">
    <button onclick="playPause()">播放/暂停</button>
    <button onclick="makeBig()">放大</button>
    <button onclick="makeSmall()">缩小</button>
    <button onclick="makeNormal()">普通</button>
    <br>
    <video id="video1" width="800" height="600" controls>
        <source src="/img/1.mp4" type="video/mp4">
    </video>
</div>

<script>
    var myVideo=document.getElementById("video1");

    function playPause()
    {
        if (myVideo.paused)
            myVideo.play();
        else
            myVideo.pause();
    }

    function makeBig()
    {
        myVideo.width=560;
    }

    function makeSmall()
    {
        myVideo.width=320;
    }

    function makeNormal()
    {
        myVideo.width=420;
    }
</script>
</body>
</html>
