<%@ include file="../common/headerXf.jsp" %>
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
    .add-sontab{
        padding-left: 20px;
        cursor: pointer;
        margin-right: 20px;
        background: url(${ctxStaticNew}/school/images/icon_addtable.png) no-repeat left 3px !important;
    }
</style>
<body>

<%@ include file="../common/sonHead/schoolHead.jsp" %>

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('菜单新增','${ctx}/menu/add','750px','600px');">新增</button>

        </div>
        <div class="roll-research" style="float: right;">
            <button class="summitButton" id="search"></button>
            <input type="hidden" id="searchHidden" value="">
            <input class="searchInput" name="menuName" type="text" placeholder="请输入菜单名称"/>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="8%">标题</th>
                <th width="8%">路径</th>
                <th width="14%">权限标识</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${menuList}" var="menu">
                <tr>
                    <td>${menu.name}</td>
                    <td>${menu.href}</td>
                    <td>${menu.permission}</td>
                    <td>
                        <span onclick="openDialog('菜单新增','${ctx}/menu/edit?id=${menu.id}','750px','600px');">编辑</span>
                        <span class="add-sontab" onclick="openDialog('添加子菜单','${ctx}/menu/addSub?parentId=${menu.id}','750px','600px');">添加子菜单</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <!--分页按钮组  -->


            <div class="fenye" style="width:100%;">
                <div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
                <div class="fenY" id="fenY">
                </div>
            </div>
        </div>


    </div>

</main>
<script type="text/javascript">
    $(function() {
        activeMenuSchool("roll-manage-menu",3);

        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/menu/index?pageNum="+p+"&pageSize=10";
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var menuName=$("input[name='menuName']").val();
            window.location.href="${ctx}/menu/index?menuName="+encodeURI(encodeURI(menuName))+"&pageSize=10";

        });
    })
</script>
</body>
</html>