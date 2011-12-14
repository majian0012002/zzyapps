package cn.com.widemex.core.utils.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * XML工具类
 * 使用XML文件存取可序列化的对象的类
 * @author 张中原
 * @since 2010-6-26
 * @version ExtFw3.0
 * 
 */
public class XmlHelper {
	private static Log logger = LogFactory.getLog(XmlHelper.class);

	/**
	 * 把java的可序列化的对象(实现Serializable接口)序列化保存到XML文件里面,如果想一次保存多个可序列化对象请用集合进行封装
	 * 保存时将会用现在的对象原来的XML文件内容
	 * 
	 * @param obj 要序列化的可序列化的对象
	 * @param fileName 带完全的保存路径的文件名
	 * @throws FileNotFoundException 指定位置的文件不存在
	 * @throws IOException 输出时发生异常
	 * @throws Exception 其他运行时异常
	 */
	public static void encoder(Object obj, String fileName) throws FileNotFoundException, IOException, Exception {
		//创建输出文件
		File fo = new File(fileName);
		//文件不存在,就创建该文件
		if (!fo.exists()) {
			// 先创建文件的目录
			String path = fileName.substring(0, fileName.lastIndexOf('.'));
			File pFile = new File(path);
			pFile.mkdirs();
		}
		//创建文件输出流
		FileOutputStream fos = new FileOutputStream(fo);
		//创建XML文件对象输出类实例
		XMLEncoder encoder = new XMLEncoder(fos);
		//对象序列化输出到XML文件
		encoder.writeObject(obj);
		encoder.flush();
		//关闭序列化工具
		encoder.close();
		//关闭输出流
		fos.close();
	}

	/**
	 * XML TO OBJECT
	 * 
	 * @param objSource 带全部文件路径的文件全名
	 * @return 由XML文件里面保存的对象
	 * @throws FileNotFoundException 指定的对象读取资源不存在
	 * @throws IOException 读取发生错误
	 * @throws Exception 其他运行时异常发生
	 */
	public static Object decoder(String objSource) throws FileNotFoundException, IOException, Exception {
//		List objList = new ArrayList();
		File fin = new File(objSource);
		FileInputStream fis = new FileInputStream(fin);
		XMLDecoder decoder = new XMLDecoder(fis);
		Object obj = null;
		try {
			obj = decoder.readObject();
//			while ((obj = decoder.readObject()) != null) {
//				objList.add(obj);
//			}
		} 
		catch (Exception e) {
			logger.error("XML TO OBJECT错误！" + e.getMessage());
		}
		fis.close();
		decoder.close();
		return obj;
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception{
//		TemplateVo vo = new TemplateVo();
//		List<String> l = new ArrayList();
//		l.add("sss");
//		l.add("ssaaa");
//		vo.setGrid(l);
//		XmlHelper.encoder(vo, "D://test.xml");
//		TemplateVo vo = (TemplateVo) XmlHelper.objectXmlDecoder("D://test.xml");
//		System.err.println(vo.isHql());
	}
}
