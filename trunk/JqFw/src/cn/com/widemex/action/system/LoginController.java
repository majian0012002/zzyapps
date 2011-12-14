package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.domain.system.SmUser;
import cn.com.widemex.service.system.LoginService;

/**
 * 登录
 * @author 张中原
 *
 */
@Controller
@RequestMapping("sys")
public class LoginController {
	/**登录service*/
	private LoginService loginService;
	
	@RequestMapping("checkLogin")
	public @ResponseBody Object checkLogin(@RequestParam String acc, @RequestParam String pwd, HttpServletRequest request){
		
		boolean flag = false;
		SmUser user = this.loginService.getUser(acc, pwd);
		if(user != null){
//			Map<String, SmFunction> funMap = this.loginService.getMapFun(user);
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", user);
//			session.setAttribute("USER_FUN_MAP", funMap);
			session.setAttribute("USER_FUN_LIST", this.loginService.getUserFunList(user));
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public @ResponseBody boolean logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session != null){
			session.invalidate();
		}
		return true;
	}
	
	

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
