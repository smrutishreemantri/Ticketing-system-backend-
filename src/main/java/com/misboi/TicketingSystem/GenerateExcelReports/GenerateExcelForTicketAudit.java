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
import com.misboi.TicketingSystem.Model.TicketsAudit;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateExcelForTicketAudit {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<TicketsAudit> listAllTicketsAudit;
	
	public GenerateExcelForTicketAudit(List<TicketsAudit> listAllTicketsAudit) {
		this.listAllTicketsAudit=listAllTicketsAudit;
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
		sheet=workbook.createSheet("TICKETS AUDIT");
		Row row=sheet.createRow(0);
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"TICKETS AUDIT REPORT",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		CreateCell(row,0,"Serial No",style);
		CreateCell(row,1,"Ticket Id",style);
		CreateCell(row,2,"App Id",style);
		CreateCell(row,3,"Tickets Description",style);
		CreateCell(row,4,"Moduleid",style);
		CreateCell(row,5,"Tickets Priority",style);
		CreateCell(row,6,"Tickets Complexity",style);
		CreateCell(row,7,"Tickets Status",style);
		CreateCell(row,8,"Remarks",style);
		CreateCell(row,9,"Tickets createdat",style);
		CreateCell(row,10,"Tickets createdby",style);
		CreateCell(row,11,"Tickets updatedat",style);
		CreateCell(row,12,"Tickets updatedby",style);
	}
	
	private void writeDataLines() {
		int rowCount=2;
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for(TicketsAudit tkt:listAllTicketsAudit) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			CreateCell(row,columnCount++,tkt.getSlno(),style);
			CreateCell(row,columnCount++,tkt.getTicketid(),style);
			CreateCell(row,columnCount++,tkt.getAppid(),style);
			CreateCell(row,columnCount++,tkt.getDescription(),style);
			CreateCell(row,columnCount++,tkt.getModuleid(),style);
			CreateCell(row,columnCount++,tkt.getPriority(),style);
			CreateCell(row,columnCount++,tkt.getComplexity(),style);
			CreateCell(row,columnCount++,tkt.getStatus(),style);
			CreateCell(row,columnCount++,tkt.getRemarks(),style);
			CreateCell(row,columnCount++,tkt.getCreatedat(),style);
			CreateCell(row,columnCount++,tkt.getCreatedby(),style);
			CreateCell(row,columnCount++,tkt.getUpdatedat(),style);
			CreateCell(row,columnCount++,tkt.getUpdatedby(),style);		
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
