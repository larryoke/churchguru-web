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
public class NewUserSetupEmail {

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

    public Organisation getOrganisation() {
	return organisation;
    }

    public void setOrganisation(Organisation organisation) {
	this.organisation = organisation;
    }
}
