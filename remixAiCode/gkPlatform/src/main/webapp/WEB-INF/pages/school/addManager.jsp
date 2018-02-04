<%@ include file="../common/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>

    <style>
        body{
            font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif

        }
        div.container{

            font-size:14px;
            color:#101010;
            width:300px;
            margin:0 auto;
            margin-top:30px;
        }
        input{
            margin-left:20px;
            height:25px;
            width:182px;
            padding:0 8px;
        }
        form{
            width: 400px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <script type="">
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $.post($("form").attr('action'),{
                schoolId:$("input[name='schoolId']").val(),
                manAccount:$("input[name='manAccount']").val(),
            },function(retJson){

            });
            return true;
        }
    </script>

</head>
<body><!-- onclick=" onclick="openDialog('11','addUser.html','500px','400px');""  -->
<div class="container" >
    <form id="inputForm" method="post" action="${ctx}/school/manage/save">
        <input type="hidden" name="schoolId" value="${schoolId}">
        分配管理员：<input type="text" name="manAccount" >
    </form>

</div>
</body>
</html>