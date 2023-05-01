package com.misboi.TicketingSystem.GenerateExcelReports;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.misboi.TicketingSystem.Model.Components;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateExcelForComponents {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Components> listAllComponents;
	
	public GenerateExcelForComponents(List<Components> listAllComponents) {
		this.listAllComponents=listAllComponents;
		workbook=new XSSFWorkbook();
	}
	
	private void CreateCell(Row row,int columnCount,Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long)value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer)value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean)value);
		}else if(value instanceof Date) {
			cell.setCellValue((Date)value);
		}else{
			cell.setCellValue((String)value);
		}
		cell.setCellStyle(style);
	}
	
	private void writeHeaderLine() {
		sheet=workbook.createSheet("COMPONENTS");
		Row row=sheet.createRow(0);
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"COMPONENTS REPORT",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"Component Id",style);
		CreateCell(row,1,"Component Name",style);
		CreateCell(row,2,"Component Description",style);
		CreateCell(row,3,"Component Type",style);
		CreateCell(row,4,"Component URL",style);
		CreateCell(row,5,"Component Status",style);
		CreateCell(row,6,"Created At",style);
		CreateCell(row,7,"Created By",style);
		CreateCell(row,8,"Updated At",style);
		CreateCell(row,9,"Updated By",style);
	}
	
	private void writeDataLines() {
		int rowCount=2;
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		for(Components Comp:listAllComponents) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			CreateCell(row,columnCount++,Comp.getCompid(),style);
			CreateCell(row,columnCount++,Comp.getCompname(),style);
			CreateCell(row,columnCount++,Comp.getDescription(),style);
			CreateCell(row,columnCount++,Comp.getComptype(),style);
			CreateCell(row,columnCount++,Comp.getCompurl(),style);
			CreateCell(row,columnCount++,Comp.getCompstatus(),style);
			CreateCell(row,columnCount++,Comp.getCreatedat(),style);
			CreateCell(row,columnCount++,Comp.getCreatedby(),style);
			CreateCell(row,columnCount++,Comp.getUpdatedat(),style);
			CreateCell(row,columnCount++,Comp.getUpdatedby(),style);		
		}
	}
	
	public void export(HttpServletResponse response)throws IOException{
		writeHeaderLine();
		writeDataLines();
		ServletOutputStream outputStream =response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}
	
}
