package com.laotek.churchguru.services;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.daos.send.EmailProcess;
import com.laotek.churchguru.daos.send.EmailProcessTO;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.model.shared.enums.ForgottenCredentialType;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailProcessImpl implements EmailProcess {

    static Logger log = LoggerFactory.getLogger(EmailProcessImpl.class);

    private String sendgridApiKey;

    public EmailProcessImpl(String sendgridApiKey) {
	this.sendgridApiKey = sendgridApiKey;
    }

    @Override
    public void sendFeedback(EmailProcessTO emailProcessTO) throws Exception {

	log.info("sendFeedback->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();
	String message = "<h3>Hi</h3><br/>User Name: %s <br/><br/>User Feedback: %s<br/>";

	Content content = new Content("text/html", String.format(message, emailProcessTO.getSenderFullname(),
		emailProcessTO.getMessage().replace("\n", "<br>")));

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendFeedback()";
	processRequest(mail, method);

	log.info("<-sendFeedback");
    }

    @Override
    public void sendNotification(EmailProcessTO emailProcessTO) throws Exception {

	log.info("sendNotification->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();
	String message = "<h3>Hi %s </h3><br/>%s<br/><br/>";

	Content content = new Content("text/html", String.format(message, emailProcessTO.getRecipientFullname(),
		emailProcessTO.getMessage().replace("\n", "<br>")));

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendNotification()";
	processRequest(mail, method);
	log.info("<-sendNotification");
    }

    @Override
    public void sendQuickEmail(EmailProcessTO emailProcessTO, String recipientIdentifier,
	    EmailRecipientType emailRecipientType) throws Exception {

	log.info("sendQuickEmail->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();
	String message = "<h3>Hi %s </h3><br/> %s <br/><br/>";

	Content content = new Content("text/html", String.format(message, emailProcessTO.getRecipientFullname(),
		emailProcessTO.getMessage().replace("\n", "<br>")));

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendQuickEmail()";
	processRequest(mail, method);
	log.info("<-sendQuickEmail");
    }

    @Override
    public void sendForgottenCredentialEmail(EmailProcessTO emailProcessTO, String recipientIdentifier,
	    ForgottenCredentialType type, String username, String host, String contextPath,
	    String passwordResetIdentifier) throws Exception {

	log.info("sendForgottenCredentialEmail->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();

	String message = null;
	if (ForgottenCredentialType.USERNAME.equals(type)) {
	    String forgottenUsernameMessage = "<h3>Hi %s</h3>"
		    + "<br/>You're receiving this email message because you requested to be reminded of your ChurchGURU Communicator %s username."
		    + "<br/>" + "<br/>You username is <b>%s</b>." + "<br/>" + "<br/>Thank you" + "<br/>" + "<br/>";
	    message = String.format(forgottenUsernameMessage, emailProcessTO.getRecipientFullname(),
		    emailProcessTO.getOrgName(), username);
	} else {
	    String forgottenPasswordMessage = "<h3>Hi %s</h3>"
		    + "<br/>You're receiving this email message because you requested to reset your ChurchGURU Communicator %s access password."
		    + "<br/>"
		    + "<br/>To reset, please click <a href=\"%s\">here</a> to start.<br/><br/>Thank you<br/><br/>";
	    message = String.format(forgottenPasswordMessage, emailProcessTO.getRecipientFullname(),
		    emailProcessTO.getOrgName(), passwordResetIdentifier);
	}

	Mail mail = new Mail(from, subject, to, new Content("text/html", message));
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendForgottenCredentialEmail()";
	processRequest(mail, method);
	log.info("<-sendForgottenCredentialEmail");
    }

    @Override
    public void sendNewUserSetupEmail(EmailProcessTO emailProcessTO, String recipientIdentifier, String host,
	    String contextPath) throws Exception {

	log.info("sendNewUserSetupEmail->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();
	String message = "<h3>Hello %s </h3>"
		+ "<br/>You're receiving this message because you've been selected as a user of ChurchGURU Communicator for %s.<br/>"
		+ "<br/>Communicator makes it easier to manage the mobile app data and activities of users and how you communicate with them. <br/>"
		+ "<br/>To set up a new user account, click <a href=\"%s\">here</a> to start.<br/>"
		+ "<br/>Select <a href=\"http://www.churchguru.com/help\">ChurchGURU Communicator Help</a> to learn more. <br/><br/>";

	String url = createUrl(host, contextPath, "NewUserSetupPlace", recipientIdentifier);

	Content content = new Content("text/html",
		String.format(message, emailProcessTO.getRecipientFullname(), emailProcessTO.getOrgName(), url));

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendNewUserSetupEmail()";
	processRequest(mail, method);
	log.info("<-sendNewUserSetupEmail");
    }

    @Override
    public void sendPrayerRequest(EmailProcessTO emailProcessTO, String senderMobile) throws Exception {

	log.info("sendPrayerRequest->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();
	String messageWithMobile = "<h3>Prayer Request from %s </h3>%s<br>Mobile: %s";
	String messageWithoutMobile = "<h3>Prayer Request from %s</h3>%s";
	String message = null;
	if (!StringUtils.isEmpty(senderMobile)) {
	    message = String.format(messageWithMobile, emailProcessTO.getSenderFullname(),
		    emailProcessTO.getMessage().replace("\n", "<br>"), senderMobile);
	} else {
	    message = String.format(messageWithoutMobile, emailProcessTO.getSenderFullname(),
		    emailProcessTO.getMessage().replace("\n", "<br>"));
	}

	Content content = new Content("text/html", message);

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendPrayerRequest()";
	processRequest(mail, method);

	log.info("<-sendPrayerRequest");
    }

    @Override
    public void replyMessageToMailSenderWithStandardMessage(String from, String to, String subject, String message,
	    String orgAdminEmail, String memberFullname, String churchName) throws Exception {

	log.info("sendNewUserSetupEmail->");

	Email fromE = new Email(from);
	Email toE = new Email(to);

	String messageBody = "<html>" + "<body><h3>Hi %s</h3><div><br/>"
		+ "<br/>Please beware that this email INBOX is not monitored.<br/>"
		+ "<br/>You may wish to send your message to %s where it can be seen and attended to by %s.<br/>"
		+ "<br/>Thanks,<br/>The ChurchGURU Communicator Team<br/></div>"
		+ "<div><small>Application Service Provided By ChurchGuru Software.</small></div></body></html>";

	Content content = new Content("text/html",
		String.format(messageBody, memberFullname, orgAdminEmail, churchName));

	Mail mail = new Mail(fromE, subject, toE, content);
	String method = "replyMessageToMailSenderWithStandardMessage()";
	processRequest(mail, method);
	log.info("<-sendNewUserSetupEmail");
    }

    @Override
    public void sendDonationThankYou(EmailProcessTO emailProcessTO, Map<DonationType, BigDecimal> donationTypes,
	    BigDecimal amount) {
	log.info("sendDonationThankYou->");

	Email from = new Email(emailProcessTO.getSenderEmailAddress(), emailProcessTO.getSenderFullname());
	Email to = new Email(emailProcessTO.getRecipientEmailAddress());
	String subject = emailProcessTO.getSubject();

	String donationTypesAsStr = "<ul>";
	for (Map.Entry<DonationType, BigDecimal> type : donationTypes.entrySet()) {
	    if (type.getValue() != null && type.getValue().intValue() > 0) {
		donationTypesAsStr = donationTypesAsStr + "<li>" + type.getKey().getDesc() + ": "
			+ type.getValue().toPlainString() + "GBP</li>";
	    }
	}
	donationTypesAsStr = donationTypesAsStr + "</ul>";

	String message = "Dear %s,<br/><br/>" + "We would like to thank you for your donation.<br/>"
		+ "We received the amount of %sGBP for the donation.<br/>" + "%s"
		+ "Should you have any questions, please do not hesitate to send an email to %s.<br/><br/>"
		+ "God bless you,<br/><br/>" + "%s<br/>" + "Church Office<br/><br/>" + "--<br/>%s";

	Content content = new Content("text/html",
		String.format(message, emailProcessTO.getRecipientFullname(), amount.toPlainString(),
			donationTypesAsStr, emailProcessTO.getReplyToEmailAddress(), emailProcessTO.getOrgName(),
			emailProcessTO.getOrgAddress()));

	Mail mail = new Mail(from, subject, to, content);
	mail.setReplyTo(new Email(emailProcessTO.getReplyToEmailAddress()));
	String method = "sendDonationThankYou()";
	processRequest(mail, method);

	log.info("<-sendDonationThankYou");
    }

    private void processRequest(Mail mail, String method) {
	SendGrid sendgrid = new SendGrid(sendgridApiKey);
	Request request = new Request();
	try {
	    request.method = Method.POST;
	    request.endpoint = "mail/send";
	    request.body = mail.build();
	    Response response = sendgrid.api(request);
	    log.info(method + " Status Code: " + response.statusCode);
	    log.info(response.body);
	    log.info(response.headers.toString());
	} catch (Exception e) {
	    log.error(e.getMessage());
	}
    }

    private String createUrl(String host, String context, String placeName, String... queryString) {
	StringBuffer sb = new StringBuffer();
	sb.append("https://");
	sb.append(host);
	sb.append("/");
	sb.append(context);
	sb.append("/index.htm#");
	sb.append(placeName);
	for (String param : queryString) {
	    sb.append(":");
	    sb.append(param);
	}
	return sb.toString();
    }

}
