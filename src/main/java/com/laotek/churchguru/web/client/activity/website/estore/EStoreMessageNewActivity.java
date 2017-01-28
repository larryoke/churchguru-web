package com.laotek.churchguru.web.client.activity.website.estore;

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
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessageAction;
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessageResult;
import com.laotek.churchguru.web.client.activity.estore.SubmitEStoreMessageAction;
import com.laotek.churchguru.web.client.activity.estore.SubmitEStoreMessageResult;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessagePictureDto;
import com.laotek.churchguru.web.shared.estore.EStoreSpeakerDto;

public class EStoreMessageNewActivity extends AbstractActivity implements
	EStoreMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private EStoreMessageNewView view;
    private EStoreMessageNewPlace place;

    public EStoreMessageNewActivity(EStoreMessageNewPlace place,
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
	GetEStoreMessageAction action = new GetEStoreMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetEStoreMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("Unable to retrieve the message request due to a server error");
		    }

		    @Override
		    public void onSuccess(GetEStoreMessageResult result) {
			EStoreMessageDto dto = result.getMessage();
			List<EStoreSpeakerDto> speakers = result.getSpeakers();
			List<EStoreCategoryDto> categories = result
				.getCategories();
			List<EStoreMessagePictureDto> pictures = result
				.getPictures();
			Map<String, Boolean> workersSelectedForFreeMessages = result
				.getWorkersSelectedForFreeMessages();

			view.initNewMessage(dto, speakers, categories,
				pictures, workersSelectedForFreeMessages);
		    }
		});

    }

    @Override
    public void submit(SubmitEStoreMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitEStoreMessageResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    SubmitEStoreMessageResult result) {
				view.uploadPhotosByWorker();
			    }
			});

    }

}
