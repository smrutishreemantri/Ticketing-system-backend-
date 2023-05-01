package com.misboi.TicketingSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.misboi.TicketingSystem.Repository.SessionsRepo;

@SpringBootApplication
public class TicketingSystemApplication implements CommandLineRunner{
	
	@Autowired
	private SessionsRepo sessionsRepo;

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
