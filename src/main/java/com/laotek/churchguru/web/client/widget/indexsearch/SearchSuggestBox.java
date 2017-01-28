package com.laotek.churchguru.web.client.widget.indexsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.widget.TextItem;

public class SearchSuggestBox extends Composite {

    public static final String TEXT_BOX_HINT = "enter name or post code";

    private FlexTable borderBox = new FlexTable();
    private TextItem keywordBox = new TextItem();
    private DropDownMenu dropDownMenu = new DropDownMenu(keywordBox);
    private NotFoundPanel notFoundPanel = new NotFoundPanel();
    private Timer timer = null;
    private IndexSearchType searchType = null;

    public SearchSuggestBox(IndexSearchType searchType) {
	this.searchType = searchType;
	Image image = new Image("images/app/toolbar_find.png");
	borderBox.setWidget(0, 0, keywordBox);
	borderBox.setCellPadding(0);
	borderBox.setCellSpacing(0);
	borderBox.setWidget(0, 2, image);
	borderBox.setSize("250px", "35px");
	borderBox.setStylePrimaryName("focusedIndexSearchBorderBox");
	borderBox.addStyleName("whitebg");

	final TextBox textbox = keywordBox.getTextbox();
	textbox.setMaxLength(20);
	textbox.setSize("220px", "30px");

	keywordBox.setStylePrimaryName("blurredIndexSearchTextbox");

	keywordBox.setValue(TEXT_BOX_HINT);
	initWidget(borderBox);

	sinkEvents(Event.ONKEYPRESS);

	textbox.addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		if (textbox.getValue() == null
			|| (textbox.getValue() != null && textbox.getValue()
				.equals(""))) {
		    textbox.setValue(TEXT_BOX_HINT);
		    textbox.setStylePrimaryName("blurredIndexSearchTextbox");
		    borderBox
			    .setStylePrimaryName("blurredIndexSearchBorderBox");
		}
	    }
	});

	textbox.addFocusHandler(new FocusHandler() {
	    @Override
	    public void onFocus(FocusEvent event) {
		if (textbox.getValue() != null
			&& textbox.getValue().equals(TEXT_BOX_HINT)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("focusedIndexSearchTextbox");
		    borderBox
			    .setStylePrimaryName("focusedIndexSearchBorderBox");
		}
	    }
	});

	textbox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (notFoundPanel.isShowing())
		    notFoundPanel.hide();
		if (textbox.getValue() != null
			&& textbox.getValue().equals(TEXT_BOX_HINT)) {
		    textbox.setValue("");
		    textbox.setStylePrimaryName("focusedIndexSearchTextbox");
		}
		refresh(textbox);
	    }
	});

	textbox.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (notFoundPanel.isShowing())
		    notFoundPanel.hide();
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    dropDownMenu.handle(0);

		} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
		    dropDownMenu.handle(0);

		} else if (event.getNativeEvent().getKeyCode() != KeyCodes.KEY_ESCAPE) {
		    refresh(textbox);
		}
	    }
	});

	textbox.addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (notFoundPanel.isShowing())
		    notFoundPanel.hide();
		if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
		    dropDownMenu.moveDown(0);
		    event.preventDefault();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
		    refresh(textbox);

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
		    dropDownMenu.hide();
		}

	    }
	});

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		if (textbox.getValue() != null
			&& textbox.getValue().equals(TEXT_BOX_HINT)) {
		    textbox.setValue(TEXT_BOX_HINT);
		    textbox.setStylePrimaryName("blurredIndexSearchTextbox");
		    borderBox
			    .setStylePrimaryName("blurredIndexSearchBorderBox");
		}
	    }
	});

    }

    public void setDropDownSelectionHandler(
	    DropDownSelectionHandler dropDownSelectionHandler) {
	dropDownMenu.setHandler(dropDownSelectionHandler);
    }

    private void refresh(final TextBox textbox) {
	if (timer != null) {
	    timer.cancel();
	}
	timer = new Timer() {
	    public void run() {
		final String keyword = textbox.getValue();
		if (keyword != null && keyword.length() > 0) {

		    final int left = borderBox.getElement().getAbsoluteLeft();
		    final int bottom = borderBox.getElement()
			    .getAbsoluteBottom();

		    final PleaseWaitPanel pleaseWaitPanel = new PleaseWaitPanel();
		    pleaseWaitPanel.setWidth("240px");
		    pleaseWaitPanel.setPopupPosition(left, bottom);
		    pleaseWaitPanel.show();

		    if (IndexSearchType.MEMBER.equals(searchType)) {
			IndexSearchMemberAction action = new IndexSearchMemberAction(
				keyword);
			UserContext.getInstance().decorateClientSessionId(
				action);
			UserContext
				.getInstance()
				.getDispatchClient()
				.execute(
					action,
					new AsyncCallback<IndexSearchMemberResult>() {
					    @Override
					    public void onFailure(
						    Throwable caught) {
						pleaseWaitPanel.hide();
						notFoundPanel.setWidth("240px");
						notFoundPanel.setPopupPosition(
							left, bottom);
						notFoundPanel.show();
					    }

					    @Override
					    public void onSuccess(
						    IndexSearchMemberResult result) {
						pleaseWaitPanel.hide();
						dropDownMenu.setPopupPosition(
							left, bottom);
						List<HashMap<String, String>> persons = result
							.getPersons();
						dropDownMenu.init(keyword,
							persons);
						dropDownMenu.show();
					    }
					});
		    } else if (IndexSearchType.GUEST.equals(searchType)) {
			IndexSearchGuestAction action = new IndexSearchGuestAction(
				keyword);
			UserContext.getInstance().decorateClientSessionId(
				action);
			UserContext
				.getInstance()
				.getDispatchClient()
				.execute(
					action,
					new AsyncCallback<IndexSearchGuestResult>() {
					    @Override
					    public void onFailure(
						    Throwable caught) {
						pleaseWaitPanel.hide();
						notFoundPanel.setWidth("240px");
						notFoundPanel.setPopupPosition(
							left, bottom);
						notFoundPanel.show();
					    }

					    @Override
					    public void onSuccess(
						    IndexSearchGuestResult result) {
						pleaseWaitPanel.hide();
						dropDownMenu.setPopupPosition(
							left, bottom);
						List<HashMap<String, String>> persons = result
							.getPersons();
						dropDownMenu.init(keyword,
							persons);
						dropDownMenu.show();
					    }
					});

		    } else if (IndexSearchType.MEMBER_AND_GUEST
			    .equals(searchType)) {
			IndexSearchPeopleAction action = new IndexSearchPeopleAction(
				keyword);
			UserContext.getInstance().decorateClientSessionId(
				action);
			UserContext
				.getInstance()
				.getDispatchClient()
				.execute(
					action,
					new AsyncCallback<IndexSearchPeopleResult>() {
					    @Override
					    public void onFailure(
						    Throwable caught) {
						pleaseWaitPanel.hide();
						notFoundPanel.setWidth("240px");
						notFoundPanel.setPopupPosition(
							left, bottom);
						notFoundPanel.show();
					    }

					    @Override
					    public void onSuccess(
						    IndexSearchPeopleResult result) {
						pleaseWaitPanel.hide();
						dropDownMenu.setPopupPosition(
							left, bottom);
						List<HashMap<String, String>> guests = result
							.getGuests();
						List<HashMap<String, String>> members = result
							.getMembers();
						List<HashMap<String, String>> everybody = new ArrayList<HashMap<String, String>>();
						everybody.addAll(members);
						everybody.addAll(guests);
						dropDownMenu.init(keyword,
							everybody);
						dropDownMenu.show();
					    }
					});
		    }
		}
	    }
	};
	timer.schedule(1000);

	dropDownMenu.hide();
    }

    public TextBox getTextbox() {
	return keywordBox.getTextbox();
    }

    public FlexTable getBorderBox() {
	return borderBox;
    }

    public DropDownMenu getDropDownMenu() {
	return dropDownMenu;
    }

    public void clear() {

	keywordBox.setStylePrimaryName("blurredIndexSearchTextbox");

	keywordBox.setValue(TEXT_BOX_HINT);
    }

}
