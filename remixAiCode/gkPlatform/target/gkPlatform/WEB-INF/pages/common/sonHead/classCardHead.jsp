<%@ page contentType="text/html;charset=UTF-8" %>

<nav>
    <div class="container">
        <div class="roll-manage-title">智慧班牌</div>
        <div class="roll-manage-menu">
            <%--<ul>--%>
            <%--<shiro:hasPermission name="class:student:view">--%>
            <%--<li id="stuManMenu"><a href="${ctx}/class/index">终端管理</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="class:banji:view">--%>
            <%--<li id="classManMenu"><a href="${ctx}/class/banji/index">班级管理</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<li id="parInfoMenu"><a href="${ctx}/class/parent/info/index">家长信息</a></li>--%>

            <%--&lt;%&ndash; <li id="teaManMenu"><a href="${ctx}/class/teacherarrangement/index">教师安排</a></li>&ndash;%&gt;--%>

            <%--<shiro:hasPermission name="class:schoolSetting:view">--%>
            <%--<li id="schoolSetMenu"><a href="${ctx}/class/schoolsetting/index">学校设置</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="class:xueDuan:view">--%>
            <%--<li id="sectionManMenu"><a href="${ctx}/class/xueduan/index">学段管理</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="class:stuAccount:view">--%>
            <%--<li id="stuAccMenu"><a href="${ctx}/class/stuaccount/index">学生账号管理</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="class:parAccount:view">--%>
            <%--<li id="parAccMenu"><a href="${ctx}/class/paraccount/index">家长账号管理</a></li>--%>
            <%--</shiro:hasPermission>--%>

            <%--<shiro:hasPermission name="class:role:view">--%>
            <%--<li id="xjjsMenu"><a href="${ctx}/class/rolemanage/index">角色管理</a></li>--%>
            <%--</shiro:hasPermission>--%>
            <%--</ul>--%>

            <ul class="firtmenu">
                <li id="base">
                    <a class="down-a">终端管理</a>
                    <ul class="second-menu">
                        <span></span><i></i>
                        <shiro:hasPermission name="classCard:equipment:view">
                            <li id="baseMenu1"><a href="${ctx}/classcard/index">设备管理</a></li>
                            <li id="baseMenu2"><a href="${ctx}/classcard/config/index">开关机配置</a></li>
                            <li id="baseMenu4"><a href="${ctx}/model/index">模块管理</a></li>
                            <li id="baseMenu3"><a href="${ctx}/classcard/custom/index">自定义界面</a></li>
                        </shiro:hasPermission>
                    </ul>
                </li>
                <li id="all">
                    <a class="down-a">菜单管理</a>
                    <ul class="second-menu">
                        <span></span><i></i>
                        <shiro:hasPermission name="classCard:notify:view">
                            <li><a href="${ctx}/classcard/notify/index">通知公告</a></li>
                        </shiro:hasPermission>

                        <shiro:hasPermission name="classCard:classspace:view">
                            <li><a href="${ctx}/classcard/introduction/index">班级空间</a></li>
                        </shiro:hasPermission>

                        <li><a href="${ctx}/classcard/schoolculture">校园文化</a></li>
                    </ul>
                </li>
                <shiro:hasPermission name="classCard:role:view">
                    <li id="role"><a href="${ctx}/classcard/role/index?appId=${appId}">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>
<style>
    .down-a{
        display: inline-block;
        width: 90px;
        padding-right: 20px;
        text-align: center;
    }
    nav > .container > div > ul{
        overflow: inherit !important;

    }
    a{
        font-size: 14px !important;
    }
    nav > .container .roll-manage-menu ul {
        overflow: inherit !important;
    }
    nav > .container .roll-manage-menu ul li>ul>li {
        float: left;
        margin-left: 0;
    }
    nav > .container .roll-manage-menu ul li>ul>li a {
        display: inline-block;
        width: 90px;
        text-align: center;
    }
    nav > .container > div > ul > li {
        position: relative;
    }
    nav > .container > div > ul > li ul {
        position: absolute;
        z-index: 999;
        border: 1px solid #eee;
        background: #fff;
        right: 0;
        display: none;
        padding: 5px 0;
        box-shadow: 0.2px 0.2px 1px 1px #eee;
    }

    .firtmenu>li>a{
        line-height: 60px !important;
    }
    .container{
        padding: 0 !important;
    }
    nav > .container div.roll-manage-title{
        margin-top: 16px;
    }
    .second-menu span{
        width: 0 !important;
        position: absolute;
        top: -16px;
        left: 38px;
        border: 8px solid transparent;
        border-bottom-color: #eee;
        z-index: 100;
    }
    .second-menu i{
        width: 0 !important;
        position: absolute;
        top: -12px;
        left: 40px;
        border: 6px solid transparent;
        border-bottom-color: #fff;
        z-index: 101;
    }
    li a:hover{
        color: #54AB37 !important;
    }
</style>