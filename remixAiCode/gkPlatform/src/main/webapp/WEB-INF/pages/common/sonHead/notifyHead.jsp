<%@ page contentType="text/html;charset=UTF-8" %>

<nav>
    <div class="container">
        <div class="notice-title">通知公告</div>
        <div class="notice-menu">
            <ul>
                <shiro:hasPermission name="notify:notify:view">
                    <li id="tzmenu"><a href="${ctx}/notify/index?pageSize=10" data="inform-notice">通知公告</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="notify:lanmu:view">
                    <li id="lmmenu"><a href="${ctx}/notify/lanmu/index" data="column-manage">栏目管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="notify:role:view">
                    <li id="tzjsmenu"><a href="${ctx}/notify/role/index" data="rolls-distribute">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>