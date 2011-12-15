/**
 * 辅助工具
 */
Wide = function(){
	
	return{
		/**
		 * 项目路径
		 **/
		path: "/JqFw/",//easyloader.base.replace("resource/easyui/", "")
		
		/**
		 * 父窗体
		 */
		parentWindow: (window.parent && window.parent.$) ? window.parent : null,
		
		/**
		 * 创建iframe
		 */
		iframe: function(url){
			return '<iframe src="'+Wide.path + url +'" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>';
		},
		/**
		 * 当按下回车键的处理事件
		 */
		onEnterKeyEvent: function(){},
		/**
		 * 切换皮肤
		 * @param skinName 皮肤名称
		 */
		changeSkin: function(skinName)
		{
			$('link[rel=stylesheet][title]').each(function(i) 
			{
				if (this.getAttribute('title') == skinName){
				 	this.disabled = false;
				}else{
					this.disabled = true;
				}
			});
			
			$("iframe").contents().find('link[rel=stylesheet][title]').each(function(i) 
			{
				if (this.getAttribute('title') == skinName){
				 	this.disabled = false;
				}else{
					this.disabled = true;
				}
			});
			
			$.cookie('skin', skinName, 365);
		},
		/**
		 * 主界面是否正在加载
		 */
		mainPageIsLoading: false
	}; 
	
}();

/**
 * 列格式化
 * @type 
 */
Formatter={
	/**
	 * 状态
	 */
	status: function(val){
		if($.isEmpty(val))return'';
		return val==1 ? '<span class="blue">有效</span>' 
						: '<span class="red">无效</span>';
	},
	/**
	 * 显示函数
	 * @param {} prop
	 * @return {}
	 */
	showFun: function(prop){
		return function(v){
			if(!v || $.isString(v))return v;
			
			return v[prop];
		}
	},
	
	/**
	 * 角色类型
	 */
	roleType: function(v){
		if($.isEmpty(v))return '';
		
		if(v == 'GR'){
			return '<span class="blue">正常角色</span>'
		}else{
			return '<span class="red">公共角色</span>'
		}
	}
	
}

Fmt = Formatter;


/**扩展基础类**/
/**
 * @class Array
 */
$.extend(Array.prototype, {
    /**
     * Checks whether or not the specified object exists in the array.
     * @param {Object} o The object to check for
     * @param {Number} from (Optional) The index at which to begin the search
     * @return {Number} The index of o in the array (or -1 if it is not found)
     */
    indexOf : function(o, from){
        var len = this.length;
        from = from || 0;
        from += (from < 0) ? len : 0;
        for (; from < len; ++from){
            if(this[from] === o){
                return from;
            }
        }
        return -1;
    },
    /**
     * 根据属性-值，获取索引
     * @param {} o
     * @param {} from
     * @return {}
     */
    indexOfByProp : function(prop, val){
        var len = this.length;
        var index = -1;
        $.each(this, function(idx, bean){
        	if(bean[prop] == val){
        		index = idx;
        		return false;
        	}
        });
        
        return index;
    },

    /**
     * Removes the specified object from the array.  If the object is not found nothing happens.
     * @param {Object} o The object to remove
     * @return {Array} this array
     */
    remove : function(o){
        var index = this.indexOf(o);
        if(index != -1){
            this.splice(index, 1);
        }
        return this;
    },
    /**
     * 根据属性-值，删除相关记录
     * @param {} prop
     * @param {} val
     * @return {}
     */
    removeByProp: function(prop, val){
        var index = this.indexOfByProp(prop, val);
        if(index != -1){
            this.splice(index, 1);
        }
        return this;
    }
});

// 公共预处理
$(function(){
	/**
	 * 监听公共事件
	 */
	$(document).keydown(function(event){
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
	    if (keyCode == 13) { // 回车键 
	    	if(event.target.nodeName != 'TEXTAREA'){
	        	Wide.onEnterKeyEvent(event); 
	    	}
	    }  
	});	
	
	$('a[form*=]').each(function(){
		var _this = this;
		var formId = $(_this).attr('form');
		$('#' + formId).focus(function(){
			Wide.onEnterKeyEvent = function(event){
				$(_this).click();
			}
		});
		
		$(formId).blur(function(){
			Wide.onEnterKeyEvent = function(event){}
		});
	});
	
	
	// 换肤
	var skinName = null;
	if(Wide.parentWindow != null){
		skinName = Wide.parentWindow.$.cookie('skin');
	}else{
		skinName = $.cookie('skin');
	}
	Wide.changeSkin( skinName || 'blue');
	
	// 监听主界面的iframe的渲染情况
	if(Wide.parentWindow && Wide.parentWindow.Wide){
		$.defer(function(){
			Wide.parentWindow.Wide.mainPageIsLoading = false;
		}, 1000);
	}
	
	
});













//
//var s = {
//	winId: '',	// 基本配置项--》弹出框
//	formId: '',	// 基本配置项--》表格
//	id:'', // 基本配置项-树表格
//	
//	winEl: '',	// 生成属性项--》弹出框
//	formEl: '',	// 生成属性项--》表格
//	el:'', // 生成属性项--》树表格
//	
//	onBeforeAdd: function(){},
//	onBeforeUpdate: function(){},
//	onBeforeSave: function(){},
//	onBeforeRemove: function(){},
//	
//	onAdd: function(){},
//	onUpdate: function(){},
//	onSave: function(){},
//	onRemove: function(){},
//	
//	addAction: function(){},
//	updateAction: function(){},
//	removeAction: function(){},
//	
//	add: function(){},
//	update: function(){},
//	remove: function(){}
//	
//};
//
//
//Wide['TreeGrid'] = function(cfg){
//	// 赋值配置项给当前对象
//	$.extend(this, cfg);
//	
//	if(cfg){
//		this.formEl =null;
//	}
//	
//};



////////////////////////////测试/////////////////////////////////////
//var _arr = [0, 1, 2, 3,4];
//alert(_arr.slice(1,5));

//var _bean = {id:111, dept:{id:'sdf', name:'nnnnnn'}, name:'bbbbbbbb'};
//alert($.beanVal(_bean, "dept.id"));
