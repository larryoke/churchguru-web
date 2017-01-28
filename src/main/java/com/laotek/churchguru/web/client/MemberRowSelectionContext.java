package com.laotek.churchguru.web.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberRowSelectionContext {
	
	private static MemberRowSelectionContext rowSelectionContext = new MemberRowSelectionContext();
	
	private Set<String> selectedIds = new HashSet<String>();
	
	private MemberRowSelectionContext(){}
	
	public static MemberRowSelectionContext getInstance(){
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
