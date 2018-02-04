<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctxStaticNew}/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${ctxStaticNew}/css/popWindow.min.css"/>
<link href="${ctxStaticNew}/css/lanrenzhijia.css" type="text/css" rel="stylesheet" />

<body>
<div class="container-fluid activity-center">
    <div class="activity-title">
        <aside class="hidden-xs">
            <c:if test="${not empty app.iconUrl}">
                <c:if test="${app.sfczxmz==0}">
                    <c:choose>
                        <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img src="${app.iconUrl}" width="64px" height="64px" /></c:when>

                        <c:otherwise><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" width="64px" height="64px" /></c:otherwise>
                    </c:choose>
                </c:if>

                <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${app.iconUrl}" width="64px" height="64px" /></c:if>
            </c:if>
        <%--    <img src="images/app9.png" alt=""/>--%>
        </aside>
        <div>
            <h3>${app.name}</h3>
            <div>
                <span>目标用户：
                 <%--<c:forEach items="${app.targetUsers}" var="targetUser">--%>
                 <c:forEach items="${targetUsers}" var="targetUser">
                     <c:if test="${targetUser==1}">教师&nbsp;&nbsp;</c:if>
                     <c:if test="${targetUser==2}">学生&nbsp;&nbsp;</c:if>
                     <c:if test="${targetUser==3}">家长&nbsp;&nbsp;</c:if>
                     <c:if test="${targetUser==4}">其他&nbsp;&nbsp;</c:if>
                 </c:forEach>
                </span>
                <span>开发商：
                ${app.developers}
                </span>
                <span>所属类别：
                 <c:if test="${app.category==1}">教务教学&nbsp;&nbsp;</c:if>
                     <c:if test="${app.category==2}">互动空间&nbsp;&nbsp;</c:if>
                </span>
                <span>更新时间：
                 <c:if test="${empty app.updateDate}">
                     ${gukeer:millsToyyyyMMdd(app.createDate)}
                 </c:if>
                    <c:if test="${not empty app.updateDate}">
                        ${gukeer:millsToyyyyMMdd(app.updateDate)}
                    </c:if>
                </span>
            </div>
        </div>
    </div>
    <div class="activity-intro">
        <h3>应用简介</h3>
        <p>
            <c:if test="${empty app.remarks}">
                暂无
            </c:if>
            <c:if test="${not empty app.remarks}">
                ${app.remarks}
            </c:if>
        </p>
    </div>

    <%--<c:if test="${not empty app.picUrls}">--%>
    <c:if test="${not empty picUrls}">
        <div class="activity-intro">
        <h3>应用截图</h3>
        <div class="scrollPic row">
            <div class="zoombox">
                <div class="zoompic"><img src="" height="394" alt="" /></div>
                <div class="sliderbox">
                    <div id="btn-left" class="arrow-btn dasabled"></div>
                    <div class="slider" id="thumbnail">
                        <ul>
                            <%--<c:forEach items="${app.picUrls}" var="picURI" varStatus="status">--%>
                            <c:forEach items="${picUrls}" var="picURI" varStatus="status">
                                <c:if test="${app.sfczxmz==0}"><li <c:if test="${status.index==0}"> class="current"</c:if> ><a href="${ctx}/file/pic/show?picPath=${picURI}" target="_blank"><img src="${ctx}/file/pic/show?picPath=${picURI}" width="115" height="74" alt="" /></a></li></c:if>
                                <c:if test="${app.sfczxmz==1}"><li  <c:if test="${status.index==0}"> class="current"</c:if> ><a href="${ctxStatic}/image/appDetails/${picURI}" target="_blank"><img src="${ctxStatic}/image/appDetails/${picURI}" width="115" height="74" alt="" /></a></li></c:if>
                                <c:if test="${app.sfczxmz==2}"><li  <c:if test="${status.index==0}"> class="current"</c:if> ><a href="${picURI}" target="_blank"><img src="${picURI}" width="115" height="74" alt="" /></a></li></c:if>
                            </c:forEach>
                          <%--  <li class="current"><a href="images/app1.png"><img src="images/app1.png" width="115" height="74" alt="" /></a></li>
                            <li><a href="images/app2.png"><img src="images/app2.png" width="115" height="74" alt="" /></a></li>
                            <li><a href="images/app1.png"><img src="images/app1.png" width="115" height="74" alt="" /></a></li>--%>
                        </ul>
                    </div>
                    <div id="btn-right" class="arrow-btn"></div>
                </div>
            </div>
        </div>
    </div>
     </c:if>
    <footer class="activity-footer">
        <c:choose>
            <c:when test="${app.isDefault == 0}"><span>系统应用,已添加,可以使用</span></c:when>
            <c:otherwise>
                <c:if test="${appIdList.contains(app.id)}">
                    <span>已添加</span>
                </c:if>
                <c:if test="${!appIdList.contains(app.id)}">
                    <span>未添加</span>
                </c:if>
            </c:otherwise>
        </c:choose>

    </footer>
</div>
<script src="${ctxStaticNew}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxStaticNew}/js/lanrenzhijia.js"></script>
</body>
</html>
<script>
    $(function () {
        var firstPhoto = $(".slider ul li:first img").attr("src");
        $(".zoompic img").attr("src",firstPhoto );
    })

    $(".zoompic>img").click(function(){
        var imgSrc=$(".zoompic>img").attr("src");
        $('body').append("<div id='showBigPic' style='z-index:999;position:fixed;top:0;bottom:0;left:0;right:0;background:rgba(0,0,0,.4)'>" +
                "<div id='modalBox' style='width:100%;height:100%;display: flex;align-items: center;justify-content: center'>" +
               // "<div id='closeModal' style='color:#efefef;font-size: 15px;position:absolute;top:20px;right:20px;'>close</div>"+
                "<img src='"+imgSrc+"' style='display:block;margin:0 auto;width:auto;'>" +
                "</div>" +
                "</div>")
    });

    $(document).on("click","#showBigPic",function(){
        console.log('click');
        $("#showBigPic").remove();
    });

    function savemyapp(id) {
        $.ajax({
            url: '${ctx}/app/savemyapp',
            type: 'POST',
            data: {ids: id},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                    return;
                }
                setTimeout(function(){window.location.reload();}, 100);

            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>
</html>