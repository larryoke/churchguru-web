package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.laotek.churchguru.web.client.EmailUserRowSelectionContext;

public class UserListTable extends FlexTable {

    public UserListTable() {
    }

    public void selectAllPageCheckBoxes(boolean selected) {
	for (int i = 0; i < this.getRowCount(); ++i) {
	    if (i == 0)
		continue;
	    FlexTable firstCol = (FlexTable) getWidget(i, 0);
	    IdCheckBox checkBox = (IdCheckBox) firstCol.getWidget(0, 0);
	    checkBox.setValue(selected);
	    String id = checkBox.getIdentifier();
	    EmailUserRowSelectionContext.getInstance().selectId(id, selected);

	    if (selected) {
		styleRowSelection(i);
	    } else {
		removeStyleRowSelection(i);
	    }
	}
    }

    public void validateMainCheckBox(CheckBox mainCheckBox) {
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

	mainCheckBox.setValue(!foundUnchecked);

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
