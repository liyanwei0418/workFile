<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title></title>
<style>
        body{
            font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif
        }
        div.container{
            width:400px;
            padding-left:10px;
            margin:0 auto;
            line-height:20px;
            text-align:center;
            font-size:14px;
            color:#101010;
        }
        input{
            margin:15px 0;
            margin-left:25px;
            padding:0 5px;
            width:190px;
            height:23px;
            border:1px solid #a9a9a9;
            border-radius:3px;
        }
        input.columnName{
            margin-top:30px;
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
<body><!-- onclick="openDialog('11','afterNewColumn.html','500px','400px');"  -->
<div class="container">
    <form action="${ctx}/notify/col/save" id="inputForm" method="post">
    栏目名称 <input type="text" class="columnName" name="columName"/><br/>
   <%-- 栏目顺序 <input type="text"/><br/>--%>
	<%--<p style="text-align:left;padding-left:51px">信息管理员<a style="padding-left:20px;color:#1ab394" onclick="openDialog('选择分组','${ctx}/notify/choosefz','400px','300px');">添加</a></p>--%>
    </form>
</div>
</body>
</html>