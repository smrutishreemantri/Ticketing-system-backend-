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
@Table(name="Components" )
public class Components {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer compid;

	@Column(name = "compname")
	private String compname;
	    
	@Column(name = "description")
	private String description;
	
	@Column(name = "comptype")
	private String comptype;
	
	@Column(name = "compurl")
	private String compurl;
	    
	@Column(name = "compstatus")
	private String compstatus;
	    
	@CreationTimestamp
	@Column(name="createdat",nullable = false)
	private Date createdat;
	    
	@Column(name = "createdby")
	private String createdby;
	
	@CreationTimestamp
	@Column(name="updatedat",nullable = false)
	private Date updatedat;
	    
	@Column(name = "updatedby")
	private String updatedby;
	    
	public Integer getCompid() {
		return compid;
	}

	public void setCompid(Integer compid) {
		this.compid = compid;
	}

	public String getCompname() {
		return compname;
	}

	public void setCompname(String compname) {
		this.compname = compname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComptype() {
		return comptype;
	}

	public void setComptype(String comptype) {
		this.comptype = comptype;
	}

	public String getCompurl() {
		return compurl;
	}

	public void setCompurl(String compurl) {
		this.compurl = compurl;
	}

	public String getCompstatus() {
		return compstatus;
	}

	public void setCompstatus(String compstatus) {
		this.compstatus = compstatus;
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
