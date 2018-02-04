<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html language="en">
	<head>
		<meta charset="utf-8">
		<title></title>

		<link rel="stylesheet" type="text/css" href="${ctxStaticNew}/css/jquery.autocomplete.css" />
		<style>
			body{
				font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif;
				font-size:14px;
				color:#101010;
			}
			.container{
				width:300px;
				margin:0 auto;
				margin-top:30px;
			}
			.row{
				margin-bottom:20px;:
			}
			.row p,.row label{
				width:30%;
				display:inline-block;
				text-align:right;
			}
			#autoComplete{height:23px;
				padding:0;
				padding-left:5px;
				width:190px;border:1px solid #a9a9a9;
				border-radius: 3px;
			}
			span,select{margin-left:15px;}
			select{padding:0 6px;width:60%;height:28px;border-radius: 5px;font-family:"Roboto","Noto Sans CJK SC","Nato Sans CJK TC","Nato Sans CJK JP","Nato Sans CJK KR",-apple-system,".SFNSText-Regular","Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei","WenQuanYi Zen Hei",Arial,sans-serif;
			}
		</style>
        <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="${ctxStaticNew}/js/jquery.autocomplete.js"></script>
		<script  src="${ctxStaticNew}/js/layer/layer.js"></script>

		<script	>
        
			$(function() {

                var data=${userList};
//				if(data.length==0){
//					alert("该应用下没有分配任何用户");
//					$("#name").hide();
//
//				}
				$("#autoComplete").autocomplete(data, {
					minChars: 1,// 在触发autoComplete前用户至少需要输入的字符数.Default: 1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表
					max: 10,//下拉显示项目的个数
					autoFill: false,//要不要在用户选择时自动将用户当前鼠标所在的值填入到input框. Default: false
					mustMatch: true,//如果设置为true,autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框.Default: false
					matchContains: true,
					formatItem: function(row, i, max) {
						return  row.name;

					},
					formatMatch: function(row, i, max) {
						return row.name;
					},
					formatResult: function(row) {
						return row.name;//+row.account.replace(/[^0-9]/ig,"");//取得账号里面的数字...
					}
				});

                $("#autoComplete").bind("input propertychange", function() {
                    if ($(this).val().trim() == "") {
                        $(".completeTips").show();
                    }else{
                        $(".completeTips").hide();
                    }
                });
			});
			
			function doSubmit(){ //回调函数，在编辑和保存动作时，供openDialog调用提交表单。
				if($('#personId').val()==''){
					layer.msg("请选择用户");
					return false;
				}

				$.post($('form').attr('action'), {
					roleId: $("input[name='roleId']").val(),
					userId: $("input[name='userId']").val()
				}, function (retJson) {
					layer.msg(retJson.msg, {
						time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function () {
						if (retJson.code == -1){
							return false;
						}else {
							window.parent.location.reload(true);
						}
					});
				});
			}
			
		</script>
	</head>

<body>
<div class="container">
	<form action="${ctx}/class/roleUser/save" id="inputForm" method="post" onkeydown="if(event.keyCode==13)return false;">
		<input type="hidden" value="${roleId}" name="roleId">
		<div class="row" id="name">
			<label>人员姓名：</label>
			<input id="autoComplete" name="userName"/>
			<input type="hidden" name="userId"  id="personId" />
		</div>
	</form>
	
	<c:if test="${userList==null}">
	<h1>该应用下没有用户</h1>
	</c:if>
</div>

</body>

</html>

