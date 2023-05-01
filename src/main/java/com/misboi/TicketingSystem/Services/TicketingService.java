package com.misboi.TicketingSystem.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misboi.TicketingSystem.Model.Tickets;
import com.misboi.TicketingSystem.Model.TicketsAudit;
import com.misboi.TicketingSystem.Repository.TicketsAuditRepo;
import com.misboi.TicketingSystem.Repository.TicketsRepo;

import jakarta.transaction.Transactional;

@Service
public class TicketingService {
	
	@Autowired
    private TicketsRepo ticketsRepo;
	@Autowired
    private TicketsAuditRepo ticketsAuditRepo;
	
	public Tickets updateTickets(Tickets ticketsDetails) {
		Integer ticketid=ticketsDetails.getTicketid();
		Tickets model= ticketsRepo.findById(ticketid).get();
		//Tickets model = ticketsRepo.findById(ticketid).orElseThrow(() -> new TicketsNotFoundException("Application with this id "+ticketid+" does not exists"));
		//Integer ticketid=tickets.getTicketid();
        TicketsAudit audit = new TicketsAudit();
        model.setAppid(ticketsDetails.getAppid());
        model.setTitle(ticketsDetails.getTitle());
        model.setDescription(ticketsDetails.getDescription());
        model.setModuleid(ticketsDetails.getModuleid());
        model.setPriority(ticketsDetails.getPriority());
        model.setComplexity(ticketsDetails.getComplexity());
        model.setStatus(ticketsDetails.getStatus());
        model.setRemarks(ticketsDetails.getRemarks());
        model.setCreatedat(ticketsDetails.getCreatedat());
        model.setCreatedby(ticketsDetails.getCreatedby());
        model.setUpdatedat(ticketsDetails.getUpdatedat());
        model.setUpdatedby(ticketsDetails.getUpdatedby());
        audit.setTicketid(ticketsDetails.getTicketid());
        audit.setAppid(ticketsDetails.getAppid());
        audit.setTitle(ticketsDetails.getTitle());
        audit.setDescription(ticketsDetails.getDescription());
        audit.setModuleid(ticketsDetails.getModuleid());
        audit.setPriority(ticketsDetails.getPriority());
        audit.setComplexity(ticketsDetails.getComplexity());
        audit.setStatus(ticketsDetails.getStatus());
        audit.setRemarks(ticketsDetails.getRemarks());
        audit.setCreatedat(ticketsDetails.getCreatedat());
        audit.setCreatedby(ticketsDetails.getCreatedby());
        audit.setUpdatedat(ticketsDetails.getUpdatedat());
        audit.setUpdatedby(ticketsDetails.getUpdatedby());
//        audit.setTickets(model);
//        model.setTicketsAudit(audit);
        ticketsAuditRepo.save(audit);
    Tickets updatedData = ticketsRepo.save(model);
    return ticketsRepo.save(updatedData);
}
	@Transactional
	 public void saveTicketidInTicketAudit(Tickets tickets,TicketsAudit ticketsAudit) {
		// Save the customer first
		 ticketsRepo.save(tickets);
	        // Set the customer ID in the order object and save the order
		 ticketsAudit.setTicketid(tickets.getTicketid());
		 ticketsAuditRepo.save(ticketsAudit);
	   
	    }
	
}
