package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.system.SmRole;
import cn.com.widemex.service.system.SmRoleService;

/**
 *角色维护 controller类
 *
 * @author 张中原
 *
 */
@Controller
@RequestMapping("sys/role")
public class RoleController {
	/**日志*/
	private static Logger logger = Logger.getLogger(RoleController.class);
	/**角色维护service*/
	private SmRoleService smRoleService;
	
	/**
	 * 初始化界面
	 * @return
	 */
	@RequestMapping
	public String init(){
		return "system/role/role";
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list(HttpServletRequest request){
		return this.smRoleService.list(PageVO.valueOf(request));
	}
	
	/**
	 * 保存、更新
	 * @param pojo
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(SmRole pojo){
		return this.smRoleService.save(pojo);
	}
	
	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	@RequestMapping("remove")
	@ResponseBody
	public Object remove(SmRole pojo){
		return this.smRoleService.remove(pojo);
	}
	
	
	public SmRoleService getSmRoleService() {
		return smRoleService;
	}
	public void setSmRoleService(SmRoleService smRoleService) {
		this.smRoleService = smRoleService;
	}
	
	
}
