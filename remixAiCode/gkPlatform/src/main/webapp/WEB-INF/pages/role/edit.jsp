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

<!-- tinymce -->
<script src="${ctxStatic}/tinymce/tinymce.min.js"></script>
<title>index</title>
<script>
    $(function() {
        $("#submit-btn").click(function(event){
            $.post($("form").attr('action'),{
                id:$("input[name='id']").val(),
                name:$("input[name='name']").val(),
                remarks:tinymce.get('remarks').getContent(),
            },function(retJson){
                if (retJson.code == '0') {
                    window.location.replace("${ctx}/role/index");
                } else {
                    alert(retJson.msg);
                }
            });
        });
    });
    tinymce.init({
        selector: 'textarea',
        height: 120,
        language : "zh_CN"
    });
</script>
</head>
<body>

<form method="post" action="${ctx}/role/save">
    <input type="hidden" name="id" value="${role.id}">
    名称：<input type="text" name="name" value="${role.name}"> <br/>

    内容：<textarea name="remarks" rows="3">${role.remarks}</textarea> <br/>

    <input id="submit-btn" type="button" value="提交">
</form>

</body>
</html>
