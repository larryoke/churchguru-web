package com.laotek.churchguru.daos.mobile.prayerrequest;

import com.laotek.churchguru.model.shared.enums.Title;

public interface PrayerRequestDao {

    void save(Title title, String forename, String surname,
	    String emailAddress, String mobile, String message);
}
