package com.misboi.TicketingSystem.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.misboi.TicketingSystem.Exception.TicketsNotFoundException;
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
import com.misboi.TicketingSystem.Services.TicketingService;


@RestController
@CrossOrigin("*")
public class TicketsController {
	
	@Autowired
	private TicketingService ticketingService;
	
	@Autowired
	private RolesRepo rolesRepo;
	
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
	private UsersRepo usersRepo;
	
	@Autowired
	private FileDetailsRepo fileDetailsRepo;
	
	@Autowired
	private EventLogRepo eventLogRepo;
	
	@Autowired
	private SessionsRepo sessionsRepo;
	
	@Autowired
	private UserRoleMappingRepo userRoleMappingRepo;
	
	@Autowired
	private ComponentsRepo componentsRepo;
	
	@Autowired
	private RoleComponentMappingRepo roleComponentMappingRepo;
	
	@PostMapping("/createUsers")
    @CrossOrigin(origins="http://localhost:4200")
    public Users createUsers(@RequestBody Users users) {
        return usersRepo.save(users);
    }
	
	@PostMapping("/createFileDetails")
    @CrossOrigin(origins="http://localhost:4200")
    public FileDetails createFileDetails(@RequestBody FileDetails fileDetails) {
        return fileDetailsRepo.save(fileDetails);
    }
	
	@PostMapping("/createRoles")
    @CrossOrigin(origins="http://localhost:4200")
    public Roles createRoles(@RequestBody Roles roles) {
        return rolesRepo.save(roles);
    }
	
	@PostMapping("/createEventLog")
    @CrossOrigin(origins="http://localhost:4200")
    public EventLog createEventLog(@RequestBody EventLog eventLog) {
        return eventLogRepo.save(eventLog);
    }
	
	@PostMapping("/createSessions")
    @CrossOrigin(origins="http://localhost:4200")
    public Sessions createSessions(@RequestBody Sessions sessions) {
        return sessionsRepo.save(sessions);
    }
	
	@PostMapping("/createUserRoleMapping")
    @CrossOrigin(origins="http://localhost:4200")
    public UserRoleMapping createUserRoleMapping(@RequestBody UserRoleMapping userRoleMapping) {
        return userRoleMappingRepo.save(userRoleMapping);
    }
	
	@PostMapping("/createComponents")
    @CrossOrigin(origins="http://localhost:4200")
    public Components createComponents(@RequestBody Components components) {
        return componentsRepo.save(components);
    }
	
	@PostMapping("/createRoleComponentMapping")
    @CrossOrigin(origins="http://localhost:4200")
    public RoleComponentMapping createRoleComponentMapping(@RequestBody RoleComponentMapping roleComponentMapping) {
        return roleComponentMappingRepo.save(roleComponentMapping);
    }
	
	@PostMapping("/createTicketAllocation")
    @CrossOrigin(origins="http://localhost:4200")
    public TicketAllocation createTicketAllocation(@RequestBody TicketAllocation ticketAllocation) {
       return ticketAllocationRepo.save(ticketAllocation);
    }
	
	@PostMapping("/createModules")
    @CrossOrigin(origins="http://localhost:4200")
    public Modules createModules(@RequestBody Modules modules) {
      return modulesRepo.save(modules);
    }
	
	@PostMapping("/createApplications")
    @CrossOrigin(origins="http://localhost:4200")
    public Applications createApplications(@RequestBody Applications applications) {
      return applicationsRepo.save(applications);
    }
	
	@PostMapping("/createTickets")
	@CrossOrigin(origins="http://localhost:4200")
	public String createTickets(@RequestBody Tickets tickets) {
	     Tickets tkt = new Tickets();
	     tkt.setAppid(tickets.getAppid());
	     tkt.setTitle(tickets.getTitle());
	     tkt.setDescription(tickets.getDescription());
	     tkt.setModuleid(tickets.getModuleid());
	     tkt.setPriority(tickets.getPriority());
	     tkt.setComplexity(tickets.getComplexity());
	     tkt.setStatus(tickets.getStatus());
	     tkt.setRemarks(tickets.getRemarks());
	     tkt.setCreatedat(tickets.getCreatedat());
	     tkt.setCreatedby(tickets.getCreatedby());
	     tkt.setUpdatedat(tickets.getUpdatedat());
	     tkt.setUpdatedby(tickets.getUpdatedby());
	     TicketsAudit ticketsAudit = new TicketsAudit();
	     ticketsAudit.setAppid(tickets.getAppid());
	     ticketsAudit.setTitle(tickets.getTitle());
	     ticketsAudit.setDescription(tickets.getDescription());
	     ticketsAudit.setModuleid(tickets.getModuleid());
	     ticketsAudit.setPriority(tickets.getPriority());
	     ticketsAudit.setComplexity(tickets.getComplexity());
	     ticketsAudit.setStatus(tickets.getStatus());
	     ticketsAudit.setRemarks(tickets.getRemarks());
	     ticketsAudit.setCreatedat(tickets.getCreatedat());
	     ticketsAudit.setCreatedby(tickets.getCreatedby());
	     ticketsAudit.setUpdatedat(tickets.getUpdatedat());
	     ticketsAudit.setUpdatedby(tickets.getUpdatedby());
	     ticketingService.saveTicketidInTicketAudit(tickets, ticketsAudit);
	     return "Ticket Create Successfully";
	}
	
	@GetMapping("/listAllTicketAllocation")
    @CrossOrigin(origins="http://localhost:4200")
    public List<TicketAllocation> getlistAllTicketAllocation(){
        return (List<TicketAllocation>) ticketAllocationRepo.findAll();
    } 
	
    @GetMapping("/listAllTickets")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Tickets> getAllTickets(){
        return (List<Tickets>) ticketsRepo.findAll();
    }
    
    @GetMapping("/listAllTicketsAudit")
    @CrossOrigin(origins="http://localhost:4200")
    public List<TicketsAudit> getAllTicketsAudit(){
        return (List<TicketsAudit>) ticketsAuditRepo.findAll();
    }
    
    @GetMapping("/listAllModules")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Modules> getAllModules(){
        return (List<Modules>) modulesRepo.findAll();
    }
    
    @GetMapping("/listAllFileDetails")
    @CrossOrigin(origins="http://localhost:4200")
    public List<FileDetails> getAllFileDetails(){
        return (List<FileDetails>) fileDetailsRepo.findAll();
    }
    
    @GetMapping("/listAllEventLog")
    @CrossOrigin(origins="http://localhost:4200")
    public List<EventLog> getAllEventLog(){
        return (List<EventLog>) eventLogRepo.findAll();
    }
    
    @GetMapping("/listAllUsers")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Users> getAllUsers(){
        return (List<Users>) usersRepo.findAll();
    }
    
    @GetMapping("/listAllSessions")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Sessions> getAllSessions(){
        return (List<Sessions>) sessionsRepo.findAll();
    }
    
    @GetMapping("/listAllUserRoleMapping")
    @CrossOrigin(origins="http://localhost:4200")
    public List<UserRoleMapping> getAllUserRoleMapping(){
        return (List<UserRoleMapping>) userRoleMappingRepo.findAll();
    }
    
    @GetMapping("/listAllComponents")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Components> getAllComponents(){
        return (List<Components>) componentsRepo.findAll();
    }
    
    @GetMapping("/listAllRoleComponentMapping")
    @CrossOrigin(origins="http://localhost:4200")
    public List<RoleComponentMapping> getAllRoleComponentMapping(){
        return (List<RoleComponentMapping>) roleComponentMappingRepo.findAll();
    }
    
    @GetMapping("/listAllApplications")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Applications> getAllApplications(){
        return (List<Applications>) applicationsRepo.findAll();
    }
    
    @GetMapping("/listTickets/{ticketid}")
    @CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<Tickets> getTicketsById(@PathVariable Integer ticketid) {
    	Tickets tickets = ticketsRepo.findById(ticketid).
    		   orElseThrow(() -> new TicketsNotFoundException("Tickets does not exists with ticketid: "+ticketid));
             
       return ResponseEntity.ok(tickets);   
    }
    
    @GetMapping("/listAllRoles")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Roles> getAllRoles(){
        return (List<Roles>) rolesRepo.findAll();
    } 
    
    @PutMapping("/updateTicketsData")
    @CrossOrigin(origins="http://localhost:4200")
	public Tickets updateTicketsData(@RequestBody Tickets tickets){
		return ticketingService.updateTickets(tickets);
	}

}
