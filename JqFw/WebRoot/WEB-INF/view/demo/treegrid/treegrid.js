// 定义对象
TreeGridDemo = function(){
	// ...定义变量
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['treegrid'], TreeGridDemo.initCom);
			
			// 初始化事件
			TreeGridDemo.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			/**  静态数据源treegrid    **/
			$('#test1').treegrid({
				title:'前台加载',
				iconCls:'icon-ok',
				width:700,
				height:250,
				rownumbers: true,
				animate:true,
				collapsible:true,
				fitColumns:true,
				url:'listTreeGrid',	// 绑定后台数据源
				idField:'id',
				treeField:'name',
				showFooter:true,
				rowStyler:function(row){
					if (row.persons > 1){
						return 'background:#AAD684;color:#fff';
					}
				},
				columns:[[
	                {title:'Task Name',field:'name',width:180},
					{field:'persons',title:'Persons',width:60,align:'right'},
					{field:'begin',title:'Begin Date',width:80},
					{field:'end',title:'End Date',width:80,rowspan:2},
					{field:'progress',title:'Progress',width:120,rowspan:2,
					    formatter:function(value){
					    	if (value){
						    	var s = '<div style="width:100%;background:#fff;border:1px solid #ccc">' +
						    			'<div style="width:' + value + '%;background:red">' + value + '%' + '</div>'
						    			'</div>';
						    	return s;
					    	} else {
						    	return '';
					    	}
				    	}
					}
				]]
			});		
			$('#test1').treegrid('loadData', {"total":7,"rows":[
				{"id":1,"name":"All Tasks","begin":"3/4/2010","end":"3/20/2010","progress":60,"iconCls":"icon-ok"},
				{"id":2,"name":"Designing","begin":"3/4/2010","end":"3/10/2010","progress":100,"_parentId":1,"state":"closed"},
				{"id":21,"name":"Database","persons":2,"begin":"3/4/2010","end":"3/6/2010","progress":100,"_parentId":2},
				{"id":22,"name":"UML","persons":1,"begin":"3/7/2010","end":"3/8/2010","progress":100,"_parentId":2},
				{"id":23,"name":"Export Document","persons":1,"begin":"3/9/2010","end":"3/10/2010","progress":100,"_parentId":2},
				{"id":3,"name":"Coding","persons":2,"begin":"3/11/2010","end":"3/18/2010","progress":80},
				{"id":4,"name":"Testing","persons":1,"begin":"3/19/2010","end":"3/20/2010","progress":20}
			],"footer":[
				{"name":"Total Persons:","persons":7,"iconCls":"icon-sum"}
			]});
			
			
			
			/**  一次性从后台加载整个数据treegrid   **/
			$('#test2').treegrid({
				title:'整个加载',
				iconCls:'icon-ok',
				width:700,
				height:250,
				rownumbers: true,
				animate:true,
				collapsible:true,
				fitColumns:true,
				url:'listTreeGrid',		// 绑定后台数据源
				idField:'id',
				treeField:'name',
				showFooter:true,
				rowStyler:function(row){
					if (row.persons > 1){
						return 'background:#AAD684;color:#fff';
					}
				},
				columns:[[
	                {title:'Task Name',field:'name',width:180},
					{field:'persons',title:'Persons',width:60,align:'right'},
					{field:'begin',title:'Begin Date',width:80},
					{field:'end',title:'End Date',width:80,rowspan:2},
					{field:'progress',title:'Progress',width:120,rowspan:2,
					    formatter:function(value){
					    	if (value){
						    	var s = '<div style="width:100%;background:#fff;border:1px solid #ccc">' +
						    			'<div style="width:' + value + '%;background:red">' + value + '%' + '</div>'
						    			'</div>';
						    	return s;
					    	} else {
						    	return '';
					    	}
				    	}
					}
				]]
			});	
			
			/**  异步加载treegrid   **/
			$('#test3').treegrid({
				title:'异步加载',
				width:700,
				height:250,
				rownumbers: true,
				animate:true,
				collapsible:true,
				fitColumns:true,
				url:'listTreeGrid2',		// 绑定后台数据源
				idField:'id',
				treeField:'name',
				columns:[[
	                {title:'Task Name',field:'name',width:180},
					{field:'persons',title:'Persons',width:60,align:'right'},
					{field:'begin',title:'Begin Date',width:80},
					{field:'end',title:'End Date',width:80,rowspan:2},
					{field:'progress',title:'Progress',width:120,rowspan:2}
				]],
				// 加载前传参数
				onBeforeLoad: function(row, param){
					param['name'] = 'zzy';
				}
			});				
			
		},
		
		initEvent: function(){
			// 监听事件
//			$('#xxx').click(function(){});
			//...
		}
		
		//....其他方法
		
	};
}();

/**初始化界面*/
$(TreeGridDemo.init);	
