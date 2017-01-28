package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TempPhoto {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PHOTO_ID")
	private Long id;

	@Column(nullable=false)
	private String filename;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	public Long getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
}
