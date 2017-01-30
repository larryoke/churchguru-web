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

import com.laotek.churchguru.model.shared.enums.EStoreMessageStatus;

@Entity
public class EStoreMessage {

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
    private EStoreMessageStatus eStoreMessageStatus = EStoreMessageStatus.NEW;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "eStoreMessage")
    private Set<ListeningMessageNotification> eStoreMessageNotifications = new HashSet<ListeningMessageNotification>();

    @ManyToOne
    @JoinColumn
    private ListeningSpeaker eStoreSpeaker;

    @ManyToOne
    @JoinColumn
    private ListeningCategory eStoreCategory;

    @ManyToOne
    @JoinColumn
    private EStoreMessagePicture eStoreMessagePicture;

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
    private List<ListeningMemberMessage> eStoreMemberMessages = new ArrayList<ListeningMemberMessage>();

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

    public List<ListeningMemberMessage> getEStoreMemberMessages() {
	return eStoreMemberMessages;
    }

    public void setEStoreMemberMessages(
	    List<ListeningMemberMessage> eStoreMessageCategories) {
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

    public ListeningSpeaker getEStoreSpeaker() {
	return eStoreSpeaker;
    }

    public void setEStoreSpeaker(ListeningSpeaker eStoreSpeaker) {
	this.eStoreSpeaker = eStoreSpeaker;
    }

    public ListeningSpeaker geteStoreSpeaker() {
	return eStoreSpeaker;
    }

    public void seteStoreSpeaker(ListeningSpeaker eStoreSpeaker) {
	this.eStoreSpeaker = eStoreSpeaker;
    }

    public ListeningCategory geteStoreCategory() {
	return eStoreCategory;
    }

    public void seteStoreCategory(ListeningCategory eStoreCategory) {
	this.eStoreCategory = eStoreCategory;
    }

    public EStoreMessagePicture geteStoreMessagePicture() {
	return eStoreMessagePicture;
    }

    public void seteStoreMessagePicture(
	    EStoreMessagePicture eStoreMessagePicture) {
	this.eStoreMessagePicture = eStoreMessagePicture;
    }

    public List<ListeningMemberMessage> geteStoreMemberMessages() {
	return eStoreMemberMessages;
    }

    public void seteStoreMemberMessages(
	    List<ListeningMemberMessage> eStoreMemberMessages) {
	this.eStoreMemberMessages = eStoreMemberMessages;
    }

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    public Set<ListeningMessageNotification> getEStoreMessageNotifications() {
	return eStoreMessageNotifications;
    }

    public void setEStoreMessageNotifications(
	    Set<ListeningMessageNotification> eStoreMessageNotifications) {
	this.eStoreMessageNotifications = eStoreMessageNotifications;
    }

}
