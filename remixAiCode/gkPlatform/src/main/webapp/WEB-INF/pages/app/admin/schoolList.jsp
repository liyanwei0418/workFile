<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/z-tree-bootStrap.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/school/css/rootpopup.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/z-treeAll.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
<div id="container">
    <form id="inputForm" method="post">
        <input type="hidden" value="${appId}" name="appId">
        <ul>

            <c:forEach items="${schoolList}" var="school">
                <li>

                    <input class="onPicCheckBox" type="checkbox" name="schools"
                           <c:if test="${schoolAlreadyPushList.contains(school.id)}">checked</c:if>
                           value="${school.id}"/>

                        <%--  <c:choose>
                              <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img src="${app.iconUrl}" data-url="${app.iconUrl}" class="appimg" id="head_url"/></c:when>
                              <c:otherwise><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" class="appimg" id="head_url"/></c:otherwise>
                          </c:choose>--%>

                    <i>${school.name}</i>
                </li>
            </c:forEach>
        </ul>
    </form>
</div>
<script type="text/javascript">

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var spCodesTemp = "";
        $("input[name='schools']:checked").each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).attr("value");
            } else {
                spCodesTemp += ("," + $(this).attr("value"));
            }
        });

        $.post("${ctx}/app/online/save", {
            schools: spCodesTemp,
            appId: $("input[name='appId']").val()
        }, function (retJson) {
                alert(retJson.msg);
                window.parent.location.reload(true);
        });

    }

</script>

</body>
</html>