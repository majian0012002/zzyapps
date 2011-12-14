// 定义对象
DataCombo = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'combogrid'], DataCombo.initCom);
			
			//....
			
			// 初始化事件
			DataCombo.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			// 下拉列表
			$('#datacombo').combobox({
				url:'demo/combo',
				valueField:'id',
				textField:'typeName'
			});
			
			$('#datagrid').combogrid({
				width: 200,
				panelWidth:200,
				value:'1',
				idField:'id',
				textField:'typeName',
				url:'demo/combo',
				columns:[[
					{field:'code',title:'Code',width:60},
					{field:'typeName',title:'Name',width:100}
				]]
			});
			
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			// 监听事件
			$('#datacobo_btn').click(function(){
				$.msg.info(
					"获取值:" + $('#datacombo').combobox('getValue') + '\n' +
					"获取对象值：" + $.encode($('#datacombo').combobox('getValue', true))
				);
			});
			
			$('#datacobo_btn_setVal').click(function(){
				$('#datacombo').combobox('setValue', 2);
			});
			$('#datacobo_btn_setBeanVal').click(function(){
				$('#datacombo').combobox('setValue', {id:3});
			});
			
			
			$('#datagrid_btn').click(function(){
				$.msg.info("获取值:" + $('#datagrid').combogrid('getValue') + '\n' +
					"获取对象值：" + $.encode($('#datagrid').combobox('getValue', true))
				);
			});
		
			$('#combogrid_cfg_btn').click(function(){
				$.msg.info("获取值:" + $('#combogrid_cfg').combogrid('getValue') + '\n' +
					"获取对象值：" + $.encode($('#combogrid_cfg').combobox('getValue', true))
				);
			});
			$('#combogrid_btn_setVal').click(function(){
				$('#combogrid_cfg').combogrid('setValue', 2);
			});
			$('#combogrid_btn_setBeanVal').click(function(){
				$('#combogrid_cfg').combogrid('setValue', {id:3});
			});
			
			
			$('#datatree_btn').click(function(){
				$.msg.info("获取值:" + $('#datatree').combogrid('getValue') + '\n' +
					"获取对象值：" + $.encode($('#datatree').combobox('getValue', true))
				);
			});
			
			$('#datatree_cfg_btn').click(function(){
				$.msg.info("获取值:" + $('#datatree_cfg').combogrid('getValues') + '\n' +
					"获取对象值：" + $.encode($('#datatree_cfg').combobox('getValue', true))
				);
			});
			$('#datatree1_btn_setVal').click(function(){
				$('#datatree_cfg').combotree('setValue', 22);
			});
			$('#datatree1_btn_setBeanVal').click(function(){
				$('#datatree_cfg').combotree('setValue', {id:'DYeH1j24Frern1LWrMKjWGr6ZuVKDGnT'});
			});
			
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(DataCombo.init);