package com.laotek.churchguru.web.server.handler.message;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.send.EmailProcessorDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.web.client.activity.user.GiveFeedbackAction;
import com.laotek.churchguru.web.client.activity.user.GiveFeedbackResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GiveFeedbackHandler extends AbstractCommandHandler implements
	ActionHandler<GiveFeedbackAction, GiveFeedbackResult> {

    @Autowired
    private EmailProcessorDao emailProcessorDao;

    @Autowired
    private UserDao userDao;

    @Override
    public GiveFeedbackResult execute(GiveFeedbackAction action,
	    ExecutionContext context) throws DispatchException {

	User op = userDao.getUserByClientSessionId(action.getClientSessionId());
	emailProcessorDao
		.saveUserFeedback("support@churchguru.com", op,
			action.getType(), action.getBody(),
			action.isSendCopyToSender());
	return new GiveFeedbackResult();
    }

    @Override
    public Class<GiveFeedbackAction> getActionType() {
	return GiveFeedbackAction.class;
    }

    @Override
    public void rollback(GiveFeedbackAction action, GiveFeedbackResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
