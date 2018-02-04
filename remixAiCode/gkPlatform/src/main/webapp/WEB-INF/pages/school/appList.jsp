<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/z-tree-bootStrap.css" />
    <link rel="stylesheet" href="${ctxStaticNew}/school/css/rootpopup.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/z-treeAll.js"></script>
    <style type="text/css">
        input[type="checkbox"] {
            -webkit-appearance: checkbox;
            background: none;
            /*background-position: -96px 0 !important;*/
            height: 15px !important;
            vertical-align: middle;
            width: 15px !important;
            position: absolute;
            right: 25px;
            bottom: 32px;
        }
        .appimg{
            display: inline-block;
            width: 60px !important;
            height: 60px !important;
        }
        i{
            font-style: normal;
            display: block;
            text-align: center;
            margin-top: 10px;

        }
        ul{
            margin: 0 auto !important;
            float: none !important;
            list-style: none;
            overflow: hidden;
        }
        li{
            margin: 0 !important;
            padding: 10px 30px;
            float: left;
            position: relative;
        }
        li:last-child{
            border-bottom: 0;
        }
    </style>
</head>
<body>
<div id="container">
    <form action="${ctx}/school/app/authorization/save" id="inputForm" method="post">
        <input type="hidden" value="${appStatus}" name="appStatus"><%--表示应用是推送还是上线--%>
        <input type="hidden" value="${schoolId}" name="schoolId">
        <ul>

        <c:forEach items="${appList}" var="app">
            <li>
                <c:if test="${app.isDefault!=0}">
                    <input class="onPicCheckBox" type="checkbox" name="apps" <c:if test="${appIds.contains(app.id.toString())}">checked</c:if> value="${app.id}"/>
                </c:if>
                <c:if test="${app.sfczxmz==0}">
                    <c:choose>
                        <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img src="${app.iconUrl}" data-url="${app.iconUrl}" class="appimg" id="head_url"/></c:when>
                        <c:otherwise><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" data-url="${app.iconUrl}" class="appimg" id="head_url"/></c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${app.iconUrl}" data-url="${app.iconUrl}" class="appimg" id="head_url"/></c:if>
                <i>${app.name}</i>
            </li>
        </c:forEach>
        </ul>
    </form>
</div>
<script type="text/javascript">

    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var spCodesTemp = "";
        $("input[name='apps']:checked").each(function(i){
            if(0==i) {
                spCodesTemp = $(this).attr("value");
            } else{
                spCodesTemp += (","+$(this).attr("value"));
            }
        });

        $.post("${ctx}/school/app/authorization/save",{

            apps:spCodesTemp,

            schoolId:$("input[name='schoolId']").val(),

            appStatus:$("input[name='appStatus']").val(),

        },function(retJson){
            alert(retJson);
        },"json");
        return true;

    }

</script>

</body>
</html>