package com.laotek.churchguru.web.clientm.activity.notice.titles;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface NoticeAndEventTitlesView extends DetailView {

    void setPresenter(Presenter presenter);

    void render(List<NoticeAndEventDto> messages);

    void displayNavigationButtons(boolean previous, boolean next);

    HasCellSelectedHandler getCellSelectedHandler();

    void displayError();

    void refresh();

    public interface Presenter {
	void goHome();

	void getNewer();

	void getOlder();
    }
}
