window.onload = function() {
		
		var str=window.location.search;//获取url后面拼接的字符串？index=value,调用getUrlParam方法能够获得该value
		chooseIndex(getUrlParam(str,"index"));
		//chooseIndex(index);//传入页面索引显示步数*/
		
		$("#personMessage").hover(function() {
			$("#test").show();
			$("#thisOrange").attr("style", "background:orange;");
		}, function() {
			$("#thisOrange").attr("style", "background:none;");
			$("#test").hide();
		});

		$(".scoreInput>input").click(function() {

			if ($(this).attr("name") == "delete") {

			} else if ($(this).attr("name") == "newB") {
				$(".scoreSetB").hide();
				$(".innerContent").hide();
				$("#" + $(this).attr("id").replace("Content", "")).show();
			} else {
				$(".innerContent").hide();
				$(".scoreSetB").show();
				$("#" + $(this).attr("id").replace("Content", "")).show();
			}

		});

		$("#autoHf>ul>li").click(
			function() {

				$("#autoHf>ul>li").css("background", "none");
				if ($(this).attr("class") == "autoHfHeader") {

				} else {
					$(this).css("background", "red");
				}

			}

		);

		$(".scoreInput>input[type='button']").click(
			function() {
				if ($(this).attr("name") == "delete" || $(this).attr("name") == "clear") {

				} else {
					$(".scoreInput>input[type='button']").attr("style", "background:none;color:none;");
					$(this).attr("style", "background: #1AB394 !important;color: #fff !important;");
				}

			}
		);

		$("#scoreCheck").click(
			function() {

				if ($(this).is(':checked')) {
					showGradeBg();
				}
			}

		);
		/*判断是否有数据来决定台显示高度*/
		if ($(".thereIsData").is(":hidden")) {
			$(".fenbanMain").attr("style", "height: 300px !important;"); //
		}

		$(".h1").click(
			function() {
				//页面层-自定义
				layer.open({
					type: 1,
					title: false,
					closeBtn: 0,
					shadeClose: true,
					skin: 'yourclass',
					content: '<div class="fenBanFail"><div ><img align="absmiddle" src="image/star/fail.png"><a> 分班失败，上传数据人数不足以按照设置分类，请更改分班个数或者每班人数</a></div><input onclick="closeAlertDiv()" type="button" value="重新设置"/></div>'
				});

				/*layer.msg('<img align="absmiddle" src="image/app1.png">玩命提示中');*/
			}
		);

		$(".h2").click(
			function() {
				//页面层-自定义
				layer.open({
					type: 1,
					title: false,
					closeBtn: 0,
					shadeClose: true,
					skin: 'yourclass',
					content: '<div class="fenBanIng"><div><a>正在分班......</a><div class="jinduTiao"><div class="jinduTiao1" style="width:58%"></div></div><label>65%</label></div><input type="button" value="取消上传"/></div>'
				});

				/*layer.msg('<img align="absmiddle" src="image/app1.png">玩命提示中');*/
			}
		);

	

		/*重新排班提示*/
		$("#rePaiBan").click(
			function(){
				var html = '<div class="rePaiBan"><div class="alertDivHeader"><label>提示</label></div><div class="alertDivContent"><label>重新排班后本次排班结果会自动注销，是否重新排班？</label><div class="alertButtons"><input type="button" value=" 取消 "/><input type="button" value=" 重新上传 "/></div></div></div>';

				//页面层-自定义
				layer.open({
					type: 1,
					title: false,
					closeBtn: 0,
					shadeClose: true,
					skin: 'yourclass',
					content: html
				});
			}
		);

		$(".alertDivContent>table>tbody>tr").hover(
			function() {
				$(this).find("td[class='deleteTd']").html('<div onclick="deleteThis()" class="alertImg"><img align="absbottom" src="image/fenban/delete.png" /><a> 删除</a></div>');

			},
			function() {
				$(this).find("td[class='deleteTd']").html("");

			}
		);

		$(".alertDivContent>table>tbody>tr>td").dblclick(

			function() {
				if ($(this).hasClass("seqId")) {

				} else {
					$(this).html(
						'<input type="text" onblur="backToNormal(this)" autofocus="autofocus" class="hoverInput" value="' + $(this).html() + '">'
					);
				}

				$(this).find("input").focus();
			}
		);
	
		$(".step3Table>table>tbody>tr").hover(
			function() {
				$(this).find("td[class='deleteTd']").html('<div onclick="deleteThis()" class="alertImg"><img align="absmiddle" src="image/fenban/delete.png" /><a> 删除</a></div>');

			},
			function() {
				$(this).find("td[class='deleteTd']").html("");

			}
		);
		
		$(".file").hover(
			function() {
				$(this).css("cursor", "pointer");
				$("#liuLan").attr("style", "background:#21A68B;");
			},
			function() {
				$("#liuLan").attr("style", "background:#1AB394;");
			});

	}

/*导入失败弹出框*/
function importFail(){
			
				var html = '<div class="upLoadFail"><div class="alertDivHeader"><label>导入失败</label></div><div class="alertDivContent"><label>可能原因如下：</label><br><br><div class="alertReason">1.上传文件格式不是excel格式。<br><br>2.上传的表格有合并的单元格。<br><br>3.上传的必填内容不完整。</div><div class="alertButtons"><input type="button" onclick="closeAlertDiv()" value=" 取消 "/><input type="button" value=" 重新上传 "/></div></div></div>';

				//页面层-自定义
				layer.open({
					type: 1,
					title: false,
					closeBtn: 0,
					shadeClose: true,
					skin: 'yourclass',
					content: html
				});
		
}



function chooseIndex(index){
		/*index 1 2 3对应分班步骤的第几步*/
		switch(index)
			{
			case 1:
				preStep();
			  break;
			case 2:
				nextStep();
			  break;
			case 3:
			    startFenBan();
			  break;
			default:
			 
			}
}

/*第一步的页面*/
function preStep(){
	$("#yiJian").removeClass("active1");
	$("#fbResult").removeClass("active2");
	
	$("#yiJian").addClass("noActive");
	$("#yiJian").addClass("noActive");
	
	$(".step1").show();
	$(".step2").hide();
	$(".step3").hide();
}

/*第二步的页面*/
function nextStep() {
	$("#yiJian").removeClass("noActive");
	$("#fbResult").removeClass("active2");
	
	$("#yiJian").addClass("active1");
	$("#fbResult").addClass("noActive");
	
	$(".step1").hide();
	$(".step3").hide();
	$(".step2").show();
}

/*第三步的页面*/
function startFenBan(){
	$("#fbResult").removeClass("noActive");
	$("#yiJian").removeClass("noActive");
	$("#fbResult").addClass("active2");
	$("#yiJian").addClass("active1");
	
	$(".step1").hide();
	$(".step2").hide();
	$(".step3").show();
	
}

function backToNormal(obj) {
	$(obj).parent().html($(obj).val());
}

function deleteThis() {
	alert("delete");
}

function create() {
	$(".fenbanMain").attr("style", "height: 700px !important;"); //
	$(".noData").hide();
	$(".thereIsData").show();
	closeBg();
}

function closeAlertDiv() {
	layer.closeAll();
}



function setCookie(name,value)
{
  var Days = 30; //此 cookie 将被保存 30 天
  var exp  = new Date();    //new Date("December 31, 9998");
  exp.setTime(exp.getTime() + Days*24*60*60*1000);
  document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString();
}
function getCookie(name)
{
  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
  if(arr != null) return unescape(arr[2]); return null;
}

function getUrlParam(str,name){
	
		if (str.indexOf(name)!=-1){           
        var pos_start=str.indexOf(name)+name.length+1;
        var pos_end=str.indexOf("&",pos_start);
        if (pos_end==-1){
           return parseInt(str.substring(pos_start));
            //alert(str.substring(pos_start));
        }else{
           // alert("对不起这个值不存在！");
        	}
		}
}
