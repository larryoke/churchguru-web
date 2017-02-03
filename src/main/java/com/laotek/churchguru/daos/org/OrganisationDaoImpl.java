package com.laotek.churchguru.daos.org;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.Base64Utils;
import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.daos.shared.exceptions.OrgNotFoundException;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.model.shared.enums.OrganisationAccountStatus;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrganisationDaoImpl extends BaseSessionFactory implements OrganisationDao {

    // @Override
    // public void addNewOrganisation(Organisation organisation) {
    // Set<LogoItem> logoItems = new HashSet<LogoItem>();
    //
    // LogoItem logoItem = new LogoItem();
    // logoItem.setLogoItemType(LogoItemType.CHURCH_LOGO);
    // logoItem.setCreatedDate(new Date());
    // logoItem.setLastUpdatedDate(new Date());
    // getCurrentSession().persist(logoItem);
    // logoItems.add(logoItem);
    //
    // // churchapp
    // logoItem = new LogoItem();
    // logoItem.setLogoItemType(LogoItemType.CHURCH_APP_PROFILE_PIC);
    // logoItem.setCreatedDate(new Date());
    // logoItem.setLastUpdatedDate(new Date());
    // getCurrentSession().persist(logoItem);
    // logoItems.add(logoItem);
    //
    // getCurrentSession().persist(organisation);
    //
    // organisation.setLogoItems(logoItems);
    // getCurrentSession().update(organisation);
    // }

    @Override
    public void addNewOrganisation(Organisation organisation) {
	Set<LogoItem> logoItems = new HashSet<LogoItem>();

	for (LogoItemType item : LogoItemType.values()) {
	    LogoItem logoItem = new LogoItem();
	    String base64Data = null;

	    if (LogoItemType.CHURCH_LOGO.equals(item)) {
		base64Data = getData("/mobileapp/images/logo.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("logo.png");

	    } else if (LogoItemType.PRAYER_REQUEST.equals(item)) {
		base64Data = getData("/mobileapp/images/home.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("home.png");

	    } else if (LogoItemType.ABOUT_US_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/aboutUs.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("aboutUs.png");

	    } else if (LogoItemType.CHURCH_APP_PROFILE_PIC.equals(item)) {
		base64Data = getData("/mobileapp/images/home.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("home.png");

	    } else if (LogoItemType.FACEBOOK_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/facebook.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("facebook.png");

	    } else if (LogoItemType.GIVE_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/give.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("give.png");

	    } else if (LogoItemType.LISTEN.equals(item)) {
		base64Data = getData("/mobileapp/images/listen.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("listen.png");

	    } else if (LogoItemType.MESSAGES_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/messages.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("messages.png");

	    } else if (LogoItemType.PASTORS_DESK_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/home.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("home.png");

	    } else if (LogoItemType.ABOUT_PASTOR_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/home.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("home.png");

	    } else if (LogoItemType.WEBSITE.equals(item)) {
		base64Data = getData("/mobileapp/images/website.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("sundaySchool.png");

	    } else if (LogoItemType.TWITTER_PHOTO.equals(item)) {
		base64Data = getData("/mobileapp/images/twitter.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("twitter.png");

	    } else if (LogoItemType.YOUTUBE.equals(item)) {
		base64Data = getData("/mobileapp/images/youtube.png");
		logoItem.setBase64Data(base64Data);
		logoItem.setContentType("image/png");
		logoItem.setFilename("youtube.png");
	    }

	    logoItem.setLogoItemType(item);
	    logoItem.setCreatedDate(new Date());
	    logoItem.setLastUpdatedDate(new Date());
	    getCurrentSession().persist(logoItem);
	    logoItems.add(logoItem);
	}

	getCurrentSession().persist(organisation);

	organisation.setLogoItems(logoItems);
	getCurrentSession().update(organisation);
    }

    protected String getData(String path) {
	String base64Data = null;
	try {
	    InputStream is = OrganisationDaoImpl.class.getResourceAsStream(path);
	    BufferedInputStream bis = new BufferedInputStream(is);
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[65536];
	    int l;
	    while ((l = bis.read(buffer)) > 0) {
		output.write(buffer, 0, l);
	    }
	    output.close();
	    byte[] data = output.toByteArray();
	    base64Data = Base64Utils.toBase64(data);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return base64Data;
    }

    @Override
    public void load(String orgName, String hostname, String subdomain, String addressLine1, String addressLine2,
	    String country, String postcode, String orgIdentifier) {

	if (getCurrentSession().get(Organisation.class, 1L) == null) {
	    Organisation org = new Organisation();
	    org.setCreatedDate(new Date());
	    org.setLastUpdatedDate(new Date());
	    org.setAppLunchDate(new Date());
	    org.setOrgName(orgName);
	    org.setSubdomain(subdomain);
	    org.setAdminEmail("larry@mailinator.com");
	    org.setPrayerRequestEmail("larry@mailinator.com");
	    org.setAddressLine1(addressLine1);
	    org.setAddressLine2(addressLine2);
	    org.setCountry(Country.find(country));
	    org.setPostcode(postcode);
	    org.setOrgIdentifier(orgIdentifier);
	    org.setOrganisationAccountStatus(OrganisationAccountStatus.ACTIVE);
	    addNewOrganisation(org);
	}
    }

    @Override
    public Organisation getOrganisation(long orgId) {
	return (Organisation) getCurrentSession().get(Organisation.class, orgId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Organisation> getOrganisations() {
	return getCurrentSession().createQuery("from Organisation org").list();
    }

    @Override
    public Organisation getOrganisationFromClientSessionId(String clientSessionId) {
	Query query = getCurrentSession()
		.createQuery("select o.organisation from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	Organisation org = (Organisation) query.uniqueResult();
	org.getLogoItems().size();
	return org;
    }

    @Override
    public long getOrganisationIdFromClientSessionId(String clientSessionId) {
	return ((Organisation) getOrganisationFromClientSessionId(clientSessionId)).getId();
    }

    @Override
    public Organisation enableOrgAccount(String orgIdentifier) {
	Query query = getCurrentSession().createQuery("from Organisation org where org.orgIdentifier = :orgIdentifier");
	query.setParameter("orgIdentifier", orgIdentifier);
	Organisation org = (Organisation) query.uniqueResult();
	if (org == null) {
	    throw new OrgNotFoundException();
	}
	org.setOrganisationAccountStatus(OrganisationAccountStatus.ACTIVE);
	return org;
    }

    @Override
    public void setAppLunchDate(String orgIdentifier) {
	Query query = getCurrentSession().createQuery("from Organisation org where org.orgIdentifier = :orgIdentifier");
	query.setParameter("orgIdentifier", orgIdentifier);
	Organisation org = (Organisation) query.uniqueResult();
	if (org == null) {
	    throw new OrgNotFoundException();
	}
	org.setAppLunchDate(new Date());
    }

    @Override
    public Organisation getOrganisationFromOrgIdentifier(String orgIdentifier) {
	Query query = getCurrentSession().createQuery("from Organisation org where org.orgIdentifier = :orgIdentifier");
	query.setParameter("orgIdentifier", orgIdentifier);
	return (Organisation) query.uniqueResult();
    }

    @Override
    public void updatePastorDeskMessage(String clientSessionId, String message) {
	Organisation org = getOrganisationFromClientSessionId(clientSessionId);
	org.setMessageFromPastorsDesk(message);
    }

    @Override
    public void updateChurchAppLabel(String clientSessionId, ChurchAppTopicEnum churchAppTopicEnum, String value) {
	Organisation org = getOrganisationFromClientSessionId(clientSessionId);

	if (ChurchAppTopicEnum.ABOUT_US.equals(churchAppTopicEnum)) {
	    org.setAboutUsChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.DONATION.equals(churchAppTopicEnum)) {
	    org.setDonationChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(churchAppTopicEnum)) {
	    org.setPrayerRequestChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.FACEBOOK.equals(churchAppTopicEnum)) {
	    org.setFacebookChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.HOME.equals(churchAppTopicEnum)) {

	} else if (ChurchAppTopicEnum.LISTEN.equals(churchAppTopicEnum)) {
	    org.setListenChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(churchAppTopicEnum)) {
	    org.setNoticesAndEventsChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.PASTORS_DESK.equals(churchAppTopicEnum)) {
	    org.setPastorDeskChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.TWITTER.equals(churchAppTopicEnum)) {
	    org.setTwitterChurchAppTopic(value);

	} else if (ChurchAppTopicEnum.YOUTUBE.equals(churchAppTopicEnum)) {
	    org.setYoutubeChurchAppTopic(value);

	}
    }

    @Override
    public String getNewsTypeChurchAppLabel(ChurchAppTopicEnum churchAppTopicEnum) {
	Organisation org = getOrganisation(1L);

	if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(churchAppTopicEnum)) {
	    return org.getNoticesAndEventsChurchAppTopic();

	}
	return null;
    }

    @Override
    public void updateChurchAppLabelFlagShow(String clientSessionId, ChurchAppTopicEnum churchAppTopicEnum,
	    boolean value) {
	Organisation org = getOrganisationFromClientSessionId(clientSessionId);

	if (ChurchAppTopicEnum.ABOUT_US.equals(churchAppTopicEnum)) {
	    org.setAboutUsChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.DONATION.equals(churchAppTopicEnum)) {
	    org.setDonationChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.FACEBOOK.equals(churchAppTopicEnum)) {
	    org.setFacebookChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(churchAppTopicEnum)) {
	    org.setPrayerRequestChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.HOME.equals(churchAppTopicEnum)) {

	} else if (ChurchAppTopicEnum.LISTEN.equals(churchAppTopicEnum)) {
	    org.setListenChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.NOTICES_AND_EVENTS.equals(churchAppTopicEnum)) {
	    org.setMessagesChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.PASTORS_DESK.equals(churchAppTopicEnum)) {
	    org.setPastorDeskChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.TWITTER.equals(churchAppTopicEnum)) {
	    org.setTwitterChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.YOUTUBE.equals(churchAppTopicEnum)) {
	    org.setYoutubeChurchAppTopicFlag(value);

	} else if (ChurchAppTopicEnum.PRAYER_REQUEST.equals(churchAppTopicEnum)) {
	    org.setPrayerRequestChurchAppTopicFlag(value);

	}

    }

    @Override
    public void updateAboutUseDetails(String clientSessionId, String orgName, String adminEmailAddress,
	    String prayerRequestEmailAddress, String aboutUsMessage, String aboutPastorMessage, String serviceTimes,
	    String adressLine1, String adressLine2, String postcode, Country country, String websiteUrl) {
	Organisation org = getOrganisationFromClientSessionId(clientSessionId);
	org.setAdminEmail(adminEmailAddress);
	org.setPrayerRequestEmail(prayerRequestEmailAddress);
	org.setAboutUsMessage(aboutUsMessage);
	org.setAboutPastorMessage(aboutPastorMessage);
	org.setServiceTimes(serviceTimes);
	org.setAddressLine1(adressLine1);
	org.setAddressLine2(adressLine2);
	org.setPostcode(postcode);
	org.setCountry(country);
	org.setOrgName(orgName);
	org.setWebsiteUrl(websiteUrl);
    }

    @Override
    public void updateAboutUseGoogleMapLocationUrlDetails(String googleApiUrl) {
	Organisation org = getOrganisation(1L);
	org.setGoogleApiUrl(googleApiUrl);
    }
}
