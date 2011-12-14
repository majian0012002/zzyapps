// 定义对象
DataGridDemo = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'grid'], DataGridDemo.initCom);
			
			//....
			
			// 初始化事件
			DataGridDemo.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			$('#datagridDIV').datagrid({
				title:'My DataGrid',
				iconCls:'icon-save',
				width:700,
				height:350,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'datagrid_data.json',
				sortName: 'code',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'code',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'code',field:'code',width:80,sortable:true}
				]],
				columns:[[
			        {title:'Base Information',colspan:3},
					{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						}
					}
				],[
					{field:'name',title:'Name',width:120},
					{field:'addr',title:'Address',width:220,rowspan:2,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					},
					{field:'col4',title:'Col41',width:150,rowspan:2}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'Add',
					iconCls:'icon-add',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						alert('add')
					}
				},{
					id:'btncut',
					text:'Cut',
					iconCls:'icon-cut',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						alert('cut')
					}
				},'-',{
					id:'btnsave',
					text:'Save',
					disabled:true,
					iconCls:'icon-save',
					handler:function(){
						$('#btnsave').linkbutton('disable');
						alert('save')
					}
				}]
			});
		},
		
		initEvent: function(){
			// 监听事件
			$('#xxx').click(function(){});
			//...
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(DataGridDemo.init);