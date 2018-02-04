<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理</title>

    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    td span {
        color: #54ab37 !important;
    }
    .layui-layer.layui-layer-iframe.layer-anim {
        top: 150px !important;
    }
    .onlineapp{
        background: url("${ctxSchool}/images/icon_line.png") no-repeat left 3px !important;
    }
</style>


<body>

<%@ include file="../../common/sonHead/schoolHead.jsp" %>

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
           <%-- <button onclick="openDialog('机构应用','${ctx}/app/edit','790px','750px');">新增应用</button>--%>
            <%--<button class="" style="background: #478ef9;border-color:#478ef9;">批量推送</button>--%>
            <%--<button class="">批量上线</button>--%>
        </div>
        <div class="roll-research" style="float: right;">
            <button class="summitButton" id="search"></button>
            <input type="hidden" id="searchHidden" value="">
            <input class="searchInput" type="text" name="name" placeholder="请输入应用名称"/>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <%--<th width="2%"><input type="checkbox"></th>--%>
                <th width="8%">应用名称</th>
                <th width="8%">应用状态</th>
                <%--<th width="10%">应用描述</th>--%>
                <th width="12%">链接地址</th>
                <th width="12%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="app" varStatus="status">
                <tr>
                    <%--<td><input type="checkbox"></td>--%>
                    <td>${app.name}</td>
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
                    <%--<td class="miaoshu">--%>
                            <%--<div>${app.remarks}</div>--%>
                    <%--</td>--%>
                    <td class="lianjie">
                            <p>${app.webUrl}</p>
                    </td>
                    <td>
                        <span onclick="openDialogView('应用查看','${ctx}/app/edit?id=${app.id}','790px','750px');">查看</span>
                        <span class="roles" onclick="window.location.href='${ctx}/school/app/distribution?id=${app.id}&name='+encodeURI(encodeURI('${app.name}'))">分配角色</span>
                        <span class="onlineapp" onclick="openDialog('上线应用','${ctx}/app/online/index?appId=${app.id}','600px','550px');">上线管理</span>
                        <%--<span onclick="alertTips(440,222,'删除应用','确定要删除当前应用吗，删除后，相关的信息也会删除,确定吗？','appDelete(\'${app.id}\')')">删除</span>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="fenye" style="width:100%;">
        <div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
        <div class="fenY" id="fenY">
        </div>
        <%--<div class="fenY2" id="fenY2">--%>
    </div>
</main>
<script type="text/javascript">
    $(function() {
        activeMenuSchool("roll-manage-menu",2);
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/app/index?pageNum="+p+"&pageSize=10";
            }
        });

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