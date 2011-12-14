package cn.com.widemex.core.utils.data;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 类型转换工具类
 * @author 张中原
 * @since 2010-6-26
 * @version ExtFw3.0
 *
 */
public class Type{
	protected static Log logger = LogFactory.getLog(Type.class);

    /**
     * toString
     * @param s
     * @param defaultValue
     * @return
     */
	public static String toString(Object s, String defaultValue) {
		if (StrHelper.isEmpty(s.toString())) return defaultValue;
		if(s.getClass().isArray()){
			try{
				String str = "";
				Object[] arr = (Object[]) s;
				for(int i=0; i<arr.length; i++){
					str += arr[i];
					if(i < arr.length - 1){
						str += ",";
					}
				}
				return str;
			}
	        catch (Exception e) {
	        	logger.error(e.getMessage() + ", return " + (StrHelper.isEmpty(defaultValue) ? "\"\"" : defaultValue));
			}
		}
		return s + "";
	}
	/**
	 * toString
	 * @param s
	 * @return
	 */
	public static String toString(Object s) {
		return toString(s, "");
	}
	/**
	 * toString
	 * @param arr
	 * @return
	 */
	public static String toString(String[] arr) {
		return toString(arr, ",");
	}
	/**
	 * toString
	 * @param arr
	 * @param sep
	 * @return
	 */
	public static String toString(String[] arr, String sep) {
		String s = "";
		for(int i=0; i<arr.length; i++){
			s += arr[i] + (i<arr.length-1 ? sep : "");
		}
		return s;
	}
	/**
	 * toLong
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Long toLong(String s, Long defaultValue) {
        try {
        	if (StrHelper.isEmpty(s)) return defaultValue;
            return Long.valueOf(s);
        }
        catch (Exception e) {
        	logger.error(e.getMessage() + ", return " + defaultValue);
        	return defaultValue;
        }
	}
	/**
	 * toLong
	 * @param s
	 * @return
	 */
	public static Long toLong(String s) {
		return toLong(s, null);
	}
	
	/**
	 * toDouble
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Double toDouble(String s, Double defaultValue) {
        try {
        	if (StrHelper.isEmpty(s)) return defaultValue;
            return Double.valueOf(s);
        }
        catch (Exception e) {
        	logger.error(e.getMessage() + ", return " + defaultValue);
        	return defaultValue;
        }
	}
	/**
	 * toDouble
	 * @param s
	 * @return
	 */
	public static Double toDouble(String s) {
		return toDouble(s, null);
	}
	
	/**
	 * toInteger
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Integer toInteger(String s, Integer defaultValue) {
        try {
        	if (StrHelper.isEmpty(s)) return defaultValue;
            return Integer.valueOf(s);
        }
        catch (Exception e) {
        	logger.error(e.getMessage() + ", return " + defaultValue);
        	return defaultValue;
        }
	}
	/**
	 * toInteger
	 * @param s
	 * @return
	 */
	public static Integer toInteger(String s) {
		return toInteger(s, null);
	}
	
    /**
     * toFloat
     * @param value
     * @param defaultValue
     * @return
     */
    public static Float toFloat(String value, Float defaultValue) {
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
        	logger.error(e.getMessage() + ", return " + defaultValue);
            return defaultValue;
        }
    }

    /**
     * toFloat
     * @param value
     * @return
     */
    public static Float toFloat(String value) {
        return toFloat(value, null);
    }
    
    /**
     * toInt
     * @param value
     * @param defaultValue
     * @return
     */
    public static int toInt(Object value, int defaultValue) {
        if (value instanceof Integer) {
            return ((Integer) value).intValue();
        }
        else if (value instanceof Long) {
            return ((Long) value).intValue();
        }
        else {
          return toInteger(value + "", defaultValue);
        }
    }
    /**
     * toInt
     * @param value
     * @return
     */
    public static int toInt(Object value) {
        return toInt(value, 0);
    }
    
    /**
     * toShort
     * @param value
     * @param defaultValue
     * @return
     */
    public static Short toShort(String value, Short defaultValue) {
        try {
            return Short.valueOf(value.split("\\.")[0]);
        } catch (Exception e) {
        	logger.error(e.getMessage() + ", return " + defaultValue);
            return defaultValue;
        }
    }
    /**
     * toShort
     * @param value
     * @return
     */
    public static Short toShort(String value) {
        return toShort(value, null);
    }
    
	/**
	 * toDate
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static Date toDate(String s, Date defaultValue) {
		try {
			if (StrHelper.isEmpty(s)) return defaultValue;
			return DateHelper.stringToDate(s);
		}
		catch (ParseException e) {
			logger.error(e.getMessage() + ", return " + defaultValue);
		}
		return defaultValue;
	}
	/**
	 * toDate
	 * @param s
	 * @return
	 */
	public static Date toDate(String s) {
		return toDate(s, null);
	}	
	/**
	 * nowDate
	 * @return
	 */
	public static Date nowDate() {
		return DateHelper.nowDate();
	}
	
	/**
	 * toSqlDate
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static java.sql.Date toSqlDate(String s, java.sql.Date defaultValue) {
		return (java.sql.Date) toDate(s, defaultValue);
	}
	/**
	 * toSqlDate
	 * @param s
	 * @return
	 */
	public static java.sql.Date toSqlDate(String s) {
		return (java.sql.Date) toDate(s, null);
	}	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.err.println(toShort("10111.333"));
	}
	
}
