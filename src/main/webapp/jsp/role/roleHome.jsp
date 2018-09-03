<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/2
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <%@ include file="../common/head.jsp" %>
    <style type="text/css">
        .htmleaf-container{
            margin: 0 auto;
            text-align: center;
            overflow: hidden;
        }

        .htmleaf-header{
            padding: 1em 190px 1em;
            letter-spacing: -1px;
            text-align: center;
            background: #66677c;
        }

        .htmleaf-header h1 {
            color: #D5D6E2;
            font-weight: 600;
            font-size: 2em;
            line-height: 1;
            margin-bottom: 0;
            font-family: "Microsoft YaHei","宋体","Segoe UI", "Lucida Grande", Helvetica, Arial,sans-serif, FreeSans, Arimo;
        }

        #gallery-wrapper {
            position: relative;
            max-width: 75%;
            width: 75%;
            margin: 50px auto;
        }

        img.gallerybox {
            width: 100%;
            max-width: 100%;
            height: auto;
        }

        .white-panel {
            position: absolute;
            background: white;
            border-radius: 5px;
            box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
            padding: 10px;
        }

        .white-panel h1 {
            font-size: 1em;
        }

        .white-panel h1 a {
            color: #A92733;
        }

        .white-panel:hover {
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
            margin-top: -5px;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }
    </style>
</head>
<body>
<article class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>图片列表</h1>
    </header>
    <section id="gallery-wrapper" class="wrapper">
        <c:if test="${list.size() le 0 }">
            <tr>
                <td colspan="8">目前还没有角色数据</td>
            </tr>
        </c:if>
        <c:if test="${list.size() gt 0}">
            <c:forEach items="${list}" var="role">
                <article class="white-panel">
                    <img class="thumbnail gallerybox"
                         src="${role.pic }"
                         onclick=javascript:location.href="/role/${role.id}/detail">
                </article>
            </c:forEach>
        </c:if>

    </section>
</article>

<script src="/js/jquery.min.js"></script>
<script src="/js/role/pinterest_grid.js"></script>
<script type="text/javascript">
    //瀑布流插件
    $(function () {
        $("#gallery-wrapper").pinterest_grid({
            no_columns: 4,
            padding_x: 10,
            padding_y: 10,
            margin_bottom: 50,
            single_column_breakpoint: 700
        });

    });
</script>
</body>
</html>
