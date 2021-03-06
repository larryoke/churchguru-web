package com.laotek.churchguru.web.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRowSelectionContext {

    private static UserRowSelectionContext rowSelectionContext = new UserRowSelectionContext();

    private Set<String> selectedIds = new HashSet<String>();

    private UserRowSelectionContext() {
    }

    public static UserRowSelectionContext getInstance() {
	return rowSelectionContext;
    }

    public void selectId(String id, boolean select) {
	if (select) {
	    selectedIds.add(id);
	} else {
	    selectedIds.remove(id);
	}
    }

    public void selectIds(List<String> ids) {
	for (String id : ids) {
	    selectedIds.add(id);
	}
    }

    public boolean isSelected(String id) {
	return selectedIds.contains(id);
    }

    public void clear() {
	selectedIds.clear();
    }

    public int size() {
	return selectedIds.size();
    }

    public Set<String> copyIds() {
	Set<String> ids = new HashSet<String>();
	for (String id : selectedIds) {
	    ids.add(id);
	}
	return ids;
    }
}
