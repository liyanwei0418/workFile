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
        padding: 0 !important;
    }

    .njUl{
        padding-left: 0 !important;
        height: 45px;
        border-bottom: 1px solid #ddd;
        margin-bottom: 0;
    }

    .njUl li {
        float: left
    }
    .njUl a{
        display: inline-block;
        width: 110px;
        height: 45px;
        line-height: 44px;
        cursor: pointer;
        color: #525252;
    }
    .njUl a:hover{
        text-decoration: none;
        color: #54AB37;
    }

    #zh-manage #generated > div{
        padding: 0 !important;
    }
    .dele-sp{
        padding-left: 20px;
        cursor: pointer;
        color: #fd3a47;
        background: url(../../../assetsNew/images/icon-delete.png) no-repeat left 2px;
    }
</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box" style="height: 30px;">
        <div class="roll-research">
            <%@ include file="../common/cycleYearAndSemester.jsp" %>
            <%--学年：--%>
            <%--<select name="cycleYear" class="cycleYear">--%>
                <%--<c:forEach items="${yearList}" var="year">--%>
                    <%--<option name="cycleYear"--%>
                            <%--<c:if test="${cycleYear ==year}">selected</c:if>  >${year}</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
            <%--学期：--%>
            <%--<select name="cycleSemester" class="cycleSemester">--%>
                <%--<c:forEach items="${semesterList}" var="cycle">--%>
                    <%--<option name="cycleSemester"--%>
                            <%--<c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if> value="${cycle.cycleSemester}">${cycle.cycleSemester}--%>
                    <%--</option>--%>
                <%--</c:forEach>--%>
                <%--<option name="cycleSemester"--%>
                        <%--<c:if test="${cycleSemester ==1}">selected</c:if> value="1">1--%>
                <%--</option>--%>
                <%--<option name="cycleSemester"--%>
                        <%--<c:if test="${cycleSemester ==2}">selected</c:if> value="2">2--%>
                <%--</option>--%>
            </select>
        </div>
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增班级日常课时','${ctx}/teach/task/daily/hour/add/pop?nj=${nj}&xdId=${xdId}&cycleSemester=${cycleSemester}&cycleYear=${cycleYear}','700px','500px')">
                新增
            </button>

        </div>
    </div>
    <section id="generated" class="row">

        <div class="anjDiv">
            <ul class="njUl">
                <c:forEach items="${classSectionList}" var="classSection">
                    <c:if test="${classSection.sectionYear == 6}">
                        <li><a value="${classSection.id}" value="${classSection.id}"
                               onclick="njButton('${classSection.id}',1)"  valueNj="1" <c:if test="${xdId==classSection.id && nj==1}" >class="active"</c:if>>小学一年级</a>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)" <c:if test="${xdId==classSection.id && nj==2}">class="active"</c:if> valueNj="2">小学二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)" <c:if test="${xdId==classSection.id &&nj==3}">class="active"</c:if> valueNj="3">小学三年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',4)" <c:if test="${xdId==classSection.id &&nj==4}">class="active"</c:if> valueNj="4">小学四年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',5)" <c:if test="${xdId==classSection.id && nj==5}">class="active"</c:if> valueNj="5">小学五年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',6)" <c:if test="${xdId==classSection.id &&nj==6}">class="active"</c:if> valueNj="6">小学六年级</a></li>
                    </c:if>
                    <c:if test="${classSection.sectionYear == 3 &&classSection.name=='初中' }" >
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',1)" <c:if test="${xdId==classSection.id && nj==1}">class="active"</c:if> valueNj="1">初中一年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)" <c:if test="${xdId==classSection.id && nj==2}">class="active"</c:if> valueNj="2">初中二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)" <c:if test="${xdId==classSection.id && nj==3}">class="active"</c:if> valueNj="3">初中三年级</a></li>
                    </c:if>
                    <c:if test="${classSection.sectionYear == 3 &&classSection.name=='高中' }">
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',1)" <c:if test="${xdId==classSection.id && nj==1}">class="active"</c:if> valueNj="1">高中一年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',2)" <c:if test="${xdId==classSection.id && nj==2}">class="active"</c:if> valueNj="2">高中二年级</a></li>
                        <li><a value="${classSection.id}" onclick="njButton('${classSection.id}',3)" <c:if test="${xdId==classSection.id && nj==3}">class="active"</c:if> valueNj="2">高中三年级</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="row">
            <table class="normal">
                <thead>
                <th width="5%">序号</th>
                <th>班级</th>
                <th>上课天数</th>
                <th>上午课时</th>
                <th>下午课时</th>
                <th>课间操</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="dailyHourView" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${dailyHourView.className}</td>
                        <td>${dailyHourView.skts}</td>
                        <td>${dailyHourView.swks}</td>
                        <td>${dailyHourView.xwks}</td>
                        <td>${dailyHourView.kjc}</td>
                        <td><span
                                onclick="openDialog('编辑','${ctx}/teach/task/daily/hour/add/pop?dailyId=${dailyHourView.id}','620px','410px');"
                                >编辑</span>
                            <sapn class='dele-sp' value="${dailyHourView.id}"
                                  onclick="alertTips(400,202,'删除周期','确定要删除该课时吗吗？','deleteSure(\'${dailyHourView.id}\')')">
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
    activeMenu("base",6);
    //id为sectionId,nj为传入的nj的
    function njButton(id, nj) {
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        window.location.href = "${ctx}/teach/task/daily/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&&nj="+nj+"&&sectionId="+id;
//        if ($(this).attr("name") == "cycle_year") {
//            cycleSemester = "";
//        }
        <%--window.location.href = "${ctx}/teach/task/master/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&nj=" + nj + "&&sectionId=" + id;--%>
    }

    $(function () {
        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();
            var nj = $('.anjDiv   .active').attr("valueNj");
            var sectionId = $('.anjDiv .active').attr("value");
            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }
            window.location.href = "${ctx}/teach/task/daily/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&&nj="+nj+"&&sectionId="+sectionId;
        });

        $(".fenY2").createPage({
            pageCount:'${pageInfo.pages}',//总页数
            current:'${pageInfo.pageNum}',//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                var nj = $('.anjDiv   .active').attr("valueNj");
                var sectionId = $('.anjDiv .active').attr("value");
                window.location.href = "${ctx}/teach/task/daily/hour?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester+"&&nj="+nj+"&&sectionId="+sectionId;;
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
                window.location.href = "${ctx}/teach/task/daily/hour?pageNum=" + $(".fenY2go").val() + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });
    });

    function deleteSure(id) {
        closeAlertDiv();
        $.post("${ctx}/teach/task/daily/hour/del", {
            dailyId: id
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

</script>
</body>
</html>