﻿/**
 * jQuery EasyUI 1.2.5
 * 
 * Licensed under the GPL terms
 * To use it on other terms please contact us
 *
 * Copyright(c) 2009-2011 stworthy [ stworthy@gmail.com ] 
 * 
 */
(function($){
function _1(el,_2,_3,_4){
var _5=$(el).window("window");
if(!_5){
return;
}
switch(_2){
case null:
_5.show();
break;
case "slide":
_5.slideDown(_3);
break;
case "fade":
_5.fadeIn(_3);
break;
case "show":
_5.show(_3);
break;
}
var _6=null;
if(_4>0){
_6=setTimeout(function(){
_7(el,_2,_3);
},_4);
}
_5.hover(function(){
if(_6){
clearTimeout(_6);
}
},function(){
if(_4>0){
_6=setTimeout(function(){
_7(el,_2,_3);
},_4);
}
});
};
function _7(el,_8,_9){
if(el.locked==true){
return;
}
el.locked=true;
var _a=$(el).window("window");
if(!_a){
return;
}
switch(_8){
case null:
_a.hide();
break;
case "slide":
_a.slideUp(_9);
break;
case "fade":
_a.fadeOut(_9);
break;
case "show":
_a.hide(_9);
break;
}
setTimeout(function(){
$(el).window("destroy");
},_9);
};
function _b(_c,_d,_e){
var _f=$("<div class=\"messager-body\"></div>").appendTo("body");
_f.append(_d);
if(_e){
var tb=$("<div class=\"messager-button\"></div>").appendTo(_f);

// zzy+ 聚焦 OK 按钮
var okBtnEl = null;
for(var _10 in _e){
	var btnEl = $("<a></a>").attr("href","javascript:void(0)").text(_10).css("margin-left",10).bind("click",eval(_e[_10])).appendTo(tb).linkbutton();
	if(_10=='ok' || _10=='OK' || _10=='确定'){
		okBtnEl = btnEl;
	}
}
}
_f.window({title:_c,noheader:(_c?false:true),width:300,height:"auto",modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,onClose:function(){
setTimeout(function(){
_f.window("destroy");
},100);
}});
_f.window("window").addClass("messager-window");

// 聚焦 ok按钮 zzy+
if(okBtnEl != null){
	okBtnEl.focus();
}
return _f;
};
$.messager={show:function(_11){
var _12=$.extend({showType:"slide",showSpeed:600,width:250,height:100,msg:"",title:"",timeout:4000},_11||{});
var win=$("<div class=\"messager-body\"></div>").html(_12.msg).appendTo("body");
win.window({title:_12.title,width:_12.width,height:_12.height,collapsible:false,minimizable:false,maximizable:false,shadow:false,draggable:false,resizable:false,closed:true,onBeforeOpen:function(){
_1(this,_12.showType,_12.showSpeed,_12.timeout);
return false;
},onBeforeClose:function(){
_7(this,_12.showType,_12.showSpeed);
return false;
}});
win.window("window").css({left:"",top:"0",right:0,height:_12.height,zIndex:$.fn.window.defaults.zIndex++,bottom:0/*zzy+  -document.body.scrollTop-document.documentElement.scrollTop*/});
win.window("open");
},alert:function(_13,msg,_14,fn){
var _15="<div>"+msg+"</div>";
switch(_14){
case "error":
_15="<div class=\"messager-icon messager-error\"></div>"+_15;
break;
case "info":
_15="<div class=\"messager-icon messager-info\"></div>"+_15;
break;
case "question":
_15="<div class=\"messager-icon messager-question\"></div>"+_15;
break;
case "warning":
_15="<div class=\"messager-icon messager-warning\"></div>"+_15;
break;
}
_15+="<div style=\"clear:both;\"/>";
var _16={};
_16[$.messager.defaults.ok]=function(){
win.dialog({closed:true});
if(fn){
fn();
return false;
}
};
_16[$.messager.defaults.ok]=function(){
win.window("close");
if(fn){
fn();
return false;
}
};
var win=_b(_13,_15,_16);
},confirm:function(_17,msg,fn){
var _18 = "<div class=\"messager-icon messager-question\"></div>"
					+ "<div>" + msg + "</div>" + "<div style=\"clear:both;\"/>";
			var _19 = {};
			_19[$.messager.defaults.ok] = function() {
				win.window("close");
				if (fn) {
					fn(true);
					return false;
				}
			};
			_19[$.messager.defaults.cancel] = function() {
				win.window("close");
				if (fn) {
					fn(false);
					return false;
				}
			};
			var win = _b(_17, _18, _19);
},prompt:function(_1a,msg,fn){
var _1b="<div class=\"messager-icon messager-question\"></div>"+"<div>"+msg+"</div>"+"<br/>"+"<input class=\"messager-input\" type=\"text\"/>"+"<div style=\"clear:both;\"/>";
var _1c={};
_1c[$.messager.defaults.ok]=function(){
win.window("close");
if(fn){
fn($(".messager-input",win).val());
return false;
}
};
_1c[$.messager.defaults.cancel]=function(){
win.window("close");
if(fn){
fn();
return false;
}
};
var win=_b(_1a,_1b,_1c);
},progress:function(_1d){
var _1e=$.extend({title:"",msg:"",text:undefined,interval:300},_1d||{});
var _1f={bar:function(){
return $("body>div.messager-window").find("div.messager-p-bar");
},close:function(){
var win=$("body>div.messager-window>div.messager-body");
if(win.length){
if(win[0].timer){
clearInterval(win[0].timer);
}
win.window("close");
}
}};
if(typeof _1d=="string"){
var _20=_1f[_1d];
return _20();
}
var _21="<div class=\"messager-progress\"><div class=\"messager-p-msg\"></div><div class=\"messager-p-bar\"></div></div>";
var win=_b(_1e.title,_21,null);
win.find("div.messager-p-msg").html(_1e.msg);
var bar=win.find("div.messager-p-bar");
bar.progressbar({text:_1e.text});
win.window({closable:false});
if(_1e.interval){
win[0].timer=setInterval(function(){
var v=bar.progressbar("getValue");
v+=10;
if(v>100){
v=0;
}
bar.progressbar("setValue",v);
},_1e.interval);
}
}};
$.messager.defaults={ok:"Ok",cancel:"Cancel"};
})(jQuery);

//zzy+
$.msg = $.messager;

/**
 * 提示
 * @param {} msg
 */
$.extend($.msg,{
	/**
	 * 提示框
	 * @param {} msg
	 */
	say: function(msg){
		$.msg.show({
			title: '提示',
			msg: msg,
			timeout: 2500,
			showSpeed: 500/*,
			height:200*/
		});
	},
	/**
	 * 信息弹出框
	 */
	info: function(msg){
		$.msg.alert('提示信息', msg, 'info');
	},
	/**
	 * 错误提示框
	 * @param {} msg
	 */
	err: function(msg){
		$.msg.alert('提示信息', msg, 'error');
	},
	/**
	 * 问题提示框
	 */
	issue: function(msg){
		$.msg.alert('提示信息', msg, 'question');
	},
	/**
	 * 警告提示框
	 */
	warn: function(msg){
		$.msg.alert('提示信息', msg, 'warning');
	},
	/**
	 * ajax错误处理解析
	 * @param {} r
	 */
	ajaxError: function(r){
		var win = $('<div class="messager-body" style="overflow:auto;"></div>').appendTo('body');
		win.append(r['responseText']);
		win.window({
			title: "后台报错",
			width: 800,
			height: 500,
			modal: false,
			collapsible: true,
			maximizable: true,
			resizable: true,
			onClose: function(){
				setTimeout(function(){
					win.window('destroy');
				}, 100);
			}
		});
	},
	/**
	 * 确认
	 * @param {} msg
	 * @param {} fn
	 */
	cfm: function(msg, fn){   
		$.msg.confirm("确认", msg, fn);
	}
});
