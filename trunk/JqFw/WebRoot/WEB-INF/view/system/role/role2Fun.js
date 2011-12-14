/**
 * 分配角色到功能
 */
Role2Fun = function(){
	//私有变量
	var showFunBtnEl/*设置功能按钮*/, 
		funWinEl/* 功能window*/, 
		funTreeEl/*功能树*/, 
		funOKBtnEl/*功能window的确定按钮*/, 
		funCancelBtnEl/*功能树的取消按钮*/;
	return{
		/**
		 * 初始化模块功能
		 * @returns
		 */
		init: function(){
			showFunBtnEl = $('#showFunBtn');
			funWinEl = $('#funWin');
			funTreeEl = $('#funTree');
			funOKBtnEl = $('#funOKBtn');
			funCancelBtnEl = $('#funCancelBtn');
			Role2Fun.initCmp();
		},
		/**
		 * 初始化组件
		 * @returns
		 */
		initCmp: function(){
			/*funWinEl.window({
				onClose: function(){
					
				}
			});*/
			Role2Fun.initEvent();
		},
		/**
		 * 初始化事件
		 * @returns
		 */
		initEvent: function(){
			showFunBtnEl.click(function(){
				var row = Role.getRoleSelected();
				
				if(!row){
					$.msg.say("请先选择要设置功能的角色!");
					return;
				}
				
				funTreeEl.tree('clearAllChecked');
				var funList = row['smFuns'];
				funTreeEl.tree('setListChecked', funList);
//				$.each(funList, function(idx, fun){
//					
//				});
				
				
				funWinEl.window('open');
			});
			
			funOKBtnEl.click(function(){
				$.msg.cfm("是否确定保存当前角色设置的功能?", function(a){
					if(a){
						Role2Fun.doSaveFun();
					}
				});
			});
			
			funCancelBtnEl.click(function(){
				funWinEl.window('close');
			});
			
		},
		
		doSaveFun: function(){
			var role = Role.getRoleSelected();
			var funList = funTreeEl.tree('getAllChecked');
			role['smFuns'] = $.fetchListProp(funList, 'id');
			role['smUsers'] = $.fetchListProp(role['smUsers'], 'id');
			
			Role.doSave(role);
			
		}
	};
}();

/**
 * 显示功能类型格式化
 * @param {} v 
 * @return {String}
 */
Fmt['funsType']= function(v){
	if(!v) return '';
	return v=='BASE' ? '<span class="blue">基本功能</span>' 
			: '<span class="red">子系统</span>';
}

Fmt['renderCheckbox']= function(v){
	return '<input type="checkbox" />'+v;
}
