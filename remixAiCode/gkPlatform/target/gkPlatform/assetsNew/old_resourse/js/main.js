window.onload=function(){
	
	
	/* $(".pengyq").height($(window).height()-220);//动态显示朋友圈div的高度
	alert($(".pengyq").height()); */
	$("#recodeI").hover(function(event){
		$("#recodeI").addClass("forever2");
	}, function(event){
		$("#recodeI").removeClass("forever2");	
	});
	
	$("#messageI").hover(function(event){
		$("#messageI").addClass("forever1");   
	}, function(event){
		$("#messageI").removeClass("forever1");	
	});
	
	$("#toTopI").hover(function(event){
		$("#toTopI").addClass("forever3");
	}, function(event){
		$("#toTopI").removeClass("forever3");	
	});
	
	
	/* 返回顶部 */
	var oTop = document.getElementById("toTopI");
	  var screenw = document.documentElement.clientWidth || document.body.clientWidth;
	  var screenh = document.documentElement.clientHeight || document.body.clientHeight;
	  oTop.style.left = screenw - oTop.offsetWidth +"px";
	  oTop.style.top = screenh - oTop.offsetHeight + "px";
	  window.onscroll = function(){
		var scrolltop = document.documentElement.scrollTop || document.body.scrollTop;
		oTop.style.top = screenh - oTop.offsetHeight + scrolltop +"px";
	  }
	  oTop.onclick = function(){
		document.documentElement.scrollTop = document.body.scrollTop =0;
	  }
	  
	  
	  /*弹出框设置 */
	 $(".menuRight").toggle(
		function(){
			
			$(this).attr("style","background:#21A68B");
			$(".pengyq").show();
		},function(){
			$(this).attr("style","background:none");
			$(".pengyq").hide();
		}
	);
	
	  $(".leftMenu").toggle(function(){
		/*   alert("aaaa"); */
		  /* $("#running").animate({height:"33px"}); */
		
			$(this).attr("style","background:#21A68B");
		   $("#running").show();
		   
		   
		   $("#personMessage").attr("style","background:none");
		$(".menuDialog").hide();
	  },function()
	  {
		  $(this).attr("style","background:none");
		   $("#running").hide();
		   
	  });
	  //鼠标放上去叉变红色
	  $(".closePic").hover(
	  function(){
		 $(this).attr('src','image/redClose.png');//把src属性更改为'redClose';
	  },function(){
		 $(this).attr('src','image/closePic.png');//把src属性更改为'closePic';
	  }
	  );
	  
	
	var huifuCount=0; //回复的多少决定div的高度
	
	 $(".tubiaoClick").toggle(
	  function(){
		
		str=$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src");
		
		if(str.indexOf("1.png") >= 0){
			str=str.replace("1.png",".png");
		}else{
			str=str.replace(".png","1.png");
		}
		  
		$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src",str); //图片更换
		
		$(this).attr("style","color:#1ab394");  //文字变色
			
		$("#"+$(this).attr("id")+"Li").show();//div显示
		
		
		if($(this).attr("class").indexOf("huifu") >= 0){
			huifuCount++;
			$(".pinglunLi").height($(".zhuanfaSet").height()+291+huifuCount*50);//动态显示评论div的高度
		}else{
			$(".pinglunLi").height($(".zhuanfaSet").height()+291);//动态显示评论div的高度
		}
		
		/* $(".pinglunLi").height($(".zhuanfaSet").height()+391);//动态显示评论div的高度 */
		
	  },function(){
		str=$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src");
		if(str.indexOf("1.png") > 0){
			str=str.replace("1.png",".png");
		}else{
			str=str.replace(".png","1.png");
		}
		  
		$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src",str); //图片更换
		
		 $(this).attr("style","color:none");
		
		$("#"+$(this).attr("id")+"Li").hide();//div隐藏
		
		if($(this).attr("class").indexOf("huifu") >= 0){
			huifuCount--;
			$(".pinglunLi").height($(".zhuanfaSet").height()+291+huifuCount*50);//动态显示评论div的高度
		}else{
			$(".pinglunLi").height($(".zhuanfaSet").height()+291);//动态显示评论div的高度
		}
	  }
	  );
	  
	  /* 1 */
	  $(".tubiaoClick").hover(
	  function(){
		$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src",$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src").replace(".png","1.png")); //图片更换
		
		$(this).attr("style","color:#1ab394");  //文字变色
			
		/* $("#"+$(this).attr("id")+"Li").attr("style","display:block");//div显示 */
		
		
		/* $(".pinglunLi").height($(".zhuanfaSet").height()+291);//动态显示评论div的高度 */
		
	  },function(){
		$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src",$("#"+document.getElementById($(this).attr("id")).childNodes[0].getAttribute("id")).attr("src").replace("1.png",".png"));
		
		 $(this).attr("style","color:none");
		
		/* $("#"+$(this).attr("id")+"Li").attr("style","display:none");//div隐藏 */
		
		isOther=false;
		/* $(".pinglunLi").height($(".zhuanfaSet").height()+291);//动态显示评论div的高度 */
	  }
	  );
	  /* 1 */
	
	
	/* $(".aaaaaa").toggle(
	function(){
		alert("1")
	},function(){
		alert("2")
	},function(){
		alert("3")
	},function(){
		alert("4")
	},function(){
		alert("5")
	},function(){
		alert("6")
	},function(){
		alert("7")
	},function(){
		alert("8")
	}
	); */
	
	/*以下是应用商店的部分js*/
	$("#personMessage").hover(function(){
		$("#test").show();
		$("#thisOrange").attr("style","background:orange;");
	},function(){
		$("#thisOrange").attr("style","background:none;");
		$("#test").hide();
	});
		
	
	
}
