package com.laotek.churchguru.web.clientm.activity.message.title;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface MessageTitleView extends DetailView {

    void setPresenter(Presenter presenter);

    HasCellSelectedHandler getCellSelectedHandler();

    void refresh();

    void render(List<MessageTitleMobDto> dtos);

    public interface Presenter {
    }
}
