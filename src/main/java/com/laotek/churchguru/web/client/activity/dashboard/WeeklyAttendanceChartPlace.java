package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WeeklyAttendanceChartPlace extends Place {
    private String name;

    public WeeklyAttendanceChartPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<WeeklyAttendanceChartPlace> {
	@Override
	public String getToken(WeeklyAttendanceChartPlace place) {
	    return place.getName();
	}

	@Override
	public WeeklyAttendanceChartPlace getPlace(String token) {
	    return new WeeklyAttendanceChartPlace(token);
	}
    }
}
