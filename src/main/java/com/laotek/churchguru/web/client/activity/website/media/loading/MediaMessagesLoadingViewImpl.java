package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.website.media.BaseViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.MediaFileCurrentUpload;
import com.laotek.churchguru.web.client.activity.website.media.MediaFiles;
import com.laotek.churchguru.web.client.activity.website.media.MediaMessageNewPlace;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;

public class MediaMessagesLoadingViewImpl extends BaseViewImpl implements MediaMessagesLoadingView {

    private Presenter presenter;
    private FlexTable messagePanels = new FlexTable();
    private FlexTable mainPanel = new FlexTable();
    private static HTML loadingProgress = new HTML();

    public MediaMessagesLoadingViewImpl() {

    }

    @Override
    public Widget asWidget() {
	mainPanel.setBorderWidth(0);
	mainPanel.setWidget(0, 0, messagePanels);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	return getMainLayout("images/app/download.png", "Manage Messages",
		new RoundedCornerPanel("Loading Message...", messagePanels));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init(String messageID) {
	Window.setTitle("Loading messages ...");
	int row = 0;

	messagePanels.setWidget(++row, 0, new HTML("Message Descrition"));
	messagePanels.setWidget(++row, 0, new Image("images/app/media_loading.gif"));
	messagePanels.setWidget(++row, 0, loadingProgress);
	messagePanels.setWidget(++row, 0, new HTML("&nbsp;"));

	if (MediaFiles.getInstance().getMediaFileCurrentUpload().equals(MediaFileCurrentUpload.SPEAKER)) {
	    FileUpload speakerFileUpload = MediaFiles.getInstance().getSpeakerUploadFile();
	    String filename = speakerFileUpload.getFilename();
	    uploadFile(messageID, speakerFileUpload.getElement(), new String[] { "images", "speaker", filename });

	} else if (MediaFiles.getInstance().getMediaFileCurrentUpload().equals(MediaFileCurrentUpload.DESC)) {
	    FileUpload descFileUpload = MediaFiles.getInstance().getDescriptionUploadFile();
	    String filename = descFileUpload.getFilename();
	    uploadFile(messageID, descFileUpload.getElement(), new String[] { "images", "messages", "desc", filename });

	} else if (MediaFiles.getInstance().getMediaFileCurrentUpload().equals(MediaFileCurrentUpload.MESSAGE)) {
	    FileUpload mediaFileUpload = MediaFiles.getInstance().getMediaUploadFile();
	    String filename = mediaFileUpload.getFilename();
	    uploadFile(messageID, mediaFileUpload.getElement(), new String[] { "media", "messages", filename });
	}
    }

    private static void onProgressReport(String progress) {
	Window.alert("in progress");
	loadingProgress.setHTML(progress);
    }

    private static void onComplete(String messageID) {
	Window.alert("on complete");
	ApplicationContext.getInstance().getPlaceController().goTo(new MediaMessageNewPlace(messageID));

    }

    private native void uploadFile(final String messageID, final Element data, String... nodes) /*-{
												//@formatter:off
												
																   
												$wnd.firebase.auth().onAuthStateChanged(function(user) {
												if (user) {
												
												
												
												
																  
												
												// Points to the root reference
												var storageRef = $wnd.firebase.storage().ref();
												
												
												
																   
												// Points to 'images'
												var ref = storageRef.child(nodes[0]);
												
												
												for (i = 1; i < nodes.length; i++){
												alert(nodes[i]);
												ref = ref.child(nodes[i]);
												}
												
												
												// Points to 'images/space.jpg'
												// Note that you can use variables to create child values
												//var fileName = 'space.jpg';
												//var spaceRef = imagesRef.child(fileName);
												
												
												
												
												var uploadTask = ref.put(data.files[0]).then(function(snapshot) {
												console.log('Uploaded a blob or file!');
												$wnd.onComplete = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onComplete(*)(messageID);
												});
												
												
																   
																   
																   
												uploadTask.on('state_changed', function(snapshot){
												
												
												
												var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
												console.log('Upload is ' + progress + '% done');									
												$wnd.onProgressReport = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onProgressReport(*)( progress);							   
												
												
												
												switch (snapshot.state) {
												
												
												case firebase.storage.TaskState.PAUSED: // or 'paused'
												console.log('Upload is paused');
												break;
												
												
												case firebase.storage.TaskState.RUNNING: // or 'running'
												console.log('Upload is running');
												break;
												
												
												}
												}, 
												
												function(error) {
												// Handle unsuccessful uploads
												}, 
												
												
												function() {
												// Handle successful uploads on complete
												// For instance, get the download URL: https://firebasestorage.googleapis.com/...
												var downloadURL = uploadTask.snapshot.downloadURL;
												alert("Download URL: " + downloadURL);
												});
												
												
												
												
												
												}//if user
												}); //auth
												
												//@formatter:on
												
												}-*/;

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("eStore");
    }

    @Override
    public void initWidgets() {

    }
}
