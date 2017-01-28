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
import javax.persistence.ManyToMany;

import com.laotek.churchguru.model.shared.enums.UserRoleName;

@Entity
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ROLE_ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private UserRoleName userRoleName;
	
	@ManyToMany(mappedBy = "userRoles")
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	public UserRole(){}
	
	public UserRole(UserRoleName roleName){
		this.userRoleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRoleName getUserRoleName() {
		return userRoleName;
	}

	public void setAdminRoleName(UserRoleName adminRoleName) {
		this.userRoleName = adminRoleName;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null){
			if (obj instanceof UserRole){
				UserRole role = (UserRole)obj;
				if (role.getUserRoleName().equals(this.getUserRoleName())){
					return true;
				}
			}			
		}
		return false;
	}
	
}
