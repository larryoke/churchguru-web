package com.laotek.churchguru.web.client.activity.website.media;

import com.google.gwt.user.client.ui.FileUpload;

public class MediaFiles {

    private FileUpload mediaUploadFile = new FileUpload();
    private FileUpload speakerUploadFile = new FileUpload();
    private FileUpload descriptionUploadFile = new FileUpload();

    private static MediaFiles mediaFiles = new MediaFiles();

    private MediaFiles() {
	mediaUploadFile.setName("MediaUpload");
	mediaUploadFile.setTitle("Media Upload");
	mediaUploadFile.getElement().setAttribute("accept", ".mp3,.mp4");

	speakerUploadFile.setName("SpeakerPhoto");
	speakerUploadFile.setTitle("Speaker Photo");
	speakerUploadFile.getElement().setAttribute("accept", ".png,.jpg,.gif,.jpeg");

	descriptionUploadFile.setName("DescriptionPhoto");
	descriptionUploadFile.setTitle("Description Photo");
	descriptionUploadFile.getElement().setAttribute("accept", ".png,.jpg,.gif,.jpeg");
    }

    public static MediaFiles getInstance() {
	return mediaFiles;
    }

    public FileUpload getMediaUploadFile() {
	return mediaUploadFile;
    }

    public FileUpload getSpeakerUploadFile() {
	return speakerUploadFile;
    }

    public FileUpload getDescriptionUploadFile() {
	return descriptionUploadFile;
    }
}
