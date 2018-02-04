<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <title>通知公告</title>
	<style>
		#header .container{padding:0;}
		main.container{padding:0;}
		#artical .title{position:relative;text-align: center;margin:0 135px;border-bottom:1px solid #ddd;padding-bottom: 20px;}
		#artical .title i{font-style:normal;color:#999;position:absolute;left:-120px;top:-4px;font-style: normal;
			cursor: pointer;}
		#artical .title i:before{content:'';position:absolute;width:19px;height:14px;background: url(${ctxStaticNew}/images/publish-4.png) no-repeat center center;top:3px;left:-19px;;}
		#artical .title h3{margin-top:45px;margin-bottom:30px;font-size:20px;color:#6b7469;}
		#artical .title>div{text-align: center;}
		#artical .title span{color:#999;display:inline-block;padding-left:20px;}
		#artical .title>div span:first-child{margin-right:30px;background:url(${ctxStaticNew}/images/publish-3.png) no-repeat left center;}
		#artical .title>div span:last-child{background:url(${ctxStaticNew}/images/publish-1.png) no-repeat left center;}
		#artical .artical-content{margin:0 135px;}
		.artical-content span{display:inline;}
		#artical footer{margin:0 135px;margin-top:50px;}
		#artical footer span{margin-bottom:15px;color:#54ab37;font-size:14px;}
		#artical footer p{margin-bottom:10px;cursor: pointer;font-size:13px;padding-left:20px;background:url(${ctxStaticNew}/images/publish-2.png) no-repeat left center;}
		footer p:hover{color:#1ab394;}
	</style>
</head>
<body>
<%--
<%@ include file="../common/sonHead/notifyHead.jsp" %>--%>
<nav>
	<div class="container">
		<div class="notice-title">通知公告</div>
		<div class="notice-menu">
			<ul>
				<shiro:hasPermission name="notify:notify:view">
					<li class="active"><a class="active" href="${ctx}/notify/index?pageSize=10" data="inform-notice">通知公告</a></li>
				</shiro:hasPermission>
			</ul>
		</div>
	</div>
</nav>

<main class="container">
	<div id="artical">
		<div class="title">
			<h3>${notify.title}</h3>
			<div>
				<span>
					<c:choose>
						<c:when test="${gukeer:notEmptyString(notify.columnName)}">
							【${notify.createName}】
						</c:when>
						<c:otherwise>
							【无】
						</c:otherwise>
					</c:choose>
				</span>
				<span>
					${gukeer:intervalNowTimeToView(notify.publishTime)}
				</span>
			</div>
			<%--<i onclick="window.location.href='${ctx}/notify/index'">返回</i>--%>
			<i onclick="window.history.go(-1)">返回</i>
		</div>
		<div class="artical-content"  style="padding-top: 15px">
			${notify.content}
		</div>
		<footer>
			<c:if test="${fn:length(notify.files) > 1 }">
				<span>附件</span>
			</c:if>
			<c:forEach items="${notify.files}" var="file">
				<p onclick="fileDownload('${ctx}/file/downLoad?fileUrl=${file}')">${gukeer:showFileName(file)}</p>
			</c:forEach>
		</footer>
	</div>
</main>
<script>
	activeMenu("tzmenu",0);

	function fileDownload(str) {
		var url = encodeURI(encodeURI(str));
		window.location.href = url;
	}
</script>
</body>
</html>