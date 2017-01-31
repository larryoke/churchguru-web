package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class GetCurrentNoticesAndEventsHistoryAction extends
	AbstractDispatchAction implements
	Action<GetCurrentNoticesAndEventsHistoryResult>,
	HasOrganisationCrudRole {

    private BrowseMessagesType browseMessagesType;
    private int currentIndex;
    private int count;

    public GetCurrentNoticesAndEventsHistoryAction() {
    }

    public GetCurrentNoticesAndEventsHistoryAction(
	    BrowseMessagesType browseMessagesType, int currentIndex, int count) {
	this.browseMessagesType = browseMessagesType;
	this.currentIndex = currentIndex;
	this.count = count;
    }

    public int getCurrentIndex() {
	return currentIndex;
    }

    public int getCount() {
	return count;
    }

    public BrowseMessagesType getBrowseMessagesType() {
	return browseMessagesType;
    }

    public void setBrowseMessagesType(BrowseMessagesType browseMessagesType) {
	this.browseMessagesType = browseMessagesType;
    }
}
