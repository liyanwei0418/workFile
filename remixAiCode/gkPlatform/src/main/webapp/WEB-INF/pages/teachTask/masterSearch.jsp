<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    main{
        font-size: 14px;
    }
    .stu-num-manage-menu > div {
        display: inline-block;
    }
    .stu-num-manage-menu select{
        width: 130px;
        height: 30px;
        border-radius: 2px;
        padding-left: 5px;
        color: #525252;
        font-size: 14px;
    }
    .row {
        margin: auto;
        padding: 0 !important;
    }

    .search-box select {
        height: 30px;
    }

    .searchh {
        float: right;
        /*margin-top: 30px;*/
    }


    .searchh button:after {
        content: '';
        display: inline-block;
        width: 16px;
        height: 16px;
        position: absolute;
        top: 8px;
        left: 10px;
        background: url(../../../assetsNew/images/icon-2.png) no-repeat -1px -65px
    }


    .searchh button {
        float: right;
        height: 30px;
        background: #54ab37;
        width: 35px;
        position: relative;
        border: 1px solid #54ab37;
        border-top-right-radius: 2px;
        border-bottom-right-radius: 2px
    }

    .searchh input {
        float: right;
        height: 30px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }

    #generated {
        padding-top: 20px;
    }

    .but {
        padding-left: 15px;
    }


</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div>
        <div class="stu-num-manage-menu">
            <%@ include file="../common/cycleYearAndSemester.jsp" %>
            <div class="searchh">
                <input type="hidden" id="searchHidden" value="${teacherName}"/>
                <button class="summitButton" onclick="searchTeacher()"></button>
                <input class="searchInput" type="text" name="zhiGong" placeholder="请输入教师姓名"/>
            </div>
        </div>

    </div>
    <section id="generated" class="row">
        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>姓名</th>
                    <th>教职工号</th>
                    <th>安排详情</th>
                    <th>所在学段</th>
                    <th>所在年级</th>
                    <th>所在班级</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bzrViewList}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${BZR.teacherName}</td>
                        <td>${BZR.teacherNo}</td>
                        <td>
                            <c:if test="${BZR.type ==1}">
                                班主任
                            </c:if>
                            <c:if test="${BZR.type ==2}">
                                副班主任
                            </c:if>
                        </td>
                        <td>${BZR.xdName}</td>
                        <td>${BZR.nj}</td>
                        <td>${BZR.className}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%--<div class="fenye" style="padding: 0;">--%>
            <%--<c:if test="${pageInfo!=null&&pageInfo.pages != 0}">--%>
                <%--<div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>--%>
            <%--</c:if>--%>
            <%--<div class="fenY2" id="fenY2">--%>
            <%--</div>--%>
        <%--</div>--%>
    </section>
</main>
<script>
    activeMenu("base",4);
    $(function () {
        if ('${cycleSemester}' == "1") {
            $(".cycleSemester1").attr("selected", "selected");
        }
        if ('${cycleSemester}' == "2") {
            $(".cycleSemester2").attr("selected", "selected");
        }
    })
    $(function () {
        $("select").change(function () {
            var cycleYear = "";
            var cycleSemester = $(".cycleSemester").find("option:selected").val();
            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }
            window.location.href = "${ctx}/teach/task/master/search?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

    });
    function reSetPass(id) {
        $.post("${ctx}/renshi/account/password/update", {
            id: id,
            password: '${password}',
        }, function (retJson) {

        }, "json");
        closeAlertDiv();
        layer.msg('重置成功', {
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        }, function () {
            parent.location.reload();
        });
    }

    function createSure() {
        closeAlertDiv();
        layer.msg('正在生成，请稍侯', {icon: 16, shade: 0.5, time: 100000000});//当生成完成这个对话框才被关掉

        $.ajax({
            type: "post",
            url: "${ctx}/renshi/account/add",
            data: {
                nameRule: $("#nameRule").val(),
                passRule: $("#passRule").val(),
                password: '${password}',
            },
            dataType: "json",
            success: function (data) {
                //alert(data);
            },
            error: function (e) {
                layer.msg('生成完毕', {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    parent.location.reload();
                });
            }
        });
    }

    function searchTeacher() {
        var cycleSemester = $("select[name='cycleSemester']").val();
        var cycleYear = $("select[name='cycleYear']").val();
        var name = $("input[name='zhiGong']").val();
        if (cycleSemester ==""||cycleSemester ==null ||cycleYear==""||cycleYear==null){
            layer.msg("学年和学期数据为空，查不到您需要的数据");
            return;
        }
        window.location.href = "${ctx}/teach/task/master/search?name=" + encodeURI(encodeURI(name)) + "&&cycleSemester=" + cycleSemester + "&&cycleYear=" + cycleYear;
    }
</script>
</body>
</html>