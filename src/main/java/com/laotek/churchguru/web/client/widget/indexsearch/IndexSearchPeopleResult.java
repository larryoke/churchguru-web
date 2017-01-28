package com.laotek.churchguru.web.client.widget.indexsearch;

import java.util.HashMap;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class IndexSearchPeopleResult implements Result{

	private List<HashMap<String,String>> guests;
	private List<HashMap<String,String>> members;

	public IndexSearchPeopleResult() {
	}

	public IndexSearchPeopleResult(List<HashMap<String, String>> guests,
			List<HashMap<String, String>> members) {
		this.guests = guests;
		this.members = members;
	}

	public List<HashMap<String, String>> getGuests() {
		return guests;
	}

	public void setGuests(List<HashMap<String, String>> guests) {
		this.guests = guests;
	}

	public List<HashMap<String, String>> getMembers() {
		return members;
	}

	public void setMembers(List<HashMap<String, String>> members) {
		this.members = members;
	}
	
}
