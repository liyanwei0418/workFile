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
    table .selectCourseClass {
        color: #54ab37;
        background: url(${ctxStaticNew}/images/modify.png) no-repeat left center;
    }

    .standardA {
        color: #fff;
    }

    .roll-teatypemanage a:hover {
        text-decoration: none;
        color: #fff;
    }
</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>

<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="roll-research">
        <%--学年：--%>
        <%--<select name="cycleYear">--%>
        <%--<c:forEach items="${yearList}" var="year">--%>
        <%--<option name="cycleYear"--%>
        <%--<c:if test="${cycleYear ==year}">selected</c:if>  >${year}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--学期：--%>
        <%--<select name="cycleSemester" class="cycleSemester">--%>
        <%--<option name="cycleSemester"--%>
        <%--<c:if test="${cycleSemester ==1}">selected</c:if> value="1">1--%>
        <%--</option>--%>
        <%--<option name="cycleSemester"--%>
        <%--<c:if test="${cycleSemester ==2}">selected</c:if> value="2">2--%>
        <%--</option>--%>
        <%--</select>--%>
            <%@ include file="../common/cycleYearAndSemester.jsp" %>
        </div>

        <div class="roll-operation">
            <button class="roll-add"
                    onclick="openDialog('新增','${ctx}/teach/task/course/pop?type=add&cycleId=${teachCycle.id}','450px','440px');">新增
            </button>
            <%--<button class="roll-add"--%>
            <%--onclick="openDialog('标准课程新增','${ctx}/teach/task/course/standard/add/pop','500px','400px');">标准课程新增--%>
            <%--</button>--%>
        </div>
        <div class="roll-teatypemanage">
            <%--<a onclick="standard()">标准课程管理--%>
            <%--</a>--%>

            <button class=""><a href="${ctx}/teach/task/course/standard/index" class="standardA">标准课程管理</a></button>
        </div>
    </div>
    <%--<div class="stu-num-manage-menu">--%>
    <%--</div>--%>
    <section id="generated" class="row">
        <div class="row">
            <table class="normal check">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>课程名称</th>
                    <th>课程英文名称</th>
                    <th>教室类型</th>
                    <th>标准课程类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${coursePageInfo.list}" var="course" varStatus="status">
                    <tr>
                        <td>${status.index+1+(coursePageInfo.pageNum-1)*10}</td>
                        <td>${course.name}</td>
                        <td>${course.englishName}</td>
                        <td>${course.roomTypeName}<c:if test="${course.roomTypeName==''||course.roomTypeName==null}">无</c:if></td>

                        <td>${course.courseTypeName}
                            <c:if test="${course.courseTypeName==''||course.courseTypeName==null}">无</c:if></td>
                        <td><span onclick="openDialog('编辑课程',
                                '${ctx}/teach/task/course/pop?id=${course.id}&&cycleId=${course.cycleId}','450px','440px');">编辑</span>
                            <span onclick="openDialog('授课班级',
                                    '${ctx}/teach/task/course/class/pop?id=${course.id}'  ,'500px','352px');"
                                  class="selectCourseClass">授课班级</span>
                            <span value="${course.id}"
                                  onclick="alertTips('400px','200px','删除课程','确定要删除${course.name}课程吗？','deleteSure(\'${course.id}\')')"> 删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <div class="fenYDetail">共${coursePageInfo.total}条记录，本页${coursePageInfo.size}条</div>
            <div class="fenY2" id="fenY2"></div>
        </div>
    </section>
</main>
<script>

    <%--function  standard() {--%>
    <%--$.get("${ctx}/teach/task/course/standard/index",{},function (data) {--%>
    <%--})--%>
    <%--}--%>
    activeMenu("base", 3);
    $(function () {
        $("select").change(function () {
            var cycleSemester ="";
            var cycleYear = $("select[name='cycleYear']").val();
            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }
            window.location.href = "${ctx}/teach/task/course/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        $(".fenY2").createPage({
            pageCount:${coursePageInfo.pages},//总页数
            current:${coursePageInfo.pageNum},//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });


        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $(".fenY2go").val();
            if (pageNum <= 0 || pageNum >${coursePageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var semester = $("select[name='semester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/course/index?pageNum=" + $(".fenY2go").val() + "&year=" + cycleYear + "&semester=" + semester;
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

    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    //删除用户
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/course/update", {
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


    function openCourseType(title, url, width, height, target) {
        layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: false, //开启最大化最小化按钮
            content: url,
            scrollbar: false,
            btn: ['关闭'],
            yes: function (index, layero) {
                top.layer.close(index);
            },
            cancel: function (index) {
                top.layer.close(index);
            }
        });
    }
</script>
</body>
</html>