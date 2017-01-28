package com.laotek.churchguru.web.server.handler.org.mobile;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.clientm.activity.home.GetMobilePropertiesAction;
import com.laotek.churchguru.web.clientm.activity.home.GetMobilePropertiesResult;
import com.laotek.churchguru.web.clientm.activity.home.MobileProperties;
import com.laotek.churchguru.web.clientm.activity.home.Topic;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetMobilePropertiesHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetMobilePropertiesAction, GetMobilePropertiesResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetMobilePropertiesResult execute(GetMobilePropertiesAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao.getOrganisation(1L);
	MobileProperties mobileProperties = new MobileProperties();
	List<Topic> topics = new ArrayList<Topic>();
	mobileProperties.setTopics(topics);
	mobileProperties.setChurchName(org.getOrgName());

	int index = -1;

	if (org.isPastorDeskChurchAppTopicFlag())
	    topics.add(new Topic(org.getPastorDeskChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.PASTORS_DESK));

	if (org.isPrayerRequestChurchAppTopicFlag())
	    topics.add(new Topic(org.getPrayerRequestChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.PRAYER_REQUEST));

	if (org.isDonationChurchAppTopicFlag())
	    topics.add(new Topic(org.getDonationChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.DONATION));

	if (org.isMessagesChurchAppTopicFlag())
	    topics.add(new Topic(org.getNoticesAndEventsChurchAppTopic(),
		    ++index, ChurchAppTopicEnum.NOTICES_AND_EVENTS));

	if (org.isFacebookChurchAppTopicFlag())
	    topics.add(new Topic(org.getFacebookChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.FACEBOOK));

	if (org.isTwitterChurchAppTopicFlag())
	    topics.add(new Topic(org.getTwitterChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.TWITTER));

	if (org.isWatchChurchAppTopicFlag())
	    topics.add(new Topic(org.getWatchChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.WATCH));

	if (org.isListenChurchAppTopicFlag())
	    topics.add(new Topic(org.getListenChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.LISTEN));

	if (org.isAboutUsChurchAppTopicFlag())
	    topics.add(new Topic(org.getAboutUsChurchAppTopic(), ++index,
		    ChurchAppTopicEnum.ABOUT_US));

	mobileProperties.setTopics(topics);

	GetMobilePropertiesResult result = new GetMobilePropertiesResult();
	result.setMobileProperties(mobileProperties);
	return result;
    }

    @Override
    public Class<GetMobilePropertiesAction> getActionType() {
	return GetMobilePropertiesAction.class;
    }

    @Override
    public void rollback(GetMobilePropertiesAction action,
	    GetMobilePropertiesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
