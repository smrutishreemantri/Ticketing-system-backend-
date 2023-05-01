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
import com.misboi.TicketingSystem.Model.EventLog;

@Service
public class GeneratePdfForEventLog {
	
//	@Autowired
//	private EventLogRepo eventLogRepo;
//	
//	public ByteArrayInputStream GeneratePdfForEventLog() throws Exception {
//        List<EventLog> eventLog = (List<EventLog>) eventLogRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                 EVENT_LOG"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (EventLog eventslog : eventLog) {
//        	document.add(new Paragraph("Event ID: " + eventslog.getEventid()));
//        	document.add(new Paragraph("Ticket Id: " + eventslog.getTicketid()));
//            document.add(new Paragraph("Event Title: " + eventslog.getEventtitle()));
//            document.add(new Paragraph("Event Details: " + eventslog.getEventdetails()));
//        	document.add(new Paragraph("Created At: " + eventslog.getCreatedat()));
//            document.add(new Paragraph("Created By: " + eventslog.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + eventslog.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + eventslog.getUpdatedby()));
//            document.add(new Paragraph("=================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForEventLog.class);
	
	public static ByteArrayInputStream eventLogPDFReport(List<EventLog> eventLog) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Event Log", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			// Add PDF Table Header ->
			Stream.of("Event Id", "Ticket Id", "Event Title", "Event Details", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (EventLog eventsLog : eventLog) {
				
				PdfPCell eventidCell = new PdfPCell(new Phrase(eventsLog.getEventid().toString()));
				eventidCell.setPaddingLeft(4);
				eventidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				eventidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(eventidCell);

				PdfPCell ticketidCell = new PdfPCell(new Phrase(eventsLog.getTicketid().toString()));
				ticketidCell.setPaddingLeft(4);
				ticketidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ticketidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ticketidCell);

				PdfPCell eventtitleCell = new PdfPCell(new Phrase(eventsLog.getEventtitle()));
				eventtitleCell.setPaddingLeft(4);
				eventtitleCell.setVerticalAlignment(Element.ALIGN_CENTER);
				eventtitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(eventtitleCell);

				PdfPCell eventdetailsCell = new PdfPCell(new Phrase(eventsLog.getEventdetails()));
				eventdetailsCell.setPaddingLeft(4);
				eventdetailsCell.setVerticalAlignment(Element.ALIGN_CENTER);
				eventdetailsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(eventdetailsCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(eventsLog.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(eventsLog.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(eventsLog.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(eventsLog.getUpdatedby()));
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
