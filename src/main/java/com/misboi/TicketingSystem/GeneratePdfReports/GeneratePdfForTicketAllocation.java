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
import com.misboi.TicketingSystem.Model.TicketAllocation;


@Service
public class GeneratePdfForTicketAllocation {
	
//	@Autowired
//    private TicketAllocationRepo ticketAllocationRepo;
//    
//    public ByteArrayInputStream GeneratePdfForTicketAllocation() throws Exception {
//        List<TicketAllocation> TicketAllocation = (List<TicketAllocation>) ticketAllocationRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                           TICKET_ALLOCATION"));
//        document.add(new Paragraph("                                                ============================"));
//        
//        for (TicketAllocation tktAllocation : TicketAllocation) {
//        	document.add(new Paragraph("Serial No: " + tktAllocation.getSlno()));
//            document.add(new Paragraph("Ticket Id: " + tktAllocation.getTicketid()));
//            document.add(new Paragraph("Allocated To: " + tktAllocation.getAllocatedto()));
//            document.add(new Paragraph("Minimum Due Date: " + tktAllocation.getMinduedate()));
//            document.add(new Paragraph("Maximum Due Date: " + tktAllocation.getMaxduedate()));
//        	document.add(new Paragraph("Created At: " + tktAllocation.getCreatedat()));
//            document.add(new Paragraph("Created By: " + tktAllocation.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + tktAllocation.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + tktAllocation.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForTicketAllocation.class);
	
	public static ByteArrayInputStream ticketAllocationPDFReport(List<TicketAllocation> ticketAllocation) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Ticket Allocation", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			// Add PDF Table Header ->
			Stream.of("Slno", "Ticket Id", "Allocated To", "Minimum Due Date", "Maximum Due Date", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (TicketAllocation tktAllocation : ticketAllocation) {
				
				PdfPCell slnoCell = new PdfPCell(new Phrase(tktAllocation.getSlno().toString()));
				slnoCell.setPaddingLeft(4);
				slnoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				slnoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(slnoCell);

				PdfPCell ticketidCell = new PdfPCell(new Phrase(tktAllocation.getTicketid().toString()));
				ticketidCell.setPaddingLeft(4);
				ticketidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ticketidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ticketidCell);

				PdfPCell allocatedtoCell = new PdfPCell(new Phrase(tktAllocation.getAllocatedto()));
				allocatedtoCell.setPaddingLeft(4);
				allocatedtoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				allocatedtoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(allocatedtoCell);

				PdfPCell minduedateCell = new PdfPCell(new Phrase(String.valueOf(tktAllocation.getMinduedate())));
				minduedateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				minduedateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				minduedateCell.setPaddingRight(4);
				table.addCell(minduedateCell);

				PdfPCell maxduedateCell = new PdfPCell(new Phrase(String.valueOf(tktAllocation.getMaxduedate())));
				maxduedateCell.setVerticalAlignment(Element.ALIGN_CENTER);
				maxduedateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				maxduedateCell.setPaddingRight(4);
				table.addCell(maxduedateCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(tktAllocation.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(tktAllocation.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(tktAllocation.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(tktAllocation.getUpdatedby()));
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
