// 定义对象
MyTree = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'tree'], MyTree.initCom);
			
			//....
			
			// 初始化事件
			MyTree.initEvent();
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
$(MyTree.init);