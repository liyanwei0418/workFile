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
    $(function() {
        $("#submit-btn").click(function(event){
            $.post($("form").attr('action'),{
                id:$("input[name='id']").val(),
                name:$("input[name='name']").val(),
            },function(retJson){
                if (retJson.code == '0') {
                    window.location.replace("${ctx}/school/index");
                } else {
                    alert(retJson.msg);
                }
            });
        });
    });
</script>
</head>
<body>

<form method="post" action="${ctx}/school/save">
    <input type="hidden" name="id" value="${school.id}">
    <input type="text" name="name" value="${school.name}">
    <input id="submit-btn" type="button" value="修改">
</form>

</body>
</html>