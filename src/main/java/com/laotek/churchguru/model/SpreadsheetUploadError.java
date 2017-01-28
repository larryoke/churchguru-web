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
public class SpreadsheetUploadError {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@Column(nullable=false)
	private String affectedRow;

	@Column(nullable=false)
	private String affectedCol;
	
	@Column(nullable=false)
	private String errorMessage;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	

	public String getAffectedRow() {
	    return affectedRow;
	}

	public void setAffectedRow(String affectedRow) {
	    this.affectedRow = affectedRow;
	}

	public String getAffectedCol() {
	    return affectedCol;
	}

	public void setAffectedCol(String affectedCol) {
	    this.affectedCol = affectedCol;
	}

	public String getErrorMessage() {
	    return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
	    this.errorMessage = errorMessage;
	}

	public Date getCreatedDate() {
	    return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
	    this.createdDate = createdDate;
	}
	
	
}
