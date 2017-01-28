package com.laotek.churchguru.web.server.handler;

import net.customware.gwt.dispatch.server.ActionHandlerRegistry;
import net.customware.gwt.dispatch.server.SimpleDispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatchBean extends SimpleDispatch {
 
	@Autowired
	public DispatchBean(ActionHandlerRegistry handlerRegistry) {
		super(handlerRegistry);
	}
}