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
<!-- Bootstrap Core JavaScript -->
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<title>index</title>
<script>
    function del(id){
        if (confirm("确定删除吗?")) {
            $.post('${ctx}/school/delete/' + id,{
                token:'a2938jg0jga80gaq0q0803==asflkaj3pkgnh472jskja',
            },function(retJson){
                if (retJson.code == '0') {
                    alert('删除成功');
                    window.location.replace("${ctx}/school/index");
                } else {
                    alert(retJson.msg);
                }
            });
        }
    }
</script>
</head>
<body>

<table>
    <tr>
        <td>机构名称</td>
        <td>操作 </td>
    </tr>
    <c:forEach items="${schoolList}" var="school">
        <tr>
            <td>${school.name}</td>
            <td>
                <a href="${ctx}/school/edit?id=${school.id}">编辑</a> |
                <a href="#" onclick="del('${school.id}')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>