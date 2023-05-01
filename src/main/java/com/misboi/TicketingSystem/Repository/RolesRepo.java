package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Integer>{

}
