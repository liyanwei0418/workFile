<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
	<head>
    	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css" />
    	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/rs.css" />
    	<script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
	</head>
    <script>
        if(${empty loginUser}){
            alert("登录超时，请重新登录！");
            window.location.href="${ctx}/doLogout";
        }
    </script>
	<body class="thisBody">
	<div style="height:30px;width:1200px;margin:0px auto">
    <div style="z-index:9999;position:fixed;margin:0px 0px">
        <div class="header app_storeHeader">
            <div class="menuContent">  
                <div class="userInfoDiv">
                    <ul>
                       <%-- <li class="noHoverLi">首页</li>--%>
                      <%--  <li class="noHoverLi">应用商店</li>
                        <li class="noHoverLi">客户端下载</li>
                        <li class="noHoverLi">消息</li>--%>
                        <li id="personMessage">
                            <div id="thisOrange">${loginUser.name}
                                <image src="${ctxStatic}/image/xiala.png"/>
                            </div>
                            <div class="personInfoLiDiv" id="test">
                                <ul>
                                   <%-- <li class="usuallyMain" onclick="window.location.href='${ctx}/user/editInfo'">
                                        <image class="menuImg" src="${ctxStatic}/image/userInfo.png" /> 用户信息
                                    </li>--%>
                                    <li class="usually" onclick="openDialog('修改密码', '${ctx}/user/editPwd', '500px', '400px')">
                                        <image class="menuImg" src="${ctxStatic}/image/safeMan.png" /> 安全管理
                                    </li>
                                    <li class="usuallyExit" onclick="window.location.href='${ctx}/doLogout'">
                                        <image class="menuImg" src="${ctxStatic}/image/exit.png" /> 退出账户
                                    </li>
                                </ul>
                            </div>
								</li>
							</ul>
						</div>
					</div>
				</div>

				
			</div>
		</div>

		<div class="grayHeader">

		</div>
		<script type="text/javascript">
		window.onload=function(){
			
			$("#personMessage").hover(function() {
			$("#test").show();
			$("#thisOrange").attr("style", "background:orange;");
			}, function() {
				$("#thisOrange").attr("style", "background:none;");
				$("#test").hide();
			});
		}
		</script>
	</body>

</html>