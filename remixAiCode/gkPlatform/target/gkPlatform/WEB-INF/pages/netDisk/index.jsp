<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>教师云盘</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <link rel="stylesheet" href="${ctxStaticNew}/css/netTeacher.css"/>
    <style>
        .layui-layer.layui-layer-iframe.layer-anim {
            top: 6% !important;
        }

        .search-box > button.notice-search {
            border-top-left-radius: 0px !important;
            border-bottom-left-radius: 0px !important;
        }

        .trAdd {
            background: url(${ctxStaticNew}/images/school-roll-icon2.png) no-repeat center;
        }

        .trDecrease {
            background: url(${ctxStaticNew}/images/icon-c-1.png) no-repeat center;
        }

        nav > .container div.netdisk-title:before {
            content: '';
            display: block;
            width: 36px;
            height: 36px;
            position: absolute;
            left: -7px;
            top: -4px;
            background: url(${ctxStaticNew}/images/netdisk.png) no-repeat center center;
            background-size: 100% 100%;
        }

        .trAddRename {
            background: url(${ctxStaticNew}/images/school-roll-icon2.png) no-repeat center;
            height: 20px;
            width: 20px;
            float: left;
        }

        .trDecreaseRename {
            background: url(${ctxStaticNew}/images/icon-c-1.png) no-repeat center;
            height: 20px;
            width: 20px;
            float: left;
        }

        .renameInput {
            float: left;
            height: 28px;
        }
    </style>
</head>
<body>

<%@ include file="../common/sonHead/netDiskHead.jsp" %>

<main class="container">
    <!--通知公告-->
    <div id="inform-notice">
        <aside class="col-xs-3" style="padding-top: 0">
            <div class="tree-menu  table-left">
                <div class="widget-main padding-8">
                    <ul class="aaaul">
                        <li>
                            <span class="my-file-icon">我的文件</span>
                            <p style="margin-bottom: 10px;">
                                <span class="all-space">
                                    <i></i>
                                </span>
                                <span class="space-num">
                                    <i class="space-num-one"></i> /
                                    <i class="space-num-two"></i>
                                </span>
                            </p>
                        </li>
                        <div class="little-table">
                            <p class="left-a all active" onclick="categoryDocuments('allFile')">全部</p>
                            <p value="documents" class="left-a documents" onclick="categoryDocuments('documents')">
                                文档</p>
                            <p value="images" class="left-a images" onclick="categoryDocuments('images')">图片</p>
                            <p value="audios" class="left-a audios" onclick="categoryDocuments('audios')">音频</p>
                            <p value="videos" class="left-a videos" onclick="categoryDocuments('videos')">视频</p>
                            <p value="others" class="left-a others" onclick="categoryDocuments('others')">其他</p>
                        </div>
                        <li class="shareli">
                            <span value="shares" class="left-a my-file-share"
                                  onclick="openDialog('选择要分享的人员','${ctx}/net/disk/share/pop?chooseIds=','860px','620px');">共享文件</span>
                        </li>
                        <%--<c:forEach items="${fileFromSeafileEntityList}" var="seafileEntity" varStatus="status">--%>
                            <%--<c:if test="${seafileEntity.name =='回收站'}">--%>
                                <li class="recycleli">
                                    <%--<span class="left-a my-file-share recycle"--%>
                                          <%--fileId="${seafileEntity.id}" fileType="${seafileEntity.type}" fileName="${seafileEntity.name}">${seafileEntity.name}</span>--%>
                                </li>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    </ul>
                </div>
            </div>
        </aside>
        <main class="col-xs-9">
            <div class="search-box">
                <div class="directoryPathDiv" id="directoryPathDiv">
                    <input type="hidden" id="hiddenCurrentfolederName">
                </div>
                <button onclick="openDialog('上传文件','${ctx}/net/disk/upload/pop?url=${ctx}/net/disk/upload/&token=${token}&repositoryId=${repositoryId}','500px','360px');"
                        class="">上传文件
                </button>
                <button onclick="mkdiradd()" class="">新建文件夹
                </button>
                <button class="notice-search summitButton" id="search" onclick="categoryDocuments('search')"></button>
                <input type="text" class="searchInput " placeholder="搜索文件" value="" name="searchTitle"/>
            </div>
            <input type="hidden" value="${repositoryId}" class="repositoryId">
            <input type="hidden" value="${totalFileUrl}" class="totalFileUrl">
            <input type="hidden" value="${repositoryUrl}" class="repositoryUrl">
            <input type="hidden" value="${token}" class="token">
            <div>
                <p style="margin-bottom: 15px;">全部</p>
                <div class="operating-containt">
                    <i><input type="checkbox"/>已经选择 <i>11</i> 个文件(夹)</i>
                    <span class="down" onclick="download()">下载</span>
                    <span class="delete"
                          <%--onclick="deleteF()"--%>onclick="alertTipsDel(500,300,'删除','确定要删除选中的文件或者文件夹吗？','deleteF()')">删除</span>
                    <span class="move"
                          onclick="openDialogMove('移动','${ctx}/net/disk/move/pop?token=${token}&repositoryId=${repositoryId}&flag=move','1000px','800px');">移动</span>
                    <span class="copy"
                          onclick="openDialogMove('复制','${ctx}/net/disk/move/pop?token=${token}&repositoryId=${repositoryId}&flag=copy','400px','500px');">复制</span>
                    <span class="rename" onclick="rename()">重命名</span>
                    <span class="share" onclick="share()">分享</span>
                </div>
            </div>
            <div class="tableparentdiv">
                <table>
                    <thead>
                    <%--<tr>--%>
                    <%--<th width="3%"><input type="checkbox" name="" value=""/></th>--%>
                    <%--<th width="18%">图标</th>--%>
                    <%--<th width="10%">文件名称</th>--%>
                    <%--<th width="8%">文件大小</th>--%>
                    <%--<th width="8%">时间</th>--%>
                    <%--</tr>--%>
                    <tr style="height: auto;">
                        <th width="2%"></th>
                        <th width="2%"></th>
                        <th width="24%"></th>
                        <th width="12%"></th>
                        <th width="10%"></th>
                    </tr>
                    </thead>
                    <tbody class="allfileTbody">
                    <c:if test="${fileFromSeafileEntityList.size()==0||null==fileFromSeafileEntityList}">
                        <i style="color: red;">请检查网络是否连接</i>
                    </c:if>
                    <c:if test="${fileFromSeafileEntityList.size()>0||null==fileFromSeafileEntityList}">
                        <c:forEach items="${fileFromSeafileEntityList}" var="seafileEntity" varStatus="status">
                            <tr class="allFileTr">
                                <td><input type="checkbox" id="" filetype="${seafileEntity.type}" val="fileName"
                                           value="${seafileEntity.name}" fileName="${seafileEntity.name}"/></td>
                                <td><img src="" alt=""></td>
                                <td class="file-type"
                                    onclick="onefileFunction('${seafileEntity.id}','${seafileEntity.type}','${seafileEntity.name}')">${seafileEntity.name}</td>
                                <td onclick="onefileFunction('${seafileEntity.id}','${seafileEntity.type}','${seafileEntity.name}')">
                                    <c:if test="${seafileEntity.sizeStr !=null}">${seafileEntity.sizeStr}</c:if></td>
                                <td onclick="onefileFunction('${seafileEntity.id}','${seafileEntity.type}','${seafileEntity.name}')">
                                    <c:if test="${seafileEntity.time !=null}">${seafileEntity.time}</c:if></td>
                                <td><input type="hidden" value="${seafileEntity.name}" oldName="${seafileEntity.name}"
                                           class="renameInput" fileType="${seafileEntity.type}">
                                    <i class="trAddRename" style="display: none"></i><i class="trDecreaseRename"
                                                                                        style="display: none"></i></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</main>
<script type="text/javascript">
    /*普通的内容提示框*/
    function alertTipsDel(width, height, title, content, clickUrl) {

        var html = '<div class="" style="height:' + height + ';width:' + width + '"><div class="alertDivHeader"><label>' + title + '</label></div><div class="alertDivContent"><label>' + content + '</label><br><br>删除后将自动保存到回收站<div class="alertButtons"><input type="button" onclick="closeAlertDiv()" value=" 取消 " /><input type="button" value=" 确定 " onclick="' + clickUrl + '" /></div></div></div>';
        //页面层-自定义
        layer.open({
            type: 1,
            area: [width, height],
            title: false,
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            content: html
        });

    }

    function size(size, flag) {
        var strSize = "";
        var tem = 0;
        if (size >= 1000 * 1000 * 1000) {
            tem = Math.ceil(size / (1000 * 1000 * 1000));
            strSize = tem + "GB"
        } else if (size < (1000 * 1000 * 1000) && size >= (1000 * 1000)) {
            tem = Math.ceil(size / (1000 * 1000));
            strSize = tem + "MB";
        } else {
            tem = Math.ceil(size / 1000);
            strSize = tem + "kb"
        }
        return strSize;
    }

    $(function () {
        var totalSize = '${sessionScope.totalSize}';
        var usage = '${sessionScope.usage}';
        var strTotal = size(totalSize);
        var strUsage = size(usage);
        $(".space-num-two").text(strTotal);
        $(".space-num-one").text(strUsage);

        var percentagee = usage / totalSize;
        if (percentagee <= 1) {
            percentagee = 1;
        }
        if (percentagee >= 100) {
            $('.all-space i').css('border-radius', '8px')
        }
        var percentage = percentagee + "%";
        $('.all-space i').width(percentage);

        var totalUrl = '${totalFileUrl}';
        var currentFolederName = "";
        partflush(totalUrl, currentFolederName);
//        var percentage = parseFloat(((usage/totalSize)*100).toFixed(2))+"%";

    })

    function share() {
        var inputs = $('input[val="fileName"]:checked');
        if (inputs.length == 0) {
            layer.msg("目前没有选中的文件");
            return;
        }
        openDialog('选择要分享的人', '${ctx}/notify/chooseperson/show?chooseIds=', '860px', '620px')
    }

    function chooseResult(depratmentIds, depratmentNames) {
        var names = "";
        var departNames = depratmentNames.split(",");
        for (var i = 0; i < departNames.length; i++) {
            if (departNames[i].trim() != "") {
                names += "<span class='receive'>" + departNames[i] + "</span>";
            }
        }
        $("#chooseWhoTell").html(names + " <input type='button' value='更改'>");
        $("#whichDepartMent").val(depratmentIds);

        $("#chooseWhoTell").attr("onclick", "openDialog('选择人员','${ctx}/notify/chooseperson/show?chooseIds=" + depratmentIds + "','950px','620px')");

    }

    $(".allfileTbody").on("click", ".trAddRename", function () {
        var divAs = $(".directoryPathDiv>a");
        var directoryPath = directoryPathF(divAs);
        var oldName = $(this).siblings("input").attr("oldName");
        var newName = $(this).siblings("input").val();
        var fileType = $(this).siblings("input").attr("fileType");

        $.post("${ctx}/net/disk/rename", {
            directoryPath: encodeURI(directoryPath),
            oldName: encodeURI(oldName),
            newName: encodeURI(newName),
            repositoryId: repositoryId,
            token: token,
            fileType: fileType
        }, function (data) {
            var currentFolederName = $("#hiddenCurrentfolederName").val();
            var totalUrl = $(".totalFileUrl").val();
            partflush(totalUrl, currentFolederName);
        })
    })


    function directoryPathF(divas) {
        var aLength = 0;
        if (divas != undefined && divas != "") {
            aLength = divas.length
        }

        var directoryPath = "";
        if (aLength > 0) {
            directoryPath = $(".directoryPathDiv a").last().attr("hre");
        }
        return directoryPath;
    }

    function rename() {
        var inputs = $('input[val="fileName"]:checked');
        if (inputs.length > 1) {
            layer.msg("请选择要修改名称的文件");
            return;
        }
        if (inputs.length == 0) {
            layer.msg("目前没有选中的文件");
            return;
        }

        $(inputs).parents("td").siblings().last().children("input").attr("type", "text");
        console.log($(inputs).parents("td").siblings().last().children("i"))
        $(inputs).parents("td").siblings().last().children("i").css("display", "inline-block");
    }
    var arr = [];
    var indexFor = 0;
    function jsonArrCheckedFiles(inputs) {
        var id = "";
        var fileName = "";
        var fileType = "";
        inputs.each(function (i) {
            fileName = $(this).attr("fileName");
            fileType = $(this).attr("fileType");
            var json = {"fileName": encodeURI(fileName), "fileType": fileType};
            arr.push(json);
        })

        return arr;
    }

    //删除
    function deleteF() {
        var divAs = $(".directoryPathDiv>a");
        var aLength = 0;
        if (divAs != undefined && divAs != "") {
            aLength = divAs.length
        }

        var directoryPath = "";
        if (aLength > 0) {
            directoryPath = $(".directoryPathDiv a").last().attr("hre");
        }
        console.log($(".directoryPathDiv a").last());
        var fileType = "file";
        var inputs = $('input[val="fileName"]:checked');

        var fileName = "";
        var inputsLength = inputs.length;
        if (inputsLength == 0) {
            layer.msg("请选择文件");
            return;
        } else if (inputsLength == 1) {
            console.log($(inputs));
            if ($(inputs).attr("filetype") == "dir") {
                fileType = "dir";
            }
            fileName = $(inputs).attr("fileName");
            var json = {"fileName": fileName, "fileType": fileType};
            arr.push(json);
        } else {
            arr = jsonArrCheckedFiles(inputs);
        }

        $.post("${ctx}/net/disk/move", {
            flag: "move",
            parent_dir: encodeURI("/回收站"),
            directoryPath: encodeURI(directoryPath),
            arr: JSON.stringify(arr),
        }, function (data) {
            if (data.code == 0) {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.layer.closeAll();

                layer.msg("移动成功");
                top.layer.close();
                var currentFolederName = parent.$("#hiddenCurrentfolederName").val();
                var totalfile = parent.$(".totalFileUrl").val();
                parent.partflush(totalfile, currentFolederName);

            }
        })

        //原来删除的代码
        <%--$.get("${ctx}/net/disk/delete", {--%>
        <%--token: token,--%>
        <%--repositoryId: repositoryId,--%>
        <%--directoryPath: encodeURI(directoryPath),--%>
        <%--arr: encodeURI(JSON.stringify(arr))--%>
        <%--}, function (data) {--%>
        <%--var currentFolederName = $("#hiddenCurrentfolederName").val();--%>
        <%--var totalUrl = $(".totalFileUrl").val();--%>
        <%--partflush(totalUrl, currentFolederName);--%>
        <%--})--%>
    }

    //第三个参数仅仅作为创建回收站文件夹时 判断拼接的位置用的  回收站拼接的地方为左侧菜单栏
    function partflush(totalUrl, currentName) {
        $(".allFileTbody").empty();
        $.get("${ctx}/net/disk/dir/fils", {
            url: encodeURI(totalUrl),
            token: token
        }, function (data) {
            var fileFromSeafiles = data.fileFromSeafileEntityList;
            $(".totalFileUrl").val(data.url);
            var html = "";
            for (var i = 0; i < fileFromSeafiles.length; i++) {
                var fileId = fileFromSeafiles[i].id;
                var fileType = fileFromSeafiles[i].type;
                var fileName = fileFromSeafiles[i].name;
                var sizeStr = fileFromSeafiles[i].sizeStr;
                var time = fileFromSeafiles[i].time;
                if (sizeStr == null) {
                    sizeStr = "";
                }
                if (time == null) {
                    time = "";
                }

                var recyclehtml = "";
                if (fileName == "回收站") {
                    recyclehtml = '<span  class="left-a my-file-share recycle"   value="' + fileName + '"  fileId="' + fileId + '" fileType="' + fileType + '" fileName="' + fileName + '">' + fileName + '</span>';
                    continue;
                }
                html += '<tr class="allFileTr">' +
                        '<td><input type="checkbox" id="" fileId="' + fileId + '" fileType="' + fileType + '" fileName="' + fileName + '" val="fileName" value="' + fileName + '"/></td>' +
                        '<td><img src="" alt=""></td>' +
                        '<td class="params file-type" fileId="' + fileId + '" fileType="' + fileType + '" fileName="' + fileName + '">' + fileName + '</td> ' +
                        '<td>' + sizeStr + '</td>' +
                        '<td>' + time + '</td>' +
                        '<td><input type="hidden" value="' + fileName + '" oldName="' + fileName + '" class="renameInput" fileType="' + fileType + '">' +
                        '<i class="trAddRename" style="display: none" ></i><i class="trDecreaseRename" style="display: none"></i></td>' +
                        '</tr>';
            }
            ;
            $(".recycleli").append(recyclehtml);
            $(".allfileTbody").append(html);
            isFileType();
        });
    }


    //    左侧菜单点击效果
    $('.little-table p').on('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });
    var flag_table = 1;
    $('.my-file-icon').on('click', function () {
        flag_table = !flag_table;
        if (!flag_table) {
            $('.little-table').slideUp(100);
            $(this).addClass('my-file-iconn');
        } else {
            $('.little-table').slideDown(100);
            $(this).removeClass('my-file-iconn');
        }
    });

    isFileType();
    //      判断文件类型
    function isFileType() {
        var flieTypes = $('.tableparentdiv .file-type');
        flieTypes.each(function (a, b) {
            var ty = $(b).text().split('.'),
                    ty_length = ty.length,
                    ty_last = ty[ty_length - 1],
                    ty_img = $(b).parent().children('td').eq(1).children();
            if (ty_length <= 1) {
                ty_img.attr('src', '${ctxStaticNew}/images/net_disk_files.png');
            }
            if (ty_length > 1 && (ty_last == 'png' || ty_last == 'jpg')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_img.png');
            }
            if (ty_length > 1 && (ty_last == 'txt')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_txt.png');
            }
            if (ty_length > 1 && (ty_last == 'doc' || ty_last == 'docx')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_do.png');
            }
            if (ty_length > 1 && (ty_last == 'xls')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_do.png');
            }
            if (ty_length > 1 && (ty_last == 'xls')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_xls.png');
            }
            if (ty_length > 1 && (ty_last == 'pdf')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_pdf.png');
            }
            if (ty_length > 1 && (ty_last == 'ppt')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_ppt.png');
            }
            if (ty_length > 1 && (ty_last == 'zip' || ty_last == 'rar')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_zip.png');
            }
            if (ty_length > 1 && (ty_last == 'rmvb' || ty_last == 'mp4')) {
                ty_img.attr('src', '${ctxStaticNew}/images/op_list_rmvb.png');
            } else {

            }
        })
    }


    var token = $(".token").val();
    var repositoryId = $(".repositoryId").val();


    //下载
    function download() {
        var divAs = $(".directoryPathDiv>a");
        var aLength = 0;
        if (divAs != undefined && divAs != "") {
            aLength = divAs.length
        }

        var directoryPath = "";
        if (aLength > 0) {
            directoryPath = $(".directoryPathDiv a").last().attr("hre");
        }
        console.log($(".directoryPathDiv a").last());
        var fileType = "file";
        var inputs = $('input[val="fileName"]:checked');
        var fileName = "";
        var inputsLength = inputs.length;
        if (inputsLength == 0) {
            layer.msg("请选择文件");
            return;
        } else if (inputsLength == 1) {
            console.log($(inputs));
            if ($(inputs).attr("filetype") == "dir") {
                fileType = "dir";
            }
            fileName = $(inputs).attr("fileName");
            var json = {"fileName": encodeURI(fileName), "fileType": fileType};
            arr.push(json);
        } else {
            arr = jsonArrCheckedFiles(inputs);
        }

        $.get("${ctx}/net/disk/download", {
            token: token,
            repositoryId: repositoryId,
            directoryPath: directoryPath,
            fileName: fileName,
            fileType: fileType,
            arr: JSON.stringify(arr)
        }, function (data) {
            if (data.code == 0) {
                var downloadUrls = data.data;
                for (var i = 0; i < downloadUrls.length; i++) {
                    window.open(downloadUrls[i]);
//                    window.location.href=downloadUrls[i];
//                    console.log(i);
                }

                var currentFolederName = $("#hiddenCurrentfolederName").val();
                var totalUrl = $(".totalFileUrl").val();

                partflush(totalUrl, currentFolederName);
            }
        })
    }

    //移动pop
    function openDialogMove(title, url, width, height, target, operation) {
        var divAs = $(".directoryPathDiv>a");
        var aLength = 0;
        if (divAs != undefined && divAs != "") {
            aLength = divAs.length
        }

        var directoryPath = "";
        if (aLength > 0) {
            directoryPath = $(".directoryPathDiv a").last().attr("hre");
        }

        var fileType = "file";
        var inputs = $('input[val="fileName"]:checked');
        var fileName = "";
        var inputsLength = inputs.length;
        if (inputsLength == 0) {
            layer.msg("请选择文件");
            return;
        } else if (inputsLength == 1) {
            console.log($(inputs));
            if ($(inputs).attr("filetype") == "dir") {
                fileType = "dir";
            }
            fileName = $(inputs).attr("fileName");
            var json = {"fileName": encodeURI(fileName), "fileType": fileType};
            arr.push(json);
        } else {
            arr = jsonArrCheckedFiles(inputs);
        }


        layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: false, //开启最大化最小化按钮
            content: url,
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                var inputForm = body.find('#inputForm');
                var top_iframe;
                /*if(target){
                 top_iframe = target;//如果指定了iframe，则在改frame中跳转
                 }
                 inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示*/
                if (iframeWin.contentWindow.doSubmit(directoryPath, arr)) {
                    //debugger;
                    setTimeout(function () {
                        parent.location.reload();
                    }, 400);
                    /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                    setTimeout(function () {
                        top.layer.close(index)
                    }, 300);//延时0.1秒，对应360 7.1版本bug
                }

            },
            cancel: function (index) {
                top.layer.close(index);
            }
        });
    }


    var directoryPath = "";
    $(".allfileTbody").on('click', '.params', function () {
        console.log(this)
        $('.tableparentdiv').html();
        var fileId = $(this).attr("fileId");
        var fileName = $(this).attr("fileName");
        var fileType = $(this).attr("fileType");
        onefileFunction(fileId, fileType, fileName);
    });

    $(".recycleli").on('click', '.recycle', function () {
        var fileId = $(this).attr("fileId");
        var fileName = $(this).attr("fileName");
        var fileType = $(this).attr("fileType");
        onefileFunction(fileId, fileType, fileName);
    });
    /*
     *   这个是点击当前文件夹 查看文件夹下文件的方法
     * */
    //id 文件的id  type:文件的类型  name:当前点击的文件夹的名称
    function onefileFunction(id, type, name) {
        $('.tableparentdiv').html();
        if (type == "dir") {
            console.log(name)
            $("#hiddenCurrentfolederName").val(name);
            $(".allFileTbody").empty();
            directoryPath += name + '/';
            $(".directoryPathDiv").val(directoryPath);
            $(".directoryPathDiv").append('<a hre="' + directoryPath + '">' + name + '/</a>');
            var url = $(".totalFileUrl").val() + name + "/";
            if (name == "recycle") {
                url = "http://120.27.46.200:60001/api2/repos/${repositoryId}/?p=/回收站";
            }
            $.get("${ctx}/net/disk/dir/fils", {
                url: encodeURI(encodeURI(url)),
                token: token
            }, function (data) {
                var fileFromSeafiles = data.fileFromSeafileEntityList;
                $(".totalFileUrl").val(data.url);
                var html = "";
                for (var i = 0; i < fileFromSeafiles.length; i++) {
                    var fileId = fileFromSeafiles[i].id;
                    var fileType = fileFromSeafiles[i].type;
                    var fileName = fileFromSeafiles[i].name;
                    var sizeStr = fileFromSeafiles[i].sizeStr;
                    var time = fileFromSeafiles[i].time;
                    if (sizeStr == null || sizeStr == "0kb") {
                        sizeStr = "";
                    }
                    if (time == null) {
                        time = "";
                    }
                    html += '<tr class="allFileTr">' +
                            '<td><input type="checkbox" id="" fileId="' + fileId + '" fileType="' + fileType + '" fileName="' + fileName + '" val="fileName" value="' + fileName + '"/></td>' +
                            '<td><img src="" alt=""></td>' +
                            '<td class="params file-type" fileId="' + fileId + '" fileType="' + fileType + '" fileName="' + fileName + '">' + fileName + '</td> ' +
                            '<td>' + sizeStr + '</td>' +
                            '<td>' + time + '</td>' +
                            '<td><input type="hidden" value="' + fileName + '" oldName="' + fileName + '" class="renameInput" fileType="' + fileType + '">' +
                            '<i class="trAddRename" style="display: none" ></i><i class="trDecreaseRename" style="display: none"></i></td>' +
                            '</tr>';
                }
                ;
                $(".allfileTbody").append(html);
                isFileType();
            });
        } else {
            window.open("");
        }
        ;
    }
    ;

    activeMenu("tzmenu", 0);


    function acquireUrl() {
        $.get("${ctx}/net/disk/acquire/upload/url", {
            token: token,
            repositoryId: repositoryId
        }, function (data) {
            if (data.code == 0) {
                var uploadUrl = data.data;
                var url = "${ctx}/net/disk/file/pop?url=${ctx}/net/disk/upload";
                openDialogUploadFile("上传文件", url, '500px', '350px');
            } else {
                layer.msg("获取上传文件的路径失败");
            }
        })
    }
    ;

    function mkdiradd() {
        var html = '<p class="mkdirTr"><input  type="text"><i class="trAdd"  onclick="mkdir(this)"></i><i class="trDecrease" onclick="deletr(this)"></i></p>';
        $(html).appendTo($('.tableparentdiv'));
        <%--$.get("${ctx}/net/disk/mkdir",{--%>

        <%--},function (data) {--%>

        <%--})--%>
    }

    function mkdir(thiso) {
        var folderName = $(thiso).siblings('input').val();
        var divAs = $(".directoryPathDiv>a");
        var directoryPath = directoryPathF(divAs);
        console.log(folderName);
        console.log($(thiso));
        $.post("${ctx}/net/disk/mkdir", {
                    repositoryId: repositoryId,
                    token: token,
                    folderName: encodeURI(folderName),
                    directoryPath: directoryPath
                }, function (data) {
                    if (data.code == 0) {

//                        var currentFolederName = $("#hiddenCurrentfolederName").val();
                        $(".mkdirTr").empty();
                        var totalUrl = $(".totalFileUrl").val();
                        partflush(totalUrl, directoryPath, data.data);
                    } else {
                        layer.msg("网络开小差了,刷新试试呢")
                    }
                }
        )
    }
    function openDialogUploadFile(title, url, width, height, target) {
        layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: false, //开启最大化最小化按钮
            content: url,
            btn: ['确定', '关闭'],
            yes: function (index, layero) {
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                var inputForm = body.find('#inputForm');
                var top_iframe;
                /*if(target){
                 top_iframe = target;//如果指定了iframe，则在改frame中跳转
                 }
                 inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示*/

                if (iframeWin.contentWindow.doSubmit()) {
                    //debugger;
                    setTimeout(function () {
                        parent.location.reload();
                    }, 400);
                    /*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*/
                    setTimeout(function () {
                        top.layer.close(index)
                    }, 300);//延时0.1秒，对应360 7.1版本bug
                }

            },
            cancel: function (index) {
                top.layer.close(index);
            }
        });
    }
    function categoryDocuments(str) {
        $('.tableparentdiv').html();
        var imagesHtml = "";
        var documentsHtml = "";
        var audiosHtml = "";
        var videosHtml = "";
        var ohersHtml = "";
        var allHtml = "";
        var searchHtml = "";
        $.post("${ctx}/net/disk/all/files", {
            repositoryId: repositoryId,
            token: token
        }, function (data) {
            var images = data.images;
            var documents = data.documents;
            var audios = data.audios;
            var videos = data.videos;
            var others = data.others;
            var shares = data.shares;
            var allFile = data.allFiles;
            if (str == "images") {
                $(".images").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
                if (images != "[]") {
                    for (var i = 0; i < images.length; i++) {
                        imagesHtml += '<tr class="imagesFileTr"><td><input filetype="' + images[i].type + '" val="fileName" type="checkbox"  id="" value="' + images[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + images[i].id + '",' + images[i].type + ')">' + images[i].name + '</td><td onclick="onefileFunction(' + images[i].id + '",' + images[i].type + ')">' + images[i].sizeStr + '</td><td onclick="onefileFunction(' + images[i].id + '",' + images[i].type + ')">' + images[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(imagesHtml);
            }

            if (str == "documents") {
                $(".documents").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
                if (documents != "[]") {
                    for (var i = 0; i < documents.length; i++) {
                        documentsHtml += '<tr class="documentsFileTr"><td><input filetype="' + documents[i].type + '" val="fileName" type="checkbox"  id="" value="' + documents[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + documents[i].id + '",' + documents[i].type + ')">' + documents[i].name + '</td><td onclick="onefileFunction(' + documents[i].id + '",' + documents[i].type + ')">' + documents[i].sizeStr + '</td><td onclick="onefileFunction(' + documents[i].id + '",' + documents[i].type + ')">' + documents[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(documentsHtml);
            }

            if (str == "audios") {
                $(".audios").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
                if (audios != "[]") {
                    for (var i = 0; i < audios.length; i++) {
                        audiosHtml += '<tr class="audiosFileTr"><td><input filetype="' + audios[i].type + '" val="fileName" type="checkbox"  id="" value="' + audios[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + audios[i].id + '",' + audios[i].type + ')">' + audios[i].name + '</td><td onclick="onefileFunction(' + audios[i].id + '",' + audios[i].type + ')">' + audios[i].sizeStr + '</td><td onclick="onefileFunction(' + audios[i].id + '",' + audios[i].type + ')">' + audios[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(audiosHtml);
            }

            if (str == "videos") {
                $(".videos").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
                if (videos != "[]") {
                    for (var i = 0; i < videos.length; i++) {
                        videosHtml += '<tr class="videosFileTr"><td><input val="fileName" filetype="' + videos[i].type + '" type="checkbox"  id="" value="' + videos[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type filename" onclick="onefileFunction(' + videos[i].id + '",' + videos[i].type + ')">' + videos[i].name + '</td><td onclick="onefileFunction(' + videos[i].id + '",' + videos[i].type + ')">' + videos[i].sizeStr + '</td><td onclick="onefileFunction(' + videos[i].id + '",' + videos[i].type + ')">' + videos[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(videosHtml);
            }
            if (str == "others") {
                $(".others").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
                if (others != "[]") {
                    for (var i = 0; i < others.length; i++) {
                        ohersHtml += '<tr class="othersFileTr"><td><input filetype="' + others[i].type + '" val="fileName" type="checkbox"  id="" value="' + others[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + others[i].id + '",' + others[i].type + ')">' + others[i].name + '</td><td onclick="onefileFunction(' + others[i].id + '",' + others[i].type + ')">' + others[i].sizeStr + '</td><td onclick="onefileFunction(' + others[i].id + '",' + others[i].type + ')">' + others[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(ohersHtml);
            }

            if (str == "allFile") {
                $(".allFileTbody").empty();
                if (allFile != "[]") {
                    for (var i = 0; i < allFile.length; i++) {
                        allHtml += '<tr class="othersFileTr"><td><input val="fileName"  filetype="' + allFile[i].type + '" type="checkbox"  id="" value="' + allFile[i].name + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].name + '</td><td onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].sizeStr + '</td><td onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].time + '</td></tr>';
                    }
                }
                $(".allfileTbody").append(allHtml);

            }

            if (str == "shares") {
                $(".shares").addClass("active");
                $(".all").removeClass("active");
                $(".allFileTbody").empty();
            }

            if (str == "search") {
                $(".all").removeClass("active");
                var fileName = $(".searchInput").val();
                $(".allFileTbody").empty();
                if (allFile != "[]") {
                    for (var i = 0; i < allFile.length; i++) {
                        if (fileName.match(allFile[i])) {
                            console.log(allFile[i].name);
                            console.log(allFile[i].name.indexOf(fileName) + "======");
                            if (allFile[i].name.indexOf(fileName) != -1) {
                                searchHtml += '<tr class="othersFileTr"><td><input val="fileName" type="checkbox"  id="" value="' + allFile[i].name + '" filetype="' + allFile[i].type + '"/></td><td><img src="" alt=""></td><td class="file-type" onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].name + '</td><td onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].sizeStr + '</td><td onclick="onefileFunction(' + allFile[i].id + '",' + allFile[i].type + ')">' + allFile[i].time + '</td></tr>';
                            }
                        }
                    }
                    $(".allfileTbody").append(searchHtml);
                }
            }
            isFileType();
        })
    }
</script>
</body>
</html>
