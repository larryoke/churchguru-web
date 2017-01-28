package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum MessagePhotoAlignment implements Serializable, IsSerializable {

    LEFT("style=\"float:left\""), CENTRE("style=\"float:center\""), BIG_CENTRE("style=\"float:center\""), RIGHT(
	    "style=\"float:right\"");

    private String style;

    private MessagePhotoAlignment(String style) {
	this.style = style;
    }

    public String getStyle() {
	return style;
    }
}
