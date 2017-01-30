package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageResult;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageResult;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public class ListeningMessageNewActivity extends AbstractActivity implements
	ListeningMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private ListeningMessageNewView view;
    private ListeningMessageNewPlace place;

    public ListeningMessageNewActivity(ListeningMessageNewPlace place,
                                       ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getEStoreMessageNewView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
	view.initWidgets();
	String identifier = place.getIdentifier();
	getNewMessage(identifier);
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
	return null;
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }

    private void getNewMessage(String identifier) {
	GetListeningMessageAction action = new GetListeningMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetListeningMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("Unable to retrieve the message request due to a server error");
		    }

		    @Override
		    public void onSuccess(GetListeningMessageResult result) {
			ListeningMessageDto dto = result.getMessage();
			List<ListeningSpeakerDto> speakers = result.getSpeakers();
			List<ListeningCategoryDto> categories = result
				.getCategories();
			List<ListeningMessagePictureDto> pictures = result
				.getPictures();
			Map<String, Boolean> workersSelectedForFreeMessages = result
				.getWorkersSelectedForFreeMessages();

			view.initNewMessage(dto, speakers, categories,
				pictures, workersSelectedForFreeMessages);
		    }
		});

    }

    @Override
    public void submit(SubmitListeningMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitListeningMessageResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    SubmitListeningMessageResult result) {
				view.uploadPhotosByWorker();
			    }
			});

    }

}
