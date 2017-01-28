package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum SmsMessageRecipientType implements Serializable, IsSerializable {
    MEMBER, GUEST, MEMBER_INVITEE, MEMBER_WORKING_COPY, DEPARTMENT, DEPARTMENT_LEADERS, USER;
}
