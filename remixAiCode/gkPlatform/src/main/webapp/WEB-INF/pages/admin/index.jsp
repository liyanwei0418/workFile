<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="login">
<meta name="author" content="lexi">

<link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />

		


<!-- jQuery -->
<script src="${ctxStatic}/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/main.js"></script>

<title>index</title>
</head>
<body>
		<div class="longHeader">
		</div>
		<div class="neiNian1">
			<div class="neiNian2">
				<div class="header">

					
					<div class="menuContent">
						
						<div class="userInfoDiv">
							<ul>
								<li class="noHoverLi">个人空间</li>
								<li class="noHoverLi">消息</li>
								<li class="noHoverLi">应用商店</li>
								<li class="noHoverLi">客户端下载</li>
								<li id="personMessage">
									<div id="thisOrange">早上好,小赵
										<img src="${ctxStatic}/image/xiala.png">
									</div>
									<div class="personInfoLiDiv" id="test">
										<ul>
											<li class="usuallyMain">
												<img class="menuImg" src="${ctxStatic}/image/personIndex.png"> 个人主页
											</li>
											<li class="usually">
												<img class="menuImg" src="${ctxStatic}/image/userInfo.png"> 用户信息
											</li>
											<li class="usually">
												<img class="menuImg" src="${ctxStatic}/image/setting.png"> 账户设置
											</li>
											<li class="usually">
												<img class="menuImg" src="${ctxStatic}/image/safeMan.png"> 安全管理
											</li >
											<li>
												<img class="menuImg" src="${ctxStatic}/image/help.png"> 帮助中心
											</li >
											<li class="usuallyExit" onclick="window.location.href='${pageContext.request.contextPath}/doLogout'">
												<img class="menuImg" src="${ctxStatic}/image/exit.png"> 退出账户
											</li>
										</ul>

									</div>
								</li>
							</ul>
						</div>
					</div>

				</div>

				<div class="headerMenu">
					<div class="menuLeft">
						<table class="menuLeftTable" cellpadding=13px;>
							<tr class="tableChange">
								<!--<td class="leftMenu">
									<img src="${ctxStatic}/image/leftDiv.png">
								</td>-->
								<td>
									
									
									<img src="${ctxStatic}/image/logo.png">
								</td>
								<td class="underLine"><a class="doudong">&nbsp;&nbsp;&nbsp;首页&nbsp;&nbsp;&nbsp;</a></td>
							</tr>
						</table>
						<div class="menuRight">
							<img id="pengyqShow" src="${ctxStatic}/image/rightDiv.png">
						</div>
					</div>

				</div>
			</div>
		</div>
		<h1>这里是admin index</h1>
		<div class="mainContain">
			
			<div class="forBeauty">
			<h1>这里是admin index</h1>
			</div>
				
				
		</div> 


	<div class="guding">	
		<div class="forever" id="recodeI"><img class="picpic" src="${ctxStatic}/image/recode.png"></div>
		<div class="forever" id="messageI"><img class="picpic"  src="${ctxStatic}/image/message.png"></div>
		<div class="forever" id="toTopI"><img  class="picpic" src="${ctxStatic}/image/toTop.png"></div>
	</div>
	<br><br><br><br>
	
<%-- admin idnex page:<br/>
<shiro:hasPermission name="user:create">
    create
</shiro:hasPermission>

<shiro:hasPermission name="user:view">
    view
</shiro:hasPermission>


<shiro:hasRole name="admin">
    user
</shiro:hasRole>

<shiro:guest>
    guest
</shiro:guest>
 --%>
</body>
</html>