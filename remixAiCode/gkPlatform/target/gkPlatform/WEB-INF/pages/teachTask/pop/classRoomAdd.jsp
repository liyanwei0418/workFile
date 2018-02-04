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
            color: #525252 !important;
        }

        table {
            /*width: 100%;*/
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

        table td input[type="radio"] {
            margin-right: 8px;
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

        .necessary:before {
            content: '*';
            color: red;
            vertical-align: middle;
            margin-right: 3px;
        }
    </style>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/easyform.js"></script>

</head>

<body>
<div class="container">
    <form method="post" action="${ctx}/teach/task/room/update" id="inputForm">
        <input type="hidden" name="id" value="${room.id}">

        <div class="popup-main">
            <table>
                <tbody>
                <tr>
                    <td>
                        <span class="necessary">所在校区:</span>
                        <select name="schoolTypeSelect">
                            <c:forEach items="${schoolTypeList}" var="schoolType">
                                <option
                                        <c:if test="${room.schoolType eq schoolType.id}">selected</c:if>
                                        value="${schoolType.id}">${schoolType.name}</option>
                            </c:forEach>
                        </select>
                        <input name="schoolType" type="hidden"/>
                        <input name="schoolTypeName" type="hidden"/>
                    </td>
                    <td>
                        <span class="necessary">教室类型:</span>
                        <select name="roomTypeSelect" id="">
                            <c:forEach items="${roomTypeList}" var="roomType">
                            <option
                                    <c:if test="${room.roomType eq roomType.id}">selected</c:if>
                                    value="${roomType.id}">${roomType.name}</option>
                            </c:forEach>
                            <input name="roomType" type="hidden"/>
                            <input name="roomTypeName" type="hidden"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="necessary">所在楼:</span>
                        <input type="text" name="teachBuilding" value="${room.teachBuilding}"
                               easyform="length:0-16;char-chinese;real-time;" message="必填项"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                    <td>
                        <span class="necessary">容纳人数:</span>
                        <input type="text" name="count" value="${room.count}" easyform="uint:1 999;real-time;" message="容纳人数1-999正整数"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="necessary">楼层:</span>
                        <input type="text" name="floor" value="${room.floor}" easyform="uint:1 100;char-normal;real-time;" message="楼层1-100正整数"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                    <td>
                        <span class="necessary">有效座位数:</span>
                        <input type="text" name="availableSeat" value="${room.availableSeat}" easyform="uint:1-999;real-time;" message="有效座位数1-999"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="necessary">房间号:</span>
                        <input type="text" name="roomNum" value="${room.roomNum}" easyform="length:0-16;char-chinese;real-time;" message="必填项"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                    <td>
                        <span class="necessary">考试座位数:</span>
                        <input type="text" name="examSeat" value="${room.examSeat}" easyform="uint:1-999;real-time;" message="考试座位数1-999"
                               easytip="position:top;disappear:other;theme:blue;">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left; padding: 20px 0 20px 0;">
                        <span>是否用于选课:</span>
                        <label name="yes" for="yes" style="margin-left: 12px;"></label>
                        <input type="radio" name="courseSelect" value="1" id="yes"
                               <c:if test="${room.courseSelect==1}">checked="checked"</c:if>>是
                        <label name="no" for="no" style="margin-left: 50px;"></label>
                        <input type="radio" name="courseSelect" value="2" id="no"
                               <c:if test="${room.courseSelect==2}">checked="checked"</c:if>>否
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <span style="width: auto">备注:</span>
                        <textarea name="remarks">${room.remarks}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <input type="submit" id="submitButton" hidden />
    </form>
</div>

<script>

    $(function () {

        $("input[name='schoolType']").val($("select[name='schoolTypeSelect']").val());
        $("input[name='schoolTypeName']").val($("select[name='schoolTypeSelect'] option:selected").text());

        $("input[name='roomType']").val($("select[name='roomTypeSelect']").val());
        $("input[name='roomTypeName']").val($("select[name='roomTypeSelect'] option:selected").text());

        $("select[name='schoolTypeSelect']").change(function () {
            $("input[name='schoolType']").val($("select[name='schoolTypeSelect']").val());
            $("input[name='schoolTypeName']").val($("select[name='schoolTypeSelect'] option:selected").text());
        })

        $("select[name='roomTypeSelect']").change(function () {
            $("input[name='roomType']").val($("select[name='roomTypeSelect']").val());
            $("input[name='roomTypeName']").val($("select[name='roomTypeSelect'] option:selected").text());
        })
    })

    $(document).ready(function () {
        $('#inputForm').easyform();
    });

    function doSubmit() {
        //回调函数，在编辑和保存动作时，供openDialog调用提交表单。

        $("#submitButton").click();
        var flag = $("#easytip-div-main").is(":visible");

        if (flag) {
            //有错误提示，不允许提交
//            layer.msg("参数填写有误");
            return;
        } else {
            $('#inputForm').submit();
            return true;
        }
    }
</script>
</body>
</html>
