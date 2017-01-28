package com.laotek.churchguru.daos.send;

import org.apache.commons.lang.StringUtils;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.QuickEmail;

public abstract class AbstractBaseMessageProcessorDaoImpl extends
	BaseSessionFactory {

    protected String mailMergeMessage(QuickEmail quickEmail) {
	String message = quickEmail.getMessage();
	if (!StringUtils.isEmpty(message)) {
	    message = messageReplaceAll(message, MailMergeConstant.FULLNAME,
		    quickEmail.getFullname());
	    message = messageReplaceAll(message, MailMergeConstant.FORENAME,
		    quickEmail.getForename());
	    message = messageReplaceAll(message, MailMergeConstant.SURNAME,
		    quickEmail.getSurname());
	    if (quickEmail.getAddress() != null) {
		message = messageReplaceAll(message, MailMergeConstant.ADDRESS,
			quickEmail.getAddress().toString());
	    }
	}
	return message;
    }

    protected String messageReplaceAll(String message,
	    MailMergeConstant mailMergeConstant, String replaceValue) {
	if (!StringUtils.isEmpty(message)) {

	    if (message.contains(mailMergeConstant.getPlaceholder())) {
		message = message.replaceAll(
			mailMergeConstant.getPlaceholder(), replaceValue);

	    }

	    if (message.contains(mailMergeConstant.getEscapedPlaceholder())) {
		message = message
			.replaceAll(mailMergeConstant.getEscapedPlaceholder(),
				replaceValue);
	    }
	}
	return message;
    }
}
