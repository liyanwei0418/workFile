<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>区级人事管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    #bm-manage {
        padding-left: 30px;
    }

    .search-box select {
        width: 120px;
        height: 28px;
        line-height: 28px;
    }

    #stu-manage .stu-num-manage-menu ul li a.active {
        color: #54ab37 !important;
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }

    #stu-manage .stu-num-manage-menu ul li a:hover {
        color: #54ab37 !important;
    }

    select {
        margin-left: 12px;
    }
</style>
<body>

<%@ include file="../common/sonHead/qujiRenShiHead.jsp" %>

<main class="container">
    <div class="row" id="stu-manage">
        <aside class="col-xs-3">
            <ul id="tree1" class="ztree"></ul>
        </aside>
        <main class="col-xs-9" id="bm-manage">
            <div class="search-box">
                <div style="float: left">
                    部门
                    <select name="departId">
                        <option value="0">全部</option>
                        <c:forEach items="${departList}" var="depart">
                            <option value="${depart.id}"
                                    <c:if test="${currentDepart.id eq depart.id}">selected</c:if> >${depart.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="roll-research">
                    <input type="hidden" id="searchHidden" value="${teacherName}">
                    <button onclick="searchTeacher()"></button>
                    <input class="searchInput" id="searchTeacher" type="text" placeholder="请输入职工姓名"/>
                </div>

            </div>

            <div class="stu-num-manage-menu">
                <ul>
                    <li><a href="#" data="generated" class="active">部门人员</a></li>
                    <li><a href="#" data="not-generate">部门信息</a></li>
                </ul>
            </div>

            <section id="generated">

                <div>
                    <table class="normal">
                        <thead>
                        <tr>
                            <th width="10%">序号</th>
                            <th width="20%">姓名</th>
                            <th width="10%">性别</th>
                            <th width="20%">教职工编号</th>
                            <th width="20%">办公室电话</th>
                            <th width="20%">所在部门</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${teacherList}" var="teacher" varStatus="status">
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
                                <td>${teacher.bgsdh}</td>
                                <td>${teacher.departName}</td>
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
        activeMenu("xiaoji", 3);

        var departId = $("select[name='departId']").val();
        var name = $("#searchHidden").val();
        var menuId = "${choose}";

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
                    jump(menuId, "${currentDepart.id}", name, p);
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
                    jump(menuId, "${currentDepart.id}", name, $(".go").val());
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
                jump(menuId, null, null, 1);
            }
        });


        function setFontCss(treeId, treeNode) {
            if (treeNode.id == "${choose}")
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


        function jump(choose, departId, teacherName, pageNum) {
            var url = "${ctx}/area/school/department/index?choose=" + choose;
            if (departId != "" && departId != null) {
                url += "&departId=" + departId;
            }
            if (teacherName != "" && teacherName != null) {
                url += "&teacherName=" + encodeURI(encodeURI(teacherName));
            }
            if (pageNum != "" && pageNum != null) {
                url += "&pageNum=" + pageNum;
            }
            window.location = url;
        }

        $("select").change(function () {
            var departId = $(this).val();
            var teacherName = $("#searchHidden").val();
            jump(menuId, departId, teacherName, 1);
        });


        function searchTeacher() {
            departId = $("select[name='departId']").val();
            var teacherName = $("#searchTeacher").val();
            jump(menuId, departId, teacherName, 1);
        }
    </script>
</main>
</body>
</html>