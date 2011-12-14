// 定义对象
Org = function(){
	// ...定义变量
	var treegridEl,formWinEl,orgFormEl;
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			treegridEl = $('#orgTable');
			formWinEl = $('#formWin');
			orgFormEl = $('#orgForm');
			
			// 加载要使用的组件
			using(['msg', 'combobox', 'form', 'treegrid'], Org.initCom);
			
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			formWinEl.window({
				onClose: function(){
					orgFormEl.form('findField', 'code').enable();
					orgFormEl.form('clear');
				}
			});
			
			// 初始化事件
			Org.initEvent();
		},
		
		/**
		 * 初始化监听事件
		 */
		initEvent: function(){
			// 查询记录
			$('#search').click(function(){
				treegridEl.treegrid('reload', '0');			
			});
			
			// 添加记录			
			$('#add').click(Org.doAdd);
			// 修改记录			
			$('#update').click(Org.doUpdate);
			// 删除记录			
			$('#remove').click(Org.doRemove);
			// 保存记录			
			$('#save').click(function(){
				var isValid = orgFormEl.form('validate');
				if(!isValid){return;}
				
				$.msg.cfm('是否保存当前记录?', function(a){
					if(a){
						if(Org.isNew){
							$.query('sys/org/isHasRecord', orgFormEl.serialize(), function(msg){
								if(!msg['success']){
									Org.saveRecord();
								}else{
									$.msg.say("已经存在相同编码的记录,请确定!");					
								}
							});
						}else{
							Org.saveRecord();
						}					
					}
				})
				
			});
			
			$('#cancel').click(function(){
				formWinEl.window('close');
			});
		},
		
		/**
		 * 添加记录
		 */
		doAdd: function(){
			var row = treegridEl.treegrid('getSelected');
			/*if(!row){
				$.msg.say("请先选择一条记录作为新增的父节点!");
				return;
			}*/
			
			formWinEl.window({title:'【新增】组织机构'});
			formWinEl.window('open');
			orgFormEl.form('findField', 'parentName').disable();
			Org.isNew = true; // 新增
			
			orgFormEl.form('load',{
				status:1,
				parentName: row ? row['name']: null
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
			
			Org.isNew = false; // 修改
			orgFormEl.form('load', row); // 设置form值
			orgFormEl.form('findField', 'code').disable();
			$('#smOrgTypeCombobox').combobox('setValue', row['smOrgType']);	
			if(row['parentId']){
				orgFormEl.form('findField', 'parentName').val(treegridEl.treegrid('find',row['parentId'])['name'] );
			}
			
			formWinEl.window({title:'【修改】组织机构'});
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
			
			$.msg.cfm('是否确定删除当前选中记录？', function(a){
				if(a){
					$.crud("sys/org/remove", row, function(data){
						if(data['success'] == false){
							$.msg.err(data['message']);
						}else{
							$.msg.say("删除成功!");
							treegridEl.treegrid('reload', row.parentId);								
						}
					});
				}
			})		
		},
		
		/**
		 * 保存记录
		 */
		saveRecord: function(){
			// form的值
			var formVal = orgFormEl.form('getValue', true);
			// 当前选中的行值 
			var row = treegridEl.treegrid('getSelected');
			if(Org.isNew){
				formVal['id'] = formVal['code'];
				if(row && row['id']){
					formVal['smOrg'] = row;
				}
			}else{
				if(row && row['parentId']){
					formVal['smOrg'] = {id: row['parentId']};
				}
			}
			
			orgFormEl.form('ajax', {
			  url: 'sys/org/save',
			  data:formVal,
			  onSubmit: function(){
		  		// 表单不可用
		  		formWinEl.disable();
			  	$.msg.say("正在保存数据,请稍候...");
			  	return true;
			  },
			  success: function(data) {
			  	if(Org.isNew){
			  		treegridEl.treegrid('reload', row ? row.id : null);
			  	}else{
			  		treegridEl.treegrid('reload', row ? row.parentId: null);
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
$(Org.init);