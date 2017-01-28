package com.laotek.churchguru.web.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuestRowSelectionContext {
	
	private static GuestRowSelectionContext rowSelectionContext = new GuestRowSelectionContext();
	
	private Set<String> selectedIds = new HashSet<String>();
	
	private GuestRowSelectionContext(){}
	
	public static GuestRowSelectionContext getInstance(){
		return rowSelectionContext;
	}
	
	public void selectIdentifier(String identifier, boolean select){
		if (select){
			selectedIds.add(identifier);
		}else{
			selectedIds.remove(identifier);
		}
	}
	
	public void selectIds(List<String> ids){
		for(String identifier : ids){
			selectedIds.add(identifier);
		}
	}
	
	public boolean isSelected(String id){
		return selectedIds.contains(id);
	}
	
	public void clear(){
		selectedIds.clear();
	}
	
	public int size(){
		return selectedIds.size();
	}
	
	public Set<String> copyIds(){
		Set<String> ids = new HashSet<String>();
		for(String id : selectedIds){
			ids.add(id);
		}
		return ids;
	}
}
