<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html language="en">
<head>
    <meta charset="utf-8">
    <title></title>

    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css"/>
    <style>
        body {
            font-family: "Microsoft YaHei";
            font-size: 13px;
            color: #101010;
        }

        .container {
            width: 450px;
            margin: 0 auto;
            margin-top: 30px;
        }

        .row {
            margin-bottom: 20px;:
        }

        input {
            height: 23px;
            width: 190px;
            border: 1px solid #a9a9a9;
            border-radius: 3px;
            padding-left: 5px;
        }

        .row p, .row label {
            width: 30%;
            display: inline-block;
            text-align: right;
        }

        .row label {
            width: 21%;
        }

        span, select {
            margin-left: 15px;
        }

        select {
            padding: 0 6px;
            width: 60%;
            height: 28px;
            border-radius: 5px;
            font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
        }
    </style>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script>

        $(function () {
            var data = ${teacherList};
            $("#autoComplete").autocomplete(data, {
                minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
                max: 100,//下拉显示项目的个数
                autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
                mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
                matchContains: true,
                formatItem: function (row, i, max) {
                    return row.name;

                },
                formatMatch: function (row, i, max) {
                    return row.name;
                },
                formatResult: function (row) {
                    return row.name;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
                }
            });


            $("#autoComplete").bind("input propertychange", function () {
                if ($(this).val().trim() == "") {
                    $(".completeTips").show();
                } else {
                    $(".completeTips").hide();
                }
            });

        });

        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if ($(".completeTips").css("display") == "none") {
                $("#inputForm").submit();
                return true;
            } else {
                layer.msg("输入有误！");
                return false;
            }
        }
    </script>
</head>

<body>
<div class="container">

    <form action="${ctx}/renshi/title/teacher/save" id="inputForm" method="post">
        <input type="hidden" value="${titleId}" name="titleId">
        <div class="row">
            <label>人员姓名：</label>
            <input id="autoComplete" name="teacherName"/><span class="completeTips">请输入系统中存在的人员！</span>
            <input type="hidden" name="teacherId" id="personId"/>
        </div>
    </form>
</div>

</body>

</html>

