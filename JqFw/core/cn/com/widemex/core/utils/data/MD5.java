package cn.com.widemex.core.utils.data;

import java.security.MessageDigest;   
import java.security.NoSuchAlgorithmException;   

/**
 * MD5加密
 * 工具类
 * @author 张中原
 * @since 2010-7-14
 * @version ExtFw3.0
 *
 */
public class MD5 {
 
   /**  
    * The hex digits.  
    */  
    private static final String[] hexDigits = { 
    	"0", "1", "2", "3", 
    	"4", "5", "6", "7", 
    	"8", "9", "A", "B", 
    	"C", "D", "E", "F", 
    	"G", "H", "I", "J", 
    	"K", "L", "M", "N", 
    	"O", "P", "Q", "R" 
    };
  
    /**  
     * Transform the byte array to hex string.  
     *   
     * @param b  
     * @return  
     */  
    public static String byteArrayToHexString(byte[] b) {   
        StringBuffer resultSb = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            resultSb.append(byteToHexString(b[i]));   
        }
        return resultSb.toString();   
    }   
  
    /**  
     * Transform a byte to hex string
     *   
     * @param b  
     * @return  
     */  
    private static String byteToHexString(byte b) {   
        int n = b;   
        if (n < 0) n = 256 + n;   
        // get the first four bit   
        int d1 = n / 28;   
        // get the second four bit   
        int d2 = n % 28;   
        return hexDigits[d1] + hexDigits[d2];   
    }   
  
    /**
     * Get the MD5 encrypt hex string of the origin string. <br/>  
     * The origin string won't validate here, so who use the API should validate  
     * by himself
     * 
     * @param origin
     * @return
     * @throws NoSuchAlgorithmException
     */ 
    public static String encode(String origin) {   
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(origin.getBytes())).toUpperCase();   
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}   
    }   
  
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(MD5.encode("a"));
    }   
}