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
import com.misboi.TicketingSystem.Model.Users;
@Service
public class GeneratePdfForUsers {
	
//	@Autowired
//	private UsersRepo usersRepo;
//	
//	public ByteArrayInputStream GeneratePdfForUsers() throws Exception {
//        List<Users> users = (List<Users>) usersRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//        document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                      USERS"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (Users User : users) {
//        	document.add(new Paragraph("User Id: " + User.getUserid()));
//        	document.add(new Paragraph("Username: " + User.getUsername()));
//            document.add(new Paragraph("Password: " + User.getPassword()));
//            document.add(new Paragraph("Email: " + User.getEmail()));
//            document.add(new Paragraph("Contact No: " + User.getContactno()));
//            document.add(new Paragraph("Status: " + User.getStatus()));
//        	document.add(new Paragraph("Created At: " + User.getCreatedat()));
//            document.add(new Paragraph("Created By: " + User.getCreatedby()));
//            document.add(new Paragraph("Updated At: " + User.getUpdatedat()));
//            document.add(new Paragraph("Updated By: " + User.getUpdatedby()));
//            document.add(new Paragraph("================================================="));
//        }
//        
//        document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//    }
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForTickets.class);
	
	public static ByteArrayInputStream usersPDFReport(List<Users> users) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 28,BaseColor.BLACK);
			Paragraph para = new Paragraph("Users", font);
			para.setAlignment(Element.ALIGN_CENTER);
			para.setSpacingAfter(100);
			para.setSpacingAfter(0);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(10);
			// Add PDF Table Header ->
			Stream.of("User Id", "Username", "Password", "Email", "contactno", "Status", "Created At", "Created By", "Updated At","Updated By").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Users user : users) {
				
				PdfPCell useridCell = new PdfPCell(new Phrase(user.getUserid().toString()));
				useridCell.setPaddingLeft(4);
				useridCell.setVerticalAlignment(Element.ALIGN_CENTER);
				useridCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(useridCell);

				PdfPCell usernameCell = new PdfPCell(new Phrase(user.getUsername()));
				usernameCell.setPaddingLeft(4);
				usernameCell.setVerticalAlignment(Element.ALIGN_CENTER);
				usernameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(usernameCell);

				PdfPCell passwordCell = new PdfPCell(new Phrase(user.getPassword()));
				passwordCell.setPaddingLeft(4);
				passwordCell.setVerticalAlignment(Element.ALIGN_CENTER);
				passwordCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(passwordCell);

				PdfPCell emailCell = new PdfPCell(new Phrase(user.getEmail()));
				emailCell.setPaddingLeft(4);
				emailCell.setVerticalAlignment(Element.ALIGN_CENTER);
				emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(emailCell);

				PdfPCell contactnoCell = new PdfPCell(new Phrase(user.getContactno()));
				contactnoCell.setPaddingLeft(4);
				contactnoCell.setVerticalAlignment(Element.ALIGN_CENTER);
				contactnoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(contactnoCell);


				PdfPCell statusCell = new PdfPCell(new Phrase(user.getStatus()));
				statusCell.setPaddingLeft(4);
				statusCell.setVerticalAlignment(Element.ALIGN_CENTER);
				statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(statusCell);
				
				PdfPCell createdatCell = new PdfPCell(new Phrase(String.valueOf(user.getCreatedat())));
				createdatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdatCell.setPaddingRight(4);
				table.addCell(createdatCell);

				PdfPCell createdbyCell = new PdfPCell(new Phrase(user.getCreatedby()));
				createdbyCell.setPaddingLeft(4);
				createdbyCell.setVerticalAlignment(Element.ALIGN_CENTER);
				createdbyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(createdbyCell);

				PdfPCell updatedatCell = new PdfPCell(new Phrase(String.valueOf(user.getUpdatedat())));
				updatedatCell.setVerticalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				updatedatCell.setPaddingRight(4);
				table.addCell(updatedatCell);

				PdfPCell updatedbyCell = new PdfPCell(new Phrase(user.getUpdatedby()));
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
