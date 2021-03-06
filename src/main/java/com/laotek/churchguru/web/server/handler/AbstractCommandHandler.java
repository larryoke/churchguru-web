package com.laotek.churchguru.web.server.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.MediaMember;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessageSpeaker;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.YoutubeVideo;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeOrEventDto;
import com.laotek.churchguru.web.client.activity.donation.DonationDto;
import com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventDto;
import com.laotek.churchguru.web.server.UserRoleHelper;
import com.laotek.churchguru.web.shared.FullnameDto;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.PhoneDto;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

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
	    dto.setDonationTransactionStatus(donation.getDonationTransactionStatus());
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

    protected List<MediaMessageSpeakerDto> mapSpeakers(List<MediaMessageSpeaker> speakers) {
	List<MediaMessageSpeakerDto> dtos = new ArrayList<MediaMessageSpeakerDto>();
	for (MediaMessageSpeaker speaker : speakers) {
	    MediaMessageSpeakerDto dto = new MediaMessageSpeakerDto();
	    dto.setIdentifier(speaker.getIdentifier());
	    dto.setDescription(speaker.getDescription());
	    dto.setPictureURL(speaker.getPictureUrl());

	    FullnameDto fullnameDto = new FullnameDto();
	    fullnameDto.setTitle(speaker.getTitle());
	    fullnameDto.setForenames(speaker.getForenames());
	    fullnameDto.setSurname(speaker.getSurname());
	    dto.setFullnameDto(fullnameDto);
	    dtos.add(dto);
	}
	return dtos;
    }

    protected List<MediaMessageCategoryDto> mapCategories(List<MediaMessageCategory> categories) {
	List<MediaMessageCategoryDto> dtos = new ArrayList<MediaMessageCategoryDto>();
	for (MediaMessageCategory category : categories) {
	    MediaMessageCategoryDto dto = new MediaMessageCategoryDto();
	    dto.setName(category.getCategoryName());
	    dto.setIdentifier(category.getIdentifier());
	    dtos.add(dto);
	}
	return dtos;
    }

    protected MediaMessageDto mapMessages(MediaMessage message) {
	MediaMessageDto dto = new MediaMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	dto.setLocation(message.getLocation());
	dto.setStatus(message.getMessageStatus());
	dto.setSalePoints(message.getSalePointPerMessage());
	dto.setMessageDate(message.getMessageDate());
	dto.setDescriptionPictureURL(message.getDescPictureUrl());
	dto.setMediaMessageUrl(message.getMediaMessageUrl());
	dto.setMessageDateAsString(new SimpleDateFormat("dd-MM-yyyy").format(message.getMessageDate()));

	MediaMessageSpeaker speaker = message.getEStoreSpeaker();
	if (speaker != null) {
	    MediaMessageSpeakerDto speakerDto = new MediaMessageSpeakerDto();
	    speakerDto.setDescription(speaker.getDescription());
	    speakerDto.setPictureURL(speaker.getPictureUrl());

	    FullnameDto fullnameDto = new FullnameDto();
	    fullnameDto.setTitle(speaker.getTitle());
	    fullnameDto.setForenames(speaker.getForenames());
	    fullnameDto.setSurname(speaker.getSurname());

	    speakerDto.setIdentifier(speaker.getIdentifier());
	    speakerDto.setFullnameDto(fullnameDto);
	    dto.setSpeakerDto(speakerDto);
	}

	MediaMessageCategory category = message.geteStoreCategory();
	if (category != null) {
	    MediaMessageCategoryDto categoryDto = new MediaMessageCategoryDto();
	    categoryDto.setIdentifier(category.getIdentifier());
	    categoryDto.setName(category.getCategoryName());
	    dto.setCategoryDto(categoryDto);
	}
	return dto;
    }

    protected YoutubeVideoDto mapYoutubeVideos(YoutubeVideo message) {
	YoutubeVideoDto dto = new YoutubeVideoDto();
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	dto.setLocation(message.getLocation());
	dto.setMessageDate(message.getMessageDate());
	dto.setSpeakers(message.getSpeakers());
	dto.setYoutubeUrl(message.getYoutubeUrl());
	dto.setMessageDateAsString(new SimpleDateFormat("dd-MM-yyyy").format(message.getMessageDate()));
	return dto;
    }

    protected List<com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventDto> mapMessageTitles(
	    List<NoticeAndEvent> instantMessages) {
	List<com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventDto> messages = new ArrayList<com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventDto>();
	for (NoticeAndEvent letter : instantMessages) {
	    messages.add(mapMessageTitle(letter));
	}
	return messages;
    }

    protected NoticeAndEventDto mapMessageTitle(NoticeAndEvent emailNewsLetter) {
	NoticeAndEventDto dto = new NoticeAndEventDto();
	dto.setTitle(emailNewsLetter.getSubject());
	dto.setCreatedTimeDesc(formatCreatedTimeDesc(emailNewsLetter.getCreatedDate()));
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
	dto.setNoticesAndEventsChurchAppTopic(org.getNoticesAndEventsChurchAppTopic());
	dto.setPastorDeskChurchAppTopic(org.getPastorDeskChurchAppTopic());
	dto.setTwitterChurchAppTopic(org.getTwitterChurchAppTopic());
	dto.setYoutubeChurchAppTopic(org.getYoutubeChurchAppTopic());
	dto.setAboutUsChurchAppTopic(org.getAboutUsChurchAppTopic());
	dto.setPrayerRequestChurchAppTopic(org.getPrayerRequestChurchAppTopic());

	dto.setDonationChurchAppTopicFlag(org.isDonationChurchAppTopicFlag());
	dto.setFacebookChurchAppTopicFlag(org.isFacebookChurchAppTopicFlag());
	dto.setListenChurchAppTopicFlag(org.isListenChurchAppTopicFlag());
	dto.setMessagesChurchAppTopicFlag(org.isMessagesChurchAppTopicFlag());
	dto.setPastorDeskChurchAppTopicFlag(org.isPastorDeskChurchAppTopicFlag());
	dto.setTwitterChurchAppTopicFlag(org.isTwitterChurchAppTopicFlag());
	dto.setYoutubeChurchAppTopicFlag(org.isYoutubeChurchAppTopicFlag());
	dto.setAboutUsChurchAppTopicFlag(org.isAboutUsChurchAppTopicFlag());
	dto.setPrayerRequestChurchAppTopicFlag(org.isPrayerRequestChurchAppTopicFlag());

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

	dto.setGoogleApiKey(org.getGoogleApiKey());
	dto.setLatitude(org.getLatitude());
	dto.setLongitude(org.getLongitude());

	dto.setFacebookUrl(org.getFacebookTimelineUrl());
	dto.setTwitterTimelineCode(org.getTwitterTimelineCode());
	dto.setYoutubePlaylistUrl(org.getYoutubePlaylistUrl());

	LogoItem logoItem = getChurchLogo(org);
	dto.setHasLogo(logoItem != null && !StringUtils.isEmpty(logoItem.getBase64Data()));
	return dto;
    }

    protected List<MediaMessageCategoryDto> mapCategoryDto(List<MediaMember> categories) {
	List<MediaMessageCategoryDto> categoryDtos = new ArrayList<MediaMessageCategoryDto>();
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
	    dto.setMobile(new PhoneDto(user.getMobileCountryCode(), user.getMobile()));
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
