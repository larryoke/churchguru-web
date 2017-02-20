package com.laotek.churchguru.daos.org;

import java.math.BigDecimal;
import java.util.List;

import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;

public interface OrganisationDao {

    void addNewOrganisation(Organisation organisation);

    Organisation getOrganisation(long orgId);

    Organisation getOrganisationFromOrgIdentifier(String orgIdentifier);

    Organisation getOrganisationFromClientSessionId(String clientSessionId);

    void updatePastorDeskMessage(String clientSessionId, String message);

    void updateAboutUseDetails(String clientSessionId, String orgName, String adminEmailAddress,
	    String prayerRequestEmailAddress, String aboutUsMessage, String aboutPastorMessage, String serviceTimes,
	    String adressLine1, String adressLine2, String postcode, Country country, String websiteUrl,
	    String googleApiKey, BigDecimal latitude, BigDecimal longitute);

    void updateAboutUseGoogleMapLocationUrlDetails(String googleApiUrl);

    void updateChurchAppLabel(String clientSessionId, ChurchAppTopicEnum churchAppTopicEnum, String value);

    void updateChurchAppLabelFlagShow(String clientSessionId, ChurchAppTopicEnum churchAppTopicEnum, boolean value);

    long getOrganisationIdFromClientSessionId(String clientSessionId);

    List<Organisation> getOrganisations();

    void load(String orgName, String hostname, String subdomain, String addressLine1, String addressLine2,
	    String country, String postcode, String orgIdentifier);

    Organisation enableOrgAccount(String orgIdentifier);

    void setAppLunchDate(String orgIdentifier);

    // for changing the label on the topics of main mobile screen
    @Deprecated
    String getNewsTypeChurchAppLabel(ChurchAppTopicEnum churchAppTopicEnum);
}
