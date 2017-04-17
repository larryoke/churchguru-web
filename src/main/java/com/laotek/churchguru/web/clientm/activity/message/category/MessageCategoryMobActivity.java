package com.laotek.churchguru.web.clientm.activity.message.category;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.activity.message.title.MessageTitlePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class MessageCategoryMobActivity extends DetailActivity implements MessageCategoryMobView.Presenter {

    private final MobileFactory mobileFactory;

    private MessageCategoryMobView view;

    private List<MessageCategoryMobDto> categoryDtos;

    public MessageCategoryMobActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getMessageCategoryView();
	view.setPresenter(this);
	panel.setWidget(view);
	initIndexAndMessages(view);
    }

    private void initIndexAndMessages(final MessageCategoryMobView view) {

	addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(new CellSelectedHandler() {

	    @Override
	    public void onCellSelected(CellSelectedEvent event) {
		int index = event.getIndex();
		MessageCategoryMobDto category = categoryDtos.get(index);

		MobileContext.getInstance().getClientFactory().getPlaceController()
			.goTo(new MessageTitlePlace(String.valueOf(category.getIdentifier())));
	    }
	}));

	GetMessageCategoriesMobAction action = new GetMessageCategoriesMobAction();
	MobileContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<GetMessageCategoriesMobResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
		    }

		    @Override
		    public void onSuccess(GetMessageCategoriesMobResult result) {
			categoryDtos = result.getCategoryDtos();
			view.getHeader().setText("Message Categories");
			view.render(result.getCategoryDtos());
			view.refresh();
		    }
		});
    }

}
