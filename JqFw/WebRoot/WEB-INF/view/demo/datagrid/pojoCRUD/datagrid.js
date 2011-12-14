// 定义对象
DataGridDemo = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'grid', 'datetimebox'], DataGridDemo.initCom);
			
			//....
			
			// 初始化事件
			DataGridDemo.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			$('#datagridDIV').datagrid({
				striped: true,
				url: 'demo/pojo/datagrid', //访问controller，会自动生成list，save，remove等方法
				sortName: 'id',  //排序字段
				sortOrder: 'asc', // ASC,DESC
				remoteSort: true, // 是否远程排序
				idField:'id',	// 组件字段
				
				pagination:true, // 是否分页页
				rownumbers:true, // 是否显示好好
				
				frozenColumns:[[	//冻结列
	                {field:'ck',checkbox:true},	// checkbox列
	                {title:'账号',field:'acc',width:80,sortable:true,
	                	editor: 'text'
	                }
				]],
				columns:[[
					{
						field:'pwd',title:'密码',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						},
						editor: 'text'
					},
			        {title:'综合信息',colspan:3}
				],[
					{field:'name',title:'姓名',width:120,
						editor: 'text'
					},
					{field:'email',title:'E-mail',width:220,rowspan:2,sortable:true,
						editor: 'text'
					},
					{field:'createTime',title:'创建时间',width:150,rowspan:2,
						editor: 'datetimebox'
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
				
				$('#datagridDIV').datagrid('add', vo);
			});
			
			$('#eidt').click(function(){
				$('#datagridDIV').datagrid('edit');
			});
			
			$('#save').click(function(){
				$('#datagridDIV').datagrid('save');
			});
			
			$('#delete').click(function(){
				$('#datagridDIV').datagrid('delete');
			});			
			
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(DataGridDemo.init);