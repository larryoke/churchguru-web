package com.laotek.churchguru.daos.send;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.ForgotCredentialsEmail;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.NewUserSetupEmail;
import com.laotek.churchguru.model.NotificationEmail;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.PrayerRequest;
import com.laotek.churchguru.model.QuickEmail;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserFeedback;
import com.laotek.churchguru.model.shared.enums.DonationThankyouEmailSendStatus;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.model.shared.enums.FeedbackMessageType;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class EmailProcessorDaoImpl extends AbstractBaseMessageProcessorDaoImpl
	implements EmailProcessorDao {

    static Logger log = LoggerFactory.getLogger(EmailProcessorDaoImpl.class);

    @Autowired
    @Value("${mail.sender.username}")
    private String mailSenderUsername;

    @Autowired
    @Value("${mail.newsletter.username}")
    private String mailNewsletterUsername;

    @Autowired
    @Value("${mail.host}")
    private String mailHost;

    @Autowired
    @Value("${hostname}")
    private String hostName;

    @Override
    public void processMessage(EmailProcess emailProcess) {
	// TODO Auto-generated method stub

    }

    @Override
    public void processMessage(EmailProcess emailProcess, Organisation org) {

	log.debug("processMessage->");

	String contextPathName = "communicator";

	Organisation organisation = (Organisation) getSessionFactory()
		.getCurrentSession().merge(org);

	// Quick Email
	processQuickEmailSend(emailProcess, organisation);

	// processMemberEmailNewsLetterSend(emailProcess,
	// getNewsletterAddress(),
	// organisation);

	// User feedback
	processUserFeedbackSend(emailProcess, organisation);

	// prayer request
	processPrayerRequest(emailProcess, organisation);

	// processGuestEmailNewsLetterSend(emailProcess, getNewsletterAddress(),
	// organisation);

	processForgotCredentialsEmailSend(emailProcess, getMailSenderAddress(),
		organisation, hostName, contextPathName);

	processNewUserSetupEmailSend(emailProcess, organisation, hostName,
		contextPathName);

	// processMemberInvitationSend(emailProcess, organisation,
	// getMailSenderAddress(), hostName, contextPathName);

	// processMemberInvitationReplyNotificationSend(emailProcess,
	// organisation, getMailSenderAddress(), hostName, contextPathName);
	//
	// processMemberDetailsVerificationSend(emailProcess, organisation,
	// getMailSenderAddress(), hostName, contextPathName);

	processNotificationSend(emailProcess, organisation);

	processDonationThankyouEmailSend(emailProcess, organisation);

	log.debug("<-processMessage");

    }

    //

    @SuppressWarnings("unchecked")
    private void processForgotCredentialsEmailSend(EmailProcess emailProcess,
	    String mailSenderAddress, Organisation organisation,
	    String hostName, String contextPathName) {
	Query query = getCurrentSession()
		.createQuery(
			"from ForgotCredentialsEmail f where f.sender.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<ForgotCredentialsEmail> forgotCredentialsEmails = query.list();
	for (ForgotCredentialsEmail fce : forgotCredentialsEmails) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(fce.getToAddr());
		emailProcessTO.setRecipientFullname(fce.getFullname());
		emailProcessTO.setReplyToEmailAddress(organisation
			.getAdminEmail());

		emailProcessTO.setSenderFullname(organisation.getOrgName());
		emailProcessTO.setSubject(fce.getSubject());
		emailProcessTO.setOrgName(organisation.getOrgName());

		LogoItem logoData = getChurchLogo(organisation);
		if (logoData != null && logoData.getBase64Data() != null) {
		    emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
		    emailProcessTO
			    .setLogoContentType(logoData.getContentType());
		}

		emailProcess.sendForgottenCredentialEmail(emailProcessTO, fce
			.getRecipientIdentifier(), fce
			.getForgottenCredentialType(), fce.getSender()
			.getUsername(), hostName, contextPathName, fce
			.getPasswordResetIdentifier());
		getCurrentSession().delete(fce);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private void processPrayerRequest(EmailProcess emailProcess,
	    Organisation organisation) {
	Query query = getCurrentSession().createQuery("from PrayerRequest pr");
	List<PrayerRequest> requests = query.list();
	for (PrayerRequest prayerRequest : requests) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setMessage(prayerRequest.getMessage());
		emailProcessTO.setReplyToEmailAddress(prayerRequest
			.getEmailAddress());
		emailProcessTO.setRecipientEmailAddress(organisation
			.getPrayerRequestEmail());
		emailProcessTO.setSubject("Prayer Request from "
			+ prayerRequest.getForenames() + " "
			+ prayerRequest.getSurname());
		emailProcessTO.setSenderFullname(prayerRequest.getForenames()
			+ " " + prayerRequest.getSurname());

		LogoItem logoData = getChurchLogo(organisation);
		if (logoData != null && logoData.getBase64Data() != null) {
		    emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
		    emailProcessTO
			    .setLogoContentType(logoData.getContentType());
		}
		emailProcess.sendPrayerRequest(emailProcessTO,
			prayerRequest.getMobile());
		getCurrentSession().delete(prayerRequest);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private void processNewUserSetupEmailSend(EmailProcess emailProcess,
	    Organisation organisation, String hostName, String contextPathName) {
	Query query;
	query = getCurrentSession()
		.createQuery(
			"from NewUserSetupEmail n where n.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<NewUserSetupEmail> newUserSetupEmail = query.list();
	for (NewUserSetupEmail nus : newUserSetupEmail) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(nus.getToAddr());
		emailProcessTO.setRecipientFullname(nus.getFullname());
		emailProcessTO.setReplyToEmailAddress(organisation
			.getAdminEmail());
		emailProcessTO.setOrgName(nus.getOrganisation().getOrgName());

		emailProcessTO.setSenderFullname(organisation.getOrgName());
		emailProcessTO.setSubject(nus.getSubject());
		emailProcess
			.sendNewUserSetupEmail(emailProcessTO,
				nus.getRecipientIdentifier(), hostName,
				contextPathName);
		LogoItem logoData = getChurchLogo(organisation);
		if (logoData != null && logoData.getBase64Data() != null) {
		    emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
		    emailProcessTO
			    .setLogoContentType(logoData.getContentType());
		}
		getCurrentSession().delete(nus);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private void processQuickEmailSend(EmailProcess emailProcess,
	    Organisation organisation) {
	Query query = getCurrentSession().createQuery(
		"from QuickEmail qe where qe.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<QuickEmail> quickEmails = (List<QuickEmail>) query.list();
	for (QuickEmail quickEmail : quickEmails) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(quickEmail.getToAddr());
		emailProcessTO.setRecipientFullname(quickEmail.getFullname());
		emailProcessTO.setSenderFullname(quickEmail
			.getReplyToFullname());
		emailProcessTO.setReplyToEmailAddress(quickEmail.getReplyTo());
		emailProcessTO.setSubject(quickEmail.getSubject());

		String message = mailMergeMessage(quickEmail);
		emailProcessTO.setMessage(message);

		LogoItem logoData = getChurchLogo(organisation);
		if (logoData != null && logoData.getBase64Data() != null) {
		    emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
		    emailProcessTO
			    .setLogoContentType(logoData.getContentType());
		}
		emailProcess.sendQuickEmail(emailProcessTO,
			quickEmail.getRecipientIdentifier(),
			quickEmail.getRecipientType());

		getCurrentSession().delete(quickEmail);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private void processNotificationSend(EmailProcess emailProcess,
	    Organisation organisation) {
	Query query = getCurrentSession()
		.createQuery(
			"from NotificationEmail ne where ne.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<NotificationEmail> notificationEmail = (List<NotificationEmail>) query
		.list();
	for (NotificationEmail email : notificationEmail) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(email.getToAddr());
		emailProcessTO.setRecipientFullname(email.getFullname());
		emailProcessTO.setReplyToEmailAddress(getMailSenderAddress());
		emailProcessTO.setSenderFullname(email.getReplyToFullname());
		emailProcessTO.setSubject(email.getSubject());

		emailProcessTO.setMessage(email.getMessage());

		LogoItem logoData = getChurchLogo(organisation);
		if (logoData != null && logoData.getBase64Data() != null) {
		    emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
		    emailProcessTO
			    .setLogoContentType(logoData.getContentType());
		}
		emailProcess.sendNotification(emailProcessTO);

		getCurrentSession().delete(email);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private void processUserFeedbackSend(EmailProcess emailProcess,
	    Organisation organisation) {
	Query query = getCurrentSession()
		.createQuery(
			"from UserFeedback uf where uf.user.organisation = :organisation");
	query.setParameter("organisation", organisation);
	List<UserFeedback> feedbacks = (List<UserFeedback>) query.list();
	for (UserFeedback feedback : feedbacks) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(feedback.getToAddr());
		emailProcessTO.setRecipientFullname(feedback.getFullname());
		emailProcessTO.setReplyToEmailAddress(feedback.getToAddr());
		emailProcessTO.setSenderFullname(feedback.getFullname());
		emailProcessTO.setSubject(feedback.getType().getDesc());
		emailProcessTO.setMessage(feedback.getMessage());
		emailProcess.sendFeedback(emailProcessTO);

		getCurrentSession().delete(feedback);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public void saveUserFeedback(String to, User from,
	    FeedbackMessageType type, String message, boolean isSendCopyToSender) {
	UserFeedback feedback = new UserFeedback();
	feedback.setCreatedDate(new Date());
	feedback.setToAddr(to);
	feedback.setFromAddr(to);
	feedback.setFullname(from.getFullname());
	feedback.setUser(from);
	feedback.setType(type);
	feedback.setMessage(message);
	feedback.setCopySender(isSendCopyToSender);
	getCurrentSession().persist(feedback);

	if (isSendCopyToSender) {
	    feedback = new UserFeedback();
	    feedback.setCreatedDate(new Date());
	    feedback.setToAddr(from.getEmailAddress());
	    feedback.setFromAddr(to);
	    feedback.setFullname(from.getFullname());
	    feedback.setUser(from);
	    feedback.setType(type);
	    feedback.setMessage(message);
	    feedback.setCopySender(isSendCopyToSender);
	    getCurrentSession().persist(feedback);
	}
    }

    @Override
    public void processDonationThankyouEmailSend(EmailProcess emailProcess,
	    Organisation organisation) {
	for (Donation donation : getReadyToBesentDonations()) {
	    try {
		EmailProcessTO emailProcessTO = new EmailProcessTO(
			getMailSenderAddress());
		emailProcessTO.setRecipientEmailAddress(donation
			.getEmailAddress());
		emailProcessTO.setRecipientFullname(donation.getFullname());

		emailProcessTO.setSenderFullname(organisation.getOrgName());
		emailProcessTO.setSubject("Thank you!");
		emailProcessTO.setReplyToEmailAddress(organisation
			.getAdminEmail());
		emailProcessTO.setOrgName(organisation.getOrgName());
		emailProcessTO.setOrgAddress(organisation.getFullAddress());

		Map<DonationType, BigDecimal> donationTypes = new HashMap<DonationType, BigDecimal>();
		donationTypes
			.put(DonationType.OFFERING, donation.getOffering());
		donationTypes.put(DonationType.TITHE, donation.getTithe());
		donationTypes.put(DonationType.THANKSGIVING,
			donation.getThanksGiving());
		donationTypes.put(DonationType.SPECIAL_OFFERING,
			donation.getSpecialOffering());
		donationTypes.put(DonationType.BUILDING_FUND,
			donation.getBuildingFund());
		donationTypes.put(DonationType.OTHER,
			donation.getOtherOffering());

		emailProcess.sendDonationThankYou(emailProcessTO,
			donationTypes, donation.getAmountTotal());

		donation.setDonationThankyouEmailSendStatus(DonationThankyouEmailSendStatus.SENT);
		donation.setLastUpdatedDate(new Date());

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private List<Donation> getReadyToBesentDonations() {
	Query query = getCurrentSession()
		.createQuery(
			"from Donation d where d.donationThankyouEmailSendStatus = :donationThankyouEmailSendStatus");
	query.setParameter("donationThankyouEmailSendStatus",
		DonationThankyouEmailSendStatus.READY_TO_BE_SENT);
	List<Donation> donations = query.list();
	return donations;
    }

    private LogoItem getChurchLogo(Organisation org) {
	for (LogoItem li : org.getLogoItems()) {
	    if (LogoItemType.CHURCH_LOGO.equals(li.getLogoItemType())) {
		return li;
	    }
	}
	return null;
    }

    private String getMailSenderAddress() {
	return mailSenderUsername + "@" + mailHost;
    }

    private String getNewsletterAddress() {
	return mailNewsletterUsername + "@" + mailHost;
    }

    @Override
    public void saveQuickEmailMessageDept(String departmentIdentifier,
	    String replyTo, String replyToFullname,
	    EmailRecipientType emailRecipientType, String subject,
	    String message, Organisation organisation,
	    boolean isSendCopyToSender) {
	// TODO Auto-generated method stub

    }

    @Override
    public void replyMessageToMailSenderWithStandardMessage(
	    EmailProcess emailProcess, String from, String subject,
	    String message) {
	// TODO Auto-generated method stub

    }

    // @Override
    // public void saveQuickEmailMessage(String to, String replyTo,
    // String replyToFullname, String forename, String surname,
    // Address address, String recipientIdentifier,
    // EmailRecipientType emailRecipientType, String subject,
    // String message, Organisation organisation,
    // boolean isSendCopyToSender) {
    //
    // QuickEmail quickEmail = new QuickEmail();
    // quickEmail.setForename(forename);
    // quickEmail.setSurname(surname);
    // quickEmail.setAddress(address != null ? address.toString() : null);
    // quickEmail.setFromAddr(getMailSenderAddress());
    // quickEmail.setToAddr(to);
    // quickEmail.setReplyTo(replyTo);
    // quickEmail.setReplyToFullname(replyToFullname);
    // quickEmail.setSubject(subject);
    // quickEmail.setMessage(message);
    // quickEmail.setRecipientIdentifier(recipientIdentifier);
    // quickEmail.setRecipientType(emailRecipientType);
    // quickEmail.setCreatedDate(new Date());
    // quickEmail.setOrganisation(organisation);
    // getCurrentSession().persist(quickEmail);
    //
    // if (isSendCopyToSender) {
    // quickEmail = new QuickEmail();
    // quickEmail.setForename(forename);
    // quickEmail.setSurname(surname);
    // quickEmail.setAddress(address != null ? address.toString() : null);
    // quickEmail.setFromAddr(getMailSenderAddress());
    // quickEmail.setToAddr(replyTo != null ? replyTo
    // : getMailSenderAddress());
    // quickEmail.setReplyTo(replyTo);
    // quickEmail.setReplyToFullname(replyToFullname);
    // quickEmail.setSubject(subject);
    // quickEmail.setMessage(message);
    // quickEmail.setRecipientIdentifier(recipientIdentifier);
    // quickEmail.setRecipientType(emailRecipientType);
    // quickEmail.setCreatedDate(new Date());
    // quickEmail.setOrganisation(organisation);
    // getCurrentSession().persist(quickEmail);
    // }
    // }

    // @Override
    // public void saveMemberInvitationMessage(
    // MemberInvitationEmail memberInvitationEmail) {
    // getCurrentSession().persist(memberInvitationEmail);
    // }

    // @Override
    // public void saveMemberInvitationNotificationToUserSender(
    // String invitationIdentifier) {
    // Query query = getCurrentSession().createQuery(
    // "from MemberInvitation m where m.identifier = :identifier");
    // query.setParameter("identifier", invitationIdentifier);
    // MemberInvitation memberInvitation = (MemberInvitation) query
    // .uniqueResult();
    // MemberInvitationReplyToUserNotificationEmail email = new
    // MemberInvitationReplyToUserNotificationEmail();
    // email.setMemberInvitation(memberInvitation);
    // getCurrentSession().persist(email);
    // }
    //
    // @Override
    // public void saveMemberDetailsVerificationRequest(String message,
    // String memberIdentifier) {
    // Query query = getCurrentSession().createQuery(
    // "from MemberWorkingCopy m where m.identifier = :identifier");
    // query.setParameter("identifier", memberIdentifier);
    // MemberWorkingCopy memberWorkingCopy = (MemberWorkingCopy) query
    // .uniqueResult();
    //
    // MemberDetailsVerificationEmail email = new
    // MemberDetailsVerificationEmail();
    // email.setMemberWorkingCopy(memberWorkingCopy);
    // email.setMessageBody(message);
    // getCurrentSession().persist(email);
    // }
    //
    // @Override
    // public void saveMemberDetailsVerificationRequest(String message) {
    // Query query = getCurrentSession().createQuery(
    // "select m.identifier from Member m ");
    // List<String> identifiers = query.list();
    // for (String memberIdentifier : identifiers) {
    // saveMemberDetailsVerificationRequest(message, memberIdentifier);
    // }
    // }
    //
    // @Override
    // public void saveQuickEmailMessageDept(String departmentIdentifier,
    // String replyTo, String replyToFullname,
    // EmailRecipientType emailRecipientType, String subject,
    // String message, Organisation organisation,
    // boolean isSendCopyToSender) {
    // for (Member member : getDepartmentMembers(departmentIdentifier)) {
    // if (emailRecipientType
    // .equals(EmailRecipientType.DEPARTMENT_LEADERS)) {
    // for (MemberDepartment md : member.getMemberDepartments()) {
    // Department dept = md.getDepartment();
    // if (dept.getIdentifier().equals(departmentIdentifier)) {
    // if (md.getDepartmentMemberStatus().equals(
    // DepartmentMemberStatus.LEADER)
    // || md.getDepartmentMemberStatus().equals(
    // DepartmentMemberStatus.DEPUTY_LEADER)) {
    // saveQuickEmailMessage(replyTo, replyToFullname,
    // emailRecipientType, subject, message,
    // organisation, isSendCopyToSender, member);
    // }
    // break;
    // }
    // }
    // } else {
    // saveQuickEmailMessage(replyTo, replyToFullname,
    // emailRecipientType, subject, message, organisation,
    // isSendCopyToSender, member);
    // }
    // }
    // }
    //
    // private void saveQuickEmailMessage(String replyTo, String
    // replyToFullname,
    // EmailRecipientType emailRecipientType, String subject,
    // String message, Organisation organisation,
    // boolean isSendCopyToSender, Member member) {
    // String recipientIdentifier = member.getIdentifier();
    // String to = member.getEmailAddress();
    // saveQuickEmailMessage(to, replyTo, replyToFullname,
    // member.getForenames(), member.getSurname(),
    // member.getAddress(), recipientIdentifier, emailRecipientType,
    // subject, message, organisation, isSendCopyToSender);

    // private List<Member> getDepartmentMembers(String departmentIdentifier) {
    // List<Member> members = new ArrayList<Member>();
    // Query query = getCurrentSession().createQuery(
    // "from Department d where d.identifier = :identifier");
    // query.setParameter("identifier", departmentIdentifier);
    // Department department = (Department) query.uniqueResult();
    // Set<MemberDepartment> memberDepartments = department
    // .getMemberDepartments();
    // for (MemberDepartment memberDepartment : memberDepartments) {
    // memberDepartment.getMember().getChildrenAtHome().size();
    // memberDepartment.getMember().getMemberDepartments().size();
    // members.add(memberDepartment.getMember());
    // }
    // return members;
    // }
    //
    // private void initialiseImageBinaryData(List<EmailNewsLetterPhoto> photos)
    // throws IOException {
    // for (EmailNewsLetterPhoto photo : photos) {
    // String intValue = photo.getFilename().replaceAll("[a-zA-Z/]", "");
    // GalleryItem galleryItem = (GalleryItem) getCurrentSession().get(
    // GalleryItem.class, Long.valueOf(intValue));
    // byte[] bytes = Base64Utils.fromBase64(galleryItem.getBase64Data());
    // photo.setData(bytes);
    // photo.setContentType(galleryItem.getContentType());
    // }
    //
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // public void replyMessageToMailSenderWithStandardMessage(
    // EmailProcess emailProcess, String from, String subject,
    // String message) {
    //
    // Query query = getCurrentSession().createQuery(
    // "from Member m where m.emailAddress = :emailAddress");
    // query.setParameter("emailAddress", from);
    // List<Member> members = query.list();
    // for (Member member : members) {
    // String memberFullname = member.getFullname();
    // String orgAdminEmail = member.getOrganisation().getAdminEmail();
    // String orgName = member.getOrganisation().getOrgName();
    // try {
    // emailProcess.replyMessageToMailSenderWithStandardMessage(
    // getMailSenderAddress(), member.getEmailAddress(),
    // subject, message, orgAdminEmail, memberFullname,
    // orgName);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    //
    // }
    //
    //
    // @SuppressWarnings("unchecked")
    // private void processMemberInvitationReplyNotificationSend(
    // EmailProcess emailProcess, Organisation organisation,
    // String mailSenderAddress, String hostName, String contextPathName) {
    // Query query = getCurrentSession()
    // .createQuery(
    // "from MemberInvitationReplyToUserNotificationEmail email  where email.memberInvitation.organisation = :organisation");
    // query.setParameter("organisation", organisation);
    //
    // List<MemberInvitationReplyToUserNotificationEmail>
    // memberInvitationReplyToUserNotificationEmails = query
    // .list();
    //
    // for (MemberInvitationReplyToUserNotificationEmail
    // memberInvitationReplyToUserNotificationEmail :
    // memberInvitationReplyToUserNotificationEmails) {
    // try {
    // MemberInvitation memberInvitation =
    // memberInvitationReplyToUserNotificationEmail
    // .getMemberInvitation();
    // EmailProcessTO emailProcessTO = new EmailProcessTO();
    // emailProcessTO.setRecipientEmailAddress(memberInvitation
    // .getUser().getEmailAddress());
    // emailProcessTO.setSenderEmailAddress(mailSenderAddress);
    // emailProcessTO.setReplyToEmailAddress(organisation
    // .getAdminEmail());
    //
    // emailProcessTO.setSenderFullname(organisation.getOrgName());
    // emailProcess.sendMemberInvitationNotificationToUserSender(
    // memberInvitation, emailProcessTO, hostName,
    // contextPathName);
    // getCurrentSession().delete(
    // memberInvitationReplyToUserNotificationEmail);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // private void processMemberDetailsVerificationSend(
    // EmailProcess emailProcess, Organisation organisation,
    // String mailSenderAddress, String hostName, String contextPathName) {
    //
    // Query query = getCurrentSession()
    // .createQuery(
    // "from MemberDetailsVerificationEmail m where m.memberWorkingCopy.organisation = :organisation");
    // query.setParameter("organisation", organisation);
    //
    // List<MemberDetailsVerificationEmail> emails = query.list();
    // for (MemberDetailsVerificationEmail email : emails) {
    // MemberWorkingCopy copy = email.getMemberWorkingCopy();
    // try {
    // EmailProcessTO emailProcessTO = new EmailProcessTO();
    // emailProcessTO
    // .setSubject("Please check your details with us are up-to-date");
    // emailProcessTO.setRecipientEmailAddress(copy.getEmailAddress());
    // emailProcessTO.setSenderEmailAddress(mailSenderAddress);
    // emailProcessTO.setRecipientFullname(copy.getFullname());
    // emailProcessTO.setSenderFullname(organisation.getOrgName());
    // emailProcessTO.setReplyToEmailAddress(organisation
    // .getAdminEmail());
    //
    // emailProcessTO.setMessage(email.getMessageBody());
    //
    // LogoItem logoData = getChurchLogo(organisation);
    // if (logoData != null && logoData.getBase64Data() != null) {
    // emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
    // emailProcessTO
    // .setLogoContentType(logoData.getContentType());
    // }
    //
    // emailProcess.sendMemberRequestToVerifyDetails(emailProcessTO,
    // copy.getIdentifier(), hostName, contextPathName);
    //
    // getCurrentSession().delete(email);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // private void processGuestEmailNewsLetterSend(EmailProcess emailProcess,
    // String mailSenderAddress, Organisation organisation) {
    // Query query = getCurrentSession()
    // .createQuery(
    // "from GuestEmailNewsletter guest where guest.emailNewsletter.organisation = :organisation ");
    // query.setParameter("organisation", organisation);
    // List<GuestEmailNewsletter> guestEmailNewsLetters = query.list();
    // for (GuestEmailNewsletter gNewsletter : guestEmailNewsLetters) {
    // try {
    // EmailNewsLetter emailNewsletter = gNewsletter
    // .getEmailNewsletter();
    // Guest guest = gNewsletter.getGuest();
    //
    // List<EmailNewsLetterPhoto> photos = emailNewsletter
    // .getEmailNewsLetterPhotos();
    //
    // initialiseImageBinaryData(photos);
    //
    // EmailProcessTO emailProcessTO = new EmailProcessTO();
    // emailProcessTO
    // .setRecipientEmailAddress(guest.getEmailAddress());
    // emailProcessTO.setRecipientFullname(guest.getFullname());
    // emailProcessTO.setSenderEmailAddress(mailSenderAddress);
    // emailProcessTO.setReplyToEmailAddress(organisation
    // .getAdminEmail());
    //
    // emailProcessTO.setSenderFullname(organisation.getOrgName());
    // emailProcessTO.setSubject(emailNewsletter.getSubject());
    //
    // String message = mailMergeMessage(guest,
    // emailNewsletter.getMessageBody());
    // emailProcessTO.setMessage(message);
    // LogoItem logoData = getChurchLogo(organisation);
    //
    // if (logoData != null && logoData.getBase64Data() != null) {
    // emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
    // emailProcessTO
    // .setLogoContentType(logoData.getContentType());
    // }
    //
    // emailProcess.sendNewsLetters(emailProcessTO, photos,
    // guest.getIdentifier(), EmailRecipientType.GUEST);
    // getCurrentSession().delete(gNewsletter);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
    //
    // @SuppressWarnings("unchecked")
    // private void processMemberEmailNewsLetterSend(EmailProcess emailProcess,
    // String mailSenderAddress, Organisation organisation) {
    // Query query = getCurrentSession()
    // .createQuery(
    // "from MemberEmailNewsletter men where men.emailNewsletter.organisation = :organisation ");
    // query.setParameter("organisation", organisation);
    // List<MemberEmailNewsletter> memberEmailNewsLetters = query.list();
    // for (MemberEmailNewsletter mNewsletter : memberEmailNewsLetters) {
    // try {
    // EmailNewsLetter emailNewsletter = mNewsletter
    // .getEmailNewsletter();
    // Member member = mNewsletter.getMember();
    //
    // List<EmailNewsLetterPhoto> photos = emailNewsletter
    // .getEmailNewsLetterPhotos();
    //
    // initialiseImageBinaryData(photos);
    //
    // EmailProcessTO emailProcessTO = new EmailProcessTO();
    // emailProcessTO.setRecipientEmailAddress(member
    // .getEmailAddress());
    // emailProcessTO.setRecipientFullname(member.getFullname());
    // emailProcessTO.setSenderEmailAddress(mailSenderAddress);
    //
    // emailProcessTO.setSenderFullname(organisation.getOrgName());
    // emailProcessTO.setSubject(emailNewsletter.getSubject());
    //
    // String message = mailMergeMessage(member,
    // emailNewsletter.getMessageBody());
    // emailProcessTO.setMessage(message);
    //
    // emailProcessTO.setReplyToEmailAddress(organisation
    // .getAdminEmail());
    // LogoItem logoData = getChurchLogo(organisation);
    // if (logoData != null && logoData.getBase64Data() != null) {
    // emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
    // emailProcessTO
    // .setLogoContentType(logoData.getContentType());
    // }
    // emailProcess.sendNewsLetters(emailProcessTO, photos,
    // member.getIdentifier(), EmailRecipientType.MEMBER);
    // getCurrentSession().delete(mNewsletter);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // @SuppressWarnings("unchecked")
    // private void processMemberInvitationSend(EmailProcess emailProcess,
    // Organisation organisation, String mailSenderAddress,
    // String hostName, String contextPathName) {
    // Query query = getCurrentSession()
    // .createQuery(
    // "from MemberInvitationEmail email where email.organisation = :organisation");
    // query.setParameter("organisation", organisation);
    // List<MemberInvitationEmail> memberInvitationEmails = query.list();
    // for (MemberInvitationEmail memberInvitationEmail :
    // memberInvitationEmails) {
    // try {
    // EmailProcessTO emailProcessTO = new EmailProcessTO();
    // emailProcessTO.setRecipientEmailAddress(memberInvitationEmail
    // .getEmailAddress());
    // emailProcessTO.setSenderEmailAddress(mailSenderAddress);
    // emailProcessTO.setReplyToEmailAddress(organisation
    // .getAdminEmail());
    //
    // emailProcessTO.setSenderFullname(organisation.getOrgName());
    // emailProcessTO.setSubject(memberInvitationEmail.getSubject());
    //
    // LogoItem logoData = getChurchLogo(organisation);
    // if (logoData != null && logoData.getBase64Data() != null) {
    // emailProcessTO.setLogoBase64Str(logoData.getBase64Data());
    // emailProcessTO
    // .setLogoContentType(logoData.getContentType());
    // }
    //
    // emailProcess.sendMemberInvitation(emailProcessTO,
    // memberInvitationEmail.getIdentifier(), hostName,
    // contextPathName);
    // getCurrentSession().delete(memberInvitationEmail);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // }
}
