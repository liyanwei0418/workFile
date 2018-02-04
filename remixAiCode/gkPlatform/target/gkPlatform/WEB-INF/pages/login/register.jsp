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

<script>
$(function() {
    $("#register_btn").click(function(event){
        $.post($('form').attr('action'),{
            account:$('#account').val(),
            password:$('#password').val()
        },function(retJson){
            if (retJson.code == '0') {
                window.location.replace("${pageContext.request.contextPath}/login");
            } else {
                alert(retJson.msg);
            }
        });
    });
});
</script>
<title>register</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h2 class="panel-title">xxxRegister</h2>
                </div>
                <div class="panel-body">
                    <form method="post" action="${pageContext.request.contextPath}/doRegister">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入帐号" id="account" name="account" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入密码" id="password" name="password" type="password" value="">
                            </div>
                            <%--<div class="checkbox">--%>
                                <%--<label>--%>
                                    <%--<input name="remember" type="checkbox" value="Remember Me">Remember Me--%>
                                <%--</label>--%>
                            <%--</div>--%>
                            <!-- Change this to a button or input when using this as a form -->
                            <div id="register_btn" class="btn btn-lg btn-success btn-block">注册</div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>