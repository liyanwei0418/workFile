<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>应用商店</title>
    <style>
        #app-index main.container{border-top:none;}
        .layui-layer.layui-layer-iframe.layer-anim {
            top: 6% !important;
        }
        .row{
            margin:0;
        }
    </style>
</head>
<body>
<nav>
    <div class="container">
        <div class="app-store-nav">
            <div><span>应用商店</span></div>
        </div>
        <div>
            <ul class="app-store-menu">
                <li>
                    <a onclick="menuShift(this)" data="app-index" id="appIndexPage">应用首页</a>
                </li>
                <li>
                    <a onclick="menuShift(this)" data="app-allApp" id="allAppsPage">全部应用</a>
                </li>
                <li>
                    <a onclick="menuShift(this)" data="app-myApp" id="myAppsPage">我的应用</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div id="app-index" style="display: none">
    <div class="app-store-banner row">
        <img class="img-responsive" src="${ctxStaticNew}/images/appStore-banner.png" alt=""/>
    </div>
    <main class="container">
        <div class="app-store-title">
            <span>推荐应用</span>
        </div>
        <div class="row app-store-app">
            <ul>
                <c:forEach items="${appAllList}" var="appAll" varStatus="status">
                    <li class="activity-center" onclick="openDialogView('应用详情','${ctx}/app/showappdetails?id=${appAll.id}','70%','90%');">
                        <c:if test="${not empty appAll.iconUrl}">

                            <c:if test="${appAll.sfczxmz==0}">
                                <c:choose>
                                    <c:when test="${gukeer:isContainsString(appAll.iconUrl,'http')}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${appAll.iconUrl}" /></c:when>
                                    <c:otherwise><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${appAll.iconUrl}" /></c:otherwise>
                                </c:choose>
                            </c:if>

                            <c:if test="${appAll.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${appAll.iconUrl}" /></c:if>
                        </c:if>
                        <p>${appAll.name}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </main>
</div>

<div id="app-allApp" class="container" style="display: none">
    <div class="app-allApp-content">
        <div class="app-store-search">
            <input type="hidden" id="searchHidden" value="${name}">
            <button class="summitButton" id="searchName" ></button>
            <input class="searchInput" type="text" style="padding-left: 5px;" placeholder="搜索应用名称" value="" name="searchName"/>
        </div>
        <div class="app-store-type">
            <span>应用类别</span>
            <ul>
                <li>
                    <a href="#" class="active" flag="all">全部</a>
                </li>
                <li>
                    <a href="#" flag="0">系统应用</a>
                </li>
                <li>
                    <a href="#" flag="1">教学教务</a>
                </li>
                <li>
                    <a href="#" flag="2">互动空间</a>
                </li>
                <li>
                    <a href="#" flag="3">校务管理</a>
                </li>
            </ul>
        </div>
        <div class="app-store-target">
            <span>应用对象</span>
            <ul>
                <li>
                    <a href="#" class="active" flag="all">全部</a>
                </li>
                <li>
                    <a href="#" flag="1" >老师</a>
                </li>
                <li>
                    <a href="#" flag="2" >学生</a>
                </li>
                <li>
                    <a href="#" flag="3" >家长</a>
                </li>
            </ul>
        </div>
        <div class="app-store-area">
            <span>应用范围</span>
            <ul>
                <li>
                    <a href="#" class="active" flag="all">全部</a>
                </li>
                <li>
                    <a href="#" flag="0" >商店应用</a>
                </li>
                <li>
                    <a href="#" flag="2" >本校应用</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="app-store-apps">
        <p id="hiddentips" style="display: none">搜索到${size}个“${name}”相关结果</p>
        <p id="hidden1" style="display: none">该分类下目前无应用</p>
        <ul>

            <c:forEach items="${appList}" var="app" varStatus="status">
                <li class="alertAppDetil" onclick="openDialogView('应用详情','${ctx}/app/showappdetails?id=${app.id}','70%','90%');">
                    <c:if test="${not empty app.iconUrl}">
                        <c:if test="${app.sfczxmz==0}">
                            <c:choose>
                                <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${app.iconUrl}" /></c:when>

                                <c:otherwise><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${app.iconUrl}" /></c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${app.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${app.iconUrl}" /></c:if>
                    </c:if>
                    <p>${app.name}</p>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>

<%---------我的应用 ---------------------------------------------------------------------------begain--------------------------------%>
<div id="app-myApp" class="container"  style="display: none">

    <div class="app-myApp-content">
        <div class="app-store-edit app-store-con">
            <button class="appButton" id="manageApp" >管理应用</button>
            <button class="appButton" id="addApp" onclick="openDialog('添加应用','${ctx}/app/showalertapps?type=my','517px ','433px')">添加应用</button>
        </div>
        <div class="app-store-edit app-store-finish">
            <button class="appButton" id="finishApp" >完成</button>
        </div>
    </div>
    <div class="app-warp-containt">
        <div class="app-store-most app-store-apps">
            <p><span>常用应用</span></p>
            <ul>
                <c:forEach items="${commonlyUsedAppList}" var="app" varStatus="status">
                    <li>
                        <c:if test="${not empty app.iconUrl}">
                            <c:if test="${app.sfczxmz==0}">
                                <c:choose>
                                    <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${app.iconUrl}" /></c:when>
                                    <c:otherwise><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${app.iconUrl}" /></c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${app.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${app.iconUrl}" /></c:if>
                        </c:if>
                        <p>${app.name}</p>
                        <span onclick="test1(this,'${app.id}')"></span>
                    </li>
                </c:forEach>
                <li class="addotherApp" onclick="openDialog('添加应用','${ctx}/app/showalertapps?type=common','540px ','433px')">
                    <div style="width: 68px;height: 68px;margin: 0 auto;"></div>
                    <p>添加常用应用</p>
                </li>
            </ul>
        </div>

        <div class="app-store-most app-store-apps app-store-range">
            <p>
                <c:forEach items="${res}" var="map" varStatus="status">
                <span <c:if test="${status.index==0}"> class="app-range-spactive" </c:if> >${map.appTypeName}</span>
                </c:forEach>
            </p>

            <c:forEach items="${res}" var="map" varStatus="status">
                <ul <c:if test="${status.index!=0}">style="display: none"</c:if> >
                    <c:forEach items="${map.appViewList}" var="app" varStatus="status">
                        <li class="alertAppDetil">
                            <c:if test="${not empty app.iconUrl}">
                                <c:if test="${app.sfczxmz==0}">
                                    <c:choose>
                                        <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${app.iconUrl}" /></c:when>
                                        <c:otherwise><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${app.iconUrl}" /></c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${app.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${app.iconUrl}" /></c:if>
                            </c:if>
                            <p>${app.name}</p>
                            <span onclick="test2(this,'${app.id}')"></span>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </div>
    </div>
</div>
<%---------我的应用 ---------------------------------------------------------------------------end--------------------------------%>

<script>
    $(function(){
        //首次进入，切换到系统应用
        if(${empty category && empty name}){
            menuShift($("#appIndexPage"));
        }
        if(${not empty chooseTap}){
            menuShift($("#${chooseTap}"))
        }

        //按应用类别和应用对象搜索时，搜索对象回显在页面上
        if(${category != ""}){
            menuShift($("#allAppsPage"));
            $(".app-store-type a[flag = all]").removeClass('active');
            $(".app-store-target a[flag = all]").removeClass('active');
            $(".app-store-area a[flag = all]").removeClass('active');
            $(".app-store-type a[flag = ${category}]").addClass('active');
            $(".app-store-target a[flag = ${targetUser}]").addClass('active');
            $(".app-store-area a[flag = ${area}]").addClass('active');
            if(${size==0 && name== ""} ){
                $("#hidden1").show();
            }
        }

        //搜索框关键字回显
        $("input[name='searchName']").val($("#searchHidden").val());
        //提示搜索结果
        if('${name}' != ""){
            menuShift($("#allAppsPage"));
            $("#hiddentips").show();
        }

        // 按应用对象和应用类别搜索时，先显示高亮，获取参数后后台查找
        $('.app-store-target a,.app-store-type a, .app-store-area a').click(function(){
            $(this).addClass('active');
            $(this).parent().siblings().children().removeClass('active');

            //按应用对象和应用类别搜索
            var searchin=$("ul li a.active");
            for(var i=0;i<searchin.length;i++){
                if(i>0){
                    searchin[i-1] = $(searchin[i]).attr("flag")
                }

            }
            window.location.href="${ctx}/app/appstore/index?category="+searchin[0]+"&targetUser="+searchin[1]+"&area="+searchin[2];
        })

        /*查询搜索*/
        $(".summitButton").click(function () {
            var name=$("input[name='searchName']").val();
            if(name != ""){
                window.location.href="${ctx}/app/appstore/index?name="+encodeURI(encodeURI(name));
            }
        });

        $('#manageApp').on('click', function () {
            $(".app-store-most li>span").show();
            $('.app-store-con').hide();
            $('.app-store-finish').show();
        });
        $('.app-store-finish #finishApp').on('click', function () {
            $('.app-store-finish').hide();
            $(".app-store-most li>span").hide();
            $('.app-store-con').show();
        })
    });
    //切换推荐应用和全部应用
    function menuShift(obj){
        $(obj).addClass('active');
        $(obj).parent().siblings().children().removeClass('active');
        var href=$(obj).attr('data');
        var div=$('div[id='+href+']');
        if(href==div[0].id){
            $('div[id^=app]').hide();
            $('div[id='+href+']').show();
        }
    };

    function test1(btn,id) {
        $.ajax({
            url: '${ctx}/app/common/delete',
            type: 'POST',
            data: {appId: id},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                    return;
                }
                $(btn).parents("li").remove();
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
    function test2(btn,id) {
        //删除已经添加的应用
        $.ajax({
            url: '${ctx}/app/delmyapp',
            type: 'POST',
            data: {appId: id},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                    return;
                }
                $(btn).parents("li").remove();

            },
            error: function () {
                alert("请求失败");
            }
        });
    }

// 切换应用类别
    $('.app-store-range>p>span').on('click', function () {
        $(this).addClass('app-range-spactive').siblings().removeClass('app-range-spactive');
        $(".app-store-range>ul").eq($('.app-store-range>p>span').index($(this))).show().siblings('ul').hide();
    })

</script>
</body>
</html>