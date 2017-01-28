package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cgservice")
public interface DispatchService extends RemoteService {
	Result execute(String sessionId, Action<?> action ) throws Exception;
}
