<%@ include file="common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>提示</title>
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>
    <style>
        body > div {
            margin-top: 12%;
            text-align: center;
        }

        .noticeDiv > div {
            padding-top: 10px;
        }

        #backIndex:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div>
    <img src="${ctxStaticNew}/images/500.png" alt=""/>
    <div class="noticeDiv" style="padding-top: 30px">
        <div style="font-size: 19px;color: #999999">系统访问异常，您可能没有权限</div>
        <div style="font-size: 14px;color: #525252">请联系管理员</div>
        <div style="font-size: 14px;color: #525252" id="backIndex" onclick="window.location.href='${ctx}/home/index'">
            返回首页
        </div>
    </div>
</div>
</body>
</html>
<%--
<body>

<h1>error:</h1> <br/>
<h3>
    您没有操作权限，请联系管理员！！
    ${sessionScope.error_handler_result_msg}
</h3>

</body>
</html>--%>
