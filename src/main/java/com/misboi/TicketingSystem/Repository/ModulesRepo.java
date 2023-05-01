package com.misboi.TicketingSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.Modules;

@Repository
public interface ModulesRepo extends CrudRepository<Modules,Integer>{
	@Query("SELECT mod FROM Modules mod where mod.appid=:appid")
	  public List<Modules>getJoinInformation(@Param ("appid") Integer appid);
}
