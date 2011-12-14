package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.MsgVO;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.system.SmFunction;
import cn.com.widemex.service.system.SmFunctionService;

/**
 * 功能维护controller
 *
 * @author 张中原
 *
 */
@Controller
@RequestMapping("sys/fun")
public class FunctionController {
	/**日志*/
	private Logger logger = Logger.getLogger(FunctionController.class);
	
	/**功能维护service**/
	private SmFunctionService smFunctionService;

	/**
	 * 初始化界面
	 * @return
	 */
	@RequestMapping
	public String init(){
		return "system/function/fun";
	}
	
	/**
	 * 初始化功能排序界面
	 * @return
	 */
	@RequestMapping("initFunOrder")
	public String initFunOrderPage(){
		return "system/function/funOrder";
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list(HttpServletRequest request){
		return this.smFunctionService.list(PageVO.valueOf(request));
	}
	
	/**
	 * 一次性查询（在业务中，有需要）
	 * @param request
	 * @return
	 */
	@RequestMapping("listOnce")
	@ResponseBody
	public Object listOnce(HttpServletRequest request){
		return this.smFunctionService.listOnce(PageVO.valueOf(request));
	}
	
	
	/**
	 * 保存记录
	 * @param pojo
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(SmFunction pojo){
		return this.smFunctionService.save(pojo);
	}
	
	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	@RequestMapping("remove")
	@ResponseBody
	public Object remove(SmFunction pojo){
		MsgVO msg = MsgVO.valueOf(true);
		
		try {
			return this.smFunctionService.remove(pojo);
		} catch (Exception e) {
			e.printStackTrace();
			String mess = "当前记录被相关业务表使用，请先确认！";
			logger.error(mess);
			msg.setMessage(mess);
			
			msg.setSuccess(false);
		}
		
		return msg;
	}
	
	/**
	 * 是否已经存在相同编码的记录
	 * @param pojo
	 * @return
	 */
	@RequestMapping("isHasRecord")
	@ResponseBody
	public Object isHasRecord(SmFunction pojo){
		return MsgVO.valueOf(this.smFunctionService.isHasRecord(pojo));
	}

	
	public SmFunctionService getSmFunctionService() {
		return smFunctionService;
	}

	public void setSmFunctionService(SmFunctionService smFunctionService) {
		this.smFunctionService = smFunctionService;
	}
	
	
	
}
