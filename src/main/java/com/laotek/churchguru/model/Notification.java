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

import com.laotek.churchguru.model.shared.enums.NotificationSchedule;
import com.laotek.churchguru.model.shared.enums.NotificationStatus;
import com.laotek.churchguru.model.shared.enums.NotificationType;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationSchedule notificationSchedule;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "ORG_ID", insertable = true, updatable = false, nullable = false)
    private Organisation organisation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

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

    public NotificationType getNotificationType() {
	return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
	this.notificationType = notificationType;
    }

    public NotificationStatus getNotificationStatus() {
	return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
	this.notificationStatus = notificationStatus;
    }

    public NotificationSchedule getNotificationSchedule() {
	return notificationSchedule;
    }

    public void setNotificationSchedule(
	    NotificationSchedule notificationSchedule) {
	this.notificationSchedule = notificationSchedule;
    }

    public String getFullname() {
	return fullname;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
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

    public Organisation getOrganisation() {
	return organisation;
    }

    public void setOrganisation(Organisation organisation) {
	this.organisation = organisation;
    }

}
