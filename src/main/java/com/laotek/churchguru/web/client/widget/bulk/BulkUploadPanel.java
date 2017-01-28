package com.laotek.churchguru.web.client.widget.bulk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.AgeGroup;
import com.laotek.churchguru.model.shared.enums.CommsPreference;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.MaritalStatus;
import com.laotek.churchguru.model.shared.enums.MemberAccountStatus;
import com.laotek.churchguru.web.client.widget.DayMonthCellItem;
import com.laotek.churchguru.web.client.widget.FullnameCellItem;
import com.laotek.churchguru.web.client.widget.PhoneCellItem;
import com.laotek.churchguru.web.client.widget.SelectCellItem;
import com.laotek.churchguru.web.client.widget.TextCellItem;
import com.laotek.churchguru.web.shared.PhoneDto;

public class BulkUploadPanel extends BaseBulkUploadPanel {

    private DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);

    private BulkUploadRowDataHandler bulkUploadRowDataHandler;

    private FlexTable leftPanel = new FlexTable();

    private FlexTable rightPanel = new FlexTable();

    private List<Map<String, Widget>> grid = new ArrayList<Map<String, Widget>>();

    private int MAX_ROWS = 50;

    public BulkUploadPanel() {

	mainPanel.setSize("820px", "450px");

	mainPanel.addNorth(getHeaderPanel(), 60);

	Button publishButton = new Button("Publish");
	publishButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		bulkUploadRowDataHandler.publishData();
	    }
	});
	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.setWidth("800px");
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.add(publishButton);
	mainPanel.addSouth(buttonPanel, 50);

	leftScrollPanel.addScrollHandler(new ScrollHandler() {
	    @Override
	    public void onScroll(ScrollEvent event) {
		rightScrollPanel.setVerticalScrollPosition(((ScrollPanel) event
			.getSource()).getVerticalScrollPosition());
	    }
	});
	leftScrollPanel.add(leftPanel);
	mainPanel.addWest(leftScrollPanel, 380);

	rightScrollPanel.addScrollHandler(new ScrollHandler() {
	    @Override
	    public void onScroll(ScrollEvent event) {
		leftScrollPanel.setVerticalScrollPosition(((ScrollPanel) event
			.getSource()).getVerticalScrollPosition());
	    }
	});
	rightScrollPanel.add(rightPanel);
	mainPanel.addEast(rightScrollPanel, 370);

	FlexTable arrows = new FlexTable();
	arrows.setWidth("60px");
	arrows.setWidget(0, 1, goTop());
	arrows.setWidget(1, 0, goLeft());
	arrows.setWidget(1, 3, goRight());
	arrows.setWidget(2, 1, goDown());

	mainPanel.add(arrows);

	initWidget(mainPanel);
    }

    public void init(BulkUploadRowDataHandler bulkUploadRowDataHandler) {
	this.bulkUploadRowDataHandler = bulkUploadRowDataHandler;
    }

    public void initSavedAndDraftData(
	    List<Map<String, String>> currentSavedAndDraftData) {

	List<Map<String, Widget>> grid = getGridWidgets(currentSavedAndDraftData);

	initTabKeyPressForNextRow(grid);

	initFullnameColumnLeftPanel(grid);

	initFieldsColumnCenterPanel(grid);
    }

    private void initFullnameColumnLeftPanel(List<Map<String, Widget>> grid) {
	leftPanel.removeAllRows();
	int rowNum = 0;
	for (Map<String, Widget> map : grid) {
	    rowNum = grid.indexOf(map) + 1;

	    leftPanel.setWidget(rowNum, 0, new HTML(String.valueOf(rowNum)));
	    leftPanel.setWidget(rowNum, 1, map.get("fullname"));
	    leftPanel.getCellFormatter().setHeight(rowNum, 0, "40px");
	}
    }

    private void initFieldsColumnCenterPanel(List<Map<String, Widget>> grid) {
	rightPanel.removeAllRows();

	for (Map<String, Widget> map : grid) {
	    int rowNum = grid.indexOf(map) + 1;
	    rightPanel.setWidget(rowNum, 0, map.get("emailAddress"));
	    rightPanel.setWidget(rowNum, 1, map.get("mobile"));
	    rightPanel.setWidget(rowNum, 2, map.get("landLine"));
	    rightPanel.setWidget(rowNum, 3, map.get("addressLine1"));
	    rightPanel.setWidget(rowNum, 4, map.get("addressLine2"));
	    rightPanel.setWidget(rowNum, 5, map.get("town"));
	    rightPanel.setWidget(rowNum, 6, map.get("postcode"));
	    rightPanel.setWidget(rowNum, 7, map.get("birthDate"));
	    rightPanel.setWidget(rowNum, 8, map.get("ageGroup"));
	    rightPanel.setWidget(rowNum, 9, map.get("maritalStatus"));
	    rightPanel.setWidget(rowNum, 10, map.get("memberAccountStatus"));
	    rightPanel.setWidget(rowNum, 11, map.get("commsPreference"));
	    rightPanel.getCellFormatter().setHeight(rowNum, 0, "40px");
	}
    }

    private List<Map<String, Widget>> getGridWidgets(
	    List<Map<String, String>> rows) {

	grid.clear();

	Set<Integer> usedIndexes = new HashSet<Integer>();

	for (Map<String, String> rowData : rows) {
	    Map<String, Widget> rowWidget = getRowWidgets(rowData);
	    rowWidget.put("rowIndex", new HTML(rowData.get("rowIndex")));
	    grid.add(rowWidget);
	    usedIndexes.add(Integer.parseInt(rowData.get("rowIndex")));
	}

	for (int i = 0; i < MAX_ROWS; ++i) {
	    if (!usedIndexes.contains(i)) {
		Map<String, Widget> rowWidget = getRowWidgets(null);
		rowWidget.put("rowIndex", new HTML(String.valueOf(i)));
		grid.add(rowWidget);
	    }
	}

	// for (int i = 0; i < MAX_ROWS; ++i) {
	// Map<String, String> rowData = null;
	// if (i < rows.size()) {
	// rowData = rows.get(i);
	// }
	// Map<String, Widget> rowWidget = getRowWidgets(rowData);
	// rowWidget.put("rowIndex", new HTML(String.valueOf(i)));
	// grid.add(rowWidget);
	// }
	return grid;
    }

    private void initTabKeyPressForNextRow(List<Map<String, Widget>> rows) {

	ListIterator<Map<String, Widget>> iter = rows.listIterator();

	while (iter.hasNext()) {
	    Map<String, Widget> row = iter.next();
	    if (iter.hasNext()) {
		final Map<String, Widget> next = iter.next();
		((SelectCellItem) row.get("commsPreference")).getListBox()
			.addKeyDownHandler(new KeyDownHandler() {

			    @Override
			    public void onKeyDown(KeyDownEvent event) {

				Scheduler.get().scheduleDeferred(
					new ScheduledCommand() {
					    @Override
					    public void execute() {
						((FullnameCellItem) next
							.get("fullname"))
							.getTitleCell()
							.getListBox()
							.setFocus(true);
					    }
					});
			    }
			});
		iter.previous();
	    }
	}
    }

    private Map<String, Widget> getRowWidgets(Map<String, String> rowData) {

	final Map<String, Widget> rowWidget = new HashMap<String, Widget>();

	FullnameCellItem fullname = new FullnameCellItem(true, rowWidget);
	if (rowData != null && rowData.get("title") != null)
	    fullname.initTitle(rowData.get("title"));
	if (rowData != null && rowData.get("forenames") != null)
	    fullname.initForenames(rowData.get("forenames"));
	if (rowData != null && rowData.get("forenames") != null)
	    fullname.initSurname(rowData.get("surname"));

	// fullname.getTitleCell()
	addHandlers("fullname", fullname, rowWidget);
	rowWidget.put("fullname", fullname);

	TextCellItem emailAddress = new TextCellItem("Email Address",
		rowWidget, true);
	emailAddress
		.setRegex("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
	if (rowData != null && rowData.get("emailAddress") != null)
	    emailAddress.setValue(rowData.get("emailAddress"));
	addHandlers(emailAddress, rowWidget);
	rowWidget.put("emailAddress", emailAddress);

	PhoneCellItem mobile = new PhoneCellItem("Mobile Number", rowWidget,
		true);
	if (rowData != null && rowData.get("mobile") != null)
	    mobile.setValue(rowData.get("mobileCountryCode"),
		    rowData.get("mobile"));
	addHandlers(mobile, rowWidget);
	rowWidget.put("mobile", mobile);

	PhoneCellItem landLine = new PhoneCellItem("Land Line", rowWidget,
		false);
	if (rowData != null && rowData.get("landLine") != null)
	    landLine.setValue(rowData.get("landNumberCountryCode"),
		    rowData.get("landLine"));
	addHandlers(landLine, rowWidget);
	rowWidget.put("landLine", landLine);

	TextCellItem addressLine1 = new TextCellItem("Address Line1",
		rowWidget, true);
	if (rowData != null && rowData.get("addressLine1") != null)
	    addressLine1.setValue(rowData.get("addressLine1"));
	addHandlers(addressLine1, rowWidget);
	rowWidget.put("addressLine1", addressLine1);

	TextCellItem addressLine2 = new TextCellItem("Address Line2",
		rowWidget, false);
	if (rowData != null && rowData.get("addressLine2") != null)
	    addressLine2.setValue(rowData.get("addressLine2"));
	addHandlers(addressLine2, rowWidget);
	rowWidget.put("addressLine2", addressLine2);

	TextCellItem town = new TextCellItem("Town", rowWidget, true, "80px");
	if (rowData != null && rowData.get("town") != null)
	    town.setValue(rowData.get("town"));
	addHandlers(town, rowWidget);
	rowWidget.put("town", town);

	TextCellItem postcode = new TextCellItem("Postcode", rowWidget, true,
		"50px");
	if (rowData != null && rowData.get("postcode") != null)
	    postcode.setValue(rowData.get("postcode"));
	addHandlers(postcode, rowWidget);
	rowWidget.put("postcode", postcode);

	DayMonthCellItem birthDate = new DayMonthCellItem(rowWidget, true);
	if (rowData != null && rowData.get("birthDay") != null
		&& rowData.get("birthMonth") != null) {
	    birthDate.initItem(Integer.parseInt(rowData.get("birthDay")),
		    rowData.get("birthMonth"));
	}
	addHandlers(birthDate, rowWidget);
	rowWidget.put("birthDate", birthDate);

	SelectCellItem ageGroup = new SelectCellItem("Age Group", rowWidget,
		true);
	for (AgeGroup status : AgeGroup.values()) {
	    ageGroup.addItem(status.getDesc());
	}
	ageGroup.setSelectedIndex(rowData != null ? rowData.get("ageGroup")
		: null);
	addHandlers(ageGroup, rowWidget);
	rowWidget.put("ageGroup", ageGroup);

	SelectCellItem maritalStatus = new SelectCellItem("Marital Status",
		rowWidget, true);
	for (MaritalStatus status : MaritalStatus.values()) {
	    maritalStatus.addItem(status.getDesc());
	}
	maritalStatus.setSelectedIndex(rowData != null ? rowData
		.get("maritalStatus") : null);
	addHandlers(maritalStatus, rowWidget);
	rowWidget.put("maritalStatus", maritalStatus);

	SelectCellItem memberAccountStatus = new SelectCellItem(
		"Account Status", rowWidget, true);
	for (MemberAccountStatus status : MemberAccountStatus.values()) {
	    memberAccountStatus.addItem(status.getDesc());
	}
	memberAccountStatus.setSelectedIndex(rowData != null ? rowData
		.get("memberAccountStatus") : null);
	addHandlers(memberAccountStatus, rowWidget);
	rowWidget.put("memberAccountStatus", memberAccountStatus);

	SelectCellItem commsPreference = new SelectCellItem("Comms Pref",
		rowWidget, true);
	for (CommsPreference status : CommsPreference.values()) {
	    commsPreference.addItem(status.getDesc());
	}
	commsPreference.setSelectedIndex(rowData != null ? rowData
		.get("commsPreference") : null);
	addHandlers(commsPreference, rowWidget);
	rowWidget.put("commsPreference", commsPreference);

	return rowWidget;
    }

    private void addHandlers(final String key, FullnameCellItem fullname,
	    final Map<String, Widget> rowWidget) {

	fullname.getTitleCell().getListBox()
		.addChangeHandler(new ChangeHandler() {
		    @Override
		    public void onChange(ChangeEvent event) {
			save(rowWidget);
		    }
		});

	fullname.getForenames().getTextbox().addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		save(rowWidget);
	    }
	});
	fullname.getSurname().getTextbox().addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		save(rowWidget);
	    }
	});
	fullname.getSurname().getTextbox()
		.addKeyDownHandler(new KeyDownHandler() {
		    @Override
		    public void onKeyDown(KeyDownEvent event) {
			int keyCode = event.getNativeEvent().getKeyCode();
			if (keyCode == KeyCodes.KEY_TAB) {

			    Scheduler.get().scheduleDeferred(
				    new ScheduledCommand() {
					@Override
					public void execute() {
					    ((TextCellItem) rowWidget
						    .get("emailAddress"))
						    .getTextbox()
						    .setFocus(true);
					}
				    });
			}
		    }
		});

    }

    private void addHandlers(DayMonthCellItem dayMonthCellItem,
	    final Map<String, Widget> rowWidget) {

	dayMonthCellItem.addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		save(rowWidget);
	    }
	}, new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		save(rowWidget);
	    }
	});
    }

    private void addHandlers(TextCellItem textCellItem,
	    final Map<String, Widget> rowWidget) {
	textCellItem.getTextbox().addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		save(rowWidget);
	    }
	});
    }

    private void addHandlers(PhoneCellItem phoneCellItem,
	    final Map<String, Widget> rowWidget) {
	phoneCellItem.getTextbox().addBlurHandler(new BlurHandler() {
	    @Override
	    public void onBlur(BlurEvent event) {
		save(rowWidget);
	    }
	});
    }

    private void addHandlers(SelectCellItem selectCellItem,
	    final Map<String, Widget> rowWidget) {
	selectCellItem.addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		save(rowWidget);
	    }
	});
    }

    private void save(Map<String, Widget> rowWidget) {
	Map<String, String> rowData = new HashMap<String, String>();

	boolean foundDataToSave = false;

	for (Map.Entry<String, Widget> entry : rowWidget.entrySet()) {
	    Widget widget = entry.getValue();
	    String valueStr = null;

	    if (widget instanceof FullnameCellItem) {
		FullnameCellItem fullnameCellItem = ((FullnameCellItem) widget);
		String title = fullnameCellItem.getTitleCell().getValue();
		String forenames = fullnameCellItem.getForenames().getValue();
		String surname = fullnameCellItem.getSurname().getValue();

		if ((!"".equals(title) && title != null)
			|| (!"".equals(forenames) && forenames != null)
			|| (!"".equals(surname) && surname != null)) {
		    foundDataToSave = true;
		    rowData.put("title", title);
		    rowData.put("surname", surname);
		    rowData.put("forenames", forenames);
		}

	    } else if (widget instanceof TextCellItem) {
		valueStr = ((TextCellItem) widget).getValue();
		if (!"".equals(valueStr) && valueStr != null) {
		    foundDataToSave = true;
		    rowData.put(entry.getKey(), valueStr);
		}

	    } else if (widget instanceof SelectCellItem) {
		SelectCellItem selectCellItem = (SelectCellItem) widget;
		valueStr = selectCellItem.getValue();
		if (!"".equals(valueStr) && valueStr != null) {
		    foundDataToSave = true;
		    rowData.put(entry.getKey(), valueStr);
		}

	    } else if (widget instanceof PhoneCellItem) {
		PhoneCellItem phoneCellItem = (PhoneCellItem) widget;
		PhoneDto dto = phoneCellItem.getValue();
		if (dto != null) {
		    CountryCode cc = dto.getCountryCode();
		    if (cc != null
			    && !cc.getCode().equals(dto.getNumber())
			    && dto.getNumber() != null
			    && !"".equals(dto.getNumber())
			    && !phoneCellItem.getLabel()
				    .equals(dto.getNumber())) {
			foundDataToSave = true;
			rowData.put(entry.getKey(), dto.getNumber());

			if ("mobile".equals(entry.getKey())) {
			    rowData.put("mobileCountryCode", cc.name());

			} else if ("landLine".equals(entry.getKey())) {
			    rowData.put("landNumberCountryCode", cc.name());
			}
		    }
		}

	    } else if (widget instanceof DayMonthCellItem) {
		DayMonthCellItem dayMonthCellItem = (DayMonthCellItem) widget;
		String day = dayMonthCellItem.getDay();
		String month = dayMonthCellItem.getMonth();

		if ((!"".equals(day) && day != null)
			|| (!"".equals(month) && month != null)) {
		    foundDataToSave = true;
		    rowData.put("birthDay", day);
		    rowData.put("birthMonth", month);
		}

	    } else if ("rowIndex".equals(entry.getKey())
		    && widget instanceof HTML) {
		String rowIndex = ((HTML) widget).getHTML();
		rowData.put("rowIndex", rowIndex);
	    }
	}

	bulkUploadRowDataHandler.saveOrDeleteRowData(rowData, foundDataToSave);
    }

    public boolean validate() {
	boolean validated = true;
	for (Map<String, Widget> rowWidget : grid) {
	    if (!emptyRow(rowWidget)) {
		for (Map.Entry<String, Widget> entry : rowWidget.entrySet()) {
		    Widget widget = entry.getValue();

		    if (widget instanceof FullnameCellItem) {
			FullnameCellItem fullnameCellItem = ((FullnameCellItem) widget);
			if (!fullnameCellItem.validate(false) && validated) {
			    validated = false;
			}

		    } else if (widget instanceof TextCellItem) {
			if (!((TextCellItem) widget).validate(false)
				&& validated) {
			    validated = false;
			}

		    } else if (widget instanceof PhoneCellItem) {
			if (!((PhoneCellItem) widget).validate(false)
				&& validated) {
			    validated = false;
			}

		    } else if (widget instanceof SelectCellItem) {
			if (!((SelectCellItem) widget).validate(false)
				&& validated) {
			    validated = false;
			}

		    } else if (widget instanceof DayMonthCellItem) {
			if (!((DayMonthCellItem) widget).validate(false)
				&& validated) {
			    validated = false;
			}
		    }
		}
	    }
	}
	return validated;
    }

    private boolean emptyRow(Map<String, Widget> rowWidget) {

	for (Map.Entry<String, Widget> entry : rowWidget.entrySet()) {
	    Widget widget = entry.getValue();

	    if (widget instanceof FullnameCellItem) {
		FullnameCellItem fullnameCellItem = ((FullnameCellItem) widget);
		if (!fullnameCellItem.empty()) {
		    return false;
		}

	    } else if (widget instanceof TextCellItem) {
		if (!((TextCellItem) widget).empty()) {
		    return false;
		}

	    } else if (widget instanceof SelectCellItem) {
		if (!((SelectCellItem) widget).empty()) {
		    return false;
		}

	    } else if (widget instanceof PhoneCellItem) {
		if (!((PhoneCellItem) widget).empty()) {
		    return false;
		}
	    } else if (widget instanceof DayMonthCellItem) {
		if (!((DayMonthCellItem) widget).emptyDay()) {
		    return false;
		}
	    } else if (widget instanceof DayMonthCellItem) {
		if (!((DayMonthCellItem) widget).emptyMonth()) {
		    return false;
		}
	    }
	}
	return true;
    }

}
