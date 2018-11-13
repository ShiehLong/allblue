<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/css/allblue.css">

<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- Google Font -->
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<script>
    $(document).ready(function () {
        var photo = "${blueUser.photo}";
        if (photo === null || photo === "") {
            document.getElementById('topPhoto').src = "/img/default.jpg";
        }
    });
</script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 30px;">
            <a class="navbar-brand" href="/index.jsp">
                <span><b>All</b>Blue</span>
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 30px;">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        数据中心
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/list">用户列表</a></li>
                        <li class="divider"></li>
                        <li><a href="/role/list">角色列表</a></li>
                        <li><a href="/jsp/test.jsp">t1</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span>${blueUser.name}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li style="text-align: center">
                            <img src="${blueUser.photo}" id="topPhoto" class="img-circle" alt="User Image"
                                 style="width: 140px;height: 140px">
                            <p>
                                ${blueUser.name}
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li>
                            <div class="pull-right" style="padding-right: 15px">
                                <a href="/user/logout" class="btn btn-default"> 退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>