package cn.com.widemex.core.mvcExtends.editor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import cn.com.widemex.core.utils.data.DateHelper;

/**
 * 日期编辑器
 * @author 张中原
 *
 */
public class DateConvertEditor extends PropertyEditorSupport {
	/**日志实例化*/
	private static Logger logger = Logger.getLogger(DateConvertEditor.class);
	
//	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void setAsText(String text) throws IllegalArgumentException {
		logger.info("DateConvertEditor被调用....");
		
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(DateHelper.DATE_FORMAT.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(DateHelper.TIMESTAMP_FORMAT.parse(text));
				}else{
					throw new IllegalArgumentException("Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}
}
