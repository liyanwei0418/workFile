<%@ include file="../common/headerXf.jsp" %>
<%@ page import="cn.gukeer.platform.common.ProjectConfig" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>角色分配</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctxStaticNew}/css/schoolRollManage.min.css"/>
</head>
<body>

<%@ include file="../common/sonHead/xuejiHead.jsp" %>

<div class="row" id="rolls-distribute">
    <main class="container">
        <aside class="col-xs-3">
            <div class="tree1">
                <ul id="tree1" class="ztree"></ul>
            </div>
        </aside>
        <main class="col-sm-9">
            <div class="rolls-distribute-add">
                <button class="add-btn"
                        onclick="openDialog('添加用户',
                                '${ctx}/class/roleUser/add?roleId=${currentRole}&&appId=${appId}','500px','352px');">
                    添加用户
                </button>
            </div>
            <table class="table-responsive">
                <thead>
                <style>table th:nth-child(1),table td:nth-child(1){text-align: center;}</style>
                <tr>
                    <th width="8%">序号</th>
                    <th width="15%">姓名</th>
                    <th width="15%">用户名</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                    <tr>
                        <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.account}</td>
                        <td>
                <span class="deleteA"
                      onclick="alertTips('400px','202px','删除用户','确定要删除该用户吗？删除后，他将不再是管理员','deleteSure(\'${teacher.id}\',\'${currentRole}\')')">删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenyDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY" id="fenY">
                    <%--<input type="text"/>--%>
                </div>
            </div>
        </main>
    </main>
</div>

<script>

    activeMenu("xjjsMenu",0);

    $(function() {
        <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/class/rolemanage/index?pageNum=" + p + "&pageSize=10";
            }
        });

        $(".gotoPage").click(function (){
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum > ${pageInfo.pages}){
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/class/rolemanage/index?pageNum=" + p + "&pageSize=10";
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
        {id: 0, pId: -1, name: "学籍管理", open: true},
        <c:forEach items='${roleList}' var='role'>
        {id:"${role.id}", pId: "0", name: "${role.name}", open: false},
        </c:forEach>
    ];


    function clickTree(event, treeId, treeNode, clickFlag) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        zTree.expandNode(treeNode);
    }

    $.fn.zTree.init($("#tree1"), setting, zNodes1);
    $(".node_name").click(function () {
        window.location.href = "${ctx}/class/rolemanage/index?pageSize=10&roleId=" + $(this).attr("menuId");
    });


    function deleteSure(userId, roleId) {
        closeAlertDiv();
        <%--$.post("${ctx}/class/roleUser/delete", {--%>
        $.post("${ctx}/class/roleUser/delete", {
            userId: userId,
            roleId: roleId,
        }, function (retJson) {

        }, "json");
        setTimeout(function () {
            window.location.reload();
        }, 1000);
    }
    ;

    function setFontCss(treeId, treeNode) {
        if (treeNode.id == "${currentRole}")
            return {
                'padding-top':' 0','background-color': '#def7f5','color': 'black','height': '25px','opacity': '.8','width': '86%'
            };
        else return {'font-weight': 'normal', color: 'black'};
    }
</script>

</body>
</html>