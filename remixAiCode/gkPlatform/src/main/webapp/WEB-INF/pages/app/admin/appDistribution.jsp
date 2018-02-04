<%@ include file="../../common/headerXf.jsp" %>
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
	.layui-layer.layui-layer-iframe.layer-anim {
		top: 150px !important;
	}
	.miaoshu>div p{
		width: 100px !important;
		display: inline-block;
	}
	.miaoshu>div, .lianjie p{
		display: inline-block;
		width:240px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap
	}

</style>

<body>

<%@ include file="../../common/sonHead/schoolHead.jsp" %>
<main class="container" id="ry-manage">
	<div class="search-box">
		<div class="roll-operation">
			<button onclick="openDialog('选择角色','${ctx}/school/role/list?appId=${appId}','500px','600px');">为${appName}选择角色</button>
		</div>
	</div>
	<div>
		<table class="check">
			<thead class="headerTh">
				<th width="15%">该应用已分配角色名称</th>
				<th width="18%">该应用已分配角色标识hasRole</th>
				<th width="20%">该应用已分配角色描述</th>
			</thead>
			<c:forEach items="${appRole}" var="role">
				<tr>
					<td>${role.name}</td>
					<td>${role.roleIdentify}</td>
					<td>${role.remarks}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="fenye" style="width:100%;">
		<div class="fenYDetail">每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
		<div class="fenY" id="fenY">
		</div>
		<%--<div class="fenY2" id="fenY2">--%>
	</div>
</main>

<script type="text/javascript">
    $(function() {
        activeMenuSchool("roll-manage-menu",2);
        $(".fenY").createPage({
            pageCount:${pageInfo.pages},//总页数
            current:${pageInfo.pageNum},//当前页面
            backFn:function(p){
                window.location.href = "";
            }
        });
    })
</script>
</body>

</html>
