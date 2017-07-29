/**
 * <p>Title: FileUtils.java</p>
 * <p>Description: 文件工具类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: xiaoma.com</p>
 * @author damon
 * @date 2014年8月1日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title: FileUtils</p>
 * <p>Description: 文件工具类</p>
 * <p>Company: xiaoma.com</p> 
 * @author damon
 * @date 2014年8月1日
 */
public class FileUtils {
	
	/**
	 * <p>Title: new_file</p>
	 * <p>Description: 创建文件</p>
	 * @param filePath
	 * @return File实例
	 * @throws IOException
	 */
	public static File new_file(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.isDirectory()){
			if(!file.exists()){
				file.mkdirs();
			}
		} else {
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
		}
		return file;
	}
	
	/**
	 * <p>Title: new_file</p>
	 * <p>Description: 根据文件路径补全目录和创建文件</p>
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File new_file(String filePath, String fileName) throws IOException {
		File file = new File(filePath, fileName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		return file;
	}
	
	/**
	 * <p>new_Dir</p>
	 * <p>补全目录</p>
	 * @author damon
	 * @date 2015年4月3日
	 * @param filePath
	 * @return
	 */
	public static File new_Dir(String filePath) {
		File file = new File(filePath);
		if(file.isDirectory()){
			file.mkdirs();
			return file;
		}
		return null;
	}
	
	/**
	 * <p>Title: transferTo</p>
	 * <p>Description: 传输文件</p>
	 * @param file
	 * @param request
	 * @param targetDirectory
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String transferTo(MultipartFile file, 
			HttpServletRequest request, String targetDirectory) throws IOException {
		String realPath = request.getRealPath(targetDirectory);
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		fileName = System.currentTimeMillis() + suffix;
		File targetFile = new_file(realPath, fileName);
		file.transferTo(targetFile);
		return targetDirectory + "/" + fileName;
	}
	
	/**
	 * <p>clear</p>
	 * <p>清除文件</p>
	 * @author damon
	 * @date 2015年3月9日
	 * @param pathName
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public static void clear(String path, HttpServletRequest request, String fileName) throws IOException {
		String realPath = request.getRealPath(path);
		File file = new_file(realPath, fileName);
		file.deleteOnExit();
	}
	
	
}
