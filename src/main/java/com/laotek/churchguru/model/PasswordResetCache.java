package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PasswordResetCache {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESET_ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="USER_ID",
	insertable = true,
	updatable = false,
	nullable = false)
	private User user;
	
	@Column(nullable=false, unique = true)
	private String passwordResetIdentifier;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswordResetIdentifier() {
		return passwordResetIdentifier;
	}

	public void setPasswordResetIdentifier(String passwordResetIdentifier) {
		this.passwordResetIdentifier = passwordResetIdentifier;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
