<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>区级人事管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<style>
    .add-containt button {
        height: 30px;
        padding: 0 15px;
        font-size: 14px;
    }

    .ztree * {
        font-size: 14px;
    }
</style>
<%@ include file="../common/sonHead/qujiRenShiHead.jsp" %>

<main class="container">
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <div class="add-containt">
                <button onclick="openDialog('新增部门','${ctx}/renshi/rsbumen/add','550px','350px');">新增部门</button>
            </div>
            <ul id="tree1" class="ztree"></ul>
        </aside>
        <main class="col-xs-9" id="bm-manage">
            <c:if test="${gukeer:notEmptyString(currentDepart.id)}">
                <div class="stu-num-manage-menu">
                    <ul>
                        <li><a href="#" data="generated" class="active">部门人员</a></li>
                        <li><a href="#" data="not-generate">部门信息</a></li>
                    </ul>
                </div>
            </c:if>
            <section id="generated">
                <div class="search-box">
                    <c:if test="${gukeer:notEmptyString(currentDepart.id)}">

                        <%--<p><button onclick="openDialog('新增人员','${ctx}/renshi/rsbumen/teacher/add?departId=${currentDepart.id}','550px','450px')">添加</button></p>--%>
                        <p>
                            <button onclick="openDialog('新增人员','${ctx}/renshi/teacher/many/add?departId=${currentDepart.id}','770px','700px')">
                                添加
                            </button>
                        </p>
                    </c:if>

                    <c:if test="${gukeer:emptyString(currentDepart.id)}">
                        <div class="roll-research">
                            <input type="hidden" id="searchHidden" value="${teacherName}">
                            <button onclick="searchTeacher()"></button>
                            <input class="searchInput" id="searchTeacher" type="text" placeholder="请输入职工姓名"/>
                        </div>
                    </c:if>
                </div>
                <div>
                    <table class="normal">
                        <thead>
                        <tr>
                            <th width="%">序号</th>
                            <th width="%">姓名</th>
                            <th width="%">性别</th>
                            <th width="%">职工编号</th>
                            <th width="%">所属部门</th>
                            <th width="18%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${teacherViewList}" var="teacher" varStatus="status">
                            <tr>
                                <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                                <td>${teacher.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${teacher.gender==1}">
                                            男
                                        </c:when>
                                        <c:when test="${teacher.gender==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${teacher.no}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${gukeer:notEmptyString(teacher.departName)}">
                                            ${teacher.departName}
                                        </c:when>
                                        <c:otherwise>
                                            无
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <span class="alter"
                                          onclick="openDialog('修改部门','${ctx}/renshi/rsbumen/update?teacherId=${teacher.id}','500px','300px');">修改部门</span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="fenye">
                    <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                        <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                    </c:if>
                    <div class="fenY" id="fenY">
                    </div>
                </div>
            </section>
            <section id="not-generate">
                <div id="column-manage-menu" style="display: block;">
                    <div class="column-manage-operation">
                        <button class="column-manage-edit"
                                onclick="openDialog('编辑部门','${ctx}/renshi/rsbumen/add?departId=${currentDepart.id}','550px','450px')">
                            编辑
                        </button>
                        <button class="column-manage-delete"
                                onclick="alertTips(400,222,'删除部门','确定要删除${currentDepart.name}吗？','buMenDelete(\'${currentDepart.id}\')')">
                            删除
                        </button>
                    </div>
                    <table>
                        <tbody>
                        <tr>
                            <td>部门名称</td>
                            <td>${currentDepart.name}</td>
                        </tr>
                      <%--  <tr>
                            <td>部门领导</td>
                            <td>${currentDepart.masterName}</td>
                        </tr>--%>
                        <tr>
                            <td>部门编号</td>
                            <td>${currentDepart.no}</td>
                        </tr>
                        <tr>
                            <td>成立时间</td>
                            <td>${gukeer:millsToyyyyMMdd(currentDepart.createDate)}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </section>

        </main>
    </div>
    <script type="text/javascript">
        activeMenu("quji", 3);

        var name = $("#searchHidden").val();

        /* 初始化分页 */
        $(function () {
            <c:if test="${pageInfo.pages != 0 }">
            $(".fenY").createPage({
                pageCount:${pageInfo.pages},//总页数
                current:${pageInfo.pageNum},//当前页面
                backFn: function (p) {
                    if (name == "undefined") {
                        name = "";
                    }
                    window.location.href = "${ctx}/area/bumen/index?pageNum=" + p + "&departmentId=${currentDepart.id}&teacherName=" + encodeURI(encodeURI(name));
                }
            });
            </c:if>
            //搜索框关键字回显
            $("#searchTeacher").val($("#searchHidden").val());

            $(".gotoPage").click(function () {
                var pageNum = $(".go").val();
                if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                    layer.msg("请输入正确的页码")
                } else {
                    if (name == "undefined") {
                        name = "";
                    }
                    window.location.href = "${ctx}/area/bumen/index?pageNum=" + $(".go").val() + "&departmentId=${currentDepart.id}&teacherName=" + encodeURI(encodeURI(name));
                }
            });

        })

        /*z_tree*/
        var zTree;
        var demoIframe;

        var setting = {
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
                onClick: clickTree
            }
        };
        var zNodes1 = [
            {id: 0, pId: -9999, name: "部门管理", open: true},
            <c:forEach items='${departmentList}' var='department'>
            {id: "${department.id}", pId: "${department.parentId}", name: "${department.name}", open: true},
            </c:forEach>

        ];
        function clickTree(event, treeId, treeNode, clickFlag) {
            var zTree = $.fn.zTree.getZTreeObj("tree1");
            zTree.expandNode(treeNode);
        }

        $.fn.zTree.init($("#tree1"), setting, zNodes1);
        /*z-tree*/

        $(".node_name").click(function () {
            window.location.href = "${ctx}/area/bumen/index?departmentId=" + $(this).attr("menuId");
        });

        function renYuanDelete(id) {
            closeAlertDiv();

            $.post("${ctx}/renshi/rsbumen/update/delete", {
                teacherId: id
            }, function (retJson) {
                alert(retJson);
            }, "json");

            setTimeout(function () {
                window.location.reload();
            }, 300);//删除

        }

        function buMenDelete(id) {
            closeAlertDiv();
            if (id != 0) {
                $.post("${ctx}/renshi/bumen/delete", {
                    departmentId: id
                }, function (retJson) {
                    alert(retJson);
                }, "json");
            } else {
                alert("当前部门无法删除");
            }


            setTimeout(function () {
                window.location.href = "${ctx}/area/bumen/index?pageSize=10";
            }, 300);//删除

        }

        function searchTeacher() {
            window.location.href = "${ctx}/area/bumen/index?teacherName=" + encodeURI(encodeURI($("#searchTeacher").val()));
        }

        function setFontCss(treeId, treeNode) {
            if (treeNode.id == "${currentDepart.id}")
                return {
                    'padding-top': ' 0',
                    'background-color': '#def7f5',
                    'color': 'black',
                    'height': '25px',
                    'opacity': '.8',
                    'width': '86%'
                };
            else return {'font-weight': 'normal', color: 'black'};
        }
    </script>
</main>
</body>
</html>