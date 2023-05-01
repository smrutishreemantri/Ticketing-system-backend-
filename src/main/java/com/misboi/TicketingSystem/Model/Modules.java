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
@Table(name="modules")
public class Modules {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleid;

    @Column(name = "appid")
    private Integer appid;
    
    @Column(name = "modname")
    private String modname;
    
    @Column(name = "description")
    private String description;
    
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

	public Integer getModuleid() {
		return moduleid;
	}

	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getModname() {
		return modname;
	}

	public void setModname(String modname) {
		this.modname = modname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
