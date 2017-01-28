package com.laotek.churchguru.web.server.handler;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionHandlerRegistryBean  extends DefaultActionHandlerRegistry { 
	@Autowired
	public void setHandlers(List<ActionHandler<?, ?>> handlers) {
		for (ActionHandler<?, ?> actionHandler : handlers) {
			addHandler(actionHandler);
		}
	}
}
