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
<link rel="stylesheet" href="${ctxStaticNew}/school/css/rootpopup.css"/>

<!-- jQuery -->
<script src="${ctxStatic}/js/jquery.js"></script>

<!-- tinymce -->
<script src="${ctxStatic}/tinymce/tinymce.min.js"></script>
<title>index</title>
<script>
    tinymce.init({
        selector: 'textarea',
        height: 120,
        language : "zh_CN"
    });

    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        $.post($("form").attr('action'),{
            menuId:"${parent.id}",
            subId:$("input[name='id']").val(),
            name:$("input[name='name']").val(),
            href:$("input[name='href']").val(),
            permission:$("input[name='permission']").val(),
            remarks:tinymce.get('remarks').getContent(),
        },function(retJson){
            if (retJson.code == '0') {
                window.location.replace("${ctx}/menu/index");
            } else {
                alert(retJson.msg);
            }
        });
        return true;
    }
</script>
</head>
<style>
    .fSpan{
        width: 14% !important;
    }
    #mceu_13{
        width: 80% !important;
        display: inline-block !important;
    }
    p{
        padding: 30px 0 10px 30px;
    }
</style>
<body>

<p>为&nbsp;&nbsp; <a href="#">${parent.name}</a> &nbsp;&nbsp;设置子菜单</p>
<form method="post" action="${ctx}/menu/saveSub">
    <input type="hidden" name="id" value="${sub.id}">
    <span class="fSpan">名称：</span>
    <input type="text" name="name" value="${sub.name}"> <br/><br/>
    <span class="fSpan">路径：</span>
    <input type="text" name="href" value="${sub.href}"> <br/><br/>
    <span class="fSpan">权限标识：</span>
    <input type="text" name="permission" value="${sub.permission}"> <br/><br/>

    <span class="fSpan">内容：</span>
    <textarea name="remarks" rows="3">${sub.remarks}</textarea> <br/><br/>
</form>

</body>
</html>
