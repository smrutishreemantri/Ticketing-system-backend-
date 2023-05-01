package com.misboi.TicketingSystem.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.misboi.TicketingSystem.Model.Tickets;


@Repository
public interface TicketsRepo extends CrudRepository<Tickets,Integer>{
	@Query("SELECT ticket FROM Tickets ticket where ticket.moduleid=:moduleid")
	  public List<Tickets>getJoinInformation(@Param ("moduleid") Integer moduleid);
}
