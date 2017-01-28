package com.laotek.churchguru.web.server;

import gwtupload.server.UploadAction;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.Base64Utils;

public abstract class BasePhotoServlet extends UploadAction {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	WebApplicationContext ctx = WebApplicationContextUtils
		.getRequiredWebApplicationContext(config.getServletContext());
	AutowireCapableBeanFactory beanFactory = ctx
		.getAutowireCapableBeanFactory();
	beanFactory.autowireBean(this);
    }

    protected String getData(FileItem fileItem) throws IOException {
	BufferedInputStream bis = new BufferedInputStream(
		fileItem.getInputStream());
	ByteArrayOutputStream output = new ByteArrayOutputStream();
	byte[] buffer = new byte[65536];
	int l;
	while ((l = bis.read(buffer)) > 0) {
	    output.write(buffer, 0, l);
	}
	output.close();
	byte[] data = output.toByteArray();
	String base64Data = Base64Utils.toBase64(data);
	return base64Data;
    }
}
