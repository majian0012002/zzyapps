package cn.com.widemex.action.demo.pojoCRUD;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.utils.reflection.Bean;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.demo.DemoDept;
import cn.com.widemex.service.demo.DemoDeptService;
/**
 * DEMO：树表格
 * @author 张中原
 */
@Controller
@RequestMapping("demo/pojo/treegrid/")
public class DemoTreeGridController {
	/**部门维护serivce类*/
	private DemoDeptService demoDeptService;
	
	/**
	 * 初始化界面
	 */
	@RequestMapping("init")
	public String initPojoTreeGrid(){
		return "demo/treegrid/pojoCRUD/crud";
	}
	
	/**
	 * 初始化界面
	 */
	@RequestMapping("initTree")
	public String initPojoTree(Model model){
		// 初始化jsp界面
		PageVO page = this.demoDeptService.list(new PageVO());
		
		model.addAttribute("deptList", page.getRows());
		
		return "demo/tree/tree";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		PageVO page = PageVO.valueOf(request);
		
		return this.demoDeptService.list(page);
//		return "{total:2, rows:[{id:111, name:'22222222222', code:333333, status:1, createTime:'2011-11-14 23:24:25'}]}";
	}
	
	/**
	 * 更新、保存
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save( @RequestBody DemoDept dept){
//		Bean.printValues(dept);
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
		     
		this.demoDeptService.save(dept);
		
		return dept;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("remove")
	public @ResponseBody Object delete( @RequestBody DemoDept dept){
		return this.demoDeptService.delete(dept);
	}	
	
	
	public DemoDeptService getDemoDeptService() {
		return demoDeptService;
	}

	public void setDemoDeptService(DemoDeptService demoDeptService) {
		this.demoDeptService = demoDeptService;
	}

}
