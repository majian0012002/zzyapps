// 定义对象
Fun = function(){
	// ...定义变量
	var treegridEl,formWinEl,funFormEl, iconClsComboEl;
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			treegridEl = $('#funTable');
			formWinEl = $('#formWin');
			funFormEl = $('#funForm');
			iconClsComboEl = $('#iconClsCombo');
			
			// 加载要使用的组件
			using(['msg', 'combobox', 'form', 'treegrid', 'combogrid'], Fun.initCom);
			
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			Fun2Role.init();
			
			formWinEl.window({
				onClose: function(){
					funFormEl.find('*[name=code]').enable();
					funFormEl.form('findField', 'parentName').enable();
					funFormEl.form('findField', 'uiPath').enable();
					funFormEl.form('clear');
				}
			});
			
			iconClsComboEl.combogrid({
			    panelWidth:150,
			    idField:'iconCls',
			    textField:'text',
			    columns:[[
			        {field:'iconCls',title:'&nbsp;',width:40, formatter: function(v){
			        	return "<span class='"+v+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>"
			        }},
			        {field:'text',title:'名称',width:80}
			    ]]
			});
			var combGrid = $.data(iconClsComboEl[0], "combogrid").grid
			combGrid.datagrid('loadData', iconClsArr);
			
			
			// 初始化事件
			Fun.initEvent();
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			// 查询记录
			$('#search').click(function(){
				treegridEl.treegrid('reload');			
			});
			
			// 添加记录			
			$('#add').click(Fun.doAdd);
			// 修改记录			
			$('#update').click(Fun.doUpdate);
			// 删除记录			
			$('#remove').click(Fun.doRemove);
			// 保存记录			
			$('#save').click(function(){
				var isValid = funFormEl.form('validate');
				if(!isValid){return;}
				
				// form的值
				var formVal = funFormEl.form('getValue', true);
				// 当前选中的行值 
				var row = treegridEl.treegrid('getSelected');
				
				if(Fun.isNew){
					formVal['id'] = formVal['code'];
					if(row && row['id']){
						formVal['parentFun'] = {id: row['id']};
					}
				}else{
					if(row && row['parentId']){
						formVal['parentFun'] = {id: row['parentId']};
					}
				}
				
				$.msg.cfm('是否保存当前记录?', function(a){
					if(a){
						if(Fun.isNew){
							$.query('sys/fun/isHasRecord', formVal, function(msg){
								if(!msg['success']){
									Fun.saveRecord(formVal, row);
								}else{
									$.msg.say("已经存在相同编码的记录,请确定!");					
								}
							});
						}else{
							Fun.saveRecord(formVal, row);
						}					
					}
				})
				
			});
			
			
			$('#cancel').click(function(){
				formWinEl.window('close');
			});
			
			// 类型事件监听
			funFormEl.form('findField', 'type').change(function(){
				if(this.value == 'SUBSYS'){
					funFormEl.form('findField', 'uiPath').val(null);
					funFormEl.form('findField', 'uiPath').disable();//validatebox('clear');
				}else{
					funFormEl.form('findField', 'uiPath').enable();
				}
			});
		},
		
		/**
		 * 添加记录
		 */
		doAdd: function(){
			var row = treegridEl.treegrid('getSelected');
			
			formWinEl.window({title:'【新增】功能菜单'});
			formWinEl.window('open');
			Fun.isNew = true; // 新增
			funFormEl.form('findField', 'parentName').disable();
			
			funFormEl.form('load',{
				type: 'BASE',
				status:1,
				parentName: row ? row['name']: ''
			});		
		},
		
		/**
		 * 更新记录
		 */
		doUpdate: function(){
			var row = treegridEl.treegrid('getSelected');
				
			if(!row){
				$.msg.say("必需先选择一行,再进行修改！");
				return;
			}
			
			if(row['id'] == '0'){
				$.msg.say("根节点不允许修改！");
				return;
			}
			
			Fun.isNew = false; // 修改
			funFormEl.form('load', row); // 设置form值
			funFormEl.form('findField', 'code').disable();
			$('#smOrgTypeCombobox').combobox('setValue', row['smOrgType']);
			if(! $.isEmpty(row['parentId'])){
				funFormEl.form('findField', 'parentName').val(treegridEl.treegrid('find',row['parentId'])['name'] );
			}
			if(row['type'] == 'SUBSYS'){
				funFormEl.form('findField', 'uiPath').disable();
			}
			funFormEl.form('findField', 'parentName').disable();
			formWinEl.window({title:'【修改】功能菜单'});
			formWinEl.window('open');		
		},
		
		/**
		 * 删除记录
		 */
		doRemove: function(){
			var row = treegridEl.treegrid('getSelected');
				
			if(!row){
				$.msg.say("必需先选择一行,再进行删除！");
				return;
			}
			
			if(row['id'] == '0'){
				$.msg.say("根节点不允许删除！");
				return;
			}
			
			$.msg.cfm('是否确定删除当前选中记录？', function(a){
				if(a){
					$.crud("sys/fun/remove", row, function(data){
						if(data['success'] == false){
							$.msg.err(data['message']);
						}else{
							$.msg.say("删除成功!");
							treegridEl.treegrid('reload', row ? row.parentId : null);								
						}
					});
				}
			});		
		},
		
		/**
		 * 保存记录
		 */
		saveRecord: function(formVal, row){
			funFormEl.form('ajax', {
			  url: 'sys/fun/save',
			  data:formVal,
			  onSubmit: function(){
		  		// 表单不可用
		  		formWinEl.disable();
			  	$.msg.say("正在保存数据,请稍候...");
			  	return true;
			  },
			  success: function(data) {
			  	if(Fun.isNew){
			  		treegridEl.treegrid('reload', (row ? row.id : null));
			  	}else{
			  		treegridEl.treegrid('reload', (row ? row.parentId : null));
			  	}
			  	
			  	formWinEl.enable();
			  	$.msg.say("保存成功!");
			  	formWinEl.window('close');
			  	$('#roleWin').window('close');
			  }
			});			
		},
		/**
		 * 获取功能选中的记录
		 */
		getFunSelected: function(){
			return treegridEl.treegrid('getSelected');
		},
		/**
		 * 功能相关事件
		 */
		funEvent: {
			/**
			 * 对遍历的结果集进行处理
			 */
			eachLoadData: function(row, index){
				if(row['type'] == 'SUBSYS'){
					row['iconCls'] = 'icon-tree-folder';
					row['state'] = 'closed';
				}else{
					row['iconCls'] = 'icon-tree-action';
					row['state'] = 'open';
				}
				
			}
		}
	};
}();

/**
 * 图标数组
 * @type 
 */
var iconClsArr = [
	{iconCls:'icon-fun-edit', text:'编辑图标'},
	{iconCls:'icon-fun-search', text:'查询图标'},
	{iconCls:'icon-fun-subsys', text:'子系统图标'}
];

/**
 * 显示功能类型格式化
 * @param {} v 
 * @return {String}
 */
Fmt['funsType']= function(v){
	if(!v) return '';
	return v=='BASE' ? '<span class="blue">基本功能</span>' 
			: '<span class="red">子系统</span>';
}


/**初始化界面*/
$(Fun.init);