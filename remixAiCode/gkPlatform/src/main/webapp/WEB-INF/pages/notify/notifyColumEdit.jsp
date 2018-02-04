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
    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="">
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
            return true;
        }
    </script>

</head>
<body><!-- onclick=" onclick="openDialog('11','addUser.html','500px','400px');""  -->
<div class="container" >
    <form action="${ctx}/notify/col/save" id="inputForm" method="post">
              <input type="hidden" name="id" value="${notifyColumn.id}">
        栏目名称： <input name="columName" type="text" value="${notifyColumn.name}"/>
    </form>
</div>
</body>
</html>