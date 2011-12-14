/**
 * 首界面
 */
Home = function(){
	return{
		/**
		 * 初始化界面
		 */
		init: function(){
			using(['msg', 'form'], Home.initCom);			
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			Wide['Left'].init();
			Wide['Top'].init();
			Wide['Main'].init();
		},
		
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			
		}
	};
}();

$(Home.init);

