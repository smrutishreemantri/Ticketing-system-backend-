package com.misboi.TicketingSystem.GeneratePdfReports;
import org.springframework.stereotype.Service;

import com.misboi.TicketingSystem.Model.Sessions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GeneratePdfForSessions {
	
//	@Autowired
//	private SessionsRepo sessionsRepo;
	
//	public ByteArrayInputStream GeneratePdfForSessions() throws Exception {
//        List<Sessions> sessions = (List<Sessions>) sessionsRepo.findAll();
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        
//       document.open();
//        document.add(new Paragraph("                                                     TICKETING SYSTEM REPORTS"));
//        document.add(new Paragraph("=========================================================================="));
//        document.add(new Paragraph("                                                                   SESSIONS"));
//        document.add(new Paragraph("                                                ==========================="));
//        
//        for (Sessions session : sessions) {
//        	document.add(new Paragraph("Session ID: " + session.getSessionid()));
//        	document.add(new Paragraph("User Id: " + session.getUserid()));
//            document.add(new Paragraph("Log On Time: " + session.getLogontime()));
//        	document.add(new Paragraph("Log Off Time: " + session.getLogofftime()));
//            document.add(new Paragraph("================================================="));
//        }
//
//       
//       document.close();
//        return new ByteArrayInputStream(out.toByteArray());
//   }
	
	
	private static Logger logger = LoggerFactory.getLogger(GeneratePdfForSessions.class);
	
	public static ByteArrayInputStream sessionPDFReport(List<Sessions> sessions) {
		
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 35,BaseColor.BLACK);
			Paragraph para = new Paragraph("Sessions", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(4);
			// Add PDF Table Header ->
			Stream.of("Session Id", "User Id", "Log On Time","Log Off Time").forEach(headerTitle ->{
            	  PdfPCell header = new PdfPCell();
            	  Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18,BaseColor.BLACK);
            	  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            	  header.setHorizontalAlignment(Element.ALIGN_CENTER);
            	  header.setBorderWidth(2);
            	  header.setPhrase(new Phrase(headerTitle, headFont));
            	  table.addCell(header);
            });
			
			for (Sessions session : sessions) {
				
				PdfPCell sessionidCell = new PdfPCell(new Phrase(session.getSessionid().toString()));
				sessionidCell.setPaddingLeft(4);
				sessionidCell.setVerticalAlignment(Element.ALIGN_CENTER);
				sessionidCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(sessionidCell);
				
				PdfPCell useridCell = new PdfPCell(new Phrase(session.getUserid().toString()));
				useridCell.setPaddingLeft(4);
				useridCell.setVerticalAlignment(Element.ALIGN_CENTER);
				useridCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(useridCell);
				
				PdfPCell logontimeCell = new PdfPCell(new Phrase(String.valueOf(session.getLogontime())));
				logontimeCell.setVerticalAlignment(Element.ALIGN_CENTER);
				logontimeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				logontimeCell.setPaddingRight(4);
				table.addCell(logontimeCell);


				PdfPCell logofftimeCell = new PdfPCell(new Phrase(String.valueOf(session.getLogofftime())));
				logofftimeCell.setVerticalAlignment(Element.ALIGN_CENTER);
				logofftimeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				logofftimeCell.setPaddingRight(4);
				table.addCell(logofftimeCell);
			}
			
			document.add(table);
			
			document.close();
			
		} catch (DocumentException e) {
			
			logger.error(e.toString());
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
