<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ include file="common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>提示</title>
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>
    <style>
        body>div{margin-top:12%;text-align:center;}
        .noticeDiv >div{
            padding-top: 16px;
        }
        #backIndex:hover{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div>
    <img src="${ctxStaticNew}/images/500.png" alt=""/>
    <div class="noticeDiv" style="padding-top: 30px">
        <div style="font-size: 18px;color: #999999">系统访问异常</div>
        <div style="font-size: 18px;color: #525252">请联系管理员</div>
        <div style="font-size: 14px;color: #54AB37" id="backIndex" onclick="window.location.href='${ctx}/login'">返回登录页</div>
        <%--异常信息${error_handler_result_msg}--%>
    </div>
</div>
</body>
</html>