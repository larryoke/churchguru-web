package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.user.HasUserViewOnlyRole;

public class GetUserDetailsAction extends AbstractDispatchAction implements
	Action<GetUserDetailsResult>, HasUserViewOnlyRole {
}
