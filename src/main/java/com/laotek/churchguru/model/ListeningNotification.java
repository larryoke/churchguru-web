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

import com.laotek.churchguru.model.shared.enums.EStoreNotificationType;

@Entity
public class EStoreNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    private EStoreNotificationType eStoreNotificationType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @OneToMany(mappedBy = "eStoreNotification")
    private Set<ListeningMessageNotification> eStoreMessageNotifications = new HashSet<ListeningMessageNotification>();

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

    public Set<ListeningMessageNotification> getEStoreMessageNotifications() {
	return eStoreMessageNotifications;
    }

    public void setEStoreMessageNotifications(
	    Set<ListeningMessageNotification> eStoreMessageNotifications) {
	this.eStoreMessageNotifications = eStoreMessageNotifications;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public EStoreNotificationType getEStoreNotificationType() {
	return eStoreNotificationType;
    }

    public void setEStoreNotificationType(
	    EStoreNotificationType eStoreNotificationType) {
	this.eStoreNotificationType = eStoreNotificationType;
    }
}
