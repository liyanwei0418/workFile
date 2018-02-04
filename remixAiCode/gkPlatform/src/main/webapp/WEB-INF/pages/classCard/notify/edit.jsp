<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="${ctxStatic}/js/jquery-1.7.2.js"></script>
    <script src="${ctxStatic}/js/laydate/laydate.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctxStatic}/js/openDialog.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/themes/default/default.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/kindeditor.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.js"></script>
    <style>

        .container {
            width: 800px;
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

        .left {
            float: left;
            width: 50%;
        }

        .right {
            float: right;
            width: 50%;
        }

        .stuMsg li>span {
            display: inline-block;
            width: 120px;
            text-align: right;
            color: #666;
        }

        .stuMsg input[type=text] {
            width: 190px;
            height: 28px;
            padding:0;
            padding-left:5px;
            border-radius:2px;
            border:1px solid #a9a9a9;
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
            padding-left:11px;
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
            padding:0;
            padding-left:5px;
            width:190px;
            border:1px solid #a9a9a9;
            border-radius: 2px;
            outline: none;
        }
        .stuMsgg textarea{
            vertical-align: top;
            width: 590px;
            outline: none;
            resize: none;
            height: 400px;
            padding: 5px;
        }

        i{
            font-style: normal;
            display: inline-block;
            padding: 10px 6px;
            /*margin-right: 10px;*/
        }
        #checkedEquipment{
            width: auto;
            max-width: 525px;
            margin-right: 10px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            vertical-align: middle;
        }
        input, textarea{
            background: #fff !important;
        }

        #content{
            width:600px;
            display: inline-block;
            vertical-align: top;
        }
        #edui1{
            width: 600px !important;
        }
        #edui1_iframeholder{
            min-height: 450px !important;
        }
        .stuMsgg .ke-container{
            width: 600px !important;
            display: inline-block;
            vertical-align: top;
        }
    </style>
</head>
<body>
<form action="${ctx}/classcard/save">
</form>

    <div class="container">
        <%--<h3>通知信息</h3>--%>
        <div class="stuMsg">
            <ul class="left">
                <input type="hidden" name="ueditorPics" value="" id="ueditorPics">
                <li>
                    <span>标题：</span>
                    <input ${disabled} type="text" maxlength="30"id="title" name="title" placeholder="请输入标题30字以内" value="${classCardNotify.title}"/>
                </li>
            </ul>
            <ul class="right">
                <li>
                    <span>通知类型：</span>
                    <select ${disabled} id="notifyType">
                        <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
                            <option value="2"  <c:if test="${classCardNotify.type==2}"> selected </c:if>>校园通知</option>
                        </shiro:hasAnyRoles>
                        <option value="1"  <c:if test="${classCardNotify.type==1}"> selected </c:if>>班级通知</option>
                    </select>
                </li>
            </ul>
            <div style="clear: both">
                <ul>
                    <li >
                        <c:if test="${disabled!='disabled'}">
                            <span>选择设备：</span>
                        </c:if>
                        <c:if test="${disabled=='disabled'}">
                            <span>查看设备：</span>
                        </c:if>
                        <span id="checkedEquipment"></span>
                        <button id="selectButton" class="roll-add" style="height: 30px;padding: 0 15px;margin-right: 4px;border:
                        1px solid #54ab37; background: #54ab37; color: #fff;border-radius: 2px;"
                                <c:if test="${disabled=='disabled'}">
                                    onclick="openDialogView('查看','${ctx}/classcard/chooseclasscard?disabled=disabled&checkedIds=${checkedIds}','800px','500px');">查看
                                </c:if>
                                <c:if test="${disabled!='disabled'}">
                                    onclick="openDialog('选择','${ctx}/classcard/chooseclasscard','800px','500px');" >选择
                                </c:if>
                        </button>
                        <input type="hidden" value="" id="checkedIds">
                        <input type="hidden" value="" id="unCheckedIds">
                    </li>
                </ul>
            </div>
        </div>
        <div class="stuMsg">
            <ul class="stuMsgg" style="margin-right: 0;">
                <li style="margin: 0;">
                    <span>内容：</span>
                    <textarea ${disabled} placeholder="请输入内容" id="content" name="content" row="10" cols="100">${classCardNotify.content}</textarea>
                    <%--<textarea ${disabled}  rows="10" cols="100" placeholder="请输入内容" id="content" name="content" value="">${classCardNotify.content}</textarea>--%>
                </li>
            </ul>
        </div>

    </div>


<script>
    $('.layui-layer').css('minWidth', '825px');


    var editor;
    KindEditor.ready(function (K) {

        editor = K.create('textarea', {
            cssPath: '${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css',
            uploadJson: '${ctxStaticNew}/kindeditor-4.1.10/jsp/upload_json.jsp',
            allowFileManager: true,
            urlType : 'domain',
            height : "400px",
            resizeType : 1,
            items:[ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
                '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', '|', 'formatblock', 'fontsize','/',
                'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage', 'table', 'hr', 'emoticons','|', 'about'],
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
//    var ue = UE.getEditor('content');

   /* $("input[name='editUrls']").val().change(function () {
        var ueditorPics= $("input[name='editUrls']").val();
        window.parent.setUeditorPics(ueditorPics);
    })*/
    //回显选中的班牌
    function chooseResult(checkedIds, unCheckedIds,checkedName) {
        $("#checkedEquipment").empty();
        var names = "";
        var checkedName = checkedName.split(",");
        for (var i = 0; i < checkedName.length; i++) {
//            if (i<5&&checkedName[i].trim() != "") {
               // names += "<span class=''>" + checkedName[i] + "</span>";
                names+="<i>"+checkedName[i]+"</i>";
//            }
//            if(i==6){
//                names+="...";
//            }
        }
        $('#checkedIds').val(checkedIds);
        $('#unCheckedIds').val(unCheckedIds);
        $("#checkedEquipment").append(names);
        $("#selectButton").attr("onclick","openDialog('选择','${ctx}/classcard/chooseclasscard?checkedIds="+checkedIds+"','800px','500px')");
    }

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var title=$('#title').val();
        var type=$('#notifyType').val();
//            var content =UE.getEditor('content').getContent();
        var content = editor.html();
        var equipment=$('#checkedEquipment i').size();

        if (title == '') {
            layer.msg("标题不能为空！");
            return false;
        }

        if (content == '') {
            layer.msg("内容不能为空！");
            return false;
        }

        if (equipment==0) {
            layer.msg("请选择班牌");
            return false;
        }

        var url="${ctx}/classcard/notify/save"
        $.post(url, {
            title:title,
            type:type,
            content:content,
            checkedIds:$('#checkedIds').val(),
            unCheckedIds:$('#unCheckedIds').val()
        }, function (retJson) {
            if (retJson.code == '0') {
                window.parent.location.reload(true);
            } else {
                layer.msg(retJson.msg);
            }
        });
        return true;
    }
</script>
</body>
</html>