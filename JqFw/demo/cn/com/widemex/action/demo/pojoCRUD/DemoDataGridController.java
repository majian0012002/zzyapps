package cn.com.widemex.action.demo.pojoCRUD;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.demo.DemoDept;
import cn.com.widemex.domain.demo.DemoUser;
import cn.com.widemex.service.demo.DemoUserService;

/**
 * DEMO：通过hibernate对datagrid进行CRUD
 * @author 张中原
 *
 */
@Controller
@RequestMapping("demo/pojo/datagrid")
public class DemoDataGridController {

	private DemoUserService demoUserService;

	/**
	 * 初始化界面
	 */
	@RequestMapping("init")
	public String init(){
		return "demo/datagrid/pojoCRUD/datagrid";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		PageVO page = PageVO.valueOf(request);
		return this.demoUserService.list(page);
	}
	
//	public String toTree(Collection<DemoDept> deptList){
//		for(DemoDept dept : deptList){
////			out.println("<li>");
////			out.println("	<span>" + dept.getName() + "</span>");
////			
////			if(dept.getDemoDepts()!=null){
////				out.println("   <lu>");
////				toTree(dept.getDemoDepts());
////				out.println("   </lu>");
////			}
////			
////			out.println("</li>");
//		
//		
//			
//		}
//		return null;
//	}
	
	/**
	 * 更新、保存
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save(@RequestBody DemoUser user){
		return this.demoUserService.save(user);
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("remove")
	public @ResponseBody Object remove(@RequestBody DemoUser user){
		return this.demoUserService.remove(user);
	}	
	

	public DemoUserService getDemoUserService() {
		return demoUserService;
	}

	public void setDemoUserService(DemoUserService demoUserService) {
		this.demoUserService = demoUserService;
	}
	
}
