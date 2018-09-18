<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/jsp/common/head.jsp" %>
</head>
<body>
<div>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header" style="margin-left: 30px;">
                <a class="navbar-brand" href="/index.jsp">
                    <span><b>All</b>Blue</span>
                </a>
            </div>
            <div>
                <ul id="loginTab" class="nav navbar-nav navbar-right" style="margin-right: 30px;display: none">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            数据中心
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/list">用户列表</a></li>
                            <li class="divider"></li>
                            <li><a href="/role/list">角色列表</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span>${blueUser.username}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li style="text-align: center">
                                <img src="${blueUser.photo}" class="img-circle" alt="User Image"
                                     style="width: 140px;height: 140px">
                                <p>
                                    ${blueUser.username}
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li>
                                <div class="pull-left" style="padding-left: 15px">
                                    <a href="/user/${blueUser.id}/detail" class="btn btn-default">详情</a>
                                </div>
                                <div class="pull-right" style="padding-right: 15px">
                                    <a href="/user/logout" class="btn btn-default"> 退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul id="logoutTab" class="nav navbar-nav navbar-right" style="display: block">
                    <li><a href="/jsp/user/login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<!-- jQuery 3 -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/js/bootstrap.min.js"></script>
<script>
    $(function (){
        var userId = "${blueUser.id}";
        if (userId != null & userId != "") {
            document.getElementById('logoutTab').style.display = 'none';
            document.getElementById('loginTab').style.display = 'block';
        } else {
            document.getElementById('logoutTab').style.display = 'block';
            document.getElementById('loginTab').style.display = 'none';
        }
    });

</script>
</body>
</html>
