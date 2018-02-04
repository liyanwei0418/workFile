<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
</head>
<style>
    *{
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    .warp-containt{
        font-size: 14px;
        overflow-y: auto;

    }
    .warp-containt>p{
        padding: 25px 0 15px 0;

    }
    p .active{
        color: #54ab37;
        display: inline-block;
    }
    p span{
        display: inline-block;
        line-height: 14px;
        padding: 0 15px;
        border-right: 1px solid #999;
        cursor: pointer;
    }
    p span:last-child{
        border: none;
    }
    ul{
        list-style: none;
        overflow: hidden;
        display: none;
    }
    div > .active{
        display: block;
    }
    ul li{
        width: 16.65%;
        padding: 6px 0 15px 0;
        text-align: center;
        position: relative;
        float: left;
    }
    ul li p{
        margin-top: 5px;
    }
    ul li span{
        position: absolute;
        top: 5px;
        right: 30px;
        display: inline-block;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        background: #54ab37;

    }
    p span:hover{
        color: #54ab37;
    }
    input{
        width: 15px;
        height: 15px;
        position: absolute;
        bottom: 40px;
        right: 3px;
    }
</style>
<body>
<div class="warp-containt">
    <p>
        <c:forEach items="${res}" var="map" varStatus="status">
            <span <c:if test="${status.index==0}"> class="active" </c:if> >${map.appTypeName}</span>
        </c:forEach>
    </p>
    <div>

        <c:forEach items="${res}" var="map" varStatus="status">
            <ul <c:if test="${status.index ==0}">class="active" </c:if> >
                <c:forEach items="${map.appViewList}" var="app" varStatus="status">
                    <li>
                        <c:if test="${not empty app.iconUrl}">
                            <c:if test="${app.sfczxmz==0}">
                                <c:choose>
                                    <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img src="${app.iconUrl}" style="width:68px;height:68px;"></c:when>
                                    <c:otherwise><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" style="width:68px;height:68px;"></c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${app.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${app.iconUrl}" /></c:if>
                        </c:if>
                        <p>${app.name}</p>
                        <input name="chooseApp" type="checkbox" id="${app.id}">
                    </li>
                </c:forEach>
            </ul>
        </c:forEach>

    </div>
</div>
</body>
</html>
<script src="${ctxStaticNew}/js/jquery.min.js"></script>
<script>
    $('p span').on('click',function () {
        $(this).addClass('active').siblings().removeClass('active');
        $('ul').eq($('p span').index($(this))).addClass('active').siblings().removeClass('active');
    });


    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var spCodesTemp = "";
        $("input[name='chooseApp']:checked").each(function(i){
            if(0==i) {
                spCodesTemp = $(this).attr("id");
            } else{
                spCodesTemp += (","+$(this).attr("id"));
            }
        });

        $.post("${ctx}/app/savemyapp",{
            ids:spCodesTemp,
            type:"${type}"
        },function(retJson){
            if (retJson.code == '0') {
                window.parent.location.href = "${ctx}/app/appstore/index?choose=myAppsPage";
            } else {
                alert(retJson.msg);
            }
        });

    }
</script>