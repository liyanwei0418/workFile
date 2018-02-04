<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>安全设置</title>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <style>
        .container{width:300px;}
        .row{margin:20px 0;font-size:14px;overflow:hidden;}
        .row span{display:inline-block;width:80px;text-align:right;color:#333;margin-right:10px;}
        .row input[type=text]{height:23px;}
        .row>label{color:#666;text-align:right;font-size:12px;display:inline-block;width:271px;margin-top:10px;}
        .row>button{margin-right:30px;float:right;border:1px solid #aaa;background:#fff;width:80px;height:28px;border-radius:6px;}
    </style>
    <script>

        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

            var oriPwd = $("input[name='oriPwd']").val();
            var newPwd = $("input[name='newPwd']").val();
            var rePwd = $("input[name='rePwd']").val();

            if (oriPwd == '' || name.trim().length == 0) {
                alert("原密码不能为空！");
                return false;
            }

            if (newPwd == '' || newPwd.trim().length == 0) {
                alert("密码不能为空！");
                return false;
            }

            if (newPwd != rePwd) {
                alert("两次密码不相等！");
                return false;
            }
            $.post($("form").attr('action'),{
                oriPwd:oriPwd,
                newPwd:newPwd,
                rePwd:rePwd,
            },function(retJson){
                if (retJson.code == '0') {
                    alert("密码修改成功！");
                    window.location.href="${ctx}/doLogout";
                } else {
                    alert(retJson.msg);
                }
            });
            return true;
        }
    </script>
</head>
<body>
<form action="${ctx}/user/updatePwd">
    <div class="container">
        <div class="row">
            <span>原密码</span>
            <input type="password" name="oriPwd"/>
        </div>
        <div class="row">
            <span>新密码</span>
            <input type="password" name="newPwd"/>
            <label>5-12个字符，只能使用字母和数字</label>
        </div>
        <div class="row">
            <span>确认密码</span>
            <input type="password" name="rePwd"/>
        </div>
    </div>
</form>
</body>
</html>