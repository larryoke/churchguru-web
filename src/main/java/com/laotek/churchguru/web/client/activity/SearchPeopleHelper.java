package com.laotek.churchguru.web.client.activity;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.laotek.churchguru.web.client.widget.indexsearch.DropDownSelectionHandler;
import com.laotek.churchguru.web.client.widget.indexsearch.IndexSearchType;
import com.laotek.churchguru.web.client.widget.indexsearch.SearchSuggestBox;

public class SearchPeopleHelper {
    private static final String TEXT_BOX_HINT = "enter name, address, post code etc";

    public static VerticalPanel initKeywordPanel(
	    final DropDownSelectionHandler dropDownSelectionHandler) {

	final SearchSuggestBox memberSearchSuggestBox = new SearchSuggestBox(
		IndexSearchType.MEMBER_AND_GUEST);
	memberSearchSuggestBox
		.setDropDownSelectionHandler(dropDownSelectionHandler);

	VerticalPanel keywordPanel = new VerticalPanel();
	keywordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	keywordPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		TextBox textbox = memberSearchSuggestBox.getTextbox();
		if (textbox.getValue() != null
			&& textbox.getValue().equals(TEXT_BOX_HINT)) {
		    textbox.setStylePrimaryName("blurredIndexSearchTextbox");
		    memberSearchSuggestBox.getBorderBox().setStylePrimaryName(
			    "blurredIndexSearchBorderBox");
		}
	    }
	});

	keywordPanel.add(memberSearchSuggestBox);
	return keywordPanel;
    }
}
