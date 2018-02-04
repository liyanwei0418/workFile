<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html language="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        li > label {
            font-size: 13px;
            color: #666;
            width: 80px;
            display: inline-block;
            width: 92px;
            font-family: "Microsoft YaHei";
        }

        .dialogForm {
            width: 100%;
            margin-top: 20px;
            height: 80%;
        }

        .dialogForm > div {
            width: 100%;
            height: auto;
        }

        .red {
            color: red;
            font-size: 15px;
            margin-right: 7px;
        }

        .dialogLeft {
            width: 50%;
            height: 100%;
            float: left;
        }

        .dialogRight {
            background: aqua;
            width: 50%;
            height: 100%;
            float: right;
        }

        .dialogForm ul {
            text-align: center;
            margin: 0;
            padding: 0;
            list-style: none;
            height: 100%;
            width: 100%;
        }

        .dialogForm li {
            padding-top: 20px;

        }

        .dialogForm > div > ul > li > label {
            text-align: right;
        }

        .dialogLeft li {
            text-align: right;
            padding-right: 5%;
        }

        .dialogForm input, select {
            height: 23px;
            width: 197px;
            border: 1px solid #a9a9a9;
            border-radius: 3px;
            padding-left: 5px;
        }

        .dialogForm input[type="radio"] {
            height: 17px;
            width: 20px;
        }

        .hasRadioButton {
            padding-right: 189px;
        }

        .hasRadioButton span {
            padding-right: 15px;
        }
        textarea{
            vertical-align: top;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.autocomplete.js"></script>
    <script>

        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $.post("${ctx}/renshi/title/save",{
                titleName:$("input[name='titleName']").val(),
                titleId:$("input[name='titleId']").val(),
                titlePx:$("input[name='titlePx']").val(),
                titleRemark:$("textarea[name='titleRemark']").val()
            }, function (data) {
                parent.location.reload(true);
                parent.showWhichTap($("#about_person"));
            })
        }
    </script>
</head>

<body>
<form action="${ctx}/renshi/title/save" method="post" class="dialogForm" id="inputForm">
    <div>
        <ul>
            <li>
                <label><a class="red">*</a>职务名称：</label>
                <input type="" name="titleName" value="${title.mc}"/>
                <input name="titleId" type="hidden" value="${title.id}"/>
            </li>
            <li>
                <label>职务排序：</label>
                <input type="" name="titlePx" value="${title.px}"/>
            </li>
            <li>
                <label>职务描述：</label>
                <textarea name="titleRemark" rows="3"
                          style="margin: 0px; width: 201px; height: 57px;">${title.remarks}</textarea>
            </li>

        </ul>

    </div>

</form>

</body>

</html>

