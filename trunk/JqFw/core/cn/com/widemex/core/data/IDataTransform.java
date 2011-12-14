package cn.com.widemex.core.data;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 数值转换接口
 * @author 张中原
 *
 */
public interface IDataTransform {
	

	/**
	 * 获取字符串类型的数据
	 * @param propName bean的属性名
	 */
	public  String getString(String propName);

	/**
	 * 获取int数据
	 * @param propName	bean的属性名
	 * @return
	 */
	@Deprecated
	public  Integer getInt(String propName);

	/**
	 * 获取int数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  Integer getInteger(String propName);	

	/**
	 * 获取float数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  float getFloat(String propName);

	/**
	 * 获取double数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  double getDouble(String propName);

	/**
	 * 获取long数据
	 * @param propName bean的属性名
	 * @return
	 */
	public  long getLong(String propName);

	/**
	 * 获取boolean 数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  boolean getBoolean(String propName);

	/**
	 * 获取BigDecimal数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  BigDecimal getBigDecimal(String propName);

	/**
	 * 获取SQL DATE 数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  java.sql.Date getSqlDate(String propName);

	/**
	 * 获取util Date 数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  Date getDate(String propName);

	/**
	 * 获取Time数据
	 * @param propName		bean的属性名
	 * @return
	 */
	public  Time getTime(String propName);

	/**
	 * 获取Timestamp 数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public  Timestamp getTimestamp(String propName);
	
	/**
	 * 获取字符串类型的数据
	 * @param propName bean的属性名
	 * @param defaultValue 默认值
	 */
	public  String getString(String propName, String defaultValue);

	/**
	 * 获取int数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  int getInt(String propName, int defaultValue);

	/**
	 * 获取float数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  float getFloat(String propName, float defaultValue);

	/**
	 * 获取double数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  double getDouble(String propName, double defaultValue);

	/**
	 * 获取long数据
	 * @param propName bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  long getLong(String propName, long defaultValue);

	/**
	 * 获取boolean 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  boolean getBoolean(String propName, boolean defaultValue);

	/**
	 * 获取BigDecimal数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  BigDecimal getBigDecimal(String propName, BigDecimal defaultValue);

	/**
	 * 获取SQL DATE 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  java.sql.Date getSqlDate(String propName, java.sql.Date defaultValue);

	/**
	 * 获取util Date 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Date getDate(String propName, Date defaultValue);

	/**
	 * 获取Time数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Time getTime(String propName, Time defaultValue);

	/**
	 * 获取Timestamp 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Timestamp getTimestamp(String propName, Timestamp defaultValue);
}

