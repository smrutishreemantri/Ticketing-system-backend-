package com.misboi.TicketingSystem.Model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="Tickets_Audit" )
public class TicketsAudit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slno;
	
	@Column(name ="ticketid")
    private Integer ticketid;

    @Column(name ="appid")
    private Integer appid;

    @Column(name = "title")
    private String title;
    
    	@Column(name = "description")
    	private String description;

    	@Column(name = "moduleid")
    	private Integer moduleid;

    	@Column(name = "priority")
    	private String priority;
    
    	@Column(name = "complexity")
    	private String complexity;
    
    	@Column(name = "status")
    	private String status;
    
    	@Column(name = "remarks")
    	private String remarks;
    
    	@CreationTimestamp
    	@Column(name="createdat",nullable = false)
    	private Date createdat;
 
    	@Column(name = "createdby")
    	private String createdby;
 
    	@UpdateTimestamp
    	@Column(name="updatedat",nullable = false)
    	private Date updatedat;
    
    	@Column(name = "updatedby")
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

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getModuleid() {
		return moduleid;
	}

	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
