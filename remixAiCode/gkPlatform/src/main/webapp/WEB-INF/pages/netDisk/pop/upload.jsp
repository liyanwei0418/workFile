<%@ include file="../../common/common.jsp" %>
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

        .demo{min-width:360px;margin:30px auto;padding:10px 20px}
        .demo h3{line-height:40px; font-weight: bold;}
        .file-item{float: left; position: relative; width: 110px;height: 110px; margin: 0 20px 20px 0; padding: 4px;}
        .file-item .info{overflow: hidden;}
        .uploader-list{width: 100%; overflow: hidden;}
    </style>

    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script>
        function directoryPathF(divas) {
            var aLength = 0;
            if (divas != undefined && divas != "") {
                aLength = divas.length
            }

            var directoryPath = "";
            if (aLength > 0) {
                directoryPath = parent.$(".directoryPathDiv a").last().attr("hre");
            }
            return directoryPath;
        }

        var isSubmit = false;
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var divas = parent.$("#directoryPathDiv").children();
            var directotypath = directoryPathF(divas);


            window.parent.layer.msg('导入中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
            if (!isSubmit) {
                $.ajax({
                    url: '${url}/?token=${token}&repositoryId=${repositoryId}&directotypath=' + encodeURI(directotypath),
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#inputForm')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
//                    window.parent.importCallBack(res);
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.layer.closeAll();
                    var currentFolederName = parent.$("#hiddenCurrentfolederName").val();
                    var totalUrl = parent.$(".totalFileUrl").val();
                    parent.partflush(totalUrl, currentFolederName);
                }).fail(function (res) {
//                    alert(res.msg);
                    layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        parent.location.reload();
                    }, 500)
                });
            } else {
                window.parent.layer.closeAll();
                window.parent.layer.msg("表单提交异常,请稍后再试!");
            }
            isSubmit = true;
            return false;
        }
        /////////////////////////////////////引入webupoloader插件测试
    </script>
</head>
<body>
<div class="container">
    <!--目标样式的文件选择框-->
    <form id="inputForm" method="post" enctype="multipart/form-data">
        <div class="file-box">
            <label>请选择文件：</label>
            <input type="file" name="file" multiple="multiple"/>
        </div>
    </form>
</div>
<div class="container">
    <div class="row main">
        <div class="demo">
            <h3>1、文件上传</h3>
            <div id="uploadfile">
                <!--用来存放文件信息-->
                <div id="thelist" class="uploader-list"></div>
                <div class="form-group form-inline">
                    <div id="picker" style="float:left">选择文件</div>
                    &nbsp;
                    <button id="ctlBtn" class="btn btn-default" style="padding:8px 15px;">开始上传</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>