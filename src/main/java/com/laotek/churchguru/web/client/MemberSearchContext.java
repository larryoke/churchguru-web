package com.laotek.churchguru.web.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.shared.enums.MemberSearchType;

public class MemberSearchContext {

    private static MemberSearchContext searchContext = new MemberSearchContext();

    private MemberSearchType currentSearchType;
    private Map<Integer, List<String>> pages = new HashMap<Integer, List<String>>();
    private int totalCount = 0;
    private int currentPageIndex = 0;
    private int currentRowIndex = 0;

    private MemberSearchContext() {
    }

    public void clear() {
	pages.clear();
	totalCount = 0;
	currentPageIndex = 0;
	currentRowIndex = 0;
    }

    public static MemberSearchContext getInstance() {
	return searchContext;
    }

    public Map<Integer, List<String>> getResults() {
	return pages;
    }

    public void initSearchResults(MemberSearchType searchType,
	    Map<Integer, List<String>> results, int totalCount) {
	this.currentSearchType = searchType;
	this.pages = results;
	this.totalCount = totalCount;
    }

    public MemberSearchType getCurrentSearchType() {
	return currentSearchType;
    }

    public List<String> getCurrentPage() {
	return pages.get(currentPageIndex);
    }

    public int getTotalCount() {
	return totalCount;
    }

    public int getCurrentPageIndex() {
	return currentPageIndex;
    }

    public boolean hasPreviousBrowsePage(int currentPageIndex) {
	if (currentPageIndex > 0) {
	    return true;
	}
	return false;
    }

    public boolean hasNextBrowsePage(int currentPageIndex) {
	if (currentPageIndex + 1 < pages.size()) {
	    return true;
	}
	return false;
    }

    public List<String> getPageIds(int pageIndex) {
	currentPageIndex = pageIndex;
	return pages.get(pageIndex);
    }

    public int getLastPageIndex() {
	return pages.size() - 1;
    }

    public int getPages() {
	return pages.size();
    }

    public String getFirstRecordId() {
	String firstRecordId = pages.get(0).get(0);
	currentPageIndex = 0;
	currentRowIndex = 0;
	return firstRecordId;
    }

    public String getLastRecordId() {
	int lastPageIndex = getLastPageIndex();
	List<String> ids = pages.get(lastPageIndex);
	int lastRowIndex = ids.size() - 1;
	String lastRecordId = ids.get(lastRowIndex);

	currentRowIndex = lastRowIndex;
	currentPageIndex = lastPageIndex;
	return lastRecordId;
    }

    public int getCurrentRecordIndex() {
	int pageSize = pages.get(0).size();
	return ((currentPageIndex * pageSize) + currentRowIndex);
    }

    public boolean hasPreviousRecord() {
	if (currentPageIndex > 0
		|| (currentPageIndex == 0 && currentRowIndex > 0)) {
	    return true;
	}
	return false;
    }

    public boolean hasNextRecord() {
	if (currentPageIndex + 1 == pages.size()) {
	    List<String> ids = pages.get(currentPageIndex);
	    int size = ids.size();
	    if (currentRowIndex + 1 == size) {
		return false;
	    }
	}
	return true;
    }

    public String getPreviousRecordId() {
	if (currentRowIndex == 0) {
	    List<String> ids = pages.get(--currentPageIndex);
	    int size = ids.size();
	    currentRowIndex = size - 1;
	    return ids.get(currentRowIndex);
	} else {
	    List<String> ids = pages.get(currentPageIndex);
	    return ids.get(--currentRowIndex);
	}
    }

    public String getNextRecordId() {
	List<String> ids = pages.get(currentPageIndex);
	if (currentRowIndex + 1 == ids.size()) {
	    currentRowIndex = 0;
	    return pages.get(++currentPageIndex).get(currentRowIndex);
	} else {
	    return pages.get(currentPageIndex).get(++currentRowIndex);
	}
    }

    public void updateCurrentRowIndex(int currentRowIndex) {
	this.currentRowIndex = currentRowIndex;
    }

    public List<String> getAllIds() {
	List<String> all = new ArrayList<String>();
	for (List<String> ids : pages.values()) {
	    all.addAll(ids);
	}
	return all;
    }
}
