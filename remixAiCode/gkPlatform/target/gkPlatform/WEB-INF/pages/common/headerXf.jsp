<%@ page import="cn.gukeer.platform.common.LogUtils" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <%--以下css的加载顺序不可调换--%>
    <link rel="icon" href="${ctxStaticNew}/images/logo.png"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/style.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/common.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/pageDevide.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/Notice.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/oldCss.css"/>

    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/action.js"></script>
    <script src="${ctxStaticNew}/js/html5shiv.min.js"></script>
    <script src="${ctxStaticNew}/js/respond.min.js"></script>
    <script src="${ctxStaticNew}/js/jquery.ztree.core.js"></script>
    <script src="${ctxStaticNew}/js/pageDevide.js"></script>
    <!--end 分页插件-->
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>

    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>


    <%--<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/util.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/interface/NotifyJs.js"></script>--%>

    <script>
        if (${empty loginUser}) {
            layer.msg("登录超时，请重新登录！");
            window.location.href = "${ctx}/doLogout";
        }

        $(function () {
            //NotifyJs.init();
            $.get("${ctx}/head/getlogo", {}, function (retJson) {
                if (retJson.msg != "") {
                    document.getElementById("logo").src = "${ctx}/file/pic/show?picPath=" + retJson.msg;
                } else {
                    document.getElementById("logo").src = "${ctxStaticNew}/images/logo-logo.png";
                }
            });
        })
        //这个方法用来启动该页面的ReverseAjax功能
        /*    dwr.engine.setActiveReverseAjax(true);
         //设置在页面关闭时，通知服务端销毁会话
         dwr.engine.setNotifyServerOnPageUnload(true);*/


        function show(msg) {
            //var i = $(".news-center > i");
            // alert(Number(i.text())+1);
            $.get("${ctx}/home/remind/notify", {}, function (retJson) {
                var count = Number(retJson.data);
                var htm = '';
                if (count > 0) {
                    htm = '<li> <span onclick="window.open(\'${ctx}/home/messagecenter\')">您有一条新的通知</span></li>';

                    if ($(".news-center > i").length == 0) {
                        $(".news-center-menu").before('<i>' + count + '</i>');
                    } else {
                        $(".news-center > i").html(count);
                    }


                    $(".news-center-menu").html(htm);
                    $('.news-center-menu').css('display', 'block');
                } else {
                    htm = '<li><span>  暂无消息 </span> </li>';
                    $(".news-center-menu").html(htm);
                }
            });
        }
    </script>
</head>

<body>
<header id="header" class="container-fluid header-scroll">
    <div class="container">
        <div class="logo">
            <img style="height: 45px; vertical-align: middle" alt="" id="logo"/>
        </div>
        <div class="nav-menu">

            <shiro:lacksRole name="root">
                <span class="home active" onclick="window.location.href='${ctx}/home/index'"></span>
                <div class="news-center">
                    <c:if test="${publicCount > 0}">
                        <i>${publicCount}</i>
                    </c:if>
                    <ul class="news-center-menu">
                        <c:choose>
                            <c:when test="${publicCount > 0}">
                                <li>
                                <span onclick="window.open('${ctx}/home/messagecenter')">
                                    通知公告
                                        <i>${publicCount}</i>
                                </span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                <span>
                                    暂无消息
                                </span>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </shiro:lacksRole>

            <ul>
                <li class="account">
                    <a href="#">
                        <c:choose>
                            <c:when test="${userType == 3}">
                                ${bottomName}
                            </c:when>
                            <c:otherwise>
                                ${loginUser.name}
                            </c:otherwise>
                        </c:choose>
                        <i></i></a>
                    <ul class="account-box">
                        <shiro:lacksRole name="root">
                            <li><a href="${ctx}/user/editInfo"><i></i>设置</a></li>
                        </shiro:lacksRole>
                        <li><a href="${ctx}/doLogout"><i></i>退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

</header>
<div style="width:100%;height: 95px"></div>

</body>

</html>