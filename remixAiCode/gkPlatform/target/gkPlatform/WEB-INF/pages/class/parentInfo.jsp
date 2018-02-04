<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>家长信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>
<style>
    table th:nth-child(2), table td:nth-child(2), table th:nth-child(1), table td:nth-child(1) {
        text-align: center;
    }


</style>
<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<main class="container">
    <!--学生管理-->
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>
        <main class="col-xs-9">
            <div class="search-box">
                <div class="roll-operation">
                    <button class="roll-add"
                            onclick="openDialog('新增','${ctx}/class/parent/info/add/index','500px','550px');">新增
                    </button>
                    <button class="roll-import"
                            onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/class/parent/import','500px','350px');">导入
                    </button>
                    <button class="roll-export" onclick="exportPar()">导出</button>
                    <button class="roll-delete" onclick="alertTips(400,202,'删除','确定要删除选中项吗？','batchDelete()')">删除</button>
                    <button class="roll-import" onclick="window.location.href='${ctx}/class/parent/template/download'">下载模板
                    </button>
                </div>
                <div class="roll-research">
                    <button class="summitButton"></button>
                    <input type="text" placeholder="请输入家长或学生姓名" name="stuName" value="${stuName}" class="searchInput"/>
                </div>
            </div>
            <div>
                <table>

                    <thead>
                    <tr>
                        <th width="4%"><input type="checkbox" id="choseAll"/></th>
                        <th width="6%">序号</th>
                        <th width="15%">家长姓名</th>
                        <th width="15%">学生姓名</th>
                        <th width="20%">学生学籍号</th>
                        <th width="10%">关系</th>
                        <th width="15%">联系方式</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="parent" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" id="${parent.parentId}"/>
                            </td>
                            <td>${status.index + 1 +(pageInfo.pageNum-1)*10}</td>
                            <td>${parent.parentName}</td>
                            <td>${parent.xsxm}</td>
                            <td>${parent.xh}</td>
                            <%--<td>${parent.xjh}</td>--%>
                            <td>
                                <c:if test="${parent.patriarch_flag == 1}">父亲</c:if>
                                <c:if test="${parent.patriarch_flag == 2}">母亲</c:if>
                                <c:if test="${parent.patriarch_flag == 3}">其他</c:if>
                            </td>
                            <td>${parent.parentPhone}</td>
                            <td>
                                <span onclick="openDialog('编辑','${ctx}/class/parent/info/add/index?prim=${parent.parentId}','500px','650px');">编辑</span>
                                <span onclick="alertTips('400px','200px','删除家长','确定要删除${parent.parentName}吗？','deleteParent(\'${parent.parentId}\')')"> 删除 </span>
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
    <form id="submit-form" method="post" action="${ctx}/class/parent/info/manage">
        <input type="hidden" name="schoolId" value="${schoolId}">
        <input type="hidden" name="classId" value="${classId}">
        <input type="hidden" name="stname" value="${stuName}">
        <input type="hidden" name="status" value="${status}">
        <input type="hidden" name="xd" value="${xd}">
        <input type="hidden" name="xq" value="${xq}">
        <input type="hidden" name="nj" value="${nj}">
        <input type="hidden" name="focusNode" value="${focusNode}">
        <input type="hidden" name="nodeList" value='${nodeList}'>
        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
        <input type="hidden" name="choose" value="">
    </form>
</main>

<script>
    activeMenu("parInfoMenu",0);

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
                    <c:forEach items='${njview.banjiview}' var='banJiView'>
                    {
                        id: "${banJiView.id}",
                        pId: "${banJiView.pid}",
                        name: "${banJiView.name}",
                        open: ${banJiView.open}
                    },
                    </c:forEach>
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
            var classId = nowId;
            var rootNode = treeNode.getParentNode().getParentNode().getParentNode().getParentNode();
            var schoolId = rootNode.id;

            classId = classId.substring("banji".length);
            schoolId = schoolId.substring("school_".length);

            loadStudent(schoolId, classId, 0, 0, 0, 0, nowfocus);
        }
        else if (nowId.indexOf("section_") < 0) {
//根节点点击
            var schoolId = nowId.substring("school_".length);
            loadStudent(schoolId, 0, 0, 0, 0, 0, nowfocus)
        }
        else if (nowId.indexOf("xq_") < 0) {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength);
            loadStudent(schoolId, 0, xd, 0, 0, 0, nowfocus)
        }
        else if (nowId.indexOf("nianji") < 0) {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength, xqstart);
            var xq = nowId.substring(xqstart + xqlength);
            loadStudent(schoolId, 0, xd, xq, 0, 0, nowfocus)
        }
        else {
            var schoolId = nowId.substring(slength, xdstart);
            var xd = nowId.substring(xdstart + xdlength, xqstart);
            var xq = nowId.substring(xqstart + xqlength, njstart);
            var nj = nowId.substring(njstart + njlength);
            loadStudent(schoolId, 0, xd, xq, nj, 0, nowfocus)
        }
    }
    ;

    function loadStudent(sid, cid, xd, xq, nj, pageNum, focusId) {
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
        var stuName = $(".searchInput").val();
        $("input[name='stname']").val(stuName);
        $("form").submit();
    });

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


    <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
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

    $("#choseAll").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true);
        } else {
            $("input[type='checkbox']").prop("checked", false);
        }
    });

    function deleteParent(val) {
        $.ajax({
            type: "post",
            url: "${ctx}/class/parent/delete",
            data: {
                prims: val
            },
            dataType: "json",
            success: function (data) {
                layer.msg(data.msg, {
                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    parent.location.reload();
                });
            },
            error: function (e) {

            }
        });
    }

    function getChecked(){
        var spCodesTemp = "";
        $("input[name='chose']:checked").each(function(i){
            if(0==i){
                spCodesTemp = $(this).attr("id");
            }else{
                spCodesTemp += (","+$(this).attr("id"));
            }
        });
        return spCodesTemp;
    }
    function batchDelete(){
        closeAlertDiv();
        deleteParent(getChecked());
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
            form.attr("action","${ctx}/class/parent/error/export");
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
    
    function exportPar() {
        var check =getChecked();
        if (check == ""){
           // layer.msg("您未选择家长，将到处当前条件的所有家长");
        }
        else{
            //layer.msg("即将导出您选择的家长");
        }

        var form=$("<form>");//定义一个form表单
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");
        form.attr("action","${ctx}/class/parent/info/export");
        var input1=$("input[name='choose']");
        var input2=$("input[name='classId']");
        var input3=$("input[name='xd']");
        var input4=$("input[name='xq']");
        var input5=$("input[name='nj']");
        var input6=$("input[name='stname']");
        input1.attr("value",check);
        $("body").append(form);//将表单放置在web中
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.submit();//表单提交
    }
</script>


</body>
</html>

