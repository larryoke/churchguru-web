package com.laotek.churchguru.web.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.Title;

public class FullnameDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private Title title;
    private String forenames;
    private String surname;

    public Title getTitle() {
	return title;
    }

    public void setTitle(Title title) {
	this.title = title;
    }

    public String getForenames() {
	return forenames;
    }

    public void setForenames(String forenames) {
	this.forenames = forenames;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getFullname() {
	return title.getDesc() + " " + forenames + " " + surname;
    }

}
