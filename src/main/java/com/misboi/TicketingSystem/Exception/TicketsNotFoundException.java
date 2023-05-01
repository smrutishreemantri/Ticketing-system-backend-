package com.misboi.TicketingSystem.Exception;

public class TicketsNotFoundException extends RuntimeException{
	
	private static final Integer serialVersionUID = (int) 1L;

    public TicketsNotFoundException(String message){
        super(message);
    }
	
}
