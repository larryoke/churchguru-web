package com.laotek.churchguru.web.client.widget.indexsearch;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.member.HasMemberAndGuestViewOnlyRole;

public class IndexSearchGuestAction extends AbstractDispatchAction implements
	Action<IndexSearchGuestResult>, HasMemberAndGuestViewOnlyRole {
    private String keywords;

    public IndexSearchGuestAction() {
    }

    public IndexSearchGuestAction(String keywords) {
	this.keywords = keywords;
    }

    public String getKeywords() {
	return keywords;
    }
}
