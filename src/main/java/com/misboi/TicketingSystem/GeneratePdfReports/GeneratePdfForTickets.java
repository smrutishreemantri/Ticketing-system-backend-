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
import com.misboi.TicketingSystem.Model.Tickets;

@Service
public class GeneratePdfForTickets {
//	@Autowired
//    private TicketsRepo ticketsRepo;
//
//	
//    public ByteArrayInputStream GeneratePdfForTickets() throws Exception {
//        List<Tickets> tickets = (List<Tickets>) ticketsRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                        TICKETS"));
//        document.add(new Paragraph("                                                =============================="));
//        
//        for (Tickets ticket : tickets) {
//        	document.add(new Paragraph("Ticket Id: " + ticket.getTicketid()));
//            document.add(new Paragraph("App Id: " + ticket.getAppid()));
//            document.add(new Paragraph("Title: " + ticket.getTitle()));
//            document.add(new Paragraph("Description: " + ticket.getDescription()));
//            document.add(new Paragraph("Module Id: " + ticket.getModuleid()));
//            document.add(new Paragraph("priority: " + ticket.getPriority()));
//            document.add(new Paragraph("Complexity: " + ticket.getComplexity()));
//            document.add(new Paragraph("Status: " + ticket.getStatus()));
//            document.add(new Paragraph("Remarks: " + ticket.getRemarks()));
//        	document.add(new Paragraph("Created At: " + ticket.getCreatedat()));
//            document.add(new Paragraph("Created By: " + ticket.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + ticket.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + ticket.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForTickets.class);
	
	public static ByteArrayInputStream ticketsPDFReport(List<Tickets> tickets) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Tickets", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(13);
			// Add PDF Table Header ->
			Stream.of("Ticket Id", "App Id", "Title", "Description", "Module Id", "Priority", "Complexity", "Status", "Remarks", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Tickets Ticket : tickets) {
				
				PdfPCell ticketidCell = new PdfPCell(new Phrase(Ticket.getTicketid().toString()));
				ticketidCell.setPaddingLeft(0);
				ticketidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ticketidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ticketidCell);

				PdfPCell appidCell = new PdfPCell(new Phrase(Ticket.getAppid().toString()));
				appidCell.setPaddingLeft(0);
				appidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				appidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(appidCell);

				PdfPCell titleCell = new PdfPCell(new Phrase(Ticket.getTitle()));
				titleCell.setPaddingLeft(0);
				titleCell.setVerticalAlignment(Element.ALIGN_CENTER);
				titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(titleCell);

				PdfPCell descriptionCell = new PdfPCell(new Phrase(Ticket.getDescription()));
				descriptionCell.setPaddingLeft(0);
				descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(descriptionCell);

				PdfPCell moduleidCell = new PdfPCell(new Phrase(Ticket.getModuleid().toString()));
				moduleidCell.setPaddingLeft(0);
				moduleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				moduleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(moduleidCell);

				PdfPCell priorityCell = new PdfPCell(new Phrase(Ticket.getPriority()));
				priorityCell.setPaddingLeft(0);
				priorityCell.setVerticalAlignment(Element.ALIGN_CENTER);
				priorityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(priorityCell);

				PdfPCell complexityCell = new PdfPCell(new Phrase(Ticket.getComplexity()));
				complexityCell.setPaddingLeft(0);
				complexityCell.setVerticalAlignment(Element.ALIGN_CENTER);
				complexityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(complexityCell);

				PdfPCell statusCell = new PdfPCell(new Phrase(Ticket.getStatus()));
				statusCell.setPaddingLeft(0);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);

				PdfPCell remarksCell = new PdfPCell(new Phrase(Ticket.getRemarks()));
				remarksCell.setPaddingLeft(0);
				remarksCell.setVerticalAlignment(Element.ALIGN_CENTER);
				remarksCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(remarksCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(Ticket.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(0);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(Ticket.getCreatedby()));
				createdbyCell.setPaddingLeft(0);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(Ticket.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(0);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(Ticket.getUpdatedby()));
				updatedbyCell.setPaddingLeft(0);
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
