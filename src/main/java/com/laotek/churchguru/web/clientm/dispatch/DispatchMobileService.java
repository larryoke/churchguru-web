package com.laotek.churchguru.web.clientm.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("mobileservice")
public interface DispatchMobileService extends RemoteService {
    Result execute(String sessionId, Action<?> action) throws Exception;
}
