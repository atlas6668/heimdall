/**
 * <p>Title: StringUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月4日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

/**
 * <p>Title: StringUtil</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月4日
 */
public class StringUtil {

	/**
	 * <p>subStr</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月4日
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String subStr(String str, String regex) {
		if(str.startsWith("http://")){
			str = str.substring(7);
		}
		if(str.startsWith("https://")){
			str = str.substring(8);
		}
		int i = str.indexOf(regex);
		str = str.substring(0, i);
		
		return str;
	}
	
	/**
	 * <p>subStrRemoveRegex</p>
	 * <p>递归去除regex</p>
	 * @author damon
	 * @date 2015年1月12日
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String subStrRemoveRegex(String str, String regex) {
		if(isValid(str)){
			int index = str.indexOf(regex);
			if(index != -1){
				str = str.substring(0, index) + str.substring(index + 1, str.length());
				str =  subStrRemoveRegex(str, regex);
			}
		}
		return str;
	}
	
	/**
	 * <p>isValid</p>
	 * <p>非空有效性验证</p>
	 * @author damon
	 * @date 2015年1月4日
	 * @param o
	 * @return
	 */
	public static boolean isValid(Object o){
		if(o == null){
			return false;
		}
		if(o.equals("")){
			return false;
		}
		if(o.equals("undefined")){
			return false;
		}
		return true;
	}
	
//	public static void main(String[] args) {
////		String str = StringUtil.subStr("http://yasi.xiaoma.com/ysjj/?utm_source=bdss_ielts", "/");
////		System.out.println(str);
//		
////		System.out.println(StringUtil.isValid(""));
//		
//		System.out.println(StringUtil.subStrRemoveRegex("2013-01-01", "-"));
//	}
}
