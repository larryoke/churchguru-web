package com.laotek.churchguru.daos.send;

import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.model.shared.enums.FeedbackMessageType;

public interface EmailProcessorDao {

    void processMessage(EmailProcess emailProcess);

    void processMessage(EmailProcess emailProcess, Organisation org);

    void saveUserFeedback(String to, User from, FeedbackMessageType subject,
	    String message, boolean isSendCopyToSender);

    void saveQuickEmailMessageDept(String departmentIdentifier, String replyTo,
	    String replyToFullname, EmailRecipientType emailRecipientType,
	    String subject, String message, Organisation organisation,
	    boolean isSendCopyToSender);

    void processDonationThankyouEmailSend(EmailProcess emailProcess,
	    Organisation organisation);

    // void saveMemberInvitationMessage(MemberInvitationEmail
    // memberInvitationEmail);
    //
    // void saveMemberInvitationNotificationToUserSender(
    // String invitationIdentifier);
    //
    // void saveMemberDetailsVerificationRequest(String message,
    // String memberIdentifier);

    // void saveMemberDetailsVerificationRequest(String message);

    void replyMessageToMailSenderWithStandardMessage(EmailProcess emailProcess,
	    String from, String subject, String message);
}
