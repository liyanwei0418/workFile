<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <style>

        .container {
            width: 800px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 13px '微软雅黑';
            overflow-x: hidden;
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg span {
            display: inline-block;
            width: 120px;
            text-align: right;
            color: #666;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 28px;
            padding: 0;
            padding-left: 5px;
            border-radius: 2px;
            border: 1px solid #a9a9a9;
            outline: none;
        }

        .stuMsg label {
            margin-right: 61px;
        }

        ul li {
            position: relative;
            margin: 15px 0;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            top: 4px;
            right: 36px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #333;
            width: 197px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }

        .uploading {
            display: inline-block;
            vertical-align: -600%;
            width: 60%;
            text-align: center;
        }

        .uploading p {
            width: 90px;
            height: 86px;
            background: url('${ctxStatic}/image/image.png');
            margin: 0;
            margin-left: 104px;
        }

        .uploading button {
            margin-top: 10px;
            padding: 5px 20px;
            color: #fff;
            background: #54AB37;
            border: 1px solid #54AB37;
            font-weight: bold;
        }

        .parentMsg P {
            FONT-SIZE: 14PX;
            color: #666;
            padding-left: 11px;
        }

        .parentMsg ul {
            overflow: hidden;
            box-sizing: border-box;
        }

        .parentMsg ul li {
            float: left;
            width: 50%;
            margin: 15px 0
        }

        .parentMsg ul li span {

            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .parentMsg input[type=text] {
            height: 28px;
            padding: 0;
            padding-left: 5px;
            width: 190px;
            border: 1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }

        .stuMsgg textarea {
            vertical-align: top;
            width: 590px;
            outline: none;
            resize: none;
            height: 400px;
            padding: 5px;
        }

        i {
            font-style: normal;
            display: inline-block;
            padding: 10px 6px;
            /*margin-right: 10px;*/
        }

        #checkedEquipment {
            width: auto;
            max-width: 525px;
            margin-right: 10px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }

        input, textarea {
            background: #fff !important;
        }

        #content {
            width: 600px;
            display: inline-block;
            vertical-align: top;
        }

        #edui1 {
            width: 600px !important;
        }

        #edui1_iframeholder {
            min-height: 450px !important;
        }
    </style>
</head>
<body>
<form action="${ctx}/classcard/save">
</form>

<div class="container">
    <%--<h3>通知信息</h3>--%>
    <div class="stuMsg">
        <ul class="left">
            <input type="hidden" name="ueditorPics" value="" id="ueditorPics">
            <c:forEach items="${classCardApps}" var="app">
                <li>
                        ${app.appName}
                        ${app.appUrl}
                    <span class="deleteRef" data-appid="${app.id}">delete</span>
                </li>

            </c:forEach>
            <input type="hidden" id="classCardId" value="${classCardId}">
        </ul>
    </div>
    <script>

        $(".deleteRef").on("click", function () {
            var appId = $(this).data('appid');
            var classCardId = $("#classCardId").val();
            $.post("${ctx}/model/deleteClassCardAppRef", {
                appId: appId,
                classCardId: classCardId
            }, function (retJson) {
                if (retJson.code == 0)
                    layer.msg('删除成功');
                else layer.msg("删除失败");
            }, "json");

            setTimeout(function () {
               /* parent.location.reload();*/
                window.parent.closeAll();
                openDialogView('模块分配', '${ctx}/model/classcard-app?id=${classCardId}&option=edit', '900px', '650px', false);
            }, 2000)

        })
    </script>
</body>
</html>