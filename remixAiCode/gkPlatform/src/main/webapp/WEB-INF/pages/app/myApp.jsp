<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/headerMenu.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用管理</title>
    <link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/fenBan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/css/rs.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <style type="text/css">
        .lanMuLeft {
            list-style: none;
            width: 100%;
        }

        .lanMuLeft > li {
            height: 60px;
            padding-left: 30px;
            line-height: 60px;
        }

        .contentTable > table {
            table-layout: fixed;
            margin: 0 auto;
            display: inline-table;
            width: 100%;
            text-align: center;
            margin-top: -228px;
        }

        /*td{*/
        /*width: 162px*/
        /*}*/
        .contentTable tr {
            height: 130px;
        }

        .borderBoxShadow {
            box-shadow: 0 5px 15px #aaaaaa; /* //rgba(0,0,0,.1); */
            transform: translate3d(0, -2px, 0); /* 跳动 */
            cursor: pointer;
            min-width: 88px;

        }

        .rightTopPic {
            display: none;
            margin: -120px 60px;
            position: absolute;
        }
 
    </style>
</head>
<body class="thisBody">

<c:forEach items="${res}" var="map">
    ${map.key}:<br>
    <c:forEach items="${map.value}" var="app">
        ${app.name}
    </c:forEach>
    <br>
</c:forEach>

<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">

        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png"/>&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">应用商店</a>
            </div>
            <div class="rsMenuRight">
                <ul style="margin-top: 15px;">
                    <li class="active">我的应用</li>
                    <li onclick="window.location.href='${ctx}/app/appstore/index?pageSize=16'">全部应用</li>
                    <%--<li>商店首页</li>--%>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">

        <div style="width: 100%;margin-top: 65px;">
            <div style="margin: 0 auto;width: 95%;height: 50px;">
						<span style="border-left: 4px #1AB394 solid;padding-left: 5px;font-size: 18px;color: #333333;">
							我的应用
						</span>
                <span id="pageSwitch" style="position: relative;font-size: 16px;color: #1AB394;float: right;">
							编辑
						</span>
            </div>
            <div class="contentTable">
                <table cellspacing="48">
                    <tr style="opacity: 0;">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%--<tr>--%>
                    <c:forEach items="${appList}" var="app" varStatus="status">
                        <c:if test="${(status.index)%6==0}"><tr></c:if>
                        <td <c:if test="${app.isDefault!=0}">class="shadow" </c:if>>
                            <c:if test="${not empty app.iconUrl}">
                                <c:if test="${app.sfczxmz==0}">
                                    <c:choose>
                                        <c:when test="${gukeer:isContainsString(app.iconUrl,'http')}"><img
                                                src="${app.iconUrl}" width="64px" height="64px"/></c:when>

                                        <c:otherwise><img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" width="64px"
                                                          height="64px"/></c:otherwise>
                                    </c:choose>
                                </c:if>

                                <c:if test="${app.sfczxmz==1}"><img src="${ctxStatic}/image/appDetails/${app.iconUrl}"
                                                                    width="64px" height="64px"/></c:if>
                            </c:if>
                                <%--<img src="${ctx}/file/pic/show?picPath=${defaultApp.iconUrl}" width="60px" height="65px"/>--%>
                            <div style="font-size:14px;color: #565656 ;padding-top: 10px;">
                                    ${app.name}
                            </div>
                            <c:if test="${app.isDefault!=0}"><img class="rightTopPic"
                                                                  src="${ctxStatic}/image/appDetails/deleteCircle.png"
                                                                  onclick="delMyApp(this,'${app.id}')"/></c:if>
                                <%--<img class="rightTopPic" src="${ctxStatic}/image/appDetails/deleteCircle.png"/>--%>
                        </td>
                        <c:if test="${(status.index)%6==5}"></tr></c:if>

                        <c:if test="${status.index==(size-1)}">
                            <td onclick="openDialog('添加应用','${ctx}/app/showalertapps','517px ','433px')">
                                <img src="${ctxStatic}/image/appDetails/jiahao1.png" width="50px" height="50px"/>
                            </td>
                        </c:if>
                        <c:if test="${status.index==(size-1)}"></tr></c:if>
                    </c:forEach>
                </table>
            </div>
        </div>

    </div>
</div>

<div class="grayHeader">

</div>
</body>
<script type="application/javascript">

    $(document).ready(function () {

        var status = 1;

        $("#pageSwitch").click(
                function () {
                    if (status == 1) {
//                        $(".contentTable td").addClass("borderBoxShadow");
                        $(".shadow").addClass("borderBoxShadow");
                        $(".rightTopPic").show();
                        status = 2;
                        $(this).html("返回");
                    } else {
                        $(".contentTable td").removeClass("borderBoxShadow");
                        $(".rightTopPic").hide();
                        status = 1;
                        $(this).html("编辑");
                    }

                });


    })

    function delMyApp(btn, id) {
        var _id = id;
        $.ajax({
            url: '${ctx}/app/delmyapp',
            type: 'POST',
            data: {appId: _id},
            async: true,
            dataType: "json",
            success: function (data) {
                if (!data) {
                    alertTips('300px', '150px', '提示', '应用失败,请刷新后重试!');
                    return;
                }
                /*var append =
                 $(".app-ul").append($(hadLi));*/
                $(btn).parents("td").remove();

            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>


</html>