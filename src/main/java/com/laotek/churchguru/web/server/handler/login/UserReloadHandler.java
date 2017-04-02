package com.laotek.churchguru.web.server.handler.login;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.tasks.OnSuccessListener;
import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.web.client.activity.user.UserReloadAction;
import com.laotek.churchguru.web.client.activity.user.UserReloadResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.UserDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class UserReloadHandler extends AbstractCommandHandler
	implements ActionHandler<UserReloadAction, UserReloadResult> {

    @Autowired
    private UserDao userDao;

    @Autowired
    @Value("${firebase.database.url}")
    private String firebaseDatabaseUrl;

    @Autowired
    @Value("${firebase.api.key}")
    private String firebaseApiKey;

    @Autowired
    @Value("${firebase.auth.domain}")
    private String firebaseAuthDomain;

    @Autowired
    @Value("${firebase.messaging.sender.id}")
    private String firebaseMessagingSenderId;

    @Autowired
    @Value("${firebase.storage.bucket}")
    private String firebaseStorageBucket;

    @Override
    public UserReloadResult execute(UserReloadAction action, ExecutionContext context) throws DispatchException {
	String sessionId = action.getSessionId();

	User user = userDao.getUserByClientSessionId(sessionId);
	if (user == null) {
	    throw new ExpiredSessionException();
	}

	Organisation organisation = user.getOrganisation();
	OrganisationDto orgDto = map(organisation);

	List<User> users = new ArrayList<User>();
	users.add(user);
	List<UserDto> userDtos = convert(users);

	UserDto userDto = userDtos.get(0);
	userDto.setFirebaseApiKey(firebaseApiKey);
	userDto.setFirebaseDatabaseURL(firebaseDatabaseUrl);
	userDto.setFirebaseAuthDomain(firebaseAuthDomain);
	userDto.setFirebaseMessagingSenderId(firebaseMessagingSenderId);
	userDto.setFirebaseStorageBucket(firebaseStorageBucket);

	UserReloadResult result = new UserReloadResult(orgDto, userDto);

	UUID uuid = UUID.randomUUID();
	String uid = uuid.toString();

	CountDownLatch startSignal = new CountDownLatch(1);

	FirebaseAuth.getInstance().createCustomToken(uid).addOnSuccessListener(new OnSuccessListener<String>() {
	    @Override
	    public void onSuccess(String customToken) {
		System.out.println("Custom Token: " + customToken);
		userDto.setFirebaseCustomToken(customToken);
		startSignal.countDown();
	    }
	});

	try {
	    startSignal.await(10, TimeUnit.MINUTES);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	return result;
    }

    @Override
    public Class<UserReloadAction> getActionType() {
	return UserReloadAction.class;
    }

    @Override
    public void rollback(UserReloadAction action, UserReloadResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
