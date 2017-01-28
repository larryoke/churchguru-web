package com.laotek.churchguru.web.client.widget.indexsearch;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.member.HasMemberAndGuestViewOnlyRole;

public class IndexSearchMemberAction extends AbstractDispatchAction implements
	Action<IndexSearchMemberResult>, HasMemberAndGuestViewOnlyRole {
    private String keywords;

    public IndexSearchMemberAction() {
    }

    public IndexSearchMemberAction(String keywords) {
	this.keywords = keywords;
    }

    public String getKeywords() {
	return keywords;
    }
}
