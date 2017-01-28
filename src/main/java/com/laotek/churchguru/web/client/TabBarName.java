package com.laotek.churchguru.web.client;

public enum TabBarName {
	DASHBOARD("Dashboard", 0), 
	MANAGE_MEMBERS("Manage Members", 1), 
	MANAGE_GUESTS("Manage Guests", 2), 
	MANAGE_USERS("Manage Users", 3), 
	EMAIL_MESSAGING("Email Messaging", 4),
	TEXT_MESSAGING("Text Messaging", 5),
	DEPARTMENTS("Manage Departments", 6),
	SYSTEM_SETTINGS("System Settings", 7);
	
	private String name;
	private int index;
	
	private TabBarName(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIndex(){
		return index;
	}
}
