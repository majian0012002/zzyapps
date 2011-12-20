/**
 * 主界面内容显示区操作
 */
Wide['Main'] = function(){
	var mainTabsEl;
	return {
		/**
		 * 初始化界面
		 */
		init: function(){
			mainTabsEl = $('#mainTabs');
			
			Wide.Main.initEvent();
		},
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			
		},
		
		/**
		 * 添加tab
		 */
		addTab: function(fun){
			var tab = mainTabsEl.tabs('getSelected');
			
			var idx = 0;
			$.timer(function(){ // 当主界面渲染后，或者2秒之后，执行更新主界面的操作
				return !Wide.mainPageIsLoading || idx++>20;
			}, function(){
				Wide.mainPageIsLoading = true;
				mainTabsEl.tabs('update', {
					tab: tab,
					options:{
						title: fun['name'],
						iconCls:'icon-save',
						content: Wide.iframe(fun['uiPath'])
					}
				});	
			}, 100);
		}
	};
}();