<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="stu-num-manage-menu">
        <select  name="teachYear">
            <c:forEach items="${teachCyclePageInfo.list}" var="teachCycle">
                <option value="普通课程" name="subjectType">${teachCycle.teach_year}</option>
            </c:forEach>
        </select>
        <select  name="semester">
            <c:forEach items="${teachCyclePageInfo.list}" var="teachCycle">
                <option value="普通课程" name="subjectType">${teachCycle.semester}</option>
            </c:forEach>
        </select>
        <ul>
            <c:forEach items="${courseNameList}" var="courseName">
                <li><a href="#" data="generated" class="active" >${courseName}</a></li>
            </c:forEach>

        </ul>


    </div>
    <section id="generated" class="row">
        <%--<div class="row">--%>
        <%--<input type="hidden" id="searchHidden" value="${teacherName}"/>--%>
        <%--<button class="summitButton"></button>--%>
        <%--<input class="searchInput" type="text" name="zhiGong" placeholder="请输入职工姓名"/>--%>
        <%--</div>--%>
        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>班级</th>
                    <th>班主任</th>
                    <th>副班主任</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th width="5%">1</th>
                    <th>二班</th>
                    <th>罗斯</th>
                    <th>杰克</th>
                    <th>编辑</th>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <c:if test="${gukeer:notEmptyString(pageInfoHave.pages)}">
                <div class="fenYDetail">共${pageInfoHave.total}条记录，本页${pageInfoHave.size}条</div>
            </c:if>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
    <section id="not-generate" class="row">
        <div class="row">
            <table class="normal">
                <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>教职工编号</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teacherListNo}" var="teacher" varStatus="status">
                    <tr>
                        <td>${status.index+1+(pageInfoNo.pageNum-1)*10}</td>
                        <td>${teacher.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${teacher.gender==1}">
                                    男
                                </c:when>
                                <c:when test="${teacher.gender==2}">
                                    女
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${teacher.no}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye">
            <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            </c:if>
            <div class="fenY1" id="fenY1">
            </div>
        </div>
    </section>
</main>
<script>
    var name = $("#searchHidden").val();
    $(function () {
        <c:if test="${pageInfoHave.pages != 0}">
        $(".fenY2").createPage({
            pageCount:${pageInfoHave.pages},//总页数
            current:${pageInfoHave.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/area/account/index?pageNumHave=" + p + "&pageSizeHave=10&teacherName=" + encodeURI(encodeURI(name));
            }
        });
        </c:if>
        <c:if test="${pageInfoNo.pages != 0}">
        $(".fenY1").createPage({
            pageCount:${pageInfoNo.pages},//总页数
            current:${pageInfoNo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/area/account/index?pageNumNo=" + p + "&pageSizeNo=10&whichPage=1";
            }
        });
        </c:if>
        <c:if test="${gukeer:notEmptyString(whichPage)}">
        $('.stu-num-manage-menu a').removeClass('active');
        $("#noGener").addClass('active');
        var data = $("#noGener").attr('data');
        var sections = $('#' + data);
        $('#' + sections[0].id).show();
        $('#' + sections[0].id).siblings('section').hide();
        </c:if>


        /*查询搜索*/
        $(".summitButton").click(function () {
            var name = $("input[name='zhiGong']").val();
            window.location.href = "${ctx}/area/account/index?teacherName=" + encodeURI(encodeURI(name));
        });
        //搜索框关键字回显
        $("input[name='zhiGong']").val($("#searchHidden").val());

        $('#fenY2goto').click(function () {
            var p = $('#fenY2go').val();
            if (p <= 0 || p >${pageInfoHave.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/area/account/index?pageNumHave=" + p + "&pageSizeHave=10&teacherName=" + encodeURI(encodeURI(name));
            }

        })

        $('#fenY1goto').click(function () {
            var p = $('#fenY1go').val();
            if (p <= 0 || p >${pageInfoNo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/area/account/index?pageNumNo=" + p + "&pageSizeNo=10&whichPage=1";
            }
        })

    })

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
</script>
</body>
</html>