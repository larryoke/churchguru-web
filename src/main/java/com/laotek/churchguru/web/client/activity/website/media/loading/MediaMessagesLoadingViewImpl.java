package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.media.UpdateMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.UpdateMediaMessageResult;
import com.laotek.churchguru.web.client.activity.website.media.BaseViewImpl;
import com.laotek.churchguru.web.client.activity.website.media.MediaFiles;
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
		new RoundedCornerPanel("Upload Message...", messagePanels));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init(String uploadType, String identity) {
	Window.setTitle("Loading messages ...");

	if ("speaker".equals(uploadType)) {
	    messagePanels.setWidget(1, 0, new HTML("Speaker Photo Upload"));
	    FileUpload speakerFileUpload = MediaFiles.getInstance().getSpeakerUploadFile();
	    String filename = speakerFileUpload.getFilename();
	    uploadFile("speaker", identity, speakerFileUpload.getElement(),
		    new String[] { "images", "speaker", filename });

	} else if ("desc".equals(uploadType)) {
	    messagePanels.setWidget(1, 0, new HTML("Message Descrition Upload"));
	    FileUpload descFileUpload = MediaFiles.getInstance().getDescriptionUploadFile();
	    String filename = descFileUpload.getFilename();
	    uploadFile("desc", identity, descFileUpload.getElement(),
		    new String[] { "images", "messages", "desc", filename });

	} else if ("message".equals(uploadType)) {
	    messagePanels.setWidget(1, 0, new HTML("Message Media Upload"));
	    FileUpload mediaFileUpload = MediaFiles.getInstance().getMediaUploadFile();
	    String filename = mediaFileUpload.getFilename();
	    uploadFile("message", identity, mediaFileUpload.getElement(),
		    new String[] { "media", "messages", filename });
	}

	messagePanels.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
	messagePanels.setWidget(2, 0, new Image("images/app/media_loading.gif"));
	messagePanels.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	messagePanels.setWidget(3, 0, loadingProgress);
	messagePanels.getFlexCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
	messagePanels.setWidget(4, 0, new HTML("&nbsp;"));
    }

    private static void onProgressReport(String progress) {
	loadingProgress.setHTML(progress + "%");
    }

    private static void onComplete(String uploadType, String identity, String downloadURL) {

	final UpdateMediaMessageAction action = new UpdateMediaMessageAction(uploadType, identity, downloadURL);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<UpdateMediaMessageResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("Error updating URL " + action.getUploadType());
		History.back();
	    }

	    @Override
	    public void onSuccess(UpdateMediaMessageResult result) {
		History.back();
	    }
	});
    }

    private native void uploadFile(final String uploadType, final String identity, final Element data,
	    String... nodes) /*-{
			     //@formatter:off
			     
			     	   
			     $wnd.firebase.auth().onAuthStateChanged(function(user) {
			     if (user) {
			     	  
			     
			     	// Points to the root reference
			     	var storageRef = $wnd.firebase.storage().ref();
			     	   
			     	// Points to 'images'
			     	var ref = storageRef.child(nodes[0]);
			     
			     
			     	for (i = 1; i < nodes.length; i++){
			     		ref = ref.child(nodes[i]);
			     	}
			     
			     	var uploadTask = ref.put(data.files[0]).then(
			     		function(snapshot) {
			          		var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;						
			             		$wnd.onProgressReport = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onProgressReport(*)( progress);
			          		
			          		switch (snapshot.state) {			     			     
			               		case 'paused':
			               			console.log('Upload is paused');
			               			break;			     			     
			                    	case 'running':
			                    	     console.log('Upload is running');
			                    	     break;		     			     
			                    	case  'success':
			                    	     console.log('Upload is done');			     
			               			var downloadURL = snapshot.downloadURL;			     
			               			$wnd.onComplete = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onComplete(*)(uploadType, identity , downloadURL);		       
			                    	     	break;			     			     
			               		}
			             	},
			        	function(error) {
			             	// Handle unsuccessful uploads
			          		 switch (error.code) {
			                         case 'storage/unauthorized':
			                           // User doesn't have permission to access the object
			                            alert('permission error');
			                           break;
			                     
			                         case 'storage/canceled':
			                           // User canceled the upload
			                            alert('cancelled');
			                           break;
			                  	}
			           	}
			         );
			     	
			     	
			     	
			     	
			     	
			     	
			     	
			     	
			     	
			     	var next = function(snapshot) {
			          	var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;						
			             $wnd.onProgressReport = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onProgressReport(*)( progress);				     
			     		switch (snapshot.state) {			     			     
			     		case firebase.storage.TaskState.PAUSED: // or 'paused'
			     			console.log('Upload is paused');
			     			break;			     			     
			          	case firebase.storage.TaskState.RUNNING: // or 'running'
			          	     console.log('Upload is running');
			          	     break;			     			     
			     		}
			     	};
			        var error = function(error) {
			        	// Handle unsuccessful uploads
			     		 switch (error.code) {
			                    case 'storage/unauthorized':
			                      // User doesn't have permission to access the object
			                       alert('permission error');
			                      break;
			                
			                    case 'storage/canceled':
			                      // User canceled the upload
			                       alert('cancelled');
			                      break;
			             	}
			        };
			        var complete = function() {			     
			     		var downloadURL = snapshot.downloadURL;			     
			     		$wnd.onComplete = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onComplete(*)(uploadType, identity , downloadURL);
			        };
			        //uploadTask.on($wnd.firebase.storage.TaskEvent.STATE_CHANGED, next, error, complete);
			         
			         
			        // Just listening for progress/state changes, this is legal.
			        uploadTask.on($wnd.firebase.storage.TaskEvent.STATE_CHANGED, function(snapshot) {
			        	alert('in progress');
			          	var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;						
			             $wnd.onProgressReport = @com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingViewImpl::onProgressReport(*)( progress);
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
