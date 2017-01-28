package com.laotek.churchguru.daos.user;

import java.util.List;

import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserAudit;
import com.laotek.churchguru.model.UserAuditType;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;

public interface UserAuditDao {
	void audit(String currentSessionId, UserAuditTypeName userAuditTypeName, String message);
	List<UserAudit> getUserAuditLogs(User user, int beginIndex, int endIndex);
	List<UserAudit> getUserAuditLogs(String userId, int beginIndex, int endIndex);
	List<UserAuditType> getUserAuditTypes();
	void load();
}
