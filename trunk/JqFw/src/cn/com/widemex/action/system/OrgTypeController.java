package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.MsgVO;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.system.SmOrgType;
import cn.com.widemex.service.system.SmOrgTypeService;
/**
 * 组织机构类型维护 controller
 *
 * @author 张中原
 *
 */
@Controller
@RequestMapping("sys/orgType")
public class OrgTypeController {
	/**日志*/
	Logger logger = Logger.getLogger(OrgTypeController.class);
	
	
	/**组织结构类型 service*/
	private SmOrgTypeService smOrgTypeService;

	/**
	 * 初始化界面
	 * @return
	 */
	@RequestMapping
	public String init(){
		return "system/orgType/orgType";
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		PageVO page = PageVO.valueOf(request);
		return this.smOrgTypeService.list(page);
	}
	
	/**
	 * 获取有效机构类别信息
	 * @param request
	 * @return
	 */
	@RequestMapping("list4ValidData")
	public @ResponseBody Object list4ValidData(){
		return this.smOrgTypeService.list4ValidData();
	}
	
	/**
	 * 保存或更新值
	 * @param pojo
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save(SmOrgType pojo){
		this.smOrgTypeService.save(pojo);
		return pojo;
	}
	
	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	@RequestMapping("remove")
	public @ResponseBody Object remove(SmOrgType pojo){
		MsgVO msg = new MsgVO();
		
		msg.setRows(pojo);
		try {
			this.smOrgTypeService.remove(pojo);
			msg.setSuccess(true);
		} catch (Exception e) {
			String mess = "当前记录以及被相关子表使用，请确定!";
			logger.error(mess);
			msg.setSuccess(false);
			msg.setMessage(mess);
		}
		return msg;
	}
	
	/**
	 * 是否已经已经存在当前记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping("isHasRecord")
	public @ResponseBody Object isHasRecord(HttpServletRequest request){
		MsgVO msg = MsgVO.valueOf(request);
		msg.setSuccess(this.smOrgTypeService.isHasRecord(msg.getParams().getString("orgTypeId")));
		return msg;
	}
	

	public SmOrgTypeService getSmOrgTypeService() {
		return smOrgTypeService;
	}

	public void setSmOrgTypeService(SmOrgTypeService smOrgTypeService) {
		this.smOrgTypeService = smOrgTypeService;
	}

	
}
