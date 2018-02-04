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
        color: #54ab37;
    }
</style>

<body>

<%@ include file="../common/sonHead/schoolHead.jsp" %>
<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增配置','${ctx}/school/config/add','450px','300px');">新增
            </button>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="14%">类别</th>
                <th width="12%">键</th>
                <th width="16%">值</th>
                <th width="12%">操作</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${pageInfo.list}" var="config" varStatus="status">

                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${config.paramType}</td>
                        <td>${config.paramKey}</td>
                        <td>${config.paramValue}</td>
                        <td>
                            <span onclick="openDialog('修改配置','${ctx}/school/config/add?id=${config.id}','450px','300px');">编辑</span>
                            <span onclick="alertTips(400,222,'删除配置','确定要删除当前配置吗？','configDelete(${config.id})')">删除</span>
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
            <%--<div class="fenY2" id="fenY2">--%>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            activeMenuSchool("roll-manage-menu",5);
            $(".fenY").createPage({
                pageCount:${pageInfo.pages},//总页数
                current:${pageInfo.pageNum},//当前页面
                backFn: function (p) {
                    window.location.href = "${ctx}/school/index?pageNum=" + p + "&pageSize=10";
                }
            });

        });

        function configDelete(id) {
            closeAlertDiv();
            $.post("${ctx}/school/config/delete", {
                id: id,
            }, "json");
            window.location.reload();
        }

    </script>
</main>
</body>
</html>