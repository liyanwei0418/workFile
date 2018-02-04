<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>平台管理</title>

    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    td span {
        color: #54ab37;
    }

</style>


<body>

<%@ include file="../common/sonHead/schoolHead.jsp" %>

<main class="container" id="ry-manage">
    <div class="search-box">
        <div class="roll-operation">
            <button class="roll-add" onclick="openDialog('新增角色','${ctx}/school/role/edit','800px','600px');">新增</button>

        </div>
        <div class="roll-research" style="float: right;">
            <button class="summitButton" id="search"></button>
            <input type="hidden" id="searchHidden" value="">
            <input class="searchInput" name="roleName" type="text" placeholder="请输入角色名称"/>
        </div>
    </div>
    <div>
        <table class="check">
            <thead>
            <tr>
                <th width="6%">角色名称</th>
                <th width="8%">角色标识</th>
                <th width="18%">角色描述</th>
                <th width="12%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roleList}" var="role">
                <tr>
                    <td>${role.name}</td>
                    <td>${role.roleIdentify}</td>
                    <td>${role.remarks}</td>
                    <td>
                        <span onclick="openDialog('编辑角色','${ctx}/school/role/edit?id=${role.id}','800px','600px');">编辑</span>
                        <span class="editperson" onclick="openDialog('权限设置','${ctx}/school/permission/view?roleId=${role.id}','500px','600px');">编辑权限</span>
                        <span onclick="alertTips(400,222,'删除角色','确定要删除当前角色吗，删除后，相关的用户的权限也会被删除,确定吗？','permissionDelete(\'${role.id}\')')">删除</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!--分页按钮组  -->
        <div class="fenye" style="width:100%;">
            <div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
            <div class="fenY" id="fenY">
            </div>
            <%--<div class="fenY2" id="fenY2">--%>
        </div>
    </div>
</main>

<script type="text/javascript">
    $(function() {
        activeMenuSchool("roll-manage-menu",1);

        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "${ctx}/school/permissionMan?pageNum="+p;
            }
        });

        /*查询搜索*/
        $(".summitButton").click(function () {
            var roleName=$("input[name='roleName']").val();
            window.location.href="${ctx}/school/permissionMan?roleName="+encodeURI(encodeURI(roleName));


        });

    })


    function  permissionDelete(id) {
        closeAlertDiv();
        $.post("${ctx}/school/role/delete",{
            roleId:id,
        },function(retJson){
            //alert(retJson);
        },"json");
        setTimeout(function(){window.location.reload();}, 100);//删除的数据越多，延时要越长。否则：刷新页面的时候，数据还没删完..
    }

    function roleDetails(id) {
        $.ajax({
            type: "POST",
            url: "${ctx}/school/role/view",
            data: {roleId:id},
            dataType: "json",
            success: function (result) {
                alert(JSON.stringify(result));
            },
            error:function(response){
                alert("error");
            }

        });

    }

</script>
</body>
</html>
