package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.Sessions;

@Repository
public interface SessionsRepo extends JpaRepository <Sessions,Integer>{

}
