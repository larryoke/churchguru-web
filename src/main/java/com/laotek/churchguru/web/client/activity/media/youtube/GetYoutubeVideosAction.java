package com.laotek.churchguru.web.client.activity.media.youtube;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class GetYoutubeVideosAction extends AbstractDispatchAction
	implements Action<GetYoutubeVideosResult>, HasOrganisationViewRole {

    public GetYoutubeVideosAction() {
    }
}
