package com.misboi.TicketingSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.Applications;

@Repository
public interface ApplicationsRepo extends CrudRepository<Applications,Integer>{

}
