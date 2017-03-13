package com.laotek.churchguru.web.client.activity.churchapp;

import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

import net.customware.gwt.dispatch.shared.Action;

public class UpdateChurchAppAction extends AbstractDispatchAction
	implements Action<UpdateChurchAppResult>, HasOrganisationCrudRole {

    private String value;
    private ChurchAppTopicEnum churchAppTopicEnum;
    private UpdateType updateType;

    public UpdateChurchAppAction() {
    }

    public UpdateChurchAppAction(ChurchAppTopicEnum churchAppTopicEnum, String value, UpdateType updateType) {
	this.value = value;
	this.churchAppTopicEnum = churchAppTopicEnum;
	this.updateType = updateType;
    }

    public String getValue() {
	return value;
    }

    public ChurchAppTopicEnum getChurchAppTopicEnum() {
	return churchAppTopicEnum;
    }

    public UpdateType getUpdateType() {
	return updateType;
    }

}
