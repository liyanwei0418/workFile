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
            padding: 35px 0px 10px 0;
            font-size: 14px !important;
        }
        .popup-main .times{
            margin-bottom: 20px;
        }
        .popup-main .times span, .popup-main .names span{
            width:100px;
            text-align: right;
            margin-right: 10px;
        }
        .popup-main .times input{
            width: 150px;
        }
        .popup-main .names input{
            width: 200px;
        }
        input{
            height: 28px;
            border: 1px solid #ddd;
            border-radius: 2px;
            outline:none;
            padding-left: 10px;
        }
        table {
            /*width: 100%;*/
            margin-top: 15px;
        }

        table td {
            font-size: 13px;
            /*text-align: right;*/
            padding: 10px 0;
        }

        table td span:first-child {
            width: 100px;
            text-align: right;
        }

        table td input[type="text"], table td select {
            width: 115px;
            height: 28px;
            line-height: 28px;
            margin-left: 46px;
            padding-left: 10px;
            outline: none;
            border: 1px solid #dadada;
            border-radius: 2px;
            color: #333;
            vertical-align: top;
            margin-right: 20px;
            position: relative;
            bottom: 5px;
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



        .datainp {
            width: 200px;
            height: 30px;
            border: 1px #A5D2EC solid;
        }


        .wicon {
            background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg==");
            background-repeat: no-repeat;
            background-position: right center;
        }

        .jedatebox{
            width:185px !important;
        }
        .jedateblue .jedatehmscon:last-child,.jedateblue .jedateproptext:last-child {
            display: none !important;
        }
        #jedatebox{
            position: fixed !important;
        }


        .trAdd{
            background: url(${ctxStaticNew}/images/tradd.png) no-repeat left;
        }
        .trDecrease{
            background: url(${ctxStaticNew}/images/trdele.png) no-repeat left;
        }
        .layui-layer-content{
            height: auto !important;
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
    <%--<form method="post" action="${ctx}/teach/task/node/add" id="inputForm">--%>

    <div class="popup-main ">
        <input type="hidden" value="${cycleId}" class="cycleId">
        <input type="hidden"  value="${courseNodeInit.id}" class="nodeInitId">
        <div class="times">
            <span>适用周期:</span>
            <input type="text" class="startweek" value="${courseNodeInit.startWeek}">&nbsp;&nbsp;——&nbsp;&nbsp;<input
                type="text" class="endweek" value="${courseNodeInit.endWeek}">&nbsp;&nbsp;
        </div>
        <div class="names">
            <span>名称:</span>
            <input type="text" class="initName" value="${courseNodeInit.name}">
        </div>
        <div style="width: 570px; border-bottom: 1px solid #dadada;margin: 25px  0 0 40px;"></div>
            <table>
                <tbody class="nodeTableTbody">
                <tr>
                    <td><span>节次名称:</span></td>
                    <td><span>开始时间:</span></td>
                    <td><span>结束时间:</span></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:if test="${courseNodeListSize==0}">
                    <tr>
                        <td><input type="text" value="${courseNode.nodeName}"></td>
                        <td><input type="text" class="datainp wicon" placeholder="00:00" readonly
                                   onclick="testShow(this)"></td>
                        <td><input type="text" class="datainp wicon" placeholder="00:00" readonly
                                   onclick="testShow(this)"></td>
                        <td><i class="trDecrease" onclick="deletr(this)"
                               style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i>
                        </td>
                        <td><i class="trAdd" onclick="addtr()"
                               style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i>
                        </td>

                    </tr>
                </c:if>
                <c:forEach items="${courseNodeExtentions}" var="courseNodeExtention">
                    <tr>
                        <td><input type="text" value="${courseNodeExtention.nodeName}"></td>
                        <td><input type="text" class="datainp wicon" placeholder="00:00" readonly
                                   onclick="testShow(this)" value="${courseNodeExtention.startTime}"></td>
                        <td><input type="text" class="datainp wicon" placeholder="00:00" readonly
                                   onclick="testShow(this)" value="${courseNodeExtention.endTime}"></td>
                        <td><i class="trDecrease" onclick="deletr(this)"
                               style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i>
                        </td>
                        <td><i class="trAdd" onclick="addtr()"
                               style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

    </div>
</body>
<script type="text/javascript">

    function addtr() {
        $('tr').eq($('tr').length - 1).children('td').eq(3).show();
        $('<tr><td><input type="text" ></td><td><input type="text" class="datainp wicon" placeholder="00:00" readonly onclick="testShow(this)"></td> <td><input type="text" class="datainp wicon morningStart"  placeholder="00:00" readonly onclick="testShow(this)"></td><td><i class="trDecrease" onclick="deletr(this)" style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i></td>  <td><i class="trAdd"  onclick="addtr()" style="display: inline-block;width:20px;height:20px;border-radius: 50%;margin: 0 10px 10px 0;"></i></td> </tr>').appendTo($(".nodeTableTbody"));
        $('.trAdd').hide();
        $('.trAdd').last().show();
    };

    function deletr(trcurrent) {
        var length = $(".trDecrease").length;
        if(length <= 1) return;
        $(trcurrent).parents('tr').remove();
        $('.trAdd').hide();
        $('.trAdd').last().show();
    };

    function doSubmit() {
        var nodeInitId = $(".nodeInitId").val();
        var cycleId = $(".cycleId").val();
        var startweek = $(".startweek").val();
        var endweek = $(".endweek").val();
        var initName = $(".initName").val();

        if (initName == ""||initName==null) {
            layer.msg("所填项均为必填");
            return;
        }

        var reg =  "^[0-9]*[1-9][0-9]*$";
        if (!startweek.match(reg)||!endweek.match(reg)) {
            layer.msg("数据格式不正确");
            return;
        }

        <%--if (startweek < '${courseNodeInitEndWeek}') {--%>
            <%--layer.msg("开始周不能小于上一个时间表的结束周");--%>
            <%--return;--%>
        <%--}--%>

        <%--if (endweek > '${endweek}') {--%>
            <%--layer.msg("时间表的结束周不能大于教学周期的结束周");--%>
            <%--return;--%>
        <%--}--%>

        var length = $('.nodeTableTbody tr').length;

        var arr = [];
        var index= 0;
        $('.nodeTableTbody tr').each(function (a, b) {
           if (a !=0){
               var nodeName = "";
               var start = "";
               var end = "";
               console.log($(this).find('input'));
               $(this).find('input').each(function (i) {
                   console.log($(this));
                   var thisInputValue = $(this).val();
                   console.log($(this).val());
                   if (i == 0) {
                       nodeName = thisInputValue;
                   } else if (i == 1) {
                       start = thisInputValue;
                   } else {
                       end = thisInputValue;
                   }
               })
               if (nodeName=="" ||start==""||end==""){
                   layer.msg("您所填数据为空的部分将不会保存,请确保数据填写正确");
                   return;
               }
               var json = {"node":index,"nodeName": nodeName, "startTime": start, "endTime": end};
               index++;
               arr.push(json);
           }
        });

        $.post("${ctx}/teach/task/node/add/new", {
            arr: JSON.stringify(arr),
            cycleId: cycleId,
            startweek: startweek,
            endweek: endweek,
            initName: initName,
            nodeInitId:nodeInitId
        }, function (data) {
            layer.msg("操作成功");
            setTimeout(function () {
                parent.location.reload();
            }, 3000);
        })
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
        });

        $('.jedatehms li input').on('focus', function () {
            $(this).blur();
        })
        $('.jedateblue .jedateproptext:eq(2)').hide();
        $('#jedatebox').css('top', '80px');
        $('.jedateblue .jedatebot .jedatehms').css({
            'border':'0',
            'background':'none'
        });
        $('.jedatehms input:eq(2)').hide();
    }

</script>
</html>
