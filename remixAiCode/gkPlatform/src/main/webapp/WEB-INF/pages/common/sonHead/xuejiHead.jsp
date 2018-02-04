<%@ page contentType="text/html;charset=UTF-8" %>

<nav>
    <div class="container">
        <div class="roll-manage-title">学籍管理</div>
        <div class="roll-manage-menu">
            <ul>
                <shiro:hasPermission name="class:student:view">
                    <li id="stuManMenu"><a href="${ctx}/class/index">学生管理</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:banji:view">
                    <li id="classManMenu"><a href="${ctx}/class/banji/index">班级管理</a></li>
                </shiro:hasPermission>

                <li id="parInfoMenu"><a href="${ctx}/class/parent/info/index">家长信息</a></li>

               <%-- <li id="teaManMenu"><a href="${ctx}/class/teacherarrangement/index">教师安排</a></li>--%>

                <shiro:hasPermission name="class:schoolSetting:view">
                    <li id="schoolSetMenu"><a href="${ctx}/class/schoolsetting/index">学校设置</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:xueDuan:view">
                    <li id="sectionManMenu"><a href="${ctx}/class/xueduan/index">学段管理</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:stuAccount:view">
                    <li id="stuAccMenu"><a href="${ctx}/class/stuaccount/index">学生账号管理</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:parAccount:view">
                    <li id="parAccMenu"><a href="${ctx}/class/paraccount/index">家长账号管理</a></li>
                </shiro:hasPermission>

                <shiro:hasPermission name="class:role:view">
                    <li id="xjjsMenu"><a href="${ctx}/class/rolemanage/index">角色管理</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>