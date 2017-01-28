package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DepartmentMetaData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPT_META_DATA_ID")
	private Long id;
	
	@Column(nullable=false, unique=false)
	private Long currentCount;
	
	@Column(nullable=false, unique=false)
	private String directorateName;	
	
	@Column(nullable=false, unique=false)
	private String departmentName;
	
	@Column(nullable=false, unique=true)
	private String departmentIdentifier;


	@ManyToOne
	@JoinColumn(name="ORG_ID",
			insertable = true,
			updatable = false,
			nullable = false)
	private Organisation organisation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	public Long getId() {
		return id;
	}	

	public Long getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Long currentCount) {
		this.currentCount = currentCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getDirectorateName() {
		return directorateName;
	}

	public void setDirectorateName(String directorateName) {
		this.directorateName = directorateName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public String getDepartmentIdentifier() {
		return departmentIdentifier;
	}

	public void setDepartmentIdentifier(String departmentIdentifier) {
		this.departmentIdentifier = departmentIdentifier;
	}

}
