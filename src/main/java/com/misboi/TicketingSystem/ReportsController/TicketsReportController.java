package com.misboi.TicketingSystem.ReportsController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForApplications;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForComponents;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForEventLog;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForFileDetails;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForModules;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForRoleComponentMapping;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForRoles;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForSessions;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForTicketAllocation;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForTicketAudit;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForTickets;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForUserRoleMapping;
import com.misboi.TicketingSystem.GenerateExcelReports.GenerateExcelForUsers;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForApplication;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForComponents;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForEventLog;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForFileDetails;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForModules;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForRoleComponentMapping;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForRoles;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForSessions;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForTicketAllocation;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForTickets;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForTicketsAudit;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForUserRoleMapping;
import com.misboi.TicketingSystem.GeneratePdfReports.GeneratePdfForUsers;
import com.misboi.TicketingSystem.Model.Applications;
import com.misboi.TicketingSystem.Model.Components;
import com.misboi.TicketingSystem.Model.EventLog;
import com.misboi.TicketingSystem.Model.FileDetails;
import com.misboi.TicketingSystem.Model.Modules;
import com.misboi.TicketingSystem.Model.RoleComponentMapping;
import com.misboi.TicketingSystem.Model.Roles;
import com.misboi.TicketingSystem.Model.Sessions;
import com.misboi.TicketingSystem.Model.TicketAllocation;
import com.misboi.TicketingSystem.Model.Tickets;
import com.misboi.TicketingSystem.Model.TicketsAudit;
import com.misboi.TicketingSystem.Model.UserRoleMapping;
import com.misboi.TicketingSystem.Model.Users;
import com.misboi.TicketingSystem.Repository.ApplicationsRepo;
import com.misboi.TicketingSystem.Repository.ComponentsRepo;
import com.misboi.TicketingSystem.Repository.EventLogRepo;
import com.misboi.TicketingSystem.Repository.FileDetailsRepo;
import com.misboi.TicketingSystem.Repository.ModulesRepo;
import com.misboi.TicketingSystem.Repository.RoleComponentMappingRepo;
import com.misboi.TicketingSystem.Repository.RolesRepo;
import com.misboi.TicketingSystem.Repository.SessionsRepo;
import com.misboi.TicketingSystem.Repository.TicketAllocationRepo;
import com.misboi.TicketingSystem.Repository.TicketsAuditRepo;
import com.misboi.TicketingSystem.Repository.TicketsRepo;
import com.misboi.TicketingSystem.Repository.UserRoleMappingRepo;
import com.misboi.TicketingSystem.Repository.UsersRepo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web")
public class TicketsReportController {
	
	@Autowired
	private GeneratePdfForTickets generatePdfForTickets;
	
	@Autowired
	private GeneratePdfForComponents generatePdfForComponents;
	
	@Autowired
	private GeneratePdfForEventLog generatePdfForEventLog;
	
	@Autowired
	private GeneratePdfForFileDetails generatePdfForFileDetails;
	
	@Autowired
	private GeneratePdfForRoleComponentMapping generatePdfForRoleComponentMapping;
	
	@Autowired
	private GeneratePdfForSessions generatePdfForSessions;
	
	@Autowired
	private GeneratePdfForUserRoleMapping generatePdfForUserRoleMapping;
	
	@Autowired
	private GeneratePdfForUsers generatePdfForUsers;
	
	@Autowired
	private GeneratePdfForTicketsAudit generatePdfForTicketsAudit;
	
	@Autowired
	private GeneratePdfForModules generatePdfForModules;
	
	@Autowired
	private GeneratePdfForApplication generatePdfForApplication;
	
	@Autowired
	private GeneratePdfForTicketAllocation generatePdfForTicketAllocation;
	
	@Autowired
	private GeneratePdfForRoles generatePdfForRoles;
	
	
	
	
	@Autowired
    private ComponentsRepo componentsRepo;
	
	@Autowired
    private EventLogRepo eventLogRepo;
	
	@Autowired
    private FileDetailsRepo fileDetailsRepo;
	
	@Autowired
    private RoleComponentMappingRepo roleComponentMappingRepo;
	
	@Autowired
    private SessionsRepo sessionsRepo;
	
	@Autowired
    private UserRoleMappingRepo userRoleMappingRepo;
	
	@Autowired
    private UsersRepo usersRepo;
	
	@Autowired
    private TicketsRepo ticketsRepo;
	
	@Autowired
    private TicketsAuditRepo ticketsAuditRepo;
	
	@Autowired
    private ModulesRepo modulesRepo;
	
	@Autowired
    private ApplicationsRepo applicationsRepo;
	
	@Autowired
	private TicketAllocationRepo ticketAllocationRepo; 
	
	@Autowired
	private RolesRepo rolesRepo;
	
	
	
	
	
	// Excel Report Exports
	
	@GetMapping("/export/excelTickets")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForTickets(HttpServletResponse response)throws IOException{
		response.setContentType("Tickets/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Tickets_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Tickets> listAllTickets=(List<Tickets>) ticketsRepo.findAll();
		GenerateExcelForTickets exp =new GenerateExcelForTickets(listAllTickets);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelTicketsAudit")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForTicketsAuditc (HttpServletResponse response)throws IOException{
		response.setContentType("TicketsAudit/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=TicketsAudit_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<TicketsAudit> listAllTicketsAudit=(List<TicketsAudit>) ticketsAuditRepo.findAll();
		GenerateExcelForTicketAudit exp =new GenerateExcelForTicketAudit(listAllTicketsAudit);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelModules")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForModules (HttpServletResponse response)throws IOException{
		response.setContentType("Modules/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Modules_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Modules> listAllModules=(List<Modules>) modulesRepo.findAll();
		GenerateExcelForModules exp =new GenerateExcelForModules(listAllModules);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelApplications")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForApplications (HttpServletResponse response)throws IOException{
		response.setContentType("Application/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Applications_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Applications> listAllApplications=(List<Applications>) applicationsRepo.findAll();
		GenerateExcelForApplications exp =new GenerateExcelForApplications(listAllApplications);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelComponents")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForComponents (HttpServletResponse response)throws IOException{
		response.setContentType("Components/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Components_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Components> listAllComponents=(List<Components>) componentsRepo.findAll();
		GenerateExcelForComponents exp =new GenerateExcelForComponents(listAllComponents);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelEventLog")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForEventLog (HttpServletResponse response)throws IOException{
		response.setContentType("EventLog/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=EventLog_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<EventLog> listAllEventLog=(List<EventLog>) eventLogRepo.findAll();
		GenerateExcelForEventLog exp =new GenerateExcelForEventLog(listAllEventLog);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelFileDetails")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForFileDetails (HttpServletResponse response)throws IOException{
		response.setContentType("FileDetails/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=FileDetails_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<FileDetails> listAllFileDetails=(List<FileDetails>) fileDetailsRepo.findAll();
		GenerateExcelForFileDetails exp =new GenerateExcelForFileDetails(listAllFileDetails);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelRoleComponentMapping")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForRoleComponentMapping (HttpServletResponse response)throws IOException{
		response.setContentType("RoleComponentMapping/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=RoleComponentMapping_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<RoleComponentMapping> listAllRoleComponentMapping=(List<RoleComponentMapping>) roleComponentMappingRepo.findAll();
		GenerateExcelForRoleComponentMapping exp =new GenerateExcelForRoleComponentMapping(listAllRoleComponentMapping);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelRoles")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForRoles (HttpServletResponse response)throws IOException{
		response.setContentType("Roles/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Roles_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Roles> listAllRoles=(List<Roles>) rolesRepo.findAll();
		GenerateExcelForRoles exp =new GenerateExcelForRoles(listAllRoles);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelSessions")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForSessions (HttpServletResponse response)throws IOException{
		response.setContentType("Sessions/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Sessions_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Sessions> listAllSessions=(List<Sessions>) sessionsRepo.findAll();
		GenerateExcelForSessions exp =new GenerateExcelForSessions(listAllSessions);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelTicketAllocation")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForTicketAllocation (HttpServletResponse response)throws IOException{
		response.setContentType("TicketAllocation/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=Roles_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<TicketAllocation> listAllTicketAllocation=(List<TicketAllocation>) ticketAllocationRepo.findAll();
		GenerateExcelForTicketAllocation exp =new GenerateExcelForTicketAllocation(listAllTicketAllocation);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelUserRoleMapping")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForUserRoleMapping (HttpServletResponse response)throws IOException{
		response.setContentType("UserRoleMapping/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=UserRoleMapping_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<UserRoleMapping> listAllUserRoleMapping=(List<UserRoleMapping>) userRoleMappingRepo.findAll();
		GenerateExcelForUserRoleMapping exp =new GenerateExcelForUserRoleMapping(listAllUserRoleMapping);
		exp.export(response);
		
	}
	
	@GetMapping("/export/excelUsers")
	@CrossOrigin(origins="http://localhost:4200")
	public void exportToExcelForUsers (HttpServletResponse response)throws IOException{
		response.setContentType("Users/octet-stream");
		String headerKey="content-Disposition";
		String headervalue="attachment;filename=RoleComponentMapping_info.xlsx";
		
		response.setHeader(headerKey, headervalue);
		List<Users> listAllUsers=(List<Users>) usersRepo.findAll();
		GenerateExcelForUsers exp =new GenerateExcelForUsers(listAllUsers);
		exp.export(response);
		
	}
	
	
	
	
	
	// PDF Report Exports
	
	@GetMapping("/export/pdfForTickets")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> ticketsPDFReport()throws IOException {
    	
        List<Tickets> tickets = (List<Tickets>) ticketsRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForTickets.ticketsPDFReport(tickets);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Tickets Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForTicketsAudit")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> ticketsAuditPDFReport()throws IOException {
    	
        List<TicketsAudit> ticketsAudit = (List<TicketsAudit>) ticketsAuditRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForTicketsAudit.ticketsAuditPDFReport(ticketsAudit);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Sessions Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForApplications")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> applicationsPDFReport()throws IOException {
    	
        List<Applications> applications = (List<Applications>) applicationsRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForApplication.applicationsPDFReport(applications);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Sessions Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForModules")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> modulesPDFReport()throws IOException {
    	
        List<Modules> modules = (List<Modules>) modulesRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForModules.modulesPDFReport(modules);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Modules Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForComponents")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<InputStreamResource> componentsPDFReport()throws IOException {
    	
        List<Components> components = (List<Components>) componentsRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForComponents.componentsPDFReport(components);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Components Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForEventLog")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<InputStreamResource> eventLogPDFReport()throws IOException {
    	
        List<EventLog> eventLog = (List<EventLog>) eventLogRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForEventLog.eventLogPDFReport(eventLog);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Event Log Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForFileDetails")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> fileDetailsPDFReport()throws IOException {
    	
        List<FileDetails> fileDetails = (List<FileDetails>) fileDetailsRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForFileDetails.fileDetailsPDFReport(fileDetails);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=File Details Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForRoleComponentMapping")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<InputStreamResource> roleComponentMappingPDFReport()throws IOException {
    	
        List<RoleComponentMapping> roleComponentMapping = (List<RoleComponentMapping>) roleComponentMappingRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForRoleComponentMapping.roleComponentMappingPDFReport(roleComponentMapping);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Role Component Mapping Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForRoles")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<InputStreamResource> rolesPDFReport()throws IOException {
    	
        List<Roles> roles = (List<Roles>) rolesRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForRoles.rolesPDFReport(roles);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Role Component Mapping Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/export/pdfForSessions")
    @CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> sessionPDFReport()throws IOException {
    	
        List<Sessions> sessions = (List<Sessions>) sessionsRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForSessions.sessionPDFReport(sessions);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Sessions Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForTicketAllocation")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> ticketAllocationPDFReport()throws IOException {
    	
        List<TicketAllocation> ticketAllocation = (List<TicketAllocation>) ticketAllocationRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForTicketAllocation.ticketAllocationPDFReport(ticketAllocation);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Ticket Allocation Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForUserRoleMapping")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<InputStreamResource> userRoleMappingPDFReport()throws IOException {
    	
        List<UserRoleMapping> userRoleMapping = (List<UserRoleMapping>) userRoleMappingRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForUserRoleMapping.userRoleMappingPDFReport(userRoleMapping);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=User Role Mapping Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/export/pdfForUsers")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<InputStreamResource> userPDFReport()throws IOException {
    	
        List<Users> users = (List<Users>) usersRepo.findAll();
        ByteArrayInputStream bis = GeneratePdfForUsers.usersPDFReport(users);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Users Report.pdf");
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
