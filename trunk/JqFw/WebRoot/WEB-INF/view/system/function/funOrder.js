FunOrder = function(){
	
	return {
		/**
		 * 初始化界面
		 */
		init: function(){
			using(['tree', 'draggable', 'droppable', 'msg'], function(){alert('init ok!')});
		}
	};
	
}();


$(FunOrder.init);