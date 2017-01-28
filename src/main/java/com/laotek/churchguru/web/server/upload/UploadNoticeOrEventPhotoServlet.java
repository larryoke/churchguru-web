package com.laotek.churchguru.web.server.upload;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.web.server.BasePhotoServlet;

import gwtupload.server.exceptions.UploadActionException;

@WebServlet(urlPatterns = { "*.uploadNoticeOrEventPhotoServlet" })
public class UploadNoticeOrEventPhotoServlet extends BasePhotoServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Override
    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	try {

	    FileItem fileItem = sessionFiles.get(0);

	    for (Cookie cookie : request.getCookies()) {
		if ("discoSessionId".equals(cookie.getName())) {

		    String keyAsStr = request.getParameter("key");
		    String identifier = request.getParameter("identifier");
		    if (keyAsStr == null || identifier == null) {
			throw new UploadActionException("Unique record identification error");
		    }
		    int key = Integer.parseInt(keyAsStr);
		    String contentType = fileItem.getContentType();
		    String filename = fileItem.getFieldName();
		    String base64Data = getData(fileItem);

		    noticeAndEventDao.uploadNoticeAndEventPicture(key, identifier, base64Data, filename, contentType);
		    break;
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
