/**
 * <p>Title: ConverUtil.java</p>
 * <p></p>
 * @author damon
 * @date 2015年4月15日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * <p>Title: ConverUtil</p>
 * <p></p> 
 * @author damon
 * @date 2015年4月15日
 */
public class ConverUtil {

private String pinyinName = "";
    
    /**
    * 汉字转拼音的方法
    * @param name 汉字
    * @return 拼音
    */
    private String HanyuToPinyin(String name){
        char[] nameChar = name.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = 
                                           new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray
                                           (nameChar[i], defaultFormat)[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        }
        return pinyinName;
    }
 
    public static void main(String[] args) {
        System.out.println(new ConverUtil().HanyuToPinyin("北京"));
    }    
	
}
