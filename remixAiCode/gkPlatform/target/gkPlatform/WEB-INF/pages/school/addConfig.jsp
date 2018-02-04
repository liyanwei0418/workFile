<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctxStaticNew}/school/css/rootpopup.css"/>
    <title></title>
    <style>
        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
        }
        select {
            width: 66%;
            height: 28px;
            border-radius: 4px;
            font-size: 14px;
            color: #999;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>

    <script>

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

            var paramType = $("input[name='paramType']").val();
            var paramKey = $("input[name='paramKey']").val();
            var paramValue = $("input[name='paramValue']").val();
            if (paramType == '' || paramType == null) {
                layer.msg("类别不能为空");
                return false;
            }

            if (paramKey == '' || paramKey== null) {
                layer.msg("key不能为空");
                return false;
            }

            if (paramValue == '' || paramValue== null) {
                layer.msg("value不能为空");
                return false;
            }

            $("form").submit();
            window.parent.location.reload(true);
        }
    </script>
</head>
<body>
<form action="${ctx}/school/config/save" id="inputForm" method="post">
    <input type="hidden" name="id" value="${config.id}">
    <div class="container">

            <span class="fSpan">配置类别：</span>
            <input type="text" name="paramType" value="${config.paramType}"/>&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b><br/><br/>

            <span class="fSpan">key：</span>
            <input type="text" name="paramKey" value="${config.paramKey}"/>&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b><br/><br/>

            <span class="fSpan">value：</span>
            <input type="text" name="paramValue" value="${config.paramValue}"/>&nbsp;&nbsp;&nbsp;&nbsp;<b>*</b>

    </div>
</form>
</body>
</html>