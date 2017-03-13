package com.laotek.churchguru.web.client.activity.churchapp.general;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateType;
import com.laotek.churchguru.web.shared.OrganisationDto;

public interface GeneralChurchAppView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String name);

    void initTab();

    void initOrganisation(OrganisationDto dto);

    void instantMessageForm();

    void clearInstantMessageForm();

    public interface Presenter {

	void submitWebsiteUrl(String websiteurl);

	void submitPastorDeskMessage(String pastorsDeskMessage);

	void submitAboutUsOrgDetails(SubmitAboutUsOrgDetailsAction action);

	void updateChurchApp(ChurchAppTopicEnum churchAppTopic, String value, UpdateType updateType);

	void goTo(Place place);
    }
}