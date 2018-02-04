<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script>
       function doSubmit(){
           var roleIds = "";
           $(":checkbox").each(function () {
               if(this.checked){
                   roleIds+=$(this).attr("id")+",";
               }
           })

           $.ajax({
               type: "post",
               url: "${ctx}/school/app/role/add",
               data: {
                   appId: "${appId}",
                   roleIds: roleIds
               },
               dataType: "json",
               success: function (res) {
                   window.parent.location.reload(true);
               },
               error: function (e) {
                   alert(e);
               }
           });
       }
    </script>
</head>
<body>

    <c:forEach items="${roleList}" var="role">
        <input type="checkbox" id="${role.id}" <c:if test="${appRoleIdList.contains(role.id)}">checked</c:if> > ${role.name} <br> <br>
    </c:forEach>
</body>
</html>

