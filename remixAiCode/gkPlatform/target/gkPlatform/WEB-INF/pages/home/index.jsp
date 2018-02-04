<%@ page import="java.util.HashMap" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        .layui-layer.layui-layer-page.yourclass.layer-anim {
            top: 30% !important;
        }

        .news-box ul li {
            padding: 10px 0 !important;
        }

        .news-box {
            padding-top: 10px;
            padding-bottom: 30px !important;
        }

        .news-box ul li p span:first-child {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .app-header {
            height: 40px;
            font-size: 0;
        }

        .app-header span {
            height: 40px;
            line-height: 40px;
            width: 80px;
            background: #fff;
            color: #333;
            text-align: center;
            border: 1px solid #efefef;
            font-size: 14px;
            cursor: pointer;
        }

        .app-header .sp-active {
            color: #54ab37;
            height: 41px;
            border: none;
        }

        #user {
            background-size: 100% 100%;
            padding: 15px;
        }

        #user > div div {
            margin-left: 15px;
            display: inline-block
        }

        #user h2 {
            font-size: 20px;
            margin-top: 22px;
        }

        #user h3 {
            font-size: 14px;
        }

        #app-frequent .app-box {
            /*background: none;*/
        }

        .app-box ul {
            padding: 0;
            /*background: #F2F2F2;*/
        }

        .app-box ul li {
            width: 19%;
            margin-right: 1%;
            background: #fff;
            margin-bottom: 7px;
            height: 144px;
            padding-top: 30px;
        }

        .app-box ul li:nth-child(5n) {
            margin-right: 0
        }

        .app-frequent > h3 {
            padding-right: 10px;
        }

        .news-box {
            padding-bottom: 30px;
        }

        @media screen and ( max-width: 994px ) {
            #user > div > span {
                float: none !important;
            }

            #user > div div {
                margin: 0;
            }

            .app-box > ul.row li {
                width: 24%;
            }

            .app-box ul li:nth-child(5n) {
                margin-right: 1%
            }
        }
    </style>
    <script>
        function appJump(appId) {
            //容易被浏览器拦截，采用如下方式
            $.ajax({
                type: "POST",
                url: "${ctx}/third/party/page",
                dataType: "json",
                data: {appId: appId},
                async: false,
                success: function (data) {
                    if (data.code == 0) {
                        if(data.data.indexOf("net/disk/index")!=-1){
                           var url ="${ctx}/net/disk/verify";
                            $.get(url,{},function (netData) {
                                if (netData.code==0){
                                    window.open("${ctx}/"+netData.data);
                                }else{
                                    hintYou(400, 202, '温馨提示',"您尚未开通云盘账号，请联系管理员");
                                }
                            })
                        }else {
                            window.open(data.data);
                        }
                    } else {
                        hintYou(400, 202, '温馨提示', '您没有权限使用该应用，如有需要请联系管理员。');
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        }


    </script>
</head>
<body style="background-color: #f2f2f2 !important;">
<div class="container">
    <div class="row">
        <!--TODO 左边一列-->
        <aside id="left-side" class="col-sm-4" style="padding-right:30px;">
            <!--TODO 用户信息-->
            <div id="user">
                <div class="row" style="display:inline-block">
                        <span style="display:inline-block;border:2px solid #fff;overflow:hidden;border-radius: 50%;float:left;">

                            <c:if test="${gukeer:emptyString(loginUser.photoUrl)}"> <img
                                    style="width:102px;height:102px"
                                    src="${ctx}/file/pic/show?picPath=${defaultHead}" alt="">
                            </c:if>
                                <c:if test="${gukeer:notEmptyString(loginUser.photoUrl)}">
                                    <img style="width:102px;height:102px"
                                         src="${ctx}/file/pic/show?picPath=${loginUser.photoUrl}" alt=""/>
                                </c:if>

                        </span>
                    <div>
                        <h2>${userName}</h2>

                        <c:choose>
                            <c:when test="${userType == 3}">
                                <h2>${bottomName}</h2>
                            </c:when>
                            <c:otherwise>
                                <h3 style="max-width: 180px;">${bottomName}</h3>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>

            <!--TODO 通知公告、消息、邮件-->
            <c:if test="${userType == 1}">
                <div id="news-block">
                    <h1>
                        <span></span>
                        通知公告
                        <p class="get-more" style="float:right;margin:0;font-size:14px;font-weight:normal;">
                            <a onclick="window.open('${ctx}/notify/index')">进入通知公告 ></a>
                        </p>
                    </h1>
                    <div class="news-box">
                        <ul>
                            <c:choose>
                                <c:when test="${empty notifyList}">
                                    暂无通知
                                </c:when>
                                <c:when test="${fn:length(notifyList) > 6}">
                                    <c:set var="endIndex" value="6"></c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="endIndex" value="${fn:length(notifyList)}"></c:set>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="0" end="${endIndex}" items="${notifyList}" var="notify">
                                <li onclick="window.open('${ctx}/notify/details/${notify.notifyId}')">
                                    <p
                                            <c:choose>
                                                <c:when test="${notify.readFlag==1}">
                                                    class="read"
                                                </c:when>
                                                <c:otherwise>
                                                    class="not-read"
                                                </c:otherwise>
                                            </c:choose>
                                    >

                                        <span>
                                            【${notify.columName}】
                                            ${fn:substring(notify.title,0, 15)}
                                        <c:if test="${notify.title.length()>15-notify.columName.length()}">
                                            .....
                                        </c:if>
                                        </span>


                                        <span>${gukeer:intervalNowTimeToView(notify.publishTime)}</span>
                                    </p>
                                </li>
                            </c:forEach>
                        </ul>
                            <%--  <div class="get-more">
                                  <a onclick="window.open('${ctx}/notify/index')">进入通知公告 ></a>
                              </div>--%>

                    </div>
                </div>
            </c:if>

        </aside>
        <main id="main-part" class="col-sm-8">
            <div id="app-frequent">
                <h3 class="get-more">
                    <span></span>
                    系统应用
                    <%-- <a onclick="window.open('${ctx}/app/appstore/index')">进入应用商店 ></a>--%>
                </h3>

                <div class="app-box">
                    <ul class="row">
                        <c:if test="${empty defaultAppList}">
                            暂无应用
                        </c:if>
                        <c:forEach items="${defaultAppList}" var="defaultApp" varStatus="status">
                            <li style="" onclick="appJump('${defaultApp.id}')">
                                  <span>
                                        <c:if test="${defaultApp.sfczxmz==0}">
                                            <c:choose>
                                                <c:when test="${gukeer:isContainsString(defaultApp.iconUrl,'http')}"><img
                                                        class="img-responsive" src="${defaultApp.iconUrl}"/></c:when>

                                                <c:otherwise><img class="img-responsive"
                                                                  src="${ctx}/file/pic/show?picPath=${defaultApp.iconUrl}"/></c:otherwise>
                                            </c:choose>
                                        </c:if>

                                        <c:if test="${defaultApp.sfczxmz==1}"><img class="img-responsive"
                                                                                   src="${ctxStatic}/image/appDetails/${defaultApp.iconUrl}"/></c:if>

                                    </span>
                                <p>${defaultApp.name}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>