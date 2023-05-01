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
import com.misboi.TicketingSystem.Model.UserRoleMapping;


@Service
public class GeneratePdfForUserRoleMapping {
	
//	@Autowired
//	private UserRoleMappingRepo userRoleMappingRepo;
//	
//	public ByteArrayInputStream GeneratePdfForUserRoleMapping() throws Exception {
//        List<UserRoleMapping> userRoleMapping = (List<UserRoleMapping>) userRoleMappingRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                         USER_ROLE_MAPPING"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (UserRoleMapping userRoleMappings : userRoleMapping) {
//        	document.add(new Paragraph("Serial No: " + userRoleMappings.getSlno()));
//        	document.add(new Paragraph("User Id: " + userRoleMappings.getUserid()));
//        	document.add(new Paragraph("Role Id: " + userRoleMappings.getRoleid()));
//            document.add(new Paragraph("Status: " + userRoleMappings.getStatus()));
//        	document.add(new Paragraph("Created At: " + userRoleMappings.getCreatedat()));
//            document.add(new Paragraph("Created By: " + userRoleMappings.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + userRoleMappings.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + userRoleMappings.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
private static Logger logger = LoggerFactory.getLogger(GeneratePdfForUserRoleMapping.class);
	
	public static ByteArrayInputStream userRoleMappingPDFReport(List<UserRoleMapping> userRoleMapping) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Users Role Mapping", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			// Add PDF Table Header ->
			Stream.of("Slno", "User Id", "Role Id", "Status", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (UserRoleMapping userRoleMappings : userRoleMapping) {
				
				PdfPCell slnoCell = new PdfPCell(new Phrase(userRoleMappings.getSlno().toString()));
				slnoCell.setPaddingLeft(4);
				slnoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				slnoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(slnoCell);
				
				PdfPCell useridCell = new PdfPCell(new Phrase(userRoleMappings.getUserid().toString()));
				useridCell.setPaddingLeft(4);
				useridCell.setVerticalAlignment(Element.ALIGN_CENTER);
				useridCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(useridCell);

				PdfPCell roleidCell = new PdfPCell(new Phrase(userRoleMappings.getRoleid().toString()));
				roleidCell.setPaddingLeft(4);
				roleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				roleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(roleidCell);

				PdfPCell statusCell = new PdfPCell(new Phrase(userRoleMappings.getStatus()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(userRoleMappings.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(userRoleMappings.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(userRoleMappings.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(userRoleMappings.getUpdatedby()));
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
