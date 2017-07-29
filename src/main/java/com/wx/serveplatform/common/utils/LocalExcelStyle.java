/**
 * <p>Title: LocalExcelStyle.java</p>
 * <p>自定义EXCEL 样式</p>
 * @author damon
 * @date 2014年12月30日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 * <p>Title: LocalExcelStyle</p>
 * <p>自定义EXCEL 样式</p> 
 * @author damon
 * @date 2014年12月30日
 */
public class LocalExcelStyle {

	private XSSFCellStyle style;
	
	/**
	 * 取得XSSFCellStyle实例
	 * @return the style
	 */
	public XSSFCellStyle getStyle() {
		return style;
	}

	/**
	 * <p></p>
	 */
	public LocalExcelStyle() {
		initialize();
	}

	/**
	 * <p>initialize</p>
	 * <p></p>
	 * @author damon
	 * @date 2014年12月30日
	 * @param wb
	 */
	private void initialize() {
		style = new XSSFCellStyle(new StylesTable());
		m0();
	}
	
	//============================   
	//        设置单元格填充颜色   
	//============================
	public LocalExcelStyle m0() {
		style.setFillForegroundColor(new XSSFColor(java.awt.Color.GREEN));
		return this;
	}
	
	//============================   
	//        设置单元格边框   
	//============================   
	public LocalExcelStyle m1() {
	    // 设置单元格边框样式   
	    // CellStyle.BORDER_DOUBLE      双边线   
	    // CellStyle.BORDER_THIN        细边线   
	    // CellStyle.BORDER_MEDIUM      中等边线   
	    // CellStyle.BORDER_DASHED      虚线边线   
	    // CellStyle.BORDER_HAIR        小圆点虚线边线   
	    // CellStyle.BORDER_THICK       粗边线   
		style.setBorderBottom(CellStyle.BORDER_THICK);   
	       
	    // 设置单元格边框颜色   
		style.setBottomBorderColor(new XSSFColor(java.awt.Color.RED));   
		style.setTopBorderColor(new XSSFColor(java.awt.Color.GREEN));   
		style.setLeftBorderColor(new XSSFColor(java.awt.Color.BLUE));   
		return this;   
	}
	
	//============================   
	//      设置单元内容的对齐方式   
	//============================   
	public LocalExcelStyle m2() {
	    // 设置单元格内容水平对其方式   
	    // XSSFCellStyle.ALIGN_CENTER       居中对齐   
	    // XSSFCellStyle.ALIGN_LEFT         左对齐   
	    // XSSFCellStyle.ALIGN_RIGHT        右对齐   
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);   
	       
	    // 设置单元格内容垂直对其方式   
	    // XSSFCellStyle.VERTICAL_TOP       上对齐   
	    // XSSFCellStyle.VERTICAL_CENTER    中对齐   
	    // XSSFCellStyle.VERTICAL_BOTTOM    下对齐   
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);   
		return this;   
	}
	
	//============================   
	//      设置单元格自动换行   
	//============================   
	public LocalExcelStyle m3() {
		// 设置单元格内容是否自动换行  
		style.setWrapText(true); 
		return this;  
	}
	
}
