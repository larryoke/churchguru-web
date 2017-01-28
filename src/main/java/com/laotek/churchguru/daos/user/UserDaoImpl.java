package com.laotek.churchguru.daos.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.AbstractServiceImpl;
import com.laotek.churchguru.daos.shared.exceptions.DisabledUserAccountException;
import com.laotek.churchguru.daos.shared.exceptions.DuplicateUserNameException;
import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.daos.shared.exceptions.FullnameAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.LoginException;
import com.laotek.churchguru.daos.shared.exceptions.MobileAlreadyExistException;
import com.laotek.churchguru.daos.shared.exceptions.UsernameAlreadyExistException;
import com.laotek.churchguru.model.ForgotCredentialsEmail;
import com.laotek.churchguru.model.NewUserSetupEmail;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.PasswordResetCache;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.ForgottenCredentialType;
import com.laotek.churchguru.model.shared.enums.OrganisationAccountStatus;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserDaoImpl extends AbstractServiceImpl implements UserDao {

    private static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    @Value("${mail.sender.username}")
    private String mailSenderUsername;

    @Autowired
    @Value("${mail.host}")
    private String mailHost;

    @Override
    public void newUser(User user) {
	LOG.debug("newUser->");
	validateNewUser(user, user.getOrganisation());
	getCurrentSession().persist(user);
	LOG.debug("<-newUser");
    }

    @Override
    public void updateUser(User user) {
	LOG.debug("updateUser->");
	validateCurrentUser(user, user.getOrganisation());
	user = (User) getCurrentSession().merge(user);
	getCurrentSession().update(user);
	LOG.debug("<-updateUser");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsers(Organisation organisation) {
	LOG.debug("getUsers->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<User> users = query.list();
	for (User user : users) {
	    user.getUserProfile().getUserRoles().size();
	}
	LOG.debug("<-getUsers");
	return users;
    }

    @Override
    public User getUser(long adminId) {
	LOG.debug("getUser->");
	User user = (User) getCurrentSession().get(User.class, adminId);
	user.getUserProfile().getUserRoles().size();
	LOG.debug("<-getUser");
	return user;
    }

    @Override
    public User getUserByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	if (user == null)
	    return null;
	user.getUserProfile().getUserRoles().size();
	LOG.debug("<-getUserByIdentifier");
	return user;
    }

    @Override
    public User getUserByUsername(String username) {
	LOG.debug("getUserByUsername->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.username = :username");
	query.setParameter("username", username);
	User user = (User) query.uniqueResult();
	if (user == null)
	    return null;
	user.getUserProfile().getUserRoles().size();
	LOG.debug("<-getUserByUsername");
	return user;
    }

    private void validateNewUser(User newAdmin, Organisation org) {
	LOG.debug("validateNewUser->");
	List<User> admins = getUsers(org);
	if (admins != null && admins.size() > 0) {
	    for (User admin : admins) {
		if (admin.getUsername() != null
			&& admin.getUsername().equals(newAdmin.getUsername())) {
		    throw new UsernameAlreadyExistException();

		} else if (admin.getFullname().equals(newAdmin.getFullname())) {
		    throw new FullnameAlreadyExistException();

		} else if (admin.getMobile().equals(newAdmin.getMobile())) {
		    throw new MobileAlreadyExistException();
		}
	    }
	}
	LOG.debug("<-validateNewUser");
    }

    private void validateCurrentUser(User currentUser, Organisation org) {
	LOG.debug("validateCurrentUser->");
	List<User> users = getUsers(org);
	if (users != null && users.size() > 0) {
	    for (User user : users) {
		if (!currentUser.getId().equals(user.getId())) {
		    if (user.getUsername() != null
			    && user.getUsername().equals(
				    currentUser.getUsername())) {
			throw new UsernameAlreadyExistException();

		    } else if (user.getFullname().equals(
			    currentUser.getFullname())) {
			throw new FullnameAlreadyExistException();

		    } else if (user.getMobile().equals(currentUser.getMobile())) {
			throw new MobileAlreadyExistException();
		    }
		}
	    }
	}
	LOG.debug("<-validateCurrentUser");
    }

    @Override
    public User login(String username, String password) {
	LOG.debug("login->");

	String pwHash = createHash(password);
	String hql = "from User o where o.username = :username and o.password = :passwd "
		+ "and o.organisation.organisationAccountStatus = :orgStatus";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("username", username);
	query.setParameter("passwd", pwHash);
	query.setParameter("orgStatus", OrganisationAccountStatus.ACTIVE);

	User user = (User) query.uniqueResult();
	if (user == null) {
	    throw new LoginException("Incorrect Login");
	}

	user.getUserProfile().getUserRoles().size();

	if (user != null
		&& user.getUserAccountStatus().equals(
			UserAccountStatus.DISABLED)) {
	    throw new DisabledUserAccountException();

	}

	LOG.debug("<-login");
	return user;
    }

    @Override
    public void updateSessionIdByUsername(String username,
	    String clientSessionId) {
	LOG.debug("updateSessionIdByUsername->");
	String hql = "from User o where o.username = :username";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("username", username);
	User user = (User) query.uniqueResult();
	user.setClientSessionId(clientSessionId);
	getCurrentSession().update(user);
	LOG.debug("<-updateSessionIdByUsername");
    }

    @Override
    public void updateSessionIdByIdentifier(String identifier,
	    String clientSessionId) {
	LOG.debug("updateSessionIdByIdentifier->");
	String hql = "from User o where o.identifier = :identifier";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	user.setClientSessionId(clientSessionId);
	getCurrentSession().update(user);
	LOG.debug("<-updateSessionIdByIdentifier");
    }

    @Override
    public void updateLastLoginDate(String username) {
	LOG.debug("updateLastLoginDate->");
	Date date = new Date();
	String hql = "from User o where o.username = :username";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("username", username);
	User user = (User) query.uniqueResult();
	user.setLastLoginDate(date);
	getCurrentSession().update(user);
	LOG.debug("<-updateLastLoginDate");
    }

    private void updateLastLoginDateByIdentifier(String identifier) {
	LOG.debug("updateLastLoginDateByIdentifier->");
	Date date = new Date();
	String hql = "from User o where o.identifier = :identifier";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	user.setLastLoginDate(date);
	getCurrentSession().update(user);
	LOG.debug("<-updateLastLoginDateByIdentifier");
    }

    @Override
    public void updateUserAfterLogin(String username, String clientSessionId) {
	LOG.debug("updateUserAfterLogin->");
	updateLastLoginDate(username);
	updateSessionIdByUsername(username, clientSessionId);
	LOG.debug("<-updateUserAfterLogin");
    }

    @Override
    public void updateUserAfterLoginByIdentifier(String identifier,
	    String clientSessionId) {
	LOG.debug("updateUserAfterLoginByIdentifier->");
	updateLastLoginDateByIdentifier(identifier);
	updateSessionIdByIdentifier(identifier, clientSessionId);
	LOG.debug("<-updateUserAfterLoginByIndentifier");
    }

    @Override
    public void load() {
	testData();
    }

    private User createTestUser(Organisation org, String username,
	    String emailAddress, String password, String forenames,
	    String surname, String mobile) {
	LOG.debug("createTestUser->");
	User user = new User();
	user.setIdentifier(RandomStringUtils.random(10, true, true));
	user.setUsername(username);
	user.setEmailAddress(emailAddress);
	user.setPassword(createHash(password));
	user.setForenames(forenames);
	user.setSurname(surname);
	user.setLastUpdatedDate(new Date());
	user.setCreatedDate(new Date());
	user.setMobileCountryCode(CountryCode.UK);
	user.setMobile(mobile);
	user.setOrganisation(org);
	user.setUserAccountStatus(UserAccountStatus.ACTIVE);

	UserProfile userProfile = getUserProfile("admin", org);
	user.setUserProfile(userProfile);
	LOG.debug("<-createTestUser");
	return user;
    }

    private void testData() {
	LOG.debug("testData->");
	List<Organisation> orgs = getCurrentSession().createQuery(
		"from Organisation org").list();
	int count = 0;
	for (Organisation org : orgs) {
	    ++count;
	    if (count == 1) {
		User user = createTestUser(org, "eddieb",
			"noreply@mailinator.com", "test", "Eddie", "Banger",
			"+44781234567");
		if (!isUserExists(user)) {
		    newUser(user);
		}
	    }
	}
	LOG.debug("<-testData");
    }

    private UserProfile getUserProfile(String profileName,
	    Organisation organisation) {
	LOG.debug("getUserProfile->");
	Query query = getCurrentSession()
		.createQuery(
			"from UserProfile o where o.name = :name and o.organisation = :organisation");
	query.setParameter("name", profileName);
	query.setParameter("organisation", organisation);
	@SuppressWarnings("unchecked")
	UserProfile userProfile = (UserProfile) query.uniqueResult();
	LOG.debug("<-getUserProfile");
	return userProfile;
    }

    private boolean isUserExists(User user) {
	LOG.debug("getUserByClientSessionId->");
	User db = getUserByUsername(user.getUsername());
	if (db != null) {
	    LOG.debug("<-isUserExists(true)");
	    return true;
	}
	LOG.debug("<-isUserExists(false)");
	return false;
    }

    @Override
    public User getUserByClientSessionId(String clientSessionId) {
	LOG.debug("getUserByClientSessionId->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	User op = (User) query.uniqueResult();
	if (op == null) {
	    throw new ExpiredSessionException();
	}
	op.getUserProfile().getUserRoles().size();
	op.getOrganisation().getLogoItems().size();
	LOG.debug("<-getUserByClientSessionId");
	return op;
    }

    @Override
    public void enableUser(String identifier) {
	LOG.debug("enableUser->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	user.setUserAccountStatus(UserAccountStatus.ACTIVE);
	LOG.debug("<-enableUser");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createUsernameReminder(String emailAddress) {
	LOG.debug("createUsernameReminder->");

	Query query = getCurrentSession().createQuery(
		"from User o where o.emailAddress = :emailAddress");
	query.setParameter("emailAddress", emailAddress);
	List<User> users = query.list();
	if (users != null) {
	    for (User user : users) {

		removeExistingForgetCredentials(user.getUsername());

		Organisation org = user.getOrganisation();

		String orgName = org.getOrgName();
		String subject = "Your " + orgName + " username reminder";

		ForgottenCredentialType type = ForgottenCredentialType.USERNAME;
		ForgotCredentialsEmail forgotCredentialsEmail = createForgetCredentials(
			getMailSenderAddress(), user, subject, type);
		forgotCredentialsEmail.setSender(user);
		getCurrentSession().persist(forgotCredentialsEmail);
	    }
	}

	LOG.debug("<-createUsernameReminder");
    }

    @Override
    public void createNewUserSetupEmail(String identifier) {
	LOG.debug("createNewUserSetupEmail->");

	String subject = "You're invited to complete your account setup ";

	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();

	removeExistingNewUserSetup(identifier);

	NewUserSetupEmail newUserSetupEmail = createNewUserSetupEmail(
		getMailSenderAddress(), user, subject);
	getCurrentSession().persist(newUserSetupEmail);
	LOG.debug("<-createNewUserSetupEmail");
    }

    @Override
    public void createPasswordReset(String username) {
	LOG.debug("createForgetCredentials->");
	String passwordResetIdentifier = RandomStringUtils
		.randomAlphanumeric(20);

	Query query = getCurrentSession().createQuery(
		"from User o where o.username = :username");
	query.setParameter("username", username);
	User user = (User) query.uniqueResult();

	if (user != null) {

	    Organisation org = user.getOrganisation();

	    String orgName = org.getOrgName();
	    String subject = "Your " + orgName + " password reset";

	    removeExistingForgetCredentials(user.getUsername());
	    PasswordResetCache passwordResetCache = new PasswordResetCache();
	    passwordResetCache.setUser(user);
	    passwordResetCache.setCreatedDate(new Date());
	    passwordResetCache
		    .setPasswordResetIdentifier(passwordResetIdentifier);
	    getCurrentSession().persist(passwordResetCache);

	    ForgottenCredentialType type = ForgottenCredentialType.PASSWORD;
	    ForgotCredentialsEmail forgotCredentialsEmail = createForgetCredentials(
		    getMailSenderAddress(), user, subject, type);
	    forgotCredentialsEmail
		    .setPasswordResetIdentifier(passwordResetIdentifier);
	    getCurrentSession().persist(forgotCredentialsEmail);
	}
	LOG.debug("<-createPasswordReset");
    }

    private ForgotCredentialsEmail createForgetCredentials(
	    String mailSenderAddress, User user, String subject,
	    ForgottenCredentialType type) {
	LOG.debug("createForgetCredentials->");
	ForgotCredentialsEmail forgotCredentialsEmail = new ForgotCredentialsEmail();
	forgotCredentialsEmail.setSender(user);
	forgotCredentialsEmail.setSubject(subject);
	forgotCredentialsEmail.setForgottenCredentialType(type);
	forgotCredentialsEmail.setFullname(user.getFullname());
	forgotCredentialsEmail.setToAddr(user.getEmailAddress());
	forgotCredentialsEmail.setFromAddr(mailSenderAddress);
	forgotCredentialsEmail.setRecipientIdentifier(user.getIdentifier());
	forgotCredentialsEmail.setCreatedDate(new Date());
	LOG.debug("<-createForgetCredentials");
	return forgotCredentialsEmail;
    }

    private NewUserSetupEmail createNewUserSetupEmail(String mailSenderAddress,
	    User user, String subject) {
	LOG.debug("createNewUserSetupEmail->");
	NewUserSetupEmail newUserSetupEmail = new NewUserSetupEmail();
	newUserSetupEmail.setSubject(subject);
	newUserSetupEmail.setFullname(user.getFullname());
	newUserSetupEmail.setToAddr(user.getEmailAddress());
	newUserSetupEmail.setFromAddr(mailSenderAddress);
	newUserSetupEmail.setRecipientIdentifier(user.getIdentifier());
	newUserSetupEmail.setCreatedDate(new Date());
	newUserSetupEmail.setOrganisation(user.getOrganisation());
	LOG.debug("<-createNewUserSetupEmail");
	return newUserSetupEmail;
    }

    private void removeExistingForgetCredentials(String username) {
	LOG.debug("removeExistingForgetCredentials->");
	String hql = "delete ForgotCredentialsEmail email where email.username = :username";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("username", username);
	query.executeUpdate();
	LOG.debug("<-removeExistingForgetCredentials");
    }

    private void removeExistingNewUserSetup(String useridentifier) {
	LOG.debug("removeExistingNewUserSetup->");
	String hql = "delete NewUserSetupEmail email where email.recipientIdentifier = :recipientIdentifier";
	Query query = getCurrentSession().createQuery(hql);
	query.setParameter("recipientIdentifier", useridentifier);
	query.executeUpdate();
	LOG.debug("<-removeExistingNewUserSetup");
    }

    @Override
    public User resetPassword(String passwordIdentifier, String newPassword) {
	LOG.debug("resetPassword->");
	Query query = getCurrentSession()
		.createQuery(
			"from PasswordResetCache o where o.passwordResetIdentifier = :passwordResetIdentifier");
	query.setParameter("passwordResetIdentifier", passwordIdentifier);
	PasswordResetCache passwordResetCache = (PasswordResetCache) query
		.uniqueResult();
	User user = passwordResetCache.getUser();
	user.setPassword(createHash(newPassword));
	getCurrentSession().update(user);
	getCurrentSession().delete(passwordResetCache);
	LOG.debug("<-resetPassword");
	return user;
    }

    @Override
    public boolean isPasswordResetIdentifierValid(String passwordIdentifier) {
	LOG.debug("isPasswordResetIdentifierValid->");
	Query query = getCurrentSession()
		.createQuery(
			"from PasswordResetCache o where o.passwordResetIdentifier = :passwordResetIdentifier");
	query.setParameter("passwordResetIdentifier", passwordIdentifier);
	PasswordResetCache passwordResetCache = (PasswordResetCache) query
		.uniqueResult();
	if (passwordResetCache != null) {
	    LOG.debug("<-isPasswordResetIdentierValid(true)");
	    return true;
	}
	LOG.debug("<-isPasswordIdentifierValid(false)");
	return false;
    }

    @Override
    public User getPasswordResetUser(String passwordIdentifier) {
	LOG.debug("getPasswordResetUser->");
	Query query = getCurrentSession()
		.createQuery(
			"from PasswordResetCache o where o.passwordResetIdentifier = :passwordResetIdentifier");
	query.setParameter("passwordResetIdentifier", passwordIdentifier);
	PasswordResetCache passwordResetCache = (PasswordResetCache) query
		.uniqueResult();
	if (passwordResetCache != null) {
	    LOG.debug("<-getPasswordResetUser(true)");
	    User user = passwordResetCache.getUser();
	    user.getOrganisation();
	    return user;
	}
	LOG.debug("<-getPasswordResetUser(false)");
	return null;
    }

    @Override
    public boolean isNewUserSetupIdentifierValid(String identifier) {
	LOG.debug("isNewUserSetupIdentifierValid->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	User user = (User) query.uniqueResult();
	if (user != null) {
	    if (!user.getUserAccountStatus().equals(UserAccountStatus.INVITED)) {
		LOG.debug("<-isNewUserSetupIdentifierValid(false)");
		return false;
	    }
	    LOG.debug("<-isNewUserSetupIdentifierValid(true)");
	    return true;
	}
	LOG.debug("<-isNewUserSetupIdentifierValid(false)");
	return false;
    }

    @Override
    public void completeNewUserSetup(String identifier, String username,
	    String password) {
	LOG.debug("completeNewUserSetup->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.username = :username");
	query.setParameter("username", username);
	User user = (User) query.uniqueResult();

	if (user != null) {
	    throw new DuplicateUserNameException();
	}

	query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	user = (User) query.uniqueResult();
	user.setUsername(username);
	String pwHash = createHash(password);
	user.setPassword(pwHash);
	user.setUserAccountStatus(UserAccountStatus.ACTIVE);
	getCurrentSession().update(user);
	LOG.debug("<-completeNewUserSetup");
    }

    private String getMailSenderAddress() {
	return mailSenderUsername + "@" + mailHost;
    }

}
