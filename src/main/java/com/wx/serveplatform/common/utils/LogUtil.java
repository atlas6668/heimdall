/**
 * <p>Title: LogUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年3月17日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Title: LogUtil</p>
 * <p>日志</p> 
 * @author damon
 * @date 2015年3月17日
 */
public class LogUtil {

	/**
	 * <p>log</p>
	 * <p>往指定路径下写日志</p>
	 * @author damon
	 * @date 2015年3月17日
	 * @param logPath
	 * @param content
	 * @throws IOException
	 */
	public static void log(String logPath, String content) throws IOException{
		FileWriter writer = new FileWriter(logPath, true);
		writer.write("\r\n"+DateUtil.getDate("yyyy-MM-dd HH:mm:ss")+"\t");
		writer.write(content);
        writer.close();
	}
	
	/**
	 * <p>log</p>
	 * <p>写日志( 默认地址下 )</p>
	 * @author damon
	 * @date 2015年3月17日
	 * @param content
	 * @throws IOException
	 */
	public static void log(String content) throws IOException{
		String logPath = "D:/log/"+DateUtil.getDate("yyyyMMddHHmm")+".log";
		log(logPath, content);
	}
	
}
