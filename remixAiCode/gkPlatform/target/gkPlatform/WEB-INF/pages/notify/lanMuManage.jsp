<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head >
	<meta charset="utf-8">
	<title>通知公告</title>
	<style>
		.ztree li span{
			max-width: 200px;
			word-break: break-all;
			word-wrap: break-word;
			white-space: normal;
			line-height: 22px !important;
		}
		.layui-layer{
			top: 15% !important;
		}
	</style>
	<script >

		$(function() {
			<c:if test="${pageInfo.pages != 0}">
			$(".fenY1").createPage({
				pageCount:${pageInfo.pages},//总页数
				current:${pageInfo.pageNum},//当前页面
				backFn:function(p){
					window.location.href = "${ctx}/notify/lanmu/index?pageNum="+p+"&pageSize=10";
				}
			});
			</c:if>
			$(".headerCheck").on("click",function () {
				if(this.checked==true){
					$("input[type='checkbox']").prop("checked",true);
				}else{
					$("input[type='checkbox']").prop("checked",false);
				}
			});

			$("input[name='queryLan']").click(
					function () {
						var beginDate=$("#beginDate").val() ;
						var endDate=$("#endDate").val() ;
						var currentColumId="${currentColumId}";
						window.location.href="${ctx}/notify/lanmu/index?endDate="+endDate+"&beginDate="+beginDate+"&columId="+currentColumId;
					}
			);

			$(".gotoPage").click(function (){
				var pageNum = $(".go").val();
				if (pageNum <= 0 || pageNum >${pageInfo.pages}){
					layer.msg("请输入正确的页码")
				} else {
					window.location.href = "${ctx}/notify/lanmu/index?pageNum="+$(".go").val()+"&pageSize=10";
				}
			});

		})

		/*删除栏目确定*/
		function  lanmuDelete() {
			closeAlertDiv();
			$.post("${ctx}/notify/col/delete",{
				colId:"${currentColumId}",
			},function(retJson){
				window.location.href="${ctx}/notify/lanmu/index";
			},"json");

		}
		/*删除栏目下信息确定*/
		function  sure() {
			closeAlertDiv();

			var howManyDelay=0;
			var spCodesTemp = "";
			$("input[name='lanmuCheck']:checked").each(function(i){
				if(0==i){
					spCodesTemp = $(this).attr("id");
				}else{
					spCodesTemp += (","+$(this).attr("id"));
				}
				howManyDelay++;
			});

			$.post("${ctx}/notify/delete",{
				id:spCodesTemp
			},function(retJson){
				alert(retJson);
			},"json");

			setTimeout(function(){window.location.reload();}, 100*howManyDelay);//删除的数据越多，延时要越长。否则：刷新页面的时候，数据还没删完..

		}
	</script>
</head>
<body>

<%@ include file="../common/sonHead/notifyHead.jsp" %>

<main class="container">

	<!--栏目管理-->
	<div id="column-manage">
		<aside class="col-xs-3">
			<div>
				<button onclick="openDialog('新增栏目','${ctx}/notify/lanmu','450px','202px');" style="height: 25px;
    padding: 0 11px;
    font-size: 12px;
    margin-left: 4px;
    border: 1px solid #54ab37;
    background: #54ab37;
    color: #fff;
    border-radius: 2px;
    margin-bottom: 10px;
	margin-top:10px;

">新增栏目</button>
			</div>
			<div class="tree-menu">
				<div class="widget-main padding-8">
					<ul id="tree1"  class="ztree"></ul>
				</div>
			</div>
		</aside>
		<main class="col-sm-9">
			<ul>
				<li><a href="#" class="active" data="column-msg">栏目信息</a></li>
				<li><a href="#" data="column-manage-menu">栏目管理</a></li>
			</ul>
			<div id="column-msg">
				<div class="column-manage-operation">
                    <shiro:hasPermission name="notify:notify:add">
                        <button class="publish" onclick="openDialog('发布公告','${ctx}/notify/add?columId=${currentColumId}','1100px','80%');">发布</button>
                    </shiro:hasPermission>
					<button class="delete"  onclick="alertTips(400,202,'删除','确定要删除选中项吗？','sure()')">删除</button>
				</div>

				<table>
					<thead>
					<tr>
						<th width="3%"><input type="checkbox" name="" value=""  class="headerCheck"/></th>
						<th width="3%">序号</th>
						<th width="18%">标题</th>
						<th width="10%">栏目</th>
						<th width="8%">发布人</th>
						<th width="8%">发布时间</th>
						<th width="5%">状态</th>
						<th width="8%">操作</th>
					</tr>
					</thead>
					<tbody>

					<c:forEach items="${notifyViewList}" var="notify" varStatus="status">

						<tr>
							<td><input type="checkbox" name="lanmuCheck" id="${notify.id}" value="" /></td>
							<td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
							<td>${notify.title}</td>
							<td>
								<c:choose>
									<c:when test="${gukeer:notEmptyString(notify.columnName)}">
										【${notify.columnName}】
									</c:when>
									<c:otherwise>
										【其他】
									</c:otherwise>
								</c:choose>
							</td>
							<td>${notify.createName}</td>
							<td>${gukeer:millsToyyyyMMdd(notify.publishTime)}</td>
							<td>
								<c:choose>
									<c:when test="${gukeer:outOfDate(notify.publishTime)==false}">
										发布
									</c:when>
									<c:when test="${gukeer:outOfDate(notify.publishTime)==true}">
										未发布
									</c:when>
								</c:choose>
							</td>
							<td class="editTd" onclick="openDialog('编辑公告','${ctx}/notify/add?id=${notify.id}','1100px','85%')"><span>编辑</span></td>
						</tr>
					</c:forEach>

					</tbody>
				</table>
				<div class="fenye">
					<div class="fenY1" id="fenY1">
						<%--<input type="text"/>--%>
					</div>
				</div>
			</div>
			<div id="column-manage-menu">
				<div class="column-manage-operation">
					<c:if test="${gukeer:notEmptyString(currentColumName)}">
						<button onclick="openDialog('编辑栏目','${ctx}/notify/col/edit?colId=${currentColumId}','500px','202px')"; class="column-manage-edit">编辑</button>
						<button  onclick="alertTips(400,222,'删除栏目','确定要删除当前栏目吗，删除后，相关的信息也会删除,确定吗？','lanmuDelete()')"  class="column-manage-delete">删除</button>
					</c:if>

				</div>
				<table>
					<tbody>
					<tr>
						<td>栏目名称</td>
						<td>${currentColumName}</td>
					</tr>
					<tr>
						<td>创建人员</td>
						<td>${createUser}</td>
					</tr>
					</tbody>
				</table>
			</div>
		</main>
	</div>

</main>

<script type="text/javascript">

	activeMenu("lmmenu",0);

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
		{id:9999, pId:-1, name:"栏目管理", open:true},

		<c:forEach items='${notifyColumnList}' var='column'>
		{id:"${column.id}", pId:9999, name:"${column.name}", open:false},
		</c:forEach>

	];

	function clickTree(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("tree1");
		zTree.expandNode(treeNode);
	}

	$.fn.zTree.init($("#tree1"), setting, zNodes1);

	$(".node_name").click(function () {
		window.location.href="${ctx}/notify/lanmu/index?pageSize=10&columId="+$(this).attr("menuId");
	});

	function setFontCss(treeId, treeNode) {
		if (treeNode.id == "${columId}")
			return {
				'padding-top':' 0','background-color': '#def7f5','color': 'black','height': '25px','opacity': '.8','width': '86%'
			};
		else return {'font-weight': 'normal', color: 'black'};
	}
</script>
</body>


</html>