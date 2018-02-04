<%@ page contentType="text/html;charset=UTF-8" %>
<style>
    nav > .container div.roll-manage-title:before {
        background: url("${ctxSchool}/images/platmanage.png") no-repeat center center;
        background-size: 100% 100%;
    }
</style>
<nav>
    <div class="container">
        <div class="roll-manage-title">平台管理</div>
        <div class="roll-manage-menu" style="display: inline-block;">
            <ul style="display: inline;">
                <li id="jihou"><a href="${ctx}/school/index">平台管理</a></li>

                <li id="juese"><a href="${ctx}/school/permissionMan">角色管理</a></li>

                <li id="yingyong"><a href="${ctx}/app/index">应用管理</a></li>

                <li id="caidan"><a href="${ctx}/menu/index?pageSize=10">菜单管理</a></li>

                <li id="rizhi"><a href="${ctx}/school/log?pageSize=10">日志管理</a></li>

                <li id="peizhi"><a href="${ctx}/school/config?pageSize=10">配置管理</a></li>

            </ul>
        </div>
    </div>
</nav>