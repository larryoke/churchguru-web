package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.UserAuditDto;
import com.laotek.churchguru.web.shared.UserDto;

public interface SingleUserView extends IsWidget  {

    void setName(String adminPermName);
    void setPresenter(Presenter presenter);
    void initUser(UserDto dto);
    void initTab();
    
    void addUserAuditTrail(List<UserAuditDto> dtos, boolean hasMore);

    public interface Presenter {
    	void collectAuditTrail();
        void goTo(Place place);
    }
}
