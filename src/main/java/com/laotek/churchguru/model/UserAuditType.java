package com.laotek.churchguru.model;

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

import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;

@Entity
public class UserAuditType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_AUDIT_TYPE_ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private UserAuditTypeName userAuditTypeName;
	
	@OneToMany(mappedBy = "userAuditType")
	private Set<UserAudit> userAudits = new HashSet<UserAudit>();
	
	
	public UserAuditType() {
	}
	
	public UserAuditType(UserAuditTypeName userLogTypeName) {
		this.userAuditTypeName = userLogTypeName;
	}


	public UserAuditTypeName getUserAuditTypeName() {
		return userAuditTypeName;
	}

	public Long getId() {
		return id;
	}

	public Set<UserAudit> getUserLogs() {
		return userAudits;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null){
			if (obj instanceof UserAuditType){
				UserAuditType type = (UserAuditType)obj;
				if (type.getUserAuditTypeName().equals(this.getUserAuditTypeName())){
					return true;
				}
			}			
		}
		return false;
	}
			
}
