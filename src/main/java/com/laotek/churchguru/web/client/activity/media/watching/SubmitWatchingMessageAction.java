package com.laotek.churchguru.web.client.activity.media.watching;

import java.util.Date;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.FullnameDto;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class SubmitWatchingMessageAction extends AbstractDispatchAction
	implements Action<SubmitWatchingMessageResult>, HasOrganisationViewRole {

    private String identifier;
    private FullnameDto newSpeaker = new FullnameDto();
    private String speakerDesc;
    private String briefDescription;
    private String existingSpeaker;

    private String existingCategory;
    private String newCategory;

    private String messageDesc;
    private String location;

    private int salesChargePerMessage;

    private Date messageDate;

    public SubmitWatchingMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public FullnameDto getNewSpeaker() {
	return newSpeaker;
    }

    public void setNewSpeaker(FullnameDto newSpeaker) {
	this.newSpeaker = newSpeaker;
    }

    public String getBriefDescription() {
	return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
	this.briefDescription = briefDescription;
    }

    public String getExistingSpeaker() {
	return existingSpeaker;
    }

    public void setExistingSpeaker(String existingSpeaker) {
	this.existingSpeaker = existingSpeaker;
    }

    public String getExistingCategory() {
	return existingCategory;
    }

    public void setExistingCategory(String existingCategory) {
	this.existingCategory = existingCategory;
    }

    public String getNewCategory() {
	return newCategory;
    }

    public void setNewCategory(String newCategory) {
	this.newCategory = newCategory;
    }

    public String getMessageDesc() {
	return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
	this.messageDesc = messageDesc;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public int getSalesChargePerMessage() {
	return salesChargePerMessage;
    }

    public void setSalesChargePerMessage(int salesChargePerMessage) {
	this.salesChargePerMessage = salesChargePerMessage;
    }

    public String getSpeakerDesc() {
	return speakerDesc;
    }

    public void setSpeakerDesc(String speakerDesc) {
	this.speakerDesc = speakerDesc;
    }

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

}
