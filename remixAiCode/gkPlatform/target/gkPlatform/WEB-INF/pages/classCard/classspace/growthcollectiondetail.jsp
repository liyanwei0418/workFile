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
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/diyUpload.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/oldCss.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/webuploader.css">
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/diyUploadmore.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/webuploader.html5only.min.js"></script>

    <style type="text/css">

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
    </style>

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

        main.col-xs-9 {
            border-left: none;
            padding-left: 0 !important;
            padding-right: 0 !important;

            width: 100%;
        }

        main.container {
            margin-bottom: 0px !important;
        }

        .roll-operation {
            width: 100%;
            margin-top: 25px;
            padding-bottom: 10px;
            border-bottom: 1px solid #ddd;
        }

        .roll-operation a {
            display: inline-block;
            margin-right: 20px;
            text-decoration: none;
            line-height: 35px;
            color: #525252;
        }

        .roll-operation .active {
            color: #54ab37;
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
            margin-right: 5px;
        }

        p {
            margin-top: 20px;
        }

        .btn-containt {
            display: inline-block;
            float: right;
            margin-top: 0;
        }

        .parentFileBox {
            display: none;
        }

        .btn-containt button, .btn-containt span {
            height: 30px;
            color: #fff;
            cursor: pointer;
            border: none;
            border-radius: 2px;
            outline: none;
            padding: 0 15px;
            text-align: center;
        }

        .btn-containt span {
            width: auto !important;
        }

        .btn-containt .save, .back, .up {
            background: #54AB37;
            margin-right: 15px;
        }

        .manage, .getmanage {
            background: #0090ff;
        }

        .btn-containt .quit {
            background: #ddd;
        }

        .btn-containt button {
            border-radius: 3px;
            padding: 0 8px;
        }

        .hj-container {
            margin-top: 30px;
        }

        .hj-container li {
            position: relative;
            margin: 0 7px 25px 8px;
        }

        .hj-container li div {
            cursor: pointer;
        }

        .hj-container li p a {
            float: right;
            margin-left: 10px;
            text-decoration: none;
        }

        .hj-container li > a {
            display: inline-block;
        }

        .hj-container li p span {
            width: 100px;
            text-align: left;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            line-height: 28px;
        }

        .hj-container li p input {
            width: 120px;
            height: 28px;
            padding-left: 5px;
            display: none;
        }

        .hj-container li p a {
            display: inline-block;
            width: 15px;
            height: 16px;
            cursor: pointer;
            float: none;
            margin-left: 0;
            position: absolute;
            top: 0;
            right: 0;
            display: none;
        }

        .back, .up-sp {
            float: left;
        }

        p .up-sp {
            position: relative;
        }

        p .up-sp button {
            margin-right: 0;
        }

        p .up-sp input {
            width: 86px !important;
            height: 30px;
            margin-right: 0;
            position: absolute;
            top: 0;
            left: 0;
            opacity: 0;
        }
    </style>
</head>
<body>

<%@ include file="../../common/sonHead/classCardHead.jsp" %>
<main class="container">
    <!--班牌管理-->
    <div id="stu-manage">
        <main class="col-xs-9">
            <div class="content-warp">
                <div class="over">
                    <p class="btn-containt over">
                        <button class="manage">批量管理</button>
                    </p>
                    <div style="display: inline-block;vertical-align: top" id="as" data-flag='collectiondetail'
                         class="webuploader-container">
                        <div class="webuploader-pick">上传照片</div>
                    </div>
                </div>

                <div>
                    <ul class="hj-container over">
                        <c:forEach items="${pageInfo.list}" var="picture">
                            <li class="left">
                                <div id='layer-photos' class='layer-photos'>
                                    <img layer-pid='' layer-src='${ctx}/file/pic/show?picPath=${picture.picUrl}'
                                         src='${ctx}/file/pic/show?picPath=${picture.thumbnailUrl!=''?picture.thumbnailUrl:picture.picUrl}' alt='${picture.picName}'
                                         width='180px' height='140px'>
                                </div>
                                <p><span>${picture.picName}</span><a class="delee" data-id="${picture.id}"
                                                                     data-url="${picture.picUrl}"></a></p>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="fenye">
                    <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                        <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                    </c:if>
                    <div class="fenY" id="fenY">
                        <%--<input type="text"/>--%>
                    </div>
                </div>
            </div>
        </main>
    </div>
</main>


<form id="submit-form" method="post" action="${ctx}/classcard/growth/collectiondetail">
    <input type="hidden" name="collectionId" value="${collectionId}">
    <input type="hidden" name="classCardId" value="${classCardId}">
    <input type="hidden" name="pageNum" value="${pageNum}">
    <input type="hidden" id='urls' value="">
    <input type="hidden" name="failList" value="">

</form>
<script>
    var flag = 0;
    $('.manage').on('click', function () {
        flag = !flag;
        if (flag) {
            $('.back .up').hide();
            $('#as').hide();
            $(".delee").show();
            $(this).text('完成管理');
        }
        else {
            $('.back .up').show();
            $('#as').show();
            $(".delee").hide();
            $(this).text('批量管理');

        }
    });

    $(".delee").on('click', function () {
        $(this).parents('li').remove();
//       var id =$(this).data('id');
//       var url=$(this).data('url');
        var id_urls = [];
        var id_url = {}
        id_url.id = $(this).data('id');
        id_url.url = $(this).data('url');
        id_urls.push(id_url);

        $.ajax({
            url: "${ctx}/classcard/picture/multidelete",
            type: "post",
            data: {
                id_urls: JSON.stringify(id_urls),
                classCardId: '${classCardId}',
                flag: "collectionDetail",
                pid: '${collectionId}'
            },
            success: function () {
            },
            error: function () {
                layer.msg("删除失败");
            }
        })
    })

    activeMenu("stuManMenu", 0);
    var count_success = 0;
    var count_err = 0;
    var flag = false;
    var count = -1;
    $('#as').diyUpload({
        url: '${ctx}/file/upload',
        success: function (retJson) {
            console.info(retJson);
            var data = retJson.data;
            console.info($('#urls').val());
            count++;
            var urlNameList = [];
            var url_name = {};
            url_name.url = data.imgRequestUrl;
            url_name.name = data.fileName;
            if( data.thumbnailUrl!=''){
                url_name.thumbnail=true;
            }else{
                url_name.thumbnail=false;
            }

            urlNameList.push(url_name);
            $.ajax({
                url: "${ctx}/classcard/growth/collection/save",
                type: "post",
                data: {
                    classCardId: '${classCardId}',
                    pid: '${collectionId}',
                    url_name: JSON.stringify(urlNameList)
                },
                success: function (saveRetJson) {
                    if (saveRetJson.code == 0) {
                        if (count_success == 0) {
                            //setTimeout(function(){ layer.msg('上传成功');}, 1000);
                            count_success++;
                        }
                        setTimeout(function () {
                            window.location.reload();
                        }, 1000);
                    } else {
                        if (count_err == 0) {
                            layer.msg(saveRetJson.msg);
                            count_err++;
                        }
                        //删除超量的图片
                        var id_urls=[];
                        var id_url={};
                        id_url.id="-1";
                        id_url.url=saveRetJson.data;
                        id_urls.push(id_url);
                        $.ajax({
                            url: "${ctx}/classcard/picture/multidelete",
                            type: "post",
                            data: {
                                id_urls: JSON.stringify(id_urls),
                                classCardId: '${classCardId}',
                                pid: '${collectionId}'
                            },
                            success: function () {
                                console.info('delete success!');
                            },
                            error: function () {
                                layer.msg("超量图片删除失败");
                            }
                        })
                    }
                },
                error: function () {
                    layer.msg('上传失败');
                }
            })
        },
        error: function (err) {
            console.info(err);
        },
        buttonText: '上传照片',
        chunked: false,
        // 分片大小
        chunkSize: 10 * 1024 * 1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit: 500,
        fileSizeLimit: 1500 * 1024 * 1024,
        fileSingleSizeLimit: 3 * 1024 * 1024,
        accept: {
            title: "Images",
            extensions: "gif,jpg,jpeg,bmp,png"
        },
    });

    layer.photos({
        photos: '.layer-photos'
        // ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
    });
    <c:if test="${pageInfo.pages != 0}">
    $(".fenY").createPage({
        pageCount:${pageInfo.pages},//总页数
        current:${pageInfo.pageNum},//当前页面
        backFn: function (p) {
            $("input[name='pageNum']").val(p);
            $("form").submit();
        }
    });
    $('.gotoPage').click(function () {
        var p = $('.go').val();
        if (p <= 0 || p >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            $("input[name='pageNum']").val(p);
            $("form").submit();
        }
    });
    </c:if>
</script>
</body>
</html>