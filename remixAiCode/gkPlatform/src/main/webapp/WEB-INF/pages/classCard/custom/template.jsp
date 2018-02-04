<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>自定义界面模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/addTemplate.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
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
            width: 36%;
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
            font-style: normal;
            margin-right: 22px;
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

        .jedatebox {
            width: 185px !important;
        }

        .jedateblue .jedatehmscon:last-child, .jedateblue .jedateproptext:last-child {
            display: none !important;
        }

        li>span{
            text-align: center !important;
            line-height: 20px;
        }
        li>span>input{
            margin: 0;
            width: 15px;
            height: 15px;
        }
        .module-lr>div{
            text-align: center;
            line-height: 140px;
        }
        .module-tb>div{
            text-align: center;
            line-height: 70px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>选择模板</h3>
    <div class="">
        <div class="content temp-container">
            <ul>
                <c:forEach items="${templateList}" var="templateView">
                    <li>
                        <span class="active"></span>
                        <div class="module-four">
                            <%--默认选中循环中的第一个--%>
                            <p><input checked type="radio" name="templateId" value="${templateView.id}">${templateView.name}</p>
                            <img src="${ctxStaticNew}/images/${templateView.preview}" alt="${templateView.detailed}">
                        </div>
                    </li>
                </c:forEach>
                <li>
                    <%--<span class="active"></span>--%>
                    <div class="module-five">
                        <%--<p>示例文字</p>--%>
                        <%--<div>示例视频</div>--%>
                    </div>
                    <p style="text-align: center"><input type="radio" name="templateId" value="${templateView.id}"><span>全屏幕</span></p>
                </li>
                <li style="text-align: left !important;">
                    <%--<span class="active"></span>--%>
                    <div class="module-tb">
                        <div style="width: 100%;height: 50%;border-bottom: 1px solid #ddd">上</div>
                        <div style="width: 100%;height: 50%;">下</div>
                    </div>
                    <p style="text-align: center;"><input type="radio" name="templateId" value="${templateView.id}"><span>上下结构</span></p>
                </li>
                <li style="text-align: left !important;">
                    <%--<span class="active"></span>--%>
                    <div class="module-lr">
                        <div style="float:left;width: 50%;height: 100%;border-right: 1px solid #ddd">左</div>
                        <div style="float:left;width: 50%;height: 100%;">右</div>
                    </div>
                    <p style="text-align: center;"><input type="radio" name="templateId" value="${templateView.id}"><span>左右结构</span></p>
                </li>
            </ul>
        </div>
    </div>
</div>

<script>

    function chooseTemplate() {
        var templateId = "";
        $('input[name="templateId"]:checked').each(function () {
            templateId = $(this).val();
        });
        if(templateId == "") {
            //防止空指针异常，给定缺省值
            templateId = "1c0204e3e6519185f048bae0dd55a266";
        }
        return templateId;
    }

    function doSubmit() {
        //获取选中模板的信息
        //拼接好参数，可以使用隐藏字段保存
        var url = "${ctx}/classcard/custom/createPageByTemplate";
        $.post(url,{
            templateId:chooseTemplate()
        },function (retJson){
            console.log(retJson);
            if(retJson.code == '0') {
                window.parent.location.href = '${ctx}'+ retJson.data;
            } else {
                layer.msg(retJson.msg);
            }
        });
       }
</script>
</body>
</html>

