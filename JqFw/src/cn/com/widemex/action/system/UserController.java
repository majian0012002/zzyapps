package cn.com.widemex.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.widemex.core.vo.MsgVO;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.domain.system.SmUser;
import cn.com.widemex.service.system.SmUserService;

/**
 * 用户维护
 * @author 张中原
 *
 */
@Controller
@RequestMapping("sys/user")
public class UserController {
	/**日志**/
	private static Logger logger = Logger.getLogger(UserController.class);
	
	/**用户维护service**/
	private SmUserService smUserService;

	/**
	 * 初始化用户维护界面
	 * @return
	 */
	@RequestMapping
	public String init(){
		return "system/user/user";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(HttpServletRequest request){
		return this.smUserService.list(PageVO.valueOf(request));
	}
	
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save(SmUser pojo){
		return this.smUserService.save(pojo);
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("remove")
	public @ResponseBody Object remove(SmUser pojo){
		MsgVO msg = MsgVO.valueOf(true, null, pojo);
		try {
			this.smUserService.remove(pojo);
		} catch (Exception e) {
			String mess = "当前用户已经其他子表或者业务表使用，不能直接删除,请确定!";
			e.printStackTrace();
			logger.error(mess);
			msg.setSuccess(false);
			msg.setMessage(mess);
		}
		return msg;
	}
	
	/**
	 * 是否已经存在相同的记录
	 * @param pojo
	 * @return
	 */
	@RequestMapping("isHasRecord")
	public @ResponseBody boolean isHasRecord(SmUser pojo){
		return this.smUserService.isHasRecord(pojo.getId());
	}

	public SmUserService getSmUserService() {
		return smUserService;
	}

	public void setSmUserService(SmUserService smUserService) {
		this.smUserService = smUserService;
	}	
	
	
}
