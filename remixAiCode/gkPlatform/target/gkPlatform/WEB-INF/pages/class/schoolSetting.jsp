<%@ include file="../common/headerXf.jsp" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学校设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctxStaticNew}/upload/h5upload.js"></script>
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/upload/h5upload.css"/>

</head>
<style>
    td input{
        width: 90%;
        padding-left: 3px;
        border-radius: 2px;
    }
</style>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<!--学校设置-->
<div class="row" id="sch-setting">
    <main class="container">
        <div class="sch-setting-menu">
        <ul>
            <li><a href="#" data="sch-msg-setting" id="sMsg">信息设置</a></li>
            <li><a href="#" data="sch-block-setting" id="sType">校区设置</a></li>
            <li><a href="#" data="sch-display-setting" id="sDisplay">显示设置</a></li>
        </ul>
    </div>
        <section id="sch-msg-setting" class="row" style="display: none">
            <input type="hidden" name="id" value="${school.id}">
            <div class="col-sm-6">
                <div class="row">
                    <span>校名：</span>
                    <input type="text" name="name" value="${school.name}"/>
                </div>
                <div class="row">
                    <span>英文名：</span>
                    <input type="text" name="ename" value="${school.ename}"/>
                </div>
              <%--  <div class="row">
                    <span>网址：</span>
                    <input type="text" name="deployUrl" value="${school.deployUrl}"/>
                </div>--%>
                <div class="row">
                    <span>校长：</span>
                    <input type="text" name="master" value="${school.master}"/>
                </div>
                <div class="row">
                    <span>地址：</span>
                    <input type="text" name="address" value="${school.address}"/>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="row">
                    <span>传真：</span>
                    <input type="text" name="fax" value="${school.fax}"/>
                </div>
                <div class="row">
                    <span>邮箱：</span>
                    <input type="text" name="email" value="${school.email}"/>
                </div>
                <div class="row">
                    <span>邮编：</span>
                    <input type="text" name="zipCode" value="${school.zipCode}"/>
                </div>
                <div class="row">
                    <span>电话：</span>
                    <input type="text" name="phone" value="${school.phone}"/>
                </div>
            </div>
            <div class="btns col-sm-12">
                <button onclick="saveInfo()">保存</button>
            </div>
        </section>
        <section id="sch-block-setting" class="row" style="display: none">
            <div class="row">
                <button onclick="addOne()">新增</button>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="35%">校区名称</th>
                        <th width="30%">校区顺序</th>
                        <th width="30%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schoolType}" var="st" varStatus="varstatus">
                        <tr>
                            <td>${varstatus.index + 1}
                            </td>
                            <td>
                                    ${st.name}
                            </td>
                            <td>
                                    ${st.sort}
                            </td>
                            <td>
                                <c:if test="${st.name != '主校区'}"><span style="display: inline-block;"
                                                                       onclick="editType(this,'${st.name}','${st.sort}','${st.id}')">编辑</span><span
                                        class="del1" onclick="alertTips(400, 200, '删除校区', '确定要删除该校区吗？删除前请确定该校区下无班级', 'schoolTypeDelete(\'${st.id}\')')">删除</span></c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <button onclick="getArrayToPost()">保存</button>
            </div>
        </section>
        <section id="sch-display-setting" class="row" style="display: none">
            <input type="hidden" name="id" id="slId" value="${school.id}">

            <div class="col-sm-6">
                <span style="
                /*line-height: 78px;*/
               margin-top: 30px;color:red;
               ">重新登陆后即可查看页面logo和背景图的样式</span>
                <p>登录页logo设置</p>
                <div class="img" style="width:302px;height:100px;">
                    <style>
                        .img img {
                            max-width: 100% !important;
                        }
                    </style>
                    <ul class="img-list-icon1">
                        <li style="position:relative;">
                                <%--<div class="removeBtn" style="padding:0;color:#fff;"--%>
                                     <%--onclick="rmPic(this)">--%>
                                    <%--—--%>
                                <%--</div>--%>
                            <c:if test="${gukeer:emptyString(school.logo)}">
                                <img style="max-width:100%;" src="${ctxStaticNew}/images/logo-logo.png"
                                     <%--data-url="${school.logo}"--%>

                                     height="100%" id="head_url">
                            </c:if>
                            <c:if test="${gukeer:notEmptyString(school.logo)}">
                            <img style="max-width:100%;" src="${ctx}/file/pic/show?picPath=${school.logo}"
                                 data-url="${school.logo}"

                                 height="100%" id="head_url">
                            </c:if>
                        </li>
                    </ul>
                    <%--<img src="${ctxStaticNew}/images/uploading-icon.png" alt=""/>--%>
                </div>
                <div>
                    <input type="file" id="file-input" style="display: none" accept="image/*">
                    <input type="hidden" name="headUrl" id="headUrl" value="${school.logo}">
                    <p style="display: inline-block;">
                        <button id="iconUpload" class="uploadBtn-a" style="margin-left:0;margin-top:42px;">上传图片</button>
                    </p>
                </div>
                <span>logo支持png格式，高度不小于45px</span>
            </div>
            <div class="col-sm-6" style="margin-top:50px;">
                <p>登录页背景图</p>
                <div class="img" style="width:200px;height:100px;">
                    <ul class="img-list-icon" style="float: right">
                        <li style="position:relative; border:none">
                                <%--<div class="removeBtn" style="padding:0;color:#fff;"--%>
                                     <%--onclick="rmPic2(this)">--%>
                                    <%--—--%>
                                <%--</div>--%>
                            <c:if test="${gukeer:notEmptyString(school.bgPicture)}">
                            <img src="${ctx}/file/pic/show?picPath=${school.bgPicture}"
                                 data-url="${school.bgPicture}"
                                 width="100%" height="100%" id="head_url2">
                            </c:if>
                            <c:if test="${gukeer:emptyString(school.bgPicture)}">
                                <img src="${ctxStaticNew}/images/background.png"
                                     <%--data-url="${school.bgPicture}"--%>
                                     width="100%" height="100%" id="head_url2">
                            </c:if>
                        </li>
                    </ul>
                    <%--<img src="${ctxStaticNew}/images/uploading-icon.png" alt=""/>--%>
                </div>
                <div>
                    <input type="file" id="file-input2" style="display: none" accept="image/*">
                    <input type="hidden" name="headUrl2" id="headUrl2" value="${school.bgPicture}">
                    <p style="display: inline-block">
                        <button id="iconUpload2" class="uploadBtn-a" style="margin-left:0">上传图片</button>
                    </p>
                </div>
                <span>背景图支持png、jpg格式，长宽尺寸建议2:1,图片宽度建议不小于1920px</span>


            </div>
            <p style="overflow:hidden;" class="col-sm-12">

                <button style="height:30px;padding:0 15px;color:#fff;border:1px solid #54AB37;background: #54AB37;border-radius: 2px;float:right;margin-top:30px;"
                        onclick="saveDisplay()">保存
                </button>
            </p>

            <div style="visibility:hidden;opacity:0" id="aaa"></div>
        </section>

    </main>
</div>

<script>

    activeMenu("schoolSetMenu",0);
    $(function () {
        if (${which == 2}) {
            $('.sch-setting-menu a').removeClass('active');
            $("#sType").addClass('active');
            var data = $("#sType").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }
        else if (${which == 3}) {
            $('.sch-setting-menu a').removeClass('active');
            $("#sDisplay").addClass('active');
            var data = $("#sDisplay").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }
        else {
            $('.sch-setting-menu a').removeClass('active');
            $("#sMsg").addClass('active');
            var data = $("#sMsg").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }
    });


    function saveInfo() {
        var name = $("input[name='name']").val();
        if (name == '' || name.trim().length == 0) {
            layer.msg("请输入学校名称");
            return false;
        }
        $.post("${ctx}/class/school/save", {
            id: $("input[name='id']").val(),
            name: $("input[name='name']").val(),
            fax: $("input[name='fax']").val(),
            ename: $("input[name='ename']").val(),
            email: $("input[name='email']").val(),
           /* deployUrl: $("input[name='deployUrl']").val(),*/
            zipCode: $("input[name='zipCode']").val(),
            master: $("input[name='master']").val(),
            zipCode: $("input[name='zipCode']").val(),
            phone: $("input[name='phone']").val(),
            address: $("input[name='address']").val(),
        }, function (retJson) {
            if (retJson.code == '0') {
                layer.msg("保存成功");
            } else {
                layer.msg(retJson.msg);
            }
            setTimeout(function () {
                window.location.reload();
            }, 1000);
        }, "json");
    }


    function addOne() {
        var lastIndex = $("tbody tr:last-child td:nth-child(1)").html();
        if(lastIndex==undefined) lastIndex=0;
        var lastNum = parseInt(lastIndex) + 1;
        $('table tbody').append('<tr><td>' + lastNum + '</td><td><input name="stname" class="schoolTypeName" type="text" placeholder="校区名称" /> </td><td><input name="stsort" class="schoolTypeSort" type="text" placeholder="校区顺序" /></td><td><span class="del">删除</span></td></tr>');

        $('.del').click(function () {
            $(this).parent().parent().remove();
        });
    }
//    $('.del1').click(function () {
//        var tid = $(this).parents("tr").children().find(".schoolTypeId").val();
//        if (tid != '' || id.trim().length != 0) {
//            alertTips("400px", "200px", "删除校区", "确定要删除该校区吗？删除前请确定该校区下无班级", "schoolTypeDelete(" + tid + ")")
//        }
//        else {
//            $(this).parent().parent().remove();
//        }
//    });


    function schoolTypeDelete(id) {
        closeAlertDiv();

        $.post("${ctx}/class/schoolType/delete", {
            id: id,
            which:2
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg(retJson.msg);
            setTimeout(function () {
                window.location.reload();
            }, 1000);
        }, "json");
    }


    function getArrayToPost() {
        var stid = document.getElementsByClassName("schoolTypeId");
        var name = document.getElementsByClassName("schoolTypeName");
        var sort = document.getElementsByClassName("schoolTypeSort");
        var nameStr = "";
        var sortStr = "";

        var idStr = "";
        if (name.length == 0) {
            layer.msg("无可保存信息");
            return false;
        }
        for (var i = 0; i < name.length; i++) {
          /*  if ($(name[i]).parents("tr").children("td").eq(0).text().trim()!= $(sort[i]).val()){
                layer.msg("校区顺序有误！");
                return false;
            }
*/
            if ($(name[i]).val() == '' || $(name[i]).val().trim().length == 0) {
                layer.msg("校区名称不能为空！");
                return false;
            }
            if ($(sort[i]).val() == '' || $(sort[i]).val().trim().length == 0) {
                layer.msg("校区顺序不能为空！");
                return false;
            }
           /* if ($(sort[i]).val() < 2) {
                layer.msg("校区顺序不能重复！");
                return false;
            }*/
            if ($(name[i]).val() == '主校区') {
                layer.msg("校区名称不能重复");
                return false;
            }
            for (var j = 0; j < i; j++) {
               /* if ($(sort[j]).val() == $(sort[i]).val()) {
                    layer.msg("校区顺序不能重复");
                    return false;
                }*/
                if ($(name[i]).val() == $(name[j]).val()) {
                    layer.msg("校区名称不能重复");
                    return false;
                }
            }
            nameStr += $(name[i]).val() + ",";
            sortStr += $(sort[i]).val() + ",";
            if (stid[i] != null) {
                idStr += $(stid[i]).val() + ",";
            }
        }
        $.post("${ctx}/class/schoolType/save", {
            stname: nameStr,
            stsort: sortStr,
            id: idStr,
        }, function (retJson) {
            if (retJson.code == '0') {
                layer.msg("保存成功");
                setTimeout(function () {
                    <%--window.location.replace("${ctx}/class/schoolsetting/index");--%>
//                    window.location.reload();
                    window.location.replace("${ctx}/class/schoolsetting/index?which=2");
                }, 500)
            } else {
                layer.msg(retJson.msg);
            }
        });
    }

    function saveDisplay() {
        if (${gukeer:notEmptyString(school.id)}) {
            $.post("${ctx}/class/school/displaySetting", {
                id: $("input[id='slId']").val(),
                headUrl: $("input[name='headUrl']").val(),
                headUrl2: $("input[name='headUrl2']").val(),
//                homeText: $("input[name='homeText']").val(),
//                bottomText: $("textarea[name='bottomText']").val(),
            }, function (retJson) {
                if (retJson.code == '0') {
                    layer.msg('保存成功');
                    setTimeout(function () {
                        <%--window.location.replace("${ctx}/class/schoolsetting/index");--%>
//                        window.location.reload();
                        window.location.replace("${ctx}/class/schoolsetting/index?which=3");
                    }, 500)
                } else {
                    layer.msg("显示设置错误");
                }
            });
        }
        else {
            layer.msg("请先保存学校基本信息设置");
        }
    }


    function rmPic(obj) {
        if (confirm("确定要删除这张图片吗?")) {
            $(obj).parent("li").children().remove();

            document.getElementById("headUrl").value = "";
        }
    }
    function rmPic2(obj) {
        if (confirm("确定要删除这张图片吗?")) {
            $(obj).parent("li").remove();
            document.getElementById("headUrl2").value = "";
        }
    }

    //上传，显示
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
            if (files.length > 1) {
                alert("最多同时只可上传9张图片");
                return;
            }
            files.forEach(function (file, i) {
                testWidthHeight(file, 400, 40);
            })

        };


    });
    //end 上传，显示

    //图片上传和截取--------------------------------------begin------------------------------------
    /**
     * 从 file 域获取 本地图片 url
     */
    function getFileUrl(sourceId) {
        var url;
        if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE
            url = document.getElementById(sourceId).value;
        }
        else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        return url;
    }

    /**
     * 将本地图片 显示到浏览器上
     */
    function preImg(sourceId, targetId) {
        var url = getFileUrl(sourceId);
        document.getElementById(targetId).src = url;
    }


    //上传，显示
    $(document).ready(function () {
        var iconChoose = document.getElementById("file-input2");
        $("#iconUpload2").on("click", function () {
            iconChoose.click();
        }).on("touchstart", function () {
            $(this).addClass("touch")
        }).on("touchend", function () {
            $(this).removeClass("touch")
        });
        iconChoose.onchange = function () {
            if (!this.files.length) return;
            var files = Array.prototype.slice.call(this.files);
            if (files.length > 1) {
                alert("最多同时只可上传9张图片");
                return;
            }
            files.forEach(function (file, i) {
                testWidthHeight2(file, 0, 451);
            });
        };


    });
    function test222(file) {
        if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;
        var reader = new FileReader();
        var li = document.createElement("li");
//          获取图片大小
        var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
        li.innerHTML =
//                '<div class="removeBtn" style="padding:0;color:#fff;" onclick="rmPic2(this)">—</div>' +
                '<div class="progress"><span></span></div><div class="size" style="height:5px">' + size + '</div>';
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
//                var data = compress(img);
//                uploads(data, file.type, $(li), file.name);
                uploads(result, file.type, $(li), file.name);           //背景图片不压缩~！
                img = null;
            }
        };
        reader.readAsDataURL(file);
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
            formdata.append("imgPath", '<%=FileUtils.SCHOOL_BGPIC_PATH%>');
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
                    $("#headUrl2").val(imageData.data.imgRequestUrl);
                    $("#head_url2").attr("data-url", imageData.data.imgRequestUrl);
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
    }
    function test111(file) {
        if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;

        var reader = new FileReader();
        var li = document.createElement("li");
//          获取图片大小
        var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
        li.style.position = 'relative';
        li.style.width = '100px';
        li.innerHTML =
//                '<div class="removeBtn" style="padding:0;color:#fff;" onclick="rmPic(this)">—</div>' +
                '<div class="progress" style="position:absolute;width:100px;height:auto;left:0;"><span></span></div><div class="size" style="height:10px">' + size + '</div>';
//                li.innerHTML = '<div class="removeBtn" style="padding:0;color:#fff;" onclick="rmPic(this)">—</div><div class="progress"><span></span></div><div class="size">' + size + '</div>';


        $(".img-list-icon1").children().remove();
        $(".img-list-icon1").append($(li));
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
            formdata.append("imgPath", '<%=FileUtils.SCHOOL_LOGO_PATH%>');
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
    }

    function testWidthHeight(file, Max_Width, Max_Height) {
        var img = new Image();
        var isAllow = false;
        var width = 0;
        var height = 0;
        img.src = window.URL
                .createObjectURL(file);

//因为这里只有把图片添加到页面后才可获取其高和宽
        document.getElementById("aaa").appendChild(img);
        img.onload = function () {
            width = img.offsetWidth;
            height = img.offsetHeight;

            if (img.complete) {
//                if (Max_Width == 0) {
//                    isAllow = height >= Max_Height;
//                }
//                else {
//                    isAllow = width >= Max_Width && height >= Max_Height;
//                }
                if (!isAllow) {
                    test111(file);
                }
                else layer.msg("图片尺寸不符合规格");
            }
        };
    }

    function testWidthHeight2(file, Max_Width, Max_Height) {
        var img = new Image();
        var isAllow = false;
        var width = 0;
        var height = 0;
        img.src = window.URL
                .createObjectURL(file);

//因为这里只有把图片添加到页面后才可获取其高和宽
        document.getElementById("aaa").appendChild(img);
        img.onload = function () {
            width = img.offsetWidth;
            height = img.offsetHeight;

            if (img.complete) {
//                if (Max_Width == 0) {
//                    isAllow = height >= Max_Height;
//                }
//                else {
//                    isAllow = width >= Max_Width && height >= Max_Height;
//                }
                if (!isAllow) {
                    test222(file);
                }
                else layer.msg("图片尺寸不符合规格");
            }
        };
    }


    function editType(bq, name, sort,id) {
    <%--<input type="hidden" class="schoolTypeId"--%>
        <%--value="${st.id}">--%>
        var idHtml = '<input type="hidden" class="schoolTypeId" value="'+id+'">';
        var nameHtml = "<input type='text' class='schoolTypeName' value='"+name+"'>";
        var sortHtml = "<input type='text' class='schoolTypeSort' value='"+sort+"'>"
        $(bq).parent().prev().html("");
        $(bq).parent().prev().prev().html("");
        $(bq).parent().prev().append(sortHtml);
        $(bq).parent().prev().prev().append(nameHtml);
        $(bq).parent().prev().prev().append(idHtml);
    }
</script>
</body>
</html>