package com.laotek.churchguru.web.client.widget.indexsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.widget.TextItem;

public class DropDownMenu extends PopupPanel {
    private FlexTable layout = new FlexTable();
    private Map<Integer, List<HashMap<String, String>>> pages;
    private int currentPageNo = 1;
    private DropDownSelectionHandler handler;
    private TextItem originalTextItem;
    private String currentKeyword;

    public DropDownMenu(TextItem originalTextItem) {
	super(true);
	this.originalTextItem = originalTextItem;
	setWidth("240px");
	layout.setWidget(0, 0, new HTML("&nbsp;"));
	layout.getFlexCellFormatter().setColSpan(0, 0, 2);
	layout.setWidget(1, 0, new HTML("&nbsp;"));
	setWidget(layout);
    }

    public void setHandler(DropDownSelectionHandler handler) {
	this.handler = handler;
    }

    public void moveDown(int row) {
	Widget widget = layout.getWidget(row, 0);
	if (widget instanceof DropDownItem) {
	    DropDownItem item = (DropDownItem) widget;
	    item.select();

	} else if (widget instanceof DropDownTopOrBottomItem) {
	    DropDownTopOrBottomItem item = (DropDownTopOrBottomItem) widget;
	    item.select();
	}
    }

    public void init(String currentKeyword,
	    List<HashMap<String, String>> persons) {

	this.currentKeyword = currentKeyword;

	// Get all pages
	pages = paginate(persons);

	// Get first page
	currentPageNo = 1;
	persons = pages.get(currentPageNo);

	initLayout(currentKeyword, persons);

	highlightFirst();
    }

    private void initLayout(String keyword,
	    List<HashMap<String, String>> persons) {
	layout.removeAllRows();
	int row = 0;

	DropDownTopOrBottomItem top = null;
	if (pages.size() > 0 && currentPageNo > 1) {
	    top = new DropDownTopOrBottomItem("Previous");
	    initTop(row, layout, top);
	    row = row + 1;
	}

	for (HashMap<String, String> person : persons) {
	    init(keyword, row, layout, person, originalTextItem);
	    row = row + 1;
	}

	DropDownTopOrBottomItem bottom = null;
	if (pages.size() > 0 && currentPageNo < pages.size()) {
	    bottom = new DropDownTopOrBottomItem("Next");
	    initBottom(row, layout, bottom);
	}
    }

    /**
     * Just highlight, no focus
     */
    private void highlightFirst() {
	if (layout.getRowCount() > 0) {
	    DropDownItem item = (DropDownItem) layout.getWidget(0, 0);
	    item.highlight();
	}
    }

    /**
     * Just highlight and focus
     */
    private void focusFirst() {
	removeAllFocus();
	if (layout.getRowCount() > 0) {
	    Widget widget = layout.getWidget(0, 0);
	    if (widget instanceof DropDownItem) {
		DropDownItem item = (DropDownItem) widget;
		item.select();

	    } else if (widget instanceof DropDownTopOrBottomItem) {
		DropDownTopOrBottomItem item = (DropDownTopOrBottomItem) widget;
		item.select();
	    }
	}
    }

    /**
     * Just highlight and focus
     */
    private void focusLast() {
	int lastRow = layout.getRowCount();
	if (lastRow > 0) {
	    Widget widget = layout.getWidget(lastRow - 1, 0);
	    if (widget instanceof DropDownItem) {
		DropDownItem item = (DropDownItem) widget;
		item.select();

	    } else if (widget instanceof DropDownTopOrBottomItem) {
		DropDownTopOrBottomItem item = (DropDownTopOrBottomItem) widget;
		item.select();
	    }
	}
    }

    public void removeAllFocus() {
	int lastRow = layout.getRowCount();
	for (int i = 0; i < lastRow; ++i) {
	    Widget widget = layout.getWidget(i, 0);
	    if (widget instanceof DropDownItem) {
		DropDownItem item = (DropDownItem) widget;
		item.deSelect();

	    } else if (widget instanceof DropDownTopOrBottomItem) {
		DropDownTopOrBottomItem item = (DropDownTopOrBottomItem) widget;
		item.deSelect();
	    }
	}
    }

    public void goToPreviousPage(String keyword) {
	currentPageNo = currentPageNo - 1;
	List<HashMap<String, String>> persons = pages.get(currentPageNo);
	initLayout(keyword, persons);
    }

    public void goToNextPage(String keyword) {
	if (currentPageNo < pages.size()) {
	    currentPageNo = currentPageNo + 1;
	    List<HashMap<String, String>> persons = pages.get(currentPageNo);
	    initLayout(keyword, persons);
	}
    }

    private Map<Integer, List<HashMap<String, String>>> paginate(
	    List<HashMap<String, String>> persons) {
	int pageNo = 0;
	Map<Integer, List<HashMap<String, String>>> pages = new LinkedHashMap<Integer, List<HashMap<String, String>>>();
	List<HashMap<String, String>> page = null;

	int personIndex = 0;
	for (Iterator<HashMap<String, String>> iter = persons.iterator(); iter
		.hasNext();) {
	    HashMap<String, String> person = iter.next();

	    if (page == null) {
		page = new ArrayList<HashMap<String, String>>();
		pages.put(++pageNo, page);
	    }
	    page.add(person);
	    person.put("personIndex", String.valueOf(++personIndex));

	    if (page.size() == 5) {
		page = null;
	    }
	}
	return pages;
    }

    private void initTop(final int row, final FlexTable layout,
	    final DropDownTopOrBottomItem top) {
	layout.setWidget(row, 0, top);

	top.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		goToPreviousPage(currentKeyword);
		focusLast();
	    }
	});

	top.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    goToPreviousPage(currentKeyword);
		    focusLast();

		} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
		    goToPreviousPage(currentKeyword);
		    focusLast();
		}
	    }
	});

	top.addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
		    top.deSelect();
		    DropDownItem item = (DropDownItem) layout.getWidget(
			    row + 1, 0);
		    item.select();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
		    goToPreviousPage(currentKeyword);
		    focusLast();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    goToPreviousPage(currentKeyword);
		    focusLast();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
		    hide();
		}

	    }
	});

	top.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		top.select();
	    }
	});

	top.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		top.deSelect();
	    }
	});
    }

    private void initBottom(final int row, final FlexTable layout,
	    final DropDownTopOrBottomItem bottom) {
	layout.setWidget(row, 0, bottom);

	bottom.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		goToNextPage(currentKeyword);
		focusFirst();
	    }
	});

	bottom.addKeyPressHandler(new KeyPressHandler() {
	    @Override
	    public void onKeyPress(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		    goToNextPage(currentKeyword);
		    focusFirst();

		} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
		    goToNextPage(currentKeyword);
		    focusFirst();
		}
	    }
	});

	bottom.addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
		    bottom.deSelect();

		    Widget widget = layout.getWidget(row - 1, 0);
		    if (widget instanceof DropDownItem) {
			DropDownItem item = (DropDownItem) widget;
			item.select();

		    } else if (widget instanceof DropDownTopOrBottomItem) {
			DropDownTopOrBottomItem item = (DropDownTopOrBottomItem) widget;
			item.select();
		    }

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
		    goToNextPage(currentKeyword);
		    focusFirst();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    goToNextPage(currentKeyword);
		    focusFirst();

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
		    hide();
		}

	    }
	});

	bottom.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		bottom.select();
	    }
	});

	bottom.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		bottom.deSelect();
	    }
	});
    }

    private void init(String keyword, final int row, final FlexTable layout,
	    HashMap<String, String> person, final TextItem originalTextItem) {
	final DropDownItem item = new DropDownItem(keyword, person);
	layout.setWidget(row, 0, item);

	item.addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
		    onKeyDown2(row);

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
		    onKeyUp(row);

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    handle(row, originalTextItem);

		} else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
		    hide();
		}
		event.preventDefault();
	    }
	});

	item.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		item.select();
	    }
	});

	item.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		removeAllFocus();
	    }
	});

	item.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		handle(row, originalTextItem);
	    }
	});
    }

    public void handle(int row) {
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		hide();
	    }
	});
	DropDownItem item = (DropDownItem) layout.getWidget(row, 0);
	handler.handle(item.getIdentifier(), item.getIndexSearchType());
    }

    private void onKeyDown2(int row) {
	Widget widget = layout.getWidget(row, 0);
	if (widget != null && widget instanceof DropDownItem) {
	    DropDownItem item = (DropDownItem) widget;
	    removeAllFocus();

	    widget = layout.getWidget(row + 1, 0);
	    if (widget != null && widget instanceof DropDownTopOrBottomItem) {
		DropDownTopOrBottomItem bottom = (DropDownTopOrBottomItem) widget;
		bottom.select();

	    } else if (widget != null && widget instanceof DropDownItem) {
		item = (DropDownItem) widget;
		item.select();
	    }
	}
    }

    private void onKeyUp(int row) {
	Widget widget = layout.getWidget(row, 0);
	if (widget instanceof DropDownItem) {
	    DropDownItem item = (DropDownItem) widget;
	    removeAllFocus();

	    widget = layout.getWidget(row - 1, 0);
	    if (widget instanceof DropDownItem) {
		item = (DropDownItem) widget;
		item.select();
	    } else if (widget instanceof DropDownTopOrBottomItem) {
		DropDownTopOrBottomItem top = (DropDownTopOrBottomItem) widget;
		top.select();
	    }

	} else if (widget instanceof DropDownTopOrBottomItem) {
	    DropDownTopOrBottomItem bottom = (DropDownTopOrBottomItem) widget;
	    bottom.deSelect();

	    DropDownItem item = (DropDownItem) layout.getWidget(row - 1, 0);
	    item.select();
	}
    }

    public FlexTable getLayout() {
	return layout;
    }

    private void handle(final int row, final TextItem originalTextItem) {
	handle(row);
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		originalTextItem.getTextbox().setStylePrimaryName(
			"blurredIndexSearchTextbox");

		originalTextItem.getTextbox().setText(
			SearchSuggestBox.TEXT_BOX_HINT);

	    }
	});
    }
}
