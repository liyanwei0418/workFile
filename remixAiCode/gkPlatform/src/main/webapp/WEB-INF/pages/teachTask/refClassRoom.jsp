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
    #zh-manage .search-box {
        margin-top: 0 !important;
    }

    #zh-manage .search-box ul {
        border: none;
    }

    .stu-num-manage-menu {
        display: inline-block;
    }

    .stu-num-manage-menu ul {
        margin-bottom: 0 !important;
        height: auto !important;
    }

    .roll-operation {
        vertical-align: middle !important;
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
        margin-top: 20px;
    }

    .njUl {
        padding-left: 0 !important;
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

    .refClassroomdel {
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
        <div class="stu-num-manage-menu">
            <ul>
                <%@ include file="../common/cycleYearAndSemester.jsp" %>
                <%--学年:--%>
                <%--<select name="cycleYear" class="cycleYear">--%>
                <%--<c:forEach items="${yearList}" var="year" varStatus="status">--%>
                <%--<option--%>
                <%--<c:if test="${cycleYear eq year}">selected</c:if>--%>
                <%--value="${year}">${year}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--学期：--%>
                <%--学期：--%>
                <%--<select name="cycleSemester" class="cycleSemester">--%>
                <%--<c:forEach items="${semesterList}" var="cycle">--%>
                <%--<option name="cycleSemester"--%>
                <%--<c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if> value="${cycle.cycleSemester}">${cycle.cycleSemester}--%>
                <%--</option>--%>
                <%--</c:forEach>--%>
                <%--&lt;%&ndash;<option name="cycleSemester"&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:if test="${cycleSemester ==1}">selected</c:if> value="1">1&ndash;%&gt;--%>
                <%--&lt;%&ndash;</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option name="cycleSemester"&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:if test="${cycleSemester ==2}">selected</c:if> value="2">2&ndash;%&gt;--%>
                <%--&lt;%&ndash;</option>&ndash;%&gt;--%>
                <%--</select>--%>
            </ul>
        </div>

        <div class="roll-operation">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/ref/class/room/import','500px','350px')"
                    style="float: left">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/ref/class/room/download'" style="float: left">
                下载模板
            </button>
        </div>
    </div>


    <section id="generated" class="row">
        <div class="row anjDiv">
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
                <tr>
                    <th width="5%">序号</th>
                    <th>学段</th>
                    <th>年级</th>
                    <th>班级</th>
                    <th>校区</th>
                    <th>教学楼</th>
                    <th>教室号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="refClassRoom" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${refClassRoom.sectionName}</td>
                        <td>${refClassRoom.nj}</td>
                        <td>${refClassRoom.banji}</td>
                        <td>${refClassRoom.schoolTypeName}</td>
                        <td>${refClassRoom.teachBuildingName}</td>
                        <td>${refClassRoom.roomNum}</td>
                        <td>
                            <span class="edit-td"
                                  onclick="openDialog('编辑','${ctx}/teach/task/ref/class/room/edit/pop?refId=${refClassRoom.refId}','500px','350px')">编辑</span>

                            <span class="refClassroomdel" onclick="alertTips('400px','200px','删除课程','确定要删除任课教师信息吗？','deleteSure(\'${refClassRoom.refId}\')')"> 删除</span>
                        </td>
                            ${building}
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
<script>

    <%--$(function () {--%>
    <%--if ('${cycleSemester}' == "1") {--%>
    <%--$(".cycleSemester1").attr("selected", "selected");--%>
    <%--}--%>
    <%--if ('${cycleSemester}' == "2") {--%>
    <%--$(".cycleSemester2").attr("selected", "selected");--%>
    <%--}--%>
    <%--})--%>
    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/ref/class/room/del", {
            id: id,
            type: "delete",
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

    $(function () {

        $(".fenY2").createPage({
            pageCount: '${pageInfo.pages}',//总页数
            current: '${pageInfo.pageNum}',//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var nj = $('.anjDiv   .active').attr("valueNj");
                var xdId = $('.anjDiv   .active').attr("value");
                window.location.href = "${ctx}/teach/task/ref/class/room/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&nj=" + nj+"&xdId="+xdId;
            }
        });

        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();
            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }
            var nj = $('.anjDiv   .active').attr("valueNj");
            var xdId = $('.anjDiv   .active').attr("value");
            window.location.href = "${ctx}/teach/task/ref/class/room/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&nj=" + nj+"&xdId="+xdId;

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
                window.location.href = "${ctx}/teach/task/ref/class/room/index?pageNum=" + pageNum + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });

    });


    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/ref/class/room/error/export");
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

    function njButton(id, nj) {
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        if (cycleSemester == "" || cycleSemester == null || cycleYear == "" || cycleYear == null) {
            layer.msg("学年和学期数据为空，请先去创建一个教学周期");
            return;
        }
        window.location.href = "${ctx}/teach/task/ref/class/room/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&nj=" + nj + "&&xdId=" + id;
    }
    activeMenu("all", 1);
</script>
</body>
</html>