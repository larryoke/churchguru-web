package com.laotek.churchguru.web.clientm.activity.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MobileProperties implements Serializable, IsSerializable {
    private static final long serialVersionUID = 1L;
    private String churchName;
    private List<Topic> topics = new ArrayList<Topic>();

    public String getChurchName() {
	return churchName;
    }

    public void setChurchName(String churchName) {
	this.churchName = churchName;
    }

    public List<Topic> getTopics() {
	return topics;
    }

    public void setTopics(List<Topic> topics) {
	this.topics = topics;
    }
}
