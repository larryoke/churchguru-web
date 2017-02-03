package com.laotek.churchguru.daos.photos;

import java.util.Date;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.GalleryItem;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.MemberPhoto;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.shared.enums.LogoItemType;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PhotoDaoImpl extends BaseSessionFactory implements PhotoDao {

    @Override
    public long saveMemberPhoto(String base64Data, String filename, String contentType) {
	MemberPhoto memberPhoto = new MemberPhoto();
	memberPhoto.setContentType(contentType);
	memberPhoto.setBase64Data(base64Data);
	memberPhoto.setFilename(filename);
	memberPhoto.setCreatedDate(new Date());
	memberPhoto.setLastUpdatedDate(new Date());
	return (Long) getCurrentSession().save(memberPhoto);
    }

    @Override
    public MemberPhoto getMemberPhoto(long id) {
	MemberPhoto memberPhoto = (MemberPhoto) getCurrentSession().get(MemberPhoto.class, id);
	return memberPhoto;
    }

    @Override
    public long saveGalleryItem(String base64Data, String filename, String contentType) {
	GalleryItem item = new GalleryItem();
	item.setContentType(contentType);
	item.setBase64Data(base64Data);
	item.setFilename(filename);
	item.setCreatedDate(new Date());
	item.setLastUpdatedDate(new Date());
	return (Long) getCurrentSession().save(item);
    }

    @Override
    public GalleryItem getGalleryItem(long id) {
	GalleryItem galleryItem = (GalleryItem) getCurrentSession().get(GalleryItem.class, id);
	return galleryItem;
    }

    @Override
    public void saveOrganisationLogo(String clientSessionId, String base64Data, String filename, String contentType) {
	LogoItemType logoItemType = LogoItemType.CHURCH_LOGO;
	saveImage(clientSessionId, base64Data, filename, contentType, logoItemType);
    }

    // @Override
    // public void saveChurchAppProfilePicture(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.CHURCH_APP_PROFILE_PIC;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationPastorDeskPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.PASTORS_DESK_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationOtherNewsPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.NEWS_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveDonationPhoto(String clientSessionId, String base64Data,
    // String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.GIVE_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationAboutUsPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.ABOUT_US_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationConfCallPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.CONF_CALL_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationEventPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.EVENTS_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisation_atchPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.WATCH;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationListenPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.LISTEN;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationFacebookPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.FACEBOOK_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }
    //
    // @Override
    // public void saveOrganisationTwitterPhoto(String clientSessionId,
    // String base64Data, String filename, String contentType) {
    // LogoItemType logoItemType = LogoItemType.TWITTER_PHOTO;
    // saveImage(clientSessionId, base64Data, filename, contentType,
    // logoItemType);
    // }

    private void saveImage(String clientSessionId, String base64Data, String filename, String contentType,
	    LogoItemType logoItemType) {
	Query query = getCurrentSession()
		.createQuery("select o.organisation from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	Organisation org = (Organisation) query.uniqueResult();

	// org
	Set<LogoItem> logoItems = org.getLogoItems();
	for (LogoItem li : logoItems) {
	    if (logoItemType.equals(li.getLogoItemType())) {
		li.setBase64Data(base64Data);
		li.setContentType(contentType);
		li.setFilename(filename);
		li.setLastUpdatedDate(new Date());
		return;
	    }
	}

	// logo
	LogoItem logoItem = new LogoItem();
	logoItem.setLogoItemType(logoItemType);
	logoItem.setBase64Data(base64Data);
	logoItem.setContentType(contentType);
	logoItem.setFilename(filename);
	getCurrentSession().saveOrUpdate(logoItem);

	logoItems.add(logoItem);
	getCurrentSession().update(org);
    }

    @Override
    public LogoItem getLogoItem(String clientSessionId) {
	Query query = getCurrentSession()
		.createQuery("select o.organisation from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	Organisation org = (Organisation) query.uniqueResult();
	for (LogoItem li : org.getLogoItems()) {
	    if (LogoItemType.CHURCH_LOGO.equals(li.getLogoItemType())) {
		return li;
	    }
	}
	return null;
    }

    @Override
    public LogoItem getPhoto(LogoItemType type) {
	Organisation org = (Organisation) getCurrentSession().get(Organisation.class, 1L);
	for (LogoItem li : org.getLogoItems()) {
	    if (type.equals(li.getLogoItemType())) {
		return li;
	    }
	}
	return null;
    }

    @Override
    public void savePhoto(LogoItemType logoItemType, String clientSessionId, String base64Data, String filename,
	    String contentType) {
	saveImage(clientSessionId, base64Data, filename, contentType, logoItemType);
    }
}
