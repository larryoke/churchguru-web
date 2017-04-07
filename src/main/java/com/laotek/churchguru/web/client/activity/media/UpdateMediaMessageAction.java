package com.laotek.churchguru.web.client.activity.media;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class UpdateMediaMessageAction extends AbstractDispatchAction
	implements Action<UpdateMediaMessageResult>, HasOrganisationViewRole {
    public UpdateMediaMessageAction() {
    }

    public UpdateMediaMessageAction(String uploadType, String identity, String downloadUrl) {
	super();
	this.uploadType = uploadType;
	this.identity = identity;
	this.setDownloadUrl(downloadUrl);
    }

    private String uploadType;
    private String identity;
    private String downloadUrl;

    public String getUploadType() {
	return uploadType;
    }

    public void setUploadType(String uploadType) {
	this.uploadType = uploadType;
    }

    public String getIdentity() {
	return identity;
    }

    public void setIdentity(String identity) {
	this.identity = identity;
    }

    public String getDownloadUrl() {
	return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
	this.downloadUrl = downloadUrl;
    }
}
