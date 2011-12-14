/**
 * 左边功能菜单
 */
Wide['Left']=function(){
	var funList;
	return{
		/**
		 * 初始化界面
		 */
		init: function(){
			funList = USER_FUN_LIST;
			using("tree", function(){
				$.util.Fun.defer(Wide['Left'].initEvent, 500)
			});
		},
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			if(funList && funList.length > 0){
//				var fun = funList[0];
				for (var index = 0; index < funList.length; index++) {
					var fun = funList[index];
					$('#FunTree-'+fun['id']).tree('loadData', fun['children']);
				}
				
			}
		},
		
		/**
		 * 菜单事件配置
		 */
		funEvent: {
			/**
			 * 选中事件处理
			 */
			onSelect: function(row){
				if(row['type'] == 'BASE'){
					Wide['Main'].addTab(row);
				}else{
					$(this).tree('expand', row['target']);
				}
			},
			/**
			 * 遍历加载数据事件
			 */
			eachLoadData: function(row, idx){
				if(row['type'] != 'SUBSYS'){
					if(!row['iconCls']){
						row['iconCls'] = 'icon-tree-action';
					}
					row['state'] = 'open';
				}else{
//					row['iconCls'] = 'icon-tree-action';
				}
			}
		}
	}
}();