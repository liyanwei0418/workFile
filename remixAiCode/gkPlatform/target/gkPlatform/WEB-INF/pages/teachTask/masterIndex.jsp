<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    main {
        font-size: 14px;
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

    .row {
        margin: 0;
    }

    .but {
        padding-left: 15px;
    }

    #zh-manage .stu-num-manage-menu {
        margin-top: 0;
    }

    /*tab页*/
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    body {
        font: 12px/1.5 Tahoma;
    }

    #outer {
        width: 450px;
        margin: 150px auto;
    }

    #tab {
        overflow: hidden;
        zoom: 1;
        background: #000;
        border: 1px solid #000;
    }

    #tab li {
        float: left;
        color: #fff;
        height: 30px;
        cursor: pointer;
        line-height: 30px;
        padding: 0 20px;
    }

    #tab li.current {
        color: #000;
        background: #ccc;
    }

    #content {
        border: 1px solid #000;
        border-top-width: 0;
    }

    #content ul {
        line-height: 25px;
        display: none;
        margin: 0 30px;
        padding: 10px 0;
    }

    .anjDiv ul li a.active {
        color: #54AB37;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }

    .anjDiv {
        font-size: 15px;
        color: #777;
        text-align: center;
        text-decoration: none;
    }

    .njUl {
        height: 45px;
        border-bottom: 1px solid #ddd;
        margin-bottom: 0;
    }

    .njUl li {
        float: left
    }

    .njUl a {
        display: inline-block;
        width: 110px;
        height: 45px;
        line-height: 44px;
        cursor: pointer;
        color: #525252;
    }

    .njUl a:hover {
        text-decoration: none;
        color: #54AB37;
    }

    #zh-manage #generated > div {
        padding: 0 !important;
    }

    .masterDel {
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
        <div class="stu-num-manage-menu" style="display: inline-block">
            <%@ include file="../common/cycleYearAndSemester.jsp" %>
            <%--学年：--%>
            <%--<select name="cycleYear" class="cycleYear">--%>
            <%--<c:forEach items="${yearList}" var="year">--%>
            <%--<option name="cycleYear"--%>
            <%--<c:if test="${cycleYear == year}">selected</c:if> value="${year}">${year}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--学期：--%>
            <%--<select name="cycleSemester" class="cycleSemester">--%>
            <%--<c:forEach items="${semesterList}" var="cycle">--%>
            <%--<option name="cycleSemester"--%>
            <%--<c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if> value="${cycle.cycleSemester}">${cycle.cycleSemester}--%>
            <%--</option>--%>
            <%--</c:forEach>--%>
            <%--&lt;%&ndash;<option name="cycleSemester" <c:if test="${cycleSemester ==1}">selected</c:if> value="1">1</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option name="cycleSemester" <c:if test="${cycleSemester ==2}">selected</c:if> value="2">2</option>&ndash;%&gt;--%>
            <%--</select>--%>
        </div>

        <div class="roll-operation">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/master/import','500px','350px')">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/master/moban/download'">下载模板</button>
        </div>
        <div class="searchh" style="display: inline-block;float: right;">
            <input type="hidden" id="searchHidden" value="${teacherName}"/>
            <button class="summitButton" onclick="searchTeacher()"></button>
            <input class="searchInput" type="text" name="zhiGong" placeholder="请输入教师姓名"/>
        </div>
    </div>
    <section id="generated" class="row">

        <div class="anjDiv">
            <ul class="njUl">
                <c:forEach items="${classSectionList}" var="classSection">
                    <c:if test="${classSection.sectionYear == 6}">
                        <li><a value="${classSection.id}" value="${classSection.id}"
                               onclick="njButton('${classSection.id}',1)" valueNj="1"
                               <c:if test="${xdId==classSection.id && nj==1}">class="active"</c:if>>小学一年级</a>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)"
                               <c:if test='${xdId==classSection.id && nj==2}'>class="active"</c:if>
                               valueNj="2">小学二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)"
                               <c:if test='${xdId==classSection.id &&nj==3}'>class="active"</c:if> valueNj="3">小学三年级</a>
                        </li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',4)"
                               <c:if test="${xdId==classSection.id &&nj==4}">class="active"</c:if> valueNj="4">小学四年级</a>
                        </li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',5)"
                               <c:if test="${xdId==classSection.id && nj==5}">class="active"</c:if>
                               valueNj="5">小学五年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',6)"
                               <c:if test="${xdId==classSection.id &&nj==6}">class="active"</c:if> valueNj="6">小学六年级</a>
                        </li>
                    </c:if>
                    <c:if test="${classSection.sectionYear == 3 &&classSection.name=='初中' }">
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',1)"
                               <c:if test="${xdId==classSection.id && nj==1}">class="active"</c:if>
                               valueNj="1">初中一年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)"
                               <c:if test="${xdId==classSection.id && nj==2}">class="active"</c:if>
                               valueNj="2">初中二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)"
                               <c:if test="${xdId==classSection.id && nj==3}">class="active"</c:if>
                               valueNj="3">初中三年级</a></li>
                    </c:if>
                    <c:if test="${classSection.sectionYear == 3 &&classSection.name=='高中' }">
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',1)"
                               <c:if test="${xdId==classSection.id && nj==1}">class="active"</c:if>
                               valueNj="1">高中一年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)"
                               <c:if test="${xdId==classSection.id && nj==2}">class="active"</c:if>
                               valueNj="2">高中二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)"
                               <c:if test="${xdId==classSection.id && nj==3}">class="active"</c:if>
                               valueNj="2">高中三年级</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="row">
            <table class="normal">
                <thead>
                <th width="5%">序号</th>
                <th width="15%">班级</th>
                <th width="20%">班主任</th>
                <th width="20%">副班主任</th>
                <th width="30%">操作</th>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>

                        <td>${BZR.className}</td>
                        <td>${BZR.teacherName}</td>
                        <td>${BZR.deputymasterName}</td>
                        <td><span
                                <%--onclick="openDialog('编辑','${ctx}/teach/task/master/edit/pop?classId=${BZR.classId}&&deputyName='+encodeURI(encodeURI('${BZR.deputymasterName}'))+'&&master='+encodeURI(encodeURI('${BZR.masterName}'))+'&&teacherId=${BZR.teacherId}&&deputyIds=${BZR.deputyIds}&&cycleId=${BZR.cycleId}','1100px','90%');"--%>
                                onclick="openDialog('编辑','${ctx}/teach/task/master/edit/pop?classId=${BZR.classId}'+'&&cycleId=${BZR.cycleId}','1000px','800px');"
                        >编辑</span>
                            <%--<span--%>
                               <%--class="masterDel"   onclick="alertTips('400px','200px','删除课程','确定要删除该班的班主任信息吗？','deleteSure(\'${BZR.classId}\')')"> 删除</span>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="fenye" style="width:100%;">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            <div class="fenY2" id="fenY2">
            </div>
        </div>

        <%--<div class="fenye" style="width:98.5%;padding-left:15px;">--%>
        <%--<div class="fenYDetail">共${teacherClassPageInfo.total}条记录，本页${teacherClassPageInfo.size}条</div>--%>
        <%--<div class="fenY" id="fenY">--%>
        <%--</div>--%>
        <%--</div>--%>
    </section>
</main>
<script>

    function chooseResult(depratmentIds, depratmentNames) {
        var names = "";
        var departNames = depratmentNames.split(",");
        for (var i = 0; i < departNames.length; i++) {
            if (departNames[i].trim() != "") {
                names += "<span class='receive'>" + departNames[i] + "</span>";
            }
        }
        $("#chooseWhoTell").html(names + " <input type='button' value='更改'>");
        $("#whichDepartMent").val(depratmentIds);

        $("#chooseWhoTell").attr("onclick", "openDialog('选择人员','${ctx}/notify/chooseperson/show?chooseIds=" + depratmentIds + "','950px','620px')");

    }

    //id为sectionId,nj为传入的nj的
    function njButton(id, nj) {
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        if (cycleSemester == "" || cycleSemester == null || cycleYear == "" || cycleYear == null) {
            layer.msg("学年和学期数据为空，请先去创建一个教学周期");
            return;
        }
        var pageNum = $(".current").text();
        window.location.href = "${ctx}/teach/task/master/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&nj=" + nj + "&&sectionId=" + id + "&pageNum=" + pageNum + "&pageSize=10";
    }

    $(function () {
        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();

            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }

            var nj = $('.anjDiv   .active').attr("valueNj");
            window.location.href = "${ctx}/teach/task/master/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&nj=" + nj;
        });

        $(".fenY2").createPage({
            pageCount: '${pageInfo.pages}',//总页数
            current: '${pageInfo.pageNum}',//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var nj = $('.anjDiv   .active').attr("valueNj");
                window.location.href = "${ctx}/teach/task/master/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&nj=" + nj + "&pageSize=10";
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
            if (pageNum <= 0 || pageNum > '${pageInfo.pages}') {
                layer.msg("请输入正确的页码")
            } else {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/master/index?pageNum=" + $(".fenY2go").val() + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });
    });

    function searchTeacher() {
        var cycleSemester = $("select[name='cycleSemester']").val();
        var cycleYear = $("select[name='cycleYear']").val();
        var name = $("input[name='zhiGong']").val();
        if (cycleSemester == "" || cycleSemester == null || cycleYear == "" || cycleYear == null) {
            layer.msg("学年和学期数据为空，查不到您需要的数据");
            return;
        }
        window.location.href = "${ctx}/teach/task/master/search?name=" + encodeURI(encodeURI(name)) + "&&cycleSemester=" + cycleSemester + "&&cycleYear=" + cycleYear;
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
            form.attr("action", "${ctx}/teach/task/master/error/export");
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

    function deleteSure(id) {

        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        $.post("${ctx}/teach/task/master/del", {
            cycleYear: cycleYear,
            cycleSemester:cycleSemester,
            classId:id
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

    activeMenu("base", 4);

</script>
</body>
</html>