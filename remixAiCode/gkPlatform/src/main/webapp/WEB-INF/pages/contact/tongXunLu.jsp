<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>通讯录</title>
	<link rel="stylesheet" href="${ctxStaticNew}/css/addressList.min.css"/>
</head>
<style>
	main.container main.col-xs-9 > ul.row li .user-content .user-name h4 span:first-child{
		border-radius: 0;
	}
</style>
<body>

<nav>
	<div class="container">
		<div>通讯录</div>
	</div>
</nav>
<main class="container">
	<div class="row">
		<aside class="col-xs-3">
			<div class="tree-menu">
				<div class="widget-main padding-8">
					<ul id="tree1"  class="ztree"></ul>
				</div>
			</div>
		</aside>
		<main class="col-xs-9">
			<div class="search-box">
				<button class="summitButton" name="" id="searchName"></button>
				<input type="hidden" id="searchHidden" value="${name}" />
				<input class="searchInput" type="text" placeholder="搜索姓名" value="" name="searchName" /> <!--高级搜索-->
			</div>
			<ul class="row">
				<c:forEach items="${teacherViewList}" var="teacherView" varStatus="status">
					<li class="col-xs-4">
						<div class="user-content">
							<div class="user-name">
								<h4>
									<c:if test="${empty teacherView.headUrl}">
										<span><img src="${ctxStaticNew}/images/default_tou.png" alt=""/></span>
									</c:if>
									<c:if test="${not empty teacherView.headUrl}">
										<span><img src="${ctx}/file/pic/show?picPath=${teacherView.headUrl}" alt=""/></span>
									</c:if>
									${teacherView.name}
									<span>${teacherView.departName}</span>
								</h4>
							</div>
							<div class="user-msg">
								<p>办公电话：${teacherView.phone}</p>
								<p>电话：${teacherView.mobile}</p>
								<p>邮箱：${teacherView.email}</p>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			<c:if test="${size != 0}">
				<div class="fenye">
					<c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
						<div class="fenYDetail" style="margin:10px 0 50px 0;">共${pageInfo.total}条记录，本页${pageInfo.size}条</div>
					</c:if>
					<div class="fenY" id="fenY">
					</div>
				</div>
			</c:if>
		</main>
	</div>
</main>

<script type="text/javascript">
	$(".fenY").createPage({
		pageCount:${pageInfo.pages},
		current:${pageInfo.pageNum},
		backFn:function(p){
			var name=$("#searchHidden").val() ;

			var departmentId ="${empty departmentId?0:departmentId}";
			if(name != ""){
				window.location.href = "${ctx}/contact/contact/index?pageNum="+p+"&pageSize=9"+"&name="+encodeURI(encodeURI(name));
			}else if(departmentId != ""){
				window.location.href="${ctx}/contact/contact/index?pageNum="+p+"&pageSize=9&departmentId="+departmentId;
			}else{
				window.location.href = "${ctx}/contact/contact/index?pageNum="+p+"&pageSize=9";
			}
		}
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
		{id:0, pId:-9999, name:"部门管理", open:true},
		<c:forEach items='${departmentList}' var='department'>
		{id:"${department.id}", pId:"${department.parentId}", name:"${department.name}", open:true},
		</c:forEach>
		<%--url:"javascript:jumpTo(${department.id})",--%>
	];

	function  jumpTo(url) {
		window.location.href="${ctx}/contact/contact/index?pageSize=10&departmentId="+url;
	}

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

	$(".node_name").click(function () {
		window.location.href="${ctx}/contact/contact/index?pageSize=9&departmentId="+$(this).attr("menuId");
	});


	/*查询搜索*/
	$(".summitButton").click(function () {
		var name=$("input[name='searchName']").val() ;
		window.location.href="${ctx}/contact/contact/index?name="+encodeURI(encodeURI(name))+"&pageSize=9";

	});

	//搜索框关键字回显
	$("input[name='searchName']").val($("#searchHidden").val());


	$(".gotoPage").click(function (){
		var pageNum = $(".go").val();
		if (pageNum <= 0 || pageNum >${pageInfo.pages}){
			layer.msg("请输入正确的页码")
		} else {
			var name=$("#searchHidden").val() ;
			var departmentId ="${empty departmentId?0:departmentId}";

			if(name != ""){
				window.location.href = "${ctx}/contact/contact/index?pageNum="+$(".go").val()+"&pageSize=9"+"&name="+encodeURI(encodeURI(name));
			}else if(departmentId != ""){
				window.location.href="${ctx}/contact/contact/index?pageNum="+$(".go").val()+"&pageSize=9&departmentId="+departmentId;
			}else{
				window.location.href = "${ctx}/contact/contact/index?pageNum="+$(".go").val()+"&pageSize=9";
			}
		}
	});

	function setFontCss(treeId, treeNode) {
		if (treeNode.id == "${departmentId}")
			return {
				'padding-top':' 0','background-color': '#def7f5','color': 'black','height': '25px','opacity': '.8','width': '86%'
			};
		else return {'font-weight': 'normal', color: 'black'};
	}
</script>
</body>
</html>