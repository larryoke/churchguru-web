package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.user.HasUserViewOnlyRole;

public class GetSingleUserDetailsAction extends AbstractDispatchAction
	implements Action<GetSingleUserDetailsResult>, HasUserViewOnlyRole {

    private static final long serialVersionUID = 1L;

    private String userIdentifier;

    public GetSingleUserDetailsAction() {
    }

    public GetSingleUserDetailsAction(String userIdentifier) {
	this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifier() {
	return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
	this.userIdentifier = userIdentifier;
    }

}