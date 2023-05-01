package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.EventLog;

@Repository
public interface EventLogRepo extends JpaRepository <EventLog,Integer>{

}
