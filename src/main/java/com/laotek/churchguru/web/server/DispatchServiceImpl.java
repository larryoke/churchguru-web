package com.laotek.churchguru.web.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.OrgDetailAction;
import com.laotek.churchguru.web.client.activity.password.PasswordResetAction;
import com.laotek.churchguru.web.client.activity.password.PasswordResetValidatorAction;
import com.laotek.churchguru.web.client.activity.user.ForgotCredentialAction;
import com.laotek.churchguru.web.client.activity.user.LoginAction;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupAction;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupValidationAction;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;
import net.customware.gwt.dispatch.shared.secure.InvalidSessionException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@WebServlet(name = "commDispatchServlet", urlPatterns = { "/comm/cgservice" })
public class DispatchServiceImpl extends RemoteServiceServlet implements DispatchService {

    private Dispatch dispatch;

    static Logger log = LoggerFactory.getLogger(DispatchServiceImpl.class);

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
	log.debug("execute->");
	try {
	    if (action instanceof LoginAction || action instanceof OrgDetailAction
		    || action instanceof ForgotCredentialAction || action instanceof PasswordResetAction
		    || action instanceof PasswordResetValidatorAction || action instanceof NewUserSetupAction
		    || action instanceof NewUserSetupValidationAction) {
		Result result = dispatch.execute(action);
		return result;
	    }

	    Cookie[] cookies = getThreadLocalRequest().getCookies();
	    for (Cookie cookie : cookies) {
		if ("discoSessionId".equals(cookie.getName())) {
		    if (sessionId != null && sessionId.equals(cookie.getValue())) {
			try {
			    Result result = dispatch.execute(action);
			    return result;

			} catch (ExpiredSessionException ese) {
			    ese.printStackTrace();
			    throw ese;

			} catch (Exception e) {
			    e.printStackTrace();
			    throw e;
			}
		    }
		}
	    }
	    throw new InvalidSessionException();

	} finally {
	    log.debug("<-execute");
	}
    }
}
