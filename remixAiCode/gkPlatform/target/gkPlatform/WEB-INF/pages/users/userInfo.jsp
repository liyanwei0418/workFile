<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ include file="../common/headerXf.jsp"%>
<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>首页</title>

    <script src="${ctxStaticNew}/upload/h5upload.js"></script>
    <link rel="stylesheet" href="${ctxStaticNew}/css/accountSetting.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/upload/h5upload.css"/>

    <style>
        body{background:#f2f2f2 !important;}
        .container{
            /*overflow: hidden;*/
            font-size: 14px;
            /*width: 700px;*/
            margin: 0 auto;
        }

        .container > ul {
            float: left;
            list-style: none;
        }

        .container > ul li {
            line-height: 40px;
        }

        .container > ul li span {
            color: #999;
            display: inline-block;
            width: 80px;
            text-align: right;
            margin-right: 30px;
        }

        .container > div {
            float: right;
            /*padding-top: 30px;*/
        }

        .container > div > span {
            /*margin-right: 30px;*/
            vertical-align: 450%;
        }

        .container > div p button {
            border: 1px solid #aaa;
            background: #fff;
            width: 92px;
            height: 28px;
            border-radius: 6px;
            margin-left: 65px;
        }
        .img-list-icon li{
            width: 100px !important;
        }
        .removeBtn{
            display: none !important;
        }
    </style>

    <script>
        function rmPic(obj) {
            if (confirm("确定要删除这张图片吗?")) {
//                $(obj).parent("li").remove();
                $(obj).parent("li").children().remove();
                document.getElementById("headUrl").value="";
            }
        }

        $(function () {
            $("#submit-btn").click(function (event) {

                $.post("${ctx}/user/updateInfo", {
                    id: $("input[name='id']").val(),
                    headUrl: $("input[name='headUrl']").val(),
                    phone: $("input[name='phone']").val(),
                }, function (retJson) {
                    if (retJson.code == '0') {
                        layer.msg('修改信息成功！',{time:1000});
                    } else {
                        layer.msg(retJson.msg,{time:1000});
                    }
                });

            });

            $("#savePassword").click(function (event) {
                var oriPwd = $("input[name='oriPwd']").val();
                var newPwd = $("input[name='newPwd']").val();
                var rePwd = $("input[name='rePwd']").val();

                if (oriPwd == '' || oriPwd.trim().length == 0) {
                    layer.msg("原密码不能为空！",{time:1000});
                    return false;
                }

                var regex = /^(\w){5,50}$/;
                if (!regex.test(newPwd)) {
                     layer.msg("请输入四位以上无特殊符号密码");
                    return false;
                }

                if (newPwd == '' || newPwd.trim().length == 0) {
                    layer.msg("密码不能为空！",{time:1000});
                    return false;
                }

                if (newPwd != rePwd) {
                    layer.msg("两次密码不相等！",{time:1000});
                    return false;
                }
                $.post("${ctx}/user/updatePwd", {
                    oriPwd: oriPwd,
                    newPwd: newPwd,
                    rePwd: rePwd,
                }, function (retJson) {
                    if (retJson.code == '0') {
                        layer.msg("密码修改成功！", {
                        }, function(){
                            window.location.href = "${ctx}/doLogout";
                        });
                    } else {
                        layer.msg(retJson.msg,{time:1000});
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
                    li.innerHTML = '<div class="removeBtn" onclick="rmPic(this)">—</div>' +
//                            '<div class="progress"><span></span></div>' +
                            '<div class="size">' + size + '</div>';
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
                        $("#headUrl").val(imageData.data.imgRequestUrl);
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
<main class="main">
    <div id="tabs">
        <ul class="account-tabs">
            <li>
                <a href="#" class="active" data="base-information">基础资料</a>
            </li>
            <li>
                <a href="#" data="change-pwd">修改密码</a>
            </li>
        </ul>
    </div>
    <div id="base-information">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="headUrl" id="headUrl" value="${headUrl}">
        <aside>
            <c:if test="${type != '家长'}">
            <div>
                <span>真实姓名</span>
                <span>${name}</span>
            </div>
            </c:if>
            <div>
                <span>用户名</span>
                <span>${account}</span>
            </div>
            <div>
                <span>角色</span>
                <span>${type}</span>
            </div>
            <div>
                <span>所属学校</span>
                <span>${school.name}</span>
            </div>

            <div>
                <span>手机号码</span>
                <span>
                        <input type="text" name="phone" value="${phone}"/>
                </span>
            </div>
        </aside>
        <section>
            <p>头像</p>
            <div>
                <input type="file" id="file-input" style="display: none" accept="image/*">
                <ul class="img-list-icon">
                    <li class="user-head">
                        <style>
                            .user-head{width:100px !important;height:100px;}
                        </style>
                        <c:if test="${gukeer:emptyString(headUrl)}">
                            <img src="${ctx}/file/pic/show?picPath=${defaultHead}" data-url="${student.xszp}"width="100%" height="100%" id="head_url">
                        </c:if>
                        <c:if test="${gukeer:notEmptyString(headUrl)}">
                            <div class="removeBtn" onclick="rmPic(this)">—</div>
                            <img src="${ctx}/file/pic/show?picPath=${headUrl}" data-url="${headUrl}"
                                 width="100%" height="100%" id="head_url">
                        </c:if>
                    </li>
                </ul>
            </div>
            <button id="iconUpload" class="uploadBtn-a" style="margin-left:0;">上传头像</button>
        </section>
        <footer
        <c:if test="${type == '家长'}">
                style="margin-left: -158px;"
        </c:if>
        >
            <button id="submit-btn">保存</button>
            <button style="margin-left:30px;background: #B0BDAD;border:1px solid #B0BDAD;">取消</button>
        </footer>
    </div>


    <div id="change-pwd">
        <p>注：修改完密码后需要重新登录</p>
        <div>
            <span>当前密码</span>
            <span>
                    <input type="password" name="oriPwd"/>
                </span>
           <%-- <span>当前密码强度符合要求</span>--%>
        </div>
        <div>
            <span>新密码</span>
            <span>
                    <input type="password" name="newPwd"/>
                </span>
            <span>请输入四位以上不包含特殊字符</span>
        </div>
        <div>
            <span>新密码确认</span>
            <span>
                    <input type="password" name="rePwd"/>
                </span>
            <span>请输入确认密码</span>
        </div>
        <footer>
            <button id="savePassword">保存</button>
            <button style="margin-left:30px;background: #B0BDAD;border:1px solid #B0BDAD;">取消</button>
        </footer>
    </div>
</main>
</body>
</html>