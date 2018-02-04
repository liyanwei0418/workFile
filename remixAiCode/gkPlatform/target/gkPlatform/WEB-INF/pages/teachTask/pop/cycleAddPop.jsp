<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/laydate.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
    }

    span {
        display: inline-block;
    }

    .popup-main {
        background: #fff;
        padding: 35px 0px 10px 25px;
        font-size: 13px !important;
        color: #525252 !important;
    }

    table td {
        font-size: 13px;
        /*text-align: right;*/
        padding: 10px 0;
    }

    table td span:first-child {
        width: 88px;
        text-align: right;
    }

    .datasame {
        width: 58px !important;
        font-size: 13px !important;
    }

    table td span:last-child {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
    }

    table td input[type="text"], table td select {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 12px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
    }

    .cla-type-containt {
        width: 380px;
        margin-left: 48px;
    }

    .cla-type-containt span {
        width: 102px;
        line-height: 30px;
        font-size: 12px;
    }

    .cla-type-containt input {
        margin-right: 5px;
        position: relative;
        top: 2px;
    }

    i {
        font-style: normal;
        color: #999;
        font-size: 12px;
        margin-left: 10px;
    }
</style>
<body>
<div>
    <table>
        <tr>
            <td><span>学年:</span>
                <c:if test="${update != 1}">
                    <input type="text" name="cycleYear" class="cycleYear" required
                           value="${teachCycle.cycleYear}"><i>请填写如下格式:2016-2017</i>
                </c:if>
                <c:if test="${update == 1}">
                    <input type="hidden">${teachCycle.cycleYear}
                </c:if>
            </td>
        </tr>
        <tr>
            <td><span>学期:</span>
                <c:if test="${update != 1}">
                    <input type="text" name="teachCycle.cycleSemester" class="cycleSemester" required
                           value="${teachCycle.cycleSemester}"><i>填写数字1或2，1表示第一学期</i>
                </c:if>
                <c:if test="${update == 1}">
                    <input type="hidden">${teachCycle.cycleSemester}
                </c:if>
            </td>
        </tr>
        <tr>
            <td><span>开学时间:</span><input type="text" class="laydate-icon" id="demo"
                                         value="${gukeer:millsToyyyyMMdd(teachCycle.termBeginTime)}"></td>
        </tr>
        <tr>
            <td><span>开始周:</span>
                <input class="start" type="text" style="margin-left: 8px;" required value="${teachCycle.beginDate}"><i>填写数字,例如1表示开始周为第一周</i>
            </td>
        </tr>
        <tr>
            <td><span>结束周:</span><input class="end" type="text" required value="${teachCycle.endDate}"><i>填写数字,例如1表示结束周为第一周</i>
            </td>
        </tr>
        <tr>
            <td><span>总周次:</span><input type="text" class="weekCount" required value="${teachCycle.weekCount}"><i>填写数字,例如10表示一共10周</i>
            </td>
        </tr>
    </table>
</div>
<c:if test="${update != 1}">
    <div class="cla-type-containt">
        <input type="hidden" value="${cycleId}" class="cycleId">
        <span class="datasame">同步数据</span> <i>（若没有上一学期数据,请不要勾选）</i><br>
        <input type="checkbox" class="room" value="教室管理"><span>教室管理</span>
        <input type="checkbox" class="course" value="课节设置"><span>课节设置</span>
        <input type="checkbox" class="course" value="课程安排"><span>课程安排</span>
        <input type="checkbox" class="master" value="班主任安排"><span>班主任安排</span>
        <input type="checkbox" class="courseTeacher" value="任课教师安排"><span>任课教师安排</span>
        <input type="checkbox" class="room" value="班级教室安排"><span>班级教室安排</span>
        <input type="checkbox" class="room" value="科目课时安排"><span>科目课时安排</span>
    </div>
</c:if>
</body>
<script>
    function doSubmit() {
        var synInfo = "";
        var inputs = $('input[type=checkbox]:checked');
        $('input[type=checkbox]:checked').each(function (i) {
            var oneText = $(this).val();
            synInfo += "," + oneText;
        })
        var cycleId = $(".cycleId").val();//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var cycleYear = null;
        var cycleSemester = null;

        cycleYear = $(".cycleYear").val();
        cycleSemester = $(".cycleSemester").val();
        var start = $(".start").val();
        var end = $(".end").val();
        var weekCount = $(".weekCount").val();
        var termsStart = $(".laydate-icon").val();

        if (cycleYear == "" || cycleSemester == "" || start == "" || end == "" || weekCount == "" || termsStart == "") {
            layer.msg("所填项均为必填");
            return;
        }


//        var pattern = /2000-2999/;
//        console.log(pattern.test(cycleYear));
//        if (!pattern.test(cycleYear)){
//            return false;
//        }
        // var regCycleYear= "(?:(?!0000)[0-9]{4}-(?:(?!0000)[0-9]{4}*$";
        // if (!regCycleYear.match(reg)) {
        // layer.msg("学年格式不正确");
        // return;
        // }

        <c:if test="${update == 1}">
        var reg = "^[0-9]*[1-9][0-9]*$";
        if ( !end.match(reg) || !weekCount.match(reg)) {
            layer.msg("数据格式不正确");
            return;
        }
        </c:if>
        <c:if test="${update != 1}">
        var reg = "^[0-9]*[1-9][0-9]*$";
        if (!cycleSemester.match(reg) || !start.match(reg) || !end.match(reg) || !weekCount.match(reg)) {
            layer.msg("数据格式不正确");
            return;
        }
        </c:if>


        <c:if test="${update == 1}">

        $('.layui-layer-btn0').attr('disabled', true);
        $('.layui-layer-btn0').css("background","#999");
        $('.layui-layer-btn0').css("border","1px solid #999");

        $.post("${ctx}/teach/task/cycle/do", {
            id: '${teachCycle.id}',
            type: '${type}',
            beginDate: start,
            endDate: end,
            weekCount: weekCount,
            synInfo: synInfo,
            cycleId: cycleId,
            termBeginTime: termsStart
        }, function (data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                setTimeout(function () {
                    parent.location.reload();
                }, 2300);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 2300);
                return true;
            } else {
                layer.msg("操作失败");
                setTimeout(function () {
                    parent.location.reload();
                }, 2300);
                /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                setTimeout(function () {
                    top.layer.close()
                }, 2300);
                return false;
            }
        });
    }
    </c:if>


    <c:if test="${update != 1}">

    $('.layui-layer-btn0').attr('disabled', true);
    $('.layui-layer-btn0').css("background","#999");
    $('.layui-layer-btn0').css("border","1px solid #999");

    $.post("${ctx}/teach/task/cycle/do", {
        type: '${type}',
        cycleYear: cycleYear,
        cycleSemester: cycleSemester,
        beginDate: start,
        endDate: end,
        weekCount: weekCount,
        synInfo: synInfo,
        cycleId: cycleId,
        termBeginTime: termsStart
    }, function (data) {
        if (data.code == 0) {
            layer.msg("操作成功");
            setTimeout(function () {
                parent.location.reload();
            }, 2300);
            /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
            setTimeout(function () {
                top.layer.close()
            }, 2300);
            return true;
        } else {
            layer.msg("操作失败");
            setTimeout(function () {
                parent.location.reload();
            }, 2300);
            /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
            setTimeout(function () {
                top.layer.close()
            }, 2300);
            return false;
        }
    });
    }
    </c:if>

    laydate({
        elem: '#demo'
    })


</script>
</html>
