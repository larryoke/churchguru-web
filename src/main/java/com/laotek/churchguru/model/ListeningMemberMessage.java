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
public class EStoreMemberMessage implements Comparable<EStoreMemberMessage> {

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
    private EStoreMessage eStoreMessage;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private EStoreMember eStoreMember;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    public EStoreMemberMessage() {
    }

    public EStoreMemberMessage(EStoreMessage eStoreMessage,
	    EStoreMember eStoreMember) {
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

    public EStoreMessage getEStoreMessage() {
	return eStoreMessage;
    }

    public void setEStoreMessage(EStoreMessage eStoreMessage) {
	this.eStoreMessage = eStoreMessage;
    }

    public EStoreMember getEStoreMember() {
	return eStoreMember;
    }

    public void setEStoreMember(EStoreMember eStoreMember) {
	this.eStoreMember = eStoreMember;
    }

    @Override
    public int compareTo(EStoreMemberMessage another) {
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
