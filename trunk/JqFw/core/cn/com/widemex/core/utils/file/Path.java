package cn.com.widemex.core.utils.file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;


/**
 * 路径操作工具类
 * 根据类的CLASS文件位置来定位
 * @author 张中原
 * @since 2010-6-26
 * @version ExtFw3.0
 * 
 */
public class Path {
	/**
	 * 返回某各类的绝对路径
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	public static String getPathFromClass(Class cls) throws IOException {
		String path = null;
		if (cls == null) {
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null) {
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol())) {
				try {
					path = new URL(path).getPath();
				} catch (MalformedURLException e) {
				}
				int location = path.indexOf("!/");
				if (location != -1) {
					path = path.substring(0, location);
				}
			}
			File file = new File(path);
			path = file.getCanonicalPath();
		}
		return path;
	}

	/**
	 * 以Cls参数类为参照
	 * 返回其相对路径所对应的绝对路径
	 * @param relatedPath
	 * @param cls
	 * @return
	 */
	public static String getFullPathRelateClass(String relatedPath, Class cls){
		String path = null;
		String clsPath;
		try {
			clsPath = getPathFromClass(cls);
			File clsFile = new File(clsPath);
			String tempPath = clsFile.getParent() + File.separator + relatedPath;
			File file = new File(tempPath);
			path = file.getCanonicalPath();			
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 以Path.class为参照
	 * 返回其相对路径所对应的绝对路径
	 * @param relatedPath
	 * @return
	 */
	public static String getFullPath(String relatedPath){
		String path = getFullPathRelateClass("../../" + relatedPath, Path.class);
		if(FileHelper.isDirectory(path, false) || FileHelper.isFile(path)){
//			System.err.println(path);
			relatedPath = path;
		}
		else{
			relatedPath = getFullPathRelateClass("../../../../../../../" + relatedPath, Path.class);
		}
		return relatedPath;
	}
	
	/**
	 * 获取类的class的路径
	 * @param cls
	 * @return
	 */
	private static URL getClassLocationURL(final Class cls) {
		if (cls == null) throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			if (cs != null) result = cs.getLocation();
			if (result != null) {
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip")){
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						}
						else if (new File(result.getFile()).isDirectory()){
							result = new URL(result, clsAsResource);
						}
					} 
					catch (MalformedURLException ignore) {
					}
				}
			}
		}

		if (result == null) {
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			System.out.println(getPathFromClass(Path.class));
			System.out.println(getFullPath("uploadFiles/tmp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
