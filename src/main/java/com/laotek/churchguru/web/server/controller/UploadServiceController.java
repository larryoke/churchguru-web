package com.laotek.churchguru.web.server.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gwt.user.server.Base64Utils;
import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.photos.PhotoDao;
import com.laotek.churchguru.model.GalleryItem;
import com.laotek.churchguru.model.LogoItem;
import com.laotek.churchguru.model.MemberPhoto;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.shared.enums.LogoItemType;
import com.laotek.churchguru.model.shared.enums.MessagePhotoAlignment;

@Controller
@RequestMapping("/uploadedphotos/photos")
public class UploadServiceController {

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Autowired
    private OrganisationDao organisationDao;

    @RequestMapping(value = "/{id}/member", method = RequestMethod.GET)
    public void getMemberPhoto(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	int size = 200;
	MemberPhoto photo = photoDao.getMemberPhoto(id);
	byte[] bytes = Base64Utils.fromBase64(photo.getBase64Data());
	BufferedImage scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Mode.AUTOMATIC, size);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	if (photo.getContentType().contains("png")) {
	    ImageIO.write(scaledImage, "png", baos);
	} else {
	    ImageIO.write(scaledImage, "jpg", baos);
	}

	response.setBufferSize(10240);
	response.setContentType(photo.getContentType());
	response.setContentLength(baos.toByteArray().length);
	response.setHeader("Content-Length", String.valueOf(baos.toByteArray().length));
	response.setHeader("Content-Disposition", "inline; filename=\"" + photo.getFilename() + "\"");
	response.getOutputStream().write(baos.toByteArray());
    }

    @RequestMapping(value = "/{id}/newsletter", method = RequestMethod.GET)
    public void getNewsletterPhoto(@PathVariable int id,
	    @RequestParam(value = "alignment", required = false) String alignment, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	int size = 200;
	if (MessagePhotoAlignment.BIG_CENTRE.name().equals(alignment)) {
	    size = 600;
	}

	GalleryItem galleryItem = photoDao.getGalleryItem(id);
	byte[] bytes = Base64Utils.fromBase64(galleryItem.getBase64Data());
	BufferedImage scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Mode.AUTOMATIC, size);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	if (galleryItem.getContentType().contains("png")) {
	    ImageIO.write(scaledImage, "png", baos);
	} else {
	    ImageIO.write(scaledImage, "jpg", baos);
	}

	response.setBufferSize(10240);
	response.setContentType(galleryItem.getContentType());
	response.setContentLength(baos.toByteArray().length);
	response.setHeader("Content-Length", String.valueOf(baos.toByteArray().length));
	response.setHeader("Content-Disposition", "inline; filename=\"" + galleryItem.getFilename() + "\"");
	response.getOutputStream().write(baos.toByteArray());
    }

    @RequestMapping(value = "/org/logo", method = RequestMethod.GET)
    public void getOrganisationLogo(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int size = 100;

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.CHURCH_LOGO);

	getImage(response, galleryItem, size);
    }

    @RequestMapping(value = "/noticeorevent/{key}/{identifier}", method = RequestMethod.GET)
    public void getNoticeOrEventPhoto(HttpServletRequest request, HttpServletResponse response, @PathVariable int key,
	    @PathVariable String identifier) throws Exception {

	NoticeAndEvent dto = noticeAndEventDao.getNoticeAndEvent(key, identifier);

	byte[] bytes = Base64Utils.fromBase64(dto.getBase64Data());

	BufferedImage scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Scalr.Method.QUALITY,
		Scalr.Mode.FIT_TO_WIDTH, 600, Scalr.OP_ANTIALIAS);

	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	if (dto.getContentType().contains("png")) {
	    ImageIO.write(scaledImage, "png", baos);
	} else {
	    ImageIO.write(scaledImage, "jpg", baos);
	}

	response.setBufferSize(10240);
	response.setContentType(dto.getContentType());
	response.setContentLength(baos.toByteArray().length);
	response.setHeader("Content-Length", String.valueOf(baos.toByteArray().length));
	response.setHeader("Content-Disposition", "inline; filename=\"" + dto.getFilename() + "\"");
	response.getOutputStream().write(baos.toByteArray());

    }

    @RequestMapping(value = "/org/home", method = RequestMethod.GET)
    public void getChurchAppHomeProfileLogo(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.CHURCH_APP_PROFILE_PIC);

	getImage(response, galleryItem, width);
    }

    private int getValue(int size, HttpServletRequest request, String dimension) {
	String value = request.getParameter(dimension);
	if (!StringUtils.isEmpty(value)) {
	    return Integer.parseInt(value);
	}
	return size;
    }

    private void getImage(HttpServletResponse response, LogoItem galleryItem, int width) throws IOException {
	byte[] bytes = Base64Utils.fromBase64(galleryItem.getBase64Data());
	BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
	int defaultWidth = 300;
	int defaultMobileHeight = 150;
	int defaultPhabletHeight = 175;
	int defaultTabletHeight = 200;

	boolean isMobileWidth = width <= 400;
	boolean isPhabletWidth = width > 400 && width <= 500;
	boolean isTabletWidth = width > 500;
	boolean isLandscape = img.getWidth() > img.getHeight();

	int currentHeight = 0;
	if (isMobileWidth) {
	    currentHeight = defaultMobileHeight;
	} else if (isPhabletWidth) {
	    currentHeight = defaultPhabletHeight;
	} else if (isTabletWidth) {
	    currentHeight = defaultTabletHeight;
	}

	BufferedImage scaledImage = null;

	if (isLandscape) {
	    if (isTabletWidth) {
		scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Scalr.Method.ULTRA_QUALITY,
			Scalr.Mode.FIT_TO_HEIGHT, currentHeight, Scalr.OP_ANTIALIAS);
	    } else {
		scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Scalr.Method.ULTRA_QUALITY,
			Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);

	    }

	} else {
	    scaledImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)), Scalr.Method.ULTRA_QUALITY,
		    Scalr.Mode.FIT_TO_HEIGHT, currentHeight, Scalr.OP_ANTIALIAS);
	}

	if (scaledImage.getWidth() < width) {
	    int imgHeight = scaledImage.getHeight();

	    BufferedImage scaledImage2 = Scalr.resize(ImageIO.read(new ByteArrayInputStream(bytes)),
		    Scalr.Method.BALANCED, Scalr.Mode.FIT_EXACT, width, currentHeight, Scalr.OP_GRAYSCALE);

	    BufferedImage baseImage = new BufferedImage(width, imgHeight, BufferedImage.OPAQUE);

	    int xposition = getXPosition(defaultWidth, width);

	    Graphics graphic = baseImage.getGraphics();
	    graphic.drawImage(scaledImage2, 0, 0, null);
	    graphic.drawImage(scaledImage, xposition, 0, null);

	    scaledImage = baseImage;

	}

	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	if (galleryItem.getContentType().contains("png")) {
	    ImageIO.write(scaledImage, "png", baos);
	} else {
	    ImageIO.write(scaledImage, "jpg", baos);
	}

	response.setBufferSize(10240);
	response.setContentType(galleryItem.getContentType());
	response.setContentLength(baos.toByteArray().length);
	response.setHeader("Content-Length", String.valueOf(baos.toByteArray().length));
	response.setHeader("Content-Disposition", "inline; filename=\"" + galleryItem.getFilename() + "\"");
	response.getOutputStream().write(baos.toByteArray());
    }

    private void getImage(HttpServletResponse response, LogoItem galleryItem) throws IOException {
	byte[] bytes = Base64Utils.fromBase64(galleryItem.getBase64Data());
	BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));

	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	if (galleryItem.getContentType().contains("png")) {
	    ImageIO.write(img, "png", baos);
	} else {
	    ImageIO.write(img, "jpg", baos);
	}

	response.setBufferSize(10240);
	response.setContentType(galleryItem.getContentType());
	response.setContentLength(baos.toByteArray().length);
	response.setHeader("Content-Length", String.valueOf(baos.toByteArray().length));
	response.setHeader("Content-Disposition", "inline; filename=\"" + galleryItem.getFilename() + "\"");
	response.getOutputStream().write(baos.toByteArray());
    }

    @RequestMapping(value = "/org/pastordesk", method = RequestMethod.GET)
    public void getPastorDeskPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.PASTORS_DESK_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/aboutpastor", method = RequestMethod.GET)
    public void getAboutPastorPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.ABOUT_PASTOR_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/prayerrequest", method = RequestMethod.GET)
    public void getprayerRequestPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.PRAYER_REQUEST);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/twitter", method = RequestMethod.GET)
    public void getTwitterPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.TWITTER_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/facebook", method = RequestMethod.GET)
    public void getFacebookPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.FACEBOOK_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/messages", method = RequestMethod.GET)
    public void getMessages(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.MESSAGES_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/listen", method = RequestMethod.GET)
    public void getListenPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.LISTEN);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/youtube", method = RequestMethod.GET)
    public void getYoutubePhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.YOUTUBE);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/aboutus", method = RequestMethod.GET)
    public void getAboutUsPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.ABOUT_US_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/give", method = RequestMethod.GET)
    public void getGivePhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.GIVE_PHOTO);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/website", method = RequestMethod.GET)
    public void getSundaySchoolPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception {

	int width = getValue(400, request, "width");

	LogoItem galleryItem = photoDao.getPhoto(LogoItemType.WEBSITE);

	getImage(response, galleryItem);
    }

    @RequestMapping(value = "/org/name", method = RequestMethod.GET)
    public void getOrganisationName(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Organisation org = organisationDao.getOrganisation(1L);
	if (org != null) {
	    String orgName = org.getOrgName();
	    if (orgName != null) {
		Font font = new Font("Verdana", Font.BOLD, 30);
		font = font.deriveFont(20);
		BufferedImage bufferedImage = textToImage(orgName.toUpperCase(), font);
		ImageIO.write(bufferedImage, "png", response.getOutputStream());
	    }
	}
    }

    private int getXPosition(int innerWidth, int outerWidth) {
	return (outerWidth - innerWidth) / 2;
    }

    private static final Map<RenderingHints.Key, Object> RenderingProperties = new HashMap<RenderingHints.Key, Object>();
    static {
	RenderingProperties.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	RenderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	RenderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    private static BufferedImage textToImage(String Text, Font f) {
	// Derives font to new specified size, can be removed if not necessary.
	FontRenderContext frc = new FontRenderContext(null, true, true);
	// Calculate size of buffered image.
	LineMetrics lm = f.getLineMetrics(Text, frc);
	Rectangle2D r2d = f.getStringBounds(Text, frc);
	BufferedImage img = new BufferedImage((int) Math.ceil(r2d.getWidth()), (int) Math.ceil(r2d.getHeight()),
		BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d = img.createGraphics();
	g2d.setRenderingHints(RenderingProperties);
	g2d.setBackground(Color.BLACK);
	g2d.setColor(Color.WHITE);
	g2d.clearRect(0, 0, img.getWidth(), img.getHeight());
	g2d.setFont(f);
	g2d.drawString(Text, 0, lm.getAscent());
	g2d.dispose();
	return img;
    }
}
