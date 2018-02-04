<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>区级学籍管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<STYLE>
    .get-info {
        padding-left: 20px;
        cursor: pointer;
        margin-right: 20px;
        color: #54ab37;
        background: url(../../../assetsNew/images/icon-edit.png) no-repeat left 3px;
    }

    #bm-manage {
        padding-left: 30px !important;
    }

    select {
        width: 120px;
        height: 28px;
    }

    select {
        margin-left: 12px;
        margin-right: 25px;
    }
</STYLE>
<body>

<%@ include file="../common/sonHead/qujiXuejiHead.jsp" %>

<main class="container">
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <ul id="tree1" class="ztree"></ul>
        </aside>
        <main class="col-xs-9" id="bm-manage">
            <div class="search-box">
                <div style="float: left">
                    <c:if test="${gukeer:notEmptyString(select.xdList)}">
                        学段
                        <select name="xdId">
                            <c:forEach items="${select.xdList}" var="xd">
                                <option value="${xd.id}"
                                        <c:if test="${select.xdChoose eq xd.id}">selected</c:if> >${xd.name}</option>
                            </c:forEach>
                        </select>
                    </c:if>

                    <c:if test="${gukeer:notEmptyString(select.njList)}">
                        年级
                        <select name="njId">
                            <c:forEach items="${select.njList}" var="nj">
                                <option value="${nj.njId}"
                                        <c:if test="${select.njChoose eq nj.njId}">selected</c:if> >${nj.njName}</option>
                            </c:forEach>
                        </select>
                    </c:if>

                    <c:if test="${gukeer:notEmptyString(select.bjList)}">
                        班级
                        <select name="bjId">
                            <c:forEach items="${select.bjList}" var="bj">
                                <option value="${bj.id}"
                                        <c:if test="${select.bj eq bj.id}">selected</c:if> >${bj.name}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </div>
                <div class="roll-research">
                    <button onclick="searchTeacher()"></button>
                    <input class="searchInput" id="searchTeacher" type="text" placeholder="请输入学生/家长姓名"
                           value="${stuName}"/>
                </div>
            </div>

            <section id="generated">
                <div>
                    <table class="normal">
                        <thead>
                        <tr>
                            <th width="10%">序号</th>
                            <th width="10%">家长姓名</th>
                            <th width="10%">学生姓名</th>
                            <th width="20%">学校</th>
                            <th width="10%">年级</th>
                            <th width="10%">班级</th>
                            <th width="20%">学籍号</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="stu" varStatus="status">
                            <tr>
                                <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                                <td>${stu.parentName}</td>
                                <td>${stu.xsxm}</td>
                                <td>${stu.school}</td>
                                <td>${stu.nj}</td>
                                <td>${stu.className}</td>
                                <td>${stu.xh}</td>
                                <td><span class="get-info"
                                          onclick="openDialogView('查看','${ctx}/class/parent/info/add/index?prim=${stu.parentId}','500px','630px');">查看</span>
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
        </main>
    </div>
    <script type="text/javascript">

        activeMenu("parInfoMenu", 3);
        var xdId = $("select[name='xdId']").val();
        var njId = $("select[name='njId']").val();
        var bjId = $("select[name='bjId']").val();
        var name = $("#searchHidden").val();
        var menuId = "${chooseSchool.id}";

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
                    jump(menuId, xdId, njId, bjId, name, p);
                }
            });
            </c:if>
            //搜索框关键字回显
            $("#searchTeacher").val("${stuName}");

            $(".gotoPage").click(function () {
                var pageNum = $(".go").val();
                if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
                    layer.msg("请输入正确的页码")
                } else {
                    if (name == "undefined") {
                        name = "";
                    }
                    jump(menuId, xdId, njId, bjId, name, $(".go").val());
                }
            });
        });

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
            {
                id: "nosearch${currentSchool.id}",
                pId: "${currentSchool.parentId}",
                name: "${currentSchool.name}",
                open: true
            },
            <c:forEach items='${schoolList}' var='school'>
            {id: "${school.id}", pId: "nosearch${school.pid}", name: "${school.name}", open: true},
            <c:forEach items="${school.list}" var="son">
            {id: "${son.id}", pId: "nosearch${son.grade}", name: "${son.name}", open: true},
            </c:forEach>
            </c:forEach>
        ];
        function clickTree(event, treeId, treeNode, clickFlag) {
            var zTree = $.fn.zTree.getZTreeObj("tree1");
            zTree.expandNode(treeNode);
        }

        $.fn.zTree.init($("#tree1"), setting, zNodes1);
        /*z-tree*/

        $(".node_name").click(function () {
            menuId = $(this).attr("menuId");
            if (menuId.indexOf("nosearch") >= 0) {
            } else {
                jump(menuId, null, null, null, null, 1);
            }
        });


        function setFontCss(treeId, treeNode) {
            if (treeNode.id == "${chooseSchool.id}")
                return {
                    'padding-top': '0',
                    'background-color': '#def7f5',
                    'color': 'black',
                    'height': '25px',
                    'opacity': '.8',
                    'width': '86%'
                };
            else return {'font-weight': 'normal', color: 'black'};
        }


        function jump(choose, xdId, njId, bjId, stuName, pageNum) {

            var url = "${ctx}/area/class/parinfo/index?choose=" + choose;
            if (xdId != "" && xdId != null) {
                url += "&xdId=" + xdId;
            }
            if (njId != "" && njId != null) {
                url += "&njId=" + njId;
            }
            if (bjId != "" && bjId != null) {
                url += "&bjId=" + bjId;
            }
            if (stuName != "" && stuName != null) {
                url += "&stuName=" + encodeURI(encodeURI(stuName));
            }
            if (pageNum != "" && pageNum != null) {
                url += "&pageNum=" + pageNum;
            }
            window.location = url;
        }

        $("select").change(function () {
            xdId = $("select[name='xdId']").val();
            njId = $("select[name='njId']").val();
            bjId = $("select[name='bjId']").val();

            if ($(this).attr("name") == 'xdId') {
                njId = null;
                bjId = null;
            }

            if ($(this).attr("name") == 'njId') {
                bjId = null;
            }

            jump(menuId, xdId, njId, bjId, null, 1);
        });

        function searchTeacher() {

            xdId = $("select[name='xdId']").val();
            njId = $("select[name='njId']").val();
            bjId = $("select[name='bjId']").val();
            var stuName = $("#searchTeacher").val();
            jump(menuId, xdId, njId, bjId, stuName, 1);
        }
    </script>
</main>
</body>
</html>