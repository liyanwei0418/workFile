<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <style>
        *{
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

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg span {
            display: inline-block;
            width: 36%;
            text-align: right;
            margin-right: 10px;
        }

        .stuMsg input[type=text] {
            width: 150px;
            height: 28px;
            padding:0;
            padding-left:5px;
            border-radius:3px;
            border:1px solid #dadada;
            outline: none;
        }

        .stuMsg input[type=radio] {
            margin: 0 20px;
        }

        .stuMsg input[class=laydate-icon] {
            width: 190px;
            border: 1px solid #dadada;
        }

        .stuMsg label {
            margin-right: 61px;
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
            padding-left:11px;
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
            padding:0;
            padding-left:5px;
            width:190px;
            border:1px solid #dadada;
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



        div input[type='checkbox']{
            position: relative;
            top:2px;
        }
        div>i{
            font-style: normal;
            margin-right: 22px;
        }
        .time-conatiner{
            padding-left: 60px;
            margin-top: 20px;
        }
        .time-conatiner span{
            margin-right: 10px;
        }
        .day-time input{
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
            margin-bottom: 20px;:
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
        .jedatebox{
            width:185px !important;
        }
        .jedateblue .jedatehmscon:last-child,.jedateblue .jedateproptext:last-child {
            display: none !important;
        }


    </style>

    <script>
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var tipmac = $(".completeTipsmac").css("display");
            var tiproom = $(".completeTipsroom").css("display");
            var tipclass = $(".completeTipsclass").css("display");
            if ( tipmac == "none" && tiproom == "none" && tipclass == "none") {
                var macId = $("input[name='macId']").val();
                var equipmentName= $("input[name='equipmentName']").val();
                var classroom= $("input[name='classroom']").val();

                if (macId == '') {
                    layer.msg("终端MAC地址不能为空！");
                    return false;
                }

                if (equipmentName == '') {
                    layer.msg("设备名称不能为空！");
                    return false;
                }

                if (classroom == '') {
                    layer.msg("班牌显示名称不能为空！");
                    return false;
                }

                var teachClassRoomId=$('#roomNum').val();
                var classId=$('#teach_class').val();
                if(teachClassRoomId==0 ||teachClassRoomId==''){
                    layer.msg("房间号不能为空！");
                    return false;
                }
                /*if(classId==0 ||classId==''){
                    layer.msg("班级不能为空！");
                    return false;
                }*/
                var bootDayOfWeek='';
                $(":checkbox").each(function () {
                    if(this.checked){
                        bootDayOfWeek+=$(this).parent().text()+",";
                    }
                })
                $.post($("form").attr('action'), {
                    macId: macId,
                    classCardId:$("input[name='classCardId']").val(),
                    equipmentName:equipmentName,
                    classroom:classroom,
                    teachClassRoomId:teachClassRoomId,
                    classId:classId,
                    id:'${classCard.id}',
                    schoolId:'${schoolId}',
                    startBootTimeOfDay:$('#timeo').val(),
                    endBootTimeOfDay:$('#timet').val(),
                    bootDayOfWeek:bootDayOfWeek.trim()

                }, function (retJson) {
                    if (retJson.code == 0) {
                        setTimeout(function () {
                            layer.msg('保存成功', {
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                window.parent.location.reload(true);
                            });
                        }, 300)
                    } else {
                       layer.msg(retJson.msg);
                    }
                });
                return true;
            } else {
                layer.msg('请修改错误内容！')
            }
        }
    </script>
</head>
<body>
<form action="${ctx}/classcard/save">

    <div class="container">
        <h3>班牌信息</h3>
        <div class="stuMsg">
            <ul class="left">
                <li>
                    <span>终端MAC地址:</span>
                    <input ${disabled} id="autoComplete" name="macId" value="${classCard.macId}"/><b>*</b>
                    <span class="completeTips completeTipsmac" style="display: none; width: 46%;margin-left: 142px;">请输入系统中存在的mac地址！</span>
                    <input type="hidden" name="classCardId" id="personId" value=""/>
                </li>

                <li>
                    <span>校区:</span>
                    <select  id="xq" name="xq">
                        <option value="0">请选择校区</option>
                        <c:forEach items="${schoolTypes}" var="xq">
                            <option value="${xq.id}"
                                    <c:if test="${classRoom.schoolType eq xq.id}">selected</c:if>> ${xq.name}</option>
                        </c:forEach>
                    </select><b>*</b>
                </li>

                <li>
                    <span>楼名称:</span>
                    <select  id="teachBuilding" name="teachBuilding">
                        <option value="0">请选择楼名称</option>
                    </select><b>*</b>
                </li>
                <li>
                    <span>楼层:</span>
                    <select  id="floor" name="floor">
                            <option value="0">请选择楼层</option>
                    </select><b>*</b>
                </li>
                <li>
                    <span>房间号:</span>
                    <select  id="roomNum" name="roomNum">
                            <option value="">请选择房间号</option>
                    </select>
                    <b>*</b>
                    <span class=" completeTipsroom" style="display: none; width: 40%;font-size: 12px;
                    color: red;margin-left: 158px;text-align: left;">该房间号已被占用!</span>
                </li>
            </ul>

            <ul class="right">
                <li>
                    <span>设备名称:</span>
                    <input type="text" name="equipmentName" value="${classCard.equipmentName}"/><b>*</b>
                </li>
                <li>
                    <span>班牌显示名称:</span>
                    <input  type="text" name="classroom" value="${classCard.classroom}"/><b>*</b>
                </li>
                <li>
                    <span>学段:</span>
                    <select  id="xd">
                            <option value="0">请选择学段</option>
                            <c:forEach items="${xds}" var="xd">
                                <option value="${xd.xdId}"
                                    <c:if test="${gradeClassExtention.xd eq xd.xdId}">selected</c:if>  > ${xd.xd}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <span>年级:</span>
                    <select  id="teach_grade">
                           <option value="0">请选择年级</option>
                    </select>
                </li>
                <li>
                    <span>班级:</span>
                    <select id="teach_class">
                            <option value="">请选择班级</option>
                    </select>

                    <span class=" completeTipsclass" style="display: none; width: 40%;font-size: 12px;
                        color: red;margin-left: 158px;text-align: left;">该班级已被占用！</span>
                </li>
            </ul>
        </div>

       <%-- <div class="time-conatiner">
            <span>开机星期设置：</span>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期一')}">checked</c:if>> 星期一</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期二')}">checked</c:if>> 星期二</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期三')}">checked</c:if>> 星期三</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期四')}">checked</c:if>> 星期四</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期五')}">checked</c:if>> 星期五</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期六')}">checked</c:if>> 星期六</i>
            <i><input type="checkbox"  <c:if test="${classCard.bootDayOfWeek.contains('星期日')}">checked</c:if>> 星期日</i>
        </div>
        <div class="time-conatiner day-time">
            <span>开机时间设置:</span>
            <input class="workinput wicon mr25" id="timeo" type="text" placeholder="请选择时间"  readonly value="${classCard.startBootTimeOfDay}" name="startBootTimeOfDay" onclick="testShow(this)">
            <span style="display: inline-block;width: 35px;height: 0;border-bottom: 1px solid #ddd;margin: 0 12px 4px 12px;"></span>
            <input class="workinput wicon mr25" id="timet" type="text" placeholder="请选择时间"  readonly value="${classCard.endBootTimeOfDay}"name="endBootTimeOfDay"onclick="testShow(this)">
        </div>--%>
    </div>
</form>
<script>
    //回显
    $(function () {
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


            //----编辑班牌时回显班级---------
            var class_params={};
            class_params.xdId='${gradeClassExtention.xd}';
            class_params.flag='xd'
            class_params.nj='';
            cascade_class(class_params,false);

            class_params.nj='${gradeClassExtention.nj}';
            class_params.flag='nj';
            cascade_class(class_params,false);
        }

        var data = ${classCardList};
        if(data==''){
            layer.msg('请先导入班牌mac地址')
        }
        $("#autoComplete").autocomplete(data, {
            minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
            max: 100,//下拉显示项目的个数
            autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
            mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
            matchContains: true,
            formatItem: function (row, i, max) {
                return row.macId;

            },
            formatMatch: function (row, i, max) {
                return row.macId;
            },
            formatResult: function (row) {
                return row.macId;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
            }
        });

        $("#autoComplete").bind("input", function () {
            if ($(this).val().trim() == ""&&$('#autoComplete').val().trim()!='') {
                $(".completeTipsmac").show();
            } else {
                $(".completeTipsmac").hide();
            }
        });
    });

    //位置级联参数
    var _param={};
    function determineLocationParam() {
        _param["xqId"]=$('#xq').val();
        _param["teachBuilding"]=$('#teachBuilding').val();
        _param["floor"]=$('#floor').val();
    }
    //位置级联方法
    function cascade_location(param,isclick) {
        $.ajax({
            url:"${ctx}/classcard/cascadeclassroom",
            type:"post",
            data:{
                'mydata':JSON.stringify(param)
            },
            success:function(data){
                if(data.code=1){
                    var flg =data.data.flag;
                    var _classRomms=data.data.classRomms;
                    if(flg == 'xq'){
                        for(var i=0; i<_classRomms.length;i++){
                            var classRoomTeachBuilding='${classRoom.teachBuilding}';
                            var tmp=_classRomms[i].teachBuilding;
                            var html="<option value='"+_classRomms[i].teachBuilding+"'"
                            if(isclick==false){
                                if(classRoomTeachBuilding==tmp){
                                    html+="selected";
                                }
                            }
                            html+=">"+_classRomms[i].teachBuilding +"</option>"
                            $('#teachBuilding').append(html);

                        }
                    }else if(flg=='teachBuilding'){
                        for(var i=0; i<_classRomms.length;i++){
                            var classRoomFloor='${classRoom.floor}';
                            var tmp=_classRomms[i].floor;
                            var html="<option value='"+_classRomms[i].floor+"'";
                            if(isclick==false) {
                                if (classRoomFloor == tmp) {
                                    html += "selected";
                                }
                            }
                            html+= ">"+_classRomms[i].floor +"</option>"
                            $('#floor').append(html);
                        }
                    }else if (flg=='floor'){
                        for(var i=0; i<_classRomms.length;i++){
                            var classRoomRoomNum='${classRoom.roomNum}';
                            var tmp=_classRomms[i].roomNum;
                            var html="<option value='"+_classRomms[i].id+"'";
                            if(isclick==false) {
                                if (classRoomRoomNum == tmp) {
                                    html += "selected";
                                }
                            }
                            html+=">"+_classRomms[i].roomNum +"</option>";
                            $('#roomNum').append(html);

                        }
                    }
                }
            },
            error: function (e) {
                layer.msg("暂无相关数据");
            }
        })
    }

    $("#xq").change(function () {
        $("#teachBuilding").empty();
        $("#floor").empty();
        $("#roomNum").empty();
        $("#teachBuilding").append("<option value=''>请选择楼名称</option>");
        $("#floor").append("<option value= ''>请选择楼层</option>");
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="xq";
        determineLocationParam();
        cascade_location(_param,true)
    });
    $("#teachBuilding").change(function () {
        $("#floor").empty();
        $("#roomNum").empty();
        $("#floor").append("<option value=''>请选择楼层</option>");
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="teachBuilding";
        // alert(_param.flag+'------');
        determineLocationParam();
        cascade_location(_param,true)
    });
    $("#floor").change(function () {
        $("#roomNum").empty();
        $("#roomNum").append("<option value=''>请选择房间号</option>");
        _param["flag"]="floor";
        // alert(_param.flag+'------');
        determineLocationParam();
        cascade_location(_param,true)
    });

    //班级级联参数
    var _param_class={};
    function determineClassParam() {
        _param_class["xdId"]=$('#xd').val();
        _param_class["nj"]=$('#teach_grade').val();
    }
    //班级级联方法
    function cascade_class(param,isclick) {
        //console.log(JSON.stringify(param));
        $.ajax({
            url:"${ctx}/classcard/cascadeclass",
            type:"post",
            data:{
                'mydata':JSON.stringify(param)
            },
            success:function(data){
                console.log(data);
                if(data.code=1){
                    var flg =data.data.flag;
                    var _gradeClasses=data.data.gradeClasses;
                    if(flg == 'xd'){
                        for(var i=0; i<_gradeClasses.length;i++){
                            var html="<option value='"+_gradeClasses[i].nj+"'";
                            if(isclick==false){
                                if(_gradeClasses[i].nj =='${gradeClassExtention.nj}'){
                                    html+="selected";
                                }
                            }
                            html+=">"+_gradeClasses[i].nj +"</option>";
                            $('#teach_grade').append(html);
                        }
                    }else if(flg=='nj'){
                        for(var i=0; i<_gradeClasses.length;i++){
                            var html ="<option value='"+_gradeClasses[i].id+"'";
                            var tmp='${gradeClassExtention.id}'
                            if(isclick==false){
                                if(_gradeClasses[i].id =='${gradeClassExtention.id}'){
                                    html+="selected";
                                }
                            }
                            html+=">"+_gradeClasses[i].name +"</option>";
                            $('#teach_class').append(html);
//                           "<option value='"+_gradeClasses[i].id+"'>"+_gradeClasses[i].name +"</option>"
                        }
                    }
                }
            },
            error: function (e) {
                layer.msg("暂无相关数据");
            }
        })
    }

    $("#xd").change(function () {
        $("#teach_grade").empty();
        $("#teach_class").empty();
        $("#teach_grade").append("<option value=''>请选择年级</option>");
        $("#teach_class").append("<option value=''>请选择班级</option>");
        _param_class["flag"]='xd';
        determineClassParam();
        cascade_class(_param_class,true);
    });
    $("#teach_grade").change(function () {
        $("#teach_class").empty();
        $("#teach_class").append("<option value=''>请选择班级</option>");
        _param_class["flag"]='nj';
        determineClassParam();
        cascade_class(_param_class,true);
    });

    //判断房间号是否被占用
    $('#roomNum').change(function () {
        $.ajax({
            url:'${ctx}/classcard/islocationtoken',
            type:'post',
            data:{
                roomId:this.value,
                classCardId:'${classCard.id}'
            },
            success:function (data) {
                if(data.code == -1){
                    $('.completeTipsroom').show();
                }else{
                    $('.completeTipsroom').hide();
                }
            },
            error:function () {
                layer.msg("暂无相关数据");
            }
        })


    })

    //判断班级是否被占用
    $('#teach_class').change(function () {
        $.ajax({
            url:'${ctx}/classcard/isclasstoken',
            type:'post',
            data:{
                classId:this.value,
                classCardId:'${classCard.id}'
            },
            success:function (data) {
                if(data.code == -1){
                    $('.completeTipsclass').show();
                }else{
                    $('.completeTipsclass').hide();
                }
            },
            error:function () {
                layer.msg("暂无相关数据");
            }
        })
    });

    //时间插件
   /* function testShow(elem) {
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
        $('.jedatehms li input').css('background', '#fff');
        $('.jedateblue .jedateproptext:eq(2)').hide();
        $('#jedatebox').css('top', '80px');
        $('.jedateblue .jedatebot .jedatehms').css({
            'border':'0',
            'background':'none'
        });
        $('.jedatehms input:eq(2)').hide();
    }*/
</script>
</body>
</html>