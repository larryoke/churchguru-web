package com.laotek.churchguru.web.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.laotek.churchguru.web.clientm.dispatch.DispatchMobileService;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@WebServlet(name = "commMobileDispatchServlet", urlPatterns = { "/mobi/mobileservice" })
public class DispatchMobileServiceImpl extends RemoteServiceServlet implements DispatchMobileService {

    private Dispatch dispatch;

    static Logger log = LoggerFactory.getLogger(DispatchMobileServiceImpl.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	WebApplicationContext ctx = WebApplicationContextUtils
		.getRequiredWebApplicationContext(config.getServletContext());
	AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
	beanFactory.autowireBean(this);
    }

    @Autowired
    @Required
    public void setDispatch(Dispatch dispatch) {
	this.dispatch = dispatch;
    }

    @Override
    public Result execute(String sessionId, Action<?> action) throws Exception {
	return dispatch.execute(action);
    }
}
