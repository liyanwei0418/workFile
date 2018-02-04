<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div class="container">
        <div class="roll-manage-title">区级人事管理</div>
        <div class="roll-manage-menu">
            <ul>
                <shiro:hasPermission name="renShi:area:quji">
                    <li id="quji">
                        <a>区级管理</a>
                        <ul class="second-menu">
                            <li><a href="${ctx}/area/renyuan/index">人员管理</a></li>
                            <li><a href="${ctx}/area/account/index">账号管理</a></li>
                            <li><a href="${ctx}/area/bumen/index">部门管理</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="renShi:area:xiao">
                    <li id="xiaoji">
                        <a>校级管理</a>
                        <ul class="second-menu">
                            <li><a href="${ctx}/area/school/person/index">人员管理</a></li>
                            <li><a href="${ctx}/area/school/person/account/index">账号管理</a></li>
                            <li><a href="${ctx}/area/school/department/index">部门管理</a></li>
                            <li><a href="${ctx}/area/school/zhiwu/index">职务管理</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="renShi:area:birt">
                    <li id="tongji"><a href="${ctx}/area/birt/index">统计报表</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="renShi:area:role">
                    <li id="role"><a href="${ctx}/area/rolefp/index">角色分配</a></li>
                </shiro:hasPermission>

            </ul>
        </div>
    </div>
</nav>