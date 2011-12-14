package cn.com.widemex.core.data;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import cn.com.widemex.core.utils.data.ConverHelper;

public class MyHashMap extends HashMap<String, Object> implements MyMap {
	
	/**
	 * 获取字符串类型的数据
	 * @param propName bean的属性名
	 */
	public String getString(String propName	){
		
		return ConverHelper.object2String(this.get(propName));
		
	}
	
	/**
	 * 获取int数据
	 * @param propName	bean的属性名
	 * @return
	 */
	@Deprecated
	public Integer getInt(String propName){
		return this.getInteger(propName);
	}
	
	/**
	 * 获取Integer数据(修正null问题)
	 * @param propName	bean的属性名
	 * @return
	 */
	public Integer getInteger(String propName){
		Object o = this.get(propName);
		if(o==null){
			return null;
		}
		if(o instanceof Integer) return (Integer)o;
		return Integer.parseInt(o.toString());
	}
	
	/**
	 * 获取float数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public float getFloat(String propName){
		
		return Float.parseFloat(this.get(propName).toString()); 
		
	}
	
	/**
	 * 获取double数据
	 * @param propName	bean的属性名
	 * @return
	 */
	public double getDouble(String propName){
		
		return Double.parseDouble(this.get(propName).toString());
		
	}
	
	/**
	 * 获取long数据
	 * @param propName bean的属性名
	 * @return
	 */
	public long getLong(String propName){
		
		return Long.parseLong(this.get(propName).toString());
		
	}
	
	/**
	 * 获取boolean 数据
	 * @param propName
	 * @return
	 */
	public boolean getBoolean(String propName){
		return Boolean.parseBoolean(this.get(propName).toString());
	}
	
	/**
	 * 获取BigDecimal数据
	 * @param propName
	 * @return
	 */
	public BigDecimal getBigDecimal(String propName){
		return (BigDecimal)(this.get(propName));
	}
	
	/**
	 * 获取SQL DATE 数据
	 * @param propName
	 * @return
	 */
	public java.sql.Date getSqlDate(String propName){
		return ConverHelper.dateTimeObject2SqlDate(this.get(propName));
	}
	
	/**
	 * 获取util Date 数据
	 * @param propName
	 * @return
	 */
	public Date getDate(String propName){
		return ConverHelper.dateTimeObject2UtilDate(this.get(propName));
	}
	
	/**
	 * 获取Time数据
	 * @param propName	
	 * @return
	 */
	public Time getTime(String propName){
		return ConverHelper.dateTimeObject2Time(this.get(propName));
	}
	
	/**
	 * 获取Timestamp 数据
	 * @param propName
	 * @return
	 */    
	public Timestamp getTimestamp(String propName){
		return ConverHelper.dateTimeObject2Timestamp(this.get(propName));
	}
	
	/**
	 * 获取字符串类型的数据
	 * @param propName bean的属性名
	 * @param defaultValue 默认值
	 */
	public  String getString(String propName, String defaultValue){
		try{
			String str = this.getString(propName);
			return str==null ? defaultValue : str;
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取int数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  int getInt(String propName, int defaultValue){
		try{
			return this.getInt(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取float数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  float getFloat(String propName, float defaultValue){
		try{
			return this.getFloat(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取double数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  double getDouble(String propName, double defaultValue){
		try{
			return this.getDouble(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取long数据
	 * @param propName bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  long getLong(String propName, long defaultValue){
		try{
			return this.getLong(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取boolean 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  boolean getBoolean(String propName, boolean defaultValue){
		try{
			return this.getBoolean(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取BigDecimal数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  BigDecimal getBigDecimal(String propName, BigDecimal defaultValue){
		try{
			return this.getBigDecimal(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取SQL DATE 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  java.sql.Date getSqlDate(String propName, java.sql.Date defaultValue){
		try{
			return this.getSqlDate(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取util Date 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Date getDate(String propName, Date defaultValue){
		try{
			return this.getDate(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取Time数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Time getTime(String propName, Time defaultValue){
		try{
			return this.getTime(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}

	/**
	 * 获取Timestamp 数据
	 * @param propName	bean的属性名
	 * @param defaultValue 默认值
	 * @return
	 */
	public  Timestamp getTimestamp(String propName, Timestamp defaultValue){
		try{
			return this.getTimestamp(propName);
		}catch(Exception e){
			return defaultValue;
		}
	}	

}
