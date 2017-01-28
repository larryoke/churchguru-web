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

import com.laotek.churchguru.model.shared.enums.ForgottenCredentialType;

@Entity
public class ForgotCredentialsEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String toAddr;

    @Column(nullable = false)
    private String fromAddr;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String recipientIdentifier;

    @Column(nullable = true, unique = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String passwordResetIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ForgottenCredentialType forgottenCredentialType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = true, updatable = false, nullable = false)
    private User sender;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getToAddr() {
	return toAddr;
    }

    public void setToAddr(String toAddr) {
	this.toAddr = toAddr;
    }

    public String getFromAddr() {
	return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
	this.fromAddr = fromAddr;
    }

    public String getFullname() {
	return fullname;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public String getRecipientIdentifier() {
	return recipientIdentifier;
    }

    public void setRecipientIdentifier(String recipientIdentifier) {
	this.recipientIdentifier = recipientIdentifier;
    }

    public String getPasswordResetIdentifier() {
	return passwordResetIdentifier;
    }

    public void setPasswordResetIdentifier(String passwordResetIdentifier) {
	this.passwordResetIdentifier = passwordResetIdentifier;
    }

    public ForgottenCredentialType getForgottenCredentialType() {
	return forgottenCredentialType;
    }

    public void setForgottenCredentialType(
	    ForgottenCredentialType forgottenCredentialType) {
	this.forgottenCredentialType = forgottenCredentialType;
    }

    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

}
