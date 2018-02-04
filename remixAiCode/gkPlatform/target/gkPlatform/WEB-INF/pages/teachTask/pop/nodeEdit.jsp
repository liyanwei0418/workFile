<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
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
            padding: 12px 0px 10px 25px;
            font-size: 13px !important;
            color: #525252 !important;
        }

        table {
            /*width: 100%;*/
        }

        table td {
            font-size: 13px;
            /*text-align: right;*/
            padding: 10px 0;
        }

        table td span:first-child{
            width: 120px;
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
            margin-right: 20px;
        }
        .con-nightt{
            position: relative;
            bottom: 10px;
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

        table td span:before {
            content: '*';
            color: red;
            vertical-align: middle;
            margin-right: 3px;
        }
        .con-night:before{
            content: '' ! important;
        }
        .bodys {
            padding: 50px 0 0 50px;
        }

        .datainp {
            width: 200px;
            height: 30px;
            border: 1px #A5D2EC solid;
        }

        .datep {
            margin-bottom: 40px;
            line-height: 24px;
            margin-right: 15px;
        }

        .wicon {
            background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg==");
            background-repeat: no-repeat;
            background-position: right center;
        }
    </style>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <!-- jQuery -->

    <%--<script src="${ctxStatic}/js/jquery.js"></script>--%>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>

    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <script>

        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $("#inputForm").submit();
            return true;
        }
    </script>
</head>

<body>
<div class="container">
    <form method="post" action="${ctx}/teach/task/node/edit" id="inputForm">
    <input type="hidden" name="id" value="${courseNodeInitView.courseNodeInit.id}" class="nodeId">
    <div class="popup-main">
        <table style="margin-top: 30px">
            <tbody>
            <tr>
                <td class="datep">
                    <span>夏/冬令时时间段</span>
                    <select class="time_section">
                        <option name="cycleSemester" value="9月1日-9月30日" <c:if test="${'9月1日-9月30日'==courseNodeInitView.courseNodeInit.monthStartEnd}">selected</c:if>>9月1日-9月30日</option>
                        <option name="cycleSemester" value="10月1日-1月中旬" <c:if test="${'10月1日-1月中旬'==courseNodeInitView.courseNodeInit.monthStartEnd}">selected</c:if>>10月1日-1月中旬</option>
                        <option name="cycleSemester" value="2月中旬-4月30日"  <c:if test="${'2月中旬-4月30日'==courseNodeInitView.courseNodeInit.monthStartEnd}">selected</c:if>>2月中旬-4月30日</option>
                        <option name="cycleSemester" value="5月1日-7月中旬"  <c:if test="${'5月1日-7月中旬'==courseNodeInitView.courseNodeInit.monthStartEnd}">selected</c:if>>5月1日-7月中旬</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>早自习开始时间</span>
                    <input type="text" class="datainp wicon morningStart" id="date01" placeholder="00:00" readonly
                           onclick="testShow(this)">
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>早自习持续时长</span>
                    <input type="text" class="monrningPersistence" value="${courseNodeInitView.courseNodeInit.morningPersistence}">分钟(min)
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>普通节次持续时长</span>
                    <input type="text" class="commonPersistence" value="${courseNodeInitView.courseNodeInit.commonPersistence}">分钟(min)
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>最高年级课时数<span class="con-night">(包含早晚自习)</span></span>
                    <input type="text" class="total con-nightt" value="${courseNodeInitView.courseNodeInit.totalNode}">
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>下午上课开始时间</span>
                    <input type="text" class="datainp wicon afternoonStart" id="date02" placeholder="00:00" readonly
                           onclick="testShow(this)">
                </td>
            </tr>
            <tr>
                <td class="datep">
                    <span>晚自习开始时间</span>
                    <input type="text" class="datainp wicon nightStart" id="date03" placeholder="00:00" readonly
                           onclick="testShow(this)" >
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    </form>
</div>
</body>
<script type="text/javascript">
    function doSubmit() {

    }
    var cancel = {
        minDate: '2015-06-16',
        maxDate: '2017-12-16'
    }
    var opts = $.extend({
        type: "je",
        minDate: undefined,
        maxDate: undefined
    }, cancel);
    if (opts.type == "je") {
        $("#optsdate").jeDate({
            isinitVal: true,
            festival: true,
            trigger: "click mouseenter focus",
            isTime: false,
            ishmsVal: false,
            minDate: opts.minDate,
            maxDate: opts.maxDate,
            format: "YYYY-MM-DD hh:mm",
            zIndex: 3000,
            okfun: function (elem, val) {
                alert(elem)
            }
        })
    }

    //实现日期选择联动
    var start = {
        format: 'YYYY-MM-DD',
        minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
        //festival:true,
        maxDate: $.nowDate({DD: 0}), //最大日期
        choosefun: function (elem, datas) {
            end.minDate = datas; //开始日选好后，重置结束日的最小日期
            endDates();
        },
        okfun: function (elem, datas) {
            alert(datas)
        }
    };
    var end = {
        format: 'YYYY年MM月DD日',
        minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
        //festival:true,
        maxDate: '2099-06-16 23:59:59', //最大日期
        choosefun: function (elem, datas) {
            start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
        }
    };
    $("#date01").jeDate({
        isinitVal: true,
        //festival:true,
        //ishmsVal:false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate({DD: 0}),
        format: "hh:mm",
        zIndex: 3000,
    })

    $("#date02").jeDate({
        isinitVal: true,
        //festival:true,
        //ishmsVal:false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate({DD: 0}),
        format: "hh:mm",
        zIndex: 3000,
    })

    $("#date03").jeDate({
        isinitVal: true,
        //festival:true,
        //ishmsVal:false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate({DD: 0}),
        format: "hh:mm",
        zIndex: 3000,
    })
    function testShow(elem) {
        $.jeDate(elem, {
            trigger: false,
            isinitVal: true,
            //festival:true,
            //ishmsVal:false,
            minDate: '2016-06-16 23:59:59',
            maxDate: $.nowDate({DD: 0}),
            format: "hh:mm",
            zIndex: 3000,
        })
    }

</script>
</html>
