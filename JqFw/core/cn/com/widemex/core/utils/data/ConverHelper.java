package cn.com.widemex.core.utils.data;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import cn.com.widemex.core.utils.reflection.ReflectionUtils;

/**
* <p>类名: TypeConverUtils</p>
* <p>描述: 类型转换的相关静态方法</p> 
* @author    张中原
* @date       Jul 7, 2010
 */
public class ConverHelper {
	
	/**
	* <p>方法名: clob2String</p>
	* <p>描述:将Clob类型转换成String 类型 </p>
	* @param clob	clob大文本对象
	* @return
	 */
	public static String clob2String( Clob clob){
		if(clob == null)return null;
		
		try {
			return clob.getSubString((long)1,(int)clob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* <p>方法名: object2String</p>
	* <p>描述: 将对象转换成字符串</p>
	* @param obj	要转换的对象
	* @return
	 */
	public static String object2String(Object obj){
		if(obj == null) return null;
		
		//如果是Clob大文本对象
		if(obj.getClass().getName().indexOf("CLOB") != -1){
			return clob2String((Clob)(obj));
		}
		
		return obj.toString();
	}
	
	/**
	* <p>方法名: object2Long</p>
	* <p>描述: 将日期时间对象转换成long类型对象</p>
	* @param obj 要转换的对象
	* @return
	 */
	public static Long dateTimeObject2Long(Object obj){
		if(obj == null)return null;
		
		if(obj.getClass() == java.sql.Date.class){
			return ((java.sql.Date)obj).getTime();
		}else if(obj.getClass() == Time.class){
			return ((Time)obj).getTime();
		}else if( obj.getClass() == Date.class){
			return ((Date)obj).getTime();
		}else{		//if(obj.getClass() == Timestamp.class)
			try {
				return ((Timestamp)obj).getTime();
			} catch (RuntimeException e) {
				throw new RuntimeException("时间日期类型转换错误，请确定你所获取的值是否是日期或者是时间类型");
			}
		}
	}
	
	/**
	* <p>方法名: dateTimeObject2SqlDate</p>
	* <p>描述: 将日期时间对象转换成sql.Date对象</p>
	* @param obj	要转换的对象
	* @return
	 */
	public static java.sql.Date dateTimeObject2SqlDate(Object obj){
		if(obj == null)return null;
		
		if(obj.getClass() == java.sql.Date.class ) return (java.sql.Date)obj;
		return new java.sql.Date(dateTimeObject2Long(obj));
	}
	
	/**
	* <p>方法名: dateTimeObject2UtilDate</p>
	* <p>描述: 将日期时间对象转换成util.Date对象</p>
	* @param obj	要转换的对象
	* @return
	 */
	public static java.util.Date dateTimeObject2UtilDate(Object obj){
		if(obj == null)return null;
		
		if(obj.getClass() == Date.class) return (Date)obj;
		return new Date(dateTimeObject2Long(obj));
	}
	
	/**
	* <p>方法名: dateTimeObject2Time</p>
	* <p>描述: 将日期时间对象转换成Time对象</p>
	* @param obj	要转换的对象
	* @return
	 */
	public static Time dateTimeObject2Time(Object obj){
		if(obj == null)return null;
		
		if(obj.getClass() == Time.class) return (Time)obj;
		return new Time(dateTimeObject2Long(obj));
	}
	
	/**
	* <p>方法名: dateTimeObject2Timestamp</p>
	* <p>描述: 将日期对象转换成Timestamp对象</p>
	* @param obj	要转换的对象
	* @return
	 */
	public static Timestamp dateTimeObject2Timestamp(Object obj){
		if(obj == null)return null;
		
		if(obj.getClass() == Timestamp.class)return (Timestamp)obj;
		return new Timestamp(dateTimeObject2Long(obj));
	}
	
	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value 待转换的字符串.
	 * @param toType 转换目标类型.
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
	}
}
