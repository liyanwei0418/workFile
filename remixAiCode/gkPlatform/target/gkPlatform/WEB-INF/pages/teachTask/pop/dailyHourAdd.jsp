<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">

    <style>

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
        }

        span {
            display: inline-block;
        }

        .popup-main {
            background: #fff;
            padding: 35px 0px 10px 25px;
            font-size: 13px !important;
           
        }

        table td {
            font-size: 13px;
            text-align: right;
            padding: 10px 0;
        }

        table td span {
            width: 88px;
            text-align: right;
        }

        table td input[type="text"], table td select{
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
        .no-modify{
            width: 150px;
            height: 28px;
            line-height: 28px;
            margin-left: 12px;
            padding-left: 10px;
            color: #333;
            border:none;
            text-align: left;
        }
        table td input[type="radio"] {
            margin-right: 8px;
            display: none;
        }

        label {
            padding-left: 20px;
            cursor: pointer;
            background: url(${ctxStaticNew}/images/nocheck.png) no-repeat left;
        }

        label.checked {
            background: url(${ctxStaticNew}/images/check.png) no-repeat left;
        }

        textarea {
            width: 415px;
            vertical-align: top;
            margin-left: 12px;
            height: 100px;
            outline: none;
            border: 1px solid #dadada;
            resize: none;
            color: #333;
            padding: 0 10px;
            tab-index: 2em;
        }

        input[type='checkbox']{
            margin-right: 5px;
            position: relative;
            bottom: -1px;
        }

        .chec-containt{
            width: 100px;
            margin-bottom: 10px;
        }
        .addClass{
            padding-left: 36px;
            margin-top: 20px;
            width: 600px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <!-- jQuery -->
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
</head>

<body>
<div class="container">
    <%--<form method="post" action="${ctx}/teach/task/room/update" id="inputForm">--%>
    <input type="hidden" name="roomId" value="">
    <div class="popup-main">
        <input type="hidden" class="dailyId" value="${dailyHour.id}" name="dailyId">
        <table>
            <tbody>

            <c:if test="${gradeClassOne!=null}">
                <tr>
                    <td>
                        <span>班级:</span>
                        <span class="no-modify">${gradeClassOne.name}</span>
                    </td>
                    <td></td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <span>上课天数:</span>
                    <input type="text" class="skts" value="${dailyHour.skts}" placeholder="请填写整数">
                </td>
                <td>
                    <span>课间操:</span>
                    <input type="text" name="count" class="kjc" value="${dailyHour.kjc}" placeholder="请填写整数">
                </td>
            </tr>
            <tr>
                <td>
                    <span>上午课时:</span>
                    <input type="text" class="swks" value="${dailyHour.swks}" placeholder="请填写整数">
                </td>
                <td>
                    <span>下午课时:</span>
                    <input type="text" name="count" class="xwks" value="${dailyHour.xwks}" placeholder="请填写整数">
                </td>
            </tr>
            <tr>
                <td>
                    <span>学年:</span>
                    <select name="cycleYear" class="cycleYear">
                        <c:forEach items="${yearList}" var="year" varStatus="status">
                            <option name="teachYear" value="${year}"
                                    <c:if test="${dailyHour.xn==year}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <span>学期:</span>
                    <select name="semester" class="cycleSemester">
                        <option name="cycleSemester"
                                <c:if test="${cycle.cycleSemester == 1}">selected="selected"</c:if>
                                value="1">1</option>
                        <option name="cycleSemester"
                                <c:if test="${cycle.cycleSemester  == 2}">selected="selected"</c:if>
                                value="2">2</option>
                    </select>
                </td>
            </tr>

            </tbody>
        </table>
        <c:if test="${gradeClassOne==null}">
            <div class="addClass">
                <span style="font-size: 14px;margin-bottom: 12px;">班级:</span><input type="checkbox" class="all">全选<br>
                <c:forEach items="${gradeClassList}" var="gradeClass">
                    <span class="chec-containt" style="color: #333;">
                        <input type="checkbox" value="${gradeClass.id}"
                               <c:if test="${gradeClass.id==dailyHour.gradeClassId}">checked</c:if>>${gradeClass.name}
                    </span>
                </c:forEach>
                <input type="hidden" name="gradeClassId" class="classIds">
            </div>
        </c:if>
    </div>
    <%--</form>--%>
</div>
</body>
<script>
    $(function () {
        $(".cycleYear").change(function () {
            $(".cycleSemester").empty();
            var cycleYear = $(".cycleYear").find("option:selected").text();
            $.post("${ctx}/teach/task/course/add/select/change", {
                cycleYear: cycleYear,

            }, function (data) {
                if (data.code == 0) {
                    var teachCycleList = data.data;
                    for(var i=0; i<teachCycleList.length;i++){
                        var cycleSemester = teachCycleList[i].cycleSemester;
                        var html='';
                        html +='<option name="cycleSemester" <c:if test="${cycleSemester == 1}">selected="selected"</c:if>value="'+cycleSemester+'">'+cycleSemester+'</option>';
                        $('.cycleSemester').append(html);

                    }
                } else {
                    layer.msg("暂无相关数据");
                }
            });
        })
    })

    $(".all").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true)
        } else {
            $("input[type='checkbox']").prop("checked", false)
        }
    });

    function doSubmit() {
        var classId = "";
        $("input[type=checkbox]:checked").each(function () {
            classId += "," + $(this).val();
        });
        $(".classIds").val(classId);

        var skts = $(".skts").val();
        var kjc = $(".kjc").val();
        var swks = $(".swks").val();
        var xwks = $(".xwks").val();
        var classIds = $(".classIds").val();
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
        var dailyId=$(".dailyId").val();
        if (skts == "" || kjc == "" || swks == "" || xwks == "" || classIds == "") {
            layer.msg("所填项均为必填");
            return;
        }
        var reg =  "^[0-9]*[1-9][0-9]*$";
        if (!skts.match(reg)||!kjc.match(reg)||!swks.match(reg)||!xwks.match(reg)) {
            layer.msg("数据格式不正确");
            return;
        }

        var url = null;
        if(${gradeClassOne==null}){
           url= "teach/task/daily/hour/add";
        }else {
            url="teach/task/daily/hour/edit";
        }
            $.post("${ctx}/"+url, {
                skts: skts,
                kjc: kjc,
                swks: swks,
                xwks: xwks,
                classIds: classIds,
                cycleYear: cycleYear,
                cycleSemester: cycleSemester,
                dailyId:dailyId
            }, function (data) {
                if (data.code==0){
                    layer.msg("操作成功");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 400);
                    /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                    setTimeout(function () {
                        top.layer.close()
                    }, 300);
                }else {
                    layer.msg(data.msg);
                }
            })
    }
</script>
</html>
