<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/easyform.js"></script>
</head>
<style>
    span {
        display: inline-block;
    }

    .popup-main {
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }
    table{
        margin-left: 20px;
    }
    table td {
        font-size: 13px;
        text-align: right;
        padding: 8px 0;
    }

    table td span {
        width: 88px;
        text-align: right;
    }

    table td input[type="text"], table td select {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
    }


</style>
<body>
<form action="${ctx}/teach/task/course/update?type=insert" id="courseEdit" method="post">
    <div>
        <table>
            <input type="hidden" value="${course.id}" name="id">
            <tr>
                <td><span>课程名称:</span><input type="text" value="${course.name}" name="name" placeholder="必填" class="name"></td>
            </tr>
            <tr>
                <td><span>课程英文名称:</span><input type="text" name="englishName" value="${course.englishName}" placeholder="必填"  class="englishname"></td>
            </tr>
            <tr>
                <td>
                    <span style="margin-top:9px;">教室类型:</span>
                    <select form="courseEdit" name="roomType">
                        <option name="roomType" value="无">无</option>
                        <c:forEach items="${roomTypeList}" var="room" varStatus="status">
                            <option name="roomType"
                                    <c:if test="${course.roomType == room.id}">selected="selected"</c:if>
                                    value="${room.id}">${room.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <span>标准课程类型:</span>
                    <select form="courseEdit" name="courseType">
                        <option name="courseType" <c:if test="${course.courseType==''||course.courseType==null}">selected</c:if> value="无">无</option>
                        <c:forEach items="${standardCourseList}" var="standardCourse" varStatus="status">
                            <option name="courseType" value="${standardCourse.id}" <c:if test="${standardCourse.id==course.courseType}">selected</c:if>> ${standardCourse.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="margin-top:9px;">学年:</span>
                    <select name="cycleYear" form="courseEdit" class="cycleYear">
                        <c:forEach items="${yearmap}" var="year" varStatus="status">
                            <option name="teachYear"
                                    <c:if test="${cycleYear == year}">selected="selected"</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="margin-top:9px;">学期:</span>
                    <select name="semester" form="courseEdit" class="cycleSemester">
                        <option name="cycleSemester"
                                <c:if test="${cycle.cycleSemester == 1}">selected="selected"</c:if>
                                value="1">1</option>
                        <option name="cycleSemester"
                                <c:if test="${cycle.cycleSemester  == 2}">selected="selected"</c:if>
                                value="2">2</option>
                    </select>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
<script>

    $(function () {
        $(".cycleYear").change(function () {
            $(".cycleSemester").empty();
            var cycleYear = $(".cycleYear").find("option:selected").text();
            $.post("${ctx}/teach/task/course/add/select/change", {
                cycleYear: cycleYear,

            }, function (data) {
                if (data.code == 0) {
                    var teachCycleList = data.data;
                    for(var i=0; i<teachCycleList.length;i++){
                        var cycleSemester = teachCycleList[i].cycleSemester;
                        var html='';
                        html +='<option name="cycleSemester" <c:if test="${cycle.cycleSemester == 1}">selected="selected"</c:if>value="'+cycleSemester+'">'+cycleSemester+'</option>';
                        $('.cycleSemester').append(html);

                    }
                } else {
                    layer.msg("暂无相关数据");
                }
            });
    })



    function doSubmitReturn() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var name = $(".name").val();
        var roomType = $(".roomType").find("option:selected").text();
//        var courseType = $(".courseType").find("option:selected").text();
        var cycleSemester = $(".cycleSemester").find("option:selected").text();
        var cycleYear = $(".cycleYear").find("option:selected").text();
        if (cycleYear == "" || cycleSemester == "" || name == "" || roomType == "" || courseType == "") {
            layer.msg("所填项均为必填");
        }

        $.post("${ctx}/teach/task/course/update", {
            cycleYear: cycleYear,
            cycleSemester: cycleSemester,
            name: name,
            roomType: roomType,
            courseType: courseType,
            type: "insert"
        }, function (data) {
            if (data.code == 0) {
                webToast(data.msg, "top", 5000);
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close(index)
                }, 5000);
                return true;
            } else {
                webToast(data.msg, "top", 5000);
                setTimeout(function () {
                    parent.location.reload();
                }, 5000);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 5000);
                return false;
            }
        });
    }
    })


    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var name = $(".name").val();
        var englishname = $(".englishname").val();
        if (name == "" || englishname == "" || name == null || englishname == null) {
            layer.msg("必填项不能为空");
            return;
        }
//        var reg = "^[A-Za-z]+$";
//        if (!englishname.match(reg)) {
//            layer.msg("数据格式不正确");
//            return;
//        }
        $("#courseEdit").submit();
        return true;
    }

</script>
</html>
