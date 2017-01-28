package com.laotek.churchguru.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleServiceNotificationService {

    @Autowired
    @Value("${gcm.api.key}")
    private String gcmApiKey;

    public void sendNotification(String message) throws Exception {

	JSONObject notificationJData = new JSONObject();
	notificationJData.put("title", "Notices and Events");
	notificationJData.put("body", message);
	notificationJData.put("sound", "default");

	JSONObject dataJData = new JSONObject();
	dataJData.put("message", message);

	JSONObject jGcmData = new JSONObject();
	jGcmData.put("to", "/topics/global");
	// jGcmData.put("priority", "high");
	jGcmData.put("content_available", true);
	jGcmData.put("notification", notificationJData);
	// jGcmData.put("data", dataJData);

	// Create connection to send GCM Message request.
	URL url = new URL("https://fcm.googleapis.com/fcm/send");
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestProperty("Authorization", "key=" + gcmApiKey);
	conn.setRequestProperty("Content-Type", "application/json");
	conn.setRequestMethod("POST");
	conn.setDoOutput(true);

	// Send GCM message content.
	OutputStream outputStream = conn.getOutputStream();
	outputStream.write(jGcmData.toString().getBytes());

	// Read GCM response.
	InputStream inputStream = null;
	if (conn.getResponseCode() >= 400) {
	    inputStream = conn.getErrorStream();
	} else {
	    inputStream = conn.getInputStream();
	}

	String resp = IOUtils.toString(inputStream);
	System.out.println(resp);
	System.out
		.println("Check your device/emulator for notification or logcat for "
			+ "confirmation of the receipt of the GCM message.");

    }
}
