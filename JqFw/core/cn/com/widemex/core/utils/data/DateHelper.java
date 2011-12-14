package cn.com.widemex.core.utils.data;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * 日期处理工具类
 * @author 张中原
 * @since 2010-6-26
 */
public class DateHelper {
	/**日期 ：格式化'yyyy-MM-dd'Date*/
	public static final SimpleDateFormat  DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**时间戳：格式化'yyyy-MM-dd HH:mm:ss'Date*/
	public static final SimpleDateFormat  TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**年月 ：格式化'yyyyMM'Date*/
	public static final SimpleDateFormat  MONTH_FORMAT = new SimpleDateFormat("yyyyMM");
	

    /**
     * nowDate
     * @return
     */
    public static Date nowDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获得某一日期的后N天字符串
     * @param date
     * @param next
     * @param partten
     * @return
     */
    public static String getNextDateString(Date date, int next, String partten) {
        return getDateString(getNextDate(date, next), partten);
    }

    /**
     * 获得某一日期的前N天字符串
     * @param date
     * @param next
     * @param partten
     * @return
     */
    public static String getPrevDateString(Date date, int Prev, String partten) {
        return getDateString(getPrevDate(date, Prev), partten);
    }
    
    /**
     * 获得某一日期的后N天
     * @param date
     * @param next
     * @return
     */
    public static Date getNextDate(Date date, int next) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + next);
        return calendar.getTime();
    }

    /**
     * 获得某一日期的前N天
     * @param date
     * @param Prev
     * @return
     */
    public static Date getPrevDate(Date date, int Prev) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - Prev);
        return calendar.getTime();
    }

    /**
     * 获得某年某月第一天的日期
     * @param year
     * @param month
     * @return Date
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获得某年某月最后一天的日期
     * @param year
     * @param month
     * @return Date
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return getPrevDate(calendar.getTime(), 1);
    }

    /**
     * 由年月日构建Date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date buildDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 取得某月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDayCountOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 0);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得某年某季度的最后一天的日期
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = quarter * 3;
        }
        return getLastDayOfMonth(year, month);

    }

    /**
     * 获得某年某季度的第一天的日期
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = (quarter - 1) * 3 + 1;
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 获得某年的第一天的日期
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year) {
        return getFirstDayOfMonth(year, 1);
    }

    /**
     * 获得某年的最后一天的日期
     * @param year
     * @return
     */
    public static Date getLastDayOfYear(int year) {
        return getLastDayOfMonth(year, 12);
    }

    /**
     * String到Date的类型转换
     * @param param
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String param) throws ParseException {
        if(param == null || param == "" || param.indexOf("null") >=0) {
            return null;
        }
        else {
        	if(param.length() > 11){
        		return new Date(Date.parse(param));
        	}
        	Date date = null;
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            param = param.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "-").replaceAll("/", "-").replaceAll("\\.", "-");
            date = sdf.parse(param);
            return new Date(date.getTime());
        }
    }

    /**
     * 得到日期字符串
     * @param date
     * @param partten
     * @return
     */
    public static String getDateString(Date date, String partten) {
    	if(date == null) date = nowDate();
        String result = null;
        SimpleDateFormat formatter = new SimpleDateFormat(partten);
        result = formatter.format(date);
        return result;
    }
    /**
     * 得到日期字符串
     * @param time
     * @param partten
     * @return
     */
    public static String getDateString(Long time, String partten) {
        return getDateString(new Date(time), partten);
    }
    /**
     * 得到日期字符串
     * @param partten
     * @return
     */
    public static String getDateString(String partten) {
        return getDateString(nowDate(), partten);
    }
    
    /**
     * 
     * @param argv
     * @throws ParseException
     */
    public static void main(String[] argv) throws ParseException {
    	System.out.println(getNextDate(new Date(), 1));

    }
}