<%@ include file="../../common/common.jsp" %>
<%@ include file="../../common/headerMenu.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用管理</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css" />
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/buttons.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/pageDevide.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
     <style>
        .caozuoTd>span{
            padding-left: 10px;
        }
        .layui-layer{
            top:250px !important;
        }
    </style>
</head>

<body class="thisBody">

<div style="height:78px;width:1200px;margin:0px auto">
    <div style="z-index:99;position:fixed;margin:0px 0px">

        <div class="app_store_headerMenu fenBan-header">

            <div class="appStoreLeft fenBanLeft">
                <img src="${ctxStatic}/image/fenban/fenban.png" />&nbsp;
                <a style="line-height: 49px;font-size:24px;color: #1AB394;">应用管理</a>
            </div>
            <div class="rsMenuRight">
                <ul>
                    <li onclick="window.location.href='${ctx}/menu/index?pageSize=10'">菜单管理</li>
                    <li onclick="window.location.href='${ctx}/app/index'" class="active">应用管理</li>
                    <li onclick="window.location.href='${ctx}/school/permissionMan'">角色管理</li>
                    <li onclick="window.location.href='${ctx}/school/index'" >平台管理</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="mainContain fenbanMain fenbanStep">
    <div class="forBeauty" style="background:#ffffff;">
        <div class="rsTableHeader">
            <div class="">

                <input onclick="openDialog('机构应用','${ctx}/app/edit','550px','600px');" class="rsButton" type="button"  value=" 新增 " />

                <div style="color: #999999;font-size: 16px;float: right;margin-right: 30px;">
                    <input class="searchInput" type="text" placeholder=" 搜索应用名称" value=""  name="name"/> <!--高级搜索-->
                    <input style="opacity: 0;position: relative;margin-left: -40px;height:30px;" class="summitButton" type="button" name="" id="search" value="搜索"/>
                </div>

            </div>
        </div>

        <div class="rsTable">

            <table>
                <tr class="headerTh">
                    <td>app名称</td>
                    <td>app图标</td>
                    <td>应用状态</td>
                    <td>描述</td>
                    <td>链接地址</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${appList}" var="app" varStatus="status">
                    <tr>
                        <td>${app.name}</td>
                        <td>
                            <img src="${ctx}/file/pic/show?picPath=${app.iconUrl}" width="100px" height="100px">
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${app.appStatus==0}">
                                    审核中
                                </c:when>
                                <c:when test="${app.appStatus==1}">
                                    已上线
                                </c:when>
                                <c:when test="${app.appStatus==2}">
                                    已下线
                                </c:when>
                                <c:when test="${app.appStatus==3}">
                                    其他异常
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            ${app.remarks}
                        </td>
                        <td>
                            ${app.webUrl}
                        </td>
                        <td>
                            <span style="color: red" onclick="alertTips(400,222,'删除应用','确定要删除当前应用吗，删除后，相关的信息也会删除,确定吗？','appDelete(\'${app.id}\')')">删除</span>
                            <span style="color: #1ab394" onclick="openDialog('机构修改','${ctx}/app/edit?id=${app.id}','550px','600px');">编辑</span>
                            <span style="color: #1ab394" onclick="window.location.href='${ctx}/school/app/distribution?id=${app.id}&name='+encodeURI(encodeURI('${app.name}'))">分配角色</span>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="fenY" id="fenY"></div>
            <br />
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/app/index?pageNum="+p+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var name=$("input[name='name']").val();
            window.location.href="${ctx}/app/selectbyname?name="+encodeURI(encodeURI(name));


        });
    });

    function  appDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/app/del",{
            id:id,
        },function(retJson){
            window.location.reload();
        });
    }

</script>
</body>

</html>
