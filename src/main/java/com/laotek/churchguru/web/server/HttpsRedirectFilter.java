package com.laotek.churchguru.web.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpsRedirectFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {

	if (request instanceof HttpServletRequest
		&& response instanceof HttpServletResponse) {

	    if (!request.isSecure()) {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		String redirectTarget = httpReq.getRequestURL().toString();
		redirectTarget = redirectTarget.replaceFirst("http://",
			"https://");
		if (!redirectTarget.startsWith("https://")) {
		    redirectTarget = "https://" + redirectTarget;
		}
		((HttpServletResponse) response).sendRedirect(redirectTarget);
	    } else {
		chain.doFilter(request, response);
	    }
	}
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
