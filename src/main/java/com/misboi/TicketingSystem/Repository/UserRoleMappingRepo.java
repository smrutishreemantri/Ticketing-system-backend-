package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.UserRoleMapping;

@Repository
public interface UserRoleMappingRepo extends JpaRepository <UserRoleMapping,Integer>{

}
