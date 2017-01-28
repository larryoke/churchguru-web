package com.laotek.churchguru.web.client.activity.churchapp;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class UpdateChurchAppLabelFlagAction extends AbstractDispatchAction
	implements Action<UpdateChurchAppLabelFlagResult>,
	HasOrganisationCrudRole {

    private boolean value;
    private ChurchAppTopicEnum churchAppTopicEnum;

    public UpdateChurchAppLabelFlagAction() {
    }

    public UpdateChurchAppLabelFlagAction(boolean value,
	    ChurchAppTopicEnum churchAppTopicEnum) {
	this.value = value;
	this.churchAppTopicEnum = churchAppTopicEnum;
    }

    public boolean isValue() {
	return value;
    }

    public ChurchAppTopicEnum getChurchAppTopicEnum() {
	return churchAppTopicEnum;
    }

}
