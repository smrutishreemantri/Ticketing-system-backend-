package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.RoleComponentMapping;

@Repository
public interface RoleComponentMappingRepo extends JpaRepository <RoleComponentMapping,Integer>{

}
