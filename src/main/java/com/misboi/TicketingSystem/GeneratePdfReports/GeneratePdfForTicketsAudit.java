package com.misboi.TicketingSystem.GeneratePdfReports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.misboi.TicketingSystem.Model.TicketsAudit;


@Service
public class GeneratePdfForTicketsAudit {
	
//	@Autowired
//    private TicketsAuditRepo ticketsAuditRepo;
//	
//    public ByteArrayInputStream GeneratePdfForTicketsAudit() throws Exception {
//        List<TicketsAudit> ticketsAudit = (List<TicketsAudit>) ticketsAuditRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//       
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                               TICKETS_AUDIT"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (TicketsAudit ticketAudit : ticketsAudit) {
//        	document.add(new Paragraph("Serial Number: " + ticketAudit.getSlno()));
//        	document.add(new Paragraph("Ticket Id: " + ticketAudit.getTicketid()));
//            document.add(new Paragraph("App Id: " + ticketAudit.getAppid()));
//            document.add(new Paragraph("Title: " + ticketAudit.getTitle()));
//            document.add(new Paragraph("Description: " + ticketAudit.getDescription()));
//            document.add(new Paragraph("Module Id: " + ticketAudit.getModuleid()));
//            document.add(new Paragraph("priority: " + ticketAudit.getPriority()));
//            document.add(new Paragraph("Complexity: " + ticketAudit.getComplexity()));
//            document.add(new Paragraph("Status: " + ticketAudit.getStatus()));
//            document.add(new Paragraph("Remarks: " + ticketAudit.getRemarks()));
//        	document.add(new Paragraph("Created At: " + ticketAudit.getCreatedat()));
//            document.add(new Paragraph("Created By: " + ticketAudit.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + ticketAudit.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + ticketAudit.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForSessions.class);
	
	public static ByteArrayInputStream ticketsAuditPDFReport(List<TicketsAudit> ticketsAudit) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Tickets Audit", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(14);
			// Add PDF Table Header ->
			Stream.of("Slno", "Ticket Id", "App Id", "Title", "Description", "Module Id", "Priority", "Complexity", "Status", "Remarks", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (TicketsAudit TicketAudit : ticketsAudit) {
				
				PdfPCell slnoCell = new PdfPCell(new Phrase(TicketAudit.getSlno().toString()));
				slnoCell.setPaddingLeft(4);
				slnoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				slnoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(slnoCell);
				
				PdfPCell ticketidCell = new PdfPCell(new Phrase(TicketAudit.getTicketid().toString()));
				ticketidCell.setPaddingLeft(4);
				ticketidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ticketidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ticketidCell);

				PdfPCell appidCell = new PdfPCell(new Phrase(TicketAudit.getAppid().toString()));
				appidCell.setPaddingLeft(4);
				appidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				appidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(appidCell);

				PdfPCell titleCell = new PdfPCell(new Phrase(TicketAudit.getTitle()));
				titleCell.setPaddingLeft(4);
				titleCell.setVerticalAlignment(Element.ALIGN_CENTER);
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(titleCell);

				PdfPCell descriptionCell = new PdfPCell(new Phrase(TicketAudit.getDescription()));
				descriptionCell.setPaddingLeft(4);
				descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(descriptionCell);

				PdfPCell moduleidCell = new PdfPCell(new Phrase(TicketAudit.getModuleid().toString()));
				moduleidCell.setPaddingLeft(4);
				moduleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				moduleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(moduleidCell);

				PdfPCell priorityCell = new PdfPCell(new Phrase(TicketAudit.getPriority()));
				priorityCell.setPaddingLeft(4);
				priorityCell.setVerticalAlignment(Element.ALIGN_CENTER);
				priorityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(priorityCell);

				PdfPCell complexityCell = new PdfPCell(new Phrase(TicketAudit.getComplexity()));
				complexityCell.setPaddingLeft(4);
				complexityCell.setVerticalAlignment(Element.ALIGN_CENTER);
				complexityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(complexityCell);

				PdfPCell statusCell = new PdfPCell(new Phrase(TicketAudit.getStatus()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);

				PdfPCell remarksCell = new PdfPCell(new Phrase(TicketAudit.getRemarks()));
				remarksCell.setPaddingLeft(4);
				remarksCell.setVerticalAlignment(Element.ALIGN_CENTER);
				remarksCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(remarksCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(TicketAudit.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(TicketAudit.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(TicketAudit.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(TicketAudit.getUpdatedby()));
				updatedbyCell.setPaddingLeft(4);
				updatedbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(updatedbyCell);
			}
			
			document.add(table);
			
			document.close();
			
		} catch (DocumentException e) {
			
			logger.error(e.toString());
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
}
