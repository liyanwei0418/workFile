<%@ include file="../common/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="${ctxStaticNew}/school/css/rootpopup.css"/>
    <title></title>

    <style>
        .fSpan{
            width: 15% !important;
        }
        #mceu_13{
            width: 80%;
            display: inline-block;
        }
        #mceu_27{
            min-height: 255px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <script src="${ctxStatic}/tinymce/tinymce.min.js"></script>
    <script type="">

        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $.post($("form").attr('action'),{
                id:$("input[name='id']").val(),
                name:$("input[name='name']").val(),
                identify:$("input[name='identify']").val(),
                remarks:tinymce.get('remarks').getContent(),
            },function(retJson){
                if (retJson.code == '0') {
                    window.location.reload();
                } else {
                    alert(retJson.msg);
                }
            });
            return true;
        }
        tinymce.init({
            selector: 'textarea',
            height: 120,
            language : "zh_CN"
        });
    </script>

</head><%--<c:if test="${gukeer:notEmptyString(role.name)}">disabled="disabled"</c:if>--%>
<body><!-- onclick=" onclick="openDialog('11','addUser.html','500px','400px');""  -->
<div class="container" >

    <form method="post" id="inputForm" action="${ctx}/school/role/save">
        <input type="hidden" name="id" value="${role.id}">
        <span class="fSpan">角色名称：</span><input type="text" name="name" value="${role.name}"> <br/><br/>

        <span class="fSpan">角色标识：</span><input type="text" name="identify" value="${role.roleIdentify}">

        <br/><br/>

        <span class="fSpan">内容：</span><textarea name="remarks" rows="3">${role.remarks}</textarea> <br/>
    </form>
</div>
</body>
</html>