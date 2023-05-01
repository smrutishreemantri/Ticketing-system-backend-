package com.misboi.TicketingSystem.GeneratePdfReports;

import com.misboi.TicketingSystem.Model.Components;
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


@Service
public class GeneratePdfForComponents {
	
//	@Autowired
//    private ComponentsRepo componentsRepo;
//	
//	public ByteArrayInputStream GeneratePdfForComponents() throws Exception {
//        List<Components> components = (List<Components>) componentsRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                               COMPONENTS"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (Components component : components) {
//        	document.add(new Paragraph("Component ID: " + component.getCompid()));
//        	document.add(new Paragraph("Component Name: " + component.getCompname()));
//            document.add(new Paragraph("Component Description: " + component.getDescription()));
//            document.add(new Paragraph("Component Type: " + component.getComptype()));
//            document.add(new Paragraph("Component URL: " + component.getCompurl()));
//            document.add(new Paragraph("Component Status: " + component.getCompstatus()));
//        	document.add(new Paragraph("Created At: " + component.getCreatedat()));
//            document.add(new Paragraph("Created By: " + component.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + component.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + component.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForComponents.class);
	
	public static ByteArrayInputStream componentsPDFReport(List<Components> components) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Components", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(10);
			// Add PDF Table Header ->
			Stream.of("Component id", "Component Name", "Description", "Componet Type", "Component Url", "Status", "created At", "Created By", "Updated At", "Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Components component : components) {
				
				PdfPCell compidCell = new PdfPCell(new Phrase(component.getCompid().toString()));
				compidCell.setPaddingLeft(10);
				compidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				compidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(compidCell);
				
				PdfPCell compnameCell = new PdfPCell(new Phrase(component.getCompname()));
				compnameCell.setPaddingLeft(4);
				compnameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				compnameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(compnameCell);
				
				PdfPCell descriptionCell = new PdfPCell(new Phrase(component.getDescription()));
				descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setPaddingRight(4);
				table.addCell(descriptionCell);
				
				PdfPCell comptypeCell = new PdfPCell(new Phrase(component.getComptype()));
				comptypeCell.setVerticalAlignment(Element.ALIGN_CENTER);
				comptypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				comptypeCell.setPaddingRight(4);
				table.addCell(comptypeCell);
				
				PdfPCell compurlCell = new PdfPCell(new Phrase(component.getCompurl()));
				compurlCell.setVerticalAlignment(Element.ALIGN_CENTER);
				compurlCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				compurlCell.setPaddingRight(4);
				table.addCell(compurlCell);
				
				PdfPCell compstatusCell = new PdfPCell(new Phrase(component.getCompstatus()));
				compstatusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				compstatusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				compstatusCell.setPaddingRight(4);
				table.addCell(compstatusCell);

				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(component.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);
				
				PdfPCell createdbyCell = new PdfPCell(new Phrase(String.valueOf(component.getCreatedby())));
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setPaddingRight(4);
				table.addCell(createdbyCell);
				
				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(component.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);
				
				PdfPCell updatedbyCell = new PdfPCell(new Phrase(String.valueOf(component.getUpdatedby())));
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
