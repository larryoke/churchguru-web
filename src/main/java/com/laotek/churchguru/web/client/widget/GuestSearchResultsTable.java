package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.laotek.churchguru.web.client.GuestRowSelectionContext;

public class GuestSearchResultsTable extends FlexTable {

    public GuestSearchResultsTable() {
    }

    public void selectAllPageCheckBoxes(boolean selected) {
	for (int i = 0; i < this.getRowCount(); ++i) {
	    if (i == 0)
		continue;
	    FlexTable firstCol = (FlexTable) getWidget(i, 0);
	    IdCheckBox checkBox = (IdCheckBox) firstCol.getWidget(0, 0);
	    checkBox.setValue(selected);
	    String identifier = checkBox.getIdentifier();
	    GuestRowSelectionContext.getInstance().selectIdentifier(identifier,
		    selected);
	    if (selected) {
		styleRowSelection(i);
	    } else {
		removeStyleRowSelection(i);
	    }
	}
    }

    public void validateMainCheckBox() {
	boolean foundUnchecked = false;
	for (int i = 0; i < this.getRowCount(); ++i) {
	    if (i == 0)
		continue;
	    FlexTable firstCol = (FlexTable) getWidget(i, 0);
	    IdCheckBox checkBox = (IdCheckBox) firstCol.getWidget(0, 0);
	    if (!checkBox.getValue()) {
		foundUnchecked = true;
		break;
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
