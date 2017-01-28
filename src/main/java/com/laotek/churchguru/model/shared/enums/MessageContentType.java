package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Free to remove later, may cause compile errors
 * 
 * @author larryoke
 *
 */
@Deprecated
public enum MessageContentType implements Serializable, IsSerializable {
    CONF_CALL("Conference Call"), SUNDAY_SCHOOL("Sunday School"), OTHER_NEWS("Other News");

    private String desc;

    private MessageContentType(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }
}
