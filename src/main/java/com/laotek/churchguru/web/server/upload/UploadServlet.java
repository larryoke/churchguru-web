package com.laotek.churchguru.web.server.upload;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.Base64Utils;
import com.laotek.churchguru.daos.photos.PhotoDao;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

@WebServlet(name = "uploadServlet", urlPatterns = { "*.gupld" })
public class UploadServlet extends UploadAction {

    private static final long serialVersionUID = 1L;

    private PhotoDao photoDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	WebApplicationContext ctx = WebApplicationContextUtils
		.getRequiredWebApplicationContext(config.getServletContext());
	AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
	beanFactory.autowireBean(this);
    }

    public PhotoDao getPhotoDao() {
	return photoDao;
    }

    @Autowired
    @Required
    public void setPhotoDao(PhotoDao photoDao) {
	this.photoDao = photoDao;
    }

    @Override
    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	try {
	    FileItem fileItem = sessionFiles.get(0);
	    BufferedInputStream bis = new BufferedInputStream(fileItem.getInputStream());
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[65536];
	    int l;
	    while ((l = bis.read(buffer)) > 0) {
		output.write(buffer, 0, l);
	    }
	    output.close();
	    byte[] data = output.toByteArray();
	    String base64Data = Base64Utils.toBase64(data);

	    long id = photoDao.saveMemberPhoto(base64Data, fileItem.getFieldName(), fileItem.getContentType());
	    return request.getContextPath() + "/uploadedphotos/photos/" + id + "/member/";

	} catch (Exception e) {
	    throw new UploadActionException(e.getMessage());
	} finally {
	    removeSessionFileItems(request);
	}
    }
}
