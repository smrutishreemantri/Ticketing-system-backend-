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
@Table(name="applications")
public class Applications {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appid;

    @Column(name = "appname")
    private String appname;
    
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

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
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
