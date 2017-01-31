package com.laotek.churchguru.web.shared.listening;

import java.io.Serializable;

import com.laotek.churchguru.model.shared.enums.ListeningNotificationType;

public class AudioMessageNotificationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public AudioMessageNotificationDto() {
    }

    public AudioMessageNotificationDto(ListeningNotificationType notificationType) {
	super();
	this.notificationType = notificationType;
    }

    private ListeningNotificationType notificationType;

    public ListeningNotificationType getNotificationType() {
	return notificationType;
    }

    public void setNotificationType(ListeningNotificationType notificationType) {
	this.notificationType = notificationType;
    }
}
