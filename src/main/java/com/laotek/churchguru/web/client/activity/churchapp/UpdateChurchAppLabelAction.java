package com.laotek.churchguru.web.client.activity.churchapp;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class UpdateChurchAppLabelAction extends AbstractDispatchAction
	implements Action<UpdateChurchAppLabelResult>, HasOrganisationCrudRole {

    private String value;
    private ChurchAppTopicEnum churchAppTopicEnum;

    public UpdateChurchAppLabelAction() {
    }

    public UpdateChurchAppLabelAction(String value,
	    ChurchAppTopicEnum churchAppTopicEnum) {
	this.value = value;
	this.churchAppTopicEnum = churchAppTopicEnum;
    }

    public String getValue() {
	return value;
    }

    public ChurchAppTopicEnum getChurchAppTopicEnum() {
	return churchAppTopicEnum;
    }

}
