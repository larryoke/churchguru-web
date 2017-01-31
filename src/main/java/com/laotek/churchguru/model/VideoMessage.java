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
public class VideoMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = true, unique = false)
    private String location;

    @Column(nullable = true, unique = false)
    private String speakers;

    @Column(nullable = true, unique = false)
    private String youtubeUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date messageDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date lastUpdatedDate = new Date();

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

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    public String getSpeakers() {
	return speakers;
    }

    public void setSpeakers(String speakers) {
	this.speakers = speakers;
    }

    public String getYoutubeUrl() {
	return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
	this.youtubeUrl = youtubeUrl;
    }

}
