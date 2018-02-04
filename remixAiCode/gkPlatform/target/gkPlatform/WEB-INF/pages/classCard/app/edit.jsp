<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${ctxStaticNew}/js/qiniu/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${ctxStaticNew}/js/qiniu/styles/main.css">
    <link rel="stylesheet" href="${ctxStaticNew}/js/qiniu/styles/highlight.css">


    <script src="${ctxStatic}/js/jquery-1.7.2.js"type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/bower_components/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/bower_components/plupload/js/moxie.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/bower_components/plupload/js/plupload.dev.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/bower_components/plupload/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/scripts/ui.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/src/qiniu.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/scripts/highlight.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/qiniu/scripts/multiple.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>


    <script src="${ctxStaticNew}/js/qiniuUpload/qiniuUploadfile.js" type="text/javascript" charset="utf-8"></script>
    <style>

        .container {
            width: 480px;
            margin: 0 auto;
            /*padding-top: 30px;*/
            font: 13px '微软雅黑';
            overflow-x: hidden;
        }

        .container > h3 {
            font-size: 16px;
            font-weight: normal;
            color: #54AB37;
            margin: 20px 0;
            padding: 0px 0 0px 8px;
            border-left: 3px solid #54AB37;
        }

        .stuMsg {
            overflow: hidden;
        }

        ul {
            margin: 0;
            padding: 0;
            list-style: none;
        }





        .stuMsg span {
            display: inline-block;
            width: 120px;
            text-align: right;
            color: #666;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 28px;
            padding: 0;
            padding-left: 5px;
            border-radius: 2px;
            border: 1px solid #a9a9a9;
            outline: none;
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
            right: 36px;
        }

        .radio b {
            top: -3px;
        }

        .stuMsg select {
            font-size: 14px;
            color: #333;
            width: 197px;
            height: 28px;
            padding-left: 5px;
            border: 1px solid #a9a9a9;
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
            color: #666;
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
            border: 1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }

        .stuMsgg textarea {
            vertical-align: top;
            width: 590px;
            outline: none;
            resize: none;
            height: 400px;
            padding: 5px;
        }

        i {
            font-style: normal;
            display: inline-block;
            padding: 10px 6px;
            /*margin-right: 10px;*/
        }

        #checkedEquipment {
            width: auto;
            max-width: 525px;
            margin-right: 10px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }

        input, textarea {
            background: #fff !important;
        }

        #content {
            width: 600px;
            display: inline-block;
            vertical-align: top;
        }

        #edui1 {
            width: 600px !important;
        }

        #edui1_iframeholder {
            min-height: 450px !important;
        }
    </style>
</head>
<body>
<form action="${ctx}/classcard/save">
</form>

<div class="container">
    <%--<h3>通知信息</h3>--%>
    <div class="stuMsg">

        <ul>
            <div>
                <input type="hidden" name="app.logo" id="tosql3"/>
                <img alt="" id="wm13" width="100px" height="100px" src=""/>

                <i>上传apk</i>
                <div class="col-md-12">
                    <div id="container13" style="margin: 23px 0px 23px -15px;">
                        <a class="btn btn-default btn-lg " id="pickfiles13" href="#">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span id="pickfile">选择文件</span>
                        </a>
                    </div>
                </div>

            </div>
            <input type="hidden" id="domain" value="${domain}">
            <input type="hidden" id="uptoken_url" value="${ctx}/${uptoken_url}">
            <div style="display:none" id="success" class="col-md-12">
                <div class="alert-success">
                    队列全部文件处理完毕
                </div>
            </div>
        </ul>

        <ul class="left">
            <input type="hidden" name="ueditorPics" value="" id="ueditorPics">
            <li>
                <span>模块名称：</span>
                <input ${disabled} type="text" id="appName" placeholder="请输入名称"
                                   value="${classCardApp.appName}"/>
            </li>
            <li>
                <span>模块地址：</span>
                <input disabled type="text" id="appUrl" placeholder=""
                                   value="${classCardApp.appUrl}"/>
            </li>
            <li>
                <span>模块版本号：</span>
                <input ${disabled} type="number" id="versionCode" placeholder="请输入版本号"
                                   value="${classCardApp.versionCode}"/>
            </li>
            <li>
                <span>模块包名：</span>
                <input ${disabled} type="text" id="packageName" placeholder="请输入模块包名"
                                   value="${classCardApp.packageName}"/>
            </li>
            <li>
                <span>自启动：</span>
                <c:choose>
                    <c:when test="${classCardApp == null}">
                        <input type="radio" name="autoStart" checked value="1">是
                        <input type="radio" name="autoStart" value="0">否
                    </c:when>

                    <c:when test="${classCardApp != null}">
                        <c:if test="${classCardApp.autoStart==1}">
                            <input type="radio" name="autoStart" checked value="1">是
                            <input type="radio" name="autoStart" value="0">否
                        </c:if>
                        <c:if test="${classCardApp.autoStart==0}">
                            <input type="radio" name="autoStart" value="1">是
                            <input type="radio" name="autoStart" checked value="0">否
                        </c:if>
                    </c:when>
                </c:choose>
            </li>
            <input type="hidden" name="appId" id="appId" value="${appId}">
            <input type="hidden" name="schoolId" id="schoolId" value="${schoolId}">

        </ul>
    </div>
    <script>
        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var appName = $("#appName").val();
            var appUrl = $("#appUrl").val();
            var versionCode = $("#versionCode").val();
            var packageName = $("#packageName").val();
            var autoStart = $("input[name='autoStart']:checked").val();

            if (appName == '') {
                layer.msg("模块名称不能为空！");
                return false;
            }

            if (appUrl == '') {
                layer.msg("下载地址不能为空！");
                return false;
            }
            if (versionCode == '') {
                layer.msg("版本号不能为空");
                return false;
            }
                var url = "${ctx}/model/modify"
            $.post(url, {
                appName: appName,
                appUrl: appUrl,
                versionCode: versionCode,
                packageName: packageName,
                autoStart: autoStart,
                appId: $("#appId").val()
            }, function (retJson) {
                if (retJson.code == '0') {
                    setTimeout(function () {
                        window.parent.location.reload();
                    }, 3000)

                } else {
                    layer.msg(retJson.msg);
                }
            });
            return true;
        }
    </script>
</body>
</html>