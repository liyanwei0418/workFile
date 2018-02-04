<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>自定义界面预览</title>
        <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    </head>
    <body>
        <div id="pageContent">
        </div>
    </body>
</html>
<script>
    $("#pageContent").html(window.parent.getContent('pageContent'));
</script>