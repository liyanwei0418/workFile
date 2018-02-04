<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
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
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/diyUpload.js"></script>
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

    </style>
</head>
<body>
<%@ include file="../../common/sonHead/classCardHead.jsp" %>
<main class="container">
    <!--班牌管理-->
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>
        <main class="col-xs-9">
            <div class="roll-operation">
                <span data-pid="" class="active">班级简介</span>
                <span data-pid="-1">成长足迹</span>
                <span data-pid="bgzl">百舸争流</span>
                <span data-pid="hdly">活动掠影</span>
            </div>
            <form id="editForm" action="" method="get" enctype="multipart/form-data">
                <div class="content-warp banjijieshao">
                    <c:if test="${flag eq 'edit'}">
                        <p style="display: inline-block;margin-right: 30px;"><span>班主任：</span><span
                                style="width: 100px;">${classIntroductionView.headTeacher}</span></p>
                        <p style="display: inline-block;"><span>副班主任：</span><span
                                style="width: 100px;">${classIntroductionView.viceTeacher}</span></p>
                        <p><span>教师寄语：</span><textarea maxlength="50" name="sendWord" value=""
                                                       placeholder="50字以内">${classIntroductionView.sendWord}</textarea>
                        </p>
                        <p><span>班级骨干：</span><textarea class="classperson" name="classBackbone" value=""
                                                       placeholder="仅限人名，最多十人，需要用中文分号隔开">${classIntroductionView.classBackbone}</textarea>
                        </p>
                        <p><span>班级简介：</span><textarea maxlength="200" name="classIntroduction" value=""
                                                       placeholder="200字以内">${classIntroductionView.classIntroduction}</textarea>
                        </p>
                        <ul style="margin-top: 20px;">
                            <li>
                                <span style="vertical-align: top;">展示信息：<i>限一张图片或一个视频(50M以内)</i></span>
                                    <%--<img src="${ctx}/file/pic/show?picPath=${classIntroductionView.displayInformationUrl}" data-url="${student.xszp}"width="25%" height="25%" id="head_url">
                                    <video id="video1" width="420" style="margin-top:15px;">
                                        <source src="${ctx}/file/pic/show?picPath=${classIntroductionView.displayInformationUrl}" type="video/mp4" />
                                        <source src="/ogg格式视频地址" type="video/ogg" />
                                        Your browser does not support HTML5 video.//如果浏览器不支持html5，则显示这一句话
                                    </video>--%>

                                <div style="display: inline-block;vertical-align: top" id="as"
                                     class="webuploader-container">
                                    <div class="webuploader-pick">选择文件</div>
                                    <div id="rt_rt_1bl0b87jqh558hn1p7uvk91iu54"
                                         style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                                        <input type="file" name="file" class="webuploader-element-invisible">
                                        <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                                    </div>
                                </div>
                                <span id='filename' name='introductionFileName'
                                      class="flie-name introductionFileName"> ${classIntroductionView.picName==null?classIntroductionView.videoName:classIntroductionView.picName}</span>
                            </li>
                            <li>
                                <div>
                                       <%--http://v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4--%>
                                           <%--${ctx}/file/play?${classIntroductionView.videoUrl}--%>
                                    <%--<video  src="${ctx}/file/play?${classIntroductionView.videoUrl}" width="320" height="240" controls="controls" autoplay="" type="video/avi">
                                        Your browser does not support the audio tag.
                                    </video>--%>
                                </div>
                            </li>
                        </ul>
                        <input type="hidden" id="displayInformationUrl" name="url"
                               value="${classIntroductionView.picUrl==null?classIntroductionView.videoUrl:classIntroductionView.picUrl}">
                        <input type="hidden" id="displayInformationName" name="displayInformationName"
                               value="${classIntroductionView.picName==null?classIntroductionView.videoName:classIntroductionView.picName}">

                        <c:if test='${classIntroductionView.picUrl!=null}'>
                            <input type="hidden" id="fileCategory" name="fileCategory" value="pic">
                        </c:if>
                        <c:if test='${classIntroductionView.videoUrl!=null}'>
                            <input type="hidden" id="fileCategory" name="fileCategory" value="video">
                        </c:if>
                        <c:if test='${classIntroductionView.videoUrl==null &&classIntroductionView.picUrl==null}'>
                            <input type="hidden" id="fileCategory" name="fileCategory" value="">
                        </c:if>

                        <input type="hidden" id="oldThum" name="oldThum" value="${classIntroductionView.thumbnailUrl}">
                        <input type="hidden" id="thumbnail" name="thumbnail"
                               value="<c:if test="${classIntroductionView.thumbnailUrl!=''}">true</c:if>">

                        <input type="hidden" id="isupload" value="">
                        <input type="hidden" id="classCardId" name="classCardId" value="${classCardId}">
                        <input type="hidden" id="classId" name="classId" value="${classId}">
                        <input type="hidden" id="introductionId" name="id" value="${classIntroductionView.id}">
                        <input type="hidden" name="focusNode" value="${focusNode}">
                        <input type="hidden" name="nodeList" value='${nodeList}'>
                        <input type="hidden" name="ctx" value='${ctx}'>
                        <input type="hidden" name="failList" value="">

                        <div class="btn-containt">
                            <span id="savBtn" class="saveBtn save" onclick="fun_editForm()">保存</span>
                        </div>
                    </c:if>
                </div>
                <div class="content-warp chengzhangzuji" style="display: none">
                    <p class="btn-containt over">
                        <c:if test="${classCardId!=null&&classCardId!=''}">
                            <span class="add"
                                  onclick="openDialogWithoutReload('新建','${ctx}/classcard/growth/collection/edit?classCardId=${classCardId}&_index=1','600px','650px',true);">新增</span>
                        </c:if>
                    </p>
                    <div>
                        <ul class="hj-container over ul_chengzhangzuji">
                            <%--  <li class="left">
                                  <a href=""><img src="${ctxStaticNew}/images/app1.png" alt="" width="180px" height="140px"></a>
                                  <p><span>合集名称</span><input type="text" class="ip-name"> <a class="dele" onclick="alertTips('400px','200px','删除合集','确定要删除-------吗？','classCardDelete(\'${classCardView.id}\')')"></a> <a class="edit"></a></p>
                              </li>--%>
                        </ul>
                    </div>
                </div>
                <div class="content-warp baigezhengliu" style="display: none">
                    <p class="btn-containt over">
                        <c:if test="${classCardId!=null&&classCardId!=''}">
                            <span class="up"
                                  onclick="openDialogWithoutReload('上传','${ctx}/classcard/baige/edit?classCardId=${classCardId}&_index=2','450px','400px',true);">上传</span>
                        </c:if>
                    </p>
                    <div>
                        <ul class="hj-container over ul_baigezhengliu">
                            <%--  <li class="left">
                                  <img src="${ctxStaticNew}/images/app1.png" alt="" width="180px" height="140px">
                                  <p><span>名称</span><input type="text" class="ip-name"> <a class="dele" onclick="alertTips('400px','200px','删除合集','确定要删除-------吗？','classCardDelete(\'${classCardView.id}\')')"></a> <a class="edit"></a></p>
                              </li>--%>
                        </ul>
                    </div>
                </div>
                <div class="content-warp huodonglueying" style="display: none">

                    <p class="btn-containt over">
                        <c:if test="${classCardId!=null&&classCardId!=''}">
                            <span class="up"
                                  onclick="openDialogWithoutReload('上传','${ctx}/classcard/active/edit?classCardId=${classCardId}&_index=3','600px','650px',true);">上传</span>
                        </c:if>
                    </p>
                    <div>
                        <ul class="hj-container over ul_huodonglueying">
                            <%--<li class="left">
                                <img src="${ctxStaticNew}/images/app1.png" alt="" width="180px" height="140px">
                                <p><span>标题</span><input type="text" class="ip-name"> <a class="dele" onclick="alertTips('400px','200px','删除合集','确定要删除-------吗？','classCardDelete(\'${classCardView.id}\')')"></a> <a class="edit"></a></p>
                            </li>--%>
                        </ul>
                    </div>
                </div>
            </form>
        </main>
    </div>
</main>


<form id="submit-form" method="post" action="${ctx}/classcard/introduction/index">
    <input type="hidden" name="schoolId" value="${schoolId}">
    <input type="hidden" name="appId" value="${appId}">
    <input type="hidden" name="focusNode" value="${focusNode}">
    <input type="hidden" name="nodeList" value='${nodeList}'>
    <input type="hidden" name="classCardId" value='${classCardId}'>
</form>

<input type="hidden" id="pages_final" value="">
<input type="hidden" id="pageNum_final" value="">
<input type="hidden" id="pid_final" value="">
<input type="hidden" id="current_this" value="">
<input type="hidden" name="growthDelUrls" value="">
<input type="hidden" name="activeDelUrls" value="">
<input type="hidden" name="baigeDelUrls" value="">

<script>
    activeMenu("all", 2);
    var aaa;
    var zTree;
    var demoIframe;

    var nowfocus = "${focusNode}";
    var setting;
    var zNodes0;

    var count;
    var flagcount;
    $('.classperson').on('keyup', function () {
        var str = $(this).val();

        var arry = [];
        arry = str.split("；");

        var checkstr = "；";
        var regex = new RegExp(checkstr, 'g'); // 使用g表示整个字符串都要匹配
        var result = str.match(regex);
        count = !result ? 0 : result.length;
        if (count > 10) {
            flagcount = 0;
        }
        if (count < 10) {
            flagcount = 1;
        }
        if (count == 10) {
            if (arry[10] == '') {
                flagcount = 1;
            } else {
                flagcount = 0;
            }
        }
    });
    $(function () {
        setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false,
                fontCss: setFontCss
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            callback: {
                onClick: onNodeClick,
            }
        };
        <c:if test="${gukeer:notEmptyString(nodeList)}">
        zNodes0 = ${nodeList}
            </c:if>
            <c:if test="${gukeer:emptyString(nodeList)}">
            zNodes0 = [
                {
                    id: "${schoolview.id}",
                    pId: "${schoolview.pid}",
                    name: "${schoolview.name}",
                    open: true
                },
                <c:forEach items='${schoolview.sections}' var='sections'>
                {
                    id: "${sections.id}",
                    pId: "${sections.pid}",
                    name: "${sections.name}",
                    open: ${sections.open}
                },
                <c:forEach items='${sections.schoolTypeView}' var='schoolTypeView'>
                {
                    id: "${schoolTypeView.id}",
                    pId: "${schoolTypeView.pid}",
                    name: "${schoolTypeView.name}",
                    open: ${schoolTypeView.open}
                },
                <c:forEach items='${schoolTypeView.njview}' var='njview'>
                {
                    id: "${njview.tid}",
                    pId: "${njview.pid}",
                    name: "${njview.njname}",
                    open: ${njview.open}
                },
                <c:forEach items='${njview.banjiview}' var='banJiView'>
                {
                    id: "${banJiView.id}",
                    pId: "${banJiView.pid}",
                    name: "${banJiView.name}",
                    open: ${banJiView.open}
                },
                </c:forEach>
                </c:forEach>
                </c:forEach>
                </c:forEach>
            ];
        </c:if>
        $.fn.zTree.init($("#tree1"), setting, zNodes0);
    });

    function setFontCss(treeId, treeNode) {
        if (treeNode.id == nowfocus)
            return {
                'padding-top': ' 0',
                'background-color': '#def7f5',
                'color': 'black',
                'height': '25px',
                'opacity': '.8',
                'width': '86%'
            };
    }
    ;

    function onNodeClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        var nowId = treeNode.id;
        if (treeNode.open == false) {
            treeNode.open = true;
        }
        else {
            treeNode.open = false;
        }
        var slength = "school_".length;

        nowfocus = nowId;
        if (nowId.indexOf("classCard") >= 0) {
            //班牌
            console.log(nowId);
            var classCardId = nowId;
            var rootNode = treeNode.getParentNode().getParentNode().getParentNode().getParentNode();
            var schoolId = rootNode.id;
            classCardId = classCardId.substring("classCard".length);
            schoolId = schoolId.substring("school_".length);
            loadIntroduction(schoolId, classCardId, nowfocus);
        }
    }
    function loadIntroduction(sid, cid, focusId) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        $("input[name='schoolId']").val(sid);
        $("input[name='classCardId']").val(cid);
        $("input[name='focusNode']").val(focusId);
        $("input[name='nodeList']").val(JSON.stringify(zTree.getNodes()));
        $("#submit-form").submit();
    }

    $('#as').diyUpload({
        url: '${ctx}/file/upload',
        success: function (data) {
            $('#displayInformationUrl').val(data.data.imgRequestUrl);
            $('#isupload').val("uploaded");
            $('#fileCategory').val(data.data.fileCategory);
            if (data.data.thumbnailUrl != '') {
                $('#thumbnail').val(true)
            } else {
                $('#thumbnail').val(false);
            }
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '选择文件',
        chunked: false,
        // 分片大小
        chunkSize: 50 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 1,
        fileSizeLimit: 50 * 1024 * 1024,
        fileSingleSizeLimit: 50 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "gif,jpg,jpeg,ico,bmp,png,rmvb,rm,flv,mp4,avi",
        },
    });

    function fun_editForm() {

        if ($(".time").length == 0 || $(".time").is(":hidden")) {
            if (flagcount == 0) {
                layer.msg('班级骨干人数超过十人！');
                return false;
            }
            var $fileName = $('.diyFileName').text();
            var $isupload = $('#isupload').val();
            if ($fileName != '') {
                $('#displayInformationName').val($fileName);
            } else if ($fileName == '' && $isupload != '') {
                $('#displayInformationUrl').val('');
            }
            $.ajax({
                url: "${ctx}/classcard/introduction/save",
                data: $('#editForm').serialize(),
                type: "post",
                success: function (data) {
                    if (data.code == 0) {
                        console.log(data);
                        layer.msg("保存成功");
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    } else {
                        layer.msg("保存失败");
                    }
                },
                error: function () {
                    layer.msg("保存失败");
                }
            });
        } else {
            layer.msg('文件上传中,请等待');
        }
    };

    //拼接页面的图片
    function showPic($pid, $this, pageNum) {
        if ($pid != '') {
            $.ajax({
                url: "${ctx}/classcard/classspace/pic/show",
                type: "post",
                data: {
                    pid: $pid,
                    classCardId: '${classCardId}',
                    pageNum: pageNum
                },
                success: function (retDate) {
                    console.log(retDate.code);
                    if (retDate.code == 0) {
                        var pageInfo = retDate.data.pageInfo;
                        var list = pageInfo.list;
                        //成长足迹
                        if (retDate.data.pid == '-1') {
                            console.log($('.ul_chengzhangzuji li'));
                            $('.ul_chengzhangzuji li').remove();
                            for (var i = 0; i < list.length; i++) {
                                console.log(list)
                                var tmp_ = '';
                                if (list[i].thumbnailUrl != '' && list[i].thumbnailUrl != null) {
                                    tmp_ = list[i].thumbnailUrl
                                } else {
                                    tmp_ = list[i].picUrl
                                }

                                $('.ul_chengzhangzuji').append("<li class='left'>" +
                                    "<a target='__black' href='${ctx}/classcard/growth/collectiondetail?classCardId=${classCardId}&collectionId=" + list[i].id + "'>" +
                                    "<img src='${ctx}/file/pic/show?picPath="
                                    + tmp_ +
                                    "' alt='' width='180px' height='140px'></a>" +
                                    " <p><span>" + list[i].picTitle + "</span>" +
                                    "<input type='text' class='ip-name'data-id='" + list[i].id + "'> " +
                                    "<a class='dele'data-flag='czzj'data-collectId='" + list[i].id + "'> </a> " +
                                    "<a class='edit'></a></p>" +
                                    " </li>");
                            }
                            $('#pages_final').val(pageInfo.pages);
                            $('#pageNum_final').val(pageInfo.pageNum);
                            $('#pid_final').val(retDate.data.pid);
                            var html = '';
                            html += "　<div class='fenye'>";
                            if ('${gukeer:notEmptyString(pageInfo.pages)}') {
                                html += "　<div class='fenyDetail'>共" + pageInfo.total + "条记录，本页" + pageInfo.size + "条</div>";
                            }
                            html += " <div class='fenY' id='fenY'>";
                            html += " </div>";
                            html += " </div>";
                            if ($('.ul_chengzhangzuji').parent().children().length > 1) {
                                $('.ul_chengzhangzuji').parent().children().eq(-1).remove();
                            }
                            ;
                            $('.ul_chengzhangzuji').parent().append(html);
                            initFunc();
                        }

                        //百舸争流
                        if (retDate.data.pid == 'bgzl') {
                            $('.ul_baigezhengliu li').remove();
                            for (var i = 0; i < list.length; i++) {
                                console.log($('.ul_baigezhengliu'));
                                var tmp_ = '';
                                if (list[i].thumbnailUrl != '' && list[i].thumbnailUrl != null) {
                                    tmp_ = list[i].thumbnailUrl
                                } else {
                                    tmp_ = list[i].picUrl
                                }
                                $('.ul_baigezhengliu').append("<li class='left'>" +
                                    "<div id='layer-photos-baige' class='layer-photos-baige'>" +
                                    "<img layer-pid='' layer-src='${ctx}/file/pic/show?picPath=" + list[i].picUrl + "' src='${ctx}/file/pic/show?picPath="
                                    + tmp_ +
                                    "' alt='" + list[i].picName + "' width='180px' height='140px'>" +
                                    " </div>" +
                                    " <p><span>" + list[i].picTitle + "</span>" +
                                    "<input type='text' class='ip-name'data-id='" + list[i].id + "'> " +
                                    "<a class='dele'data-flag='bgzl'data-picId='" + list[i].id + "'data-pic_url='" + list[i].picUrl + "'> </a> " +
                                    "<a class='edit'></a></p>" +
                                    " </li>");
                            }
                            $('#pages_final').val(pageInfo.pages);
                            $('#pageNum_final').val(pageInfo.pageNum);
                            $('#pid_final').val(retDate.data.pid);
                            var html = '';
                            html += "　<div class='fenye'>";
                            if ('${gukeer:notEmptyString(pageInfo.pages)}') {
                                html += "　<div class='fenyDetail'>共" + pageInfo.total + "条记录，本页" + pageInfo.size + "条</div>";
                            }
                            html += " <div class='fenY' id='fenY'>";
                            html += " </div>";
                            html += " </div>";
                            if ($('.ul_baigezhengliu').parent().children().length > 1) {
                                $('.ul_baigezhengliu').parent().children().eq(-1).remove();
                            }
                            ;
                            $('.ul_baigezhengliu').parent().append(html);
                            initFunc();
                        }
                        //活动掠影
                        if (retDate.data.pid == 'hdly') {
                            $('.ul_huodonglueying li').remove();
                            for (var i = 0; i < list.length; i++) {
                                console.log($('.ul_huodonglueying'));
                                var tmp_ = '';
                                if (list[i].thumbnailUrl != '' && list[i].thumbnailUrl != null) {
                                    tmp_ = list[i].thumbnailUrl
                                } else {
                                    tmp_ = list[i].picUrl
                                }
                                $('.ul_huodonglueying').append("<li class='left'>" +
                                    "<div id='layer-photos-active' class='layer-photos-active'>" +
                                    "<img layer-pid='' layer-src='${ctx}/file/pic/show?picPath=" + list[i].picUrl + "' src='${ctx}/file/pic/show?picPath="
                                    + tmp_ +
                                    "' alt='" + list[i].picName + "' width='180px' height='140px'>" +
                                    " </div>" +
                                    " <p><span>" + list[i].picTitle + "</span>" +
                                    "<input type='text' class='ip-name'data-id='" + list[i].id + "'> " +
                                    "<a class='dele'data-flag='hdly'data-picId='" + list[i].id + "'data-pic_url='" + list[i].picUrl + "'> </a> " +
                                    "<a class='edit'></a></p>" +
                                    " </li>");
                            }
                            $('#pages_final').val(pageInfo.pages);
                            $('#pageNum_final').val(pageInfo.pageNum);
                            $('#pid_final').val(retDate.data.pid);
                            var html = '';
                            html += "　<div class='fenye'>";
                            if ('${gukeer:notEmptyString(pageInfo.pages)}') {
                                html += "　<div class='fenyDetail'>共" + pageInfo.total + "条记录，本页" + pageInfo.size + "条</div>";
                            }
                            html += " <div class='fenY' id='fenY'>";
                            html += " </div>";
                            html += " </div>";
                            if ($('.ul_huodonglueying').parent().children().length > 1) {
                                $('.ul_huodonglueying').parent().children().eq(-1).remove();
                            }
                            ;
                            $('.ul_huodonglueying').parent().append(html);
                            initFunc();
                        }
                    }
                    localStorage.moduleindex = '';
                    var a = $('.roll-operation span').index($this);
                    $this.addClass('active').siblings().removeClass('active');
                    $('.content-warp').eq(a).show().siblings().hide();
                },
                error: function () {
                    layer.msg('error');
                }
            })
        } else {
            var a = $('.roll-operation span').index($this);
            $this.addClass('active').siblings().removeClass('active');
            $('.content-warp').eq(a).show().siblings().hide();
        }
    }
    $('.roll-operation span').on("click", function () {
        var $pid = $(this).data("pid");
        var $this = $(this);
        showPic($pid, $this, 1);
        $('#current_this').val($this);
    });

    //上传照片后刷新当前导航栏
    function chooseResult(_index) {
        var $this = $('.roll-operation span').eq(_index);
        var $pid = $this.data("pid");
        showPic($pid, $this, 1);
    }
    //上传后的图片url
    function setDelUrls(delUrls, flag) {
        if (flag == 'growth') {
            $('input[name=growthDelUrls]').val(delUrls);
            $('input[name=activeDelUrls]').val('');
            $('input[name=baigeDelUrls]').val('');
        }
        if (flag == 'active') {
            $('input[name=activeDelUrls]').val(delUrls);
            $('input[name=growthDelUrls]').val('');
            $('input[name=baigeDelUrls]').val('');
        }
        if (flag == 'baige') {
            $('input[name=baigeDelUrls]').val(delUrls);
            $('input[name=growthDelUrls]').val('')
            $('input[name=activeDelUrls]').val('');
        }
    }

</script>
</body>
</html>
<script>
    function initFunc() {
        var flag = 1;

        $('.hj-container li ').on('mouseover', function () {
            if (flag) {
                $(this).children('p').children('a').show();
                $(this).children('p').children('span').css('width', '100px');
            }
        });
        $('.hj-container li ').on('mouseout', function () {
            $(this).children('p').children('a').hide();
            $(this).children('p').children('span').css('width', '175px');
        });


        $('.edit').on('click', function () {
            $(this).hide();
            $(this).siblings().hide();
            $(this).siblings('input').show().val($(this).siblings('span').text());
            $(this).siblings('input').focus();
            flag = 0;
        });

        $('.ip-name').on('keyup', function (e) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                $(this).hide();
                $(this).siblings('span').show().text($(this).val());

                var $id = $(this).data("id");
                $.ajax({
                    url: "${ctx}/classcard/picture/save",
                    type: "post",
                    data: {
                        picTitle: $(this).val(),
                        id: $id,
                        pid: "-1",
                        classCardId: '${classCardId}'
                    },
                    success: function (retDate) {
                        if (retDate.code == -1) {
                            layer.msg(retDate.msg);
                        }
                    },
                    error: function () {
                        layer.msg(err);
                    }
                })
                flag = 1;
            }
            ;
        });
        $('.dele').on('click', function () {
            if ($(this).data('flag') == 'czzj') {
                var $collectId = $(this).data('collectid');
                alertTips('400px', '200px', '删除通知', '确定要删除此合集吗，若确定则合集内所有照片也将删除？', 'picDelete(\'' + $collectId + '\',\'-1\',\'\')');
            }
            if ($(this).data('flag') == 'bgzl') {
                var $picId = $(this).data('picid');
                var $pic_url = $(this).data('pic_url');
                var ori_id_url = $picId + "," + $pic_url;
                alertTips('400px', '200px', '删除通知', '确定要删除此照片吗？', 'picDelete(\'\',\'bgzl\',\'' + ori_id_url + '\')');
            }
            if ($(this).data('flag') == 'hdly') {
                var $picId = $(this).data('picid');
                var $pic_url = $(this).data('pic_url');
                var ori_id_url = $picId + "," + $pic_url;
                alertTips('400px', '200px', '删除通知', '确定要删除此照片吗？', 'picDelete(\'\',\'hdly\',\'' + ori_id_url + '\')');
            }
        });

        _pages = $('#pages_final').val();
        _pageNum = parseInt($('#pageNum_final').val());
        _this = $('#current_this').val();

        <c:if test="${_pages != 0}">
        $(".fenY").createPage({
            pageCount: _pages,//总页数
            current: _pageNum,//当前页面
            backFn: function (p) {
                _this = $('.roll-operation .active')
                showPic($('#pid_final').val(), _this, p);
            }
        });
        $('.gotoPage').on('click', function () {
            var p = $('.go').val();
            if (p <= 0 || p > _pages) {
                layer.msg("请输入正确的页码")
            } else {
                _this = $('.roll-operation .active')
                showPic($('#pid_final').val(), _this, p);
            }
        })
        </c:if>
        layer.photos({
            photos: '.layer-photos-baige'
            //,anim: 1 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
        layer.photos({
            photos: '.layer-photos-active'
            //,anim: 1 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
    }
    function picDelete(id, pid, ori_id_url) {
        closeAlertDiv();
        var idUrlList = [];

        if (ori_id_url != '') {
            var id_url = {};
            id_url.id = ori_id_url.split(',')[0];
            id_url.url = ori_id_url.split(',')[1];
            idUrlList.push(id_url);
        }
        $.post("${ctx}/classcard/picture/multidelete", {
            classCardId: '${classCardId}',
            collectionId: id,
            pid: pid,
            id_urls: JSON.stringify(idUrlList)
        }, function (retJson) {
            if (retJson.code == 0) {
                var $this = $('.roll-operation .active');
                showPic($this.data("pid"), $this, 1);
                layer.msg("删除成功");
            } else {
                layer.msg("删除失败");
            }
        }, "json");
    }
</script>
