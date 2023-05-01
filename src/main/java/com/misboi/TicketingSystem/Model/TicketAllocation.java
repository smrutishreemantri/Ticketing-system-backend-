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
@Table(name="TicketAllocation" )
public class TicketAllocation {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer slno;
	 
	 @Column(name ="ticketid")
	 private Integer ticketid;
	 
	 @Column(name ="allocatedto")
	 private String allocatedto;
	 
	 @CreationTimestamp
	 @Column(name="minduedate",nullable = false)
	 private Date minduedate;
	 
	 @CreationTimestamp
	 @Column(name="maxduedate",nullable = false)
	 private Date maxduedate;
	 
	 @CreationTimestamp
	 @Column(name="createdat",nullable = false)
	 private Date createdat;
	 
	 @Column(name ="createdby")
	 private String createdby;
	 
	 @CreationTimestamp
	 @Column(name="updatedat",nullable = false)
	 private Date updatedat;
	 
	 @Column(name ="updatedby")
	 private String updatedby;

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Integer getTicketid() {
		return ticketid;
	}

	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}

	public String getAllocatedto() {
		return allocatedto;
	}

	public void setAllocatedto(String allocatedto) {
		this.allocatedto = allocatedto;
	}

	public Date getMinduedate() {
		return minduedate;
	}

	public void setMinduedate(Date minduedate) {
		this.minduedate = minduedate;
	}

	public Date getMaxduedate() {
		return maxduedate;
	}

	public void setMaxduedate(Date maxduedate) {
		this.maxduedate = maxduedate;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
}
