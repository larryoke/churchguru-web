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
public class UserAudit {
	@Embeddable
	public static class Id implements Serializable{
		private static final long serialVersionUID = 1L;

		@Temporal(TemporalType.TIMESTAMP)
		private Date createdDate = new Date();
		
		@Column(name="USER_ID")
		private Long userId;
		
		@Column(name="USER_AUDIT_TYPE_ID")
		private Long userAuditTypeId;
		
		public Id(){}
		
		public Id(Long userId, Long userAuditTypeId){
			this.userId = userId;
			this.userAuditTypeId = userAuditTypeId;
		}
		
		public boolean equals(Object o){
			if (o !=null && o instanceof Id){
				Id that = (Id)o;
				return this.createdDate.compareTo(that.createdDate) == 0 && 
						this.userId.equals(that.userId) && 
						this.userAuditTypeId.equals(that.userAuditTypeId);
			}else{
				return false;
			}
		}
		public int hashCode(){
			return createdDate.hashCode() + userId.hashCode() + userAuditTypeId.hashCode();
		}
		
		public Date getCreatedDate(){
			return createdDate;
		}
	}
	
	
	@EmbeddedId
	private Id id = new Id();
	
	@ManyToOne
	@JoinColumn(name="USER_ID",
			insertable = false,
			updatable = false)
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="USER_AUDIT_TYPE_ID",
			insertable = false,
			updatable = false)
	private UserAuditType userAuditType;

	
	
	@Column
	private String message;

	public UserAudit() {}
	
	public UserAudit(User user, UserAuditType userAuditType, String message){
		this.user = user;
		this.userAuditType = userAuditType;
		this.message = message;
		
		this.id.userId = user.getId();
		this.id.userAuditTypeId = userAuditType.getId();
		
		user.getUserLogs().add(this);
		userAuditType.getUserLogs().add(this);
	}

	public Id getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public UserAuditType getUserLogType() {
		return userAuditType;
	}

	public Date getCreatedDate() {
		return getId().createdDate;
	}

	public String getMessage() {
		return message;
	}
	
	
}
