<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>区级学籍管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <style>
        .la_input {
            border-left: 2px #54AB37 solid;
            font-size: 14px;
            text-align: center;
            padding-left: 6px;
        }

        #bm-manage {
            padding-left: 30px !important;
        }

        #bm-manage .stu-num-manage-menu ul li a:hover {
            color: #54ab37;
        }

        input[type='text'], select {
            width: 120px;
            height: 28px;
            outline: none;
        }

        .get-info {
            padding-left: 20px;
            cursor: pointer;
            margin-right: 20px;
            color: #54ab37;
            background: url(../../../assetsNew/images/icon-edit.png) no-repeat left 3px;
        }

        #column-manage-menu {
            overflow: hidden;
        }

        #column-manage-menu > div {
            margin: 0 40px 40px 0;

        }

        #column-manage-menu > div > span {
            margin-bottom: 20px;
        }

        select {
            margin-left: 12px;
            margin-right: 25px;
        }

        .left {
            float: left;
            margin-right: 200px;
        }

        .info {
            font-size: 13px;
        }
    </style>
</head>
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
                    <input class="searchInput" id="searchTeacher" type="text" placeholder="请输入学生姓名" value="${stuName}"/>
                </div>

            </div>

            <div class="stu-num-manage-menu">
                <ul>
                    <li><a href="#" data="generated" class="active">学生信息</a></li>
                    <li><a href="#" data="not-generate">学校信息</a></li>
                </ul>
            </div>

            <section id="generated">

                <div>
                    <table class="normal">
                        <thead>
                        <tr>
                            <th width="6%">序号</th>
                            <th width="10%">姓名</th>
                            <th width="10%">性别</th>
                            <th width="15%">学籍号</th>
                            <th width="20%">学校</th>
                            <th width="20%">班级</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${stuList}" var="stu" varStatus="status">
                            <tr>
                                <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                                <td>${stu.xsxm}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${stu.xsxb==1}">
                                            男
                                        </c:when>
                                        <c:when test="${stu.xsxb==2}">
                                            女
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${stu.xh}</td>
                                <td>${stu.schoolName}</td>
                                <td>${stu.sectionName}${stu.nj}年级${stu.className}</td>
                                <td><span class="get-info"
                                          onclick="openDialogView('查看','${ctx}/student/edit?id=${stu.id}&type=1','900px','650px');">查看</span>
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
                    <%--${chooseSchool}------
                    ${schoolTypeList}----
                    ${sectionList}
                   --%>
                    <div>
                        <span class="la_input">基本信息</span>
                        <div class="info" style="overflow: hidden;">
                            <div class="left">
                                校名：${chooseSchool.name}<br><br>
                                传真：${chooseSchool.fax}<br><br>
                                英文名：${chooseSchool.ename}<br><br>
                                邮箱：${chooseSchool.email}
                            </div>
                            <div class="left">
                                校长：${chooseSchool.master}<br><br>
                                邮编：${chooseSchool.zipCode}<br><br>
                                地址：${chooseSchool.address}<br><br>
                                电话：${chooseSchool.phone}
                            </div>
                        </div>
                    </div>
                    <div>
                        <span class="la_input">学段信息</span>
                        <div class="info">
                            已启用学段：
                            <c:forEach items="${sectionList}" var="section">
                                ${section.name}&nbsp;&nbsp;
                            </c:forEach>
                        </div>
                    </div>
                    <div>
                        <span class="la_input">校区信息</span>
                        <div class="info">
                            已设校区：
                            <c:forEach items="${schoolTypeList}" var="schoolType">
                                ${schoolType.name}&nbsp;&nbsp;
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>

        </main>
    </div>
    <script type="text/javascript">
        activeMenu("stuInfoMenu", 0);
        var xdId = $("select[name='xdId']").val();
        var njId = $("select[name='njId']").val();
        var bjId = $("select[name='bjId']").val();
        var name = $("#searchTeacher").val();
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
                    'padding-top': ' 0',
                    'background-color': '#def7f5',
                    'color': 'black',
                    'height': '25px',
                    'opacity': '.8',
                    'width': '86%'
                };
            else return {'font-weight': 'normal', color: 'black'};
        }


        function jump(choose, xdId, njId, bjId, stuName, pageNum) {

            var url = "${ctx}/area/class/stuinfo/index?choose=" + choose;
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