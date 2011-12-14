// 定义对象
OrgType = function(){
	// ...定义变量
	var gridEl,formWinEl,orgTypeFormEl;
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'grid'], OrgType.initCom);
			
			gridEl = $('#datagridDIV');
			
			formWinEl = $('#formWin');
			
			orgTypeFormEl = $('#orgTypeForm');
			
			// 初始化事件
			OrgType.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			formWinEl.window({
				onClose: function(){
					orgTypeFormEl.form('findField', 'orgTypeId').enable();
					orgTypeFormEl.form('clear');
				}
			})
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			// 查询记录
			$('#search').click(function(){
				gridEl.datagrid('reload');			
			});
			
			// 添加记录			
			$('#add').click(function(){
				formWinEl.window({title:'【新增】组织机构类型'});
				formWinEl.window('open');
				OrgType.isNew = true; // 新增
				orgTypeFormEl.form('load',{status:1});
			});
			
			// 修改记录			
			$('#update').click(function(){
				var row = gridEl.datagrid('getSelected');
				
				if(!row){
					$.msg.say("必需先选择一行,再进行修改！");
					return;
				}
				
				OrgType.isNew = false; // 修改
				orgTypeFormEl.form('clear'); // 清空form
				orgTypeFormEl.form('load', row); // 设置form值
				orgTypeFormEl.form('findField', 'orgTypeId').disable();
				
				formWinEl.window({title:'【修改】组织机构类型'});
				formWinEl.window('open');
			});
			
			// 删除记录			
			$('#remove').click(function(){
				var row = gridEl.datagrid('getSelected');
				
				if(!row){
					$.msg.say("必需先选择一行,再进行删除！");
					return;
				}
				
				$.msg.cfm('是否确定删除当前选中记录？', function(a){
					if(a){
						$.crud("sys/orgType/remove", row, function(data){
							if(data['success'] == false){
								$.msg.err(data['message']);
							}else{
								$.msg.say("删除成功!");
								gridEl.datagrid('deleteRow', row);							
							}
						});
					}
				})
			});
			
			// 保存记录			
			$('#save').click(function(){
				var isValid = orgTypeFormEl.form('validate');
				if(!isValid){
					$.msg.say("请确保表单正常!");
					return;
				}
				
				$.msg.cfm('是否保存当前记录?', function(a){
					if(a){
						if(OrgType.isNew){
							$.query('sys/orgType/isHasRecord', orgTypeFormEl.serialize(), function(msg){
								if(!msg['success']){
									OrgType.saveRecord();
								}else{
									$.msg.say("已经存在相同编码的记录,请确定!");					
								}
							});
						}else{
							OrgType.saveRecord();
						}					
					}
				})
				
			});
			
			$('#cancel').click(function(){
				formWinEl.window('close');
			});
		},
		/**
		 * 保存记录
		 */
		saveRecord: function(){
			orgTypeFormEl.form('ajax', {
			  url: 'sys/orgType/save',
			  onSubmit: function(){
		  		// 表单不可用
		  		formWinEl.disable();
			  	$.msg.say("正在保存数据,请稍候...");
			  	return true;
			  },
			  success: function(data) {
			  	if(OrgType.isNew){
			  		gridEl.datagrid('appendRow', data);
			  	}else{
			  		gridEl.datagrid('updateRow', {
			  			index: gridEl.datagrid("getRowIndex", gridEl.datagrid('getSelected')),
			  			row: data
			  		});
			  	}
			  	
			  	formWinEl.enable();
			  	$.msg.say("保存成功!");
			  	formWinEl.window('close');
			  }
			});			
		}
	};
}();

/**初始化界面*/
$(OrgType.init);