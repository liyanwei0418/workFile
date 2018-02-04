<%@ page contentType="text/html;charset=UTF-8" %>

<%--<nav>--%>
    <%--<div class="container">--%>
        <%--<div class="netdisk-title">教师云盘</div>--%>
        <%--<div class="notice-menu">--%>
            <%--<ul>--%>
               <%----%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</nav>--%>



<style>
    nav > .container div.roll-manage-title:before {
        background: url("${ctxSchool}/images/platmanage.png") no-repeat center center;
        background-size: 100% 100%;
    }
</style>
<nav>
    <div class="container">
        <div class="roll-manage-title">教师云盘</div>
        <div class="roll-manage-menu" style="display: inline-block;">
            <ul style="display: inline;">
                <shiro:hasPermission name="net:disk:view">
                    <li id="tzmenu"><a href="${ctx}/net/disk/index" data="inform-notice">我的云盘</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="net:disk:manage">
                    <li id="lmmenu"><a href="${ctx}/net/disk/manage/index" data="column-manage">云盘管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="net:disk:role">
                    <li id="tzjsmenu"><a href="${ctx}/net/disk/rolefp/index" data="rolls-distribute">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>