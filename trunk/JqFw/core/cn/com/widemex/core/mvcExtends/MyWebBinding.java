package cn.com.widemex.core.mvcExtends;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import cn.com.widemex.core.mvcExtends.editor.DateConvertEditor;

/**
 * 定制WEB绑定
 * @author 张中原
 *
 */
public class MyWebBinding implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		//1. 使用spring自带的CustomDateEditor
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		// 注册Date编辑器
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		
		// 注册Timestamp编辑器
		binder.registerCustomEditor(Timestamp.class, new DateConvertEditor());

	}

}
