package com.laotek.churchguru.daos.user;

import java.util.List;

import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;

public interface UserDao {
    void newUser(User user);

    void updateUser(User user);

    List<User> getUsers(Organisation org);

    User getUser(long adminId);

    User getUserByIdentifier(String identifier);

    User getUserByUsername(String username);

    User getUserByClientSessionId(String clientSessionId);

    User login(String emailAddress, String password);

    void updateSessionIdByUsername(String username, String clientSessionId);

    void updateSessionIdByIdentifier(String identifier, String clientSessionId);

    void updateUserAfterLoginByIdentifier(String identifier,
	    String clientSessionId);

    void updateLastLoginDate(String emailAddress);

    void updateUserAfterLogin(String emailAddrss, String clientSessionId);

    void enableUser(String userIdentifier);

    void load();

    void createUsernameReminder(String recipientEmailAddress);

    void createPasswordReset(String username);

    User resetPassword(String passwordIdentifier, String newPassword);

    boolean isPasswordResetIdentifierValid(String passwordIdentifier);

    User getPasswordResetUser(String passwordIdentifier);

    void createNewUserSetupEmail(String userIdentifier);

    boolean isNewUserSetupIdentifierValid(String identifier);

    void completeNewUserSetup(String identifier, String username,
	    String password);
}
