<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    .row {
        margin: auto;
        padding: 0 !important;
    }

    .addbutton {
        margin-top: 30px;
        height: 30px;
        padding: 0 15px;
        border: 1px solid #54ab37;
        background: #54ab37;
        color: #fff;
        border-radius: 2px;
    }
    #ry-manage table tbody td:last-child span:last-child{

    }
    .reset{
        padding-left: 20px;
        cursor: pointer;
        color: #fd3a47;
        background: url(../../../assetsNew/images/icon-delete.png) no-repeat left 2px;
    }
</style>
<body>

<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <section id="generated" class="row">
        <button class="addTeachCycleButton addbutton"
                onclick="openDialog('添加教学周期',
                        '${ctx}/teach/task/cycle/add/pop','650px','550px');">
            添加教学周期
        </button>
        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <%--<th width="5%"><input type="checkbox"></th>--%>
                    <th width="5%">序号</th>
                    <th>学年</th>
                    <th>学期</th>
                    <th>开学日期</th>
                    <th>开始周</th>
                    <th>结束周</th>
                    <th>总周次</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="teachCycle" varStatus="status">
                    <tr>
                        <%--<td><input type="checkbox"></td>--%>
                        <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                        <td>${teachCycle.cycleYear}</td>
                        <td>${teachCycle.cycleSemester}</td>
                        <td>${gukeer:millsToyyyyMMdd(teachCycle.termBeginTime)}</td>
                        <td>${teachCycle.beginDate}</td>
                        <td>${teachCycle.endDate}</td>
                        <td>${teachCycle.weekCount}</td>
                        <td><span onclick="openDialog('编辑周期',
                                '${ctx}/teach/task/cycle/edit/pop?id=${teachCycle.id}&&type=update','550px','420px');">编辑</span>

                            <sapn class="reset" value="${teachCycle.id}"
                                  onclick="alertTips(400,202,'删除周期','确定要删除${teachCycle.cycleYear}第${teachCycle.cycleSemester}学期吗？','deleteSure(\'${teachCycle.id}\')')">
                                删除
                            </sapn>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:100%;">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
<script>
    $(function () {
        $(".fenY2").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/teach/task/cycle/index?pageNum=" + p + "&pageSize=10";
            }
        });

        $('#fenY2goto').click(function () {
            var p = $('#fenY2go').val();
            if (p <= 0 || p >${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/teach/task/cycle/index?pageNum=" + p + "&pageSize=10";
            }

        })
    })
    function deleteSure(id) {
        closeAlertDiv();
        $.post
        ("${ctx}/teach/task/cycle/do", {
            id: id,
            type: "delete",
        }, function (retJson) {
            window.location.reload();
        });
    }

    activeMenu("base",1);
</script>
</body>
</html>