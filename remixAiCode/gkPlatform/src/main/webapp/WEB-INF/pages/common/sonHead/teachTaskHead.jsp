<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div class="container">
        <div class="roll-manage-title">教务管理</div>
        <div class="roll-manage-menu">
            <ul class="firtmenu">
                <%--<shiro:lacksPermission name="teach:task:base">--%>
                    <%--您没有权限使用该应用，如有需要请联系管理员。--%>
                <%--</shiro:lacksPermission>--%>
                <shiro:hasPermission name="teach:task:base">
                    <li id="base">
                        <a class="down-a">基础管理</a>
                        <ul class="second-menu">
                            <span></span><i></i>
                            <li id="baseMenu1"><a href="${ctx}/teach/task/cycle/index">教学周期</a></li>
                            <li id="baseMenu2"><a href="${ctx}/teach/task/room/index">教室管理</a></li>
                            <li id="baseMenu3"><a href="${ctx}/teach/task/course/index">课程安排</a></li>
                            <li id="baseMenu4"><a href="${ctx}/teach/task/master/index">班主任安排</a></li>
                            <li id="baseMenu5"><a href="${ctx}/teach/task/course/teacher/index">任课教师安排</a></li>
                            <li id="baseMenu6"><a href="${ctx}/teach/task/daily/hour">班级日常课时</a></li>
                            <li id="baseMenu7"><a href="${ctx}/teach/task/node/new">课节设置</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="teach:task:zonghe">
                    <li id="all">
                        <a class="down-a">综合管理</a>
                        <ul class="second-menu">
                            <span></span><i></i>
                            <li><a href="${ctx}/teach/task/ref/class/room/index">班级教室安排</a></li>
                            <li><a href="${ctx}/teach/task/course/hour">科目课时安排</a></li>
                            <li><a href="${ctx}/teach/task/table">课表信息</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="teach:task:role">
                    <li id="role"><a href="${ctx}/teach/task/rolefp/index">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>
<style>
    nav > .container > div > ul > li ul{
        right: 0!important;
    }

    .second-menu li a {
        width: 108px !important;
    }

    .second-menu span {
        position: absolute;
        top: -16px;
        left: 45px;
        border: 8px solid transparent;
        border-bottom-color: #eee;
    }

    .second-menu i {
        position: absolute;
        top: -12px;
        left: 47px;
        border: 6px solid transparent;
        border-bottom-color: #fff;
    }
</style>