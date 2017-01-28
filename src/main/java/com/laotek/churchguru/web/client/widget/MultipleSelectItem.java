package com.laotek.churchguru.web.client.widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;

@Deprecated
public class MultipleSelectItem extends FormItem {
    private List<ListBox> items = new ArrayList<ListBox>();
    private Anchor addItemAnchor = new Anchor("Add");
    private String[] options;
    private FlexTable itemGrid = new FlexTable();

    public MultipleSelectItem(String title, String options[]) {
	super(title);
	this.options = options;

	setBorderWidth(0);

	FlexTable inner = new FlexTable();
	inner.setBorderWidth(0);
	inner.setWidget(0, 0, itemGrid);
	inner.setWidget(1, 0, addItemAnchor);
	inner.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	inner.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	setWidget(1, 0, inner);
	getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	handleAddItemClickHandler();
    }

    private ListBox addNewItem() {
	final ListBox listBox = new ListBox();
	listBox.addItem("");
	for (String option : options) {
	    listBox.addItem(option);
	}
	items.add(listBox);
	return listBox;
    }

    private void handleAddItemClickHandler() {
	addItemAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		// getCellFormatter().setVerticalAlignment(0, 0,
		// HasVerticalAlignment.ALIGN_TOP);
		final int nextIndex = itemGrid.getRowCount();
		itemGrid.setWidget(nextIndex, 0, createItemPanel(nextIndex));

	    }
	});
    }

    private FlexTable createItemPanel(int rowIndex) {
	final FlexTable panel = new FlexTable();
	panel.setBorderWidth(0);
	final ListBox listbox = addNewItem();
	final Image cancel = new Image("images/app/button_cancel.png");
	panel.setWidget(0, 0, listbox);
	panel.setWidget(0, 1, cancel);
	cancel.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ListBox listbox = (ListBox) panel.getWidget(0, 0);
		items.remove(listbox);

		FlexTable parent = (FlexTable) panel.getParent();
		parent.remove(panel);
		for (int i = 0; i < parent.getRowCount(); ++i) {
		    if (parent.getWidget(i, 0) == null) {
			parent.removeRow(i);
			break;
		    }
		}
	    }
	});
	return panel;
    }

    public Set<String> getSelectedItems() {
	Set<String> set = new HashSet<String>();
	for (ListBox item : items) {
	    int selectedIndex = item.getSelectedIndex();
	    String selectedValue = item.getValue(selectedIndex);
	    if (selectedValue != null && !"".equals(selectedValue)
		    && !set.contains(selectedValue)) {
		set.add(selectedValue);
	    }
	}
	return set;
    }

    @Override
    boolean validate() {
	if (isRequired() && getSelectedItems().isEmpty()) {
	    addStyleName("textboxError");
	    return false;
	}
	return true;
    }

    public List<String> getValues() {
	List<String> values = new ArrayList<String>();
	for (ListBox item : items) {
	    int index = item.getSelectedIndex();
	    String value = item.getValue(index);
	    if (value != null && !"".equals(value)) {
		values.add(value);
	    }
	}
	return values;
    }

    @Override
    void disable() {
	for (ListBox item : items) {
	    item.setEnabled(false);
	}
    }

    @Override
    void enable() {
	for (ListBox item : items) {
	    item.setEnabled(true);
	}
    }

    protected void displayError(String error) {
	HTML html = new HTML(error);
	html.setStylePrimaryName("errorMessage");
	setWidget(2, 0, html);
    }

    protected void removeErrorMessage() {
	if (isCellPresent(2, 1)) {
	    clearCell(2, 1);
	}
    }
}
