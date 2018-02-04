<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>自定义界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jeDate-test.css">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/diyUploadCustom/css/webuploader.css">
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/diyUploadCustom/css/diyUpload.css">
    <script type="text/javascript" src="${ctxStaticNew}/diyUploadCustom/js/webuploader.html5only.min.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/diyUploadCustom/js/diyUploadmore.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
</head>
<style>

    .mode-name input{
        border: none;
    }
    main.container{
        margin-bottom: 0 !important;
    }

    /*-------------------------------------------------*/
    .mode-name{
        margin: 10px;
        margin-left: 0;
        overflow: hidden;
        display: inline-block;
    }
    .mode-container{
        width: 100%;
        height:65%;
        border: 5px solid #ddd;
    }
    .check-btnp button{
        height: 30px;
        padding: 0 15px;
        margin-right: 4px;
        border: 1px solid #54ab37;
        background: #54ab37;
        color: #fff;
        border-radius: 2px;
    }
    .edit-range>div{
        display: none;
    }
    .imgAtext button{
        background: #54AB37;
        height: 30px;
        padding: 0 15px;
        border: 1px solid #54AB37;
        outline: none;
        border-radius: 2px;
        color: #fff;
    }
    .mode-name select{
        height: 30px;
        width: 130px;
        border-radius: 2px;
        padding-left: 5px;
        color: #525252;
        font-size: 14px;
    }
    .edit-range{
        display: inline-block;
        vertical-align: top;
        margin: 10px 0;
    }
    #imgButton-upload,#videoButton-upload{
        display: inline-block;
    }
    .parentFileBox{
        width: 100% !important;
    }

    .check-btnp{
        overflow: hidden;
        margin: 10px 0;
    }
    .check-btnp > button, .check-btnp>span, .check-btnp>a{
        float: right;
    }
    #img-upload input[type='radio'],
    #video-upload input[type='radio']{
        position: relative;
        top:2px;
        margin: 5px;
    }
</style>
<body>
<%@ include file="../../../common/sonHead/classCardHead.jsp" %>
<main class="container">

    <div>
        <p class="mode-name">页面名称：<input ${option} type="text" value="${pageName}" id="pageName">
            <span style="padding-left: 30px">
                <c:if test="${option == 'disabled'}">
                    发布的时间：${loopTime}#发布周期：${loopMark}>>${loopDate}
                </c:if>
            </span>
        </p>
    </div>
    <div>
        <c:if test="${option == 'disabled'}">
            <b>已发布不能修改</b>
        </c:if>
        <c:if test="${option != 'disabled'}">
            <div class="mode-name">内容类型：
                <select class="editR-sl">
                    <option value="1" selected="selected">文字及图片</option>
                    <option value="2">仅图片</option>
                    <option value="3">仅视频</option>
                </select>
            </div>
            <div class="edit-range">
                <div class="imgAtext" style="display: block;">
                    <button onclick="openEditor('编辑内容','pageContent')">编辑内容</button>
                </div>
                <div id="img-upload">
                    <div id="imgButton-upload"></div>
                   <%-- <span style="position: relative;bottom: 4px;">
                    <span>图片是否自动播放：</span>
                        <input type="radio" name="imgPlay">是
                        <input type="radio" name="imgPlay">否
                    </span>--%>
                    <button onclick="openEditor('编辑标题','headline')">修改标题文字</button>
                    <button onclick="managerImg('img_container')">轮播图片管理</button>
                </div>
                <div id="video-upload">
                    <div id="videoButton-upload"></div>
                    <button onclick="openEditor('编辑标题','headline')">修改标题文字</button>
                </div>
            </div>
        </c:if>
    </div>
    <p class="check-btnp">
        <span class="check-mode"><button onclick="preview()">预览</button></span>
        <a href="${ctx}/classcard/custom/index"><button>返回主页面</button></a>
        <button onclick="doSubmit()">保存</button>
    </p>

    <div id="img-container"></div>

    <%--班牌编辑预览容器，将模板子页面嵌套在这里--%>
    <div class="mode-container" id="pageContent" style="overflow: auto">
        <c:if test="${empty pageContent}">
            <jsp:include page="${templateFilePath}.jsp" flush="true"/>
        </c:if>
        <c:if test="${not empty pageContent}">
            ${pageContent}
        </c:if>
    </div>

</main>
</body>
<script>
    activeMenu("base",4);
    function chooseResult(content,nameId) {
        $("#"+nameId).empty();
        $("#"+nameId).html(content);
    }

    /*需求：编辑器只能修改模板为图文模块的内容，name为图文模块的div的name。。。目前情况:修改整个模板的内容（单图文模式）*/
    function openEditor(option,name) {
        openDialogWithoutReload(option,'${ctx}/classcard/custom/richEditor?name='+name,'75%','75%');
    }
    function preview(pageId) {
        openDialogView('页面预览','${ctx}/classcard/custom/preview?pageId'+pageId,'65%','75%');
    }
    function managerImg(imgContainerId) {
        //管理轮播图片容器中的图片，删除，调顺序？
        var container = $("#"+imgContainerId);
        //获取img容器中的img
        //管理：1.每个img能删除(服务器已上传的图片也同时删除)。
        // 2.排序
        layer.msg("功能未上线");
    }

    function doSubmit() {
        var names = "pageContent";
        var contents = $("#pageContent").html();
        var pageName = $("#pageName").val();
        var templateId = '${templateId}';
        var pageId = '${pageId}';
        var option = '${option}';
        var url = "${ctx}/classcard/custom/save";
        $.post(url, {
            names: names,
            contents: contents,
            pageName: pageName,
            templateId: templateId,
            pageId: pageId,
            option: option
        }, function (retJson) {
            if (retJson.code == '0') {
                window.location.href = "${ctx}/classcard/custom/index";
            } else {
                layer.msg(retJson.msg);
            }
        },"json");
    }

    /*弹窗子页面调用读取父页面数据*/
    function getContent(name) {
        return $("#"+name).html();
    }

    function startUpload() {
        /*图片上传*/
        $('#imgButton-upload').diyUpload({
            url:'${ctx}/file/upload',
            success:function( data ) {
                /*上传成功后在pageContent中预览，添加返回的url*/
                var imgContainer = $("#img_container");
                var childImg = "<div class=\"swiper-slide\"><img src=\"${ctx}/file/pic/show?picPath="+data.data.imgRequestUrl+"\" alt=\"\"></div>";
                imgContainer.append(childImg);
                //上传图片后重新加载轮播插件，在轮播中添加新上传的图片
                init();
                console.info( data );
            },
            error:function( err ) {
                console.info( err );
            },
            buttonText : '选择图片',
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

        /*视频上传*/
        $('#videoButton-upload').diyUpload({
            url:'${ctx}/file/upload',
            success:function( data ) {
                /*上传成功后修改 模板中video的 src*/
                console.info( data );
                /*<source src="http://121.42.168.14:10012/video/test.mp4" type='video/mp4'>*/
                $("#my-video").attr("src",data.data.imgRequestUrl);//更新url
                $("#my-video").attr("autoplay","true");//直接播放
            },
            error:function( err ) {
                console.info( err );
            },
            buttonText : '选择视频',
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
    }

    /*下拉框的选择事件*/
    $(".editR-sl").on("change",function(){
        var index_op = $(this).find('option').index($(this).find('option:selected'))
        $(this).parent().siblings().children('div').eq(index_op).show().siblings().hide();
        startUpload();
    });

    //图片轮播插件
    function init() {
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            nextButton: '.swiper-button-next',
            prevButton: '.swiper',
            paginationClickable: true,
            spaceBetween: 30,
            centeredSlides: true,
            autoplay: 2500,
            autoplayDisableOnInteraction: false
        });
    }
    init();
</script>
</html>