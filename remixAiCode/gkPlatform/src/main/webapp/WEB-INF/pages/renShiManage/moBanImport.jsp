<%@ include file="../common/common.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title></title>
    <style>
        body {
            font-size: 0.8em;
            font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            margin-top: 30px;
        }

        input {
            margin: 0 10px 0 20px
        }

        span {
            margin-right: 10px;
        }

        a {
            color: #B7B8B8
        }

        .file-box {
            position: absolute;
        }

        .txt {
            width: 200px;
            height: 35px;
        }

        .file {
            filter: alpha(opacity:0);
            opacity: 0;
            border: 1px red solid;
            z-index: 8888;
            position: absolute;
            right: 0px;
            height: 40px;
            width: 45px;
            margin: -40px -12px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/openDialog.js"></script>
    <script type="">
        var isSubmit = false;
        function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            //closeAlertDiv();
            window.parent.layer.msg('导入中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
            if (!isSubmit) {
                $.ajax({
                    url: '${ctx}/renshi/moban/import/save',
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#inputForm')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    window.parent.importCallBack(res);
                }).fail(function (res) {
                    window.parent.layer.closeAll();
                    window.parent.layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 500)//alert(res);
                });
            } else {
                window.parent.layer.closeAll();
                window.parent.layer.msg("表单提交异常,请稍后再试!");
            }
            isSubmit = true;

            return false;
        }
    </script>
</head>
<body>
<div class="container">

    <!--目标样式的文件选择框-->
    <form action="${ctx}/renshi/moban/import/save" id="inputForm" method="post" enctype="multipart/form-data">
        <div class="file-box">
            <label>请选择导入文件：</label>


            <%--<input type='text' name='textfield' id='textfield' class='txt' />&nbsp;&nbsp;
            <a style="color: #1AB394;">浏览</a>onchange="document.getElementById('textfield').value=this.value" --%>
            <input type="file" name="file"/>

        </div>
        <div style="position: absolute;margin-top: 45px;margin-left: 125px;">
            <a class="a">注意事项：</a>
            <br><br><a class="a">1：上传文件格式只限于excel格式</a>
            <br><br><a class="a">2：上传的表格只读取第一个sheet</a>
            <br><br><a class="a">3：上传的表格不可以有合并的单元格</a>
        </div>

    </form>
</div>
</body>
</html>