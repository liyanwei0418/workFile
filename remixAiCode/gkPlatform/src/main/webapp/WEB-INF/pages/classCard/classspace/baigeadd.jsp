<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
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
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/diyUpload.css">
    <link rel="stylesheet" type="text/css" href="${ctxStaticNew}/diyUpload/css/webuploader.css">
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/diyUpload.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/diyUpload/js/webuploader.html5only.min.js"></script>

    <style>
        .over{
            overflow: hidden;
        }
        .left{
            float: left;
        }
        i{
            font-style: normal;
        }
        *{
            margin:0;
            padding: 0;
        }
        .content-warp{
            margin-top: 30px;
        }
        span{
            display: inline-block;
        }
        p span:first-child{
            width: 70px;
            text-align: right;
            margin-right: 10px;
        }
        p textarea{
            width: 380px;
            min-height: 80px;
            padding-left: 5px;
            border: 1px solid #ddd;
            outline: none;
            resize: none;
            vertical-align: top;
            border-radius: 3px;
        }
        p textarea[name='classIntroduction']{
            height: 120px;
        }
        p{
            margin-top: 20px;
        }
    </style>
    <style>
        .parentFileBox{
            width: auto !important;
            display: block !important;
            margin-left: 76px !important;
        }
        .fileBoxUl{
            width: 300px !important;
            max-height: 400px !important;
            overflow: auto !important;
        }
        .fileBoxUl li{
            margin-left: 0 !important;
        }
        .parentFileBox>.diyButton {
            width: 100%;
            margin-top: 5px;
            margin-bottom: 5px;
            height: 20px;
            line-height: 20px;
            text-align: left;
        }
        .container{
            padding-left: 50px;
        }
        p input{
            width: 190px;
            height: 28px;
            border-radius: 2px;
            outline: none;
            border: 1px solid #ddd;
            padding-left: 5px;
        }
        span{
            font-size: 13px;
            display: inline-block;
            width: 70px;
            text-align: right;
            margin-right: 10px;
        }
        i{
            display: inline-block;
            text-align: center !important;
            font-size: 12px;
            font-style: normal;
            color: #999;
            padding: 5px 0 0 3px;
            position: absolute;
            top: 70px;
            left: 225px;
        }
        li{
            list-style: none;
        }
    </style>
</head>
<body>

<form  action="${ctx}/classcard/picture/save">
    <div class="container">
        <p><span>标题:</span><input type="text" placeholder="格式:名称-作者" maxlength="20"  name="picTitle" id="picTitle" value=""></p>
        <ul style="margin-top: 20px;">
            <li>
                <span style="vertical-align: top;margin-top: 5px">上传作品:</span>
                <div style="display: inline-block;vertical-align: top" id="as" class="webuploader-container">
                    <div class="webuploader-pick">选择文件</div>
                    <div id="rt_rt_1bl0b87jqh558hn1p7uvk91iu54" style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                        <input type="file" name="file" class="webuploader-element-invisible">
                        <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                    </div>
                </div>
                <i>仅限1张（3M以内）</i>
            </li>
        </ul>
    </div>
    <input type="hidden" name="url" value="" id="picUrl">
    <input type="hidden" name="pic_urls" id="thumbnail_url" value="">
    <input type="hidden" name="" value="" id="picName">
    <input type="hidden" name="classCardId" value="${classCardId}" >
    <input type="hidden" name="ctx" value="${ctx}" >
    <input type="hidden" name="failList" value="">


</form>
<script>
    $('#as').diyUpload({
        url:'${ctx}/file/upload',
        success:function( data ) {
            console.info( data );
            $('#picUrl').val(data.data.imgRequestUrl);
            if(data.data.thumbnailUrl!=''){
                $('#thumbnail_url').val(true);
            }else{
                $('#thumbnail_url').val(false);
            }

            //将url赋值给父页面，为删除未保存的图片
            window.parent.setDelUrls($('#picUrl').val(),'baige');
        },
        error:function( err ) {
            console.info( err );
        },
        buttonText : '选择文件',
        chunked:false,
        // 分片大小
        chunkSize:10*1024*1024,
        //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
        fileNumLimit:1,
        fileSizeLimit:3*1024 * 1024,
        fileSingleSizeLimit:3*1024* 1024,
        accept:{
            title:"Images",
            extensions:"gif,jpg,jpeg,bmp,png",
        },
    });

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        if($(".time").length == 0 ||$(".time").is(":hidden")) {
            var pictitle=$('#picTitle').val();
            if(pictitle!=''){
            $.post($("form").attr('action'), {
                picTitle:$('#picTitle').val(),
                classCardId:'${classCardId}',
                pic_url:$('#picUrl').val(),
                pic_name:$('.diyFileName').text(),
                thumbnail_url:$('#thumbnail_url').val(),
                pid:"bgzl"
            }, function (retJson) {
                if (retJson.code == 0) {
                    layer.msg('上传成功');

                    //index为导航栏的序号
                    window.parent.chooseResult('${_index}');//调用父级frame的js方法将选择的值传递给父级页面
                } else {
                    layer.msg('上传失败');
                }
            });
            return true;
            }else {
                    layer.msg("请输入名称-作者！");
                return false;
            }
        }else{
            layer.msg('图片上传中,请等待');
        }
    }
</script>
</body>
</html>