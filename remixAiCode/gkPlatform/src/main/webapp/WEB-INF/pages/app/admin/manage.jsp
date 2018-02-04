<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用管理</title>
    <link href="${ctxStatic}/css/pageDivide.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <script>
        $(function () {
            $(".fenY").createPage({
                pageCount:${pageInfo.pages},//总页数
                current:${pageInfo.pageNum},//当前页面
                backFn: function (p) {
                    window.location.href = "${ctx}/manage?pageNum=" + p + "&pageSize=20";
                }
            });
        });
    </script>
</head>
<body>
<table>
    <tr>
        <td>角色名称</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${pageInfo.list}" var="school">
        <tr>
            <td>${school.name}</td>
            <td>
                <button onclick="openDialog('添加应用','${ctx}/app/manage/add?id=${school.id}','800px','500px')">添加应用</button>
                <button onclick="openDialog('删除应用','${ctx}/app/manage/delete?id=${school.id}','800px','500px')">删除应用</button>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="fenY" id="fenY"></div>
</body>
</html>

