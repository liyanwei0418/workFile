<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ include file="../../common/headerXf.jsp" %>--%>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级教室编辑</title>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
</head>
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
        color: #525252 !important;
    }

    table {
        margin-top: 20px;
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

    table td input[type="text"], table td select {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 10px;
        padding-left: 10px;
        outline: none;
        border: 1px solid #dadada;
        border-radius: 2px;
        color: #333;
    }

    .cla-sp {
        width: 150px;
        height: 28px;
        line-height: 28px;
        margin-left: 14px;
    }

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

    .btn-containt button {
        border: 1px solid #54ab37;
        border-radius: 2px;
        background: #54AB37;
        color: #fff;
        width: 70px;
        height: 30px;
        line-height: 30px;
        margin-top: 20px;
    }
</style>
<div style="padding-left: 30px;">
    <table>
        <input type="hidden" value="${refClassRoom.id}" class="refId">
        <tr class="njbanji">
            <td><span>年级班级:</span><span
                    class="cla-sp">${classSection.name}${gradeClass.nj}年级${gradeClass.name}</span>
            </td>
        </tr>

        <tr class="first">
            <td><span>校区:</span>
                <select name="schoolType" class="schoolType">
                    <option value="0">请选择校区</option>
                    <c:forEach items="${schoolTypeList}" var="schoolType" varStatus="status">
                        <option
                                <c:if test="${refClassRoom.schoolTypeId==schoolType.id}">selected</c:if>
                                value="${schoolType.id}">${schoolType.name}</option>
                    </c:forEach>
                </select></td>
        </tr>
        <tr class="first">
            <td><span>教学楼:</span>

                <select name="teachBuilding" class="teachBuilding">
                    <%--<c:forEach items="${buildingList}" var="building" varStatus="status">--%>
                        <option value=""
                                <%--<c:if test="${classRoom.teachBuilding==building.teachBuilding}">selected</c:if>--%>>请选择</option>
                    <%--</c:forEach>--%>
                </select></td>
        </tr>
        <tr class="first">
            <td><span>楼层:</span>
                <select name="floor" class="floor">
                    <%--<c:forEach items="${classRoomList}" var="floor" varStatus="status">--%>
                        <option value="0"
                                <%--<c:if test="${floor.floor==classRoom.floor}">selected</c:if>--%>>请选择</option>
                    <%--</c:forEach>--%>
                </select></td>
        </tr>
        <tr class="first">
            <td><span>教室号:</span>
                <select name="roomNum" class="roomNum">
                    <%--<c:forEach items="${classRoomList}" var="roomNum" varStatus="status">--%>
                        <option value=""
                               <%-- <c:if test="${classRoom.roomNum==roomNum.roomNum}">selected</c:if>--%>>请选择</option>
                    <%--</c:forEach>--%>
                </select>
            </td>
        </tr>
    </table>
</div>
</body>
<script>
    $(function () {
        //位置级联参数
        var option ='${option}';
        if(option=="edit"){
            //-----编辑班牌时回显位置---------
            var xqId ='${classRoom.schoolType}';
            var teachBuilding = '${classRoom.teachBuilding}';
            var floor = '${classRoom.floor}';
            var paras = {};
            paras.xqId = xqId;
            paras.flag = 'xq';
            paras.teachBuilding = '';
            paras.floor = '';
            cascade_location(paras,false);

            paras.teachBuilding = teachBuilding;
            paras.flag = 'teachBuilding';
            cascade_location(paras,false);

            paras.flag = 'floor';
            paras.floor = floor;
            cascade_location(paras,false);
        }
            $(".schoolType").change(function () {
            $(".teachBuilding").empty();
            $(".floor").empty();
            $(".roomNum").empty();
            $(".teachBuilding").append("<option value=''>请选择楼名称</option>");
            $(".floor").append("<option value= ''>请选择楼层</option>");
            $(".roomNum").append("<option value=''>请选择房间号</option>");
            _param["flag"] = "xq";
            determineLocationParam();
            cascade_location(_param, true)
        });
        $(".teachBuilding").change(function () {
            $(".floor").empty();
            $(".roomNum").empty();
            $(".floor").append("<option value=''>请选择楼层</option>");
            $(".roomNum").append("<option value=''>请选择房间号</option>");
            _param["flag"] = "teachBuilding";
            // alert(_param.flag+'------');
            determineLocationParam();
            cascade_location(_param, true)
        });
        $(".floor").change(function () {
            $(".roomNum").empty();
            $(".roomNum").append("<option value=''>请选择房间号</option>");
            _param["flag"] = "floor";
            // alert(_param.flag+'------');
            determineLocationParam();
            cascade_location(_param, true)
        });
    })

    function doSubmit() {
        var refId = $(".refId").val();
        var schoolTypeId = $(".schoolType").find("option:selected").val();
//                var teachBuilding = $(".teachBuilding").find("option:selected").val();
//            var floor = $(".floor").find("option:selected").val();
        var roomId = $(".roomNum").find("option:selected").val();

        $.post("${ctx}/teach/task/ref/class/room/edit", {
            refId: refId,
            schoolTypeId: schoolTypeId,
//                    teachBuilding: encodeURI(teachBuilding),
//                    floor:floor,
            roomId: roomId
        }, function (data) {
            setTimeout(function () {
                parent.location.reload();
            }, 400);
        });
    }

    <%--function confirmSave() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。--%>
    <%--var refId = $(".refId").val();--%>
    <%--var schoolTypeId = $(".schoolType").find("option:selected").val();--%>
    <%--var teachBuilding = $(".teachBuilding").find("option:selected").val();--%>
    <%--var roomId = $(".roomNum").find("option:selected").val();--%>
    <%--$.post("${ctx}/teach/task/ref/class/room/edit", {--%>
    <%--refId: refId,--%>
    <%--schoolTypeId: schoolTypeId,--%>
    <%--teachBuilding: teachBuilding,--%>
    <%--roomId: roomId,--%>
    <%--refId: refId--%>
    <%--}, function (data) {--%>
    <%--layer.msg("保存成功");--%>
    <%--location.reload();--%>
    <%--});--%>
    <%--}--%>

    var _param = {};

    function determineLocationParam() {
        _param["xqId"] = $('.schoolType').val();
        _param["teachBuilding"] = $('.teachBuilding').val();
        _param["floor"] = $('.floor').val();
    }

    //位置级联方法
    function cascade_location(param, isclick) {
        $.ajax({
            url: "${ctx}/classcard/cascadeclassroom",
            type: "post",
            data: {
                'mydata': JSON.stringify(param)
            },
            success: function (data) {
                if (data.code = 1) {
                    var flg = data.data.flag;
                    var _classRomms = data.data.classRomms;
                    if (flg == 'xq') {
                        for (var i = 0; i < _classRomms.length; i++) {
                            var classRoomTeachBuilding = '${classRoom.teachBuilding}';
                            var tmp = _classRomms[i].teachBuilding;
                            var html = "<option value='" + _classRomms[i].teachBuilding + "'"
                            if (isclick == false) {
                                if (classRoomTeachBuilding == tmp) {
                                    html += "selected";
                                }
                            }
                            html += ">" + _classRomms[i].teachBuilding + "</option>"
                            $('.teachBuilding').append(html);

                        }
                    } else if (flg == 'teachBuilding') {
                        for (var i = 0; i < _classRomms.length; i++) {
                            var classRoomFloor = '${classRoom.floor}';
                            var tmp = _classRomms[i].floor;
                            var html = "<option value='" + _classRomms[i].floor + "'";
                            if (isclick == false) {
                                if (classRoomFloor == tmp) {
                                    html += "selected";
                                }
                            }
                            html += ">" + _classRomms[i].floor + "</option>"
                            $('.floor').append(html);
                        }
                    } else if (flg == 'floor') {
                        for (var i = 0; i < _classRomms.length; i++) {
                            var classRoomRoomNum = '${classRoom.roomNum}';
                            var tmp = _classRomms[i].roomNum;
                            var html = "<option value='" + _classRomms[i].id + "'";
                            if (isclick == false) {
                                if (classRoomRoomNum == tmp) {
                                    html += "selected";
                                }
                            }
                            html += ">" + _classRomms[i].roomNum + "</option>";
                            $('.roomNum').append(html);

                        }
                    }
                }
            },
            error: function (e) {
                layer.msg("暂无相关数据");
            }
        })
    }

</script>
</html>

