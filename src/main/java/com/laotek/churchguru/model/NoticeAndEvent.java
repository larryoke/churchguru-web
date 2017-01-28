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

import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;

@Entity
public class NoticeAndEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTANT_MESSAGE_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String messageBody;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BrowseMessagesType messageType;

    @Column(name = "base64PicData", length = 16777215)
    private String base64Data;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "filename")
    private String filename;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date eventDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EventTime eventTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = true, updatable = false, nullable = false)
    private User user;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getMessageBody() {
	return messageBody;
    }

    public void setMessageBody(String messageBody) {
	this.messageBody = messageBody;
    }

    public Date getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public BrowseMessagesType getMessageType() {
	return messageType;
    }

    public void setMessageType(BrowseMessagesType messageType) {
	this.messageType = messageType;
    }

    public Date getEventDate() {
	return eventDate;
    }

    public void setEventDate(Date eventDate) {
	this.eventDate = eventDate;
    }

    public EventTime getEventTime() {
	return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
	this.eventTime = eventTime;
    }

    public String getBase64Data() {
	return base64Data;
    }

    public void setBase64Data(String base64Data) {
	this.base64Data = base64Data;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getFilename() {
	return filename;
    }

    public void setFilename(String filename) {
	this.filename = filename;
    }

}
