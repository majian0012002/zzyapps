/**
 * 主界面Top操作
 */
Wide['Top'] = function(){
	var quitSysBtnEl, deskBtnEl, skinBtnEl, 
		userFormWinEl, userFormEl, modiPwdEl,
		userSaveBtnEl;
	return {
		/**
		 * 初始化界面
		 */
		init: function(){
			deskBtnEl = $('#deskBtn');
			skinBtnEl = $('#skinBtn');
			quitSysBtnEl = $('#quitSysBtn');
			userFormWinEl = $('#userFormWin');
			userFormEl = $('#userForm');
			modiPwdEl = $('#modiPwd');
			userSaveBtnEl = $('#userSaveBtn');
			
			Wide['Top'].initCom();
			Wide['Top'].initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			userFormWinEl.window({
				onClose: function(){
//					userFormEl.form('clear');
				},
				onOpen: function(){
					userFormEl.form('clear');
					userFormEl.form('load', USER_INFO);
					userFormEl.find('input[name=smOrgText]').val(USER_INFO['smOrg']['name']);
				}
			});
		},
		
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			// 监听换肤事件
			$('div[id*=skin-]').click(function(){
				var skinName = this.id.replace('skin-', '');
				Wide.changeSkin(skinName);
			});
			
			quitSysBtnEl.click(function(){
				$.msg.cfm("是否退出本系统?", function(a){
					if(a){
						// 注销session
						$.crud('sys/logout',null, function(){
							window.location.reload();
						});
					}
				});
			});
			
			modiPwdEl.click(function(){
				userFormWinEl.window('open');
			});
			
			userSaveBtnEl.click(function(){
				// 验证是否正常
				if(userFormEl.form('validate')){
					$.msg.cfm('是否保存您个人信息?', function(a){
						if(a){
							var user = $.extend(USER_INFO, {
								name: userFormEl.form('findField', 'name').val(),
								pwd: userFormEl.form('findField', 'pwd').val(),
								phone: userFormEl.form('findField', 'phone').val(),
								email: userFormEl.form('findField', 'email').val(),
								remark: userFormEl.form('findField', 'remark').val()
							});
							
							user['smOrg'] = {id: user['smOrg']['id']};
							user['smRoles'] = $.fetchListProp(user['smRoles'], 'id');
							
							$.crud('sys/user/save', user, function(a){
								userFormWinEl.window('close');
								$.msg.say('恭喜<span class=red>'+ a['name'] +'</span>,您的个人信息修改成功!')
								
								$.msg.cfm('系统需要重新登录，才可以刷新个人信息?', function(a){
									if(a){
										// 注销session
										$.crud('sys/logout',null, function(){
											window.location.reload();
										});
									}
								});
							})						
						}
					});
				}
			});
			
			deskBtnEl.click(function(){
				$.msg.say($.blue('暂不支持该功能'))
			});
			
			skinBtnEl.click(function(){
				$.msg.say($.blue('暂不支持该功能'));
			});
			
			
		}
	};
}();