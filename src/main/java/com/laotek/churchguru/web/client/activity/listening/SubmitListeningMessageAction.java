package com.laotek.churchguru.web.client.activity.listening;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laotek.churchguru.model.shared.enums.ListeningNotificationType;
import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.FullnameDto;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class SubmitListeningMessageAction extends AbstractDispatchAction implements
	Action<SubmitListeningMessageResult>, HasOrganisationViewRole {

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

    private List<ListeningNotificationType> notifications = new ArrayList<ListeningNotificationType>();

    private List<String> workersWithFreeAccessToMessage;

    public SubmitListeningMessageAction() {
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

    public List<ListeningNotificationType> getNotifications() {
	return notifications;
    }

    public void setNotifications(List<ListeningNotificationType> notifications) {
	this.notifications = notifications;
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

    public List<String> getWorkersWithFreeAccessToMessage() {
	return workersWithFreeAccessToMessage;
    }

    public void setWorkersWithFreeAccessToMessage(
	    List<String> workersWithFreeAccessToMessage) {
	this.workersWithFreeAccessToMessage = workersWithFreeAccessToMessage;
    }

}
