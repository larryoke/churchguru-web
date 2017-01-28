package com.laotek.churchguru.web.client;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.shared.UserDto;

public class UserContext {

    private static UserContext userContext = new UserContext();

    private DispatchClient dispatchClient;

    private String clientSessionId;

    private String emailAddress;

    private UserDto userDto = new UserDto();

    private UserContext() {

    }

    public static UserContext getInstance() {
	return userContext;
    }

    public String getClientSessionId() {
	return clientSessionId;
    }

    public void setClientSessionId(String clientSessionId) {
	this.clientSessionId = clientSessionId;
    }

    public void initDispatchClient(DispatchClient dispatchClient) {
	this.dispatchClient = dispatchClient;
    }

    public DispatchClient getDispatchClient() {
	return dispatchClient;
    }

    public void decorateClientSessionId(AbstractDispatchAction action) {
	action.setClientSessionId(clientSessionId);
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public UserDto getUserDto() {
	return userDto;
    }

    public void setUserDto(UserDto userDto) {
	this.userDto = userDto;
    }

}
