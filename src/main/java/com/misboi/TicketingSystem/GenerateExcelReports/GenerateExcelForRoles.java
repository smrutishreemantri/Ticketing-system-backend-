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

import com.misboi.TicketingSystem.Model.Roles;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateExcelForRoles {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Roles> listAllRoles;
	
	public GenerateExcelForRoles(List<Roles> listAllRoles) {
		this.listAllRoles=listAllRoles;
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
		sheet=workbook.createSheet("ROLES");
		Row row=sheet.createRow(0);
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"ROLES REPORT",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"Role Id",style);
		CreateCell(row,1,"Role Name",style);
		CreateCell(row,2,"Role Description ",style);
		CreateCell(row,3,"Status",style);
		CreateCell(row,4,"Created At",style);
		CreateCell(row,5,"Created By",style);
		CreateCell(row,6,"Updated At",style);
		CreateCell(row,7,"Updated By",style);
	}
	
	private void writeDataLines() {
		int rowCount=2;
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		for(Roles role:listAllRoles) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			CreateCell(row,columnCount++,role.getRoleid(),style);
			CreateCell(row,columnCount++,role.getRolename(),style);
			CreateCell(row,columnCount++,role.getRoledescription(),style);
			CreateCell(row,columnCount++,role.getStatus(),style);
			CreateCell(row,columnCount++,role.getCreatedat(),style);
			CreateCell(row,columnCount++,role.getCreatedby(),style);
			CreateCell(row,columnCount++,role.getUpdatedat(),style);
			CreateCell(row,columnCount++,role.getUpdatedby(),style);		
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
