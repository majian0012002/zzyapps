package cn.com.widemex.core.utils.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import cn.com.widemex.core.data.MyHashMap;
import cn.com.widemex.core.data.MyMap;

/**
 * WEB相关的工具
 * (1)、存储session信息
 * @author 张中原
 *
 */
public class WebUtils {
	public final static String  OPERATE_TYPE_ADD = "0";
	public final static String  OPERATE_TYPE_UPDATE = "1";
	public final static String  OPERATE_TYPE_DELETE = "2";
	
	public final static String  RESULT_FAILED = "0";
	public final static String  RESULT_SUCCESS = "1";
	

	/**
	 * 获取参数map对象
	 * @param request 请求对象
	 * @return
	 */
	public static MyMap getParamsMap(HttpServletRequest request){
		MyMap map = new MyHashMap();
		for (Enumeration<String> em = request.getParameterNames(); em.hasMoreElements();) {
			String key = em.nextElement();
			map.put(key, request.getParameter(key));
		}
		
		return map;
	}
}
