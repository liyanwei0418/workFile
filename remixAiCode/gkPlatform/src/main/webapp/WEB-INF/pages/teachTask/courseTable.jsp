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

    .search-box {
        padding: 0 !important;
    }

    .row {
        margin: auto;
        padding: 0 !important;
    }

    #zh-manage #generated > div:first-child {
        padding: 0;
    }

    #zh-manage #generated > div:nth-child(2) {
        padding: 0;
    }

    select {
        height: 30px;
        width: 130px;
        border-radius: 2px;
        padding-left: 5px;
        color: #525252;
        font-size: 14px;
    }

    td input {
        width: 100%;
    }

    .savebtn {
        width: 70px;
        height: 30px;
        border: 1px solid #54AB37;
        color: #fff;
        background: #54AB37;
        border-radius: 2px;
        float: right;
        margin-top: 30px;
    }

    .updateButton {
        width: 70px;
        height: 30px;
        border: 1px solid #54AB37;
        color: #fff;
        background: #54AB37;
        border-radius: 2px;
        float: right;
        margin-top: 30px;
    }

    .roll-operation{
        margin-top: 30px;
    }
    .roll-operation span{
        display: inline-block;
        padding: 0 15px;
        height: 30px;
        line-height: 30px;
        background: #54AB37;
        color: #fff;
        border-radius: 2px;
        margin-left: 10px;
    }
</style>
<body>

<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <section id="generated" class="row" style="overflow: hidden;">
        <div style="overflow: hidden;">
            <div class="stu-num-manage-menu" style="float: left">
                <%@ include file="../common/cycleYearAndSemester.jsp" %>
            </div>
            <div class="roll-operation" style="float: left;">
                <span onclick="openDialog('导入数据','${ctx}/teach/task/fileImport?url=${ctx}/teach/task/table/import&cycleId=${cycleId}','500px','360px')">
                    导入
                </span>
                <span onclick="window.location.href='${ctx}/teach/task/table/download'">下载模板</span>
                <%--<button onclick="openDialogView('查看所有任课教师安排','${ctx}/teach/task/course/teacher/all','500px','350px')">查看任课教师安排--%>
                <%--</button>--%>
            </div>
        </div>
        <div style="margin-top: 20px;">
            <input type="hidden" value="${cycleId}" class="cycleId">
            <input type="hidden" value="${gradeClassId}" class="gradeClassId">
            <input type="hidden" value="${nj}" class="nj">
            学段：
            <select name="section" class="section">
                <option value="">请选择</option>
                <c:forEach items="${sectionList}" var="section">
                    <option name="section" value="${section.id}"
                            <c:if test="${sectionId eq section.id}">selected</c:if>>${section.name}</option>
                </c:forEach>
            </select>
            年级：
            <select name="nj" class="nj">
                <option value="">请选择</option>
                <c:forEach items="${njList}" var="njforeach" varStatus="status">
                    <option value="${status.count}"
                            <c:if test="${nj eq status.count}">selected</c:if>>${njforeach}
                    </option>
                </c:forEach>
            </select>
            班级：
            <select name="gradeClass" class="gradeClass">
                <option value="">请选择</option>
                <c:forEach items="${gradeClassList}" var="gradeClass">
                    <option name="gradeClass" value="${gradeClass.id}"
                            <c:if test="${gradeClass.id eq gradeClassId}">selected</c:if>>${gradeClass.name}
                    </option>
                </c:forEach>
            </select>

            <span class="teachCycle">教学周次：</span>
            <select name="week" class="week">
                <option value="">请选择</option>
                <c:forEach items="${integerList}" var="week">
                    <option name="week" value="${week}"
                            <c:if test="${weekFetch==week}">selected</c:if>>${week}</option>
                </c:forEach>
            </select>

            <%--教学周次：--%>
            <%--<select name="gradeClass" class="gradeClass">--%>
            <%--<option value="">请选择</option>--%>
            <%--<c:forEach items="${gradeClassList}" var="gradeClass">--%>
            <%--<option name="gradeClass" value="${gradeClass.id}"--%>
            <%--<c:if test="${gradeClass.id eq gradeClassId}">selected</c:if>>${gradeClass.name}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>

        </div>
        <div class="info"><br>
            <p style="color: red;size: 10px">提示信息：请按如下格式填写:授课教师-课程</p><br></div>
        <div class="row">
            <c:if test="${gradeClassId != '' && gradeClassId !=null &&refClassRoomId==''}">
                <div class="info"><br>
                    <p style="color: red;size: 10px">班级教室信息没有匹配，请到教务班级教室目录下添加相关数据</p><br></div>
            </c:if>

            <table class="normal" cellpadding="0" cellspacing="0" border="1" bordercolor="#ddd">
                <thead class="thead">
                </thead>
                <tbody class="tbody">
                <%--<tr class="theadTr">--%>
                <%--<td class="theadTd"></td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>

        <div class="adaptCycle" style="display: none">
            <br>
            <span>适用教学周</span><br><input type="checkbox" class="all">全选<br>
            <c:forEach items="${integerList}" var="week">
                <input type="checkbox" class="adaptCycleWeekInput" name="adaptCycleWeekInput" value="${week}"><span
                    class="adaptCycleWeekSpan">${week}</span>
            </c:forEach>
        </div>
        <div>
            <button class="savebtn">
                保存
            </button>
            <button class="updateButton">
                修改
            </button>
        </div>
    </section>
</main>
<script>

    var mapMap = '${mapMap}';
    var refClassRoomId = '${refClassRoomId}';
    var gradeClassId = '${gradeClassId}';

    $(".all").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true)
        } else {
            $("input[type='checkbox']").prop("checked", false)
        }
    });

    $(function () {
        if (gradeClassId != "" && gradeClassId != null && refClassRoomId == "") {
            layer.msg("班级教室信息没有匹配，请到教务班级教室目录下添加相关数据");
        }

        if (${week==0}) {
            $(".tbody").empty();
        }
        if (mapMap != '{}') {
            $(".inputForUpdate").hide();
            $(".savebtn").hide();
        } else {
            $(".inputForUpdate").show();
            $(".updateButton").hide();
            $(".savebtn").show();

//            $(".week").hide();
//            $(".teachCycle").hide();
            if (refClassRoomId != "") {
                $('.adaptCycle').css('display', 'block');
            }
        }
    })

    $(".updateButton").click(function () {
        $(".inputForUpdate").attr("type", "text");
        $(".updateButton").hide();
        $(".savebtn").show();
        $(".havavalueSpan").hide();
        $('.adaptCycle').css('display', 'block');
//        $(".week").hide();
//        $(".teachCycle").hide();
    })

    $(".savebtn").click(function () {
//        $(".haveValueTd").empty();

        var cycleId = $(".cycleId").val();
        var gradeClassId = $(".gradeClassId").val();
        var str = '{"oneWeek":[';
        var inputs = $('.courseTeacher');
        var nj = $(".nj");
        var week = $("select[name='week']").val();

        inputs.each(function (i) {
            var thisInputNameValue = $(this).attr('name');
            var thisInputIndexValue = $(this).attr('index');
            var thisInputValue = $(this).val();
            var id = $(this).parents('td').attr('value');
            var teacher = "";
            var course = "";
            if (thisInputValue == "自习") {
                str += '{"oneday":"' + thisInputNameValue + '","node":"' + thisInputIndexValue + '","teacher":"","course":"自习","id":"' + id + '"},';
            } else {
                if (thisInputValue != '' && thisInputValue != null && thisInputValue != undefined) {
                    thisInputValue.replace('——', '-');
                    var arr = thisInputValue.split("-");
                    if (arr.length == 2) {
                        teacher = arr[0];
                        var arrArray = ["小一", "小二", "小三", "小四", "小五", "小六", "初一", "初二", "初三", "高一", "高二", "高三"];

                        var shuzi = $.inArray(arr[1].substr(0, 2), arrArray);
                        if (shuzi > 0) {
                            course = arr[1].substr(2, arr[1].length);
                        } else {
                            course = arr[1];
                        }
                    }
                }

                if (i == inputs.length - 1) {
                    str += '{"oneday":"' + thisInputNameValue + '","node":"' + thisInputIndexValue + '","teacher":"' + teacher + '","course":"' + course + '","id":"' + id + '","week":"' + week + '"},';
                } else {
                    str += '{"oneday":"' + thisInputNameValue + '","node":"' + thisInputIndexValue + '","teacher":"' + teacher + '","course":"' + course + '","id":"' + id + '","week":"' + week + '"},';
                }
            }
        })
        str += ']}';

        var inputsAdaptWeek = $("input[name='adaptCycleWeekInput']:checked");
        var weekArr = "";
        inputsAdaptWeek.each(function (i) {
            if (i == 0) {
                weekArr = $(this).val();
            } else {
                weekArr += "," + $(this).val();
            }
        })

        if (weekArr == "") {
            layer.msg("请选择适用教学周");
            return;
        }

        console.log(str);
        $('.savebtn').attr('disabled', true);
        $('.savebtn').css("background","#999");
        $('.savebtn').css("border","1px solid #999");
        $.post("${ctx}/teach/task/table/save", {
            //获取所有input的值
            str: str,
            cycleId: cycleId,
            gradeClassId: gradeClassId,
            week: week,
            weekArr: weekArr
        }, function (data) {
            console.log($('.courseTeacher'));
            if (data.code == 0) {
                $('#savebtn').attr('disabled', false);
                layer.msg("操作成功");
                window.location.reload();
            } else {
//                layer.msg(data.msg);
                var inputsForFecth = $('.courseTeacher');
                inputsForFecth.each(function (i) {
                    if (data.msg == i) {
                        $(this).val("输入信息不存在");
                        $(this).css('style', 'color:red;');
                    }
                })
//                setTimeout(function () {
//                    window.location.reload();
//                }, 3000);
            }
        })
    })

    activeMenu("all", 3);
    var skts = '${skts}';
    var totalHour = '${totalHour}';
    function isEmptyObject(e) {
        for (var name in e){
            console.log(name);
            return false;//返回false，不为空对象
        }
        return true;//返回true，为空对象
    }
    $(function () {
        var json = JSON.parse(mapMap);
        console.log(json);
        console.log(json[0]);
        if (skts != 0 && refClassRoomId != "" && refClassRoomId != undefined && refClassRoomId != null) {
            $('<th width="6%"></th>').appendTo(".thead");
            for (var i = 1; i <= skts; i++) {
                if (i == 1) {
                    $('<th>星期一</th>').appendTo(".thead");
                } else if (i == 2) {
                    $('<th>星期二</th>').appendTo(".thead");
                } else if (i == 3) {
                    $('<th>星期三</th>').appendTo(".thead");
                } else if (i == 4) {
                    $('<th>星期四</th>').appendTo(".thead");
                } else if (i == 5) {
                    $('<th>星期五</th>').appendTo(".thead");
                }
            }
            for (var hour = 0; hour < totalHour; hour++) {
                if (hour == 0) {
                    $('<tr class="node' + hour + '" index="' + hour + '"><td>早自习</td></tr>').appendTo(".tbody");
                } else {
                    $('<tr class="node' + hour + '" index="' + hour + '"><td>第' + hour + '节</td></tr>').appendTo(".tbody");
                }
            }

            if (mapMap != '{}') {
                for (var i = 0; i < totalHour; i++) {
                    var onetofive = json[i];
                    console.log(onetofive);

                    if (!isEmptyObject(onetofive)) {

                        //第一个参数：周一到在周五的第几节，第二个参数第几节
                        forWeek(onetofive,"node"+i,i);
                    } else {
                        for (var oneskts = 1; oneskts <= skts; oneskts++) {
                            if (oneskts == 1) {
                                $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="monday" class="courseTeacher inputForUpdate"></td>').appendTo(".node" + i);
                            }
                            if (oneskts == 2) {
                                $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="tuesday" class="courseTeacher inputForUpdate"></td>').appendTo(".node" + i);
                            }
                            if (oneskts == 3) {
                                $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="wednesday" class="courseTeacher inputForUpdate"></td>').appendTo(".node" + i);
                            }
                            if (oneskts == 4) {
                                $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="thursday" class="courseTeacher inputForUpdate"></td>').appendTo(".node" + i);
                            }
                            if (oneskts == 5) {
                                $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="friday" class="courseTeacher inputForUpdate"></td>').appendTo(".node" + i);
                            }
                        }
                    }
                }
            } else {
                if (refClassRoomId != "" && refClassRoomId != undefined && refClassRoomId != null) {
                    console.log(refClassRoomId);
                    for (var k = 0; k < totalHour; k++) {
                        for (var j = 1; j <= skts; j++) {
                            if (j==1){
                                $('<td value=""><input type="text" index=' + k + ' name="monday" class="courseTeacher"></td>').appendTo(".node"+k);
                            }
                            if (j==2){
                                $('<td value=""><input type="text" index=' + k + ' name="tuesday" class="courseTeacher"></td>').appendTo(".node"+k);
                            }
                            if (j==3){
                                $('<td value=""><input type="text" index=' + k + ' name="wednesday" class="courseTeacher"></td>').appendTo(".node"+k);
                            }
                            if (j==4){
                                $('<td value=""><input type="text" index=' + k + ' name="thursday" class="courseTeacher"></td>').appendTo(".node"+k);
                            }
                            if (j==5){
                                $('<td value=""><input type="text" index=' + k + ' name="friday" class="courseTeacher"></td>').appendTo(".node"+k);
                            }
                        }
                    }
                }

            }
        } else {
            $(".theadTd").hide();
        }


        <%--if (totalHour != 0 && refClassRoomId != "" && refClassRoomId != undefined && refClassRoomId != null) {--%>
        <%--$('<th width="6%"></th>').appendTo(".thead");--%>
        <%--for (var i = 0; i < totalHour; i++) {--%>
        <%--if (i == 0) {--%>
        <%--$('<th>早自习</th>').appendTo(".thead");--%>
        <%--} else {--%>
        <%--$('<th>第' + i + '节</th>').appendTo(".thead");--%>
        <%--}--%>

        <%--}--%>
        <%--$('<tr class="monday"><td>星期一</td></tr><tr class="tuesday"><td>星期二</td></tr><tr class="wednesday"><td>星期三</td></tr><tr class="thursday"><td>星期四</td></tr><tr class="friday"><td>星期五</td></tr>').appendTo(".tbody");--%>
        <%--var str = "<th></th>";--%>
        <%--for (var i = 0; i <${totalHour}; i++) {--%>
        <%--str += '<th>第' + (i + 1) + '节</th>';--%>
        <%--}--%>
        <%--$(str).appendTo(".theadTr");--%>
        <%--$('<tr class="monday"><td>星期一</td></tr><tr class="tuesday"><td>星期二</td></tr><tr class="wednesday"><td>星期三</td></tr><tr class="thursday"><td>星期四</td></tr><tr class="friday"><td>星期五</td></tr>').appendTo(".tbody");--%>
        <%--var tuesday = json.tuesday;--%>
        <%--var wednesday = json.wednesday;--%>
        <%--var thursday = json.thursday;--%>
        <%--var friday = json.friday;--%>

        <%--} else {--%>
        <%--$(".theadTd").hide();--%>
        <%--}--%>
        if (mapMap != '{}') {
//            var monday = json.monday;
//            var tuesday = json.tuesday;
//            var wednesday = json.wednesday;
//            var thursday = json.thursday;
//            var friday = json.friday;
//        for (var i = 0; i < totalHour; i++) {
//            $('<tr><td class="node">第' + (i + 1) + '节</td></tr>').appendTo(".tbody");
//        }
//        forWeek(monday, "node");
//            if (monday == undefined) {
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + ' name="monday" class="courseTeacher inputForUpdate" ></td>').appendTo(".monday");
//                }
//            }
//            if (tuesday == undefined) {
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="tuesday" class="courseTeacher inputForUpdate" ></td>').appendTo(".tuesday");
//                }
//            }
//            if (wednesday == undefined) {
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="wednesday" class="courseTeacher inputForUpdate" ></td>').appendTo(".wednesday");
//                }
//            }
//            if (thursday == undefined) {
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="thursday" class="courseTeacher inputForUpdate" ></td>').appendTo(".thursday");
//                }
//            }
//            if (friday == undefined) {
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value="" class="haveValueTd"><input type="hidden" index=' + i + '  name="friday" class="courseTeacher inputForUpdate"></td>').appendTo(".friday");
//                }
//            }
//
//            if (monday != undefined) {
//                forWeek(monday, "monday");
//            }
//            if (tuesday != undefined) {
//                forWeek(tuesday, "tuesday");
//            }
//            if (wednesday != undefined) {
//                forWeek(wednesday, "wednesday");
//            }
//            if (thursday != undefined) {
//                forWeek(thursday, "thursday");
//            }
//            if (friday != undefined) {
//                forWeek(friday, "friday");
//            }
        } else {

            if (refClassRoomId != "" && refClassRoomId != undefined && refClassRoomId != null) {
//                console.log(refClassRoomId);
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value=""><input type="text" index=' + i + ' name="monday" class="courseTeacher"></td>').appendTo(".monday");
//                }
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value=""><input type="text" index=' + i + '  name="tuesday" class="courseTeacher"></td>').appendTo(".tuesday");
//                }
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value=""><input type="text" index=' + i + '  name="wednesday" class="courseTeacher"></td>').appendTo(".wednesday");
//                }
//
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value=""><input type="text" index=' + i + '  name="thursday" class="courseTeacher"></td>').appendTo(".thursday");
//                }
//
//                for (var i = 0; i < totalHour; i++) {
//                    $('<td value=""><input type="text" index=' + i + '  name="friday" class="courseTeacher"></td>').appendTo(".friday");
//                }
            } else {
                <%--if ('${gradeClassId!=""&&gradeClassId!=null}') {--%>
                <%--$('<div class="info"><br><p style="color: red;size: 10px">班级教室信息没有匹配，请到教务班级教室目录下添加相关数据</p><br></div>').appendTo($(".info"));--%>
                <%--}--%>
            }

        }

        <%--var html = '<c:forEach items="${mapMap}" var="tableRow"><tr><td>${tableRow.monday}</td></tr><tr><td>${tableRow.tuesday}</td></tr><tr><td>${tableRow.wednesday}</td></tr><tr><td>${tableRow.thursday}</td></tr><tr><td>${tableRow.friday}</td></tr></c:forEach>';--%>
        <%--html.appendTo($(".tbody"));--%>
        $("select").change(function () {
            var cycleSemester = "";
            var cycleYear = $("select[name='cycleYear']").val();

            if ($(this).attr("name") == "cycleYear") {
                cycleSemester = "";
            } else {
                cycleSemester = $("select[name='cycleSemester']").val()
            }
            var sectionId = $(".section").find("option:selected").val();
            var sectionName = $(".section").find("option:selected").text();
            var nj = $("select[name='nj']").val();
            var gradeClass = $("select[name='gradeClass']").val();
            var week = $("select[name='week']").val();
            window.location.href = "${ctx}/teach/task/table?cycleSemester=" + cycleSemester + "&cycleYear=" + cycleYear + "&sectionId=" + sectionId + "&sectionName=" + encodeURI(encodeURI(sectionName)) + "&nj=" + nj + "&gradeClass=" + gradeClass + "&week=" + week;
        });
    });


    function deleteSure(id) {
        closeAlertDiv();
        $.post
        ("${ctx}/teach/task/cycle/do", {
            id: id,
            type: "delete",
        }, function (retJson) {
            window.location.reload();
        });
    }

    function importCallBack(res){
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表','关闭'] //按钮
        }, function(){
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","${ctx}/teach/task/table/error/export");
            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","msg");
            input1.attr("value",JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function(){
            window.location.reload(true);
        });
    }

    function forWeek(onetofive, name,index) {

        for (var i = 1; i <= skts; i++) {
            console.log(onetofive[i]);
            var nameForweek="";
            if (i==1) nameForweek="monday";
            if (i==2) nameForweek="tuesday";
            if (i==3) nameForweek="wednesday";
            if (i==4) nameForweek="thursday";
            if (i==5) nameForweek="friday";
            if(onetofive[i] !=undefined){
                var teachName = onetofive[i].teacherName;
                var coursename = onetofive[i].courseName;
                var id = onetofive[i].id;
                if (coursename == "") {
                    $('<td value="" class="haveValueTd"><input type="hidden" index="' + index + '" name="' + nameForweek + '"  class="courseTeacher inputForUpdate" ></td>').appendTo($("." + name));
                } else {
                    $('<td value=' + id + ' class="haveValueTd"><span class="havavalueSpan">' + teachName + '-' + coursename + '</span><input type="hidden" index=' + index + ' value="' + teachName + '-' + coursename + '" name="' + nameForweek + '"  class="courseTeacher inputForUpdate" ></td>').appendTo($("." + name));
//                            $('<td>'+teachName + '—' + coursename+'</td>').appendTo("." + name);
                }
            }else{
                $('<td value="" class="haveValueTd"><input type="hidden" index="' + index + '" name="' + nameForweek + '"  class="courseTeacher inputForUpdate" ></td>').appendTo($("." + name));
            }



//            for (var index in onetofive) {

//                var onedayIndex = oneDay[index];
//                for (values  in onedayIndex) {
//
//                    var onedayValues = onedayIndex[values];
//                    var id = onedayValues.id;
//                    var node = onedayValues.node;
//                    var coursename = onedayValues.courseName;
//                    if (coursename == undefined) {
//                        coursename = "";
//                    }
//                    var teachName = onedayValues.teacherName;
//                    if (teachName == undefined) {
//                        teachName = "";
//                    }
//                    console.log(teachName + "--" + coursename);

//        if (index == i) {
//                        if (coursename == "") {
//                            $('<td value="" class="haveValueTd"><input type="hidden" index="' + (i) + '" name="' + name + '"  class="courseTeacher inputForUpdate" ></td>').appendTo($("." + name));
//                        } else {
//                            $('<td value=' + id + ' class="haveValueTd"><span class="havavalueSpan">' + teachName + '-' + coursename + '</span><input type="hidden" index=' + (i) + ' value="' + teachName + '-' + coursename + '" name="' + name + '"  class="courseTeacher inputForUpdate" ></td>').appendTo($("." + name));
////                            $('<td>'+teachName + '—' + coursename+'</td>').appendTo("." + name);
//                        }
        }
//                }
//            }
    }
    activeMenu("all", 3);
</script>
</body>
</html>