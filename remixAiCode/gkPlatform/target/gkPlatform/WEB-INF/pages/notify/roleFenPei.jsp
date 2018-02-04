<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>通知公告</title>
    </head>
<body>

<%@ include file="../common/sonHead/notifyHead.jsp" %>

<main class="container">
    <!--角色分配-->
    <div  id="rolls-distribute">
        <aside class="col-xs-3">
            <div class="tree-menu">
                <div class="widget-main padding-8">
                   <%-- <div id="tree3" class="tree tree-selectable"></div>--%>
                    <ul id="tree1"  class="ztree"></ul>
                </div>
            </div>
        </aside>
        <main class="col-sm-9">
            <div class="rolls-distribute-add">
                <button onclick="openDialog('添加用户','${ctx}/renshi/roleuser/add?roleId=${currentRole}','500px','402px');">添加用户</button>
            </div>
            <table class="table-responsive">
                <thead>
                <tr>
                    <th width="6%">序号</th>
                    <th width="27%">姓名</th>
                    <th width="10%">用户名</th>
                    <th width="16%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${teacherList}" var="teacher" varStatus="status">
                    <tr>
                        <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.account}</td>
                        <td onclick="alertTips(400,222,'删除用户','确定要删除该用户吗？删除后，他将不再是管理员','deleteSure(\'${teacher.id}\',\'${currentRole}\')')"><span>删除</span></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <div class="fenye">
                <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    <div class="fenYDetail">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
                </c:if>
                <div class="fenY2" id="fenY2">
                </div>
            </div>
        </main>
    </div>
</main>

<script>
    activeMenu("tzjsmenu",0);
    $(function() {
        <c:if test="${pageInfo.pages != 0}">
        $(".fenY2").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/notify/role/index?pageNum="+p+"&pageSize=10";
            }
        });
        </c:if>

        $(".node_name").click(function () {
            window.location.href="${ctx}/notify/role/index?pageSize=10&roleId="+$(this).attr("menuId");
        });

        $(".gotoPage").click(function (){
            var pageNum = $(".go").val();
            if (pageNum <= 0 || pageNum >${pageInfo.pages}){
                layer.msg("请输入正确的页码")
            } else {
                window.location.href = "${ctx}/notify/role/index?pageNum="+$(".go").val()+"&pageSize=10";
            }
        });
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
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onClick : clickTree
        }
    };
    var zNodes1 =[
        {id:0, pId:-1, name:"通知公告", open:true},
        <c:forEach items='${roleList}' var='role'>
        {id:"${role.id}", pId:"0", name:"${role.name}", open:false},
        </c:forEach>
    ];


    function clickTree(event, treeId, treeNode, clickFlag) {
        var zTree = $.fn.zTree.getZTreeObj("tree1");
        zTree.expandNode(treeNode);
        /*alert("您点击了：treeId:" + treeId + "   name:" + treeNode.name + "level:"
         + treeNode.level + "   tid:" + treeNode.tId + "   parentTId:"
         + treeNode.parentTId + "   children:" + treeNode.children);
         */

    }

    $.fn.zTree.init($("#tree1"), setting, zNodes1);
    /*z-tree*/

    function setFontCss(treeId, treeNode) {
        if (treeNode.id == "${currentRole}")
            return {
                'padding-top':' 0','background-color': '#def7f5','color': 'black','height': '25px','opacity': '.8','width': '86%'
            };
        else return {'font-weight': 'normal', color: 'black'};
    }

    function  deleteSure(userId,roleId) {
        closeAlertDiv();
        $.post("${ctx}/notify/roleuser/delete",{
            userId:userId,
            roleId:roleId,
        },function(retJson){

        },"json");
        window.location.reload();
    }
</script>
</body>


</html>