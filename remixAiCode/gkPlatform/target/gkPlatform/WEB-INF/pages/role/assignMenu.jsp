<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="login">
<meta name="author" content="lexi">

<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="${ctxStatic}/css/bootstrap.min.css"/>

<!-- jQuery -->
<script src="${ctxStatic}/js/jquery.js"></script>

<title>index</title>
<script>
    $(function() {
        $("#submit-btn").click(function(event){

            var addIds ='';
            var delIds ='';
            $('input[name="menuId"]').each(function() {

                if($(this).is(':checked')) {
                    addIds += $(this).val() + ",";
                } else {
                    delIds += $(this).attr("menuIdVal") + ",";
                }
            });

            if (addIds.length > 0) {
                addIds = addIds.substr(0, addIds.length - 1);
            }
            if (delIds.length > 0) {
                delIds = delIds.substr(0, delIds.length - 1);
            }
            $.post($("form").attr('action'),{
                addIds:addIds,
                delIds:delIds,
                roleId:${role.id}
            },function(retJson){
                if (retJson.code == '0') {
                    alert("分配成功");
                    window.location.replace("${ctx}/role/index");
                } else {
                    alert(retJson.msg);
                }
            });

        });

    });

</script>
</head>
<body>

<form method="post" action="${ctx}/role/doAssign">
    <input type="hidden" name="roleId" value="${role.id}">
    角色：${role.name} 的可操作权限<br>
    <c:forEach items="${menuList}" var="menu">
        ${menu.name} :
        <c:choose>
            <c:when test="${gukeer:roleMenuContains(role.id, menu.id, roleMenuList)}">
                <input type="checkbox" name="menuId" checked menuIdVal="${menu.id}" value="${menu.id}">
            </c:when>
            <c:otherwise>
                <input type="checkbox" name="menuId" menuIdVal="${menu.id}" value="${menu.id}">
            </c:otherwise>
        </c:choose>
        <br/>
    </c:forEach>

    <input id="submit-btn" type="button" value="提交">
</form>

</body>
</html>
