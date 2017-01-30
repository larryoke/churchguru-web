package com.laotek.churchguru.web.shared.estore;

import java.io.Serializable;

import com.laotek.churchguru.model.shared.enums.EStoreNotificationType;

public class EStoreNotificationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public EStoreNotificationDto() {
    }

    public EStoreNotificationDto(EStoreNotificationType notificationType) {
	super();
	this.notificationType = notificationType;
    }

    private EStoreNotificationType notificationType;

    public EStoreNotificationType getNotificationType() {
	return notificationType;
    }

    public void setNotificationType(EStoreNotificationType notificationType) {
	this.notificationType = notificationType;
    }
}
