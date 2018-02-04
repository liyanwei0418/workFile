<%@ include file="../../common/common.jsp" %>
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
            margin-top: 49px;
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
            width: 190px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
            border-radius: 3px;
            padding-left: 5px;
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

        #autoComplete {
            height: 28px;
        }

        input, select {
            outline: none;
        }
    </style>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script>

        $(function () {
            var data =${teacherList};
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


            $("#autoComplete").bind("input  ", function () {
                if ($(this).val().trim() == "") {
                    $(".completeTips").show();
                } else {
                    $(".completeTips").hide();
                }
            });

        });

        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if ($(".completeTips").css("display") == "none") {
                /*$("#inputForm").submit();
                 return true;*/
                $.post($('form').attr('action'), {
                    roleId: $("input[name='roleId']").val(),
                    teacherId: $("input[name='teacherId']").val(),
                    teacherName: $("input[name='teacherName']").val(),
                }, function (retJson) {
                    if(retJson.code==-1){
                        layer.msg(retJson.msg)
                    }else{
                        layer.msg(retJson.msg, {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            window.parent.location.reload(true);
                        });
                    }
                });
            } else {
                layer.msg("输入有误！");
                return false;
            }
        }
    </script>
</head>

<body>
<div class="container">

    <form action="${ctx}/classcard/roleuser/save" id="inputForm" method="post"
          onkeydown="if(event.keyCode==13)return false;">
        <input type="hidden" value="${roleId}" name="roleId">
        <div class="row">
            <label>人员姓名：</label>
            <input id="autoComplete" name="teacherName"/><span class="completeTips">请输入系统中存在的人员！</span>
            <input type="hidden" name="teacherId" id="personId"/>
        </div>
    </form>
</div>

</body>

</html>

