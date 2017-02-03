package com.laotek.churchguru.web.client.activity.media.youtube;

import java.util.Date;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class SubmitYoutubeVideoAction extends AbstractDispatchAction
	implements Action<SubmitYoutubeVideoResult>, HasOrganisationViewRole {

    private String identifier;
    private String youtubeUrl;

    private String speaker;

    private String messageDesc;
    private String location;

    private Date messageDate;

    public SubmitYoutubeVideoAction() {
    }

    public SubmitYoutubeVideoAction(String identifier, String youtubeUrl, String speaker, String location, Date date) {
	this.identifier = identifier;
	this.setYoutubeUrl(youtubeUrl);
	this.speaker = speaker;
	this.location = location;
	this.messageDate = date;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getMessageDesc() {
	return messageDesc;
    }

    public String getSpeaker() {
	return speaker;
    }

    public void setSpeaker(String speaker) {
	this.speaker = speaker;
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

    public Date getMessageDate() {
	return messageDate;
    }

    public void setMessageDate(Date messageDate) {
	this.messageDate = messageDate;
    }

    public String getYoutubeUrl() {
	return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
	this.youtubeUrl = youtubeUrl;
    }

}
