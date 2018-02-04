<%@ page contentType="text/html;charset=UTF-8" %>

<nav>
    <div class="container">
        <div class="roll-manage-title">区级学籍管理</div>
        <div class="roll-manage-menu">
            <ul>

                <shiro:hasPermission name="class:area:stuinfo">
                    <li id="stuInfoMenu"><a href="${ctx}/area/class/stuinfo/index">学生信息</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:area:parinfo">
                    <li id="parInfoMenu"><a href="${ctx}/area/class/parinfo/index">家长信息</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:area:birt">
                    <li id="birtMenu"><a href="${ctx}/area/class/birt/index">统计报表</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:area:role">
                    <li id="roleMenu"><a href="${ctx}/area/class/rolefp/index">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>