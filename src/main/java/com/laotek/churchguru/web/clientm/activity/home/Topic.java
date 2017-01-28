package com.laotek.churchguru.web.clientm.activity.home;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;

public class Topic implements Serializable, IsSerializable {

    /**
     * 
     */
    private static final long serialVersionUID = -134211444131752658L;

    private String name;

    private int count;

    private ChurchAppTopicEnum churchAppTopicEnum;

    public Topic() {

    }

    public Topic(String name, int count, ChurchAppTopicEnum churchAppTopicEnum) {
	this.name = name;
	this.count = count;
	this.setChurchAppTopicEnum(churchAppTopicEnum);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }

    public ChurchAppTopicEnum getChurchAppTopicEnum() {
	return churchAppTopicEnum;
    }

    public void setChurchAppTopicEnum(ChurchAppTopicEnum churchAppTopicEnum) {
	this.churchAppTopicEnum = churchAppTopicEnum;
    }

}