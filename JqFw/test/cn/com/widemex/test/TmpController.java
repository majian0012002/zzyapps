package cn.com.widemex.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.widemex.core.vo.PageVO;

/**
 *   XX 维护 controller类
 *
 * @author 张中原
 *
 */
@Controller("模块名/功能名")
public class TmpController {
	
	/**
	 * 初始化界面
	 * @return
	 */
	@RequestMapping
	public String init(){
		return "demo/form/combo"; //jsp所在的模块文件夹的相关路径,
								  //注：路径最后是jsp不要加.jsp后缀
	} 
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public Object list(HttpServletRequest request){
		PageVO page = PageVO.valueOf(request);
		
		// 查询处理...
		
		return page;
	}
	
	/**
	 * 保存或更新值
	 * @param pojo
	 * @return
	 */
	@RequestMapping("save")
	public Object save(Object pojo){
		//保存...
		return pojo;
	}
	
	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	@RequestMapping("remove")
	public Object remove(Object pojo){
		// 删除。。。
		
		return pojo;
	}
	

}
