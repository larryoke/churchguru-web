package com.laotek.churchguru.daos.mobile.prayerrequest;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.PrayerRequest;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PrayerRequestDaoImpl extends BaseSessionFactory implements
	PrayerRequestDao {

    @Override
    public void save(Title title, String forename, String surname,
	    String emailAddress, String mobile, String message) {
	PrayerRequest prayerRequest = new PrayerRequest();
	prayerRequest.setTitle(title);
	prayerRequest.setIdentifier(RandomStringUtils.random(30, true, true));
	prayerRequest.setEmailAddress(emailAddress);
	prayerRequest.setForenames(forename);
	prayerRequest.setSurname(surname);
	prayerRequest.setMobile(mobile);
	prayerRequest.setMessage(message);
	prayerRequest.setCreatedDate(new Date());
	prayerRequest.setLastUpdatedDate(new Date());
	getCurrentSession().persist(prayerRequest);
    }
}
