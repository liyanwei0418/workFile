<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">


    <!-- jQuery -->
    <script src="${ctxStatic}/js/jquery.js"></script>

    <title>login</title>
</head>
<body>

<div class="loginForm">
    <form method="post" action="">

        <div class="" style="height: 80%;">
            <input class="form-control noAuto" placeholder="请输入帐号" id="username" name="username" type="text">
            <input class="form-control noAuto" placeholder="请输入密码" id="password" name="password" type="password"
                   value="">
<br>
            您是否允许${client.clientName}访问您的个人信息？
            <br>
        <input type="submit" id="loginButton" value="授权并登录"/>
        </div>

    </form>

</div>
<%--
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h2 class="panel-title">xxxLogin</h2>
                </div>
                <div class="panel-body">
                    <form method="post" action="${pageContext.request.contextPath}/doLogin">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入帐号" id="username" name="username" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入密码" id="password" name="password" type="password" value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <div id="login_btn" class="btn btn-lg btn-success btn-block">登录</div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div> --%>
</body>
</html>