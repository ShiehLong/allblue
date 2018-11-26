<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>菜单树</title>
    <link rel="stylesheet" type="text/css" href="/css/metroStyle/metroStyle.css">
</head>
<body>
<%@ include file="/jsp/common/header.jsp" %>
<div class="container-fluid">
    <hr style="margin:0;background-color:#2092d6;height:2px;">

    <div id="tree-container" class="row context-row" style="margin: 15px 0px; overflow-y:scroll">
        <div class=" col-md-12">
            <ul id="regionZTree" class="ztree"></ul>
        </div>
    </div>

    <hr style="margin:0;background-color:#2092d6;height:2px;">
</div>
<script type="text/javascript" src="/js/jquery.ztree.all.min.js"></script>
<script src="/js/system/list.js"></script>
</body>
</html>