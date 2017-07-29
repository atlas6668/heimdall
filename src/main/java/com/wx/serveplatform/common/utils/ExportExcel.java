/**
 * <p>Title: ExportExcel</p>
 * <p>Description: 导出Excel文件（导出“XLSX”格式，支持大数据量导出   
 * @see org.apache.poi.ss.SpreadsheetVersion）</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: xiaoma.com</p>
 * @author damon
 * @date 2014年7月9日
 * @version 1.0
 */
package com.wx.serveplatform.common.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * <p>Title: ExportExcel</p>
 * <p>Description: 导出Excel文件（导出“XLSX”格式，支持大数据量导出   
 * @see org.apache.poi.ss.SpreadsheetVersion）</p>
 * <p>Company: xiaoma.com</p> 
 * @author damon
 * @date 2014年7月9日
 */
public class ExportExcel {
	private static Logger log = LoggerFactory.getLogger(ExportExcel.class);
	
	//工作薄对象
	private SXSSFWorkbook wb;
	
	//工作表对象
	private Sheet sheet;
	
	//行
	private Row row;
	
	//单元格
	private Cell cell;
	
	/**
	 * <p>Description: 创建一个传入名称的工作表和初始化传入的表头</p>
	 */
	public ExportExcel(String sheet, String[] headers) {
		initialize(sheet, Lists.newArrayList(headers));
	}
	
	/**
	 * <p>Description: 创建一个传入名称的工作表和初始化传入的表头</p>
	 */
	public ExportExcel(String sheet, List<String> headerList) {
		initialize(sheet, headerList);
	}
	
	/**
	 * <p>Title: initialize</p>
	 * <p>Description: 初始化工作薄</p>
	 * @param headers
	 */
	private void initialize(String sheet, List<String> headerList) {
		//init workbook
		this.wb = new SXSSFWorkbook();
		//init sheet
		this.sheet = this.wb.createSheet(sheet);
		//Create header
		if(headerList == null){
			throw new RuntimeException("表头为空无效!");
		}
		//init header row
		this.row = this.sheet.createRow(0);
		for(int i = 0; i < headerList.size(); i++){
			this.cell = this.row.createCell(i);
			// 样式
			LocalExcelStyle style = new LocalExcelStyle();
			style.m0();
			this.cell.setCellStyle(style.getStyle());
			this.setCellValue(headerList.get(i));
		}
		
		log.debug("Initialize success.");
	}

	/**
	 * <p>Title: setDataList</p>
	 * <p>Description: 注入数据</p>
	 * @param lists
	 * @return
	 */
	public ExportExcel setDataList(List<List<Object>> lists) {
		if(lists == null){
			throw new RuntimeException("数据为空无效!");
		}
		for(int i = 0; i < lists.size(); i++){
			//第0行为表头
			this.row = this.sheet.createRow(i + 1);
			for(int j = 0; j < lists.get(i).size(); j++){
				this.cell = this.row.createCell(j);
				this.setCellValue(lists.get(i).get(j));
			}
		}
		
		return this;
	}

	/**
	 * <p>Title: setCellValue</p>
	 * <p>Description: 单元格赋值</p>
	 * @param cell2
	 * @param object
	 */
	private void setCellValue(Object val) {
		if (val == null){
			cell.setCellValue("");
		} else if (val instanceof String) {
			cell.setCellValue((String) val);
		} else if (val instanceof Integer) {
			cell.setCellValue((Integer) val);
		} else if (val instanceof Long) {
			cell.setCellValue((Long) val);
		} else if (val instanceof Double) {
			cell.setCellValue((Double) val);
		} else if (val instanceof Float) {
			cell.setCellValue((Float) val);
		} else if (val instanceof Date) {
			cell.setCellValue(DateUtil.formatDateTime((Date) val));
		}
	}
	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportExcel write(OutputStream os) throws IOException{
		this.wb.write(os);
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public ExportExcel write(HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public ExportExcel writeFile(String name) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}
}
