package com.laotek.churchguru.web.clientm.activity.message.title;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.activity.message.MessagePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class MessageTitleActivity extends DetailActivity implements MessageTitleView.Presenter {

    private final MobileFactory mobileFactory;

    private MessageTitleView view;

    private List<MessageTitleMobDto> titleDtos;

    private MessageTitlePlace place;

    public MessageTitleActivity(MessageTitlePlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getMessageTitleView();
	view.setPresenter(this);
	panel.setWidget(view);
	initIndexAndMessages(view);
    }

    private void initIndexAndMessages(final MessageTitleView view) {

	addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(new CellSelectedHandler() {

	    @Override
	    public void onCellSelected(CellSelectedEvent event) {
		int index = event.getIndex();
		MessageTitleMobDto title = titleDtos.get(index);

		MobileContext.getInstance().getClientFactory().getPlaceController()
			.goTo(new MessagePlace(String.valueOf(title.getIdentifier())));
	    }
	}));

	GetMessageTitlesMobAction action = new GetMessageTitlesMobAction(place.getCategoryIdentifier());
	MobileContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMessageTitlesMobResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetMessageTitlesMobResult result) {
		titleDtos = result.getTitleDtos();
		view.getHeader().setText(result.getCategoryName());
		view.render(result.getTitleDtos());
		view.refresh();
	    }
	});
    }

}
