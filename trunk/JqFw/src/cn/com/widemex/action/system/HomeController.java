package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.widemex.service.system.SmFunctionService;

@Controller
@RequestMapping("sys")
public class HomeController {
	/**功能service*/
	private SmFunctionService smFunctionService;
	
	/**
	 * 初始化首页
	 * @param model
	 * @return
	 */
	@RequestMapping("home")
	public String initHome(Model model){
		return "home";
	}

	public SmFunctionService getSmFunctionService() {
		return smFunctionService;
	}

	public void setSmFunctionService(SmFunctionService smFunctionService) {
		this.smFunctionService = smFunctionService;
	}
	
	

}
