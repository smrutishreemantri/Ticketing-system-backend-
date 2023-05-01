package com.misboi.TicketingSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.TicketsAudit;

@Repository
public interface TicketsAuditRepo extends CrudRepository<TicketsAudit,Integer> {

}
