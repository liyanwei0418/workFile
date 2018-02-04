<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班级管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>

</head>
<body>
<style>
    .alertDivContent label{
        margin: 0 !important;
        padding: 0 10px;
    }
</style>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<main class="container">
    <!--班级管理-->
    <div class="row" id="class-manage">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">
                    <button class="roll-addNew"
                            onclick="openDialog('新增','${ctx}/class/banji/edit?focusNode=${focusNode}','450px','500px');"/>
                    新增</button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                    <button class="roll-import"
                            onclick="openDialog('导入数据','${ctx}/class/classImport','500px','350px');">导入
                    </button>
                    <button class="roll-download" onclick="window.location.href='${ctx}/class/bjTemplate'">下载模板</button>
                </div>
            </div>
            <div>
                <table>
                    <thead>
                    <style>table th:nth-child(2), table td:nth-child(2),table th:nth-child(1), table td:nth-child(1) {
                        text-align: center;
                    }</style>
                    <tr>
                        <th width="6%"><input type="checkbox" id="choseAll"/></th>
                        <th width="6%">序号</th>
                        <th width="9%">班级名称</th>
                        <th width="9%">班级简称</th>
                        <th width="8%">班号</th>
                        <th width="12%">校区</th>
                        <th width="9%">班级类型</th>
                        <th width="9%">入学年度</th>
                        <th width="8%">学段</th>
                        <th width="8%">学制</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="gradeClass" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${gradeClass.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${gradeClass.name}
                            </td>
                            <td>
                                    ${gradeClass.shortName}
                            </td>
                            <td>
                                    ${gradeClass.bh}
                            </td>
                            <td>
                                <c:forEach items="${schoolTypes}" var="schoolType">
                                    <c:if test="${schoolType.id==gradeClass.xq}">${schoolType.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${gradeClass.bjlx == 0}">
                                        普通班级
                                    </c:when>
                                    <c:when test="${gradeClass.bjlx == 1}">
                                        民族班
                                    </c:when>
                                    <c:when test="${gradeClass.bjlx == 2}">
                                        体育班级
                                    </c:when>
                                    <c:when test="${gradeClass.bjlx == 3}">
                                        外语班级
                                    </c:when>
                                    <c:when test="${gradeClass.bjlx == 4}">
                                        其他特殊班
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                    ${gradeClass.rxnd}
                            </td>
                            <td>
                                <c:forEach items="${classSections}" var="classSection">
                                    <c:if test="${gradeClass.xd==classSection.id}">
                                        ${classSection.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach items="${classSections}" var="xz">
                                    <c:if test="${xz.id==gradeClass.xd}">${xz.sectionYear}</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <span class="edit"
                                      onclick="openDialog('修改','${ctx}/class/banji/edit?id=${gradeClass.id}','450px','500px');">编辑</span>

                                <span style="color: red"
                                      onclick="alertTips(400,222,'删除班级','确定要删除${gradeClass.name}吗？删除前请确定该班级下无学生，否则无法删除','banjiDelete(\'${gradeClass.id}\')')"> 删除 </span>
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


<form id="submit-form" method="post" action="${ctx}/class/banji/index">
    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
    <input type="hidden" name="schoolId" value="${schoolId}">
    <input type="hidden" name="xd" value="${xd}">
    <input type="hidden" name="xq" value="${xq}">
    <input type="hidden" name="nj" value="${nj}">
    <input type="hidden" name="focusNode" value="${focusNode}">
    <input type="hidden" name="nodeList" value='${nodeList}'>
</form>

<script>
    activeMenu("classManMenu",0);

    var zTree;
    var demoIframe;
    var nowfocus = "${focusNode}";

    var setting;
    var zNodes1;

    function onNodeClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        var nowId = treeNode.id;
        var njstart = nowId.indexOf("nianji");
        var xdstart = nowId.indexOf("section_");
        var xqstart = nowId.indexOf("xq_");
        var slength = "school_".length;
        var xqlength = "xq_".length;
        var njlength = "nianji".length;
        var xdlength = "section_".length;
        if (nowId.indexOf("nianji") >= 0) {
            nowfocus = nowId;
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength, xqstart);
            var xq = nowId.substring(xqstart + xqlength, njstart);
            var nj = nowId.substring(njstart + njlength);
            loadClass(schoolId, xd, xq, nj, 0, nowfocus);
        }
        zTree.expandNode(treeNode);
    }

    function setFontCss(treeId, treeNode) {
        <%--if(treeNode.id=="school_"+${schoolId}){--%>
        <%--return { 'font-weight': 'bold', color: 'black'};--%>
        <%--}--%>

        if (treeNode.id == nowfocus)
            return {
                'padding-top': ' 0',
                'background-color': '#def7f5',
                'color': 'black',
                'height': '25px',
                'opacity': '.8',
                'width': '86%'
            };
//        else return {'font-weight': 'normal', color: 'black','background-color': null};
    }
    ;
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
                onClick: onNodeClick
            }
        };
        <c:if test="${gukeer:notEmptyString(nodeList)}">
        zNodes1 = ${nodeList};
        </c:if>
        <c:if test="${gukeer:emptyString(nodeList)}">
        zNodes1 =
                [
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

                    </c:forEach>
                    </c:forEach>
                    </c:forEach>

                ];
        </c:if>
        $.fn.zTree.init($("#tree1"), setting, zNodes1);
    });

    function loadClass(sid, xd, xq, nj, pageNum, nowfocus) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        $("input[name='schoolId']").val(sid);
        $("input[name='xd']").val(xd);
        $("input[name='xq']").val(xq);
        $("input[name='nj']").val(nj);
        $("input[name='pageNum']").val(pageNum);
        $("input[name='focusNode']").val(nowfocus);
        $("input[name='nodeList']").val(JSON.stringify(zTree.getNodes()));
        $("form").submit();
    }

    function banjiDelete(id) {
        closeAlertDiv();

        $.post("${ctx}/class/banji/delete", {
            classid: id
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg(retJson.msg);
        }, "json");

        setTimeout(function () {
            window.location.reload();
        },1000);//删除

    }

    <c:if test="${pageInfo.pages!=0}">
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

    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '删除班级', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '个班级吗？', 'choose()')
        }
        else {
            layer.msg("请选择班级")
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
        $.post("${ctx}/class/banji/delete", {
            classid: spCodesTemp
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg(retJson.msg);
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 2000);//删除
    }

    function importCallBack(res){
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表','关闭'] //按钮
        }, function(){
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","${ctx}/class/class/error/export");
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
</script>
</body>
</html>