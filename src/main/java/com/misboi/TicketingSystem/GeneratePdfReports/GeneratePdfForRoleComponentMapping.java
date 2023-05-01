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
import com.misboi.TicketingSystem.Model.RoleComponentMapping;

@Service
public class GeneratePdfForRoleComponentMapping {
	
//	@Autowired
//	private RoleComponentMappingRepo roleComponentMappingRepo;
//	
//	public ByteArrayInputStream GeneratePdfForRoleComponentMapping() throws Exception {
//        List<RoleComponentMapping> roleComponentMapping = (List<RoleComponentMapping>) roleComponentMappingRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                     ROLE_COMPONENT_MAPPING"));
//        document.add(new Paragraph("                                                  ============================"));
//        
//        for (RoleComponentMapping RoleComponentMappings : roleComponentMapping) {
//        	document.add(new Paragraph("Serial No: " + RoleComponentMappings.getSlno()));
//        	document.add(new Paragraph("Role Id: " + RoleComponentMappings.getRoleid()));
//            document.add(new Paragraph("Comp Id: " + RoleComponentMappings.getCompid()));
//            document.add(new Paragraph("Status: " + RoleComponentMappings.getStatus()));
//        	document.add(new Paragraph("Created At: " + RoleComponentMappings.getCreatedat()));
//            document.add(new Paragraph("Created By: " + RoleComponentMappings.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + RoleComponentMappings.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + RoleComponentMappings.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForRoleComponentMapping.class);
	
	public static ByteArrayInputStream roleComponentMappingPDFReport(List<RoleComponentMapping> roleComponentMapping) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Role Component Mapping", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			// Add PDF Table Header ->
			Stream.of("Slno", "Role Id", "Comp Id", "Status", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (RoleComponentMapping RoleComponentMappings : roleComponentMapping) {
				
				PdfPCell slnoCell = new PdfPCell(new Phrase(RoleComponentMappings.getSlno().toString()));
				slnoCell.setPaddingLeft(4);
				slnoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				slnoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(slnoCell);

				PdfPCell roleidCell = new PdfPCell(new Phrase(RoleComponentMappings.getRoleid().toString()));
				roleidCell.setPaddingLeft(4);
				roleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				roleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(roleidCell);

				PdfPCell compidCell = new PdfPCell(new Phrase(RoleComponentMappings.getCompid().toString()));
				compidCell.setPaddingLeft(4);
				compidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				compidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(compidCell);

				PdfPCell statusCell = new PdfPCell(new Phrase(RoleComponentMappings.getStatus()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(RoleComponentMappings.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(RoleComponentMappings.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(RoleComponentMappings.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(RoleComponentMappings.getUpdatedby()));
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
