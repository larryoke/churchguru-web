package com.laotek.churchguru.web.clientm.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

@Deprecated
public class InsertPictureDialog extends DialogBox {

    private FlexTable panel = new FlexTable();
    private FileUpload fileUpload = new FileUpload();
    private Button submit = new Button("Submit");
    private Anchor hide = new Anchor("Hide");
    private FormPanel form = new FormPanel();

    public InsertPictureDialog(String caption) {
	super(true);
	setText(caption);
	setSize("450px", "280px");
	setAnimationEnabled(true);

	panel.setSize("80%", "100%");
	setWidget(panel);

	hide.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});

	submit.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		form.submit();
	    }
	});

	form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
	    @Override
	    public void onSubmitComplete(SubmitCompleteEvent event) {
		Window.alert(event.getResults());
	    }
	});

	form.addSubmitHandler(new SubmitHandler() {
	    @Override
	    public void onSubmit(SubmitEvent event) {

	    }
	});

	form.setAction("/churchguru");
	form.setEncoding(FormPanel.ENCODING_MULTIPART);
	form.setMethod(FormPanel.METHOD_POST);
	form.add(this);
    }

    public void init() {
	panel.removeAllRows();
	panel.setBorderWidth(0);
	FlexCellFormatter formatter = panel.getFlexCellFormatter();

	panel.setWidget(0, 0, new Image("images/app/user.png"));
	formatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_RIGHT);
	panel.setHTML(0, 1, "&nbsp;");

	panel.setHTML(1, 0, "&nbsp;");
	panel.setHTML(1, 1, "&nbsp;");

	panel.setWidget(2, 0, fileUpload);
	formatter.setHorizontalAlignment(2, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	formatter.setColSpan(2, 0, 2);

	panel.setHTML(3, 0, "&nbsp;");
	panel.setHTML(3, 1, "&nbsp;");

	panel.setWidget(4, 0, hide);
	formatter.setHorizontalAlignment(4, 0,
		HasHorizontalAlignment.ALIGN_RIGHT);
	panel.setWidget(4, 1, submit);
	formatter.setHorizontalAlignment(4, 1,
		HasHorizontalAlignment.ALIGN_LEFT);

	RootPanel.get().add(form);
	center();
    }
}
