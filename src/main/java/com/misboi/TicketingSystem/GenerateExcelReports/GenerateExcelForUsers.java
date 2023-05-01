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

import com.misboi.TicketingSystem.Model.Users;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateExcelForUsers {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Users> listAllUsers;
	
	public GenerateExcelForUsers(List<Users> listAllUsers) {
		this.listAllUsers=listAllUsers;
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
		sheet=workbook.createSheet("USERS");
		Row row=sheet.createRow(0);
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"USERS REPORT",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"User Id",style);
		CreateCell(row,1,"Username",style);
		CreateCell(row,2,"Password",style);
		CreateCell(row,3,"Email",style);
		CreateCell(row,4,"Contact No",style);
		CreateCell(row,5,"Tickets Status",style);
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
		for(Users User:listAllUsers) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			CreateCell(row,columnCount++,User.getUserid(),style);
			CreateCell(row,columnCount++,User.getUsername(),style);
			CreateCell(row,columnCount++,User.getPassword(),style);
			CreateCell(row,columnCount++,User.getEmail(),style);
			CreateCell(row,columnCount++,User.getContactno(),style);
			CreateCell(row,columnCount++,User.getStatus(),style);
			CreateCell(row,columnCount++,User.getCreatedat(),style);
			CreateCell(row,columnCount++,User.getCreatedby(),style);
			CreateCell(row,columnCount++,User.getUpdatedat(),style);
			CreateCell(row,columnCount++,User.getUpdatedby(),style);		
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
