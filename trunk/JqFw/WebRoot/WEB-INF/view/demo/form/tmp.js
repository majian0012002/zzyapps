// 定义对象
Tmp = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'grid'], Tmp.initCom);
			
			//....
			
			// 初始化事件
			Tmp.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			// 监听事件
			$('#xxx').click(function(){});
			//...
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(Tmp.init);