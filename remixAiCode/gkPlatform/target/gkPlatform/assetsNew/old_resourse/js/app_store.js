window.onload=function(){
	/*var x= 95*(0.2-1);
	$(".starHalf").css("background-position",x);
	*/
	
	$("#personMessage").hover(function(){
		$("#test").show();
		$("#thisOrange").attr("style","background:orange;");
	},function(){
		$("#thisOrange").attr("style","background:none;");
		$("#test").hide();
	});
		
	
	$("#appIcon").toggle(function(){
		$("#appStoreTotal").hide();
		$("#appStoreDetail").show();
	},function(){
		$("#appStoreTotal").show();
		$("#appStoreDetail").hide();
	});
	
	//点击图标样式更换
	var src="";
	$(".menuRight_appStore>ul>li").toggle(function(){
		src=$(this).find("img").attr("src");
		
		if(src.indexOf("1.png") >= 0){
			src=src.replace("1.png",".png");
		}else{
			src=src.replace(".png","1.png");
		}
		$(this).find("img").attr("src",src);
		
	},function(){
		/*alert("b")*/
		src=$(this).find("img").attr("src");
		
		if(src.indexOf("1.png") >= 0){
			src=src.replace("1.png",".png");
		}else{
			src=src.replace(".png","1.png");
		}
		$(this).find("img").attr("src",src);
	});
	
	
	/*应用排行的选择效果*/
	var isCurrentSelected=$(".paihangContent>ul>li");//判断首次选择和默认选中是否同一个
	var isChange=false;//保证列表中必须有一个处于选中状态
	$(".paihangContent>ul>li").hover(
		function(){
			//全部效果改为正常，然后再给选定的附加选中效果
			for(var i=0;i<isCurrentSelected.length;i++){
				
				$(isCurrentSelected[i]).find("div[class='selectedContent']").hide();
				$(isCurrentSelected[i]).find("div[class='normalContent']").show();	
			}
				
				$(this).find("div[class='selectedContent']").show();
				$(this).find("div[class='normalContent']").hide();	
			
			
			
		},function(){
			//alert($(this).find("div[class='normalContent']").is(":hidden"));
		/*	for(var i=0;i<isCurrentSelected.length;i++){
				if($(isCurrentSelected[i]).find("div[class='selectedContent']").style.display){
					
				}
			}*/
			if($(this).find("div[class='normalContent']").is(":hidden")){//判断如果当前的div是属于中的div，则鼠标移到其他地方的时候，不改变
				//这里不改变当前选中的样式
			}else{
				$(this).find("div[class='selectedContent']").hide();
				$(this).find("div[class='normalContent']").show();	
			}
			
		
		}
	);
	
	/*应用搜索必须单选的效果*/
	var aAll=null;
	$(".searchTr > td > a").click(
		function(){
			aAll=$("#"+$(this).parents("td").parents("tr").attr("id")+">td>a");//获取当前行标签并且判断是否有异于当前选中的标签被选中
			for(var i=-1;i<aAll.length;i++){
				if($(aAll[i]).attr("class")!=$(this).attr("class")){ //如果选中的标签已经被选中过
					//则不操作
				}else{
					//如果选中的标签未被选中过，则将之前选中的标签样式清除，当前选中的添加样式
					$(aAll[i]).removeClass("clickHover");
				}
					$(this).addClass("clickHover");//当前选中标签添加样式
			}
		}
	);
	
	/*由于评论在页面最下方，点击整个模块需要下拉*/
	$(".pinglun").toggle(
		function(){
			$(this).parent().parent().parent().parent().find("div[class='ycPingLun']").show();
			$(".autoHeightDiv").height($(".autoHeightDiv").height()+$(".formDivForm").height()+30);
		},function(){
			$(this).parent().parent().parent().parent().find("div[class='ycPingLun']").hide();
			$(".autoHeightDiv").height($(".autoHeightDiv").height()-$(".formDivForm").height()-30);
		}
	);
	
	var htmlContent="";

	$(".appDetilCaozuo>div").toggle(
		function(){
			
				htmlContent=$(this).html().replace(".png","1.png");
				$(this).attr("style","color: #1AB394");
				$(this).html(htmlContent);
				
				
			/*	$("#yc"+$(this).attr("id")).attr("style","display: block");*/
				/*$(this).unbind('hover');
			*/
		},
		function(){
			htmlContent=$(this).html().replace("1.png",".png");
			$(this).attr("style","color:none");
			$(this).html(htmlContent);
			
			
				
			/*$(this).bind('hover');*/
			
		}
	);
	
	/*点击回复出现回复操作栏*/
	$(".ycDivRight_huiFu").toggle(
		function(){
			$(this).attr("style","color:#1AB394 !important");
			$(this).parent().parent().find("div[class='appMyRe']").show();
			$(".autoHeightDiv").height($(".autoHeightDiv").height()+$(".formDivForm").height()-85);
		},function(){
			$(this).attr("style","color:none !important");
			$(this).parent().parent().find("div[class='appMyRe']").hide();
			$(".autoHeightDiv").height($(".autoHeightDiv").height()-$(".formDivForm").height()+85);
		}
	);
/*	$(".appDetilCaozuo>div").hover(
		function(){
			
				htmlContent=$(this).html().replace(".png","1.png");
				$(this).html(htmlContent);
				$(this).attr("style","color: #1AB394");
				
			
		},
		function(){
				htmlContent=$(this).html().replace("1.png",".png");
				$(this).html(htmlContent);				
				$(this).attr("style","color:none");
			
		}
	);*/
}
