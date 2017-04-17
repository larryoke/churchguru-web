package com.laotek.churchguru.web.clientm.activity.message.category;

import java.util.List;

import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.laotek.churchguru.web.clientm.activity.DetailView;

public interface MessageCategoryMobView extends DetailView {

    void setPresenter(Presenter presenter);

    HasCellSelectedHandler getCellSelectedHandler();

    void refresh();

    void render(List<MessageCategoryMobDto> dtos);

    public interface Presenter {
    }
}
