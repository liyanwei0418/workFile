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
        height: 31px;
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
        display: inline-block;
        /*width: 600px;*/
        height: 45px;
        line-height: 44px;
        font-size: 15px;
        color: #777;
        text-align: center;
        text-decoration: none;
    }

    .njUl li {
        float: left
    }

    .njUl a {
        padding: 10px 15px;
        cursor: pointer;
        color: #525252;
        border-bottom: 1px solid #ddd;
    }

    .njUl a:hover {
        text-decoration: none;
        color: #54AB37;
    }

    #zh-manage #generated > div {
        padding: 0 !important;
    }

</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="stu-num-manage-menu" style="display: inline-block">
            学年：
            <select name="cycleYear" class="cycleYear">
                <c:forEach items="${yearList}" var="year">
                    <option name="cycleYear"
                            <c:if test="${cycleYear == year}">selected</c:if> value="${year}">${year}</option>
                </c:forEach>
            </select>
            学期：
            <select name="cycleSemester" class="cycleSemester">
                <c:forEach items="${semesterList}" var="cycle">
                    <option name="cycleSemester" value="${cycle.cycleSemester}"
                            <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if>
                            value="${cycle.cycleSemester}">${cycle.cycleSemester}
                    </option>
                </c:forEach>
            </select>
            <%--冬/夏令时时间段：--%>
            <%--<select name="cycleSemester" class="cycleSemester">--%>
            <%--<option name="cycleSemester" value="夏令时">夏令时</option>--%>
            <%--<option name="cycleSemester" value="冬令时">冬令时</option>--%>
            <%--<option name="cycleSemester" value="冬令时">冬令时</option>--%>
            <%--<option name="cycleSemester" value="冬令时">冬令时</option>--%>
            <%--</select>--%>
        </div>
        <div class="roll-operation">
            <button onclick="openDialog('增设课节','${ctx}/teach/task/node/add/pop','700px','560px')">
                增设课节
            </button>
        </div>

    </div>
    <section id="generated" class="row">
        <div class="row">
            <table class="normal">
                <thead>
                <th width="5%">序号</th>
                <%--<th>早自习开始时间</th>--%>
                <%--<th>下午上课时间</th>--%>
                <%--<th>晚自习上课时间</th>--%>
                <%--<th>最高年级课时数</th>--%>
                <%--<th>最高年级课时数</th>--%>
                <th>作息时间表对应时间段</th>
                <th>作息时间表名称</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="courseNodeInit" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${courseNodeInit.monthStartEnd}</td>
                        <td>${courseNodeInit.monthStartEndName}</td>
                        <td><span class="edit-td"
                                onclick="openDialog('编辑','${ctx}/teach/task/node/add/pop?nodeId=${courseNodeInit.id}','550px','500px');">编辑</span>
                            <span class="detial-td"onclick="openDialog('详情','${ctx}/teach/task/node/detail/pop?nodeId=${courseNodeInit.id}','700px','600px');">详情</span>
                            <sapn class="reset dele-td" value="${teachCycle.id}"
                                  onclick="alertTips(400,202,'删除周期','确定要删除${courseNodeInit.monthStartEndName}吗？','deleteSure(\'${courseNodeInit.id}\')')">
                                删除
                            </sapn>

                        </td>
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
    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
//            var nj = $('.anjDiv   .active').attr("valueNj");
            window.location.href = "${ctx}/teach/task/node?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        $(".fenY2").createPage({
            pageCount:'${pageInfo.pages}',//总页数
            current:'${pageInfo.pageNum}',//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/node?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
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
            if (pageNum <= 0 || pageNum >'${pageInfo.pages}') {
                layer.msg("请输入正确的页码")
            } else {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/node?pageNum=" + $(".fenY2go").val() + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
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
            form.attr("action", "${ctx}/teach/task/room/error/export");
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
        closeAlertDiv();
        $.get("${ctx}/teach/task/node/del", {
            nodeId: id,
        }, function (retJson) {
            window.location.reload();
        });
    }
</script>
</body>
</html>