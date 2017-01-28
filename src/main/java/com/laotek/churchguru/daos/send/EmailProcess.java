package com.laotek.churchguru.daos.send;

import java.math.BigDecimal;
import java.util.Map;

import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.model.shared.enums.ForgottenCredentialType;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

public interface EmailProcess {

    void sendFeedback(EmailProcessTO emailProcessTO) throws Exception;

    void sendQuickEmail(EmailProcessTO emailProcessTO,
	    String recipientIdentifier, EmailRecipientType emailRecipientType)
	    throws Exception;

    void sendNotification(EmailProcessTO emailProcessTO) throws Exception;

    void sendForgottenCredentialEmail(EmailProcessTO emailProcessTO,
	    String recipientIdentifier, ForgottenCredentialType type,
	    String username, String host, String contextPath,
	    String passwordResetIdentifier) throws Exception;

    void sendNewUserSetupEmail(EmailProcessTO emailProcessTO,
	    String recipientIdentifier, String host, String contextPath)
	    throws Exception;

    void replyMessageToMailSenderWithStandardMessage(String from, String to,
	    String subject, String message, String orgAdminEmail,
	    String memberFullname, String churchName) throws Exception;

    void sendPrayerRequest(EmailProcessTO emailProcessTO, String senderMobile)
	    throws Exception;

    void sendDonationThankYou(EmailProcessTO emailProcessTO,
	    Map<DonationType, BigDecimal> donationTypes, BigDecimal amount);
}
