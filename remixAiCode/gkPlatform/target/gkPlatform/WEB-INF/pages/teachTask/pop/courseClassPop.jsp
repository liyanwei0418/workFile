<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
    p {
        line-height: 30px;
        padding-left: 20px;
        margin-top: 10px;
        margin-bottom: 0;
    }

    form {
        font-size: 14px;
        color: #525252;
    }

    form {
        width: 80%;
    }

    form input[type='checkbox'] {
        margin: 8px 5px 8px 0;
    }

    .school-type {
        font-size: 16px;
        margin-top: 20px;
    }

    ul {
        margin-top: 0 !important;
        padding-left: 20px;
    }

    ul span {
        display: inline-block;
        width: 66px;
    }

    ul input {
        position: relative;
        top: 1px;
    }

    .njDiv > div {
        margin-top: 10px;
        padding-bottom: 10px;
        border-bottom: 1px solid #dddddd;
    }
</style>
<body>
<div>
    <form action="${ctx}/teach/task/course/class/add?courseId=${courseId}" method="post" class="courseClass">
        <p><input type="checkbox" class="all"><span>全选</span></p>
        <ul>
            <div class="sectionDiv">
                <c:forEach items="${sectionList}" var="section">
                    <div>
                        <span style="margin-right: 18px;">${section.name}</span>
                        <input type="checkbox" class="oneSection" value="" name="section">
                        <span>全选</span><br>
                            <%--年级--%>
                        <c:forEach items="${njList}" var="nj">
                            <c:if test="${section.id == nj.xd}">
                                <div class="njDiv">
                                    <span style="margin-right: 18px;">${nj.nj}年级</span>
                                    <input type="checkbox" class="oneNj" name="nj" value="">
                                    <span>全选</span><br>
                                        <%--班级--%>
                                    <div>
                                        <c:forEach items="${gradeClassList}" var="gradeClass">
                                            <c:if test="${section.id == gradeClass.xd && nj.nj == gradeClass.nj}">
                                                <%--<c:forEach items="${courseClassList}" var="courseClass">--%>
                                                <input type="checkbox" name="banji" value="${gradeClass.id}"
                                                       class="oneBanji" val="onebanji" <c:if
                                                               test="${list.contains(gradeClass.id)}">checked</c:if>><span>${gradeClass.name}</span>
                                                <%--</c:forEach>--%>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </ul>
    </form>
</div>
</body>
<script>
    var listOld = '${list}';
    $(".all").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true)
        } else {
            $("input[type='checkbox']").prop("checked", false)
        }
    });

    $(".oneSection").on("click", function () {
        var njinputs = $(this).siblings('div').children('div').children('input');
        console.log($(this).siblings('div').children('input'));
        var banjiInputs = $(this).siblings('div').children('div').children('div').children('input');
        if (this.checked == true) {
            $(njinputs).prop("checked", true);
            $(banjiInputs).prop("checked", true);
        } else {
            $(njinputs).prop("checked", false);
            $(banjiInputs).prop("checked", false);
        }
    });

    $(".oneNj").on("click", function () {
        console.log($(this).siblings('div'));
        var banjiinput = $(this).siblings('div').children('input');
//        console.log(banjiinput);
        if (this.checked == true) {
            $(banjiinput).prop("checked", true);
        } else {
            $(banjiinput).prop("checked", false);
        }
    });

    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() {
        var classIds = "";
        var inputs = $("input[val=onebanji]:checked").length;
        $("input[val=onebanji]:checked").each(function (i) {
            if ($(this).attr("checked")) {
                console.log($(this));
                if (i == 0) {
                    classIds = $(this).val();
                } else {
                    classIds += "," + $(this).val();
                }

            }
        });
        $.post(postPath + "/teach/task/course/class/add", {
            courseId: '${courseId}',
            classIds: classIds,
            listOld: listOld
        }, function (data) {

        });
        return true;
    }
</script>
</html>
