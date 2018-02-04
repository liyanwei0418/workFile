<%@ page import="cn.gukeer.platform.service.SchoolService" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <!--[if lt IE 9]>
    <script src="${ctxStaticNew}/js/html5shiv.min.js"></script>
    <script src="${ctxStaticNew}/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${ctxStaticNew}/css/login.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/normalize.min.css"/>
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>

    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>

    <%--<script src="${ctxStatic}/js/ajaxfileupload.js"></script>--%>
    <title>教育云平台</title>
    <%--<script src="${ctxStaticNew}/js/particals.js"></script>--%>
    <script>

        var remember = 0;
        $(function () {

            $("#remember").click(function () {
                if (document.getElementById("remember").checked) {
                    remember = 1;
                } else {
                    remember = 0;
                }
            });

            $("#login_btn").click(function (event) {
                login();
            });

            $("body").keydown(function () {
                if (event.keyCode == "13") {//keyCode=13是回车键
                    login();
                }
            });
            <c:if test="${gukeer:notEmptyString(bgPicture)}">
            $("body").attr("style", "background:url(${ctx}/file/pic/show?picPath=${bgPicture}) 100% 0%/cover no-repeat !important");
            </c:if>
        });
        function login() {

            $.ajax({
                type: "post",
                url: "${ctx}/doLogin",
                data: {
                    username: $('#username').val(),
                    password: $('#password').val(),
                    shortFlag: '@${shortFlag}',
                    remember: remember
                },
                dataType: "json",
                success: function (data) {
                    if (data.code == '0') {
                        window.location.replace("${pageContext.request.contextPath}/" + data.data);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (e) {
                    //alert(JSON.stringify(e));
                }
            });
        }

        //        调用背景执行动画
        //        window.onload = function () {
        //            init();
        //            setInterval(function () {
        //                for (var i = 0; i < POINT; i++) {
        //                    var cir = circleArr[i];
        //                    cir.x += cir.moveX;
        //                    cir.y += cir.moveY;
        //                    if (cir.x > WIDTH) cir.x = 0;
        //                    else if (cir.x < 0) cir.x = WIDTH;
        //                    if (cir.y > HEIGHT) cir.y = 0;
        //                    else if (cir.y < 0) cir.y = HEIGHT;
        //
        //                }
        //                draw();
        //            }, 16);
        //        }


    </script>
</head>
<style>
    #particals {
        position: absolute;
        width: 100%;
        height: 100%;
        left: 0;
        top: 0;
        z-index: -22;
    }
</style>
<body>
<div class="particals-box">
    <main style="display: inline-block;vertical-align: middle">
        <p style="width:400px;margin: 0 auto;margin-bottom:20px;margin-top:-54px;padding-left: 64px;">
            <c:choose>
                <c:when test="${gukeer:notEmptyString(logo)}">
                    <img src="${ctx}/file/pic/show?picPath=${logo}" style="height: 45px; vertical-align: middle"
                         alt=""/>
                </c:when>
                <c:otherwise>
                    <img src="${ctxStaticNew}/images/logo-logo.png" alt=""/>
                </c:otherwise>
            </c:choose>
        </p>
        <div class="login-box" style="margin-top:50px">
            <div class="user">
                <p style="text-align: left;">用户名</p>
                <input id="username" type="text"/>
            </div>
            <div class="pwd">
                <p style="text-align: left;">密码</p>
                <input id="password" type="password"/>
            </div>
            <div class="operation">
                <p>
                    <input type="checkbox" id="remember"/>
                    <label for="remember">记住我</label>
                </p>
                <!--<div>-->
                <!--<a href="#">忘记密码？</a>-->
                <!--</div>-->
            </div>
            <div class="login-btn">
                <button id="login_btn">登录</button>
            </div>
        </div>
    </main>
    <c:if test="${gukeer:emptyString(bgPicture)}">
        <div id="particals" style="overflow:hidden">
            <canvas id="Mycanvas"></canvas>
        </div>
    </c:if>
</div>

<footer>


    <%--<c:if test="${gukeer:notEmptyString(bottomText)}">
        ${bottomText}
    </c:if>
    <c:if test="${gukeer:emptyString(bottomText)}">
        <p>©2014-2016 Beijing Search Champion Technology Co.,Ltd. 北京教育云开放平台科技有限公司</p>
        <p>京ICP备16000182-3号|京公安网备11010502027075</p>
    </c:if>--%>
</footer>
</body>
<script>
    /**
     * Created by admin on 2016/11/28.
     */

//定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;

    var canvas = document.getElementById('Mycanvas');
    canvas.width = WIDTH;
    canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(0,0,0,0.02)',
            context.strokeWidth = 1,
            context.fillStyle = 'rgba(0,0,0,0.05)';
    var circleArr = [];

    //线条：开始xy坐标，结束xy坐标，线条透明度
    function Line(x, y, _x, _y, o) {
        this.beginX = x,
                this.beginY = y,
                this.closeX = _x,
                this.closeY = _y,
                this.o = o;
    }
    //点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle(x, y, r, moveX, moveY) {
        this.x = x,
                this.y = y,
                this.r = r,
                this.moveX = moveX,
                this.moveY = moveY;
    }
    //生成max和min之间的随机数
    function num(max, _min) {
        var min = arguments[1] || 0;
        return Math.floor(Math.random() * (max - min + 1) + min);
    }
    // 绘制原点
    function drawCricle(cxt, x, y, r, moveX, moveY) {
        var circle = new Circle(x, y, r, moveX, moveY)
        cxt.beginPath()
        cxt.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI)
        cxt.closePath()
        cxt.fill();
        return circle;
    }
    //绘制线条
    function drawLine(cxt, x, y, _x, _y, o) {
        var line = new Line(x, y, _x, _y, o)
        cxt.beginPath()
        cxt.strokeStyle = 'rgba(0,0,0,' + o + ')'
        cxt.moveTo(line.beginX, line.beginY)
        cxt.lineTo(line.closeX, line.closeY)
        cxt.closePath()
        cxt.stroke();

    }
    //初始化生成原点
    function init() {
        circleArr = [];
        for (var i = 0; i < POINT; i++) {
            circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(15, 2), num(10, -10) / 40, num(10, -10) / 40));
        }
        draw();
    }

    //每帧绘制
    function draw() {
        context.clearRect(0, 0, canvas.width, canvas.height);
        for (var i = 0; i < POINT; i++) {
            drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
        }
        for (var i = 0; i < POINT; i++) {
            for (var j = 0; j < POINT; j++) {
                if (i + j < POINT) {
                    var A = Math.abs(circleArr[i + j].x - circleArr[i].x),
                            B = Math.abs(circleArr[i + j].y - circleArr[i].y);
                    var lineLength = Math.sqrt(A * A + B * B);
                    var C = 1 / lineLength * 7 - 0.009;
                    var lineOpacity = C > 0.03 ? 0.03 : C;
                    if (lineOpacity > 0) {
                        drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i + j].x, circleArr[i + j].y, lineOpacity);
                    }
                }
            }
        }
    }
    window.onload = function () {
        init();
        setInterval(function () {
            for (var i = 0; i < POINT; i++) {
                var cir = circleArr[i];
                cir.x += cir.moveX;
                cir.y += cir.moveY;
                if (cir.x > WIDTH) cir.x = 0;
                else if (cir.x < 0) cir.x = WIDTH;
                if (cir.y > HEIGHT) cir.y = 0;
                else if (cir.y < 0) cir.y = HEIGHT;

            }
            draw();
        }, 16);
    }


</script>
</html>
<%--<header>
    <div class="slogan">
        <c:choose>
            <c:when test="${gukeer:notEmptyString(logo)}">
                <img src="${ctx}/file/pic/show?picPath=${logo}" style="height: 45px; vertical-align: middle" alt=""/>
            </c:when>
            <c:otherwise>
                <img src="${ctxStaticNew}/images/logo-logo.png" alt=""/>
            </c:otherwise>
        </c:choose>
    </div>



</header>--%>
<%--  <main>
  <div>

      <img src="${ctxStaticNew}/images/login-image.png" alt="" <c:if test="${gukeer:notEmptyString(bgPicture)}"> style="visibility:hidden;"</c:if>/>

  </div>
  <div class="login-box">
      <div class="user">
          <p>用户名</p>
          <input id="username" type="text"/>
      </div>
      <div class="pwd">
          <p>密码</p>
          <input id="password" type="password"/>
      </div>
      <div class="operation">
          <p>
              <input type="checkbox" id="remember"/>
              <label for="remember">记住我</label>
          </p>
         &lt;%&ndash; <div>
              <a href="#">忘记密码？</a>
          </div>&ndash;%&gt;
      </div>
      <div class="login-btn">
          <button id="login_btn">登录</button>
      </div>
  </div>
</main>
<footer>
  <p>©2014-2016 Beijing Search Champion Technology Co.,Ltd. 北京教育云开放平台科技有限公司</p>
  <p>京ICP备16000182-3号|京公安网备11010502027075</p>
</footer>
</body>
</html>--%>