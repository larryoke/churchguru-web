package com.laotek.churchguru.web.server.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.laotek.churchguru.model.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.model.ListeningSpeaker;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.NoticeOrEventDto;
import com.laotek.churchguru.web.client.activity.donation.DonationDto;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventDto;
import com.laotek.churchguru.web.server.UserRoleHelper;
import com.laotek.churchguru.web.shared.FullnameDto;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.PhoneDto;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.ListeningNotificationDto;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public abstract class AbstractCommandHandler {

    protected String createRandom() {
	return RandomStringUtils.random(20, true, true);
    }

    protected List<DonationDto> getDonations(List<Donation> donations) {
	List<DonationDto> dtos = new ArrayList<DonationDto>();
	for (Donation donation : donations) {
	    DonationDto dto = new DonationDto();
	    FullnameDto fullnameDto = new FullnameDto();
	    fullnameDto.setForenames(donation.getForenames());
	    fullnameDto.setSurname(donation.getSurname());
	    fullnameDto.setTitle(donation.getTitle());
	    dto.setFullnameDto(fullnameDto);

	    dto.setOffering(donation.getOffering());
	    dto.setTithe(donation.getTithe());
	    dto.setThanksGiving(donation.getThanksGiving());
	    dto.setBuildingFund(donation.getBuildingFund());
	    dto.setSpecialOffering(donation.getSpecialOffering());
	    dto.setOtherOffering(donation.getOtherOffering());
	    dto.setOtherSpecified(donation.getOtherGivingType());
	    dto.setAmountTotal(donation.getAmountTotal());

	    StringBuffer sb = new StringBuffer();
	    sb.append(donation.getAddressLine1());
	    sb.append(", ");
	    sb.append(donation.getAddressLine2());
	    sb.append(", ");
	    sb.append(donation.getPostcode());
	    dto.setFullAddress(sb.toString());
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:mm");
	    dto.setDonationDateAsString(sdf.format(donation.getCreatedDate()));
	    dto.setDonationTransactionStatus(donation
		    .getDonationTransactionStatus());
	    dto.setEmailAddress(donation.getEmailAddress());
	    dto.setMember(donation.isMember());
	    dto.setInMailingList(donation.isInMailingList());

	    dto.setGiftAidToday(donation.isGiftAidToday());
	    dto.setGiftAidPast4Years(donation.isGiftAidPast4Years());
	    dto.setGiftAidFuture(donation.isGiftAidFuture());

	    dtos.add(dto);
	}
	return dtos;
    }

    protected List<ListeningSpeakerDto> mapSpeakers(List<ListeningSpeaker> speakers) {
	List<ListeningSpeakerDto> dtos = new ArrayList<ListeningSpeakerDto>();
	for (ListeningSpeaker speaker : speakers) {
	    ListeningSpeakerDto dto = new ListeningSpeakerDto();
	    dto.setIdentifier(speaker.getIdentifier());
	    dto.setDescription(speaker.getDescription());

	    FullnameDto fullnameDto = new FullnameDto();
	    fullnameDto.setTitle(speaker.getTitle());
	    fullnameDto.setForenames(speaker.getForenames());
	    fullnameDto.setSurname(speaker.getSurname());
	    dto.setFullnameDto(fullnameDto);
	    dtos.add(dto);
	}
	return dtos;
    }

    protected List<ListeningMessagePictureDto> mapMessagePictures(
	    List<ListeningMessagePicture> eStoreMessagePictures) {
	List<ListeningMessagePictureDto> dtos = new ArrayList<ListeningMessagePictureDto>();
	for (ListeningMessagePicture picture : eStoreMessagePictures) {
	    ListeningMessagePictureDto dto = new ListeningMessagePictureDto();
	    dto.setName(picture.getPictureName());
	    dtos.add(dto);
	}
	return dtos;

    }

    protected List<ListeningCategoryDto> mapCategories(
	    List<ListeningCategory> categories) {
	List<ListeningCategoryDto> dtos = new ArrayList<ListeningCategoryDto>();
	for (ListeningCategory category : categories) {
	    ListeningCategoryDto dto = new ListeningCategoryDto();
	    dto.setName(category.getCategoryName());
	    dto.setIdentifier(category.getIdentifier());
	    dtos.add(dto);
	}
	return dtos;
    }

    protected ListeningMessageDto mapMessages(ListeningMessage message) {
	ListeningMessageDto dto = new ListeningMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	dto.setLocation(message.getLocation());
	dto.setSalePoints(message.getSalePointPerMessage());
	dto.setMessageDate(message.getMessageDate());
	dto.setMessageDateAsString(new SimpleDateFormat("dd-MM-yyyy")
		.format(message.getMessageDate()));

	ListeningSpeaker speaker = message.getEStoreSpeaker();
	if (speaker != null) {
	    ListeningSpeakerDto speakerDto = new ListeningSpeakerDto();
	    speakerDto.setDescription(speaker.getDescription());

	    FullnameDto fullnameDto = new FullnameDto();
	    fullnameDto.setTitle(speaker.getTitle());
	    fullnameDto.setForenames(speaker.getForenames());
	    fullnameDto.setSurname(speaker.getSurname());

	    speakerDto.setIdentifier(speaker.getIdentifier());
	    speakerDto.setFullnameDto(fullnameDto);
	    dto.setSpeakerDto(speakerDto);
	}

	ListeningCategory category = message.geteStoreCategory();
	if (category != null) {
	    ListeningCategoryDto categoryDto = new ListeningCategoryDto();
	    categoryDto.setIdentifier(category.getIdentifier());
	    categoryDto.setName(category.getCategoryName());
	    dto.setCategoryDto(categoryDto);
	}

	ListeningMessagePicture picture = message.geteStoreMessagePicture();
	if (picture != null) {
	    ListeningMessagePictureDto pictureDto = new ListeningMessagePictureDto();
	    pictureDto.setName(picture.getPictureName());
	    pictureDto.setIdentifier(picture.getIdentifier());
	}

	Set<ListeningMessageNotification> messageNotifications = message
		.getEStoreMessageNotifications();
	if (messageNotifications != null && messageNotifications.size() > 0) {
	    List<ListeningNotificationDto> dtos = map(messageNotifications);
	    dto.setNotificationDtos(dtos);
	}
	return dto;
    }

    protected List<ListeningNotificationDto> map(
	    Set<ListeningMessageNotification> messageNotifications) {
	List<ListeningNotificationDto> dtos = new ArrayList<ListeningNotificationDto>();
	for (ListeningMessageNotification notification : messageNotifications) {
	    dtos.add(new ListeningNotificationDto(notification
		    .getEStoreNotification().getEStoreNotificationType()));
	}
	return dtos;
    }

    protected List<com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventDto> mapMessageTitles(
	    List<NoticeAndEvent> instantMessages) {
	List<com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventDto> messages = new ArrayList<com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventDto>();
	for (NoticeAndEvent letter : instantMessages) {
	    messages.add(mapMessageTitle(letter));
	}
	return messages;
    }

    protected NoticeAndEventDto mapMessageTitle(NoticeAndEvent emailNewsLetter) {
	NoticeAndEventDto dto = new NoticeAndEventDto();
	dto.setTitle(emailNewsLetter.getSubject());
	dto.setCreatedTimeDesc(formatCreatedTimeDesc(emailNewsLetter
		.getCreatedDate()));
	dto.setId(emailNewsLetter.getId());
	if (emailNewsLetter.getEventDate() != null) {
	    dto.setEventDate(formatDateAndTime(emailNewsLetter.getEventDate()));
	}
	return dto;
    }

    protected NoticeOrEventDto mapNoticeOrEvent(NoticeAndEvent ne) {
	NoticeOrEventDto dto = new NoticeOrEventDto();
	dto.setBody(ne.getMessageBody());
	dto.setSubject(ne.getSubject());
	dto.setCreatedTimeDesc(formatCreatedTimeDesc(ne.getCreatedDate()));
	dto.setId(ne.getId());
	if (ne.getEventDate() != null) {
	    dto.setEventDateAsStr(formatDateAndTime(ne.getEventDate()));
	}
	dto.setEventDate(ne.getEventDate());
	dto.setEventTime(ne.getEventTime());
	dto.setIdentifier(ne.getIdentifier());
	dto.setHasPicture(ne.getBase64Data() != null);
	dto.setMessageType(ne.getMessageType());
	return dto;
    }

    protected OrganisationDto map(Organisation org) {
	OrganisationDto dto = new OrganisationDto();
	dto.setDonationChurchAppTopic(org.getDonationChurchAppTopic());
	dto.setFacebookChurchAppTopic(org.getFacebookChurchAppTopic());
	dto.setListenChurchAppTopic(org.getListenChurchAppTopic());
	dto.setNoticesAndEventsChurchAppTopic(org
		.getNoticesAndEventsChurchAppTopic());
	dto.setPastorDeskChurchAppTopic(org.getPastorDeskChurchAppTopic());
	dto.setTwitterChurchAppTopic(org.getTwitterChurchAppTopic());
	dto.setWatchChurchAppTopic(org.getWatchChurchAppTopic());
	dto.setAboutUsChurchAppTopic(org.getAboutUsChurchAppTopic());
	dto.setPrayerRequestChurchAppTopic(org.getPrayerRequestChurchAppTopic());

	dto.setDonationChurchAppTopicFlag(org.isDonationChurchAppTopicFlag());
	dto.setFacebookChurchAppTopicFlag(org.isFacebookChurchAppTopicFlag());
	dto.setListenChurchAppTopicFlag(org.isListenChurchAppTopicFlag());
	dto.setMessagesChurchAppTopicFlag(org.isMessagesChurchAppTopicFlag());
	dto.setPastorDeskChurchAppTopicFlag(org
		.isPastorDeskChurchAppTopicFlag());
	dto.setTwitterChurchAppTopicFlag(org.isTwitterChurchAppTopicFlag());
	dto.setWatchChurchAppTopicFlag(org.isWatchChurchAppTopicFlag());
	dto.setAboutUsChurchAppTopicFlag(org.isAboutUsChurchAppTopicFlag());
	dto.setPrayerRequestChurchAppTopicFlag(org
		.isPrayerRequestChurchAppTopicFlag());

	dto.setAboutUsMessage(org.getAboutUsMessage());
	dto.setAboutPastorMessage(org.getAboutPastorMessage());
	dto.setOrgDomain(org.getSubdomain());
	dto.setOrgIdentifier(org.getOrgIdentifier());
	dto.setOrgName(org.getOrgName());
	dto.setAdminEmail(org.getAdminEmail());
	dto.setPrayerRequestEmail(org.getPrayerRequestEmail());
	dto.setAppLunchDate(formatDate(org.getAppLunchDate()));
	dto.setPastorDeskMessage(org.getMessageFromPastorsDesk());
	dto.setAddressLine1(org.getAddressLine1());
	dto.setAddressLine2(org.getAddressLine2());
	dto.setPostcode(org.getPostcode());
	dto.setServiceTimes(org.getServiceTimes());
	dto.setWebsiteUrl(org.getWebsiteUrl());

	LogoItem logoItem = getChurchLogo(org);
	dto.setHasLogo(logoItem != null
		&& !StringUtils.isEmpty(logoItem.getBase64Data()));
	return dto;
    }

    protected List<ListeningCategoryDto> mapCategoryDto(
	    List<ListeningMember> categories) {
	List<ListeningCategoryDto> categoryDtos = new ArrayList<ListeningCategoryDto>();
	return categoryDtos;
    }

    protected List<UserDto> convert(List<User> users) {
	List<UserDto> dtos = new ArrayList<UserDto>();
	for (User user : users) {
	    UserDto dto = new UserDto();
	    dto.setId(user.getId());
	    dto.setUsername(user.getUsername());
	    dto.setEmailAddress(user.getEmailAddress());
	    dto.setForenames(user.getForenames());
	    dto.setSurname(user.getSurname());
	    dto.setMobile(new PhoneDto(user.getMobileCountryCode(), user
		    .getMobile()));
	    dto.setUserAccountStatus(user.getUserAccountStatus());
	    dto.setIdentifier(user.getIdentifier());
	    dtos.add(dto);

	    UserRoleHelper.populateDto(user, dto);

	}
	return dtos;
    }

    protected static String formatDate(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	return sdf.format(date);
    }

    protected static String formatDateAndTime(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm");
	return sdf.format(date);
    }

    private LogoItem getChurchLogo(Organisation org) {
	for (LogoItem li : org.getLogoItems()) {
	    if (LogoItemType.CHURCH_LOGO.equals(li.getLogoItemType())) {
		return li;
	    }
	}
	return null;
    }

    protected String formatCreatedTimeDesc(Date createdTime) {

	Calendar cal = Calendar.getInstance();

	for (int i = 1; i < 60; ++i) {
	    cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, -i);
	    if (createdTime.after(cal.getTime())) {
		return String.format(" %sm", i);
	    }
	}

	for (int i = 1; i < 24; ++i) {
	    cal = Calendar.getInstance();
	    cal.add(Calendar.HOUR, -i);
	    if (createdTime.after(cal.getTime())) {
		return String.format(" %sh", i);
	    }
	}

	for (int i = 1; i < 365; ++i) {
	    cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -i);
	    if (createdTime.after(cal.getTime())) {
		return String.format(" %sd", i);
	    }
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
	return sdf.format(createdTime);
    }
}