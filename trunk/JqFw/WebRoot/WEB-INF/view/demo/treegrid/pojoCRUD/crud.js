// 定义对象
PojoTreeGrid = function(){
	// ...定义变量
	var treeGrid = null;
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'numberbox', 'datetimebox', 'form', 'treegrid'], PojoTreeGrid.initCom);
			
			//....
			
			// 初始化事件
			PojoTreeGrid.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			treeGrid = $('#test');
			
			treeGrid.treegrid({
//				url: Wide['path'] + 'demo/pojo/treegrid/list',
				
				url: 'demo/pojo/treegrid/',	// 对应的controller类的path，如果配置了本属性，则会自动生成list/save/remove，等方法
//				list: 'demo/pojo/treegrid/list',
//				save: 'demo/pojo/treegrid/save',
//				remove: 'demo/pojo/treegrid/remove',
				
				// 查询参数
				queryParams: function(){
					return {
						mm: 1111,
						yy: 2222
					};
				},
				parentField: 'pDept',
				idField:'id',	// 主键字段映射
				treeField:'code',	// 树列的字段映射
				
				fit: true,
//				rownumbers: true,
				frozenColumns:[[
	                {title:'编码',field:'code',width:200, editor:{type:'numberbox',options:{precision:1}}}
				]],
				columns:[[
					{
						field:'name',
						title:'部门名称',
						width:150, 
						editor:'text'
					},
					{
						field:'status',
						title:'status',
						width:150,
						editor:{
			                type:'checkbox',
			                options:{
			                    on: '1',
			                    off: '0'
			                }
			            }
					},
					{
						field:'createTime',
						title:'createTime',
						width:150,
						editor:'datetimebox'
					}
				]]
			});
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			var idx = 1000;
			// 监听事件
			$('#add').click(function(){
				idx++;
				var vo = {
					code: "code-"+idx,
					name: "name-"+idx
				};
				
				$('#test').treegrid('add', vo);
			});
			
			$('#eidt').click(function(){
				$('#test').treegrid('edit');
			});
			
			$('#save').click(function(){
				$('#test').treegrid('save');
			});
			
			$('#delete').click(function(){
				$('#test').treegrid('delete');
			});
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(PojoTreeGrid.init);