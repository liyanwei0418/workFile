<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    span{display:inline-block;}
    .popup-main{
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }
    table td{
        font-size: 13px;
        padding: 10px 0;
    }
    table td span{
        width: 145px;
        text-align: right;
    }
    table td input[type="text"],table td select{
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
<form action="${ctx}/teach/task/course/standard/add" id="standardCourseAdd" method="post" target="hidden_frame">
    <div>
        <input type="hidden" name="id" value="${standardCourse.id}">
        <table>
            <tabody>
                <tr>
                    <td><span>标准课程名称:</span><input type="text" name="name" value="${standardCourse.name}" class="name"></td>
                </tr>
                <tr>
                    <td><span>标准课程英文名称:</span><input type="text" name="englishName" value="${standardCourse.englishName}"></td>
                </tr>
                <tr>
                    <td>
                        <span>科目类型:</span>


                        <select form="standardCourseAdd" name="courseTypeId" style="margin-left: 8px;" class="subjectCategory">
                            <option name="subjectTypeName" value="无" <c:if test="${standardCourse.courseTypeId=='无'}">selected</c:if>>无</option>>
                            <c:forEach items="${courseTypePageInfo.list}" var="courseType" varStatus="status">
                                <option name="subjectTypeName" value="${courseType.id}" <c:if test="${standardCourse.courseTypeId==courseType.id}">selected</c:if>> ${courseType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>是否系统默认:</span>
                        <div style="margin-left: 8px; display: inline-block">
                            <input type="radio" value="0" name="sys" <c:if test="${standardCourse.sys==0}">checked</c:if>>是
                            <input type="radio" value="1" name="sys" <c:if test="${standardCourse.sys==1}">checked</c:if>>否
                        </div>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<span>是否字典学科:</span>--%>
                        <%--<div style="margin-left: 8px; display: inline-block">--%>
                            <%--<input type="radio" value="0" name="isDictionary" <c:if test="${standardCourse.isDictionary==0}">checked</c:if>>是--%>
                            <%--<input type="radio" value="1" name="isDictionary" <c:if test="${standardCourse.isDictionary==1}">checked</c:if>>否--%>
                        <%--</div>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </tabody>
        </table>
    </div>
</form>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
<script>
    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
       if ($(".name").val()==""){
           layer.msg("名称不能为空");
           return;
       }
        $("#standardCourseAdd").submit();
        return true;
    }

    function doSubmitReturn() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var name = $(".name").val();
        var roomType =$(".roomType").find("option:selected").text();
        var courseType =$(".courseType").find("option:selected").text();
        var cycleSemester =$(".cycleSemester").find("option:selected").text();
        var cycleYear =$(".cycleYear").find("option:selected").text();
        if (cycleYear == "" || cycleSemester == "" || name == "" || roomType == ""||name==undefined) {
            webToast("所填项均为必填", "top", 2300);
            return;
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

</script>
</html>
