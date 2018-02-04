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
    #ry-manage .search-box .roll-research {
        vertical-align: middle;
        float: right;
    }

    #ry-manage .search-box, #zh-manage .search-box {
        margin-top: 0 !important;
    }
</style>
<body>

<%@ include file="../common/sonHead/qujiRenShiHead.jsp" %>

<main class="container">
    <div class="row" id="js-manage">
        <aside class="col-xs-3">
            <ul id="tree1" class="ztree"></ul>
        </aside>
        <main class="col-sm-9" id="ry-manage">
            <div class="rolls-distribute-add search-box ">
                <button class="add-btn" onclick="exportStu()">
                    导出
                </button>

                <div class="roll-research roll-search">
                    <input type="hidden" id="searchHidden" value="${teacherName}">
                    <button style="width: 32px" onclick="searchTeacher()"></button>
                    <input class="searchInput" value="${teacherName}" id="searchTeacher" type="text"
                           placeholder="请输入职工姓名"/>
                </div>
            </div>
            <table class="table-responsive">
                <thead>
                <tr>
                    <th width="4%"><input class="rsCheck headerCheck" type="checkbox"/></th>
                    <th width="6%">序号</th>
                    <th width="10%">姓名</th>
                    <th width="5%">性别</th>
                    <th width="15%">所属学校</th>
                    <th width="15%">教职工编号</th>
                    <th width="15%">办公室电话</th>
                    <th width="15%">手机号码</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                    <tr>
                        <td><input class="rsCheck" name="lanmuCheck" id="${teacher.id}" type="checkbox"/></td>
                        <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                        <td>${teacher.name}</td>
                        <td>
                            <c:if test="${teacher.gender ==1}">男</c:if>
                            <c:if test="${teacher.gender ==2}">女</c:if>
                        </td>
                        <td>${teacher.schoolName}</td>
                        <td>${teacher.no}</td>
                        <td>${teacher.bgsdh}</td>
                        <td>${teacher.mobile}</td>
                        <td>
                            <span onclick="openDialogView('修改信息','${ctx}/renshi/renyuan/edit?id=${teacher.id}&type=1','900px','600px')">查看</span>
                            <span></span>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY">

                </div>
            </div>
        </main>
    </div>
</main>
<script>
    activeMenu("xiaoji", 1);

    var menuId = "${choose}";
    var teacherName = encodeURI(encodeURI($("#searchTeacher").val()));
    $(function () {
        <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn: function (p) {
                window.location.href = "${ctx}/area/school/person/index?teacherName=" + teacherName + "&choose=" + menuId + "&pageNum=" + p;
            }
        });

        $(".gotoPage").click(function () {
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum > ${pageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/area/school/person/index?teacherName=" + teacherName + "&choose=" + menuId + "&pageNum=" + $(".go").val();
            }
        });
        </c:if>

    });

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
            window.location.href = "${ctx}/area/school/person/index?choose=" + menuId;
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

    function searchTeacher() {
        var teacherName = $("#searchTeacher").val();
        window.location.href = "${ctx}/area/school/person/index?choose=" + menuId + "&teacherName=" + encodeURI(encodeURI(teacherName));
    }

    $(".headerCheck").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true);
        } else {
            $("input[type='checkbox']").prop("checked", false);
        }
    });

    function exportStu() {
        var spCodesTemp = "";
        $("input:checkbox[name='lanmuCheck']:checked").each(function (i) {
            if (0 == i) {
                spCodesTemp = $(this).attr("id");
            } else {
                spCodesTemp += ("," + $(this).attr("id"));
            }
        });
        if ($("input:checkbox[name='lanmuCheck']:checked").length > 0) {
            openDialog('导出' + $('input:checkbox[name=lanmuCheck]:checked').length + '位教师数据', '${ctx}/renshi/moban/export?teachers=' + spCodesTemp + '&chooseSchoolId=${choose}', '1000px', '800px');
        }
        else {
            //layer.msg("请选择教师");
            var teacherName = $("#searchTeacher").val();
            openDialog('导出${pageInfo.total}位教师数据', '${ctx}/renshi/moban/export?teacherName=' + encodeURI(encodeURI(teacherName)) + '&chooseSchoolId=${choose}', '1000px', '800px');
        }
    }

</script>
</body>
</html>