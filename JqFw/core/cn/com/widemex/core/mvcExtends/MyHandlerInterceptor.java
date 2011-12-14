package cn.com.widemex.core.mvcExtends;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.widemex.core.utils.reflection.Bean;

/**
 * 定制拦截器，主要是权限管理及登录校验
 * @author 张中原
 *
 */
public class MyHandlerInterceptor extends HandlerInterceptorAdapter {
	/**
	 * easyui标签的相关字符串
	 */
	private static String jqFW = null;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
			
		Bean.printReq(request);
		
		if(jqFW == null){
			ServletContext sc = request.getServletContext();
			String isDebug = sc.getInitParameter("isDebug");
			
			String rootPath = sc.getContextPath();
			
			String str = 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/gray/easyui.css\" rel=\"stylesheet\" title=\"gray\"> \n" +
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/green/easyui.css\" rel=\"stylesheet\" title=\"green\"> \n"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/orange/easyui.css\" rel=\"stylesheet\" title=\"orange\"> \n"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/default/easyui.css\" rel=\"stylesheet\" title=\"blue\"> \n"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/pink/easyui.css\" rel=\"stylesheet\" title=\"pink\"> \n";
			
			
			if("true".equals(isDebug)){
				str	+=	//"	<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/default/easyui.css" + "\" />  \n" +
						" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/easyui/jquery-1.6.min.js" + "\"></script> \n" +
						" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/easyui/easyloader.js" + "\"></script> \n" +
						" 	<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/icon.css" + "\" /> \n";
			}else{
				str	=	//"	<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/default/easyui.css" + "\" />  \n" +
						" 	<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rootPath + "/resource/themes/icon.css" + "\" /> \n" +
						" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/easyui/jquery-1.6.min.js" + "\"></script> \n" +
						" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/easyui/jquery.easyui.min.js" + "\"></script> \n" +
						" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/easyui/locale/easyui-lang-zh_CN.js" + "\" />\"></script>\n";
			}
			
			str +=	" 	<script type=\"text/javascript\" src=\"" + rootPath + "/resource/wide/Wide.js" + "\" />\"></script>\n";
			
			
			System.out.println("jqFW::::" + str);
			
			jqFW = str;
			
			// 设置application中
			sc.setAttribute("jqFW", jqFW);
			
			sc.setAttribute("path", request.getContextPath());
			
		}
		
		String url = request.getRequestURI();
		System.out.println("url::::" + url);
		
		
		if(url.indexOf("/sys/checkLogin") != -1){
			return true;
		}else if(request.getSession() != null && request.getSession().getAttribute("USER_INFO")==null){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;	
	}
	
}
