Fun2Role = function(){
	//定义私有变量
	var showRoleBtnEl/*显示用户维护弹出框的按钮*/,
		roleWinEl/*用户弹出框*/,
		roleOrgTreeEl/*显示用户组织结构树*/,
		roleGridEl/*用户表格*/,
		selctedRoleEl/*已选用户显示文本框*/,
		roleOKBtnEl/*用户保存按钮*/;
	
	/*当前角色的相关用户*/
	var currFunRoles = [];
	/*是否是自动选中表格*/
	var isAutoSelect = false;
	
	return{
		/**
		 * 初始化模块
		 * @returns
		 */
		init: function(){
			showRoleBtnEl = $('#showRoleBtn');
			roleWinEl = $('#roleWin');
			roleOrgTreeEl = $('#roleOrgTree');
			roleGridEl = $('#roleGrid');
			selctedRoleEl = $('#selctedRole');
			roleOKBtnEl = $('#roleOKBtn');
			
			Fun2Role.initEvent();
		},
		/**
		 * 初始化监听事件
		 * @returns
		 */
		initEvent: function(){
			showRoleBtnEl.click(function(){
				var row = Fun.getFunSelected();
				
				if(!row){
					$.msg.say("请先选择要设置角色的功能!");
					return;
				}
				
				if(row['type'] != 'BASE'){
					$.msg.say("只可对<span class='red'>基本功能</span>进行分配角色");
					return;
				}
				
				currFunRoles = row['smRoles'] ? row['smRoles'] : [];
				Fun2Role.setRoleGridSelect();
				Fun2Role.setRoleText();
				roleWinEl.window('open');
			});
			
			roleOKBtnEl.click(function(){
				$.msg.cfm("是否确定保存当前功能设置的关联角色?", function(a){
					if(a){
						Fun2Role.doSaveFun2Role();
					}
				});
			});
		},
		/**
		 * 用户查询
		 * @returns
		 */
		queryRoleParams: function(){
			var org = roleOrgTreeEl.treegrid('getSelected');
			return {
				status:1,
				orgId: org['id']
			};
		},
		/**
		 * 组织树事件
		 */
		orgTreeEvent: {
			onSelect: function(row){
				//Fun2Role.onSearch(row);
				roleGridEl.datagrid('reload', {status: 1});
			}
		},
		
		/**
		 * 用户表格事件
		 */
		roleGridEvent: {
			onSelect: function(idx, row){
				if(currFunRoles.indexOfByProp('id', row['id']) == -1){
					currFunRoles.push(row);
					Fun2Role.setRoleText();
				}
			},
			onUnselect: function(idx, row){
				currFunRoles.remove(row);
				Fun2Role.setRoleText();
			},
			onLoadSuccess: function(){
				Fun2Role.setRoleGridSelect();
			}
		},
		
		/**
		 * 保存角色和用户的关联信息
		 * @returns
		 */
		doSaveFun2Role: function(){
			var fun = Fun.getFunSelected();
//			fun['smFuns'] = $.fetchListProp(role['smFuns'], 'id');
			fun['smRoles'] = $.fetchListProp(currFunRoles, 'id');
			fun['parentFun'] = {id: fun.parentId};
			Fun.saveRecord(fun, fun);
		},
		
		/**
		 * 设置用户选中
		 * @param Roles
		 * @returns
		 */
		setRoleGridSelect: function(){
			roleGridEl.datagrid('clearSelections');
			$.each(currFunRoles, function(idx, role){
				roleGridEl.datagrid('selectRecord', role['id']);
			});
		},

		/**
		 * 设置用户选中的显示值
		 * @param roles
		 * @returns
		 */
		setRoleText: function(){
			var showRoles = [];
			$.each(currFunRoles, function(idx, role){
				showRoles.push(role['name'] + "【"+ role['smOrg']['name'] +"】");
			});
			selctedRoleEl.val(showRoles.join('\n'));
		}
		
	};
}();
