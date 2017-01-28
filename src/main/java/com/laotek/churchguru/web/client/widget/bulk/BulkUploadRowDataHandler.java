package com.laotek.churchguru.web.client.widget.bulk;

import java.util.Map;

public interface BulkUploadRowDataHandler {

    void saveOrDeleteRowData(Map<String, String> rowData, boolean save);

    void publishData();
}
