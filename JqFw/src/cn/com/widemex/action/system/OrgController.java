package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.widemex.core.vo.MsgVO;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.system.SmOrg;
import cn.com.widemex.service.system.SmOrgService;
import cn.com.widemex.service.system.SmOrgTypeService;

/**
 * 组织机构维护
 * @author 张中原
 *
 */

@Controller
@RequestMapping("sys/org")
public class OrgController {
	/**日志*/
	Logger logger = Logger.getLogger(OrgController.class);
	
	/**机构维护service*/
	private SmOrgService smOrgService;
	/**机构类型service**/
	private SmOrgTypeService smOrgTypeService;

	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping
	public String init(Model model){
		model.addAttribute("orgTypeList", this.smOrgTypeService.list4ValidData());
		return "system/org/org";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		return this.smOrgService.list(PageVO.valueOf(request));
	}
	
	/**
	 * 保存
	 * @param org
	 * @param oldId
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save(SmOrg org, String oldId){
		this.smOrgService.save(org);		
		return org;
	}
	
	/**
	 * 删除
	 * @param org
	 * @return
	 */
	@RequestMapping("remove")
	public @ResponseBody Object remove(SmOrg org){
		MsgVO msg = MsgVO.valueOf(true, null, org);
		
		try {
			this.smOrgService.remove(org);
		} catch (Exception e) {
			String mess = "当前记录以及被相关子表使用，请确定!";
			logger.error(mess);
			msg.setSuccess(false);
			msg.setMessage(mess);
		}
		
		return msg;
	}

	/**
	 * 是否已经存在当前新增的编码
	 * @param id
	 * @return
	 */
	@RequestMapping("isHasRecord")
	public @ResponseBody Object isHasRecord(SmOrg org){
		 return MsgVO.valueOf(this.smOrgService.isHasRecord(org.getId()));
	}

	public SmOrgService getSmOrgService() {
		return smOrgService;
	}

	public void setSmOrgService(SmOrgService smOrgService) {
		this.smOrgService = smOrgService;
	}

	public SmOrgTypeService getSmOrgTypeService() {
		return smOrgTypeService;
	}

	public void setSmOrgTypeService(SmOrgTypeService smOrgTypeService) {
		this.smOrgTypeService = smOrgTypeService;
	}
	
	
	
}
