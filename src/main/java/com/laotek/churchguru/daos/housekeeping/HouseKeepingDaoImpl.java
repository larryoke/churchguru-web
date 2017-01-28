package com.laotek.churchguru.daos.housekeeping;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.TempPhoto;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HouseKeepingDaoImpl extends BaseSessionFactory implements
	HouseKeepingDao {

    @Override
    public void removeRedundantPhotos() {

    }

    @Override
    public void addNewTempPhoto(String photoName) {
	TempPhoto tempPhoto = new TempPhoto();
	tempPhoto.setFilename(photoName);
	getCurrentSession().persist(tempPhoto);
    }

}
