package com.laotek.churchguru.daos.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserAudit;
import com.laotek.churchguru.model.UserAuditType;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserAuditDaoImpl extends BaseSessionFactory implements
	UserAuditDao {

    private static Log logger = LogFactory.getLog(UserAuditDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<UserAudit> getUserAuditLogs(User user, int beginIndex,
	    int endIndex) {
	Query query = getCurrentSession().createQuery(
		"from UserAudit audit where audit.user = :user "
			+ "order by audit.id.createdDate desc");
	query.setParameter("user", user);
	query.setFirstResult(beginIndex);
	query.setMaxResults(endIndex - beginIndex);
	return query.list();
    }

    @Override
    public void load() {
	List<UserAuditType> types = getUserAuditTypes();
	for (UserAuditTypeName name : UserAuditTypeName.values()) {
	    UserAuditType type = new UserAuditType(name);
	    if (!types.contains(type)) {
		getCurrentSession().persist(type);
	    }
	}
    }

    @Override
    public List<UserAuditType> getUserAuditTypes() {
	Query query = getCurrentSession().createQuery(
		"from UserAuditType userAuditType");
	return query.list();
    }

    @Override
    public void audit(String clientSessionId,
	    UserAuditTypeName userLogTypeName, String message) {
	String hql = "from User o where o.clientSessionId = :clientSessionId";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("clientSessionId", clientSessionId);
	User user = (User) query.uniqueResult();

	query = getCurrentSession().createQuery(
		"from UserAuditType uat where uat.userAuditTypeName = :name");
	query.setParameter("name", userLogTypeName);
	UserAuditType userLogType = (UserAuditType) query.uniqueResult();

	getCurrentSession().save(new UserAudit(user, userLogType, message));
    }

    @Override
    public List<UserAudit> getUserAuditLogs(String identifier, int beginIndex,
	    int endIndex) {
	logger.debug("->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	return getUserAuditLogs(user, beginIndex, endIndex);
    }

}
