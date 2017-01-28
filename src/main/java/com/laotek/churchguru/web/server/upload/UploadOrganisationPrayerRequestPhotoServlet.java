package com.laotek.churchguru.web.server.upload;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.laotek.churchguru.daos.photos.PhotoDao;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.web.server.BasePhotoServlet;

import gwtupload.server.exceptions.UploadActionException;

@WebServlet(urlPatterns = { "*.uploadOrganisationPrayerRequestPhoto" })
public class UploadOrganisationPrayerRequestPhotoServlet extends BasePhotoServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PhotoDao photoDao;

    @Override
    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	try {

	    FileItem fileItem = sessionFiles.get(0);

	    for (Cookie cookie : request.getCookies()) {
		if ("discoSessionId".equals(cookie.getName())) {
		    String clientSessionId = cookie.getValue();

		    String contentType = fileItem.getContentType();
		    String filename = fileItem.getFieldName();
		    String base64Data = getData(fileItem);

		    photoDao.savePhoto(LogoItemType.PRAYER_REQUEST, clientSessionId, base64Data, filename, contentType);
		}
	    }

	    return request.getContextPath();

	} catch (Exception e) {
	    throw new UploadActionException(e.getMessage());
	} finally {
	    removeSessionFileItems(request);
	}
    }
}
