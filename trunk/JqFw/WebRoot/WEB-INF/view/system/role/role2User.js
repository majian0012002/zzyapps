Role2User = function(){
	//定义私有变量
	var showUserBtnEl/*显示用户维护弹出框的按钮*/,
		userWinEl/*用户弹出框*/,
		userOrgTreeEl/*显示用户组织结构树*/,
		userGridEl/*用户表格*/,
		selctedUserEl/*已选用户显示文本框*/,
		userOKBtnEl/*用户保存按钮*/;
	
	/*当前角色的相关用户*/
	var currRoleUsers = [];
	/*是否是自动选中表格*/
	var isAutoSelect = false;
	
	return{
		/**
		 * 初始化模块
		 * @returns
		 */
		init: function(){
			showUserBtnEl = $('#showUserBtn');
			userWinEl = $('#userWin');
			userOrgTreeEl = $('#userOrgTree');
			userGridEl = $('#userGrid');
			selctedUserEl = $('#selctedUser');
			userOKBtnEl = $('#userOKBtn');
			
			Role2User.initEvent();
		},
		/**
		 * 初始化监听事件
		 * @returns
		 */
		initEvent: function(){
			showUserBtnEl.click(function(){
				var row = Role.getRoleSelected();
				
				if(!row){
					$.msg.say("请先选择要设置功能的角色!");
					return;
				}
				
				currRoleUsers = row['smUsers'] ? row['smUsers'] : [];
				Role2User.setUserGridSelect();
				Role2User.setUserText();
				userWinEl.window('open');
			});
			
			userOKBtnEl.click(function(){
				$.msg.cfm("是否确定保存当前角色设置的关联用户?", function(a){
					if(a){
						Role2User.doSaveRole2User();
					}
				});
			});
		},
		/**
		 * 用户查询
		 * @returns
		 */
		queryUserParams: function(){
			var org = userOrgTreeEl.treegrid('getSelected');
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
				//Role2User.onSearch(row);
				userGridEl.datagrid('reload', {status: 1});
			}
		},
		
		/**
		 * 用户表格事件
		 */
		userGridEvent: {
			onSelect: function(idx, row){
				if(currRoleUsers.indexOfByProp('id', row['id']) == -1){
					currRoleUsers.push(row);
					Role2User.setUserText();
				}
				
			},
			onUnselect: function(idx, row){
				currRoleUsers.remove(row);
				Role2User.setUserText();
			},
			onLoadSuccess: function(){
				Role2User.setUserGridSelect();
			}
		},
		
		/**
		 * 保存角色和用户的关联信息
		 * @returns
		 */
		doSaveRole2User: function(){
			var role = Role.getRoleSelected();
			role['smFuns'] = $.fetchListProp(role['smFuns'], 'id');
			role['smUsers'] = $.fetchListProp(currRoleUsers, 'id');
			
			Role.doSave(role);
		},
		
		/**
		 * 设置用户选中
		 * @param users
		 * @returns
		 */
		setUserGridSelect: function(){
			$.each(currRoleUsers, function(idx, user){
				userGridEl.datagrid('selectRecord', user['id']);
			});
		},

		/**
		 * 设置用户选中的显示值
		 * @param users
		 * @returns
		 */
		setUserText: function(){
			var showUsers = [];
			$.each(currRoleUsers, function(idx, user){
				showUsers.push(user['name'] + "【"+ user['smOrg']['name'] +"】");
			});
			selctedUserEl.val(showUsers.join('\n'));
		}
		
	};
}();
