package com.misboi.TicketingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misboi.TicketingSystem.Model.FileDetails;

@Repository
public interface FileDetailsRepo extends JpaRepository <FileDetails,Integer>{

}
