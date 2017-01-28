package com.laotek.churchguru.web.server.handler.message;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.stereotype.Component;

import com.laotek.churchguru.web.client.activity.VerifySPFAction;
import com.laotek.churchguru.web.client.activity.VerifySPFResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class VerifySPFHandler extends AbstractCommandHandler implements
	ActionHandler<VerifySPFAction, VerifySPFResult> {

    @Override
    public VerifySPFResult execute(VerifySPFAction action,
	    ExecutionContext context) throws DispatchException {

	String hostname = action.getHostname();

	return new VerifySPFResult();
    }

    @Override
    public Class<VerifySPFAction> getActionType() {
	return VerifySPFAction.class;
    }

    @Override
    public void rollback(VerifySPFAction action, VerifySPFResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
