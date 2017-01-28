package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;

public class GetUserProfileOptionsAction extends AbstractDispatchAction
	implements Action<GetUserProfileOptionsResult>, HasUserCrudRole {

}
