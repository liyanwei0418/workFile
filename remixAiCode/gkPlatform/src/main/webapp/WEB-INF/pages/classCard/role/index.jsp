<%@ include file="../../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>班牌管理</title>
	<link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
	<link rel="stylesheet" href="${ctxStaticNew}/css/classCard.min.css"/>
</head>
<style>
	nav > .container .roll-manage-menu .down-a:after {
		content: '';
		display: inline-block;
		 width: 0;
		 height: 0;
		 background: none;
		 margin-left: 0;
	}
	.down-a {
		 display: inline-block;
	}
	#role a {
		width: auto;
	}
	.fenye .fenYDetail{
		float: left !important;
	}
	.nav-menu li a:hover{
		color:#fff !important;
	}
</style>
<body>
<%@ include file="../../common/sonHead/classCardHead.jsp" %>
<main class="container">
	<div class="row" id="js-manage">
		<aside class="col-xs-3">
			<ul id="tree1"  class="ztree"></ul>
		</aside>
		<main class="col-sm-9">
			<div class="rolls-distribute-add">
				<button class="add-btn" onclick="openDialog('添加用户','${ctx}/classcard/roleuser/add?roleId=${currentRole}','500px','352px')">添加用户</button>
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
						<td><span onclick="alertTips(400,202,'删除用户','确定要删除该用户吗？删除后，他将不再是管理员','deleteSure(\'${teacher.id}\',\'${currentRole}\')')">删除</span></td>
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
<form id="submit-form" method="post" action="${ctx}/classcard/role/index">
	<input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
	<input type="hidden" name="roleId" value="${currentRole}">
</form>

<script>
	activeMenu("role",0);

	$(function() {
		<c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
		$(".fenY").createPage({
			pageCount:${pageInfo.pages},//总页数
			current:${pageInfo.pageNum},//当前页面
			backFn:function(p){
				//window.location.href = "${ctx}/role/index?appId=${appId}&pageNum="+p+"&pageSize=10";
               // /platform/classCard/role/index?appId=a7a0c7724b38c52fdc73767070ccf6ca
                $("input[name='pageNum']").val(p);
                $("form").submit();
			}
		});

		$(".gotoPage").click(function (){
			var pageNum = $(".go").val();
			if (pageNum <= 0 || pageNum > ${pageInfo.pages}){
				layer.msg("请输入正确的页码")
			} else {
				//window.location.href = "${ctx}/role/index?pageNum="+$(".go").val()+"&pageSize=10";
                $("input[name='pageNum']").val(p);
                $("form").submit();
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
		{id:0, pId:-1, name:"班牌管理", open:true},
		<c:forEach items='${roleList}' var='role'>
		{id:"${role.id}", pId:"0", name:"${role.name}", open:false},
		</c:forEach>
	];

	function clickTree(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("tree1");
		zTree.expandNode(treeNode);
	}

	$.fn.zTree.init($("#tree1"), setting, zNodes1);
	/*z-tree*/
	$(".node_name").click(function () {
		window.location.href="${ctx}/classcard/role/index?pageSize=10&roleId="+$(this).attr("menuId");
	});

	function  deleteSure(userId,roleId) {
		closeAlertDiv();
		$.post("${ctx}/classcard/roleUser/delete",{
			userId:userId,
			roleId:roleId,
		},function(retJson){

		    if(retJson.code=0){
                layer.msg('删除成功', {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    window.location.reload();
                });
			}else{
		        layer.msg(retJson.msg);
			}
		},"json");
		setTimeout(function () {
            window.location.reload();
        },2000)


	}

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