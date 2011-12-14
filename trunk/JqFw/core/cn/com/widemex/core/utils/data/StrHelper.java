package cn.com.widemex.core.utils.data;

/**
 * 字符串辅助类
 * @author 张中原
 * @since 2010-6-26
 * @version ExtFw3.0
 */
public class StrHelper {

	/**
	 * 判断str中是否包含str2或者str3...
	 * 
	 * @author davi
	 * 
	 * @param str
	 * @param str2
	 * @throws Exception
	 * @return
	 */
	public static boolean like(Object str, Object... str2) throws Exception {
		return StrHelper.indexOf(str, str2) != -1;
	}

	/**
	 * 返回匹配参数的序号
	 * 
	 * @author davi
	 * 
	 * @param str
	 * @param str2
	 * @throws Exception
	 * @return
	 */
	public static Integer indexOf(Object str, Object... str2) throws Exception {
		if (str == null || str2 == null || str2.length == 0) {
			return -1;
		} else {
			for (int i = 0; i < str2.length; i++) {
				if (str2[i] == null) {
					return -1;
				} else if ((str + "").indexOf(str2[i] + "") >= 0) {
					return i + 1;
				}
			}
			return -1;
		}
	}

	/**
	 * 字符串截取
	 * @param str
	 * @param idx
	 * @param str2[]
	 * @return
	 * @throws Exception
	 */
	public static String substring(String str, int idx, Object... str2) throws Exception{
		if (str == null || str2 == null || str2.length == 0) {
			return str;
		} 
		else {
			int i=StrHelper.indexOf(str, str2);
			if(i == -1){
				return str.substring(idx);
			}
			else{
				return StrHelper.substring(str.substring(0, str.indexOf(str2[i-1].toString())), idx, str2);
			}
		}
	}
	
	/**
	 * 字符串替换
	 * @author davi
	 * 
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String replaceAll(String source, String oldString, String newString) {
		StringBuffer output = new StringBuffer();
		int lengthOfSource = source.length();
		int lengthOfOld = oldString.length();
		int posStart = 0;
		int pos; //
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengthOfOld;
		}
		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}
	
	/**
	 * 判断参数值是否为空
	 * @param s
	 * @return
	 */	
	public static boolean isEmpty(String s) {
		return s == null || s.equals("") || s.toUpperCase().equals("NULL") || s.equals("undefined");
	}
	
	/**
	 * 字符集转换
	 * @param s
	 * @return
	 */
	public static String toUTF8(String s) {
		try {
			return new String(toISO(s), "UTF-8");
		} 
		catch (Exception e) {
			return null;
		}   
	}
	private static byte[] toISO(String s) {
		try {
			return s.getBytes("ISO-8859-1");
		} 
		catch (Exception e) {
			return null;
		}   
	}
	
	/**
	 * 
	 */
	public static void main(String[] argv) throws Exception {
		System.err.println(StrHelper.substring("机碎片湖南07年", 0, 0, 1, 2, 3));
	}
}
