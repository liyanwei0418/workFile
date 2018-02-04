<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>首页</title>
    <link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css" />
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/main.js"></script>
</head>

<body>
    <table>
        <tr>
            <td>ID</td>
            <td>标题</td>
            <td>内容</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${notifyList}" var="notify">
            <tr>
                <td>${notify.id}</td>
                <td>${notify.title}</td>
                <td>${notify.content}</td>
                <td>
                    <a href="${ctx}/notify/edit?id=${notify.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

