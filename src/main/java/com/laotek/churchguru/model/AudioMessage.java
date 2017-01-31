package com.laotek.churchguru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.ListeningMessageStatus;

@Entity
public class AudioMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false, unique = false)
    private int salePointPerMessage = 0;

    @Column(nullable = true, unique = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    private ListeningMessageStatus eStoreMessageStatus = ListeningMessageStatus.NEW;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "eStoreMessage")
    private Set<AudioMessageNotification> eStoreMessageNotifications = new HashSet<AudioMessageNotification>();

    @ManyToOne
    @JoinColumn
    private AudioSpeaker eStoreSpeaker;

    @ManyToOne
    @JoinColumn
    private AudioCategory eStoreCategory;

    @ManyToOne
    @JoinColumn
    private AudioMessagePicture eStoreMessagePicture;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date messageDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, unique = false)
    private Date lastUpdatedDate = new Date();

    @OneToMany(mappedBy = "eStoreMessage")
    private List<AudioMemberMessage> eStoreMemberMessages = new ArrayList<AudioMemberMessage>();

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

    public List<AudioMemberMessage> getEStoreMemberMessages() {
	return eStoreMemberMessages;
    }

    public void setEStoreMemberMessages(
	    List<AudioMemberMessage> eStoreMessageCategories) {
	this.eStoreMemberMessages = eStoreMessageCategories;
    }

    public int getSalePointPerMessage() {
	return salePointPerMessage;
    }

    public void setSalePointPerMessage(int salePointPerMessage) {
	this.salePointPerMessage = salePointPerMessage;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public AudioSpeaker getEStoreSpeaker() {
	return eStoreSpeaker;
    }

    public void setEStoreSpeaker(AudioSpeaker eStoreSpeaker) {
	this.eStoreSpeaker = eStoreSpeaker;
    }

    public AudioSpeaker geteStoreSpeaker() {
	return eStoreSpeaker;
    }

    public void seteStoreSpeaker(AudioSpeaker eStoreSpeaker) {
	this.eStoreSpeaker = eStoreSpeaker;
    }

    public AudioCategory geteStoreCategory() {
	return eStoreCategory;
    }

    public void seteStoreCategory(AudioCategory eStoreCategory) {
	this.eStoreCategory = eStoreCategory;
    }

    public AudioMessagePicture geteStoreMessagePicture() {
	return eStoreMessagePicture;
    }

    public void seteStoreMessagePicture(
	    AudioMessagePicture eStoreMessagePicture) {
	this.eStoreMessagePicture = eStoreMessagePicture;
    }

    public List<AudioMemberMessage> geteStoreMemberMessages() {
	return eStoreMemberMessages;
    }

    public void seteStoreMemberMessages(
	    List<AudioMemberMessage> eStoreMemberMessages) {
	this.eStoreMemberMessages = eStoreMemberMessages;
    }

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    public Set<AudioMessageNotification> getEStoreMessageNotifications() {
	return eStoreMessageNotifications;
    }

    public void setEStoreMessageNotifications(
	    Set<AudioMessageNotification> eStoreMessageNotifications) {
	this.eStoreMessageNotifications = eStoreMessageNotifications;
    }

}
