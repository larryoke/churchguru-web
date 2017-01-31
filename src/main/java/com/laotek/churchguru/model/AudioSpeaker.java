package com.laotek.churchguru.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.Title;

@Entity
public class AudioSpeaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

    @Column(nullable = false)
    private String forenames;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @OneToMany(mappedBy = "eStoreSpeaker")
    private Set<AudioMessage> messages = new HashSet<AudioMessage>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public Title getTitle() {
	return title;
    }

    public void setTitle(Title title) {
	this.title = title;
    }

    public String getForenames() {
	return forenames;
    }

    public void setForenames(String forenames) {
	this.forenames = forenames;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
