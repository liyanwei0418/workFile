<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>通知公告发布页面</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/oldCss.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/validate.css"/>

    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <script src="${ctxStaticNew}/js/laydate.js"></script>

    <%--<script src="${ctxStaticNew}/tinymce/tinymce.min.js"></script>--%>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/themes/default/default.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/kindeditor.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.js"></script>
    <style>
        .receive {
            border: 1px #1AB394 solid;
            margin-right: 10px;
            padding: 3px 8px;
        }

        #chooseWhoTell > span {
            display: inline-flex;
            margin-top: 12px;
        }

        .container {
            padding-top: 15px;
            width:850px;
        }

        .row {
            margin-bottom: 35px;
            overflow: hidden;
        }

        .row > p {
            font-size: 13px;
            color: #777;
            float: left;
            width: 75px;
        }

        .row > p > span {
            position: relative;
            line-height: 30px;
        }

        .row > p > span:after {
            content: '*';
            color: #f00;
            font-size: 18px;
            position: absolute;
            top: -6px;
            right: -6px;
        }

        .row > input[type=text], .row > select {
            width: 245px;
            height: 30px;
            padding-left: 5px;
            border-radius: 3px;
            width: 190px;
            border: 1px solid #a9a9a9;
        }

        .row > button {
            border: 1px solid #00B7EE;
            background: #00B7EE;
            color: #fff;
            border-radius: 2px;
            height: 28px;
            padding: 0 23px;
        }

        .row > i {
            font-size: 13px;
            color: #999;
            font-style: normal;
            margin-left: 15px;
        }

        #mceu_13 {
            margin-top: 33px;
        }

        input[type=button] {
            width: 55px;
            height: 30px;
            border: 1px solid #54AB37;
            background: #54ba37;
            color: #fff;
            border-radius: 3px;
            margin-top: 0px;
        }
        .ke-container.ke-container-default{width:704px !important;}
        .layui-layer.layui-layer-iframe.layer-anim {
            top: 6% !important;
        }
    </style>
</head>
<form action="${ctx}/notify/save" id="inputForm" method="post">
    <input type="hidden" value="<c:if test="${gukeer:notEmptyString(notify.id)}">
								${notify.id}
							</c:if>" id="notifyId"/>
    <div class="container">
        <div class="row">
            <p><span>标题:</span></p>
            <%--<input type="text" class="editInputP" data-rule-notNull="true" required data-msg-notNull="不能为空" name="title" value="${notify.title}"/>--%>
            <input type="text" class="editInputP" name="title" value="${notify.title}"/>
            <div class="row" style="margin-right:28px;margin-bottom:0;float:right;width:310px">
                <p><span>栏目:</span></p>
                <select class=" editSelectP" name="lanmu">
                    <option value="0">请选择栏目</option>
                    <c:forEach items="${notifyColumnList}" var="column">
                        <option value="${column.id}"
                                <c:if test="${columId==column.id}">selected</c:if> >${column.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <p><span>接收人:</span></p>
            <style>#chooseWhoTell > span {
                margin-top: 0;
                border: 1px solid #54AB37;
            }</style>

           <%--case1--%>
          <%--  <span id="chooseWhoTell" style="position:relative;top:5px;cursor: pointer"
                  onclick="openDialog('选择通知部门','${ctx}/notify/choosefz','500px','400px');">
                <input type="button" value="添加"/></span>
--%>
            <%--case2--%>
            <span id="chooseWhoTell" onclick="openDialog('选择人员','${ctx}/notify/chooseperson/show?chooseIds=','950px','90%');">
                <input type="button" value="添加"/>
            </span>

            <input type="hidden" id="whichDepartMent" name="bumens"/>
        </div>

        <div class="row">
            <p>附件:</p>
            <%--  <button>选择文件</button>--%>
            <input type="file" name="file" id="file" style="display: none">
            <input type="button" value="上传" id="attachButton"/>
            <input type="hidden" name="fileUrl" id="fileUrl" value="${filesString}">
            <span id="fileSpan">
                <c:forEach items="${fileList}" var="file">
                    <span>${gukeer:showFileName(file)}<i onclick='removePar(this,"${file}")'>-</i></span>
                </c:forEach>
            </span>
            <style>
                #fileSpan span {
                    position: relative;
                    padding: 3px 8px;
                    border: 1px solid #54AB37;
                    margin-right: 10px;
                }

                #fileSpan i {
                    position: absolute;
                    width: 11px;
                    cursor: pointer;
                    height: 11px;
                    border-radius: 50%;
                    color: #fff;
                    background: #f00;
                    font-size: 26px;
                    line-height: 8px;
                    top: -3px;
                    text-align: center;
                    right: -5px;
                }
            </style>

        </div>

        <div class="row">
            <p><span>内容:</span></p>
            <textarea data-rule-gt="true" data-msg-gt="不能为空" name="content" row="3">${notify.content}</textarea>
        </div>
        <%--<input type="button" value="预览" onclick="preview()"/>--%>
        <%--<span class="ke-outline" data-name="preview" title="预览" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-preview" unselectable="on"></span></span>--%>
    </div>
</form>


<script type="text/javascript">

    var isSubmit = false;
    var id = 0;
    $(function () {
        var fileChoose = document.getElementById("file");
        $("#attachButton").on("click", function () {
            fileChoose.click();
        });
        fileChoose.onchange = function () {
            fileUpload();
        };

        //$("form").validate();
        //获取公告已经通知的部门
        <c:if test="${gukeer:notEmptyString(departIds)}">
        chooseResult("${departIds}", "${departNames}");
        </c:if>

        id = $("#notifyId").val().trim();


        $("#submit-btn").click(function (event) {
            doSubmit();
        });
    });

    var editor;
    KindEditor.ready(function (K) {

        editor = K.create('textarea', {
                cssPath: '${ctxStaticNew}/kindeditor-4.1.10/plugins/code/prettify.css',
                uploadJson: '${ctxStaticNew}/kindeditor-4.1.10/jsp/upload_json.jsp',
                allowFileManager: true,
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

    function chooseResult(depratmentIds, depratmentNames) {
        var names = "";
        var departNames = depratmentNames.split(",");
        for (var i = 0; i < departNames.length; i++) {
            if (departNames[i].trim() != "") {
                names += "<span class='receive'>" + departNames[i] + "</span>";
            }
        }
//        $("#chooseWhoTell").html(names + " <input type='button' value='更改'>");

          $("#chooseWhoTell").html("已选择"+(departNames.length-1)+"人" + " <input type='button' value='更改'>");

        $("#whichDepartMent").val(depratmentIds);


        //这里选择人员过多，参数太长，使用cookie存储选择的人员列表来传递这个cookie的key

        var key = generateUUID();//生成唯一的uuid
        //setCookie(key,depratmentIds);
        window.localStorage.setItem(key,depratmentIds);

        $("#chooseWhoTell").attr("onclick","openDialog('选择人员','${ctx}/notify/chooseperson/show?chooseIds="+key+"','950px','620px')");

    }

    function reloadPage() {
        window.location.reload();
    }


    function doSubmit() { //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        var whichDepartMent = $("#whichDepartMent").val();
        var lanmu = $("select[name='lanmu']").val();
        var publishTime = $("#dateInput").val();
        var title = $("input[name='title']").val();
        // var content = tinymce.get('content').getContent();
        var content = editor.html();
        var fileUrl = $("input[name='fileUrl']").val();
        if (whichDepartMent == '' || whichDepartMent.trim().length == 0 || lanmu == '0' || lanmu.trim().length == 0 || title == '' || title.trim().length == 0 || content == '' || content.trim().length == 0) {
            layer.msg("必填项不能为空");
            return false;
        } else {
            if (!isSubmit) {
                $.post($("form").attr('action'), {
                    whichDepartMent: whichDepartMent,
                    lanmu: lanmu,
                    publishTime: publishTime,
                    title: title,
                    content: content,
                    id: id,
                    fileUrl: fileUrl
                }, function (retJson) {
                    if (retJson.code == '0') {
                        layer.msg("发布成功");
                        window.parent.location.reload(true);
                    } else {
                        //alert(retJson.msg);
                    }
                });
            }
            isSubmit = true;
            return false;
        }
        return true;
    }

    function formatFileName(str) {
        var arr = str.split("/");
        var fileName = arr[arr.length - 1];
        var oriFileName = fileName.substring(13, fileName.length);
        return oriFileName;
    }

    function fileUpload() {
        var fileObj = document.getElementById("file").files[0]; // 获取文件对象

        if (fileObj.size > 1024 * 1024 * 30) {
            layer.msg("上传的附件大小不能大于30MB")
            return;
        } else {
            layer.msg('上传中', {icon: 16, shade: 0.5, time: 100000000});
        }

        // FormData 对象
        var form = new FormData();
        //form.append("author", "hooyes");
        form.append("file", fileObj);                           // 文件对象
        // XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", "${ctx}/file/file/upload", true);
        xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var fileData = JSON.parse(xhr.responseText);
                var fileUrl = fileData.data ? '上传成功' : '上传失败';
                //当收到该消息时上传完毕
                layer.msg(fileUrl, {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    closeAlertDiv();
                });
                $("#fileUrl").val($("#fileUrl").val() + "," + fileData.data);

                var arr = fileData.data.split("/");
                var fileName = arr[arr.length - 1];
                var oriFileName = fileName.substring(13, fileName.length);
                //13 是timestamp的长度
                $("#fileSpan").append("<span>" + oriFileName + "<i onclick=removePar(this,'" + fileData.data + "')>-</i></span>");
            }
        };
        xhr.send(form);
    }

    function removePar(span, str) {
        $("#fileUrl").val($("#fileUrl").val().replace("," + str, ""));
        $(span).parent().remove();
    }

    function preview() {
        $(".ke-icon-preview").click();
        // var textcontent = tinymce.get('content').getContent();
       /* var textcontent = editor.html();

        layer.open({
            type: 1,
            area: [700, 500],
            title: false,
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            content: textcontent
        });*/

    }

</script>

<body>