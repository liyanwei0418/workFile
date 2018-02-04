<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班牌管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/classspace.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/oldCss.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/diyUpload.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/webuploader.css">
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/diyUploadmore.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/webuploader.html5only.min.js"></script>

    <style type="text/css">
        .banjijieshao {
            margin-top: 15px;
        }

        .none {
            visibility: hidden;
        }

        .content {
            width: 1200px;
            margin: 0 auto;
        }

        .file_upload {
            width: 48%;
            min-height: 400px;
            margin: 10px;
            position: relative;
            display: inline-block;
            vertical-align: top;
        }

        .file_upload > div {
            width: 100%;
            height: 100%;
        }

        .file_con .hidee {
            width: 120px;
            height: 30px;
            opacity: 0;
            filter: alpha(opacity=0);
            position: absolute;
            left: 0;
            z-index: 22;
        }

        .file_con .file_uploader, .upload_bt {
            position: absolute;
            left: 0;
            top: 0;
            display: inline-block;
            padding: 6px 14px;
            color: #fff;
            background: #2ECC71;
            text-align: center;
            z-index: 11;
            border-radius: 15px;
            cursor: pointer;
        }

        .upload_bt {
            left: 130px;
        }

        .file_con .hide:hover {
            box-shadow: 1px 2px #44795b;
        }

        .parentFileBox > .fileBoxUl > li > .diyFileName {
            width: 115px !important;
        }

        .img_holder, .m_img_holder {
            padding-top: 40px;
        }

        .img_holder img, .m_img_holder img {
            max-width: 200px;
        }

        .img_box {
            position: relative;
            display: inline-block;
            vertical-align: top;
            border: 1px transparent dashed;
            padding: 12px;
            box-shadow: 2px 2px 10px #ccc;
        }

        .img_box:hover {
            /*border: 1px #ccc dashed;*/
        }

        .img_box:hover .delete {
            display: block;
        }

        .img_box .delete {
            position: absolute;
            right: 1px;
            top: 0;
            display: none;
            font-family: Arial;
            font-size: 12px;
            cursor: pointer;
        }

        .progress {
            display: inline-block;
            margin-top: 10px;
        }

        .flie-name {
            /*display: block;*/
            margin: 5px 0 0 30px;
            vertical-align: top;
        }

        .parentFileBox {
            display: inline-block;
            margin-left: 30px;
        }

        .fenY .go, .fenY1 .go, .fenY2 .go, .gotoPage {
            bottom: 0 !important;
        }

        #fenYgo {
            bottom: -1px !important;
        }

        .nav-menu li a:hover {
            color: #fff !important;
        }

        .layer-photos {
            cursor: pointer;
        }

        .list-ul{
            min-height: 450px;
        }
        .list-ul li{
            margin-top: 20px;
        }
        .list-ul li span:first-child{
            display: inline-block;
            line-height: 28px;
        }
        .parentFileBox{
            width: 580px !important;
        }

        textarea{
            width: 650px !important;
            height: 200px !important;
        }
    </style>
</head>
<body>
<%@ include file="../../common/sonHead/classCardHead.jsp" %>
<main class="container">
    <!--班牌管理-->
    <div class="" id="stu-manage">

        <main class="">

            <form id="editForm"  method="post" enctype="multipart/form-data">
                <div class="content-warp banjijieshao">

                    <div class="time" style="position: absolute; margin-left: -25px;top:200px;left: 50%; "></div>
                    <p><span>校园简介：</span><textarea maxlength="150" id="introduction" value=""
                                                   placeholder="150字以内">${schoolCulture.introduction}</textarea>
                    </p>
                    <ul class="list-ul" style="margin-top: 20px;">
                        <li>
                            <span style="vertical-align: top;">学校校徽：</span>

                            <div style="display: inline-block;vertical-align: top" id="schoolBadgePicUpload"
                                 data-flag='schoolCulture' data-flagtype='schoolculturebadge'
                                 class="webuploader-container ">
                                    <div class="webuploader-pick">上传校徽</div>
                                <div
                                        style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                                    <input type="file" name="file" class="webuploader-element-invisible">
                                    <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                                </div>
                            </div>
                            <span id='schoolBadgePic'
                                  class="flie-name introductionFileName"> ${schoolBadgePicName}</span>
                        </li>
                        <li>
                            <span style="vertical-align: top;">宣传视频：</span>

                            <div style="display: inline-block;vertical-align: top" id="schoolVideoUpload"
                                 data-flag='schoolCulture' data-flagtype='schoolculturevideo'
                                 class="webuploader-container ">
                                <div class="webuploader-pick">上传视频</div>
                                <div
                                        style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                                    <input type="file" name="file" class="webuploader-element-invisible">
                                    <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                                </div>
                            </div>
                            <span id='schoolVideo'
                                  class="flie-name introductionFileName"> ${videoName}</span>
                        </li>
                        <li>
                            <span style="vertical-align: top;">学校图片：</span>

                            <div style="display: inline-block;vertical-align: top" id="schoolPicUpload"
                                 data-flag='schoolCulture'
                                 class="webuploader-container ">
                                <div class="webuploader-pick">上传图片</div>
                                <div
                                        style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                                    <input type="file" name="file" class="webuploader-element-invisible">
                                    <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                                </div>
                            </div>
                            <%--<span id='schoolPic'
                                  class="flie-name introductionFileName"> ${picNames}</span>--%>
                            <%--<button class="roll-add"
                                    &lt;%&ndash;onclick="openDialogWithoutReload('查看图片','${ctx}/classcard/schoolculture/pics','900px','55%',false);"&ndash;%&gt;>查看图片
                            </button>--%>

                            <a target="__black" href="${ctx}/classcard/schoolculture/pics" style="vertical-align: bottom;">查看图片</a>
                        </li>
                    </ul>

                    <div class="btn-containt">
                        <span id="savBtn" class="saveBtn save" onclick="fun_editForm()">保存</span>
                    </div>

                </div>
                <input type="hidden" id="schoolBadgePicUrl" value="">
                <input type="hidden" id="schoolBadgePicName" value="">
                <input type="hidden" id="schoolBadgePicThumbnailUrl" value="">

                <input type="hidden" id="schoolVideoUrl" value="">
                <input type="hidden" id="schoolVideoName" value="">


                <input type="hidden" id="schoolPicUrl" value="">
                <input type="hidden" id="schoolPicThumbnailUrl" value="">

                <input type="hidden" name="pic_urls" , id="pic_urls">
                <input type="hidden" name="fileName" id="fileName" value="">
                <input type="hidden" name="thumbnail_flag" id="thumbnail_flag" value="">
                <input type="hidden" name="failList" value="">
                <input type="hidden" name="ctx" value='${ctx}'>

            </form>
        </main>
    </div>
</main>

<script>
    activeMenu("all", 3);

    $('#schoolBadgePicUpload').diyUpload({
        url: '${ctx}/file/upload',
        success: function (data) {
            $('#schoolBadgePicUrl').val(data.data.imgRequestUrl);
            $('#schoolBadgePicName').val(data.data.fileName);

            if (data.data.thumbnailUrl != '') {
                $('#schoolBadgePicThumbnailUrl').val(true)
            } else {
                $('#schoolBadgePicThumbnailUrl').val(false);
            }
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '上传校徽',
        chunked: false,
        // 分片大小
        chunkSize: 50 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 1,
        fileSizeLimit: 3 * 1024 * 1024,
        fileSingleSizeLimit: 3 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "gif,jpg,jpeg,ico,bmp,png",
        },
    });

    $('#schoolVideoUpload').diyUpload({
        url: '${ctx}/file/upload',
        success: function (data) {
            $('#schoolVideoUrl').val(data.data.imgRequestUrl);
            $('#schoolVideoName').val(data.data.fileName);
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '上传视频',
        chunked: false,
        // 分片大小
        chunkSize: 50 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 1,
        fileSizeLimit: 50 * 1024 * 1024,
        fileSingleSizeLimit: 50 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "rmvb,rm,flv,mp4,avi"
        },
    });


    $('#schoolPicUpload').diyUpload({
        url: '${ctx}/file/upload',
        success: function (data) {

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
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '上传图片',
        chunked: false,
        // 分片大小
        chunkSize: 50 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 50,
        fileSizeLimit: 150 * 1024 * 1024,
        fileSingleSizeLimit: 3 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "gif,jpg,jpeg,ico,bmp,png",
        },
    });


    function fun_editForm() {
        var $urls = $('#pic_urls').val().split(',');
        var thumbnailurls = $('#thumbnail_flag').val().split(',');
        var filename = $('#fileName').val().split(',');
        var urlNameList = [];

        if ($('#pic_urls').val() != '') {
            for (var i = 0; i < $urls.length; i++) {
                var url_name = {};
                url_name.url = $urls[i].split("@#@")[1];
                url_name.name = filename[i];
                url_name.thumbnail = thumbnailurls[i];
                urlNameList.push(url_name);
            }
        }

        $.post($("form").attr('action'), {
            url_name: JSON.stringify(urlNameList),
            introduction: $("#introduction").val(),
            schoolBadgePicUrl: $("#schoolBadgePicUrl").val(),
            schoolBadgePicName: $("#schoolBadgePicName").val(),
            schoolBadgePicThumbnailUrl: $("#schoolBadgePicThumbnailUrl").val(),
            schoolVideoUrl: $("#schoolVideoUrl").val(),
            schoolVideoName: $("#schoolVideoName").val()
        }, function (retJson) {
            if (retJson.code == 0) {
                layer.msg('保存成功');
                setTimeout(function () {
                    window.location.reload();
                },1000)
            } else {
                layer.msg(retJson.msg);
            }
        });
        return true;
    }
</script>
</body>
</html>

