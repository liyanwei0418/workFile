<%@ page import="cn.gukeer.platform.common.ConstantUtil" %>
<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学生管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<main class="container">
    <!--学生管理-->
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>
        <main class="col-xs-9" id="stu-num-manage">
            <div class="stu-num-manage-menu">
                <ul>
                    <li><a href="#" data="datamaster" id="master">班主任</a></li>
                    <li><a href="#" data="datacourse" id="course">任课教师</a></li>
                </ul>
            </div>
            <section id="datamaster" class="row" style="display: none;padding:0 15px;margin: -15px">
                <div class="search-box">
                    <div class="roll-operation">
                        <button class="roll-addNew"
                                onclick="openDialog('添加','${ctx}/class/teacherarrangement/add?which=1&focusNode=${focusNode}','1000px','700px');"/>
                        添加</button>
                        <button class="roll-delete" onclick="ifChoose(1)">删除</button>
                    </div>
                </div>
                <div>
                    <table>
                        <thead>
                        <style>table th:nth-child(2), table td:nth-child(2), table th:nth-child(1), table td:nth-child(1) {
                            text-align: center;
                        }</style>
                        <tr>
                            <th width="2%"><input type="checkbox" id="choseAllMaster"/></th>
                            <th width="4%">序号</th>
                            <th width="9%">姓名</th>
                            <th width="9%">教职工编号</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${masterInfo.list}" var="teacher" varStatus="status">
                            <tr>
                                <td>
                                    <input name="chose" class="choseMaster" type="checkbox" value="${teacher.id}"/>
                                </td>
                                <td>
                                        ${status.index + 1 +(masterInfo.pageNum-1)*10}
                                </td>
                                <td>
                                        ${teacher.name}
                                </td>
                                <td>
                                        ${teacher.no}
                                </td>

                                <td>
                                <span style="color: red"
                                      onclick="alertTips(400,222,'删除班级','确定要删除吗？',' delref(1,\'${teacher.id}\')')"> 删除 </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="fenye">
                    <c:if test="${gukeer:notEmptyString(masterInfo.pages)}">
                        <div class="fenyDetail">共${masterInfo.total}条记录，本页${masterInfo.size}条</div>
                    </c:if>
                    <div class="fenY" id="fenY1">
                    </div>
                </div>
            </section>
            <section id="datacourse" class="row" style="display: none;padding:0 15px;margin: -15px">
                <div class="search-box">
                    <div class="roll-operation">
                        <button class="roll-addNew"
                                onclick="openDialog('添加','${ctx}/class/teacherarrangement/add?which=0&focusNode=${focusNode}','1000px','700px');"/>
                        添加</button>
                        <button class="roll-delete" onclick="ifChoose(0)">删除</button>
                    </div>
                </div>
                <div>
                    <table>
                        <thead>
                        <style>table th:nth-child(2), table td:nth-child(2), table th:nth-child(1), table td:nth-child(1) {
                            text-align: center;
                        }</style>
                        <tr>
                            <th width="2%"><input type="checkbox" id="choseAllCourse"/></th>
                            <th width="4%">序号</th>
                            <th width="9%">姓名</th>
                            <th width="9%">教职工编号</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${courseInfo.list}" var="teacher" varStatus="status">
                            <tr>
                                <td>
                                    <input name="chose" class="normalCourseBtn" type="checkbox" value="${teacher.id}"/>
                                </td>
                                <td>
                                        ${status.index + 1 +(courseInfo.pageNum-1)*10}
                                </td>
                                <td>
                                        ${teacher.name}
                                </td>
                                <td>
                                        ${teacher.no}
                                </td>

                                <td>
                                <span style="color: red"
                                      onclick="alertTips(400,222,'删除班级','确定要删除吗？',' delref(0,\'${teacher.id}\')')"> 删除 </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="fenye">
                    <c:if test="${gukeer:notEmptyString(courseInfo.pages)}">
                        <div class="fenyDetail">共${courseInfo.total}条记录，本页${courseInfo.size}条</div>
                    </c:if>
                    <div class="fenY" id="fenY2">
                        <%--<div class="fenY" id="fenY2">--%>
                    </div>
                </div>
            </section>
        </main>
    </div>

</main>


<form id="submit-form" method="post" action="${ctx}/class/teacherarrangement/index">
    <input type="hidden" name="focusNode" value="${focusNode}">
    <input type="hidden" name="nodeList" value='${nodeList}'>
    <input type="hidden" name="which" value='${which}'>
    <input type="hidden" name="pageNumCourse" value="${pageNumCourse}">
    <input type="hidden" name="pageNumMaster" value="${pageNumMaster}">
    <input type="hidden" name="pageSizeCourse" value="${pageSizeCourse}">
    <input type="hidden" name="pageSizeMaster" value="${pageSizeMaster}">
</form>

<script>
    activeMenu("teaManMenu",0);

    /* 初始化分页 */
    $(function () {
        if (${gukeer:emptyString(which)} || ${which == 1}) {
            $('.stu-num-manage-menu a').removeClass('active');
            $("#master").addClass('active');
            var data = $("#master").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        } else {
            $('.stu-num-manage-menu a').removeClass('active');
            $("#course").addClass('active');
            var data = $("#course").attr('data');
            var sections = $('#' + data);
            $('#' + sections[0].id).show();
            $('#' + sections[0].id).siblings('section').hide();
        }

        <c:if test="${masterInfo!=null&&masterInfo.pages != 0}">
        $("#fenY1").createPage({
            pageCount:${masterInfo.pages},//总页数
            current:${masterInfo.pageNum},//当前页面
            backFn: function (p) {
                <%--window.location.href = "${ctx}/class/teacherarrangement/index?pageNumMaster=" + p + "&pageSizeMaster=10"+"$focusNode="+'${focusNode}';--%>
                $("input[name='which']").val(1);
                $("input[name='pageSizeMaster']").val(10);
                $("input[name='pageNumMaster']").val(p);
                <%--$("input[name='$focusNode']").val(${focusNode});--%>
                $("form").submit();
            }
        });
        $('#fenY1goto').click(function () {
            var p = $('#fenY1go').val();
            if (p <= 0 || p >${masterInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                <%--window.location.href = "${ctx}/class/teacherarrangement/index?pageNumMaster=" + p + "&pageSizeMaster=10"+"$focusNode="+'${focusNode}';--%>
                $("input[name='which']").val(1);
                $("input[name='pageSizeMaster']").val(10);
                $("input[name='pageNumMaster']").val(p);
                <%--$("input[name='$focusNode']").val(${focusNode});--%>
                $("form").submit();
            }
        })
        </c:if>

        <c:if test="${courseInfo!=null&&courseInfo.pages != 0}">
        $("#fenY2").createPage({
            pageCount:${courseInfo.pages},//总页数
            current:${courseInfo.pageNum},//当前页面
            backFn: function (p) {
                <%--window.location.href = "${ctx}/class/teacherarrangement/index?pageNumCourse=" + p + "&pageSizeCourse=10&which=2"+"$focusNode="+'${focusNode}';--%>
                $("input[name='which']").val(2);
                $("input[name='pageSizeCourse']").val(10);
                $("input[name='pageNumCourse']").val(p);
                <%--$("input[name='$focusNode']").val(${focusNode});--%>
                $("form").submit();
            }
        });
        $('#fenY2goto').click(function () {
            var p = $('#fenY2go').val();
            if (p <= 0 || p >${courseInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                <%--var name = $("input[name='studentname']").val();--%>
                <%--window.location.href = "${ctx}/class/teacherarrangement/index?pageNumCourse=" + p + "&pageSizeCourse=10&which=2";--%>
                <%--window.location.href = "${ctx}/class/teacherarrangement/index?pageNumCourse=" + p + "&pageSizeCourse=10&which=2"+"$focusNode="+'${focusNode}';--%>
                $("input[name='which']").val(2);
                $("input[name='pageSizeCourse']").val(10);
                $("input[name='pageNumCourse']").val(p);
                <%--$("input[name='$focusNode']").val(${focusNode});--%>
                $("form").submit();
            }
        })
        </c:if>

    });


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
//                onExpand: zTreeOnExpand,
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
        $("input[name='nodeList']").val(JSON.stringify($.fn.zTree.getZTreeObj("tree1").getNodes()));
    });


    function setFontCss(treeId, treeNode) {
        if (treeNode.id == nowfocus)//{
//            $().addClass('focusNode')
//        }

            return {
                'padding-top': ' 0',
                'background-color': '#def7f5',
                'color': 'black',
                'height': '25px',
                'opacity': '.8',
                'width': '86%'
            };
    }
    //   }
    ;

    function onNodeClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        var nowId = treeNode.id;
        if (nowId.indexOf("banji") >= 0) {
            nowfocus = nowId;
            $("input[name='focusNode']").val(nowfocus);
            $("input[name='which']").val(1);
            $("input[name='nodeList']").val(JSON.stringify(zTree.getNodes()));
            $("form").submit();
        }
        zTree.expandNode(treeNode);
    }

    var allMasterBtn = $('#choseAllMaster');
    var normalMasterBtn = $('.choseMaster');
    //    console.log(allBtn.checked);
    $(allMasterBtn).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalMasterBtn.length; i++) {
                normalMasterBtn[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalMasterBtn.length; i++) {
                normalMasterBtn[i].checked = false;
            }
        }
    });

    var allCourseBtn = $('#choseAllCourse');
    var normalCourseBtn = $('.normalCourseBtn');
    //    console.log(allBtn.checked);
    $(allCourseBtn).click(function () {
        if (this.checked == true) {
            for (var i = 0; i < normalCourseBtn.length; i++) {
                normalCourseBtn[i].checked = true;
            }
        } else {
            for (var i = 0; i < normalCourseBtn.length; i++) {
                normalCourseBtn[i].checked = false;
            }
        }
    })


    function ifChoose(which) {
        if (which == 1) {
            if ($('input:checkbox[class=choseMaster]:checked').length > 0) {
                alertTips('400px', '200px', '删除班主任安排', '确定要删除选中的' + $('input:checkbox[class=choseMaster]:checked').length + '个班主任教师安排吗？', 'choose(1)')
            }
            else {
                layer.msg("请选择教师")
            }
        } else if (which == 0) {
            if ($('input:checkbox[class=normalCourseBtn]:checked').length > 0) {
                alertTips('400px', '200px', '删除任课教师安排', '确定要删除选中的' + $('input:checkbox[class=normalCourseBtn]:checked').length + '个任课教师安排吗？', 'choose(0)')
            }
            else {
                layer.msg("请选择教师")
            }
        }
    }

    function choose(which) {
        closeAlertDiv();
        var spCodesTemp = "";
        if (which == 1) {
            $('input:checkbox[class=choseMaster]:checked').each(function (i) {
                if (0 == i) {
                    spCodesTemp = $(this).val();
                } else {
                    spCodesTemp += ("," + $(this).val());
                }
            });
        } else if (which == 0) {
            $('input:checkbox[class=normalCourseBtn]:checked').each(function (i) {
                if (0 == i) {
                    spCodesTemp = $(this).val();
                } else {
                    spCodesTemp += ("," + $(this).val());
                }
            });
        }
        delref(which, spCodesTemp);
    }


    function delref(which, id) {
        var classId = "${focusNode}";
        classId = classId.substring("banji".length);
        $.post("${ctx}/class/teacherarrangement/delete", {
            teacherId: id,
            classId: classId,
            which: which
        }, function (retJson) {
            if (retJson.code == '0')
                layer.msg("删除成功");
            else layer.msg("删除失败");
        }, "json");


        setTimeout(function () {
            if (which == 1) {
                refresh(1)
            } else {
                refresh(0);
            }
        }, 1000);//删除
    }

    function refresh(which) {
        $("input[name='which']").val(which);
        $("form").submit();
    }
</script>

</body>
</html>