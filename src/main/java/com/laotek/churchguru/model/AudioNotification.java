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

import com.laotek.churchguru.model.shared.enums.ListeningNotificationType;

@Entity
public class AudioNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    private ListeningNotificationType eStoreNotificationType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @OneToMany(mappedBy = "eStoreNotification")
    private Set<AudioMessageNotification> eStoreMessageNotifications = new HashSet<AudioMessageNotification>();

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

    public Set<AudioMessageNotification> getEStoreMessageNotifications() {
	return eStoreMessageNotifications;
    }

    public void setEStoreMessageNotifications(
	    Set<AudioMessageNotification> eStoreMessageNotifications) {
	this.eStoreMessageNotifications = eStoreMessageNotifications;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public ListeningNotificationType getEStoreNotificationType() {
	return eStoreNotificationType;
    }

    public void setEStoreNotificationType(
	    ListeningNotificationType eStoreNotificationType) {
	this.eStoreNotificationType = eStoreNotificationType;
    }
}
