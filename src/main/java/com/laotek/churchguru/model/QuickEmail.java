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

import com.laotek.churchguru.model.shared.enums.EmailRecipientType;

@Entity
public class QuickEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String toAddr;

    @Column(nullable = false)
    private String fromAddr;

    @Column(nullable = false)
    private String replyTo;

    @Column(nullable = false)
    private String replyToFullname;

    @Column(nullable = false)
    private String forename;

    @Column(nullable = false)
    private String surname;

    // because we don't store user addresses
    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String recipientIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailRecipientType recipientType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean copySender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "ORG_ID", insertable = true, updatable = false, nullable = false)
    private Organisation organisation;

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
	return forename + " " + surname;
    }

    public String getForename() {
	return forename;
    }

    public String getSurname() {
	return surname;
    }

    public String getAddress() {
	return address;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public boolean isCopySender() {
	return copySender;
    }

    public void setCopySender(boolean copySender) {
	this.copySender = copySender;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public String getReplyTo() {
	return replyTo;
    }

    public String getReplyToFullname() {
	return replyToFullname;
    }

    public void setReplyToFullname(String replyToFullname) {
	this.replyToFullname = replyToFullname;
    }

    public void setReplyTo(String replyTo) {
	this.replyTo = replyTo;
    }

    public String getRecipientIdentifier() {
	return recipientIdentifier;
    }

    public void setRecipientIdentifier(String recipientIdentifier) {
	this.recipientIdentifier = recipientIdentifier;
    }

    public EmailRecipientType getRecipientType() {
	return recipientType;
    }

    public void setRecipientType(EmailRecipientType recipientType) {
	this.recipientType = recipientType;
    }

    public Organisation getOrganisation() {
	return organisation;
    }

    public void setOrganisation(Organisation organisation) {
	this.organisation = organisation;
    }

}
