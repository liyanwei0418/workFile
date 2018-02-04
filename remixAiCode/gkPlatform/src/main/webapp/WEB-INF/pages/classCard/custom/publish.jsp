<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>发布界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jeDate-test.css">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/upload/h5upload.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>

    <style>
        * {
            box-sizing: border-box;
        }

        .container {
            width: 800px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 13px '微软雅黑';
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .container > h3 button {
            float: right;
            color: #fff;
            border-radius: 2px;
            background: #54AB37;
            border: 1px solid #54AB37;
            width: 80px;
            height: 35px;
            font-weight: bold
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        #checkedEquipment{
            width: auto;
            max-width: 525px;
            margin-right: 10px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }

        .stuMsg span {
            display: inline-block;
            text-align: right;
            margin-right: 10px;
        }

        .stuMsg input[type=text] {
            width: 150px;
            height: 28px;
            padding: 0;
            padding-left: 5px;
            border-radius: 3px;
            border: 1px solid #dadada;
            outline: none;
        }

        .stuMsg input[type=radio] {
            margin: 0 5px 0 2px;
            position: relative;
            top:2px;
        }

        .stuMsg input[class=laydate-icon] {
            width: 190px;
            border: 1px solid #dadada;
        }

        ul li {
            position: relative;
            margin: 15px 0;
        }

        b {
            font-size: 20px;
            color: #E51C23;
            position: absolute;
            top: 4px;
            right: 76px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #333;
            width: 150px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #dadada;
            border-radius: 2px;
            outline: none;
        }

        .uploading {
            display: inline-block;
            vertical-align: -600%;
            width: 60%;
            text-align: center;
        }

        .uploading p {
            width: 90px;
            height: 86px;
            background: url('${ctxStatic}/image/image.png');
            margin: 0;
            margin-left: 104px;
        }

        .uploading button {
            margin-top: 10px;
            padding: 5px 20px;
            color: #fff;
            background: #54AB37;
            border: 1px solid #54AB37;
            font-weight: bold;
        }

        .parentMsg P {
            FONT-SIZE: 14PX;
            padding-left: 11px;
        }

        .parentMsg ul {
            overflow: hidden;
            box-sizing: border-box;
        }

        .parentMsg ul li {
            float: left;
            width: 50%;
            margin: 15px 0
        }

        .parentMsg ul li span {

            display: inline-block;
            width: 36%;
            text-align: right;
        }

        .parentMsg input[type=text] {
            height: 28px;
            padding: 0;
            padding-left: 5px;
            width: 190px;
            border: 1px solid #dadada;
            border-radius: 2px;
            outline: none;
        }

        .parentMsg select {
            width: 190px;
            height: 28px;
            border-radius: 2px;
            outline: none;
        }

        .container table {
            border-collapse: collapse
        }

        .container table th, .container table td {
            border: 1px solid #ddd;
            padding: 10px 0;
        }

        .container table th {
            background: #eee;
        }

        .addContent {
            font-size: 16px;
            color: #999;
            padding-left: 15px;
        }

        div input[type='checkbox'] {
            position: relative;
            top: 2px;
        }

        div > i {
            display: inline-block;
            font-style: normal;
            margin-right: 22px;
            width: 58px;
        }

        .time-conatiner {
            padding-left: 60px;
            margin-top: 20px;
        }

        .time-conatiner span {
            margin-right: 10px;
        }

        .day-time input {
            width: 150px;
            line-height: 24px;
            border: 1px solid #dadada;
            padding-left: 5px;
            border-radius: 2px;
            outline: none !important;
        }

        .wicon {
            background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg==") no-repeat right center;
        }

        .row {
            margin-bottom: 20px;
        }

        .row p, .row label {
            width: 20%;
            display: inline-block;
            text-align: right;
        }

        #autoComplete {
            height: 23px;
            width: 150px;
            padding-left: 5px;
            border: 1px solid #dadada;
            border-radius: 3px;
            padding-left: 5px;
            outline: none;
        }

        #autoComplete {
            height: 28px;
        }


        .recyle-mode{
            width: 60%;
            float: left;
            padding-top: 10px;
        }
        .recyle-mode>div{
            margin-bottom: 10px;
        }
        .time-box{
            display: inline-block;
            width: 70%;
            vertical-align: top;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>配置发布的信息</h3>
    <div class="stuMsg">
        <%--<p>pageName:${customPage.name}  createBY ${customPage.createByName}</p>--%>

        <div class="content">
                <input type="hidden" id="pageId" value="${customPage.id}">
            <div style="overflow: hidden;">
                <div class="jeitem">
                    <label class="jelabel">开始时间：</label>
                    <div class="jeinpbox">
                        <input type="text" class="jeinput" id="startTime" placeholder="" onfocus="timeShow(this)"
                               value="${customPage.startTimeView}" name="startTime" readonly>
                    </div>
                    <label class="jelabel">结束时间:</label>

                    <div class="jeinpbox">
                        <input type="text" class="jeinput" id="endTime" placeholder="" onfocus="timeShow(this)"
                               value="${customPage.endTimeView}" name="endTime" readonly>
                    </div>

                </div>

                <div class="jeitem">
                    <label class="jelabel">循环模式</label>
                    <%--<div class="jeinpbox">--%>

                    <%--</div>--%>
                    <div class="recyle-mode">
                        <div>
                            <input type="radio"  name="loopDate" value="D">
                            <span>按天</span>
                        </div>
                        <div>
                            <input type="radio"  name="loopDate" value="W">
                            <span>按周</span>
                            <div class="time-box">
                                <i><input type="checkbox" name="loopWeek" value="1">
                                    星期一 </i>
                                <i><input type="checkbox" name="loopWeek" value="2">
                                    星期二 </i>
                                <i><input type="checkbox" name="loopWeek" value="3">
                                    星期三 </i>
                                <i><input type="checkbox" name="loopWeek" value="4">
                                    星期四 </i>
                                <i><input type="checkbox" name="loopWeek" value="5">
                                    星期五 </i>
                                <i><input type="checkbox" name="loopWeek" value="6">
                                    星期六 </i>
                                <i><input type="checkbox" name="loopWeek" value="7">
                                    星期日 </i>
                                <input type="hidden" value="${classCardConfigScreen.get(0).screenOffWeek}" id="">
                            </div>
                        </div>

                        <div>
                            <input type="radio" name="loopDate" value="M">
                            <span>按月</span>
                            <div class="time-box">
                                <i><input type="checkbox" name="loopMonth" value="1">
                                    一月 </i>
                                <i><input type="checkbox" name="loopMonth" value="2">
                                    二月 </i>
                                <i><input type="checkbox" name="loopMonth" value="3">
                                    三月 </i>
                                <i><input type="checkbox" name="loopMonth" value="4">
                                    四月 </i>
                                <i><input type="checkbox" name="loopMonth" value="5">
                                    五月 </i>
                                <i><input type="checkbox" name="loopMonth" value="6">
                                    六月 </i>
                                <i><input type="checkbox" name="loopMonth" value="7">
                                    七月 </i>
                                <i><input type="checkbox" name="loopMonth" value="8">
                                    八月 </i>
                                <i><input type="checkbox" name="loopMonth" value="9">
                                    九月 </i>
                                <i><input type="checkbox" name="loopMonth" value="10">
                                    十月 </i>
                                <i><input type="checkbox" name="loopMonth" value="11">
                                    十一月 </i>
                                <i><input type="checkbox" name="loopMonth" value="12">
                                    十二月 </i>
                                <input type="hidden" value="${classCardConfigScreen.get(0).screenOffWeek}" id="loopMonth">
                            </div>
                        </div>

                        <div><input type="radio" name="loopDate" value="N"><span>不循环</span></div>
                    </div>
                </div>



                <div class="jeitem">
                    <label class="jelabel">
                        <%--<c:if test="${option=='addconfig'}">
                            选择设备
                        </c:if>
                        <c:if test="${option=='edit'}">
                            查看设备
                        </c:if>--%>
                        查看设备
                    </label>
                    <div class="jeinpbox">
                        <button id="selectButton" class="roll-add" style="height: 30px;padding: 0 15px;margin-right: 4px;border:
                        1px solid #54ab37; background: #54ab37; color: #fff;border-radius: 2px;"
                        <%--<c:if test="${option=='addconfig'}">
                            onclick="openDialog('选择','${ctx}/classcard/config/chooseclasscardConfig?option=${option}&checkedIds=${checkedIds}','800px','500px');">选择
                        </c:if>
                        <c:if test="${option=='edit'}">
                            onclick="openDialog('查看','${ctx}/classcard/config/chooseclasscardConfig?option=${option}&checkedIds=${checkedIds}','800px','500px');">查看
                        </c:if>--%>
                                onclick="openDialog('选择','${ctx}/classcard/config/chooseclasscardConfig?option=${option}&checkedIds=${checkedIds}','800px','500px');">选择
                        </button>
                        <input type="hidden" value="${checkedIds}" id="checkedIds">
                        <input type="hidden" value="" id="unCheckedIds">
                    </div>
                </div>
            </div>
                <div class="jeitem" style="display: block;float:none;">
                    <label class="jelabel" style="float: none;display: inline-block;padding-top: 10px;">
                        已选择的设备
                    </label>
                    <div class="jeinpbox" style="float: none;display: inline-block;">
                        <div id="checkedEquipment"></div>
                    </div>
                </div>
                <div class="">
                    <%--<label class="jelabel">--%>
                        <%--发布自定义界面会进行发布设备时间段判断--%>
                    <%--</label>--%>
                    <div style="margin-top: 20px;">
                        <button class="roll-det" onclick="doSubmit()" style="padding: 4px 8px; background: #54ab37;color: #fff;">发布自定义界面</button>
                    </div>
                </div>
        </div>
    </div>
</div>

<script>
    //时间插件
    function timeShow(element) {
        $.jeDate(element, {
            format: "hh:mm"
        });
    }
    //将已选中的循环周期回填
    $(function () {
        $(":radio[name='loopDate']").prop("checked",false);
        var loopMark = '${customPage.loopMark}';
        $(":radio[name='loopDate'][value=" + loopMark + "]").prop("checked",true);
        if(loopMark == 'W') {
            $(":checkbox[name='loopWeek']").prop("checked",false);
            var loopDate = '${customPage.loopDate}'.split(",");
            $.each(loopDate,function (index,val) {
                $(":checkbox[name='loopWeek'][value=" + val +"]").prop("checked",true);
            });
        } else if(loopMark == 'M') {
            $(":checkbox[name='loopMonth']").prop("checked",false);
            var loopDate = '${customPage.loopDate}'.split(",");
            $.each(loopDate,function (index,val) {
                $(":checkbox[name='loopMonth'][value=" + val +"]").prop("checked",true);
            });
        }
    });
    //选择发布设备后数据返回
    function chooseResult(checkedIds, unCheckedIds, checkedName) {
        $("#checkedEquipment").empty();
        var names = "";
        var checkedName = checkedName.split(",");
        for (var i = 0; i < checkedName.length; i++) {
            names += "<i>" + checkedName[i] + "</i> &nbsp;";
            if ((i+1) % 5 == 0){
                names += "<br>";
            }
        }
        $('#checkedIds').val(checkedIds);
        $('#unCheckedIds').val(unCheckedIds);
        $("#checkedEquipment").append(names);
        $("#selectButton").attr("onclick", "openDialog('修改','${ctx}/classcard/config/chooseclasscardConfig?option=edit&checkedIds=" + checkedIds + "','800px','500px')");
    }

    function getMark() {
        var loopMark = 'D';
        $('input[name="loopDate"]:checked').each(function() {
           loopMark = $(this).val();
        });
        return loopMark;
    }

    function getLoopDate(loopMark) {
        if(loopMark == '') {
            loopMark = getMark();
        }
        var loopDate = '';
        if(loopMark == 'W') {
            $('input[name="loopWeek"]:checked').each(function () {
               loopDate += $(this).val() + ',';
            });
        } else if(loopMark == 'M') {
            $('input[name="loopMonth"]:checked').each(function () {
                loopDate += $(this).val() + ',';
            });
        }
        return loopDate;
    }

    function doSubmit() {
        //1.获取需要提交的数据
        var pageId = $('#pageId').val();
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var loopMark = getMark();
        var loopDate = getLoopDate(loopMark);
        var classCardList = $('#checkedIds').val();
        //2.判断数据的合理性

        //3.ajax提交数据
        var url = "${ctx}/classcard/custom/publish";
        $.post(url, {
            pageId: pageId,
            startTime: startTime,
            endTime: endTime,
            classCardList: classCardList,
            loopMark:loopMark,
            loopDate:loopDate
        }, function (retJson) {
            if(retJson.code == 0) {
                layer.msg(retJson.data);
                window.parent.closeAlertDiv();
                window.parent.location.reload();
            } else {
                layer.msg(retJson.msg);
                setTimeout(function () {
                    window.location.reload();
                    //这里有个小bug，在发布失败以后，修改的时间和周期已经保存(设备失败不保存)，返回主页后没有刷新，需要手动F5刷新更新数据
                    //怎么能在点击了取消和关闭发布弹窗以后触发index(父窗体)刷新事件。
                    //window.parent.location.reload();
                }, 1000);//发布失败
            }
        }, "json");
    }
</script>
</body>
</html>

