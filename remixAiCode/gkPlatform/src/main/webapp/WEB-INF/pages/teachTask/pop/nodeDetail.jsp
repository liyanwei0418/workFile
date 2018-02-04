<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>课节设置</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    th, td{
        text-align: left;
        padding: 0 !important;
        padding-left: 10px !important;
    }
    .searchh input {
        float: right;
        height: 31px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }
    table.normal thead {
        background: #f8f8f8;
    }

    /*tab页*/
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }
    .normal{
        width: 80% !important;
        margin: 0 auto;

    }
    main.container{
        border-top: none;
    }
    #zh-manage #generated > div {
        padding: 0 !important;
    }
</style>
<body>
<main class="container" id="zh-manage">
    <section id="generated" class="row">
        <div class="row">
            <table class="normal" border="1" cellpadding="0" cellspacing="0" bordercolor="#ddd">
                <thead>
                <th>节次名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                </thead>
                <tbody>
                <c:forEach items="${courseNodeViewList}" var="courseNodeView" varStatus="status">
                    <tr>
                        <%--<td>${status.count}</td>--%>
                            <td>${courseNodeView.courseNode.nodeName}</td>
                            <%--<td>${courseNodeView.courseNode.cycleYear}</td>--%>
                            <%--<td>${courseNodeView.courseNode.cycleSemester}</td>--%>
                            <%--<td>${courseNodeView.courseNode.morningAfternoon}</td>--%>

                            <td>${courseNodeView.startTime}</td>
                            <td>${courseNodeView.endTime}</td>
                            <%--<td>${courseNodeInit.morningStart}</td>--%>
                            <%--<td>${courseNodeInit.afternoonStart}</td>--%>
                            <%--<td>${courseNodeInit.nightStart}</td>--%>
                        <%--<td>${courseNodeInit.monthStartEnd}</td>--%>
                        <%--<td>${courseNodeInit.monthStartEndName}</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>

</body>
</html>