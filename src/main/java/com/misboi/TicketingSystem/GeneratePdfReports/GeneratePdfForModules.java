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
import com.misboi.TicketingSystem.Model.Modules;


@Service
public class GeneratePdfForModules {

//	@Autowired
//    private ModulesRepo modulesRepo;
//	
//    public ByteArrayInputStream GeneratePdfForModules() throws Exception {
//        List<Modules> modules = (List<Modules>) modulesRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                    MODULES"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (Modules modules1 : modules) {
//        	document.add(new Paragraph("Modules ID: " + modules1.getModuleid()));
//        	document.add(new Paragraph("Application ID: " + modules1.getAppid()));
//            document.add(new Paragraph("Modules Name: " + modules1.getModname()));
//            document.add(new Paragraph("Modules Description: " + modules1.getDescription()));
//        	document.add(new Paragraph("Created At: " + modules1.getCreatedat()));
//            document.add(new Paragraph("Created By: " + modules1.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + modules1.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + modules1.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForModules.class);
	
	public static ByteArrayInputStream modulesPDFReport(List<Modules> modules) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Modules", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			// Add PDF Table Header ->
			Stream.of("Module Id", "App Id", "Module Name", "Description", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Modules Module : modules) {

				PdfPCell ModidCell = new PdfPCell(new Phrase(Module.getModuleid().toString()));
				ModidCell.setPaddingLeft(4);
				ModidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				ModidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(ModidCell);

				PdfPCell appidCell = new PdfPCell(new Phrase(Module.getAppid().toString()));
				appidCell.setPaddingLeft(4);
				appidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				appidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(appidCell);

				PdfPCell modnameCell = new PdfPCell(new Phrase(Module.getModname()));
				modnameCell.setPaddingLeft(4);
				modnameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				modnameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(modnameCell);


				PdfPCell descriptionCell = new PdfPCell(new Phrase(Module.getDescription()));
				descriptionCell.setPaddingLeft(4);
				descriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(descriptionCell);

				PdfPCell moduleidCell = new PdfPCell(new Phrase(Module.getModuleid().toString()));
				moduleidCell.setPaddingLeft(4);
				moduleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				moduleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(moduleidCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(Module.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(Module.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(Module.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(Module.getUpdatedby()));
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
