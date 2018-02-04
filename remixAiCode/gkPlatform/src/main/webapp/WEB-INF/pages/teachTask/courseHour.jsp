<%--
  Created by IntelliJ IDEA.
  User: LL
  Date: 2017/5/9
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/headerXf.jsp" %>
<html>
<head>
    <title>教务管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
</head>
<style>
    .search-box select {
        height: 30px;
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
        height: 31px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }

    .course ul li a {
        display: inline-block;
        width: 135px;
        height: 45px;
        line-height: 44px;
        font-size: 15px;
        color: #777;
        text-align: center;
        text-decoration: none;
    }

    .course ul li a.active {
        color: #54ab37;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }

    .course ul li a:hover {
        color: #54ab37;
    }

    .course ul {
        border-bottom: 1px solid #ddd;
        height: 45px
    }

    .course ul li {
        float: left
    }

    #zh-manage .search-box {
        margin-top: 0 !important;
    }

    .course ul {
        border: none;
    }

    .course ul li a {
        border-bottom: 1px solid #ddd;
    }

    .stu-num-manage-menu {
        display: inline-block;
    }

    .course ul {
        margin-bottom: 0 !important;
    }

    .btn-containt input {
        border: 1px solid #54ab37;
        border-radius: 2px;
        background: #54AB37;
        color: #fff;
        width: 70px;
        height: 30px;
        line-height: 26px;
        margin: 20px 0 0 0;
    }

    .cla-hour-containt {
        margin-top: 40px;
    }

    .cla-hour-containt span {
        margin-bottom: 20px;
    }

    .cla-hour-containt span input {
        height: 30px;
        line-height: 30px;
        margin-left: 10px;
        padding-left: 10px;
    }


    .gobefore, .goafter{
        vertical-align: middle;
        width: 20px;
        height: 20px;
        background: #54AB37;
        margin-top: 20px;
        cursor: pointer;
    }
    .gobefore{
        background: url(../../../assetsNew/images/publish-4.png) no-repeat center;
    }
    .goafter{
        background: url(../../../assetsNew/images/publish-4.png) no-repeat center;
        transform: rotate(180deg);
    }
    .anjDiv{
        overflow: hidden;
        width: 1100px;
        margin: 0 auto;
        display: inline-block;
        vertical-align: middle;
    }

    .anjDiv ul li a.active {
        color: #54AB37;
        /*border: 1px solid #ddd;*/
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
        /*border-bottom: 1px solid #ddd;*/
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
    input{
        border-radius: 0 !important;
    }
</style>

<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="stu-num-manage-menu" style="display: inline-block">
                <%@ include file="../common/cycleYearAndSemester.jsp" %>
                <%--<option name="cycleSemester"--%>
                        <%--<c:if test="${cycleSemester ==1}">selected</c:if> value="1">1--%>
                <%--</option>--%>
                <%--<option name="cycleSemester"--%>
                        <%--<c:if test="${cycleSemester ==2}">selected</c:if> value="2">2--%>
                <%--</option>--%>
            </select>
        </div>
    </div>
    <div>

        <c:if test="${courseList !=null &&courseList!=''&&courseList!='[]'}">
            <span class="gobefore"></span>
            <div class="anjDiv">
                <ul class="njUl">

                    <c:forEach items="${courseList}" var="course" varStatus="status">
                        <li><a href="#"
                               <c:if test="${course.id==courseId}">class="active"</c:if> va="${course.id}"
                               class="acourse">${course.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <span class="goafter"></span>
        </c:if>



    </div>

    <c:if test="${courseClassViewList !=null &&courseClassViewList!=''&&courseClassViewList!='[]'}">
    <div class="cla-hour-containt">
        <c:forEach items="${courseClassViewList}" varStatus="status" var="courseClassView">
            <c:if test="${courseClassView.classSection!=null}">
            <span class="njBanji" value="${courseClassView.classSectionId}"> <span valu="${courseClassView.nj}"
                                                                                   class="tdForNj"
                                                                                   style="display: none"></span>
            ${courseClassView.classSection}${courseClassView.nj}年级:<input type="text"
                                                                          value="${courseClassView.courseHour}"></span><%--<span  value="${status.count+1}" class="tdForNj" style="display: none"></span>--%></span>
                <br>
            </c:if>
        </c:forEach>
    </div>
    </c:if>

    <div class="btn-containt">
        <input type="button" onclick="save()" class="save" value="保存">
    </div>
</main>
</body>
<script>
    activeMenu("all", 2);
    $(function () {
        $('.njUl').css('marginLeft','${courseLL}');
        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();
            if ($(this).attr("name")== "cycleYear") {
                cycleSemester = "";
            }else{
                cycleSemester =  $("select[name='cycleSemester']").val()
            }
            var courseId = $('.active').val();
            window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });
    });
    $(".acourse").click(function () {
        $(this).addClass('active').siblings().removeClass("active");
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var courseId = $(this).attr("va");
        var LL = $('.njUl').css('marginLeft');
        window.location.href = "${ctx}/teach/task/course/hour?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&courseId=" + courseId+ "&&courseLL=" + LL;
    });



    function save() {
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var courseId = $('.njUl').find('.active').attr('va');
        var sectionIdSpan = $('.njBanji');
        console.log(sectionIdSpan.length);
        var sectionIdAndCourseHour = "";
        sectionIdSpan.each(function (i) {
            console.log($(this).siblings("input").val());
            sectionIdAndCourseHour += "," + $(this).attr("value") + ":" + $(this).children("span").attr("valu") + ":" + $(this).children("input").val();
        });
        layer.msg("保存成功");
        $.post("${ctx}/teach/task/course/hour/edit", {
            sectionIdAndCourseHour: sectionIdAndCourseHour,
            courseId: courseId,
            cycleYear: cycleYear,
            cycleSemester: cycleSemester
        }, function (data) {
//            layer.msg("保存成功");
//            setTimeout(function () {
//                window.location.reload()
//            }, 2000);
        })
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
</html>
