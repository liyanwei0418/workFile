<%@ include file="../common/common.jsp" %>
<%@ page import="cn.gukeer.common.utils.FileUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">

    <style>

        span.error {
            color: #C00;
            padding: 0 6px;
            display: inline
        }

        body {
            height: auto !important;
            font-size: 0.75em;
            color: #666;
            font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;
        }

        .container {
            width: 800px;
            padding: 5px 8px;
            margin: 0 auto
        }

        input {
            margin: 0;
            padding: 0;
            border: 1px solid #ddd;
            height: 28px !important;
            padding: 0 3px;
            border-radius: 2px;
            width: 190px
        }

        .container ul {
            list-style: none;
            width: 50%;
            margin: 0;
            padding: 0;
            float: left;
        }

        .container form > ul > li {
            margin: 15px 0;
        }

        .fSpan {
            display: inline-block;
            width: 27%;
            text-align: right;
        }

        .container ul li span b {
            color: #f00;
            margin-right: 8px;
        }

        select {
            width: 190px;
            height: 28px;
            border: 1px solid #ddd;
        }

        .img-list-icon {
            background: url(${ctxStaticNew}/images/default_tou.png) no-repeat 10px 12px !important;
        }

        .laydate-icon {
            border: 1px solid #ddd !important;
        }

        input, select {
            outline: none;
        }

        /*input.laydate-icon{border-color:#999;width:178px;}*/
    </style>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/validate.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/pageDivide.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/fenBan.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/upload/h5upload.css"/>
    <!-- jQuery -->
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/laydate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
    <!-- h5upload -->
    <script src="${ctxStatic}/upload/h5upload.js"></script>

    <title>index</title>
    <script>
        $(function () {
            //jquery.validate
            $("#inputForm").validate();


        })


        function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            var name = $("input[name='name']").val();
            var teaNo = $("input[name='no']").val();
            if (name == '' || name.trim().length == 0 || teaNo == '' || teaNo.trim().length == 0) {
                parent.layer.msg("必填项不能为空");
                return false;
            }
            if ($(".error:visible").length > 0) {
                parent.layer.msg("参数格式错误");
                return false;
            } else {
                $("#inputForm").submit();
                if ($(".error:visible").length > 0) {
                    parent.layer.msg("参数格式错误");
                    return false;
                } else {
                    return true;
                }
            }

        }

        /*pic upload*/
        function rmPic(obj) {
            parent.layer.confirm('确定要删除这张图片吗?', function (index) {
                parent.layer.close(index);
                $(obj).parent("li").remove();
                $("#head_url").src = "";
                $("#headUrl").val("");
            });
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
                formdata.append("imgPath", '<%=FileUtils.TEACHER_HEADERS_PATH%>');
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
        //end 上传，显示
    </script>
</head>
<body>
<div class="container">
    <form method="post" action="${ctx}/renshi/renyuan/save" id="inputForm">
        <input type="hidden" name="id" value="${teacher.id}">
        <ul>
            <li>
                <span class="fSpan">
                    <b>*</b>
                    姓名：
                </span>
                <input ${disabled} type="text" name="name" value="${teacher.name}">
            </li>

            <li>
                <span class="fSpan">性别：</span>
                <select name="gender" ${disabled}>
                    <option value="-1">请选择性别</option>
                    <option value="1" <c:if test="${teacher.gender==1}">selected="selected"</c:if>>男</option>
                    <option value="2" <c:if test="${teacher.gender==2}">selected="selected"</c:if>>女</option>
                </select>
            </li>

            <li>
                <span class="fSpan">
                     <b>*</b>
                    职工编号：
                </span>
                <input type="text" name="no" ${disabled} value="${teacher.no}">
            </li>
            <li>
                <span class="fSpan">手机号码：</span>
                <input type="text" name="mobile" ${disabled} data-rule-mobile="true" data-msg-mobile="手机格式错误"
                       value="${teacher.mobile}">
            </li>
            </li>
            <li>
                <span class="fSpan">邮箱：</span>
                <input type="text" name="email" ${disabled} data-rule-mail="true" data-msg-mail="邮箱格式错误"
                       value="${teacher.email}">
            </li>

            <li>
                <span class="fSpan">身份证号码：</span>
                <input type="text" name="identity" ${disabled} data-rule-idCard="true" data-msg-idCard="身份证格式错误"
                       value="${teacher.identity}">
            </li>

            <li>
                <span class="fSpan">所属部门：</span>
                <select name="departmentId" ${disabled}>
                    <option value="0">请选择部门</option>
                    <c:forEach items="${departList}" var="depart">
                        <option value="${depart.id}"
                                <c:if test="${teacher.departmentId ==depart.id}">selected</c:if>>${depart.name}</option>
                    </c:forEach>
                </select>
            </li>

            <li>
                <span class="fSpan">开始工作时间：</span>
                <input type="text"  ${disabled} name="startWork" id="beginWork"
                       <c:if test="${gukeer:notEmptyString(teacher.startWork)}">value="${gukeer:millsToyyyyMMdd(teacher.startWork)}"</c:if>
                       class="laydate-icon"/>
            </li>
            <li>
                <span class="fSpan">最高毕业时间：</span>
                <input ${disabled} type="text"
                                   <c:if test="${gukeer:notEmptyString(teacher.highTime)}">value="${gukeer:millsToyyyyMMdd(teacher.highTime)}"</c:if>
                                   id="graduateDate" name="highTime" class="laydate-icon"/>
            </li>
            <li>
                <span class="fSpan">最高专业：</span>
                <input ${disabled} type="text" name="highJob" value="${teacher.highJob}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">评职详细：</span>
                    <input ${disabled} type="text" name="pzxx" value="${teacher.pzxx}"/>
                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">家庭住址详细：</span>
                <input ${disabled} type="text" name="address" value="${teacher.address}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">骨干教师级别：</span>
                    <input ${disabled} type="text" name="ggjsjb" value="${gukeer:intToString(teacher.ggjsjb)}"/>
                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">合同开始时间：</span>
                <input ${disabled} type="text" id="beginDate"
                                   <c:if test="${gukeer:notEmptyString(teacher.htkssj)}">value="${gukeer:millsToyyyyMMdd(teacher.htkssj)}"</c:if>
                                   name="htkssj" class="laydate-icon"/>
            </li>
            <li>
                <span class="fSpan">曾用名：</span>
                <input ${disabled} type="text" name="cym" value="${teacher.cym}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">家庭邮编：</span>
                    <input ${disabled} type="text" name="jtyb" value="${teacher.jtyb}"/>
                </li>
                <li>
                    <span class="fSpan">是否专任教师：</span>
                   <%-- <input ${disabled} type="text" name="sfzrjs" value="${gukeer:intToString(teacher.sfzrjs)}"/>--%>
                    <select name="sfzrjs">
                        <option value="0">请选择</option>
                        <option value="是" <c:if test="${teacher.sfzrjs eq '是'}">selected</c:if>>是</option>
                        <option value="否" <c:if test="${teacher.sfzrjs eq '否'}">selected</c:if>>否</option>
                    </select>

                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">身份：</span>
                <input ${disabled} type="text" name="sf" value="${teacher.sf}"/>
            </li>
            <li>
                <span class="fSpan">外语水平：</span>
                <input ${disabled} type="text" name="wysp" value="${teacher.wysp}"/>
            </li>
            <li>
                <span class="fSpan">最高学制：</span>
                <input ${disabled} type="text" name="zgxz" value="${teacher.zgxz}"/>
            </li>
            <li>
                <span class="fSpan">学位数量：</span>
                <input ${disabled} type="text" name="xwsl" value="${teacher.xwsl}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">任教学科级别：</span>
                    <input ${disabled} type="text" name="rjxkjb" value="${teacher.rjxkjb}"/>
                </li>
               <%-- <li>
                    <span class="fSpan">校区：</span>
                    <input ${disabled} type="text" name="xq" value="${teacher.xq}"/>
                </li>--%>

                <li>
                    <span class="fSpan">任教学科：</span>
                    <input ${disabled} type="text" name="rjxk" value="${teacher.rjxk}"/>
                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">实职级别：</span>
                <input ${disabled} type="text" name="szjb" value="${teacher.szjb}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">工资岗位(副)：</span>
                    <input ${disabled} type="text" name="gzgwf" value="${teacher.gzgwf}"/>
                </li>
            </shiro:hasRole>

        </ul>
        <ul>
            <li>
                <span class="fSpan">教师头像：</span>
                <input type="file" id="file-input" style="display: none" accept="image/*">
                <input type="hidden" name="headUrl" id="headUrl" value="${teacher.headUrl}">
                <ul class="img-list-icon" style="width: 100px;height:100px;float: right;margin-right:141px;">
                    <li>
                        <c:if test="${gukeer:emptyString(teacher.headUrl)}">
                            <img src="${ctx}/file/pic/show?picPath=${defaultHead}" data-url="${defaultHead}"
                                 width="100%" height="100%" id="head_url">
                        </c:if>
                        <c:if test="${gukeer:notEmptyString(teacher.headUrl)}">
                            <c:if test="${gukeer:emptyString(disabled)}">
                                <div class="removeBtn" onclick="rmPic(this)">—</div>
                            </c:if>
                            <img src="${ctx}/file/pic/show?picPath=${teacher.headUrl}" data-url="${teacher.headUrl}"
                                 width="100%" height="100%" id="head_url">
                        </c:if>
                    </li>
                </ul>

                <p style="display: inline-block;margin-left:128px;margin-top: 11px">
                    <c:if test="${gukeer:emptyString(disabled)}">
                        <a id="iconUpload" class="uploadBtn-a" style="background: #54AB37;color:#fff;">上传照片</a>
                    </c:if>
                    <c:if test="${gukeer:notEmptyString(disabled)}">
                        <a class="uploadBtn-a" style="color:#fff;">&nbsp;&nbsp;</a>
                    </c:if>
                </p>


            </li>
            <li>
                <span class="fSpan">薪资：</span>
                <input ${disabled} type="text" name="salary" value="${gukeer:intToString(teacher.salary)}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">岗位分类(副)：</span>
                    <input ${disabled} type="text" name="gwflf" value="${teacher.gwflf}"/>
                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">籍贯：</span>
                <input ${disabled} type="text" name="jg" value="${teacher.jg}"/>
            </li>
            <li>
                <span class="fSpan">政治面貌：</span>
                <select ${disabled} name="zzmm">
                    <option value="0" <c:if test="${gukeer:emptyString(teacher.zzmm)}">selected="selected"</c:if>>
                        请选择政治面貌
                    </option>
                    <option value="中共党员" <c:if test="${teacher.zzmm=='中共党员'}">selected="selected"</c:if>>中共党员</option>
                    <option value="共青团员" <c:if test="${teacher.zzmm=='共青团员'}">selected="selected"</c:if>>共青团员</option>
                    <option value="群众" <c:if test="${teacher.zzmm=='群众'}">selected="selected"</c:if>>群众</option>
                    <option value="其他" <c:if test="${teacher.zzmm=='其他'}">selected="selected"</c:if>>其他</option>
                </select>
            </li>

            <li>
                <span class="fSpan">原始毕业时间：</span>
                <input ${disabled} type="text" id="firstGraduateDate"
                                   <c:if test="${gukeer:notEmptyString(teacher.ysbysj)}">value="${gukeer:millsToyyyyMMdd(teacher.ysbysj)}"</c:if>
                                   name="ysbysj" class="laydate-icon"/>
            </li>
            <li>
                <span class="fSpan">最高学历：</span>
                <select ${disabled} name="zgxl">
                    <option value="" <c:if test="${gukeer:emptyString(teacher.zgxl)}">selected="selected"</c:if>>
                        请选择最高学历
                    </option>
                    <option value="小学" <c:if test="${teacher.zgxl=='小学'}">selected="selected"</c:if>>小学</option>
                    <option value="初中" <c:if test="${teacher.zgxl=='初中'}">selected="selected"</c:if>>初中</option>
                    <option value="中职/高中" <c:if test="${teacher.zgxl=='中职/高中'}">selected="selected"</c:if>>中职/高中
                    </option>
                    <option value="专科" <c:if test="${teacher.zgxl=='专科'}">selected="selected"</c:if>>专科</option>
                    <option value="本科" <c:if test="${teacher.zgxl=='本科'}">selected="selected"</c:if>>本科</option>
                    <option value="硕士研究生" <c:if test="${teacher.zgxl=='硕士研究生'}">selected="selected"</c:if>>硕士研究生
                    </option>
                    <option value="博士研究生" <c:if test="${teacher.zgxl=='博士研究生'}">selected="selected"</c:if>>博士研究生
                    </option>
                </select>
                <%--<input type="text" name="zgxl" value="${teacher.zgxl}"/>--%>
            </li>
            <li>
                <span class="fSpan">最高毕业学校：</span>
                <input ${disabled} type="text" name="zgbyxx" value="${teacher.zgbyxx}"/>
            </li>
            <li>
                <span class="fSpan">原专业：</span>
                <input ${disabled} type="text" name="yzy" value="${teacher.yzy}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">评职时间：</span>
                    <input ${disabled} type="text" id="pzDate" name="pzsj" class="laydate-icon"
                                       <c:if test="${gukeer:notEmptyString(teacher.pzsj)}">value="${gukeer:millsToyyyyMMdd(teacher.pzsj)}"</c:if> />
                </li>
                <li>
                    <span class="fSpan">来我校时间：</span>
                    <input ${disabled} type="text" id="reportDate" name="lwxsj" class="laydate-icon"
                                       <c:if test="${gukeer:notEmptyString(teacher.lwxsj)}">value="${gukeer:millsToyyyyMMdd(teacher.lwxsj)}"</c:if>/>
                </li>
                <li>
                    <span class="fSpan">住宅电话：</span>
                    <input ${disabled} type="text" name="zzdh" value="${teacher.zzdh}"/>
                </li>
                <li>
                    <span class="fSpan">工资岗位：</span>
                    <input ${disabled} type="text" name="gzgw" value="${teacher.gzgw}"/>
                </li>
            </shiro:hasRole>

            <li>
                <span class="fSpan">合同结束时间：</span>
                <input ${disabled} type="text" id="finishDate" name="htjssj" class="laydate-icon"
                                   <c:if test="${gukeer:notEmptyString(teacher.htjssj)}">value="${gukeer:millsToyyyyMMdd(teacher.htjssj)}"</c:if>/>
            </li>
            <li>
                <span class="fSpan">办公室电话：</span>
                <input ${disabled} type="text" name="bgsdh" value="${teacher.bgsdh}"/>
            </li>
            <li>
                <span class="fSpan">是否华侨：</span>
                <input ${disabled} type="text" name="sfhq" value="${teacher.sfhq}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">是否班主任：</span>
                    <input ${disabled} type="text" name="sfbzr" value="${teacher.sfbzr}"/>
                </li>
            </shiro:hasRole>
            <li>
                <span class="fSpan">外语语种：</span>
                <input ${disabled} type="text" name="wyyz" value="${teacher.wyyz}"/>
            </li>
            <li>
                <span class="fSpan">原学制：</span>
                <input ${disabled} type="text" name="yxz" value="${teacher.yxz}"/>
            </li>
            <li>
                <span class="fSpan">最高学位：</span>
                <input ${disabled} type="text" name="zgxw" value="${teacher.zgxw}"/>
            </li>

            <shiro:hasRole name="common">
                <li>
                    <span class="fSpan">专业技术岗位分类：</span>
                    <input ${disabled} type="text" name="zyjsgwfl" value="${teacher.zyjsgwfl}"/>
                </li>
            </shiro:hasRole>

        </ul>
    </form>
</div>
<script>
    !function () {
        laydate.skin('molv');
        laydate({elem: '#beginWork'});
        laydate({elem: '#graduateDate'});
        laydate({elem: '#beginDate'});
        laydate({elem: '#firstGraduateDate'});
        laydate({elem: '#pzDate'});
        laydate({elem: '#reportDate'});
        laydate({elem: '#finishDate'});
        /* laydate({elem:'#joinDate'});
         laydate({elem:'#jobDate'});
         laydate({elem:'#graduateDate'});
         laydate({elem:'#beginDate'});
         laydate({elem:'#firstGraduateDate'});
         laydate({elem:'#pzDate'});
         laydate({elem:'#reportDate'});
         laydate({elem:'#finishDate'})*/
    }();
</script>

</body>
</html>
