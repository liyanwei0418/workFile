<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    button{
        width: 65px;
        height: 28px;
        line-height: 28px;
        background: #54AB37;
        border-radius: 2px;
        border: 1px solid #54AB37;
        color: #fff;
    }
    table{
        margin-top: 20px;
        color: #525252;
        width: 100%;
    }
    td{
        font-size: 13px;
        line-height: 30px;
        padding-left: 5px;
    }
    div{
        padding: 20px;
    }
</style>
<body>


<div>
    <button onclick="courseTeacherExport()">导出</button>
    <table border="1" cellspacing="0" cellpadding="0" bordercolor="#ddd">
    <c:forEach items="${coureClassViewList}" var="courseClassView" varStatus="status">

        <tr>
            <input type="hidden" value="${courseClassView.courseId}">
            <input type="hidden" value="${courseClassView.classId}">
            <input type="hidden" value="${courseClassView.teacherId}">

            <td width="10%">${status.count}</td>
            <td>${courseClassView.classSection}${courseClassView.nj}年级${courseClassView.className}</td>
            <td>${courseClassView.courseName}</td>
            <td>${courseClassView.teacherName}</td>
        </tr>


    </c:forEach>
    <form action="${ctx}/teach/task/course/teacher/export" method="post" id="export">
        <input type="hidden" name="courseClassViews" class="courseClassViews" value=${json}>
    </form>
    </table>
</div>
</body>
<script>
    function courseTeacherExport() {
        var json = $(".courseClassViews").val();
        $("#export").submit();
    }
</script>
</html>
