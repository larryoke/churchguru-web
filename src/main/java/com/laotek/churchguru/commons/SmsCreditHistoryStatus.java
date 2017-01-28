package com.laotek.churchguru.commons;

public enum SmsCreditHistoryStatus {
    
    CREDIT_PURCHASE("Credit Purchase", true), VENDOR_PROMOTION("Vendor Promotion", true), USAGE("Usage", false);

    private boolean isCredit;
    private String desc;
    
    private SmsCreditHistoryStatus(String desc, boolean isCredit){
	this.desc = desc;
	this.isCredit = isCredit;
    }
    public String getDesc() {
        return desc;
    }
    public boolean isCredit() {
        return isCredit;
    }
}
