// 定义对象
Role = function(){
	// ...定义变量
	var orgTreeEl, 
		roleGridEl,
		formWinEl,
		formEl;
	
	return{
		/**
		 * 获取角色表格元素
		 * @returns
		 */
		getRoleSelected: function(){
			return roleGridEl.datagrid('getSelected');
		},
		
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'treegrid', 'grid'], Role.initCom);
			
			orgTreeEl = $('#orgTree');
			roleGridEl = $('#roleGrid');
			formWinEl = $('#formWin');
			formEl = $('#form');
		},
		
		/**
		 * 组织树事件
		 */
		orgTreeEvent: {
			onSelect: function(row){
				Role.onSearch(row);
			}
		},
		
		/**
		 * 查询参数
		 */
		queryParams: function(){
			var orgId = null;
			try{
				orgId = orgTreeEl.treegrid('getSelected')['id'];
			}catch(e){}
			return{
				orgId: orgId
			};
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			Role2Fun.init();
			Role2User.init();
			
			formWinEl.window({
				onClose: function(){
					formEl.form('clear');
					formEl.form('findField', 'acc').enable();
					formEl.form('findField', 'smOrg').enable();
				}
			})
			
			// 初始化事件
			Role.initEvent();
		
		},
		
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			// 监听事件
			$('#add').click(Role.doAdd);
			$('#update').click(Role.doUpdate);
			$('#remove').click(Role.doRemove);
			$('#save').click(function(){
				var isValid = formEl.form('validate');
				if(!isValid){
					$.msg.say("请确保表单正常!");
					return;
				}
				
				$.msg.cfm('是否保存当前记录?', function(a){
					if(a){
						// form的值
						var formVal = formEl.form('getValue', true);
						// 当前选中的组织机构
						var org = orgTreeEl.treegrid('getSelected');
						formVal['smOrg'] = org['id']=='0'? null :{id: org['id']};
						if(Role.isNew){
							delete formVal['id'];
						}
						
						Role.doSave(formVal);					
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
			var row = orgTreeEl.treegrid('getSelected');
			if(!row){
				$.msg.say("请先选择部门作为角色的所在部门!");
				return;
			}
			
			formWinEl.window({title:'【新增】角色'});
			formWinEl.window('open');
			Role.isNew = true; // 新增
			
			formEl.form('load',{
				type:'GR',
				status:1,
				smOrg: orgTreeEl.treegrid('getSelected')['name']
			});				
		},
		
		/**
		 * 编辑修改记录
		 */
		doUpdate: function(){
			var row = roleGridEl.datagrid('getSelected');
				
			if(!row){
				$.msg.say("请先选择要修改的角色!");
				return;
			}
			
			
			row['smOrg'] = orgTreeEl.treegrid('getSelected')['name']; 
			
			Role.isNew = false; // 修改
			formEl.form('load', row); // 设置form值
			formEl.form('findField', 'acc').disable();
			formEl.form('findField', 'smOrg').disable();
			
			formWinEl.window({title:'【修改】角色'});
			formWinEl.window('open');			
		},
		
		/**
		 * 删除记录
		 */
		doRemove: function(){
			var row = roleGridEl.datagrid('getSelected');
				
			if(!row){
				$.msg.say("请先选择要删除的角色!");
				return;
			}
			
			$.msg.cfm('是否确定删除当前选中记录？', function(a){
				if(a){
					$.crud("sys/role/remove", row, function(data){
						if(data['success'] == false){
							$.msg.err(data['message']);
						}else{
							$.msg.say("删除成功!");
							roleGridEl.datagrid('reload');								
						}
					});
				}
			})		
		},
		
		/**
		 * 保存记录
		 */
		doSave: function(formVal){
			formEl.form('ajax', {
			  url: 'sys/role/save',
			  data:formVal,
			  onSubmit: function(){
		  		// 表单不可用
		  		formWinEl.disable();
			  	$.msg.say("正在保存数据,请稍候...");
			  	return true;
			  },
			  success: function(data) {
			  	Role.onSearch();
			  	formWinEl.enable();
			  	$.msg.say("保存成功!");
			  	formWinEl.window('close');
			  	$('#funWin').window('close');
			  	$('#userWin').window('close');
			  }
			});				
		},
		
		/**
		 * 查询数据
		 */
		onSearch: function(){
			roleGridEl.datagrid('reload');	
		}
		
	};
}();

/**初始化界面*/
$(Role.init);