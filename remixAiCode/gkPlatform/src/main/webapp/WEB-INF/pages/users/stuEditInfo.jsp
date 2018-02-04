<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/bootstrap.min.css"/>
    <!-- WebUploader CSS -->
    <link rel="stylesheet" href="${ctxStaticNew}/upload/h5upload.css"/>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script  src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script src="${ctxStaticNew}/js/openDialog.js"></script>
    <!-- h5upload -->
    <script src="${ctxStaticNew}/upload/h5upload.js"></script>

    <style>
        .container{overflow:hidden;font-size:14px;width:700px;margin:0 auto;}
        .container>ul{float:left;list-style:none;}
        .container>ul li{line-height:40px;}
        .container>ul li span{color:#999;display:inline-block;width:80px;text-align:right;margin-right:30px;}
        .container>div{float:right;padding-top:30px;}
        .container>div>span{margin-right:30px;vertical-align:450%;}
        .container>div p button{border:1px solid #aaa;background:#fff;width:92px;height:28px;border-radius:6px;margin-left:65px;}
    </style>
    <script>
        function rmPic(obj) {
            if (confirm("确定要删除这张图片吗?")) {
                $(obj).parent("li").remove();
            }
        }

        $(function() {
            $("#submit-btn").click(function(event){

                $.post($("form").attr('action'),{
                    id:$("input[name='id']").val(),
                    headUrl:$("input[name='xszp']").val(),
                    phone:$("input[name='phone']").val(),
                },function(retJson){
                    if (retJson.code == '0') {
                        alert('修改信息成功！');
                    } else {
                        alert(retJson.msg);
                    }
                });

            });
        });
    </script>
    <script>
        $(document).ready(function () {
            var iconChoose = document.getElementById("file-input");
            $("#iconUpload").on("click", function () {
                iconChoose.click();
            }).on("touchstart", function () {
                $(this).addClass("touch")
            }).on("touchend", function () {
                $(this).removeClass("touch")
            });
            iconChoose.onchange = function () {
                if (!this.files.length) return;
                var files = Array.prototype.slice.call(this.files);
                if (files.length > 9) {
                    alert("最多同时只可上传9张图片");
                    return;
                }
                files.forEach(function (file, i) {
                    if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;
                    var reader = new FileReader();
                    var li = document.createElement("li");
                    //          获取图片大小
                    var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
                    li.innerHTML = '<div class="removeBtn" onclick="rmPic(this)">—</div><div class="progress"><span></span></div><div class="size">' + size + '</div>';
                    $(".img-list-icon").children().remove();
                    $(".img-list-icon").append($(li));
                    reader.onload = function () {
                        var result = this.result;
                        var img = document.createElement("img");
                        $(img).attr("width", "100%");
                        $(img).attr("height", "100%");
                        $(img).attr("data-url", "");
                        $(img).attr("class", "pictures");
                        img.src = result;
                        $(li).append(img);
                        //如果图片大小小于100kb，则直接上传
                        if (result.length <= maxsize) {
                            img = null;
                            uploads(result, file.type, $(li), file.name);
                            return;
                        }
                        //      图片加载完毕之后进行压缩，然后上传
                        if (img.complete) {
                            callback();
                        } else {
                            img.onload = callback;
                        }
                        function callback() {
                            var data = compress(img);
                            uploads(data, file.type, $(li), file.name);
                            img = null;
                        }
                    };
                    reader.readAsDataURL(file);
                })
            };

            function uploads(basestr, type, $li, name) {
                var text = window.atob(basestr.split(",")[1]);
                var buffer = new Uint8Array(text.length);
                var pecent = 0, loop = null;
                for (var i = 0; i < text.length; i++) {
                    buffer[i] = text.charCodeAt(i);
                }
                var blob = getBlob([buffer], type);
                var xhr = new XMLHttpRequest();
                var formdata = getFormData();
                formdata.append('file', blob);
                formdata.append("imgName", name);
                formdata.append("imgPath", '<%=FileUtils.USER_HEADERS_PATH%>');
                xhr.open('post', '${ctx}/file/upload');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var imageData = JSON.parse(xhr.responseText);
                        var text = imageData.data.imgRequestUrl ? '上传成功' : '上传失败';
                        clearInterval(loop);
                        //当收到该消息时上传完毕
                        $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function () {
                            $(this).html(text);
                        });
                        $("#xszp").val(imageData.data.imgRequestUrl);
                        $("#head_url").attr("data-url", imageData.data.imgRequestUrl);
                    }
                };
                //数据发送进度，前50%展示该进度
                xhr.upload.addEventListener('progress', function (e) {
                    if (loop) return;
                    mockProgress();
                }, false);
                //数据后50%用模拟进度
                function mockProgress() {
                    if (loop) return;
                    loop = setInterval(function () {
                        pecent++;
                        $li.find(".progress span").css('width', pecent + "%");
                        if (pecent == 100) {
                            clearInterval(loop);
                        }
                    }, 50)
                }

                xhr.send(formdata);
            }
        });
    </script>
</head>
<body>

<form action="${ctx}/user/updateInfo">
    <input type="hidden" name="id" value="${student.id}">
    <input type="hidden" name="xszp" id="xszp" value="${student.xszp}">
    <div class="container">
        <ul>
            <li>
                <span>真实姓名</span>
                <label>${student.name}</label>
            </li>
            <li>
                <span>用户名</span>
                <label>${student.studentAccount}</label>
            </li>
            <li>
                <span>角色</span>
                <label>学生</label>
            </li>
            <li>
                <span>所属学校</span>
                <label>${school.name}</label>
            </li>
            <li>
                <span>联系电话</span>
                <input type="text" name="phone" value="${student.phone}">
            </li>
        </ul>
        <div>
            <span>头像</span>
            <input type="file" id="file-input" style="display: none" accept="image/*">
            <ul class="img-list-icon">
                <li>
                    <div class="removeBtn" onclick="rmPic(this)">—</div>
                    <img src="${ctx}/file/pic/show?picPath=${student.xszp}" data-url="${student.xszp}" width="100%" height="100%" id="head_url">
                </li>
            </ul>
            <p>
                <a id="iconUpload" class="uploadBtn-a">上传图片</a>
            </p>

        </div>
    </div>
    <div align="center">
        <input type="button" value="保存" id="submit-btn">
    </div>
</form>
</body>
</html>