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
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="">
        var isSubmit = false;
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            window.parent.layer.msg('上传中', {icon: 16, shade: 0.5,time:1000000000});//当生成完成这个对话框才被关掉
            if (!isSubmit) {

                $.ajax({
                    url: '${url}',
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#inputForm')[0]),
                    processData: false,
                    contentType: false,

                }).done(function (res) {
                    window.parent.importCallBack(res);
                }).fail(function (res) {
//                    alert(res.msg);
                    layer.msg("上传失败！");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 500)
                });
            }else {
                window.parent.layer.closeAll();
                window.parent.layer.msg("上传异常,请稍后再试!");
            }
            isSubmit = true;
            return false;
        }
    </script>
</head>
<body>
<div class="container">

    <!--目标样式的文件选择框-->
    <form id="inputForm" method="post" enctype="multipart/form-data">
        <div class="file-box">
            <label>请选择导入文件：</label>
            <input type="file"   name="file"/>

        </div>
    </form>
</div>
</body>
</html>