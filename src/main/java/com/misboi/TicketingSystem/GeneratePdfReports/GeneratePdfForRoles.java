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
import com.misboi.TicketingSystem.Model.Roles;



@Service
public class GeneratePdfForRoles {
	
//	@Autowired 
//	private RolesRepo rolesRepo;
//	
//	public ByteArrayInputStream GeneratePdfForRoles() throws Exception {
//        List<Roles> roles = (List<Roles>) rolesRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                      ROLES"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (Roles Roles1 : roles) {
//        	document.add(new Paragraph("Role Id: " + Roles1.getRoleid()));
//            document.add(new Paragraph("Role Name: " + Roles1.getRolename()));
//            document.add(new Paragraph("Role Description: " + Roles1.getRoledescription()));
//            document.add(new Paragraph("Role Status: " + Roles1.getStatus()));
//        	document.add(new Paragraph("Created At: " + Roles1.getCreatedat()));
//            document.add(new Paragraph("Created By: " + Roles1.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + Roles1.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + Roles1.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForRoles.class);
	
	public static ByteArrayInputStream rolesPDFReport(List<Roles> roles) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Roles", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(8);
			// Add PDF Table Header ->
			Stream.of("Role Id", "Role Name", "Description", "Status", "Created At", "Created By", "Updated At", "Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Roles role : roles) {
				
				PdfPCell roleidCell = new PdfPCell(new Phrase(role.getRoleid().toString()));
				roleidCell.setPaddingLeft(4);
				roleidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				roleidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(roleidCell);

				PdfPCell rolenameCell = new PdfPCell(new Phrase(role.getRolename()));
				rolenameCell.setPaddingLeft(4);
				rolenameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				rolenameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rolenameCell);

				PdfPCell roledescriptionCell = new PdfPCell(new Phrase(role.getRoledescription()));
				roledescriptionCell.setPaddingLeft(4);
				roledescriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
				roledescriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(roledescriptionCell);

				PdfPCell statusCell = new PdfPCell(new Phrase(role.getStatus()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(role.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(role.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(role.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(role.getUpdatedby()));
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
