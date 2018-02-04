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
    }

    #generated {
        padding-top: 20px !important;
    }

    #generated select{
        height: 30px;
        width: 130px;
        border-radius: 2px;
        padding-left: 5px;
        color: #525252;
        font-size: 14px;
        outline: none;
    }

    .search-box select {
        height: 30px;
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

    #zh-manage #generated > div:first-child {
        overflow: inherit;
    }
    #zh-manage #generated > div:nth-child(2) {
        padding: 0;
    }
    .but {
        padding-left: 15px;
    }

    .gobefore, .goafter{
        vertical-align: middle;
        width: 20px;
        height: 20px;
        margin-top: 20px;
        cursor: pointer;
    }

    /*.anjDiv{*/
        /*overflow: hidden;*/
        /*width: 1100px;*/
        /*margin: 0 auto;*/
        /*display: inline-block;*/
        /*vertical-align: middle;*/
    /*}*/

    /*.anjDiv ul li a.active {*/
        /*color: #54AB37;*/
        /*!*border: 1px solid #ddd;*!*/
        /*border-bottom: 0;*/
        /*background: #fff;*/
    /*}*/
    /*.anjDiv {*/
        /*font-size: 15px;*/
        /*color: #777;*/
        /*text-align: center;*/
        /*text-decoration: none;*/
        /*margin-top: 20px;*/
    /*}*/
    /*.njUl {*/
        /*padding-left: 0 !important;*/
        /*height: 45px;*/
        /*!*border-bottom: 1px solid #ddd;*!*/
        /*margin-bottom: 0;*/
    /*}*/
    /*.njUl li {*/
        /*float: left*/
    /*}*/
    /*.njUl a {*/
        /*display: inline-block;*/
        /*width: 110px;*/
        /*height: 45px;*/
        /*line-height: 44px;*/
        /*cursor: pointer;*/
        /*color: #525252;*/
    /*}*/
    /*.njUl a:hover {*/
        /*text-decoration: none;*/
        /*color: #54AB37;*/
    /*}*/

    .class-containt span {
        float: left;
        padding: 10px 15px;
        cursor: pointer;
        color: #525252;
        border-bottom: 1px solid #ddd;
    }

    .class-containt span:hover {
        color: #54AB37;
    }
    .editor{
        padding-left: 20px;
        cursor: pointer;
        padding-left: 20px;
    }

    .courseTeacherDel {
        padding-left: 20px;
        cursor: pointer;
        color: #fd3a47;
        background: url(${ctxStaticNew}/images/icon-delete.png) no-repeat left 3px;
    }
</style>

<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <%@ include file="../common/cycleYearAndSemester.jsp" %>
        <div class="roll-operation" style="display: inline-block;">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/course/teacher/import','500px','350px')">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/course/teacher/moban/download'">下载模板</button>
            <%--<button onclick="openDialogView('查看所有任课教师安排','${ctx}/teach/task/course/teacher/all','500px','350px')">查看任课教师安排--%>
            <%--</button>--%>
        </div>
        <div class="row searchh" style="display: inline-block;float: right;">
            <input type="hidden" id="searchHidden" value="${teacherName}"/>
            <button class="summitButton" onclick="searchTeacher()"></button>
            <input class="searchInput" type="text" name="zhiGong" placeholder="请输入教师姓名"/>
        </div>
    </div>
    </div>
    <section id="generated" class="row">
        <%--<span class="gobefore"></span>--%>
        <%--<div class="anjDiv">--%>
            <%--<ul class="njUl">--%>
                <%--<c:forEach items="${courseList}" var="course">--%>
                        <%--<li><a onclick="courseChange('${course.id}')" valCourseId="${course.id}"--%>
                               <%--<c:if test="${course.id==courseId}">class="active"</c:if>>${course.name}</a></li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
            课程：
            <select name="course" class="course">
                <c:forEach items="${courseList}" var="course">
                    <option name="cycleYear"
                            <c:if test="${course.id==courseId}">selected</c:if> value="${course.id}">${course.name}</option>
                </c:forEach>
            </select>
                学段：
                <select name="xd" class="xd">
                    <c:forEach items="${xd}" var="xd">
                        <option name="cycleYear"
                                <c:if test="${xdId==xd.id}">selected</c:if> value="${xd.id}">${xd.name}</option>
                    </c:forEach>
                </select>
                年级：
                <select name="nj" class="nj">
                    <c:forEach items="${njList}" var="onenj" varStatus="status">
                        <option name="class"
                                <c:if test="${nj==onenj.nj}">selected</c:if> value="${onenj.nj}">${onenj.nj}年级</option>
                    </c:forEach>
                </select>
        <%--</div>--%>
        <%--<span class="goafter"></span>--%>


        <div class="row" style="padding: 0;">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>年级</th>
                    <th>班级</th>
                    <th>任课教师</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${BZR.xdName}${BZR.nj}年级</td>
                        <td>${BZR.className}</td>
                        <td>${BZR.courseTeacher}</td>
                        <td>
                            <span class="editor"
                                onclick="openDialog('编辑','${ctx}/teach/task/course/teacher/edit/pop?xd=${BZR.xdName}&&classId=${BZR.classId}&&teacherId=\
                                    ${BZR.teacherId}&&courseId=${BZR.courseId}&&nj=${BZR.nj}&&refId=${BZR.courseClassId}&cycleId=${cycleId}','450px','300px');"
                                style="float: left;margin-right:20px;">编辑</span>
                            <%--<span class="courseTeacherDel" onclick="alertTips('400px','200px','删除课程','确定要删除任课教师信息吗？','deleteSure(\'${BZR.courseClassId}\')')"> 删除</span>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
</body>
</html>
<script>

    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/course/teacher/del", {
            id: id,
            type: "delete"
        }, function (retJson) {
        }, "json");
        setTimeout(function () {
            layer.msg('删除成功', {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.location.reload();
            });
        }, 300)
    }
    activeMenu("base",5);
    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            var courseId = $("select[name='course']").val();
            var xdId = $("select[name='xd']").val();
            var nj = $("select[name='nj']").val();
            if (nj ==null){
                nj="";
            }
            window.location.href = "${ctx}/teach/task/course/teacher/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&courseId=" + courseId+"&xdId="+xdId+"&nj="+nj;
        });

        $(".fenY2").createPage({
            pageCount:'${pageInfo.pages}',//总页数
            current:'${pageInfo.pageNum}',//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var courseId = $("select[name='course']").val();
                var xdId = $("select[name='xd']").val();
                var nj = $("select[name='nj']").val();
                if (nj ==null){
                    nj="";
                }
                window.location.href = "${ctx}/teach/task/course/teacher/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&courseId=" + courseId + "&pageSize=10&xdId="+xdId+"&nj="+nj;
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $("#fenY2go").val();
            if (pageNum <= 0 || pageNum >'${pageInfo.pages}') {
                layer.msg("请输入正确的页码")
            } else {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var valCourseId = $('.class-containt   .active').attr("valCourseId");
                window.location.href = "${ctx}/teach/task/course/teacher/index?pageNum=" + pageNum + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&courseId=" + valCourseId;
            }
        });
    });

    <%--function courseChange(id) {--%>
        <%--var cycleYear =  $(".cycleYear").find("option:selected").val();--%>
        <%--var cycleSemester = $(".cycleSemester").find("option:selected").val();--%>
        <%--window.location.href = "${ctx}/teach/task/course/teacher/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + id;--%>
    <%--}--%>
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


    function searchTeacher() {
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();

        var name = $(".searchInput").val();
        if (cycleSemester == "" || cycleSemester == null || cycleYear == "" || cycleYear == null) {
            layer.msg("学年和学期数据为空，查不到您需要的数据");
            return;
        }
        window.location.href = "${ctx}/teach/task/course/teacher/search?name=" + encodeURI(encodeURI(name)) + "&&cycleSemester=" + cycleSemester + "&&cycleYear=" + cycleYear;
    }

    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/course/teacher/error/export");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "msg");
            input1.attr("value", JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function () {
            window.location.reload(true);
        });
    }
</script>
<script>
    var num_a = $('.njUl a').length;
    var c = parseInt(num_a/10);
    $('.njUl').width(num_a*110);
    $('.goafter').on('click', function () {
        var Left = parseInt($('.njUl').css('marginLeft'));
        if(Left == c*-1100) return;
        Left = Left - 1100;
        $('.njUl').css('marginLeft', Left + 'px');
    });
    $('.gobefore').on('click', function () {
        var Left = parseInt($('.njUl').css('marginLeft'));
        if(Left == 0) return;
        Left = Left + 1100;
        $('.njUl').css('marginLeft', Left + 'px');
    });
    <%--$(".acourse").click(function () {--%>
    <%--$(this).addClass('active').siblings().removeClass("active");--%>
    <%--var cycleYear = $(".cycleYear").find("option:selected").val();--%>
    <%--var cycleSemester = $(".cycleSemester").find("option:selected").val();--%>
    <%--var courseId = $(this).attr("va");--%>
    <%--window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + courseId;--%>
    <%--var index_a = $(".acourse").index($(this));--%>
    <%--var n = parseInt((index_a + 1) / 10);--%>
    <%--alert(n);--%>
    <%--$('.njUl').css('marginLeft', n * 1100 + 'px');--%>
<%--});--%>
</script>