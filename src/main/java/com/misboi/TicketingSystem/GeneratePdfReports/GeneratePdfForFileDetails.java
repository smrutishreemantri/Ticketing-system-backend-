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
import com.misboi.TicketingSystem.Model.FileDetails;

@Service
public class GeneratePdfForFileDetails {
		
//	@Autowired
//	private FileDetailsRepo fileDetailsRepo;
//	
//	public ByteArrayInputStream GeneratePdfForFileDetails() throws Exception {
//        List<FileDetails> fileDetails = (List<FileDetails>) fileDetailsRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                FILE_DETAILS"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (FileDetails files : fileDetails) {
//        	document.add(new Paragraph("File ID: " + files.getFileid()));
//        	document.add(new Paragraph("Ticket ID: " + files.getTicketid()));
//        	document.add(new Paragraph("Event ID: " + files.getEventid()));
//        	document.add(new Paragraph("File Name: " + files.getFilename()));
//        	document.add(new Paragraph("File Path: " + files.getFilepath()));
//        	document.add(new Paragraph("Created At: " + files.getCreatedat()));
//            document.add(new Paragraph("Created By: " + files.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + files.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + files.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForFileDetails.class);
	
	public static ByteArrayInputStream fileDetailsPDFReport(List<FileDetails> fileDetails) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("FIle Details", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			// Add PDF Table Header ->
			Stream.of("File ID", "Ticket Id", "EVent Id", "File Name", "File Path", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (FileDetails fileDetail : fileDetails) {
				
				PdfPCell fileidCell = new PdfPCell(new Phrase(fileDetail.getFileid().toString()));
				fileidCell.setPaddingLeft(4);
				fileidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				fileidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(fileidCell);

				PdfPCell ticketidCell = new PdfPCell(new Phrase(fileDetail.getTicketid().toString()));
				ticketidCell.setPaddingLeft(4);
				ticketidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ticketidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ticketidCell);
				
				PdfPCell eventidCell = new PdfPCell(new Phrase(fileDetail.getEventid().toString()));
				eventidCell.setPaddingLeft(4);
				eventidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				eventidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(eventidCell);

				PdfPCell filenameCell = new PdfPCell(new Phrase(fileDetail.getFilename()));
				filenameCell.setPaddingLeft(4);
				filenameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				filenameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(filenameCell);

				PdfPCell filepathCell = new PdfPCell(new Phrase(fileDetail.getFilepath()));
				filepathCell.setPaddingLeft(4);
				filepathCell.setVerticalAlignment(Element.ALIGN_CENTER);
				filepathCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(filepathCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(fileDetail.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(fileDetail.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(fileDetail.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(fileDetail.getUpdatedby()));
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
