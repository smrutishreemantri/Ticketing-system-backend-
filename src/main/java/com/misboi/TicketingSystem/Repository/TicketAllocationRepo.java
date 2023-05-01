package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.TicketAllocation;

@Repository
public interface TicketAllocationRepo extends JpaRepository<TicketAllocation,Integer>{

}
