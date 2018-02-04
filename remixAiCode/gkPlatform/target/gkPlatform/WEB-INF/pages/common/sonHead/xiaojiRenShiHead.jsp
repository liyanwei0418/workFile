<%@ page contentType="text/html;charset=UTF-8" %>

<nav>
    <div class="container">
        <div class="roll-manage-title">人事管理</div>
        <div class="roll-manage-menu">
            <ul>
                <shiro:hasPermission name="renShi:renYuan:view">
                    <li id="rymenu" ><a href="${ctx}/renshi/renyuan/index">人员管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="renshi:account:view">
                    <li id="zhmenu"><a href="${ctx}/renshi/account/index">账号管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="renShi:bumen:view">
                    <li id="bumenu"><a href="${ctx}/renshi/bumen/index">部门管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="renShi:zhiwu:view">
                    <li id="zwmenu"><a href="${ctx}/renshi/zhiwu/index">职务信息</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="renShi:role:view">
                    <li id="jsmenu"><a href="${ctx}/renshi/rolefp/index">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>