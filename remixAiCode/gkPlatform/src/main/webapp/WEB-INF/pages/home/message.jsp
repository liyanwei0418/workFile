<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>消息中心</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/newsCenter.min.css"/>
</head>
<body>

<main class="container">
    <div id="news-top">
        <p>消息中心</p>
    </div>
    <aside id="news-aside" class="col-sm-3">
        <ul>
            <li>
                <a href="#" data="news-center">消息中心</a>
                <%--<i id="newsCenter">0</i>--%>
            </li>
            <li class="active">
                <a href="#" data="inform-notice">通知公告</a>
                <c:if test="${publicCount > 0}">
                    <i id="publicMessage">${publicCount}</i>
                </c:if>
            </li>
        </ul>
    </aside>
    <section id="inform-notice" class="col-sm-9">
        <div class="news-center-inform-notice">
            <ul>
                <c:forEach items="${notifyList}" var="notify">
                    <li onclick="window.location.href='${ctx}/notify/details/${notify.notifyId}'">
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
                            【${notify.columName}】
                                ${fn:substring(notify.title,0, 15)}
                            <c:if test="${notify.title.length()>15-notify.columName.length()}">
                                .....
                            </c:if>
                        </p>
                        <div>
                            <span>${gukeer:intervalNowTimeToView(notify.publishTime)}</span>
                           <%-- <span>21:12</span>--%>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="fenye">

                <div class="fenY" id="fenY">
                </div>
            </div>
        </div>
    </section>
</main>
<!--分页插件-->

<!--end 分页插件-->
<script>
    $(".fenY").createPage({
        pageCount:${pageInfo.pages},//总页数
        current:${pageInfo.pageNum},//当前页面
        backFn:function(p){
            window.location.href = "${ctx}/home/messagecenter?pageNum="+p+"&pageSize=10";
        }
    });

    $(".gotoPage").click(function (){
        var pageNum = $(".go").val();
        if (pageNum <= 0 || pageNum >${pageInfo.pages}){
            layer.msg("请输入正确的页码")
        } else {
            window.location.href = "${ctx}/home/messagecenter?pageNum="+$(".go").val();
        }
    });
</script>

</body>
</html>