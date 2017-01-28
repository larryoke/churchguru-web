package com.laotek.churchguru.web.client.widget.indexsearch;

import java.util.HashMap;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class IndexSearchGuestResult implements Result{

	private List<HashMap<String,String>> persons;

	public IndexSearchGuestResult() {
	}

	public IndexSearchGuestResult(List<HashMap<String, String>> persons) {
		this.persons = persons;
	}

	public List<HashMap<String, String>> getPersons() {
		return persons;
	}	
}
