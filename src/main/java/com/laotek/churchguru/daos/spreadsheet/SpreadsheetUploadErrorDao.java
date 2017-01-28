package com.laotek.churchguru.daos.spreadsheet;

import java.util.List;

import com.laotek.churchguru.model.SpreadsheetUploadError;

public interface SpreadsheetUploadErrorDao {
    int saveErrors(List<SpreadsheetUploadError> error);

    List<SpreadsheetUploadError> errors();
}
