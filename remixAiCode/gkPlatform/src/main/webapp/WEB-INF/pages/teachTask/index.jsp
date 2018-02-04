<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教务管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<nav>
    <div class="container">
        <div class="roll-manage-title">学籍管理</div>
        <div class="roll-manage-menu">
            <ul>
                <li><a href="${ctx}/class/parent/info/index">家长信息</a></li>

                <shiro:hasPermission name="class:student:view">
                    <li><a href="${ctx}/class/index" class="active">学生管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="class:banji:view">
                    <li><a href="${ctx}/class/banji/index">班级管理</a></li>
                </shiro:hasPermission>
                <li><a href="${ctx}/class/teacherarrangement/index">教师安排</a></li>
                <shiro:hasPermission name="class:schoolSetting:view">
                    <li><a href="${ctx}/class/schoolsetting/index">学校设置</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="class:xueDuan:view">
                    <li><a href="${ctx}/class/xueduan/index">学段管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="class:stuAccount:view">
                    <li><a href="${ctx}/class/stuaccount/index">学生账号管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="class:parAccount:view">
                    <li><a href="${ctx}/class/paraccount/index">家长账号管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="class:role:view">
                    <li><a href="${ctx}/class/rolemanage/index">角色管理</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>
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
                    <select class="xjSelect">
                        <option value="-1" <c:if test="${status==-1}">selected</c:if>>所有状态</option>
                        <option value="0" <c:if test="${status==0}">selected</c:if>>在籍在校</option>
                        <option value="1" <c:if test="${status==1}">selected</c:if>>在籍离校</option>
                        <option value="2" <c:if test="${status==2}">selected</c:if>>在校不在籍</option>
                        <option value="3" <c:if test="${status==3}">selected</c:if>>不在籍不在校</option>
                    </select>
                    <button class="roll-add"
                            onclick="openDialog('新增','${ctx}/student/edit?focusNode=${focusNode}','900px','650px');">新增
                    </button>
                    <button class="roll-import"
                            onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/class/student/import','500px','350px');">导入
                    </button>
                    <button class="roll-export" onclick="exportStu()">导出</button>
                    <button class="roll-delete" onclick="ifChoose()">删除</button>
                    <button class="roll-import" onclick="window.location.href='${ctx}/class/studentTemplate'">下载模板
                    </button>
                </div>
                <div class="roll-research">
                    <button class="summitButton"></button>
                    <input type="text" placeholder="请输入学生姓名" name="studentname" value="${stname}" class="searchInput"/>
                </div>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th width="4%"><input type="checkbox" id="choseAll"/></th>
                        <th width="6%">序号</th>
                        <th width="10%">姓名</th>
                        <th width="6%">性别</th>
                        <th width="20%">学籍号</th>
                        <th width="13%">班级</th>
                        <th width="15%">状态</th>
                        <th width="18%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="student" varStatus="status">
                        <tr>
                            <td>
                                <input name="chose" class="chose" type="checkbox" value="${student.id}"/>
                            </td>
                            <td>
                                    ${status.index + 1 +(pageInfo.pageNum-1)*10}
                            </td>
                            <td>
                                    ${student.xsxm}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${student.xsxb==1}">
                                        男
                                    </c:when>
                                    <c:when test="${student.xsxb==2}">
                                        女
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                    <%--${student.xjh}--%>
                                    ${student.xh}
                            </td>
                            <td>
                                <c:forEach items="${njList}" var="nj">
                                    <c:if test="${student.nj == nj.key}">${nj.value}</c:if>
                                </c:forEach>${student.className}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${student.status==0}">
                                        在籍在校
                                    </c:when>
                                    <c:when test="${student.status==1}">
                                        在籍离校
                                    </c:when>
                                    <c:when test="${student.status==2}">
                                        在校不在籍
                                    </c:when>
                                    <c:when test="${student.status==3}">
                                        不在籍不在校
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                    <span
                            onclick="openDialog('修改','${ctx}/student/edit?id=${student.id}','900px','650px');">编辑</span>
                                <span
                                        onclick="alertTips('400px','200px','删除学生','确定要删除${student.xsxm}吗？','studentDelete(\'${student.id}\')')"> 删除 </span>
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


<form id="submit-form" method="post" action="${ctx}/class/index">
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
        var stname = $(".searchInput").val();
        $("input[name='stname']").val(stname);
        $("form").submit();
    });

    <c:if test="${pageInfo.pages != 0}">
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

    function studentDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/student/delete", {
            studentId: id
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除

    }

    function ifChoose() {
        if ($('input:checkbox[name=chose]:checked').length > 0) {
            alertTips('400px', '200px', '删除学生', '确定要删除选中的' + $('input:checkbox[name=chose]:checked').length + '位学生吗？', 'choose()')
        }
        else {
            layer.msg("请选择学生")
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
        $.post("${ctx}/student/multiDelete", {
            students: spCodesTemp
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg(retJson.msg);
            else layer.msg("删除失败");
        }, "json");

        setTimeout(function () {
            window.location.reload();
        }, 1000);//删除
    }

    function exportStu() {
        var spCodesTemp = "";
        $('input:checkbox[name=chose]:checked').each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).val();
            } else {
                spCodesTemp += ("," + $(this).val());
            }
        });
        <%--window.location.href="${ctx}/class/export?students="+spCodesTemp;--%>
        /* if ($('input:checkbox[name=chose]:checked').length > 0) {
         openDialog('导出' + $('input:checkbox[name=chose]:checked').length + '位学生数据', '${ctx}/class/stuexport?students=' + spCodesTemp, '1000px', '800px');
         }
         else {
         layer.msg("请选择学生");
         }*/

        if ($('input:checkbox[name=chose]:checked').length > 0) {
            openDialog('导出' + $('input:checkbox[name=chose]:checked').length + '位学生数据', '${ctx}/class/stuexport?students=' + spCodesTemp, '1000px', '800px');
        }
        else {
            openDialog('导出${pageInfo.total}位学生数据', '${ctx}/class/stuexport?students='+encodeURI(encodeURI("${searchParam}")), '1000px', '800px');
        }

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
            form.attr("action","${ctx}/class/student/error/export");
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

