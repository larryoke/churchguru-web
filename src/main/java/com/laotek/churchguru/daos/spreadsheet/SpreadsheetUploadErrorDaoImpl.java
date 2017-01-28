package com.laotek.churchguru.daos.spreadsheet;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.SpreadsheetUploadError;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class SpreadsheetUploadErrorDaoImpl extends BaseSessionFactory implements
	SpreadsheetUploadErrorDao {

    @Override
    public int saveErrors(List<SpreadsheetUploadError> errors) {
	getCurrentSession().createQuery("delete from SpreadsheetUploadError")
		.executeUpdate();

	int count = 0;
	for (SpreadsheetUploadError error : errors) {
	    getCurrentSession().persist(error);
	    ++count;
	}
	return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SpreadsheetUploadError> errors() {
	return getCurrentSession().createQuery(
		"from SpreadsheetUploadError errors").list();
    }

}
