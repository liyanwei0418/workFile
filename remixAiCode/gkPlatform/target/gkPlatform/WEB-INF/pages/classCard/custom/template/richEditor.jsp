<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>图文编辑器</title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/upload/h5upload.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/themes/default/default.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/kindeditor.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.js"></script>
    <style>
        .container {
            width: 95%;
            margin: 0px auto;
            /*padding-top: 30px;*/
            font: 13px '微软雅黑';
            overflow-x: hidden;
        }
        .stuMsgg textarea{
            vertical-align: top;
            width: 590px;
            outline: none;
            resize: none;
            height: 400px;
            padding: 5px;
        }
        .ke-container{
            width: 99% !important;
            max-width: 1030px !important;
            margin: 0 auto;
        }

        @media (min-width: 1200px) {
            .ke-edit{
                height: 750px !important;
            }
            .ke-edit-iframe{
                height: 750px !important;
            }
        }
        @media (min-width: 992px) {
            .ke-edit{
                height: 520px !important;
            }
            .ke-edit-iframe{
                height: 520px !important;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div style="padding-top: 20px;">
        <textarea placeholder="请输入内容" id="content" name="content" row="10" cols="100"></textarea>
    </div>
</div>
<script>
    $('.layui-layer').css('minWidth', '100px');

    var editor;
    KindEditor.ready(function (K) {
        editor = K.create('textarea', {
            cssPath: '${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css',
            uploadJson: '${ctxStaticNew}/kindeditor-4.1.10/jsp/upload_json.jsp',
            allowFileManager: true,
            urlType : 'domain',
            height : "500px",
            resizeType : 1,
            items:[  'undo', 'redo', '|','preview','print','|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', '|', 'formatblock', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage', 'table', 'hr', 'emoticons'],
            afterCreate: function () {
                var self = this;
                K.ctrl(document, 13, function () {
                    self.sync();
                    document.forms['example'].submit();
                });
                K.ctrl(self.edit.doc, 13, function () {
                    self.sync();
                    document.forms['example'].submit();
                });
            }
        });
        prettyPrint();
    });

    var name = '${name}';
    /* 通过name获取父页面对应的content初始化编辑器的数据*/
    $("#content").html(window.parent.getContent(name));
    /*获取编辑器内容*/
    function retContent() {
       return editor.html();
    }
    function doSubmit(){ //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        window.parent.chooseResult(retContent(),name);//调用父级frame的js方法将选择的值传递给父级页面
        window.parent.closeAlertDiv();
        return false;//防止顶层页面刷新
    }
</script>
</body>
</html>