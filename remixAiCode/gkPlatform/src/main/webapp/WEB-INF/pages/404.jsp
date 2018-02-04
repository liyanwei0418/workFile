<%@ include file="common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>
    <title>提示</title>
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
    <img src="${ctxStaticNew}/images/404.png" alt=""/>
    <div class="noticeDiv" style="padding-top: 30px">
        <div style="font-size: 18px;color: #999999">无法访问本页，可能是URL拼写错误或者所访问的页面不存在</div>
        <div style="font-size: 18px;color: #525252">请重新键入URL地址进行访问</div>
    </div>
</div>
</body>
</html>