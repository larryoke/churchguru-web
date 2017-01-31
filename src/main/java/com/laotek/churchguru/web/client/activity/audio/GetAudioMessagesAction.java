package com.laotek.churchguru.web.client.activity.audio;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetAudioMessagesAction extends AbstractDispatchAction implements
	Action<GetAudioMessagesResult>, HasOrganisationViewRole {

    public GetAudioMessagesAction() {
    }
}
