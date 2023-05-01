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
import com.misboi.TicketingSystem.Model.Applications;



@Service
public class GeneratePdfForApplication {

//	@Autowired
//    private ApplicationsRepo applicationsRepo;
//	
//    public ByteArrayInputStream GeneratePdfForApplications() throws Exception {
//        List<Applications> applications = (List<Applications>) applicationsRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                 APPLICATIONS"));
//        document.add(new Paragraph("                                                ============================"));
//        
//        for (Applications application : applications) {
//        	document.add(new Paragraph("Applications ID: " + application.getAppid()));
//        	document.add(new Paragraph("Application Name: " + application.getAppname()));
//            document.add(new Paragraph("Application Description: " + application.getDescription()));
//        	document.add(new Paragraph("Created At: " + application.getCreatedat()));
//            document.add(new Paragraph("Created By: " + application.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + application.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + application.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForApplication.class);
	
	public static ByteArrayInputStream applicationsPDFReport(List<Applications> applications) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Applications", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(7);
			// Add PDF Table Header ->
			Stream.of("Application Id", "Application Name", "Description", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Applications application : applications) {
				
				PdfPCell appidCell = new PdfPCell(new Phrase(application.getAppid().toString()));
				appidCell.setPaddingLeft(10);
				appidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				appidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(appidCell);
				
				PdfPCell appnameCell = new PdfPCell(new Phrase(application.getAppname()));
				appnameCell.setPaddingLeft(4);
				appnameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				appnameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(appnameCell);
				
				PdfPCell descriptionCell = new PdfPCell(new Phrase(application.getDescription()));
				descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setPaddingRight(4);
				table.addCell(descriptionCell);

				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(application.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);
				
				PdfPCell createdbyCell = new PdfPCell(new Phrase(String.valueOf(application.getCreatedby())));
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setPaddingRight(4);
				table.addCell(createdbyCell);
				
				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(application.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);
				
				PdfPCell updatedbyCell = new PdfPCell(new Phrase(String.valueOf(application.getUpdatedby())));
				updatedbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedbyCell.setPaddingRight(4);
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
