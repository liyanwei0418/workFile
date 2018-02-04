<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>

    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/diyUpload.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/webuploader.css">
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/diyUploadmore.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/webuploader.html5only.min.js"></script>

    <style>
        .over {
            overflow: hidden;
        }

        .left {
            float: left;
        }

        i {
            font-style: normal;
        }

        * {
            margin: 0;
            padding: 0;
        }

        .content-warp {
            margin-top: 30px;
        }

        span {
            display: inline-block;
        }

        p span:first-child {
            width: 70px;
            text-align: right;
            margin-right: 10px;
        }

        p textarea {
            width: 380px;
            min-height: 80px;
            padding-left: 5px;
            border: 1px solid #ddd;
            outline: none;
            resize: none;
            vertical-align: top;
            border-radius: 3px;
        }

        p textarea[name='classIntroduction'] {
            height: 120px;
        }

        p {
            margin-top: 20px;
        }
    </style>
    <style>
        .parentFileBox {
            width: auto !important;
            display: block !important;
            margin-left: 76px !important;
        }

        .fileBoxUl {
            width: 320px !important;
            /*max-height: 400px !important;*/
            overflow: auto !important;
            mrgin-bottom: 20px;
        }

        .fileBoxUl li {
            margin-left: 0 !important;
        }

        .parentFileBox > .diyButton {
            width: 100%;
            margin-top: 5px;
            margin-bottom: 5px;
            height: 20px;
            line-height: 20px;
            text-align: left;
        }

        .container {
            padding-left: 50px;
        }

        p input {
            width: 190px;
            height: 28px;
            border-radius: 2px;
            outline: none;
            border: 1px solid #ddd;
            padding-left: 5px;
        }

        span {
            font-size: 13px;
            display: inline-block;
            width: 70px;
            text-align: right;
            margin-right: 10px;
        }

        i {
            display: inline-block;
            text-align: center !important;
            font-size: 12px;
            font-style: normal;
            color: #999;
            padding: 5px 0 0 3px;
            position: absolute;
            top: 50px;
            left: 225px;
        }

        li {
            list-style: none;
        }
    </style>
</head>
<body>
<form action="${ctx}/classcard/growth/collection/save">
    <div class="container" style="position: relative;">
        <div class="time" style="position: absolute; margin-left: -25px;top:200px;left: 50%; "></div>
        <p><span>合集名称:</span><input type="text" placeholder="15个字以内" maxlength="15" name="picTitle" id="picTitle"
                                    value=""></p>
        <ul style="margin-top: 20px;">
            <li>
                <span style="vertical-align: top;margin-top: 5px">上传图片:</span>
                <div style="display: inline-block;vertical-align: top" id="as" class="webuploader-container">
                    <div class="webuploader-pick">选择文件</div>
                    <div id="rt_rt_1bl0b87jqh558hn1p7uvk91iu54"
                         style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                        <input type="file" name="file" class="webuploader-element-invisible">
                        <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                    </div>
                </div>
                <i>每张大小3M以内</i>
            </li>
        </ul>
    </div>
    <input type="hidden" name="pic_urls" id="pic_urls" value="">
    <input type="hidden" name="thumbnail_flag" id="thumbnail_flag" value="">
    <input type="hidden" name="fileName" id="fileName" value="">
    <input type="hidden" name="failList" value="">

    <input type="hidden" id="classCardId" name="classCardId" value="${classCardId}">
    <input type="hidden" name="ctx" value='${ctx}'>
</form>
<script>
    $('#as').diyUpload({
        url: '${ctx}/file/upload',
        success: function (data) {
            console.info(data);
            var tmpurl = $('#pic_urls').val();
            if (tmpurl != '') {
                $('#pic_urls').val(tmpurl + "," + data.data.fileName + "@#@" + data.data.imgRequestUrl);
            } else {
                $('#pic_urls').val(data.data.fileName + "@#@" + data.data.imgRequestUrl);
            }

            var fileName = $('#fileName').val();
            if (fileName != '') {
                $('#fileName').val(fileName + "," + data.data.fileName);
            } else {
                $('#fileName').val(data.data.fileName);
            }

            var thumbnailurl = $('#thumbnail_flag').val();
            if (thumbnailurl != '') {
                if (data.data.thumbnailUrl != '') {
                    $('#thumbnail_flag').val(thumbnailurl + "," + true);
                } else {
                    $('#thumbnail_flag').val(thumbnailurl + "," + false);
                }
            } else {
                if (data.data.thumbnailUrl != '') {
                    $('#thumbnail_flag').val(true);
                } else {
                    $('#thumbnail_flag').val(false);
                }
            }
            //将url赋值给父页面，为删除未保存的图片
            window.parent.setDelUrls($('#pic_urls').val(), 'growth');
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '选择文件',
        chunked: false,
        // 分片大小
        chunkSize: 10 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 500,
        fileSizeLimit: 1500 * 1024 * 1024,
        fileSingleSizeLimit: 3 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "gif,jpg,jpeg,bmp,png",
        },
    });

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if ($(".time").length == 0 || $(".time").is(":hidden")) {
            var pictitle = $('#picTitle').val();
            if (pictitle != '') {
                /*var tmpnames = '';
                 $('.fileBoxUl li ').each(function () {
                 if (tmpnames != '') {
                 tmpnames += ',' + $(this).text();
                 } else {
                 tmpnames += $(this).text();
                 }
                 })*/
                var $urls = $('#pic_urls').val().split(',');
                var thumbnailurls = $('#thumbnail_flag').val().split(',');
                var filename = $('#fileName').val().split(',');
                var urlNameList = [];

                for (var i = 0; i < $urls.length; i++) {
                    var url_name = {};
                    url_name.url = $urls[i].split("@#@")[1];
                    url_name.name = filename[i];
                    url_name.thumbnail = thumbnailurls[i];
                    urlNameList.push(url_name);
                }

                $.post($("form").attr('action'), {
                    picTitle: pictitle,
                    classCardId: '${classCardId}',
                    url_name: JSON.stringify(urlNameList),
                    pid: "-1"
                }, function (retJson) {
                    if (retJson.code == 0) {
                        layer.msg('上传成功');
                        //index为导航栏的序号
                        window.parent.chooseResult('${_index}');//调用父级frame的js方法将选择的值传递给父级页面
                    } else {
                        layer.msg(retJson.msg);
                    }
                });
                return true;
            } else {
                layer.msg("请输入合集名称！");
                return false;
            }
        } else {
            layer.msg('图片上传中,请等待');
        }
    }
</script>
</body>
</html>