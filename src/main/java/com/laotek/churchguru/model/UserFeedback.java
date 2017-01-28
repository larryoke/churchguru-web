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

import com.laotek.churchguru.model.shared.enums.FeedbackMessageType;

@Entity
public class UserFeedback {

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
    @Enumerated(EnumType.STRING)
    private FeedbackMessageType type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean copySender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = true, updatable = false, nullable = false)
    private User user;

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

    public FeedbackMessageType getType() {
	return type;
    }

    public void setType(FeedbackMessageType type) {
	this.type = type;
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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
