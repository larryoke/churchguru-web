package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.GuestSearchType;

@Entity
public class GuestMetaData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GUEST_META_DATA_ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false, unique=false)
	private GuestSearchType searchType;
	
	@Column
	private Long currentCount;
	

	@ManyToOne
	@JoinColumn(name="ORG_ID",
			insertable = true,
			updatable = false,
			nullable = false)
	private Organisation organisation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	
	public Long getId() {
		return id;
	}	

	public Long getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Long currentCount) {
		this.currentCount = currentCount;
	}

	public void setSearchType(GuestSearchType searchType) {
		this.searchType = searchType;
	}

	public GuestSearchType getSearchType() {
		return searchType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
}
