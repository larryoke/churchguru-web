package com.laotek.churchguru.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AudioMessageNotification implements
	Comparable<AudioMessageNotification> {

    @Embeddable
    public static class Id implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "MESSAGE_ID")
	private Long eStoreMessageId;

	@Column(name = "NOTIF_ID")
	private Long eStoreNotificationId;

	public Id() {
	}

	public Id(Long eStoreMessageId, Long noteId) {
	    this.eStoreMessageId = eStoreMessageId;
	    this.eStoreNotificationId = noteId;
	}

	public boolean equals(Object o) {
	    if (o != null && o instanceof Id) {
		Id that = (Id) o;
		return this.eStoreMessageId.equals(that.eStoreMessageId)
			&& this.eStoreNotificationId
				.equals(that.eStoreNotificationId);
	    } else {
		return false;
	    }
	}

	public int hashCode() {
	    return eStoreMessageId.hashCode() + eStoreNotificationId.hashCode();
	}
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "MESSAGE_ID", insertable = false, updatable = false)
    private AudioMessage eStoreMessage;

    @ManyToOne
    @JoinColumn(name = "NOTIF_ID", insertable = false, updatable = false)
    private AudioNotification eStoreNotification;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    public AudioMessageNotification() {
    }

    public AudioMessageNotification(AudioMessage eStoreMessage,
                                        AudioNotification eStoreNotification) {
	this.eStoreMessage = eStoreMessage;
	this.eStoreNotification = eStoreNotification;

	this.id.eStoreMessageId = eStoreMessage.getId();
	this.id.eStoreNotificationId = eStoreNotification.getId();

	eStoreMessage.getEStoreMessageNotifications().add(this);
	eStoreNotification.getEStoreMessageNotifications().add(this);
    }

    public Id getId() {
	return id;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public AudioMessage getEStoreMessage() {
	return eStoreMessage;
    }

    public void setEStoreMessage(AudioMessage eStoreMessage) {
	this.eStoreMessage = eStoreMessage;
    }

    public AudioNotification getEStoreNotification() {
	return eStoreNotification;
    }

    public void setEStoreNotification(AudioNotification eStoreNotification) {
	this.eStoreNotification = eStoreNotification;
    }

    @Override
    public int compareTo(AudioMessageNotification another) {
	if (eStoreNotification.getEStoreNotificationType().compareTo(
		another.getEStoreNotification().getEStoreNotificationType()) < 0) {
	    return -1;

	} else if (eStoreNotification.getEStoreNotificationType().compareTo(
		another.getEStoreNotification().getEStoreNotificationType()) > 0) {
	    return 1;
	}
	return 0;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

}
