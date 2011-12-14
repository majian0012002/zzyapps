package cn.com.widemex.core.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.widemex.core.utils.data.StrHelper;
import cn.com.widemex.core.utils.data.Type;

/**
 * 文件操作工具类
 * @author 何启伟
 * @since 2010-6-26
 * @version ExtFw3.0
 *
 */
public class FileHelper{
	protected static Log logger = LogFactory.getLog(FileHelper.class);

	/**
	 * 写入文件
	 * @param filePath
	 * @param fileName
	 * @param content
	 * @throws Exception
	 */
    public static void write(String filePath, String fileName, String content) throws Exception {
		OutputStreamWriter fw = null;
		PrintWriter out = null;
		try {
			FileHelper.isDirectory(filePath, true);
			fw = new OutputStreamWriter(new FileOutputStream(filePath + "/" + fileName), "UTF-8");
			out = new PrintWriter(fw);
			out.print(content);
		} 
		catch (Exception e) {
			logger.error("文件写入错误！\n" + e.getMessage());
		} 
		finally {
			if (out != null){
				out.close();
			}
			if (fw != null){
				fw.close();
			}
		}
	}
    
	/**
	 * 根据size返回粘贴后的name
	 */	
	public static String getPasteName(int size, String name) {
		if(StrHelper.isEmpty(name)) return "";
		if(size <= 1){
			name = "复件 " + name;
		}
		else{
			String[] arr = name.split(" ");
			if(arr.length == 1){
				return "复件 ("+size+") " + name;
			}
			else if(arr[0].equals("复件") &&
				arr[1].substring(0, 1).equals("(") &&
				arr[1].substring(arr[1].length() - 1).equals(")")){
				
				arr[1] = "("+size+")";
			}
			else{
				arr[0] += " ("+size+")";
			}
			name = Type.toString(arr, " ");
		}
		return name;
	}
	
	/**
	 * 根据粘贴后的name获得原始name
	 * @param name
	 * @return
	 */
	public static String getPasteName(String name) {
		String[] arr = name.split(" ");
		if(arr.length == 1) return name;
		int index=0;
		if(arr[0].equals("复件") &&
			arr[1].substring(0, 1).equals("(") &&
			arr[1].substring(arr[1].length() - 1).equals(")")){
			index = 2;
		}
		else{
			index = 1;
		}
		name = "";
		for(int ii=index; ii<arr.length; ii++){
			name += arr[ii] + (ii<arr.length-1 ? " " : "");
		}
		return name;
	}
	
	/**
	 * 判断目录是否存在
	 * @param path
	 * @param autoCreate
	 */
	public static boolean isDirectory(String path, boolean autoCreate){
		File file = new File(path);
		if(file.isDirectory()){
			return true;
		}
		else if(autoCreate){
			file.mkdirs();
			return true;
		}
		return false;
	}
	
	/**
	 * 判断文件是否存在
	 * @param path
	 * @param autoCreate
	 */
	public static boolean isFile(String file){
		File f = new File(file);
		if(f.isFile()){
			return true;
		}
		return false;
	}
	
	/**
	 * 移动指定文件夹内的全部文件
	 * @param 要移动的文件目录
	 * @param 目标文件目录
	 * @throws Exception 
	 * @throws Exception
	 */
	public static void moveAll(String from, String to) throws Exception{
		try {
			File dir = new File(from);
			//所有文件
			File[] files = dir.listFiles();
			if (files == null) return;
			//目标
			File moveDir = new File(to);
			if (!moveDir.isDirectory()) {
				moveDir.mkdirs();
			}
			//文件移动
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					moveAll(files[i].getPath(), to + "\\" + files[i].getName());
					// 成功，删除原文件
					files[i].delete();
				}
				File moveFile = new File(moveDir.getPath() + "\\" + files[i].getName());
				// 目标文件夹下存在的话，删除
				if (moveFile.exists()) {
					moveFile.delete();
				}
				files[i].renameTo(moveFile);
			}
			dir.delete();
		} 
		catch (Exception e) {
			logger.error("移动文件夹[" + from + "]出错！");
			throw e;
		}
	}
	
	/**
	 * 删除文件夹
	 * @param dir
	 * @return
	 * @throws Exception 
	 */
	public static void deleteFolder(String dir) throws Exception{
		try {
			File file = new File(dir);
			if (!file.isDirectory()) {
				file.delete();
			} 
			else{
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(dir + "\\" + filelist[i]);
					if (!delfile.isDirectory()){
						delfile.delete();
					}
					else{
						deleteFolder(dir + "\\" + filelist[i]);
					}
				}
				file.delete();
			}
		}
		catch (Exception e) {
			logger.error("删除文件夹[" + dir + "]出错！");
			throw e;
		}
	}   
	
	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath 原文件路径 如：c:/aaa
	 * @param newPath 复制后路径 如：f:/bbb
	 * @return boolean
	 * @throws Exception 
	 */
	public static void copyFolder(String oldPath, String newPath) throws Exception {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} 
				else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} 
		catch (Exception e) {
			logger.error("复制文件夹[" + oldPath + "]出错！");
			throw e;
		}
	}

	/**
	 * 复制正式目录
	 * 
	 * @param 正式目录
	 * @return
	 */
	public static void copy(String copyDir, String dir){
		copyDir = Path.getFullPath("uploadFiles/" + copyDir);
		dir = Path.getFullPath("uploadFiles/" + dir);
		try {
			copyFolder(copyDir, dir);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除正式目录
	 * 
	 * @param 正式目录
	 * @return
	 */
	public static void delete(String dir){
		dir = Path.getFullPath("uploadFiles/" + dir);
		try {
			deleteFolder(dir);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将临时目录中的文件移动到正式目录
	 * 
	 * @param 临时目录
	 * @param 正式目录
	 * @return
	 */
	public static void move(String tempDir, String dir){
		tempDir = Path.getFullPath("uploadFiles/tmp/" + tempDir);
		dir = Path.getFullPath("uploadFiles/" + dir);
		if(isDirectory(tempDir, false)){
			try {
				moveAll(tempDir, dir);
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		for(int i=3;i<11; i++){
//			String s = getPasteName(i, "复件 ("+i+") 测试测试测试");
//			System.err.println(getPasteName(i, s));
		}
		System.err.println(getPasteName(2, "复件 (33) 测试测"));
//		System.err.println(getPasteName("复件测试测试测试"));
//		System.err.println(getPasteName("复件 (33) 测试测试测试"));
	}
}
