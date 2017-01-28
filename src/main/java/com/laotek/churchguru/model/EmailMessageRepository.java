package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.EmailMessageType;

@Entity
public class EmailMessageRepository {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(columnDefinition="TEXT", nullable=false)
	//@Type(type="text")
	private String message;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private EmailMessageType emailMessageType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmailMessageType getEmailMessageType() {
		return emailMessageType;
	}

	public void setEmailMessageType(EmailMessageType emailMessageType) {
		this.emailMessageType = emailMessageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
