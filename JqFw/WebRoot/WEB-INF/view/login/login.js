Login = function(){
//	var accField, pwdField;
	var accEl, pwdEl, savePwdEl, loginFormEl;
	return{
		/**
		 * 初始化信息
		 */
		init: function(){
			// 初始化，要引用的组件
			using(['form', 'msg'], Login.initCom);
			
			loginFormEl = $('#loginForm');
			accEl = loginFormEl.find('input[name=acc]');
			pwdEl = loginFormEl.find('input[name=pwd]');
			savePwdEl = loginFormEl.find('input[name=savePwd]');
			
			Login.initEvent();
		},
		
		/**
		 * 初始化组件
		 */
		initCom: function(){
			//cookie用户信息
			var user = $.cookie('user');			
			loginFormEl.form('load', $.decode(user));		
		},
		
		/**
		 * 初始化事件
		 */
		initEvent: function(){
			$('input[name=acc]').focus();
			$('#loginBtn').click(Login.loginCheck);
			$('#resetBtn').click(Login.reset);
		},
		/**
		 * 重置登录信息
		 */
		reset: function(){
			loginFormEl.form('clear');	
		},
		
		/**
		 * 登录校验
		 */
		loginCheck: function(){
			loginFormEl.form('ajax', {
			  url: 'sys/checkLogin',
			  onSubmit: function(){
			  	var isValid = $(this).form('validate');
			  	if(isValid){
			  		// 加载信息
			  		$('#checkLoadingDiv').show();
			  		// 表单不可用
			  		$(this).disable();
			  	}
			  	return isValid;
			  },
			  success: function(data) {
			  	loginFormEl.enable();
			  	$('#checkLoadingDiv').hide();
				
				if(data == true){
					$.msg.say("登录成功!");
					
					if(savePwdEl.attr('checked')){
						$.cookie('user', $.encode({
							acc: accEl.val(),
							pwd: pwdEl.val(),
							savePwd: savePwdEl.val()
						}));
					}else{
						$.removeCookie('user');
					}
					
					// 刷新界面
					location.reload();
//					window.location = window.location;//easyloader.base.replace('resource/easyui/','');
				}else{
					$.msg.say("登录失败，请确认你的账号密码是否正确!");
					Login.reset();
				}
			  }
			});	
		}
	};
	
}();


/**
 * 初始化组件
 */
$(Login.init);
