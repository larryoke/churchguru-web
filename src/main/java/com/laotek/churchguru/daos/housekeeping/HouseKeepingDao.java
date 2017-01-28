package com.laotek.churchguru.daos.housekeeping;


public interface HouseKeepingDao {
	
	void addNewTempPhoto(String photoName);
	
	void removeRedundantPhotos();
}
