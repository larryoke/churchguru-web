package com.laotek.churchguru.web.client.widget;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private List<FormItem> formItems = new ArrayList<FormItem>();

    public void addFormItem(FormItem formItem) {
	formItems.add(formItem);
    }

    public boolean validateForm() {
	boolean validate = true;
	// remove error messages if any before validating
	for (FormItem formItem : formItems) {
	    formItem.removeErrorMessage();
	}

	// now validate
	for (FormItem formItem : formItems) {
	    if (!formItem.validate()) {
		validate = false;
	    }
	}
	return validate;
    }

    public void disableFormItems() {
	for (FormItem formItem : formItems) {
	    formItem.disable();
	}
    }

    public void enableFormItems() {
	for (FormItem formItem : formItems) {
	    formItem.enable();
	}
    }
}
