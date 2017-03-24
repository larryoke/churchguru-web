package com.laotek.churchguru.web.clientm.dispatch;

import com.google.gwt.place.shared.PlaceController;
import com.laotek.churchguru.web.clientm.MobileFactory;

public class MobileContext {

    private static MobileContext mobileContext = new MobileContext();

    private PlaceController placeController;

    private DispatchMobileClient dispatchClient;

    private String clientSessionId;

    private MobileFactory clientFactory;

    private MobileContext() {

    }

    public static MobileContext getInstance() {
	return mobileContext;
    }

    public void initPlaceController(PlaceController placeController) {
	this.placeController = placeController;
    }

    public PlaceController getPlaceController() {
	return placeController;
    }

    public String getClientSessionId() {
	return clientSessionId;
    }

    public void setClientSessionId(String clientSessionId) {
	this.clientSessionId = clientSessionId;
    }

    public void initDispatchClient(DispatchMobileClient dispatchClient) {
	this.dispatchClient = dispatchClient;
    }

    public DispatchMobileClient getDispatchClient() {
	return dispatchClient;
    }

    public void decorateClientSessionId(AbstractDispatchAction action) {
	action.setClientSessionId(clientSessionId);
    }

    public MobileFactory getClientFactory() {
	return clientFactory;
    }

    public void setClientFactory(MobileFactory clientFactory) {
	this.clientFactory = clientFactory;
    }
}
