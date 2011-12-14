package cn.com.widemex.core.vo;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import cn.com.widemex.core.data.MyHashMap;
import cn.com.widemex.core.data.MyMap;
import cn.com.widemex.core.utils.web.WebUtils;

/**
 * 消息返回对象
 * Restful是用到
 * @author 张中原
 *
 */
public class MsgVO {
	/**日志实例化*/
//	private static Logger logger = Logger.getLogger(MsgVO.class);
	
	/**是否执行成功*/
	private boolean success = true;
	
	/**执行消息 */
	private String message = "";
	
	/**返回数据*/
	private Object rows;

	/**前台传输的参数*/
	private MyMap params;
	
	/**空构造函数*/
	public MsgVO(){};
	
	/**
	 * 有参构造函数：请求对象转换成参数
	 * @param request 请求对象
	 */
	public MsgVO(HttpServletRequest request){
		this.params = new MyHashMap();
		for (Enumeration<String> em = request.getParameterNames(); em.hasMoreElements();) {
			String key = em.nextElement();
			this.params.put(key, request.getParameter(key));
		}
	}
	
	/**
	 * 获取MsgVO对象
	 * @param request 请求对象
	 * @return
	 */
	public static MsgVO valueOf(HttpServletRequest request){
		MsgVO vo = new MsgVO( request );
		return vo;
	}

	/**
	 * 获取MsgVO对象
	 * @param success 是否成功
	 * @return
	 */
	public static MsgVO valueOf(Object results){
		MsgVO vo = new MsgVO();
		vo.setRows(results);
		return vo;
	}
	
	/**
	 * 获取MsgVO对象
	 * @param success 是否成功
	 * @return
	 */
	public static MsgVO valueOf(boolean success){
		MsgVO vo = new MsgVO();
		vo.setSuccess(success);
		return vo;
	}
	
	/**
	 * 获取MsgVO对象
	 * @param success 是否成功
	 * @param message 消息内容
	 * @return
	 */
	public static MsgVO valueOf(boolean success, String message){
		MsgVO vo = new MsgVO();
		vo.setSuccess(true);
		vo.setMessage(message);
		return vo;
	}
	
	/**
	 * 获取MsgVO对象
	 * @param success 是否成功
	 * @param message 消息内容
	 * @param data 消息数据
	 * @return
	 */
	public static MsgVO valueOf(boolean success, String message, Object results){
		MsgVO vo = new MsgVO();
		vo.setSuccess(true);
		vo.setMessage(message);
		vo.setRows(results);
		return vo;
	}
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	public Object getData() {
//		return data;
//	}
//	public void setData(Object data) {
//		this.data = data;
//	}
	
	public MyMap getParams() {
		return params;
	}

	public void setParams(MyMap params) {
		this.params = params;
	}
	

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
//	public Object getResults() {
//		return results;
//	}
//
//	public void setResults(Object results) {
//		results = results==null ? new ArrayList() : results;
//		this.results = results;
//	}
}
