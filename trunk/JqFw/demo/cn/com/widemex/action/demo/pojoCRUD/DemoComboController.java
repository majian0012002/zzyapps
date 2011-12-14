package cn.com.widemex.action.demo.pojoCRUD;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.service.demo.DemoTypeService;

/**
 * 下拉框
 * @author 张中原
 *
 */
@Controller
@RequestMapping("demo/combo/")
public class DemoComboController {
	
	private DemoTypeService demoTypeService;
	
	/**
	 * 初始化界面
	 * @return
	 */
	@RequestMapping("init")
	public String init(){
		return "demo/form/combo";
	}
	
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		return this.demoTypeService.list(PageVO.valueOf(request));
	}

	public DemoTypeService getDemoTypeService() {
		return demoTypeService;
	}

	public void setDemoTypeService(DemoTypeService demoTypeService) {
		this.demoTypeService = demoTypeService;
	}
	
}
