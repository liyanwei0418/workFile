//禁止表单一个输入框时，回车提交
$(function() {
	$('form').keydown(function(){
		if(event.keyCode==13){ return false;}
	});
})

	//弹出tips
	/*layer.tips(html, this, {
			  tips: [2, '#1ab394']
			});
*/
	//参数是在哪个html元素上显示这个tips
	function showScore(obj){
	/*var html="";
		var target=$(obj).attr("id").replace("xszf","scores");
		html=$("#"+target).html();
		*/
		layer.tips("内容在openDialog.js里面修改", obj, {
		  tips: [3, '#1ab394']   //1上面弹出 2默认右边 3下面 4 左边
		});

	
	}

	//打开对话框(添加修改)
		function openDialog(title,url,width,height,target){
			layer.open({
			    type: 2,  
			    area: [width, height],
			    title: title,
		        maxmin: false, //开启最大化最小化按钮
			    content: url ,
                scrollbar: false ,
			    btn: ['确定', '关闭'],
			    yes: function(index, layero){
			    	var body = top.layer.getChildFrame('body', index);
					var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					var inputForm = body.find('#inputForm');
					var top_iframe;
					/*if(target){
						top_iframe = target;//如果指定了iframe，则在改frame中跳转
					}
					inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示*/

					if(iframeWin.contentWindow.doSubmit()){
						 setTimeout(function(){parent.location.reload();}, 400);/!*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*!/
						 setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
						}
				  },
				  cancel: function(index){
					  top.layer.close(index);
			       }
			}); 	
		}

//打开对话框(点击X和关闭删除上传的照片)
function openDialogWithClosingDelete(title,url,width,height,target){
    layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: false, //开启最大化最小化按钮
        content: url ,
        scrollbar: false ,
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var body = top.layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var inputForm = body.find('#inputForm');

            if(iframeWin.contentWindow.doSubmit()){
                setTimeout(function(){parent.location.reload();}, 400);/!*刷新父级页面,延迟保证页面刷新的时候数据已经更新完毕*!/
                setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
            }
        },
        //按钮【关闭】的回调
        btn2: function(index, layero){
            delPics();
            //return false 开启该代码可禁止点击该按钮关闭
        },
        cancel: function(index){
            delPics();
            top.layer.close(index);
        }
    });
}


//打开对话框(添加修改) 不会刷新父页面
function openDialogWithoutReload(title,url,width,height,closeChild){
    layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: false, //开启最大化最小化按钮
        content: url ,
        scrollbar: false ,
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var body = top.layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var inputForm = body.find('#inputForm');
            //清空父页面中url
            $('input[name=growthDelUrls]').val('');
            $('input[name=activeDelUrls]').val('');
            $('input[name=baigeDelUrls]').val('');
                if(iframeWin.contentWindow.doSubmit()){
                    if(closeChild){
                   		 setTimeout(function(){top.layer.close(index)}, 2000);//延时0.1秒，对应360 7.1版本bug
					}
				}
        },
        //按钮【关闭】的回调
        btn2: function(index, layero){
            delPics();
            //return false 开启该代码可禁止点击该按钮关闭
    },
        cancel: function(index){
            delPics();
            top.layer.close(index);
        }
    });
}

//若urls不为空则将服务器中的图片删除
function delPics(){
    var pic_urls='';

    if($("input[name='ueditorPics']").val()!=''&&$("input[name='ueditorPics']").val()!=undefined){
        pic_urls=$("input[name='ueditorPics']").val();
	}
    if($("input[name='growthDelUrls']").val()!=''&&$("input[name='growthDelUrls']").val()!=undefined){
        pic_urls=$("input[name='growthDelUrls']").val();
    }
    if($("input[name='activeDelUrls']").val()!=''&&$("input[name='activeDelUrls']").val()!=undefined){
        pic_urls=$("input[name='activeDelUrls']").val();
    }
    if( $("input[name='baigeDelUrls']").val()!=''&&$("input[name='baigeDelUrls']").val()!=undefined){
        pic_urls=$("input[name='baigeDelUrls']").val();
    }
    var delUrls='';
    var id_urls=[];
    if(pic_urls!=''&&pic_urls!=undefined){
        var urlsArray=pic_urls.split(',');
        for(var i=0;i<urlsArray.length;i++){
          var id_url={};
          id_url.id="-1";
          id_url.url=urlsArray[i];
          id_urls.push(id_url);
        }
        var classCardId = $("input[name='classCardId']").val();
        var ctx = $("input[name='ctx']").val();
        $.ajax({
            url: ctx + "/classcard/picture/multidelete",
            type: "post",
            data: {
                id_urls: JSON.stringify(id_urls),
                classCardId: classCardId
            },
            success: function () {
                console.info('未保存图片已在服务器删除');
            },
            error: function () {
                console.info('未保存图片在服务器删除失败');
            }
        })
    }
    $("input[name='growthDelUrls']").val('');
    $("input[name='activeDelUrls']").val('');
    $("input[name='baigeDelUrls']").val('');
}

	//打开对话框(查看)，只有关闭按钮
	function openDialogView(title,url,width,height){
		top.layer.open({
			type: 2,
			area: [width, height],
			title: title,
			maxmin: false, //开启最大化最小化按钮
			content: url ,
            scrollbar: false ,
			btn: ['关闭'],
			cancel: function(index){
			}
		});

	}
	
	//打开frame
	function  openFrame(title,width,height,url) {
		layer.open({
			type: 2,
			title: [title],
			shadeClose: true,
			shade: 0.5,
			area: [width,height],
			content: url
		});
	}
		
		/*导入失败弹出框*/
		function importFail(width,height){
					
						var html = '<div class="upLoadFail"><div class="alertDivHeader"><label>导入失败</label></div><div class="alertDivContent"><label>可能原因如下：</label><br><br><div class="alertReason">1.上传文件格式不是excel格式。<br><br>2.上传的表格有合并的单元格。<br><br>3.上传的必填内容不完整。</div><div class="alertButtons"><input type="button" onclick="closeAlertDiv()" value=" 取消 "/><input type="button" value=" 重新上传 "/></div></div></div>';

						//页面层-自定义
						layer.open({
							type: 1,
							area: [width, height],
							title: false,
							closeBtn: 0,
							shadeClose: true,
							skin: 'yourclass',
							content: html
						});
				
		}

		/*普通的内容提示框*/
		function alertTips(width,height,title,content,clickUrl){
					
		     var html = '<div class="roll-delete" style="height:'+height+';width:'+width+'"><div class="alertDivHeader"><label>'+title+'</label></div><div class="alertDivContent"><label>'+content+'</label><br><br><div class="alertButtons"><input type="button" onclick="closeAlertDiv()" value=" 取消 " /><input type="button" value=" 确定 " onclick="'+clickUrl+'" /></div></div></div>';
						//页面层-自定义
						layer.open({
							type:1,
							area: [width, height],
							title: false,
							closeBtn: 0,
							shadeClose: true,
							skin: 'yourclass',
							content: html
						});
				
		}

/*普通的内容提示框*/
function hintYou(width,height,title,content,clickUrl){

	var html = '<div class="" style="height:'+height+';width:'+width+'"><div class="alertDivHeader"><label>'+title+'</label></div><div class="alertDivContent"><label>'+content+'</label><br><br><div class="alertButtons"><input style="width: auto;" type="button" value=" 我知道了 " onclick="closeAlertDiv()" /></div></div></div>';
	//页面层-自定义
	layer.open({
		type:1,
		area: [width, height],
		title: false,
		closeBtn: 0,
		shadeClose: true,
		skin: 'yourclass',
		content: html
	});

}
		
		function closeAlertDiv() {
            $("input[name='failList']").val('');
			layer.closeAll();
		}


	//不用修改浏览器安全配置的javascript代码，兼容ie， firefox全系列,获取文件路径，obj为input type file
	function getPath(obj)
	{
		if(obj)
		{

			if (window.navigator.userAgent.indexOf("MSIE")>=1)
			{
				obj.select();

				return document.selection.createRange().text;
			}

			else if(window.navigator.userAgent.indexOf("Firefox")>=1)
			{
				if(obj.files)
				{

					return obj.files.item(0).getAsDataURL();
				}
				return obj.value;
			}
			return obj.value;
		}
	}
	//参数obj为input file对象


function generateUUID() {
	var d = new Date().getTime();
	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random()*16)%16 | 0;
		d = Math.floor(d/16);
		return (c=='x' ? r : (r&0x3|0x8)).toString(16);
	});
	return uuid;
};


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