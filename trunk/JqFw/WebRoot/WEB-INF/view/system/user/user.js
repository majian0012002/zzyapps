// 定义对象
User = function(){
	// ...定义变量
	var orgTreeEl, 
		userGridEl,
		formWinEl,
		formEl,
		roleGridEl;
	
	return{
		/**
		 * 初始化组件
		 */
		init: function(){
			// 加载要使用的组件
			using(['msg', 'form', 'treegrid', 'grid'], User.initCom);
			
			orgTreeEl = $('#orgTree');
			userGridEl = $('#userGrid');
			formWinEl = $('#formWin');
			formEl = $('#form');
			roleGridEl = $('#roleGrid');
		},
		
		/**
		 * 组织树事件
		 */
		orgTreeEvent: {
			onSelect: function(row){
				User.onSearch(row);
				roleGridEl.datagrid('reload', {status: 1});
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
			formWinEl.window({
				onClose: function(){
					formEl.form('clear');
					formEl.form('findField', 'acc').enable();
					formEl.form('findField', 'smOrg').enable();
				}
			})
			
			// 初始化事件
			User.initEvent();
		
		},
		
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			// 监听事件
			$('#add').click(User.doAdd);
			$('#update').click(User.doUpdate);
			$('#remove').click(User.doRemove);
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
						if(User.isNew){
							formVal['id'] = formVal['acc'];
						}
						formVal['smOrg'] = {id: org['id']};
						
						if(User.isNew){
							$.crud('sys/user/isHasRecord', formVal, function(data){
								if(!data){
									User.doSave(formVal);
								}else{
									$.msg.say("已经存在相同账号的记录,请确认!");					
								}
							});
						}else{
							User.doSave(formVal);
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
			var row = orgTreeEl.treegrid('getSelected');
			if(!row){
				$.msg.say("请先选择部门作为用户的所在部门!");
				return;
			}
			
			formWinEl.window({title:'【新增】用户'});
			formWinEl.window('open');
			User.isNew = true; // 新增
			
			formEl.form('load',{
				type:1,
				status:1,
				smOrg: orgTreeEl.treegrid('getSelected')['name']
			});				
		},
		
		/**
		 * 编辑修改记录
		 */
		doUpdate: function(){
			var row = userGridEl.datagrid('getSelected');
				
			if(!row){
				$.msg.say("请先选择要修改的用户!");
				return;
			}
			
			row['smOrg'] = orgTreeEl.treegrid('getSelected')['name']; 
			
			User.isNew = false; // 修改
			formEl.form('load', row); // 设置form值
			formEl.form('findField', 'acc').disable();
			formEl.form('findField', 'smOrg').disable();
			
			if(row['smRoles']){
				$.each(row['smRoles'], function(idx, role){
					roleGridEl.datagrid('selectRecord', role['id']);
				});
			}
			
			formWinEl.window({title:'【修改】用户'});
			formWinEl.window('open');			
		},
		
		/**
		 * 删除记录
		 */
		doRemove: function(){
			var row = userGridEl.datagrid('getSelected');
				
			if(!row){
				$.msg.say("请先选择要删除的用户!");
				return;
			}
			row = {id: row['id']};
			
			$.msg.cfm('是否确定删除当前选中记录？', function(a){
				if(a){
					$.crud("sys/user/remove", row, function(data){
						if(data['success'] == false){
							$.msg.err(data['message']);
						}else{
							$.msg.say("删除成功!");
							userGridEl.datagrid('reload');								
						}
					});
				}
			});		
		},
		
		/**
		 * 保存记录
		 */
		doSave: function(formVal){
			// 获取当前记录的相关权限
			var roles = roleGridEl.datagrid('getSelections');
			if(roles){
				var rs = [];
				$.each(roles, function(idx, role){
					rs.push({id: role['id']});
				});
				formVal['smRoles'] = rs;
			}
			
			formEl.form('ajax', {
			  url: 'sys/user/save',
			  data:formVal,
			  onSubmit: function(){
		  		// 表单不可用
		  		formWinEl.disable();
			  	$.msg.say("正在保存数据,请稍候...");
			  	return true;
			  },
			  success: function(data) {
			  	User.onSearch();
			  	formWinEl.enable();
			  	$.msg.say("保存成功!");
			  	formWinEl.window('close');
			  }
			});				
		},
		
		/**
		 * 查询数据
		 */
		onSearch: function(){
			userGridEl.datagrid('reload');	
		}
		
	};
}();

/**
 * 拥有角色
 */
Fmt.hasRoles = function(v){
	if($.isEmpty(v))return '';
	
	var nameArr = [];
	$.each(v, function(idx, role){
		nameArr.push(role['name']);
	});
	
	return nameArr.join(',');
}


/**初始化界面*/
$(User.init);