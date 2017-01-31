package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class NoticeAndEventAction extends AbstractDispatchAction implements
	Action<NoticeAndEventActionResult>, HasOrganisationCrudRole {

    private int id;
    private String identifier;
    private String subject;
    private boolean notifyAppUsers;
    private EnumNoticeOrEventAction action;

    public NoticeAndEventAction() {
    }

    public NoticeAndEventAction(int id, String identifier,
	    EnumNoticeOrEventAction action) {
	this.id = id;
	this.identifier = identifier;
	this.action = action;
    }

    public NoticeAndEventAction(String subject, EnumNoticeOrEventAction action) {
	this.subject = subject;
	this.action = action;
    }

    public NoticeAndEventAction(int id, String identifier, String subject,
	    boolean notifyAppUsers, EnumNoticeOrEventAction action) {
	this.id = id;
	this.identifier = identifier;
	this.notifyAppUsers = notifyAppUsers;
	this.action = action;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public int getId() {
	return id;
    }

    public String getIdentifier() {
	return identifier;
    }

    public EnumNoticeOrEventAction getAction() {
	return action;
    }

    public void setAction(EnumNoticeOrEventAction action) {
	this.action = action;
    }

    public boolean isNotifyAppUsers() {
	return notifyAppUsers;
    }

    public void setNotifyAppUsers(boolean notifyAppUsers) {
	this.notifyAppUsers = notifyAppUsers;
    }
}
