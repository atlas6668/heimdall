/**
 * <p>Title: ReceiveXmlHandler.java</p>
 * <p>处理接收的微信xml消息</p>
 * @author damon
 * @date 2014年12月19日
 * @version 1.0
 */
package com.wx.serveplatform.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wx.middleware.server.bean.ReceiveXml;
import com.wx.middleware.server.util.WeiXinUtil;

/**
 * <p>Title: ReceiveXmlHandler</p>
 * <p>处理接收的微信xml消息</p> 
 * @author damon
 * @date 2014年12月19日
 */
public class ReceiveXmlHandler {
	
	/**
	 * <p>getMsgEntity</p>
	 * <p>解析微信端发送过来的数据 封装到ReceiveXml对象</p>
	 * @author damon
	 * @date 2014年12月19日
	 * @date 2015年1月23日
	 * @param msgXml
	 * @param encryptType
	 * @param msgSignature
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws Exception
	 */
	public ReceiveXml getMsgEntity(String msgXml, String encryptType, String msgSignature, String timestamp, String nonce) throws Exception{
		ReceiveXml msg = null; 
		// 检查strXml是微信明文消息或是密文消息
		if(encryptType != null && encryptType.equals("aes")){
			// 如果是密文先解密再plainTextXml2ReceiveXml()
			String str = msgXml;
			// 先解密
			str = WeiXinUtil.decryptMsg(str, msgSignature, timestamp, nonce);
			msg = plainTextXml2ReceiveXml(str);
		// 如果是明文 直接plainTextXml2ReceiveXml()
		} else {
			msg = plainTextXml2ReceiveXml(msgXml);
		}
		
		return msg;
	}
	
	/** 
     * 解析微信明文strXml消息 转成 ReceiveXml对象
     * @param strXml 微信消息明文
     * @return ReceiveXml
     */  
    public ReceiveXml plainTextXml2ReceiveXml(String strXml){  
        ReceiveXml msg = null;  
        try {  
            if(strXml.length() <= 0 || strXml == null){
            	return null;  
            }
            // 将字符串转化为XML文档对象  
            Document document = DocumentHelper.parseText(strXml);  
            // 获得文档的根节点  
            Element root = document.getRootElement();  
            // 遍历根节点下所有子节点  
            Iterator<?> iter = root.elementIterator();  
              
            // 遍历所有结点  
            msg = new ReceiveXml();  
            // 利用反射机制，调用set方法  获取该实体的元类型  
            Class<?> c = Class.forName("com.wx.middleware.server.bean.ReceiveXml");  
            // 创建这个实体的对象  
            msg = (ReceiveXml)c.newInstance();
            
            while(iter.hasNext()){  
                Element ele = (Element)iter.next();  
                // 获取set方法中的参数字段（实体类的属性）  
                Field field = c.getDeclaredField(ele.getName());  
                // 获取set方法，field.getType())获取它的参数数据类型  
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());  
                // 调用set方法  
                method.invoke(msg, ele.getText());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return msg;  
    } 
    
//    public static void main(String[] args){
//    	
//    }
}
