package com.laotek.churchguru.web.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageRowSelectionContext {
	
	private static MessageRowSelectionContext rowSelectionContext = new MessageRowSelectionContext();
	
	private Set<String> selectedIds = new HashSet<String>();
	
	private MessageRowSelectionContext(){}
	
	public static MessageRowSelectionContext getInstance(){
		return rowSelectionContext;
	}
	
	public void selectId(String identifier, boolean select){
		if (select){
			selectedIds.add(identifier);
		}else{
			selectedIds.remove(identifier);
		}
	}
	
	public void selectIds(List<String> ids){
		for(String id : ids){
			selectedIds.add(id);
		}
	}
	
	public boolean isSelected(String identifier){
		return selectedIds.contains(identifier);
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
