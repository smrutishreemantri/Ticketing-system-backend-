package com.misboi.TicketingSystem.Model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Sessions" )
public class Sessions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sessionid;

	@Column(name ="userid")
	private Integer userid;
	    
	@CreationTimestamp
	@Column(name="logontime",nullable = false)
	private Date logontime;
	    
	@CreationTimestamp
	@Column(name="logofftime",nullable = false)
	private Date logofftime;

	

	public Integer getSessionid() {
		return sessionid;
	}

	public void setSessionid(Integer sessionid) {
		this.sessionid = sessionid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getLogontime() {
		return logontime;
	}

	public void setLogontime(Date logontime) {
		this.logontime = logontime;
	}

	public Date getLogofftime() {
		return logofftime;
	}

	public void setLogofftime(Date logofftime) {
		this.logofftime = logofftime;
	}
	
	
	
}
