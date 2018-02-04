
var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
function DropDownList(targetObj, dataProvider, config) {
	this.conf = Object.extend(
		{
			'className' : '',
			'dataFields':[{'name' : 'name', 'type' : 'string'}],//describe the JASON data fields returned from dataProvider
			'dataKeyField':{'name' : 'name', 'type' : 'string'},//describe identify field 
			'dataDescField':{'name' : 'name', 'type' : 'string'},//for display 
			'dropdownTrigger': [{eventName:'keyup', cacheable:true, triggerMinLength:1}, {eventName:'click', loadAllForOnce:true}], //fired when input characters:  click OR keyup
			'beforeDropdown': function(){}, //fired before dropping down
			'afterDropdown': function(){}, //fired when collapse TODO
			'onSelect': function(data){alert('onSelect event is not implemented!');}, //fired when select one item from drop down list
			'onNewInput': function(){}, //fired when be sure to input a value not exist in list
			'onEmpty': function(){},//fired when clear input
			'getFiltered': function(val){return val;},// filter the input contents and set it as filter of datasource
			'loadingImgPath':'',//image for showing progress
			'height':200,//height of drop down list
			'width':0,//width of drop down list
			'footHtml':'',//footer below the drop down list
			'footHeight':0,//height of footer
			'colorOfhighlightWords':'blue',
			'bgColorOfItem':['#ffffff','#F3F2FF', '#6666FF']// 0: for odd items 1: for even items 2: for focus items
		}, 
		config
	);
	console.log(targetObj);
	this.target = targetObj;
	this.target.setAttribute("autocomplete", "off");
	this.dataService = dataProvider;
	this.targetPos = positionOf(this.target);
	this.loadingBar = new CLoadingBar({"left": this.targetPos.left+ parseInt(this.target.offsetWidth,10) + 3,"top" : this.targetPos.top});
	this.loadingBar.setTarget(this.target.getAttribute("id"));
	if('' != this.conf.loadingImgPath)
		this.loadingBar.setImgPath(this.conf.loadingImgPath);
	this.ajaxCall = ajaxGet;
	this.currObj;
	this.data = [];
	this.evtCache = {};
	var self = this;
	this.hide = function(){
		this.dropDownList.parentNode.style.display = "none";
	}
	this.show = function(){
		this.dropDownList.parentNode.style.display = "";
	}
	this.toggleDisplay = function(){
		this.dropDownList.parentNode.style.display = 
			this.dropDownList.parentNode.style.display == "none"?"" : "none";
	}
	this.goDown = function(){
		if(!this.data.length || this.target.value.trim() == ''){
			this.hide();
			return;
		}
		this.show();
		if(!this.currObj) {
			this.currObj = this.dropDownList.childNodes[0];
			this.currObj.setAttribute("oc",this.conf.bgColorOfItem[2]);
			this.currObj.style.backgroundColor = this.conf.bgColorOfItem[2];
			return;
		}
		var idx = this.getItemIndex(this.currObj);
		this.currObj.setAttribute("oc", idx%2 == 0? this.conf.bgColorOfItem[1]:this.conf.bgColorOfItem[0]);
		this.doMsOut(this.currObj);
		if(idx == this.dropDownList.childNodes.length - 1) {
			idx = 0;
			this.dropDownList.scrollTop = 0;
		}else {
			idx++;
			if((idx+1)*15 - this.dropDownList.scrollTop > 180){
				this.dropDownList.scrollTop = this.dropDownList.scrollTop + 15;
			}
		}
		this.currObj = this.dropDownList.childNodes[idx];
		this.currObj.setAttribute("oc",this.conf.bgColorOfItem[2]);
		this.currObj.style.backgroundColor = this.conf.bgColorOfItem[2];
	}
	
	this.goUp = function(){
		if(!this.data.length || this.target.value.trim() == '') {
			this.hide();
			return;
		}
		this.show();
		if(!this.currObj) {
			this.currObj = this.dropDownList.childNodes[this.dropDownList.childNodes.length - 1];
			this.currObj.style.backgroundColor = this.conf.bgColorOfItem[2];
			this.currObj.setAttribute("oc",this.conf.bgColorOfItem[2]);
			return;
		}
		var idx = this.getItemIndex(this.currObj);
		this.currObj.setAttribute("oc",idx%2 == 0?this.conf.bgColorOfItem[1]:this.conf.bgColorOfItem[0]);
		this.doMsOut(this.currObj);
		if(idx == 0) {
			idx = this.dropDownList.childNodes.length - 1;
			this.dropDownList.scrollTop = this.dropDownList.childNodes.length * 15 - this.h; 
		}else {
			idx--;
			if((idx+1)*15 - this.dropDownList.scrollTop < 10){
				this.dropDownList.scrollTop = this.dropDownList.scrollTop - 15;
			}
		}
		this.currObj = this.dropDownList.childNodes[idx];
		this.currObj.setAttribute("oc",this.conf.bgColorOfItem[2]);
		this.currObj.style.backgroundColor = this.conf.bgColorOfItem[2];
		this.show();
	}
	
	this.getItemIndex = function(item){
		if(!item || !item.getAttribute('id') || item.getAttribute('id').indexOf("nm") != 0)
    		return -1;
		return parseInt(item.getAttribute("id").substr(2),10);
	}
	
	this.doSel = function(item){
		if(!this.isOptItem(item)) {
			return this.doSel(item.parentNode);
		}
		var idx = this.getItemIndex(item);
		if(this.currObj) {
			this.currObj.setAttribute("oc", idx%2 != 0?this.conf.bgColorOfItem[0]:this.conf.bgColorOfItem[1]);
			this.doMsOut(this.currObj);
		}
		this.currObj = item;
		var idx = this.getItemIndex(item);
		this.conf.onSelect(this.data[idx]);
		this.currObj.setAttribute("oc", this.conf.bgColorOfItem[2]);
		this.currObj.style.backgroundColor = this.conf.bgColorOfItem[2];
		this.hide();
		return false;
	}
    this.isOptItem = function(obj){
    	return obj.getAttribute("id") && obj.getAttribute("id").indexOf("nm") == 0;
    }
    
    this.isDirectionKeyEntered = function(evt){
    	var e = window.event? window.event : evt;
    	if(e.type == 'keyup' || e.type == 'keypress' || e.type == 'keydown') {
    		var kc = window.event? window.event.keyCode : evt.which;
    		return kc >= 37 && kc <= 40 || kc == 13 || kc == 27;
    	}
    	return false;
    }
	this.doMsOver = function(item){
		if(this.isOptItem(item)) {
		  item.setAttribute("oc", item.style.backgroundColor);
		  item.style.backgroundColor = this.conf.bgColorOfItem[2];
		}else
			return this.doMsOver(item.parentNode);
		return false;
	}

	this.doMsOut = function(item){
		if(this.isOptItem(item)) {
			item.style.backgroundColor = item.getAttribute("oc");
		}else
			return this.doMsOut(item.parentNode);
		return false;
	}
    this.getEventConfig = function(eventType){
    	 for(var i = 0;i < self.conf.dropdownTrigger.length; i++) {
			  if(self.conf.dropdownTrigger[i].eventName == eventType) {
				  return self.conf.dropdownTrigger[i];
			  }
		  }
    	 return null;
    }
	for(var e = 0; e < this.conf.dropdownTrigger.length; e++) {
		attachEventX(this.target, this.conf.dropdownTrigger[e].eventName, function(event){
		  if(window.event) {
			  event.cancelBubble=true; 
		  } else {
			  event.stopPropagation(); 
		  }
		  var param = self.getEventConfig(event.type);
		  if(!self.isDirectionKeyEntered(event) && param && typeof(param.delay) != 'undefined' && param.delay > 0) {
			 var to = eval('window.timeout_'+ self.target.getAttribute("id"));
			 if(to) {
				 clearTimeout(to);
			 }
			 to = setTimeout(
				function(){
				  eval('window.timeout_'+ self.target.getAttribute("id") + ' = null;');
				  self.dropdownCustomerList(
					  event, 
					  param
					);
				  }, 
				 param.delay
			);
			eval('window.timeout_'+ self.target.getAttribute("id") + ' = to;');
		 }else
		    self.dropdownCustomerList(
				  event, 
				  param
				  );
		});
	}
	this.createPopup = function(){
		var ret = document.createElement("DIV");
		this.w = this.conf.width?parseInt(this.conf.width, 10) : (30+parseInt(this.target.offsetWidth,10));
		this.h = this.conf.height?parseInt(this.conf.height, 10) : 200;
		this.outH = this.conf.footHtml&&this.conf.footHeight? parseInt(this.conf.footHeight) + this.h: this.h;
		
		ret.style.cssText = 'display:none;z-index:999;background-color:white;position:absolute; border:1px solid black;width:'+ this.w +'px;height: auto!important;max-height:'+ this.outH +'px;height:'+ this.outH +'px;';
		if(this.conf.className)
			ret.className = this.conf.className;
		var htmStr = "<div style='height:"+this.h+"px;width:100%; overflow-x: hidden; overflow-y: auto;'></div>";
		if(this.conf.footHtml && this.conf.footHeight) {
			htmStr += this.conf.footHtml;
		}
		ret.innerHTML = htmStr;
		this.dropDownList = ret.childNodes[0];
		document.body.appendChild(ret);
		ret.style.top = this.targetPos.top + parseInt(this.target.offsetHeight, 10) + 'px';
  	    ret.style.left = this.targetPos.left + 'px';
	}
	this.createPopup();
	
	attachEventX(document.body, 'click', function(event){
			var e=window.event||event;
			obj = e.srcElement ? e.srcElement : e.target;
			if(obj.id.indexOf("nm") != 0){
				if(obj.id == self.target.id) {
					if(self.target.value.trim() == ''){
						self.hide();
					}else {
						self.toggleDisplay();
					}
					return;
				}
				self.hide();
				var val = self.target.value;
				var exist = false;
				if(self.data.length && trim(val) != ''){
					for(var e =0; e< self.dropDownList.childNodes.length; e++){
						if(self.dropDownList.childNodes[e].getAttribute(self.conf.dataKeyField.name).unescape().toLowerCase() == val.toLowerCase()){
							exist = true;
							break;
						}
					}
					if(!exist)
						self.conf.onNewInput(val);
				}
			}
		}
	);
	
	this.dropdownCustomerList = function(evt, e){
		this.conf.beforeDropdown();
		if(this.target.value == '' || this.target.value.length == 0){
			this.hide();
			this.conf.onEmpty();
		}
		if(evt)
			kc = window.event?window.event.keyCode : evt.which;
		//Up OR Left Arrow key
		if(kc == 38 || kc == 37){
			this.goUp();
			//Down OR Right Arrow key 
		}else if(kc == 40 || kc == 39){
			this.goDown();
		}else if(kc == 27){//Escape Key
			this.data.length = 0;
		    this.target.value = '';
		    this.hide();
		    this.conf.onEmpty();
		}else if(kc == 13){//Enter key
			if(!this.currObj)//Did not download completely
				return false;
			this.doSel(this.currObj);
		}else {
			var content = this.conf.getFiltered(this.target.value).replace(/^\s+/ig,'');
			var kc = 0;
			if(e && typeof(e.triggerMinLength) != 'undefined' && content.length < e.triggerMinLength) {
				this.hide();
				return false;
			}
			if(e && e.loadAllForOnce && this.firstLoadForAll) {//ensure to trigger only ONE time
				this.toggleDisplay();
				return false;
			}
			if(e && e.cacheable && this.evtCache[content] && this.evtCache[content].length) {//share model: different event share the cache
				this.processResult(this.evtCache[content], content);
				this.conf.afterDropdown();
				return false;
			}
			var thisObj = this;
			if(typeof this.dataService == 'string'){
  			this.ajaxCall(this.dataService + '?ct='+encodeURIComponent(content), function(data){
  				//the data format align with this.conf.dataFields
  				var data = eval(data);
  				if(e && e.cacheable) {
  					thisObj.evtCache[content] = data;
  				}
  				if(e && e.loadAllForOnce && !thisObj.firstLoadForAll) {
  					thisObj.firstLoadForAll = true;
  				}
  				thisObj.processResult(data, content);
  				thisObj.conf.afterDropdown();
  			}, 'json'
  			);
  		 }else if(this.dataService.length){
          if(e && e.loadAllForOnce && !thisObj.firstLoadForAll) {
  					thisObj.firstLoadForAll = true;
  				}
          thisObj.processResult(this.dataService, content);
  				thisObj.conf.afterDropdown(); 
       }        
		}
	}
	
	this.processResult = function(data, content) {
		this.data = data;
		this.show();       		
		var str = "";
		var dl = 0;
		for(var e =0; e < data.length; e++){
			var item = data[e];
			var desc = item[this.conf.dataDescField.name];
			if(content) {
				 var start = desc.toLowerCase().indexOf(content.toLowerCase(), 0);
				 var end = start + content.length;
				 var lpart = '';
				 if(start == 0){
				    lpart = '';
				 }else if(start > 0){
				    lpart = desc.substr(0, start);
				 }else
				    continue;
				 desc = lpart + '<font color="'+ this.conf.colorOfhighlightWords +'">' + desc.substr(start, end - start) + '</font>' + desc.substr(end);
			}
			dl++;
			str += "<span style='cursor:pointer;width:100%;padding-left:5px;height:15px; float:left;overflow:hidden;";
			if(e%2==0)
				str += "background-color:" + this.conf.bgColorOfItem[1] + ";";
			str += "'  id='nm" + e + "' title='"+ item[this.conf.dataDescField.name].escape() +"' ";
			for(var c = 0; c < this.conf.dataFields.length;c++) {
				str +=  (this.conf.dataFields[c].name + "='" + item[this.conf.dataFields[c].name].escape() + "' ");
			}
			str += ">";
			str += desc +"</span>"
		}
		if(!dl){
			this.dropDownList.style.height = '15px';
			this.dropDownList.parentNode.style.height = '35px';
			this.dropDownList.innerHTML = "";
			return;
		}
		if(dl * 15 < 200){
			this.dropDownList.style.height = dl * 15  + 'px';
			this.dropDownList.parentNode.style.height = dl * 15 + (this.outH - this.h) + 'px';
		}else{
			this.dropDownList.style.height = this.h;
			this.dropDownList.parentNode.style.height = this.outH + 'px';
		}
		this.dropDownList.innerHTML = str;
		var thisObj = this;
		for(var e = this.dropDownList.childNodes.length-1; e > -1; e--) {
			attachEventX(this.dropDownList.childNodes[e], 'mouseover',
					function(e){
				thisObj.doMsOver(window.event?window.event.srcElement : e.target);
			}
			);
			attachEventX(this.dropDownList.childNodes[e], 'mouseout',
					function(e){
				thisObj.doMsOut(window.event?window.event.srcElement : e.target);
			}
			);
			attachEventX(this.dropDownList.childNodes[e], 'click',
					function(e){
						var obj = window.event?window.event.srcElement : e.target;
						thisObj.doSel(obj);
					}
			);
		}
	}
}

function ajaxGet(url, callBack, resultType, asynch, progressCallback) {
	var lb = this.loadingBar||window.LoadingBar;
	var xml = resultType && resultType == 'xml';
	var asyn = typeof asynch != 'undefined' && asynch == false? false: true;
	var xhr = window.ActiveXObject? 
		(xml&&!asyn? new ActiveXObject("Microsoft.XMLDOM"):new window.ActiveXObject("Microsoft.XMLHTTP"))
		:  new window.XMLHttpRequest();
	if(xml && xhr.overrideMimeType )
		xhr.overrideMimeType('text/xml');
	progressCallback = progressCallback? progressCallback : function(){}/**TODO def implement**/;
	if(asyn) {
		xhr.onreadystatechange = function(){
	        if(xhr)
	        	progressCallback({'status': xhr.readyState == 4?xhr.status : '0', 'readyState':xhr.readyState,'desc':states[xhr.readyState]});
			if ( xhr.readyState == 4 ) {
				lb.hide();
				if(xhr.status == 200 ){
			    	callBack(xml? xhr.responseXML : xhr.responseText);
		    		xhr = null;
				}
			}
	    };
	}
    if(asyn)
    	lb.show();
    if(window.ActiveXObject && xml&&!asyn) {
    	xhr.async = false;
    	xhr.load(url);
    	return xhr;
    }else {
	    xhr.open('GET', url,  asyn);
	    xhr.send(null);
    }
    if(!asyn) {
    	return xml? xhr.responseXML : xhr.responseText;
    }
}

/*Retrieve the element's postion*/
function positionOf(obj){
    var curleft = curtop = 0;
    do {
      curleft += obj.offsetLeft;
      curtop += obj.offsetTop;
    } while (obj = obj.offsetParent);
    return {'left':curleft,'top':curtop};
 }

/*Trim blank char at begin and end of a string*/
function trim(src) {
	return src?src.replace(/^\s+|\s$/ig,'') : src;
}
String.prototype.trim = function(){
	return trim(this);
}
/*show loading for ajax calling*/
var CLoadingBar = LoadingBar;
window.LoadingBar = new LoadingBar();
function LoadingBar(posConfig) {
	this.setPosition = function(conf){
		this.left = conf.left;
		this.top = conf.top;
	};
	this.setImgPath = function(path){
		this.imgPath = path;
	}
	this.setTarget = function(target){
		this.target = target;
	}
	this.imgPath = postPath+"/assetsNew/images/loading.gif";
	this.target = "";
	this.setPosition(posConfig? posConfig : {'left': 150 / 2 - 60, 'top':  '300'});
	this.show = function(){
		   var noop = document.getElementById(this.target + '_noop');
		   if(!noop || typeof noop == 'undefined'){
	    	     noop = document.createElement("DIV");
	    	     document.body.appendChild(noop);
	             noop.appendChild(document.createElement("IMG"));
	             noop.childNodes[0].src = this.imgPath;
	    	     noop.setAttribute("id", this.target + "_noop");
	    	     noop.style.cssText = "z-index : 200;position: absolute; top:"+ this.top +"px; left:"+ this.left +"px;";
	  	   }
	  	   noop.style.display = '';
	};
	this.hide = function(){
		if(document.getElementById(this.target + '_noop'))
			document.getElementById(this.target + '_noop').style.display = 'none';
	}
}

Object.extend = function(destination, source) {
	if(source) {
		for (var property in source) {
		    destination[property] = source[property];
		}
	}
	return destination; 
}

function attachEventX(target,eventName,handlerName){
	if ( target.addEventListener )
		target.addEventListener(eventName, handlerName, false);
	else if ( target.attachEvent )
		target.attachEvent("on" + eventName, handlerName);
	else
		target["on" + eventName] = handlerName;
}

String.prototype.escape = function(){
    return escape(this);
}   
String.prototype.unescape = function(){
    return unescape(this);
}   