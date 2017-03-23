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
public class MediaMemberMessage implements Comparable<MediaMemberMessage> {

    @Embeddable
    public static class Id implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "MESSAGE_ID")
	private Long eStoreMessageId;

	@Column(name = "MEMBER_ID")
	private Long eStoreMemberId;

	public Id() {
	}

	public Id(Long eStoreMessageId, Long eStoreMemberId) {
	    this.eStoreMessageId = eStoreMessageId;
	    this.eStoreMemberId = eStoreMemberId;
	}

	public boolean equals(Object o) {
	    if (o != null && o instanceof Id) {
		Id that = (Id) o;
		return this.eStoreMessageId.equals(that.eStoreMessageId)
			&& this.eStoreMemberId.equals(that.eStoreMemberId);
	    } else {
		return false;
	    }
	}

	public int hashCode() {
	    return eStoreMessageId.hashCode() + eStoreMemberId.hashCode();
	}
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private MediaMessage eStoreMessage;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private MediaMember eStoreMember;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    public MediaMemberMessage() {
    }

    public MediaMemberMessage(MediaMessage eStoreMessage,
								  MediaMember eStoreMember) {
	this.eStoreMessage = eStoreMessage;
	this.eStoreMember = eStoreMember;

	this.id.eStoreMessageId = eStoreMessage.getId();
	this.id.eStoreMemberId = eStoreMember.getId();

	eStoreMessage.getEStoreMemberMessages().add(this);
	eStoreMember.getEStoreMemberMessages().add(this);
    }

    public Id getId() {
	return id;
    }

    public Date getCreatedDate() {
	return createdDate;
    }

    public MediaMessage getEStoreMessage() {
	return eStoreMessage;
    }

    public void setEStoreMessage(MediaMessage eStoreMessage) {
	this.eStoreMessage = eStoreMessage;
    }

    public MediaMember getEStoreMember() {
	return eStoreMember;
    }

    public void setEStoreMember(MediaMember eStoreMember) {
	this.eStoreMember = eStoreMember;
    }

    @Override
    public int compareTo(MediaMemberMessage another) {
	if (eStoreMember.getIdentifier().compareTo(
		another.getEStoreMember().getIdentifier()) < 0) {
	    return -1;

	} else if (eStoreMember.getIdentifier().compareTo(
		another.getEStoreMember().getIdentifier()) > 0) {
	    return 1;
	}
	return 0;
    }

}
