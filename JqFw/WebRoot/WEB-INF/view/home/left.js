/**
 * 左边功能菜单
 */
Wide['Left']=function(){
	var funList;
	var cookieMenuTitle,cookieMenuTreeId, cookieMenuTreeItemId;
	return{
		/**
		 * 初始化界面
		 */
		init: function(){
			funList = USER_FUN_LIST;
			
			// 初始化菜单状态
			cookieMenuTitle = $.cookie("menuTitle");
			cookieMenuTreeId = $.cookie("menuTreeId");
			cookieMenuTreeItemId = $.cookie("menuTreeItemId");
			
			using(["tree", "accordion"], Wide.Left.initCom);
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			$('#menu').accordion({
				/**
				 *选中事件
				 */
				onSelect: function(title){
					$.cookie("menuTitle", title);
				}
			});
			
			$.util.Fun.defer(Wide['Left'].initEvent, 500)
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
			
			$('#menu').accordion('select', cookieMenuTitle);
			$.timer(function(){
				return $('#' + cookieMenuTreeId).find('*[node-id]').length > 1;
			}, function(){
				$('#' + cookieMenuTreeId).tree('select', cookieMenuTreeItemId);
			}, 500);
		},
		
		/**
		 * 菜单事件配置
		 */
		funEvent: {
			/**
			 * 选中事件处理
			 */
			onSelect: function(row){
				$.cookie("menuTreeId", this.id)
				$.cookie("menuTreeItemId", row['id']);
				if(row['type'] == 'BASE'){
					Wide['Main'].addTab(row);
				}else{
//					$(this).tree('expand', row['target']);
					$(this).tree('toggle', row['target']);
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
					row['iconCls'] = 'icon-fun-subsys';
				}
			}
		}
	}
}();