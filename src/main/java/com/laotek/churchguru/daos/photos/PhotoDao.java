package com.laotek.churchguru.daos.photos;

import com.laotek.churchguru.model.GalleryItem;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.MemberPhoto;
import com.laotek.churchguru.model.shared.enums.LogoItemType;

public interface PhotoDao {
    long saveMemberPhoto(String base64Data, String filename, String contentType);

    long saveGalleryItem(String base64Data, String filename, String contentType);

    MemberPhoto getMemberPhoto(long id);

    GalleryItem getGalleryItem(long id);

    void saveOrganisationLogo(String clientSessionId, String base64Data, String filename, String contentType);

    void savePhoto(LogoItemType logoItemType, String clientSessionId, String base64Data, String filename,
	    String contentType);

    //
    // void saveChurchAppProfilePicture(String clientSessionId, String
    // base64Data,
    // String filename, String contentType);
    //
    // void saveOrganisationPastorDeskPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);
    //
    // void saveOrganisationOtherNewsPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);
    //
    // void saveDonationPhoto(String clientSessionId, String base64Data,
    // String filename, String contentType);
    //
    // void saveOrganisationAboutUsPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);
    //
    // void saveOrganisationConfCallPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);
    //
    // void saveOrganisationEventPhoto(String clientSessionId, String
    // base64Data,
    // String filename, String contentType);
    //
    // void saveOrganisation_atchPhoto(String clientSessionId, String
    // base64Data,
    // String filename, String contentType);

    // void saveOrganisationListenPhoto(String clientSessionId, String
    // base64Data,
    // String filename, String contentType);
    //
    // void saveOrganisationFacebookPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);

    // void saveOrganisationTwitterPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType);

    LogoItem getLogoItem(String clientSessionId);

    LogoItem getPhoto(LogoItemType type);
}
