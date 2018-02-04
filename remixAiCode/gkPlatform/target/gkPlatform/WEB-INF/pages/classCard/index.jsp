<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班牌管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
</head>
<style>
    .nav-menu li a:hover{
        color:#fff !important;
    }
    .layui-layer{
        top: 15% !important;
    }
</style>
<body>

<%@ include file="../common/sonHead/classCardHead.jsp" %>

<main class="container">
    <!--班牌管理-->
    <div class="row" id="stu-manage">

        <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>

    </shiro:hasAnyRoles>
        <main class="col-xs-9">
            <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
            <div class="search-box">
                <div class="roll-operation">
                    <button class="roll-add"
                            onclick="openDialogWithoutReload('新增','${ctx}/classcard/edit?focusNode=${focusNode}','900px','55%',false);">新增
                    </button>
                    <button class="roll-delete" onclick="ifChoose()">解绑</button>
                    <button class="roll-import"
                            onclick="openDialog('导入数据','${ctx}/classcard/fileimport?url=${ctx}/classcard/mac/import','500px','350px');">导入
                    </button>
                    <button class="roll-import" onclick="window.location.href='${ctx}/classcard/classcardtemplate'">下载模板
                    </button>
                </div>

            </div>
            </shiro:hasAnyRoles>
            <div>
                <table>
                    <thead>
                    <tr>
                        <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
                             <th width="3%"><input type="checkbox" id="choseAll"/></th>
                        </shiro:hasAnyRoles>
                        <th width="3%">序号</th>
                        <th width="10%">设备名称</th>
                        <th width="22%">所在位置</th>
                        <th width="18%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="classCardView" varStatus="status">
                        <tr>
                            <shiro:hasAnyRoles name="classCardAdmin,schoolAdmin">
                                <td>
                                    <input name="chose" class="chose" type="checkbox" value="${classCardView.id}"/>
                                </td>
                            </shiro:hasAnyRoles>
                            <td>
                                ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                ${classCardView.equipmentName}
                            </td>
                            <td>
                                ${classCardView.locationName}
                            </td>
                            <td class="mode-td">
                                <span onclick="openDialogView('模块分配','${ctx}/model/classcard-app?id=${classCardView.id}&option=edit','900px','650px',false);">模块分配</span>
                                <span onclick="openDialogWithoutReload('修改','${ctx}/classcard/edit?id=${classCardView.id}&option=edit','900px','650px',false);">编辑</span>
                                <span onclick="alertTips('400px','200px','解绑班牌','确定要解绑${classCardView.equipmentName}吗？','classCardDelete(\'${classCardView.id}\')')"> 解绑 </span>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </main>
    </div>
</main>
<form id="submit-form" method="post" action="${ctx}/classcard/index">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
    <input type="hidden" name="schoolId" value="${schoolId}">
    <input type="hidden" name="classId" value="${classId}">
    <input type="hidden" name="appId" value="${appId}">
    <input type="hidden" name="stname" value="${stname}">
    <input type="hidden" name="status" value="${status}">
    <input type="hidden" name="xd" value="${xd}">
    <input type="hidden" name="xq" value="${xq}">
    <input type="hidden" name="nj" value="${nj}">
    <input type="hidden" name="focusNode" value="${focusNode}">
    <input type="hidden" name="nodeList" value='${nodeList}'>
</form>

<script>
    activeMenu("base",1);
    var zTree;
    var demoIframe;

    var nowfocus = "${focusNode}";
    var setting;
    var zNodes0;

    $(function () {
        setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false,
                fontCss: setFontCss
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            },
            callback: {
                onClick: onNodeClick,
            }
        };

                <c:if test="${gukeer:notEmptyString(nodeList)}">
                    zNodes0 = ${nodeList}
                </c:if>
                <c:if test="${gukeer:emptyString(nodeList)}">
                zNodes0 = [
                    {
                        id: "${schoolview.id}",
                        pId: "${schoolview.pid}",
                        name: "${schoolview.name}",
                        open: true
                    },
                    <c:forEach items='${schoolview.sections}' var='sections'>
                    {
                        id: "${sections.id}",
                        pId: "${sections.pid}",
                        name: "${sections.name}",
                        open: ${sections.open}
                    },
                    <c:forEach items='${sections.schoolTypeView}' var='schoolTypeView'>
                    {
                        id: "${schoolTypeView.id}",
                        pId: "${schoolTypeView.pid}",
                        name: "${schoolTypeView.name}",
                        open: ${schoolTypeView.open}
                    },
                    <c:forEach items='${schoolTypeView.njview}' var='njview'>
                    {
                        id: "${njview.tid}",
                        pId: "${njview.pid}",
                        name: "${njview.njname}",
                        open: ${njview.open}
                    },
                    <%--<c:forEach items='${njview.banjiview}' var='banJiView'>
                    {
                        id: "${banJiView.id}",
                        pId: "${banJiView.pid}",
                        name: "${banJiView.name}",
                        open: ${banJiView.open}
                    },
                    </c:forEach>--%>
                    </c:forEach>
                    </c:forEach>
                    </c:forEach>

                ];
        </c:if>
        $.fn.zTree.init($("#tree1"), setting, zNodes0);

    });


    function setFontCss(treeId, treeNode) {
        if (treeNode.id == nowfocus)
            return {
                'padding-top': ' 0',
                'background-color': '#def7f5',
                'color': 'black',
                'height': '25px',
                'opacity': '.8',
                'width': '86%'
            };
    }
    ;

    function onNodeClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        var nowId = treeNode.id;
        var name =treeNode.name;
        if (treeNode.open == false) {
            treeNode.open = true;
        }
        else {
            treeNode.open = false;
        }
        var njstart = nowId.indexOf("nianji");
        var xdstart = nowId.indexOf("section_");
        var xqstart = nowId.indexOf("xq_");
        var slength = "school_".length;
        var xqlength = "xq_".length;
        var njlength = "nianji".length;
        var xdlength = "section_".length;

        nowfocus = nowId;
        if (nowId.indexOf("banji") >= 0) {
//班级
           /* var classId = nowId;
            var rootNode = treeNode.getParentNode().getParentNode().getParentNode().getParentNode();
            var schoolId = rootNode.id;

            classId = classId.substring("banji".length);
            schoolId = schoolId.substring("school_".length);

            loadClassCard(schoolId, classId, 0, 0, 0, 0, nowfocus);*/
        }
        else if (nowId.indexOf("section_") < 0) {
//根节点点击
            var schoolId = nowId.substring("school_".length);
            loadClassCard(schoolId, 0, 0, '', 0, 0, nowfocus)
        }
        else if (nowId.indexOf("xq_") < 0) {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength);
            loadClassCard(schoolId, 0, xd, '', 0, 0, nowfocus)
        }
        else if (nowId.indexOf("nianji") < 0) {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength, xqstart);
            var xq = nowId.substring(xqstart + xqlength);
            loadClassCard(schoolId, 0, xd, xq, 0, 0, nowfocus)
        }else if(treeNode.name =='其他'){
            loadClassCard(schoolId, 0, 'qt', xq, 0, 0, nowfocus);
        }
        else {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength, xqstart);
            var xq = nowId.substring(xqstart + xqlength, njstart);
            var nj = nowId.substring(njstart + njlength);
            loadClassCard(schoolId, 0, xd, xq, nj, 0, nowfocus)
        }
    }
    ;

    function toggle(tag) {
        if (tag == $('.list li.active')) return;
        var list = $('.list li'), index = zNodes0
        for (var i = 0; i < list.length; i++) {
            if (tag == list[i]) {
                index = i;
            } else {
                $(list[i]).removeClass('active')
            }
        }
        $(list[index]).addClass('active');
        $.fn.zTree.init($("#tree1"), setting, eval('zNodes' + index));

    }
    function loadClassCard(sid, cid, xd, xq, nj, pageNum, focusId) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        var stname = $(".searchInput").val();
        $("input[name='schoolId']").val(sid);
        $("input[name='classId']").val(cid);
        $("input[name='xd']").val(xd);
        $("input[name='xq']").val(xq);
        $("input[name='nj']").val(nj);
        $("input[name='focusNode']").val(focusId);
        $("input[name='pageNum']").val(pageNum);
        $("input[name='stname']").val(stname);
        $("input[name='nodeList']").val(JSON.stringify(zTree.getNodes()));
        $("form").submit();
    }
    $(".summitButton").click(function () {
        var stname = $(".searchInput").val();
        $("input[name='stname']").val(stname);
        $("form").submit();
    });

    <c:if test="${pageInfo.pages != 0&&pageInfo!=null}">
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

    })

    </c:if>


    $(".xjSelect").change(function () {
        $("input[name='status']").val($(".xjSelect").val());
        $("form").submit();
    });

    function classCardDelete(id) {
        $.post("${ctx}/classcard/multidelete", {
            classCardIds: id+","
        }, function (retJson) {
            if (retJson.code == '0'){
                layer.msg(retJson.msg);
            }
            else {
                layer.msg("解绑失败");
            }
        }, "json");
        closeAlertDiv();
        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除

    }
    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '解绑班牌', '确定要解绑选中的' + $('input:checkbox[name=chose]:checked').length + '个班牌吗？', 'choose()')
        }
        else {
            layer.msg("请选择班牌")
        }
    }

    function choose() {
        closeAlertDiv();
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).val();
            } else {
                spCodesTemp += ("," + $(this).val());
            }
        });
        $.post("${ctx}/classcard/multidelete", {
            classCardIds: spCodesTemp
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg("解绑失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }
    var allBtn = $('#choseAll');
    var normalBtn = $('.chose');
    //    console.log(allBtn.checked);
    $(allBtn).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalBtn.length; i++) {
                normalBtn[i].checked = false;
            }
        }
    })

    function importCallBack(res){
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表','关闭'] //按钮
        }, function(){
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","${ctx}/classcard/error/export");
            var input1=$("<input>");
            input1.attr("type","hidden");
            input1.attr("name","msg");
            input1.attr("value",JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function(){
            window.location.reload(true);
        });
    }
    var numzteer = $(".row .col-xs-3").length;
    if(numzteer == 0){
        $("main .col-xs-9").css("padding-left", '15pxF')
        $(".col-xs-9").css("width", '100%');
    }

    function closeAll() {
        layer.closeAll(); //关闭加载层
    }

</script>
</body>
</html>

