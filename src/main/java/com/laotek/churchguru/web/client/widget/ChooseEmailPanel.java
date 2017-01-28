package com.laotek.churchguru.web.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChooseEmailPanel extends Composite {
    private VerticalPanel layout = new VerticalPanel();
    private ScrollPanel scrollPanel = new ScrollPanel();
    private VerticalPanel rows = new VerticalPanel();
    private Map<String, Boolean> ids = new HashMap<String, Boolean>();
    private Map<String, CheckBoxItem> cbs = new HashMap<String, CheckBoxItem>();

    public ChooseEmailPanel() {
	scrollPanel.setHeight("200px");
	scrollPanel.setWidth("350px");
	scrollPanel.setWidget(rows);
	layout.add(scrollPanel);
	initWidget(layout);
    }

    public ChooseEmailPanel(Map<String, Boolean> ids) {
	this();
	this.ids = ids;
	for (Map.Entry<String, Boolean> id : ids.entrySet()) {
	    CheckBoxItem cb = new CheckBoxItem(id.getKey(), false);
	    cb.getCheckbox().setValue(id.getValue());
	    rows.add(cb);
	    cbs.put(id.getKey(), cb);
	}
    }

    public void setIds(Map<String, Boolean> ids) {
	this.ids = ids;
	for (Map.Entry<String, Boolean> id : ids.entrySet()) {
	    CheckBoxItem cb = new CheckBoxItem(id.getKey(), false);
	    cb.getCheckbox().setValue(id.getValue());
	    rows.add(cb);
	    cbs.put(id.getKey(), cb);
	}
    }

    public List<String> getSelectedWorkers() {
	List<String> selects = new ArrayList<String>();
	for (Map.Entry<String, CheckBoxItem> cb : cbs.entrySet()) {
	    if (cb.getValue().getCheckbox().getValue()) {
		selects.add(cb.getKey());
	    }
	}
	return selects;
    }

    public void clear() {
	this.ids.clear();
	rows.clear();
    }
}
