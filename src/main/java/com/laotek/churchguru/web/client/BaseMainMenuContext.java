package com.laotek.churchguru.web.client;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageResult;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.EnumNoticeOrEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventActionResult;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.media.youtube.CreateNewYoutubeVideoAction;
import com.laotek.churchguru.web.client.activity.media.youtube.CreateNewYoutubeVideoResult;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.MediaMessageNewPlace;

public abstract class BaseMainMenuContext {

    protected FlexTable dashboardPanel = new FlexTable();
    protected FlexTable websitePanel = new FlexTable();
    protected FlexTable noticesAndEventsPanel = new FlexTable();
    protected FlexTable messageMediaPanel = new FlexTable();
    protected FlexTable donationPanel = new FlexTable();
    protected FlexTable socialMediaPanel = new FlexTable();
    protected FlexTable userPanel = new FlexTable();

    protected Timer startBlinker;
    protected Set<Anchor> blinkingAnchors = new HashSet<Anchor>();

    /**
     * If
     *
     * @param requiredUserRoleName
     * @param currentUserRoleName
     * @return
     */
    protected boolean hasRoleRequired(UserRoleName requiredUserRoleName, UserRoleName currentUserRoleName) {
	if (requiredUserRoleName.equals(currentUserRoleName)) {
	    return true;
	} else if (currentUserRoleName != null && currentUserRoleName.getChildren() != null
		&& currentUserRoleName.getChildren().length > 0) {
	    for (UserRoleName userRoleName : currentUserRoleName.getChildren()) {
		if (requiredUserRoleName.equals(userRoleName)) {
		    return true;
		}
	    }
	}
	return false;
    }

    protected void createLink(Image image, FlexTable panel, final Anchor anchor) {
	int row = panel.getRowCount();
	panel.setWidget(row, 0, image);
	panel.setWidget(row, 1, anchor);
	image.setWidth("30px");
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
    }

    protected void createLink(Image image, FlexTable panel, final Anchor anchor, UserRoleName requiredUserRoleName,
	    UserRoleName currentUserRoleName) {
	if (hasRoleRequired(requiredUserRoleName, currentUserRoleName)) {
	    createLink(image, panel, anchor);
	}
    }

    protected void createLink(Image image, String label, boolean blinkAnchor, FlexTable panel, final Place place,
	    UserRoleName minimumUserRoleName, UserRoleName currentUserRoleName) {
	Anchor link = new Anchor(label);
	if (blinkAnchor) {
	    blinkingAnchors.add(link);
	}
	createLink(image, link, panel, place, minimumUserRoleName, currentUserRoleName);
    }

    private void createLink(Image image, Anchor link, FlexTable panel, final Place place,
	    UserRoleName minimumUserRoleName, UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.setWidget(row, 0, image);
	panel.setWidget(row, 1, link);
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(place);
	    }
	});
    }

    protected void createPostNoticeOrEventLink(Image image, FlexTable panel, UserRoleName minimumUserRoleName,
	    UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.setWidget(row, 0, image);
	Anchor link = new Anchor("Post new notice or event");
	panel.setWidget(row, 1, link);
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = Window.prompt("Please start here by submitting the subject of new notice or event",
			"");
		if (subject != null && !subject.trim().equals("")) {
		    NoticeAndEventAction action = new NoticeAndEventAction(subject, EnumNoticeOrEventAction.CREATE);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext.getInstance().getDispatchClient().execute(action,
			    new AsyncCallback<NoticeAndEventActionResult>() {
				@Override
				public void onFailure(Throwable throwable) {
				    Window.alert("An error occured:  " + throwable.getMessage());
				}

				@Override
				public void onSuccess(NoticeAndEventActionResult result) {

				    ApplicationContext.getInstance().getPlaceController()
					    .goTo(new PostNoticeOrEventChurchAppPlace(result.getId(),
						    result.getIdentifier()));

				}
			    });
		}
	    }
	});
    }

    protected void createNewListeningMessageLink(Image image, FlexTable panel, UserRoleName minimumUserRoleName,
	    UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.setWidget(row, 0, image);
	Anchor link = new Anchor("Post new audio message");
	panel.setWidget(row, 1, link);
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = Window.prompt("Please provide the title of the new audio message to be uploaded", "");
		if (subject != null && !subject.trim().equals("")) {

		    CreateNewAudioMessageAction action = new CreateNewAudioMessageAction(subject);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext.getInstance().getDispatchClient().execute(action,
			    new AsyncCallback<CreateNewAudioMessageResult>() {
				@Override
				public void onFailure(Throwable throwable) {
				    Window.alert("A server error occured when attempting to create a new message.");
				}

				@Override
				public void onSuccess(CreateNewAudioMessageResult result) {
				    ApplicationContext.getInstance().getPlaceController()
					    .goTo(new MediaMessageNewPlace(result.getNewMessageID()));
				}
			    });
		}
	    }
	});
    }

    protected void createNewYoutubeEmbedLink(Image image, FlexTable panel, UserRoleName minimumUserRoleName,
	    UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.setWidget(row, 0, image);
	Anchor link = new Anchor("Post new YouTube video");
	panel.setWidget(row, 1, link);
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = Window.prompt("Please provide the title of the new youtube video to be embedded", "");
		if (subject != null && !subject.trim().equals("")) {
		    CreateNewYoutubeVideoAction action = new CreateNewYoutubeVideoAction(subject);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext.getInstance().getDispatchClient().execute(action,
			    new AsyncCallback<CreateNewYoutubeVideoResult>() {
				@Override
				public void onFailure(Throwable throwable) {
				    Window.alert("A server error occured when attempting to create a new message.");
				}

				@Override
				public void onSuccess(CreateNewYoutubeVideoResult result) {
				    ApplicationContext.getInstance().getPlaceController()
					    .goTo(new YoutubeVideoNewPlace(result.getNewMessageID()));
				}
			    });
		}
	    }
	});
    }

    protected void createHelpLink(Image image, Anchor link, FlexTable panel, final String placeName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	// panel.setWidget(row, 0, new Image(
	// "images/app/2nd_level_bullet_black.png"));
	panel.setWidget(row, 0, image);
	panel.setWidget(row, 1, link);
	panel.getCellFormatter().setHeight(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 0, "30px");
	panel.getCellFormatter().setWidth(row, 1, "100%");
	panel.getCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_TOP);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String protocol = Window.Location.getProtocol();
		String host = Window.Location.getHost();
		UrlBuilder builder = Window.Location.createUrlBuilder().setProtocol(protocol).setHost(host)
			.setPath(placeName);
		String url = builder.buildString();
		Window.open(url, "_blank", "");
	    }
	});
    }

    protected void initBlinking() {
	Window.setTitle("Member Invitation Awaiting Approval");
	if (startBlinker != null) {
	    startBlinker.cancel();
	}

	startBlinker = new Timer() {
	    public void run() {

		Iterator<Anchor> iter = blinkingAnchors.iterator();
		while (iter.hasNext()) {
		    iter.next().setStylePrimaryName("labelBlink");
		}

		Timer stopBlinker = new Timer() {
		    public void run() {
			Iterator<Anchor> iter = blinkingAnchors.iterator();
			while (iter.hasNext()) {
			    iter.next().removeStyleName("labelBlink");
			}
			cancel();
		    }
		};
		stopBlinker.schedule(1000);
	    }
	};

	startBlinker.scheduleRepeating(2000);
    }

    protected Anchor createHelpAnchor(final String url) {
	Anchor helpAnchor = new Anchor("Help");
	helpAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {

		String protocol = Window.Location.getProtocol();
		String host = Window.Location.getHost();
		UrlBuilder builder = Window.Location.createUrlBuilder().setProtocol(protocol).setHost(host).setPath(url)
			.setHash(null);
		String url = builder.buildString();
		Window.open(url, "_blank", "");

	    }
	});
	return helpAnchor;
    }
}
