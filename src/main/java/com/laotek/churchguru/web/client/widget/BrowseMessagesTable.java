package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MemberRowSelectionContext;

public class BrowseMessagesTable extends FlexTable {

    public BrowseMessagesTable() {
    }

    public void selectAllPageCheckBoxes(boolean selected) {
	for (int i = 0; i < this.getRowCount(); ++i) {

	    Widget widget = getWidget(i, 0);
	    if (widget != null && widget instanceof FlexTable) {
		FlexTable ft = (FlexTable) widget;
		widget = ft.getWidget(0, 0);
		if (widget != null && widget instanceof IdCheckBox) {
		    IdCheckBox checkBox = (IdCheckBox) widget;
		    checkBox.setValue(selected);
		    String identifier = checkBox.getIdentifier();
		    MemberRowSelectionContext.getInstance().selectId(
			    identifier, selected);
		    if (selected) {
			styleRowSelection(i);
		    } else {
			removeStyleRowSelection(i);
		    }
		}
	    }
	}
    }

    public void validateMainCheckBox() {
	boolean foundUnchecked = false;
	for (int i = 0; i < this.getRowCount(); ++i) {
	    Widget widget = getWidget(i, 0);
	    if (widget != null && widget instanceof FlexTable) {
		FlexTable ft = (FlexTable) widget;
		widget = ft.getWidget(0, 0);
		if (widget != null && widget instanceof IdCheckBox) {
		    IdCheckBox checkBox = (IdCheckBox) widget;

		    if (!checkBox.getValue()) {
			foundUnchecked = true;
			break;
		    }
		}
	    }
	}
	CheckBox checkBox = (CheckBox) getWidget(0, 0);
	checkBox.setValue(!foundUnchecked);
    }

    public void styleRowSelection(int currentRowIndex) {
	RowFormatter formatter = getRowFormatter();
	formatter.addStyleName(currentRowIndex, "selectedRow");
    }

    public void removeStyleRowSelection(int currentRowIndex) {
	RowFormatter formatter = getRowFormatter();
	formatter.removeStyleName(currentRowIndex, "selectedRow");
    }
}
