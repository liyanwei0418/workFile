<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>同步上一学期数据</title>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
</head>
<body>
<div>
    <input type="hidden" value="${cycleId}" class="cycleId">
    <input type="checkbox" class="room" value="教室管理"><span>教室管理</span>
    <input type="checkbox"  class="course" value="课节设置"><span>课节设置</span>
    <input type="checkbox"  class="course" value="课程安排"><span>课程安排</span>
    <input type="checkbox" class="master" value="班主任安排"><span>班主任安排</span>
    <input type="checkbox" class="courseTeacher" value="任课教师安排"><span>任课教师安排</span>
    <input type="checkbox" class="room" value="班级教室安排"><span>班级教室安排</span>
    <input type="checkbox" class="room" value="科目课时安排"><span>科目课时安排</span>
</div>
</body>
<script>
    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var synInfo = "";
        var inputs =  $('input[type=checkbox]:checked');
        console.log(inputs.length);
        $('input[type=checkbox]:checked').each(function (i) {
            var oneText = $(this).val();
            synInfo +=","+oneText;
        })
        var cycleId = $(".cycleId").val();

        $.post("${ctx}/teach/task/cycle/syn", {
            synInfo:synInfo,
            cycleId:cycleId
        }, function (data) {

        })
        return true;;
    }
</script>
</html>
