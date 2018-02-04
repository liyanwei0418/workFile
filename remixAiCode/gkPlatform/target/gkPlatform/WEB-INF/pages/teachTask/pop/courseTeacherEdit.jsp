<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <%--<script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.8.3.min.js"></script>--%>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <script>
        $(function () {
            var data = ${teacherList};

            $("#autoComplete").autocomplete(data, {
                minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
                max: 100,//下拉显示项目的个数
                autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
                mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
                matchContains: true,
                formatItem: function (row, i, max) {
                    return row.name;

                },
                formatMatch: function (row, i, max) {
                    return row.name;
                },
                formatResult: function (row) {
                    return row.name;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
                }
            });

            $("#autoComplete").bind("input propertychange", function () {
                if ($(this).val().trim() == "") {
                    $(".completeTips").show();
                } else {
                    $(".completeTips").hide();
                }
            });
        })
    </script>
</head>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    ul li{
        font-size: 13px;
        list-style: none;
        margin-top: 20px;
        padding-left: 30px;
    }
    ul li span:first-child{
        display: inline-block;
        width: 80px;
        text-align: right;
        margin-right: 10px;
    }
    input{
        width: 120px;
        height: 28px;
        line-height: 28px;
        margin-right: 10px;
        outline: none;
        padding-left: 5px;
    }

</style>
<body>
    <div>
        <input type="hidden" value="${courseClassId}"class="courseClassId">
        <input type="hidden" value="${gradeClass.id}"  class="classId">
        <input type="hidden" value="${course.id}" class="courseId">
        <input type="hidden" value="${teacher.id}"  class="teacherIdFromHouTai">
        <input type="hidden" value="${cycleId}"  class="cycleId">

        <ul>
            <li><span>年级班级:</span>&nbsp;<span>${classSection.name}${gradeClass.nj}年级${gradeClass.name}</span></li>
            <li><span>科目:</span>&nbsp;<span>${course.name}</span></li>
            <li><span>任课教师:</span>
                <div style="display: inline-block">
                    <input id="autoComplete" name="teacherName" value="${teacher.name}"/><span class="completeTips">请输入系统中存在的信息！</span>
                    <input type="hidden" name="teacherId" id="personId"/>
                </div>
            </li>
        </ul>
    </div>
    <div class="suggest" id="search-suggest" style="display: none">
        <ul id="search-result">
            <li></li>
        </ul>
    </div>
</body>
<script>
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var autoComplete = $("#autoComplete").val();
        var classId = $(".classId").val();
        var courseId = $(".courseId").val();
        var teacherIdFromHouTai =$(".teacherIdFromHouTai").val();
        var teacherId = $("#personId").val();
        var courseClassId = $(".courseClassId").val();
        var cycleId = $(".cycleId").val();
        if (autoComplete==""){
            alert("请选择授课老师");
        }
        $.post("${ctx}/teach/task/course/teacher/edit", {
            classId:classId,
            teacherIdFromHouTai:teacherIdFromHouTai,
            courseId:courseId,
            refId:courseId,
            teacherId:teacherId,
            courseClassId:courseClassId,
            cycleId:cycleId
        }, function (data) {

        })
        return true;
    }
</script>
</html>
