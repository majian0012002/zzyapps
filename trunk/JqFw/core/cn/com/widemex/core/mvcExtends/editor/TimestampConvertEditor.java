package cn.com.widemex.core.mvcExtends.editor;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 日期编辑器
 * @author 张中原
 *
 */
public class TimestampConvertEditor extends PropertyEditorSupport {
	/**日志实例化*/
	private static Logger logger = Logger.getLogger(TimestampConvertEditor.class);
	
	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void setAsText(String text) throws IllegalArgumentException {
		logger.info("TimestampConvertEditor被调用....");
		
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(new Timestamp(this.dateFormat.parse(text).getTime()));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(new Timestamp(this.datetimeFormat.parse(text).getTime()));
				}else{
					throw new IllegalArgumentException("不能转换为Timestamp格式，当前字符串格式不准确。value：：" + text);
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException("不能正常转换TimeStamp: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}
}
